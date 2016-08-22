package com.syscraft.caremeds;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;

import com.syscraft.caremeds.Utill_parser.JSONParser;
import com.syscraft.caremeds.constants.App_Constants;
import com.syscraft.caremeds.database.CRMDS_Database_Handler;
import com.syscraft.caremeds.dialogs.AboutDialog;
import com.syscraft.caremeds.model.OFFLINE_DATA_MODEL;
import com.syscraft.caremeds.serverCommunication.NetworkAvailablity;
import com.syscraft.caremeds.service.Demoservice;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;


public class LoginActivity extends AppCompatActivity {

    Intent i;
    Button login, btn_test;
    EditText username, password;
    String name, pswd;
    private JSONParser jsonParser;
    private App_Constants app_constants;
    CRMDS_Database_Handler mydbobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CareMeds eMar");
        setSupportActionBar(toolbar);
        app_constants = new App_Constants();
        jsonParser = new JSONParser(LoginActivity.this);
        mydbobj = new CRMDS_Database_Handler(LoginActivity.this);

        if (NetworkAvailablity.chkStatus(this)) {
        } else {
            App_Constants.showAlertDialog("Alert", "Please check your internet connection.", this, false);
        }
        username = (EditText) findViewById(R.id.edtUsername);
        password = (EditText) findViewById(R.id.edtPassword);
        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.e("login ", "username ---> " + username.getText().toString());
                        Log.e("login ", " password --->" + password.getText().toString());

                        if (username.getText().toString().length() > 0) {
                            if (password.getText().toString().length() > 0) {
                                closeKeyPad();

                                String userName = username.getText().toString().trim();
                                String passWord = password.getText().toString().trim();

                                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.LOGIN_ACCOUNT_NAME, username.getText().toString().trim());
                                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.IS_LOGIN, "true");

                                String BaseUrl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                                Log.e("login ", " BaseUrl --->" + BaseUrl_value);

                                if (BaseUrl_value != null) {

                                    boolean value2 = URLUtil.isHttpsUrl(BaseUrl_value);
                                    boolean value3 = URLUtil.isHttpUrl(BaseUrl_value);
                                    if (value2 == true || value3 == true) {
                                        boolean is_anything_remains_to_sync = check_is_anything_remains_to_sync();
                                        if (is_anything_remains_to_sync) {
                                            String lastlogin_userid = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_USERID, "");
                                            String lastlogin_userpass = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_USERPASSWORD, "");
                                            Log.e("login ", " lastlogin_userid --->" + lastlogin_userid);
                                            Log.e("login ", " lastlogin_userpass --->" + lastlogin_userpass);
                                            if (lastlogin_userid.equals(userName) && lastlogin_userpass.equals(passWord)) {
                                                if (!isMyServiceRunning(Demoservice.class)) {
                                                    startService(new Intent(getBaseContext(), Demoservice.class));
                                                } else {
                                                    stopService(new Intent(getBaseContext(), Demoservice.class));
                                                    startService(new Intent(getBaseContext(), Demoservice.class));
                                                }
                                                Intent i = new Intent(LoginActivity.this, PatientsActivity.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                App_Constants.showAlertDialog("Alert", "you will not be able to login as any other user.", LoginActivity.this, false);
                                            }

                                        } else {
                                            Log.e("login ", " login_Activity --->");
                                            if (NetworkAvailablity.chkStatus(LoginActivity.this)) {
                                                new LoginWebService().execute(userName, passWord);
                                            } else {

                                                String lastlogin_userid = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_USERID, "");
                                                String lastlogin_userpass = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_USERPASSWORD, "");
                                                Log.e("login ", " else lastlogin_userid --->" + lastlogin_userid);
                                                Log.e("login ", "else  lastlogin_userpass --->" + lastlogin_userpass);
                                                if (lastlogin_userid.equals(userName) && lastlogin_userpass.equals(passWord)) {
                                                    if (!isMyServiceRunning(Demoservice.class)) {
                                                        startService(new Intent(getBaseContext(), Demoservice.class));
                                                    } else {
                                                        stopService(new Intent(getBaseContext(), Demoservice.class));
                                                        startService(new Intent(getBaseContext(), Demoservice.class));
                                                    }
                                                    Intent i = new Intent(LoginActivity.this, PatientsActivity.class);
                                                    startActivity(i);
                                                    finish();
                                                } else {
                                                    App_Constants.showAlertDialog("Alert", "Please check network connection!", LoginActivity.this, false);
                                                }
                                            }
                                        }
                                    } else {
                                        App_Constants.showAlertDialog("Alert", "Cannot convert host to URI: https://", LoginActivity.this, false);
                                    }
                                } else {
                                    App_Constants.showAlertDialog("Alert", "Please enter BaseUrl in Setting Page", LoginActivity.this, false);
                                }

                            } else {
                                App_Constants.showAlertDialog("Alert", "Please enter a username and password", LoginActivity.this, false);
                            }
                        } else {
                            App_Constants.showAlertDialog("Alert", "Please enter a username and password", LoginActivity.this, false);
                        }
                    }
                }
        );
    }

    public void closeKeyPad() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            i = new Intent(LoginActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_about) {
            AboutDialog aboutDialog = new AboutDialog();
            aboutDialog.show(getSupportFragmentManager(), "About");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private boolean check_is_anything_remains_to_sync() {
        boolean b1 = false;
        ArrayList<OFFLINE_DATA_MODEL> offline_modelArrayList = new ArrayList<OFFLINE_DATA_MODEL>();
        String caretaker_id = PreferenceConnector.readString(LoginActivity.this, PreferenceConnector.LAST_LOGIN_CARETAKER_ID, "");
        offline_modelArrayList = mydbobj.get_offline_Listfromdb(caretaker_id);
        Log.e("sync...", "Login offline_modelArrayList size --->" + offline_modelArrayList.size());

        if (offline_modelArrayList.size() > 0) {
            b1 = true;
        } else {
            b1 = false;
        }
        return b1;
    }

    class LoginWebService extends AsyncTask<String, String, String> {

        HttpResponse loginResponse;
        private String username_val;
        private String password_val;
        Dialog dlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dlg = app_constants.dialog(LoginActivity.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                String weburl = baseurl_value + "/users/sign_in";
                json.put("login", params[0]);
                json.put("password", params[1]);
                json1.put("user_login", json);
                username_val = params[0];
                password_val = params[1];

                result = postdata_method(weburl, json1.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.e("login", "login---->" + result);
            // App_Constants.showAlertDialog("Alert", result, LoginActivity.this, false);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            JSONObject dataObj = null;
            try {
                dataObj = new JSONObject(result);
                String success = dataObj.optString("success");
                if (success.equals("true")) {
                    try {
                        String token = dataObj.optString("authentication_token");
                        String username = dataObj.optString("username");
                        String userId = dataObj.optString("user_id");
                        String fullname = dataObj.optString("fullname");
                        String can_audit = dataObj.optString("can_audit");
                        int session_timeout = dataObj.optInt("session_timeout");
                        int warntime = session_timeout - 5;
                        Log.e("token", token);
                        Log.e("username", username);
                        Log.e("userId", userId);
                        Log.e("fullname", fullname);

                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, token);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.USERNAME, username);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.USERID, userId);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.FULLNAME, fullname);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.CAN_AUDIT, can_audit);
                        PreferenceConnector.writeInteger(LoginActivity.this, PreferenceConnector.SESSION_TIMEOUT, session_timeout);
                        PreferenceConnector.writeInteger(LoginActivity.this, PreferenceConnector.WARNING_TIME, warntime);
                        PreferenceConnector.writeLong(LoginActivity.this, PreferenceConnector.LAST_ACTIVITY_TIME, (new Date().getTime()));

                        Log.e("TIMEOUT_TIME", "" + (session_timeout * 60 * 1000));

                        if (!isMyServiceRunning(Demoservice.class)) {
                            startService(new Intent(getBaseContext(), Demoservice.class));
                        } else {
                            stopService(new Intent(getBaseContext(), Demoservice.class));
                            startService(new Intent(getBaseContext(), Demoservice.class));
                        }

                        Intent i = new Intent(LoginActivity.this, PatientsActivity.class);
                        PreferenceConnector.writeBoolean(LoginActivity.this, PreferenceConnector.IS_USER_DONE_LOGIN_ONCE, true);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_USERID, username_val);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_USERPASSWORD, password_val);
                        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.LAST_LOGIN_CARETAKER_ID, userId);
                        startActivity(i);
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    App_Constants.showAlertDialog("Alert", "Unauthorized", LoginActivity.this, false);
                }
            } catch (Exception e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
        }

        private String convertStreamToString(InputStream in) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String LoginName_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LOGIN_ACCOUNT_NAME, "");

        if (!LoginName_value.isEmpty()) {
            username.setText("" + LoginName_value);
            username.setSelection(username.getText().length());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public String postdata_method(String url, String json_params) {
        JSONObject jObj = null;
        InputStream is = null;
        String json_response = "";
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000); //Timeout Limit
            HttpPost httpPost = new HttpPost(url);
            StringEntity se = new StringEntity(json_params);
            se.setContentType(new BasicHeader("Accept", "application/json"));
            se.setContentType(new BasicHeader("Content-type", "application/json"));
            httpPost.setEntity(se);

            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                StatusLine status = httpResponse.getStatusLine();
                int statusCode = status.getStatusCode();
                Log.e("login response", "" + httpResponse.getStatusLine());
            } catch (final UnknownHostException e5) {
                e5.printStackTrace();
                runOnUiThread(new Runnable() {
                    public void run() {
                        App_Constants.showAlertDialog("Alert", "" + e5.getMessage(), LoginActivity.this, false);
                    }
                });
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            is.close();

            Log.e("sb.toString()", "--------------> " + sb.toString());
            json_response = sb.toString();

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // return JSON String
        return json_response;

    }


}


