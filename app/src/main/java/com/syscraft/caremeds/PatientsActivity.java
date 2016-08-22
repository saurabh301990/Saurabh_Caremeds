package com.syscraft.caremeds;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.syscraft.caremeds.New_Model.Homely_Model_Phase2;
import com.syscraft.caremeds.New_Model.Homely_Today_Mars_Mar_Bean_Phase2;
import com.syscraft.caremeds.New_Model.MeasureModel_Phase2;
import com.syscraft.caremeds.New_Model.NotesModel_Phase2;
import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;
import com.syscraft.caremeds.New_Model.Patient_Model_Phase2;
import com.syscraft.caremeds.New_Model.Today_Mars_Mar_Bean_Phase2;
import com.syscraft.caremeds.New_Model.Yesterday_Mar_Bean;
import com.syscraft.caremeds.Utility.BaseActivity;
import com.syscraft.caremeds.Utill_parser.JSONParser;
import com.syscraft.caremeds.adapters.New_Patients_Adapter;
import com.syscraft.caremeds.constants.App_Constants;
import com.syscraft.caremeds.database.CRMDS_Database_Handler;
import com.syscraft.caremeds.dialogs.AboutDialog;
import com.syscraft.caremeds.model.API_NAME_AND_URL;
import com.syscraft.caremeds.model.Homely_Today_Mars_Mar_Bean;
import com.syscraft.caremeds.model.OFFLINE_DATA_MODEL;
import com.syscraft.caremeds.serverCommunication.NetworkAvailablity;
import com.syscraft.caremeds.service.Demoservice;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

//import com.syscraft.caremeds.model.PRESCRIPTION_COMMON_Model;

public class PatientsActivity extends BaseActivity {

    ListView lvPatients;
    ArrayList<Patient_Model_Phase2> new_patient_Model_List;
    New_Patients_Adapter adptr;
    private JSONParser jsonParser;
    CRMDS_Database_Handler mydbobj;
    App_Constants app_constants;
    private String global_auth_token;
    private String baseurl_value;
    ArrayList<String> getapilist;
    ArrayList<OFFLINE_DATA_MODEL> offline_modelArrayList;
    ArrayList<PRESCRIPTION_Model_Phase2> thiscycle_chklist;
    ArrayList<PRESCRIPTION_Model_Phase2> nextcycle_chklist;
    ArrayList<PRESCRIPTION_Model_Phase2> lastcycle_chklist;
    ArrayList<NotesModel_Phase2> notelist;
    int count;
    ArrayList<API_NAME_AND_URL> urllist;
    Dialog global_dlg;
    ArrayList<MeasureModel_Phase2> measurearraylist;
    ArrayList<Homely_Model_Phase2> new_homely_List;
    ArrayList<PRESCRIPTION_Model_Phase2> returndata_list;

    ArrayList<Today_Mars_Mar_Bean_Phase2> today_mars_mar_bean_list;
    ArrayList<Yesterday_Mar_Bean> yesterday_mars_mar_bean_list = new ArrayList<Yesterday_Mar_Bean>() ;

    String from = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Patients");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        app_constants = new App_Constants();
        setSupportActionBar(toolbar);
        jsonParser = new JSONParser(PatientsActivity.this);
        lvPatients = (ListView) findViewById(R.id.lvPatients);
        new_patient_Model_List = new ArrayList<Patient_Model_Phase2>();
        thiscycle_chklist = new ArrayList<PRESCRIPTION_Model_Phase2>();
        nextcycle_chklist = new ArrayList<PRESCRIPTION_Model_Phase2>();
        lastcycle_chklist = new ArrayList<PRESCRIPTION_Model_Phase2>();
        notelist = new ArrayList<NotesModel_Phase2>();
        measurearraylist = new ArrayList<MeasureModel_Phase2>();
        returndata_list = new ArrayList<PRESCRIPTION_Model_Phase2>();
        new_homely_List = new ArrayList<Homely_Model_Phase2>();
        today_mars_mar_bean_list = new ArrayList<Today_Mars_Mar_Bean_Phase2>();

        mydbobj = new CRMDS_Database_Handler(PatientsActivity.this);
        baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
        baseurl_value = baseurl_value + "/patients/";
        global_auth_token = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");

        from = "oncreate";

//        Date_utility.getCurrentTime();

        lvPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(PatientsActivity.this, PatientDetail.class);
                String name = new_patient_Model_List.get(position).getFullname();
                String dob = new_patient_Model_List.get(position).getDob();
                String gp = new_patient_Model_List.get(position).getGp_name();
                String allergies = new_patient_Model_List.get(position).getAllergies();
                String notes = new_patient_Model_List.get(position).getPatient_notes();
                String patientId = new_patient_Model_List.get(position).getId();
                String patientImagelink = new_patient_Model_List.get(position).getPhoto_image();
                String patientIs_absent = new_patient_Model_List.get(position).getIs_absent();
                String prescribed_time_slots_for_patient_list_array = new_patient_Model_List.get(position).getPrescribed_time_slots_for_patient_list_array();

                intent.putExtra("patientId", patientId);
                intent.putExtra("name", name);
                intent.putExtra("dob", dob);
                intent.putExtra("gp", gp);
                intent.putExtra("allergies", allergies);
                intent.putExtra("notes", notes);
                intent.putExtra("patientImagelink", patientImagelink);
                intent.putExtra("patientIs_absent", patientIs_absent);
                intent.putExtra("prescribed_time_slots_for_patient_list_array", prescribed_time_slots_for_patient_list_array);

                startActivity(intent);
            }
        });

        boolean is_anything_remains_to_sync = check_is_anything_remains_to_sync();
        if (!is_anything_remains_to_sync) {

            if (NetworkAvailablity.chkStatus(PatientsActivity.this)) {
//                new GetPatientList().execute();
                String auth_token = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");

                String Getfulljson_url = baseurl_value + "/patients/full_index.json?authentication_token=" + auth_token;
                new CompleteResponse(Getfulljson_url).execute();

            } else {
                new_patient_Model_List.clear();
                new_patient_Model_List = mydbobj.get_patients_fromdb();
                adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "offline");
                lvPatients.setAdapter(adptr);
            }
        } else {

            new_patient_Model_List.clear();
            new_patient_Model_List = mydbobj.get_patients_fromdb();
            adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "offline");
            lvPatients.setAdapter(adptr);

        }

    }


    private boolean check_is_anything_remains_to_sync() {
        boolean b1 = false;
        ArrayList<OFFLINE_DATA_MODEL> offline_modelArrayList = new ArrayList<OFFLINE_DATA_MODEL>();
        String caretaker_id = PreferenceConnector.readString(PatientsActivity.this, PreferenceConnector.LAST_LOGIN_CARETAKER_ID, "");
        offline_modelArrayList = mydbobj.get_offline_Listfromdb(caretaker_id);
        Log.e("sync...", "PatientsActivity offline_modelArrayList size --->" + offline_modelArrayList.size());

        if (offline_modelArrayList.size() > 0) {
            b1 = true;
        } else {
            b1 = false;
        }
        return b1;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_patients;
    }

    private SearchView searchView = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.patient_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) PatientsActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(PatientsActivity.this.getComponentName()));
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true; // handled
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("onQueryTextChange", "--->" + newText);
                adptr.filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(PatientsActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_about) {
            AboutDialog aboutDialog = new AboutDialog();
            aboutDialog.show(getSupportFragmentManager(), "About");
            return true;
        } else if (id == R.id.action_logout) {
            //SharedPrefrnceCareMeds.setDataInSharedPrefrence(PatientsActivity.this, "IsLogin", "false");
//            onBackPressed();

            logout();

            return true;
        } else if (id == R.id.action_search) {
            return false;
        } else if (id == R.id.action_sync) {

//            Toast.makeText(PatientsActivity.this, "SYNC work is not done yet.......", Toast.LENGTH_SHORT).show();

            if (NetworkAvailablity.chkStatus(PatientsActivity.this)) {

                offline_modelArrayList = new ArrayList<OFFLINE_DATA_MODEL>();

                String caretaker_id = PreferenceConnector.readString(PatientsActivity.this, PreferenceConnector.LAST_LOGIN_CARETAKER_ID, "");
                offline_modelArrayList = mydbobj.get_offline_Listfromdb(caretaker_id);
                Log.e("sync...", "p1 offline_modelArrayList size --->" + offline_modelArrayList.size());

                if (offline_modelArrayList.size() > 0) {
                    count = 0;
                    new SyncingData_AsyncTask(count).execute();
                } else {
                    Toast.makeText(PatientsActivity.this, "No data to sync..", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(PatientsActivity.this, "Please check your Internet connection.....", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
////        SharedPrefrnceCareMeds.setDataInSharedPrefrence(PatientsActivity.this, "IsLogin", "false");
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.IS_LOGIN, "false");
//        finish();
//    }
//


    public void logout() {

        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.IS_LOGIN, "false");

//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.USERNAME, "");
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.USERID, "");
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.FULLNAME, "");
//        PreferenceConnector.writeInteger(PatientsActivity.this, PreferenceConnector.SESSION_TIMEOUT, 10);
//        PreferenceConnector.writeInteger(PatientsActivity.this, PreferenceConnector.WARNING_TIME, 0);
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SECONDRY_USERID, "");
        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SECONDRY_USERFULLNAME, "");

        stopService(new Intent(PatientsActivity.this, Demoservice.class));
        Intent i = new Intent(PatientsActivity.this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        try {
//            finishAffinity();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        finish();
        startActivity(i);


    }

//    class GetPatientList extends AsyncTask<String, String, String> {
//        Dialog dlg;
//        Context context;
//        HttpResponse loginResponse;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dlg = dialog(PatientsActivity.this, "Loading");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String auth_token = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
//            String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
//            baseurl_value = baseurl_value + "/patients?authentication_token=";
//            String GetPatientList_url = baseurl_value + auth_token;
//            String result = "";
//            result = jsonParser.getjson_method(GetPatientList_url);
//            Log.e("Patient response", "----->" + result);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            if (dlg.isShowing()) {
//                dlg.dismiss();
//            }
//            new_patient_Model_List.clear();
//            try {
//                JSONArray jsonArray = new JSONArray(result);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
//
//                    JSONObject jsonObject2 = jsonObject1.optJSONObject("patient");
//                    String address = jsonObject2.optString("address");
//                    String allergies = jsonObject2.optString("allergies");
//                    String careHomeId = jsonObject2.optString("care_home_id");
//                    String createdAt = jsonObject2.optString("created_at");
//                    String cycleBaseDate = jsonObject2.optString("cycle_base_date");
//                    String cycleDuration = jsonObject2.optString("cycle_duration");
//                    String deletedAt = jsonObject2.optString("deleted_at");
//                    String dob = jsonObject2.optString("dob");
//                    String firstName = jsonObject2.optString("first_name");
//                    String gpId = jsonObject2.optString("gp_id");
//                    String gpName = jsonObject2.optString("gp_name");
//                    String id = jsonObject2.optString("id");
//                    String inactive = jsonObject2.optString("inactive");
//                    String name = jsonObject2.optString("name");
//                    String nhsNumber = jsonObject2.optString("nhs_number");
//                    String patientNotes = jsonObject2.optString("patient_notes");
//                    String photoImage = jsonObject2.optString("photo_image");
//                    String room = jsonObject2.optString("room");
//                    String title = jsonObject2.optString("title");
//                    String updatedAt = jsonObject2.optString("updated_at");
//                    String fullName = jsonObject2.optString("fullname");
//                    String is_absent = jsonObject2.optString("is_absent");
//                    String current_absence_start = jsonObject2.optString("current_absence_start");
//                    String current_absence_end = jsonObject2.optString("current_absence_end");
//                    String current_absence_reason = jsonObject2.optString("current_absence_reason");
//
//                    JSONArray jsonArray1 = jsonObject2.optJSONArray("prescribed_time_slots_for_patient_list");
//                    String prescribed_time_slots_for_patient_list_array = jsonArray1.toString();
//
//                    New_Patient_Model npm = new New_Patient_Model(id, address, allergies, careHomeId, createdAt, cycleBaseDate, cycleDuration, deletedAt, dob, firstName, gpId, gpName, inactive, name, nhsNumber, patientNotes, photoImage, room, title, updatedAt, fullName, prescribed_time_slots_for_patient_list_array, is_absent, current_absence_start, current_absence_end, current_absence_reason);
//                    new_patient_Model_List.add(npm);
//                }
//
//                urllist = new ArrayList<API_NAME_AND_URL>();
//
//
////                for (int k = 0; k < new_patient_Model_List.size(); k++) {
////
////
////                    String patientId = new_patient_Model_List.get(k).getPatientId();
////
////                    String checkin_this_cycle_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=this_cycle&type=check_ins";
////                    String checkin_next_cycle_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=next_cycle&type=check_ins";
////                    String meds_this_cycle_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=this_cycle&view=meds-list";
////                    String meds_next_cycle_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=next_cycle&view=meds-list";
////                    String meds_last_cycle_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=last_cycle&view=meds-list";
////                    String admin_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token;
////                    String homelyweburl = baseurl_value + patientId + "/homely_remedies/?authentication_token=" + global_auth_token + "&note_type=General";
////                    String get_return_tab_weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&type=returns";
////                    String measureweburl = baseurl_value + patientId + "/measurements/?authentication_token=" + global_auth_token + "&last=true&all_types=true";
////                    String getnotes_weburl = baseurl_value + patientId + "/notes?authentication_token=" + global_auth_token + "&note_type=General";
////                    String bsbiteweburl = baseurl_value + patientId + "/notes?authentication_token=" + global_auth_token + "&note_type=BS_Site&limit=1";
////                    String patientrecordweburl = "" + baseurl_value + patientId + "?authentication_token=" + global_auth_token;
////
////                    urllist.add(new API_NAME_AND_URL(checkin_this_cycle_weburl, "checkin_this_cycle_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(checkin_next_cycle_weburl, "checkin_next_cycle_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(meds_this_cycle_weburl, "meds_this_cycle_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(meds_next_cycle_weburl, "meds_this_cycle_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(meds_last_cycle_weburl, "meds_last_cycle_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(admin_weburl, "admin_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(homelyweburl, "homelyweburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(get_return_tab_weburl, "get_return_tab_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(measureweburl, "measureweburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(getnotes_weburl, "getnotes_weburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(bsbiteweburl, "bsbiteweburl", patientId));
////                    urllist.add(new API_NAME_AND_URL(patientrecordweburl, "patientrecordweburl", patientId));
////
////
////                }
////
////                new getPatients_Related_all_Data(urllist.get(0).getApi_web_url(), 0, urllist.get(0).getApi_patientid(), urllist.get(0).getApi_name()).execute();
//
////                for (int i = 0; i < urllist.size(); i++) {
////                    new getPatients_Related_all_Data(urllist.get(i).getApi_web_url(), "" + i, urllist.get(i).getApi_patientid(), urllist.get(i).getApi_name()).execute();
////                }
//
//
//                mydbobj.insert_patients_list(new_patient_Model_List);
//                adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "online");
//                lvPatients.setAdapter(adptr);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            }
//        }
//
//    }

    @Override
    protected void onResume() {
        super.onResume();

//        boolean is_anything_remains_to_sync = check_is_anything_remains_to_sync();
//        if (!is_anything_remains_to_sync) {
//
//            if (NetworkAvailablity.chkStatus(PatientsActivity.this)) {
//                new UpdatePatientList().execute();
//                // new PatientItem().execute();
//            } else {
//                new_patient_Model_List.clear();
//                new_patient_Model_List = mydbobj.get_patients_fromdb();
//                adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "offline");
//                lvPatients.setAdapter(adptr);
//
//            }
//        } else {
//            new_patient_Model_List.clear();
//            new_patient_Model_List = mydbobj.get_patients_fromdb();
//            adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "offline");
//            lvPatients.setAdapter(adptr);
//
//        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        new_patient_Model_List.clear();
        new_patient_Model_List = mydbobj.get_patients_fromdb();
        adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "offline");
        lvPatients.setAdapter(adptr);

    }

    /*****************************************************************/


    public Dialog dialog(Context context, String Message) {

        Dialog com_dialog = new Dialog(context);
        com_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        com_dialog.setContentView(R.layout.custom_dialog);
        com_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        com_dialog.setCancelable(false);

        TextView msg = (TextView) com_dialog.findViewById(R.id.text);
        msg.setText(Message);

        com_dialog.show();
        return com_dialog;
    }


    /***********************************************************************************************************************/


//    private class getPatients_Related_all_Data extends AsyncTask<String, Void, String> {
//
//        String weburl, patientId;
//        int urlcount;
//        String name_of_api;
//
//        public getPatients_Related_all_Data(String web_url, int count, String pId, String apiname) {
//
//            weburl = web_url;
//            urlcount = count;
//            patientId = pId;
//            name_of_api = apiname;
//
//        }
//
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            try {
//                if (!global_dlg.isShowing()) {
//                    global_dlg = app_constants.dialog(PatientsActivity.this, "Please be patient, your data is currently being synced from the server.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                global_dlg = app_constants.dialog(PatientsActivity.this, "Please be patient, your data is currently being synced from the server.");
//            }
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String result = "";
//            try {
//                result = jsonParser.getjson_method(weburl);
//                Log.e("httpGet", "get_Data url--->" + weburl);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            PreferenceConnector.writeLong(PatientsActivity.this, PreferenceConnector.LAST_ACTIVITY_TIME, (new Date().getTime()));
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            Log.e("Response", "get_Data_for---->" + result);
//
//            if (name_of_api.equalsIgnoreCase("checkin_this_cycle_weburl")) {
//
//                parsing_checkin_json("this_cycle", result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("checkin_next_cycle_weburl")) {
//
//                parsing_checkin_json("next_cycle", result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("meds_this_cycle_weburl")) {
//
//                parsing_meds_json("this_cycle", result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("meds_next_cycle_weburl")) {
//
//                parsing_meds_json("next_cycle", result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("meds_last_cycle_weburl")) {
//
//                parsing_meds_json("last_cycle", result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("admin_weburl")) {
//                parsing_admin_response(result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("homelyweburl")) {
//                parse_homely_response(result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("get_return_tab_weburl")) {
//                parse_returntab_response(result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("measureweburl")) {
//                parse_measure_response(result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("getnotes_weburl")) {
//                parse_notes_response(result, patientId);
//
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//
//            } else if (name_of_api.equalsIgnoreCase("bsbiteweburl")) {
//                parse_BSSite_response(result, patientId);
//                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//
//            } else if (name_of_api.equalsIgnoreCase("patientrecordweburl")) {
//                parse_about_patient_detail_response(result, patientId);
//
//                if ((urlcount + 1) < urllist.size()) {
//
//                    new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//                }
//
//
//                if (global_dlg.isShowing()) {
//
//                    if (urlcount == (urllist.size() - 1)) {
//
//                        global_dlg.dismiss();
//                    }
//                }
//
//            }
//
//
////            progDialog.dismiss();
//
//
//        }
//    }

//    private void parse_about_patient_detail_response(String result, String patientId) {
//
//        try {
//            JSONObject obj = new JSONObject(result);
//            JSONObject obj1 = obj.optJSONObject("patient");
//            String id = obj1.optString("id");
//            String address = obj1.optString("address");
//            String allergies = obj1.optString("allergies");
//            String care_home_id = obj1.optString("care_home_id");
//            String created_at = obj1.optString("created_at");
//            String cycle_base_date = obj1.optString("cycle_base_date");
//            String cycle_duration = obj1.optString("cycle_duration");
//            String deleted_at = obj1.optString("deleted_at");
//            String dob = obj1.optString("dob");
//            String first_name = obj1.optString("first_name");
//            String gp_id = obj1.optString("gp_id");
//            String gp_name = obj1.optString("gp_name");
//
//            String inactive = obj1.optString("inactive");
//            String name = obj1.optString("name");
//            String nhs_number = obj1.optString("nhs_number");
//            String patient_notes = obj1.optString("patient_notes");
//            String photo_image = obj1.optString("photo_image");
//            String room = obj1.optString("room");
//            String title = obj1.optString("title");
//            String updated_at = obj1.optString("updated_at");
//            String _global_inr_reading = obj1.optString("inr_reading");
//            String _global_inr_date = obj1.optString("inr_date");
//            String _global_warfarin_dose = obj1.optString("warfarin_dose");
//
//            ABOUT_PATIENT_INR abt_pt_inr = new ABOUT_PATIENT_INR(id, address, allergies, care_home_id, created_at, cycle_base_date, cycle_duration, deleted_at, dob, first_name, gp_id, gp_name, inactive, name, nhs_number, patient_notes, photo_image, room, title, updated_at, _global_inr_reading, _global_inr_date, _global_warfarin_dose);
//            mydbobj.insert_about_patients_inr_detail_table(abt_pt_inr);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    private void parse_BSSite_response(String result, String patientId) {
//
//        ArrayList<NotesModel> bsSiteList = new ArrayList<>();
//        try {
//            JSONArray array = new JSONArray(result);
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject obj = array.optJSONObject(i);
//                JSONObject obj1 = obj.optJSONObject("note");
//                String content = obj1.optString("content");
//                String created_at = obj1.optString("created_at");
//                String deleted_at = obj1.optString("deleted_at");
//                String id = obj1.optString("id");
//                String mar_id = obj1.optString("mar_id");
//                String note_date = obj1.optString("note_date");
//                String note_type = obj1.optString("note_type");
//                String patient_id = obj1.optString("patient_id");
//                String prescription_id = obj1.optString("prescription_id");
//                String subject = obj1.optString("subject");
//                String user_id = obj1.optString("user_id");
//                String updated_at = obj1.optString("updated_at");
////                Log.e("content", "" + content);
//                JSONObject obj2 = obj1.optJSONObject("user");
//                JSONObject obj3 = obj2.optJSONObject("user");
//
//                String can_manage_users = obj3.optString("can_manage_users");
//                String care_home_id = obj3.optString("care_home_id");
//                String email = obj3.optString("email");
//                String fullname = obj3.optString("fullname");
//                String is_active = obj3.optString("is_active");
//                String username = obj3.optString("username");
//
////              BsSiteModel bs = new BsSiteModel(content, created_at, deleted_at, id, mar_id, note_date, note_type, patient_id, prescription_id, subject, updated_at, user_id, can_manage_users, email, care_home_id, fullname, is_active, username);
//
//                NotesModel notesModel = new NotesModel(id, content, created_at, deleted_at, mar_id, note_date, note_type,
//                        patient_id, prescription_id, subject, updated_at, user_id, can_manage_users, care_home_id, email, fullname, is_active, username);
//
//                bsSiteList.add(notesModel);
//            }
//
//            mydbobj.insertNotesData(bsSiteList, "online", "BS_Site");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    private void parse_notes_response(String result, String patientId) {
//
//        ArrayList<NotesModel> notesarraylist = new ArrayList<NotesModel>();
//        try {
//
//            JSONArray array = new JSONArray(result);
//            for (int k = 0; k < array.length(); k++) {
//                JSONObject obj = array.optJSONObject(k);
//                JSONObject obj1 = obj.optJSONObject("note");
//                String content = obj1.optString("content");
//                String created_at = obj1.optString("created_at");
//                String deleted_at = obj1.optString("deleted_at");
//                String id = obj1.optString("id");
//                String mar_id = obj1.optString("mar_id");
//                String note_date = obj1.optString("note_date");
//                String note_type = obj1.optString("note_type");
//                String patient_id = obj1.optString("patient_id");
//                String prescription_id = obj1.optString("prescription_id");
//                String subject = obj1.optString("subject");
//                String updated_at = obj1.optString("updated_at");
//                String user_id = obj1.optString("user_id");
////                Log.e("user_id", user_id);
//
//                JSONObject obj2 = obj1.optJSONObject("user");
//                JSONObject obj3 = obj2.optJSONObject("user");
//
//                String can_manage_users = obj3.optString("can_manage_users");
//                String care_home_id = obj3.optString("care_home_id");
//                String email = obj3.optString("email");
//                String fullname = obj3.optString("fullname");
//                String is_active = obj3.optString("is_active");
//                String username = obj3.optString("username");
////                Log.e("username", username);
//
//                NotesModel notesModel = new NotesModel(id, content, created_at, deleted_at, mar_id, note_date, note_type,
//                        patient_id, prescription_id, subject, updated_at, user_id, can_manage_users, care_home_id, email, fullname, is_active, username);
//
//                if ("General".equals(note_type)) {
//                    notesarraylist.add(notesModel);
//                }
//            }
//
//            mydbobj.insertNotesData(notesarraylist, "online", "General");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    private void parse_measure_response(String result, String patientId) {
//
//        ArrayList<MeasureModel> measurearraylist = new ArrayList<>();
//
//        try {
//            JSONArray array = new JSONArray(result);
//            for (int k = 0; k < array.length(); k++) {
//                JSONObject obj = array.optJSONObject(k);
//                JSONObject obj1 = obj.optJSONObject("measurement");
//                String created_at = obj1.optString("created_at");
//                String deleted_at = obj1.optString("deleted_at");
//                String id = obj1.optString("id");
//                String measurement_type_id = obj1.optString("measurement_type_id");
//                String patient_id = obj1.optString("patient_id");
//                String updated_at = obj1.optString("updated_at");
//                int value = obj1.optInt("value");
//
//                JSONObject obj2 = obj1.optJSONObject("measurement_type");
//                JSONObject obj3 = obj2.optJSONObject("measurement_type");
//
//                String created_at_second = obj3.optString("created_at");
//                String deleted_at_second = obj3.optString("deleted_at");
//                String id_second = obj3.optString("id");
//                String name = obj3.optString("name");
//                String units = obj3.optString("units");
//                String updated_at_second = obj3.optString("updated_at");
//                MeasureModel measureModel1 = new MeasureModel(id, created_at, deleted_at, measurement_type_id, patient_id, updated_at, value, created_at_second, deleted_at_second, id_second, name, units, updated_at_second, "false");
//                measurearraylist.add(measureModel1);
////                Log.e("measurearraylist name", "--->" + name);
//            }
//
//            mydbobj.insertMeasureData(measurearraylist, "online");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    private void parse_returntab_response(String result, String patientId) {
//
//        ArrayList<PRESCRIPTION_COMMON_Model> new_return_List = new ArrayList<>();
//
//        try {
//
//            JSONArray array = new JSONArray(result);
//            for (int i = 0; i < array.length(); i++) {
//
//                JSONObject obj = array.optJSONObject(i);
//                JSONObject obj1 = obj.optJSONObject("prescription");
//                int id = obj1.optInt("id");
//                String indications = obj1.optString("indications");
//                String instructions = obj1.optString("instructions");
//                String is_patch = obj1.optString("is_patch");
//                String is_special = obj1.optString("is_special");
//                String non_blistered = obj1.optString("non_blistered");
//                String prescription_type = obj1.optString("prescription_type");
//                String quantity_dispensed = obj1.optString("quantity_dispensed");
//                String special_medication_name = obj1.optString("special_medication_name");
//                String start_date = obj1.optString("start_date");
//                String status = obj1.optString("status");
//                String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
//                String mandatory_instructions = obj1.optString("mandatory_instructions");
//
//                //skipping todays_mars data here
//
//                String can_carry_forward = obj1.optString("can_carry_forward");
//                String front_image_url = obj1.optString("front_image_url");
//                String drug_name = obj1.optString("drug_name");
//                String detailed_drug_name = obj1.optString("detailed_drug_name");
//                String short_drug_name = obj1.optString("short_drug_name");
//                String patch_location = obj1.optString("patch_location");
//
//                // parsing for prescribed_time_slots
//
//                JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
//                String prescribed_time_slots_array = array1.toString();
//
//                String lst_admin_string_obj = "";
//
//                try {
//                    JSONObject jsonObject = obj1.optJSONObject("last_admin");
//                    lst_admin_string_obj = jsonObject.toString();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                String first_check_in_date = obj1.optString("first_check_in_date");
//                int dispensed_quantity = obj1.optInt("dispensed_quantity");
//                int available_quantity = obj1.optInt("available_quantity");
//                int checked_in_quantity = obj1.optInt("checked_in_quantity");
//                int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
//                int returned_quantity = obj1.optInt("returned_quantity");
//                int destroyed_quantity = obj1.optInt("destroyed_quantity");
//                int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
//                int wasted_quantity = obj1.optInt("wasted_quantity");
//                int administered_quantity = obj1.optInt("administered_quantity");
//                int audited_quantity = obj1.optInt("audited_quantity");
//                String patient_absent_today = obj1.optString("patient_absent_today");
//                String is_controlled = obj1.optString("is_controlled");
//                String pil_url = obj1.optString("pil_url");
//
//                //  Parsing for meditation
//
//                String medication_string_obj = "";
//                try {
//                    JSONObject obj4 = obj1.optJSONObject("medication");
//                    medication_string_obj = obj4.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                // Parsing for branded_medication
//
//                String branded_medication_string_obj = "";
//                try {
//                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
//                    branded_medication_string_obj = obj5.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                // Parsing for time_slot_doses
//
//                JSONArray array2 = obj1.optJSONArray("time_slot_doses");
//                String TIME_SLOT_DOSES_array = array2.toString();
//
//                PRESCRIPTION_COMMON_Model new_return_model = new PRESCRIPTION_COMMON_Model(id,
//                        indications, instructions, is_patch, is_special, non_blistered,
//                        prescription_type, quantity_dispensed, special_medication_name,
//                        start_date, status, suppress_non_blister_display, mandatory_instructions,
//                        can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
//                        patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
//                        dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
//                        returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
//                        administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
//                        medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", "last_cycle", patientId, "");
//                new_return_List.add(new_return_model);
//
//            }
//
//            mydbobj.addAllFeed(PatientsActivity.this, new_return_List);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    private void parse_homely_response(String result, String patientId) {
//
//        ArrayList<New_Homely_Remedy_Model> new_homely_List = new ArrayList<>();
//        ArrayList<Remedy_Dose_Bean> remedy_and_doselist = new ArrayList<>();
//
//        try {
//            JSONArray array = new JSONArray(result);
//            for (int i = 0; i < array.length(); i++) {
//
//                ArrayList<Homely_Today_Mars_Mar_Bean> homely_Today_Mars_Mar_objects_list;
//                homely_Today_Mars_Mar_objects_list = new ArrayList<Homely_Today_Mars_Mar_Bean>();
//
//                JSONObject obj = array.optJSONObject(i);
//                JSONObject obj1 = obj.optJSONObject("homely_remedy");
//                String care_home_id = obj1.optString("care_home_id");
//                String created_at = obj1.optString("created_at");
//                String deleted_at = obj1.optString("deleted_at");
//                String id = obj1.optString("id");
//                String mandatory_warnings = obj1.optString("mandatory_warnings");
//                String name = obj1.optString("name");
//                String updated_at = obj1.optString("updated_at");
//
//                JSONArray array1 = obj1.optJSONArray("todays_mars");
//
//                String ddoss = "";
//
//                for (int j = 0; j < array1.length(); j++) {
//
//                    JSONObject obj2 = array1.optJSONObject(j);
//                    JSONObject obj3 = obj2.optJSONObject("mar");
//                    String created_at_mar = obj3.optString("created_at");
//                    String administered_at_mar = obj3.optString("administered_at");
//                    String deleted_at_mar = obj3.optString("deleted_at");
//                    String dose = obj3.optString("dose");
//                    String false_reason = obj3.optString("false_reason");
//                    String gps_location = obj3.optString("gps_location");
//                    String homely_remedy_id = obj3.optString("homely_remedy_id");
//                    String homely_remedy_name = obj3.optString("homely_remedy_name");
//                    String id_mar = obj3.optString("id");
//                    String is_waste = obj3.optString("is_waste");
//                    String medication_id = obj3.optString("medication_id");
//                    String outcome = obj3.optString("outcome");
//                    String patient_id = obj3.optString("patient_id");
//                    String prescription_id = obj3.optString("prescription_id");
//                    String quantity_wasted = obj3.optString("quantity_wasted");
//                    String secondary_user_id = obj3.optString("secondary_user_id");
//                    String seconday_user_fullname = obj3.optString("seconday_user_fullname");
//                    String slot_time = obj3.optString("slot_time");
//                    String uid = obj3.optString("uid");
//                    String updated_at_mar = obj3.optString("updated_at");
//                    String user_fullname = obj3.optString("user_fullname");
//                    String user_id = obj3.optString("user_id");
//
//
//                    Homely_Today_Mars_Mar_Bean hm = new Homely_Today_Mars_Mar_Bean(id_mar, created_at_mar, deleted_at_mar, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, is_waste, medication_id, outcome, patient_id, prescription_id, quantity_wasted, secondary_user_id, seconday_user_fullname, slot_time, uid, updated_at_mar, user_fullname, user_id, administered_at_mar);
//                    homely_Today_Mars_Mar_objects_list.add(hm);
//
//                    if (ddoss.isEmpty()) {
//
//                        String a1 = "";
//                        if (dose.isEmpty()) {
//                            a1 = "REASON: " + false_reason + " ";
//
//                        } else {
//                            a1 = "DOSE: " + dose + " ";
//                        }
//
//                        ddoss = "" + a1 + "DATE: " +
//                                Date_utility.getDate(created_at_mar) + " " + "TIME: " +
//                                Date_utility.getTime(created_at_mar) + " " + "USER: " + user_fullname;
//                    } else {
//
//                        String a1 = "";
//                        if (dose.isEmpty()) {
//                            a1 = "REASON: " + false_reason + " ";
//                        } else {
//                            a1 = "DOSE: " + dose + " ";
//                        }
//
//                        ddoss = ddoss + "\n" + a1 + "DATE: " +
//                                Date_utility.getDate(created_at_mar) + " " + "TIME: " +
//                                Date_utility.getTime(created_at_mar) + " " + "USER: " + user_fullname;
//                    }
//
//                }
//
//                if (!ddoss.isEmpty()) {
//                    Remedy_Dose_Bean r1 = new Remedy_Dose_Bean(name, ddoss);
//                    remedy_and_doselist.add(r1);
//                }
//
//                mydbobj.insert_today_marsData(homely_Today_Mars_Mar_objects_list, "online");
//                New_Homely_Remedy_Model nhr_model = new New_Homely_Remedy_Model(id, care_home_id, created_at, deleted_at, mandatory_warnings, name, updated_at, homely_Today_Mars_Mar_objects_list, false, "" + 0, patientId);
//                new_homely_List.add(nhr_model);
//            }
//            mydbobj.insert_homelyData(new_homely_List);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    private void parsing_admin_response(String result, String patientId) {
//
//        ArrayList<PRESCRIPTION_COMMON_Model> admin_prescription_modelArrayList = new ArrayList<PRESCRIPTION_COMMON_Model>();
//        ArrayList<Today_Mars_Mar_Bean> admin_Today_Mars_Mar_objects_list;
//
//        try {
//            JSONArray array = new JSONArray(result);
//            for (int i = 0; i < array.length(); i++) {
//                admin_Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean>();
////                timedosenamelist.clear();
//
//                JSONObject obj = array.optJSONObject(i);
//                JSONObject obj1 = obj.optJSONObject("prescription");
//                int id = obj1.optInt("id");
//                String indications = obj1.optString("indications");
//                String instructions = obj1.optString("instructions");
//                String is_patch = obj1.optString("is_patch");
//                String is_special = obj1.optString("is_special");
//                String non_blistered = obj1.optString("non_blistered");
//                String prescription_type = obj1.optString("prescription_type");
//                String quantity_dispensed = obj1.optString("quantity_dispensed");
//                String special_medication_name = obj1.optString("special_medication_name");
//                String start_date = obj1.optString("start_date");
//                String status = obj1.optString("status");
//                String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
//                String mandatory_instructions = obj1.optString("mandatory_instructions");
//
//                try {
//                    JSONArray todaysmars = obj1.optJSONArray("todays_mars");
//                    for (int l = 0; l < todaysmars.length(); l++) {
//                        JSONObject obj7 = todaysmars.getJSONObject(l);
//                        JSONObject obj8 = obj7.optJSONObject("mar");
//                        String mar_created_at = obj8.optString("created_at");
//                        String mar_administered_at = obj8.optString("administered_at");
//                        String mar_deleted_at = obj8.optString("deleted_at");
//                        String mar_dose = obj8.optString("dose");
//                        String mar_false_reason = obj8.optString("false_reason");
//                        String mar_gps_location = obj8.optString("gps_location");
//                        String mar_homely_remedy_id = obj8.optString("homely_remedy_id");
//                        String mar_homely_remedy_name = obj8.optString("homely_remedy_name");
//                        String mar_id = obj8.optString("id");
//                        String mar_is_waste = obj8.optString("is_waste");
//                        String mar_medication_id = obj8.optString("medication_id");
//                        String mar_outcome = obj8.optString("outcome");
//                        // newly added on 15-04-2016
//                        String patient_id = obj8.optString("patient_id");
//                        String prescription_id = obj8.optString("prescription_id");
//                        //////////////
//                        String mar_quantity_wasted = obj8.optString("quantity_wasted");
//                        String mar_secondary_user_id = obj8.optString("secondary_user_id");
//                        String mar_seconday_user_fullname = obj8.optString("seconday_user_fullname");
//                        String mar_slot_time = obj8.optString("slot_time");
//                        String mar_uid = obj8.optString("uid");
//                        String mar_updated_at = obj8.optString("updated_at");
//
//                        // newly added on 15-04-2016
//                        String user_fullname = obj8.optString("user_fullname");
//                        String user_id = obj8.optString("user_id");
//                        //////////////
//                        Today_Mars_Mar_Bean tdm1 = new Today_Mars_Mar_Bean(mar_id, mar_created_at, mar_deleted_at, mar_dose, mar_false_reason, mar_gps_location, mar_homely_remedy_id, mar_homely_remedy_name, mar_is_waste, mar_medication_id, mar_outcome, patient_id, prescription_id, mar_quantity_wasted, mar_secondary_user_id, mar_seconday_user_fullname, mar_slot_time, mar_uid, mar_updated_at, user_fullname, user_id, mar_administered_at);
//                        admin_Today_Mars_Mar_objects_list.add(tdm1);
//
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                String can_carry_forward = obj1.optString("can_carry_forward");
//                String front_image_url = obj1.optString("front_image_url");
//                String drug_name = obj1.optString("drug_name");
//                String detailed_drug_name = obj1.optString("detailed_drug_name");
//                String short_drug_name = obj1.optString("short_drug_name");
//                String patch_location = obj1.optString("patch_location");
//
//                JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
//                String prescribed_time_slots_array = array1.toString();
//
//                String lst_admin_string_obj = "";
//
//                try {
//                    JSONObject jsonObject = obj1.optJSONObject("last_admin");
//                    lst_admin_string_obj = jsonObject.toString();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
////                    String last_admin = obj1.optString("last_admin");
//                String first_check_in_date = obj1.optString("first_check_in_date");
//                int dispensed_quantity = obj1.optInt("dispensed_quantity");
//                int available_quantity = obj1.optInt("available_quantity");
//                int checked_in_quantity = obj1.optInt("checked_in_quantity");
//                int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
//                int returned_quantity = obj1.optInt("returned_quantity");
//                int destroyed_quantity = obj1.optInt("destroyed_quantity");
//                int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
//                int wasted_quantity = obj1.optInt("wasted_quantity");
//                int administered_quantity = obj1.optInt("administered_quantity");
//                int audited_quantity = obj1.optInt("audited_quantity");
//
//                String patient_absent_today = obj1.optString("patient_absent_today");
//                String is_controlled = obj1.optString("is_controlled");
//                String pil_url = obj1.optString("pil_url");
//
//                //  Parsing for meditation
//                String medication_string_obj = "";
//                try {
//                    JSONObject obj4 = obj1.optJSONObject("medication");
//                    medication_string_obj = obj4.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                // Parsing for branded_medication
//                String branded_medication_string_obj = "";
//                try {
//                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
//                    branded_medication_string_obj = obj5.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//                JSONArray array2 = obj1.optJSONArray("time_slot_doses");
//                String TIME_SLOT_DOSES_array = array2.toString();
//                for (int j = 0; j < array2.length(); j++) {
//                    JSONObject obj6 = array2.optJSONObject(j);
//                    String time_slot_doses_color = obj6.optString("color");
//                    String time_slot_doses_dose = obj6.optString("dose");
//                    String time_slot_doses_id = obj6.optString("id");
//                    String time_slot_doses_show_as = obj6.optString("show_as");
//                    String time_slot_doses_slot_time = obj6.optString("slot_time");
//
////                    timedosenamelist.add(time_slot_doses_show_as);
//                }
//
//                PRESCRIPTION_COMMON_Model new_checkIn_model = new PRESCRIPTION_COMMON_Model(id,
//                        indications, instructions, is_patch, is_special, non_blistered,
//                        prescription_type, quantity_dispensed, special_medication_name,
//                        start_date, status, suppress_non_blister_display, mandatory_instructions,
//                        can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
//                        patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
//                        dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
//                        returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
//                        administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
//                        medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", "this_cycle", patientId, "AdminTab");
//
//                new_checkIn_model.setToday_Mars_Mar_objects_list(admin_Today_Mars_Mar_objects_list);
//                try {
//                    mydbobj.insert_today_marsData_admin(admin_Today_Mars_Mar_objects_list, "online");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                admin_prescription_modelArrayList.add(new_checkIn_model);
//            }
//            mydbobj.addAllFeed(PatientsActivity.this, admin_prescription_modelArrayList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    private void parsing_meds_json(String meds_cycle_type, String result, String patientId) {
//
//        ArrayList<PRESCRIPTION_COMMON_Model> newmedsList = new ArrayList<PRESCRIPTION_COMMON_Model>();
////        String taken = "";
//
//        try {
//            JSONArray array = new JSONArray(result);
//            for (int i = 0; i < array.length(); i++) {
//
//                JSONObject obj = array.optJSONObject(i);
//                JSONObject obj1 = obj.optJSONObject("prescription");
//                int id = obj1.optInt("id");
//                String indications = obj1.optString("indications");
//                String instructions = obj1.optString("instructions");
//                String is_patch = obj1.optString("is_patch");
//                String is_special = obj1.optString("is_special");
//                String non_blistered = obj1.optString("non_blistered");
//                String prescription_type = obj1.optString("prescription_type");
//                String quantity_dispensed = obj1.optString("quantity_dispensed");
//                String special_medication_name = obj1.optString("special_medication_name");
//                String start_date = obj1.optString("start_date");
//                String status = obj1.optString("status");
//                String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
//                String mandatory_instructions = obj1.optString("mandatory_instructions");
//
//                //skipping todays_mars data here
//
//                String can_carry_forward = obj1.optString("can_carry_forward");
//                String front_image_url = obj1.optString("front_image_url");
//                String drug_name = obj1.optString("drug_name");
//                String detailed_drug_name = obj1.optString("detailed_drug_name");
//                String short_drug_name = obj1.optString("short_drug_name");
//                String patch_location = obj1.optString("patch_location");
//
//                // parsing for prescribed_time_slots
//
//                JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
//                String prescribed_time_slots_array = array1.toString();
//
//                String lst_admin_string_obj = "";
//                try {
//                    JSONObject jsonObject = obj1.optJSONObject("last_admin");
//                    lst_admin_string_obj = jsonObject.toString();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                String first_check_in_date = obj1.optString("first_check_in_date");
//                int dispensed_quantity = obj1.optInt("dispensed_quantity");
//                int available_quantity = obj1.optInt("available_quantity");
//                int checked_in_quantity = obj1.optInt("checked_in_quantity");
//                int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
//                int returned_quantity = obj1.optInt("returned_quantity");
//                int destroyed_quantity = obj1.optInt("destroyed_quantity");
//                int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
//                int wasted_quantity = obj1.optInt("wasted_quantity");
//                int administered_quantity = obj1.optInt("administered_quantity");
//                int audited_quantity = obj1.optInt("audited_quantity");
//                String patient_absent_today = obj1.optString("patient_absent_today");
//                String is_controlled = obj1.optString("is_controlled");
//                String pil_url = obj1.optString("pil_url");
//
//                //  Parsing for meditation
//
//                String medication_string_obj = "";
//                try {
//                    JSONObject obj4 = obj1.optJSONObject("medication");
//                    medication_string_obj = obj4.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                // Parsing for branded_medication
//
//                String branded_medication_string_obj = "";
//                try {
//                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
//                    branded_medication_string_obj = obj5.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                // Parsing for time_slot_doses
//
//                JSONArray array2 = obj1.optJSONArray("time_slot_doses");
//                String TIME_SLOT_DOSES_array = array2.toString();
//
//                PRESCRIPTION_COMMON_Model new_meds_model = new PRESCRIPTION_COMMON_Model(id,
//                        indications, instructions, is_patch, is_special, non_blistered,
//                        prescription_type, quantity_dispensed, special_medication_name,
//                        start_date, status, suppress_non_blister_display, mandatory_instructions,
//                        can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
//                        patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
//                        dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
//                        returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
//                        administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
//                        medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", meds_cycle_type, patientId, "");
//                newmedsList.add(new_meds_model);
//
//            }
//
//            mydbobj.addAllFeed(PatientsActivity.this, newmedsList);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

    /***********************************************************************************************/

//    private void parsing_checkin_json(String cycle_type, String result, String patientId) {
//
//        ArrayList<PRESCRIPTION_COMMON_Model> newchecklist = new ArrayList<PRESCRIPTION_COMMON_Model>();
//
//        try {
//            JSONArray array = new JSONArray(result);
//            for (int i = 0; i < array.length(); i++) {
//
//                JSONObject obj = array.optJSONObject(i);
//                JSONObject obj1 = obj.optJSONObject("prescription");
//
//                int id = obj1.optInt("id");
//                String indications = obj1.optString("indications");
//                String instructions = obj1.optString("instructions");
//                String is_patch = obj1.optString("is_patch");
//                String is_special = obj1.optString("is_special");
//                String non_blistered = obj1.optString("non_blistered");
//                String prescription_type = obj1.optString("prescription_type");
//                String quantity_dispensed = obj1.optString("quantity_dispensed");
//                String special_medication_name = obj1.optString("special_medication_name");
//                String start_date = obj1.optString("start_date");
//                String status = obj1.optString("status");
//                String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
//                String mandatory_instructions = obj1.optString("mandatory_instructions");
//
//                //skipping todays_mars data here
//
//                String can_carry_forward = obj1.optString("can_carry_forward");
//                String front_image_url = obj1.optString("front_image_url");
//                String drug_name = obj1.optString("drug_name");
//                String detailed_drug_name = obj1.optString("detailed_drug_name");
//                String short_drug_name = obj1.optString("short_drug_name");
//                String patch_location = obj1.optString("patch_location");
//
//                // parsing for prescribed_time_slots
//
//                JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
//                String prescribed_time_slots_array = array1.toString();
//                // parsing for last_admin
//                String lst_admin_string_obj = "";
//
//                try {
//                    JSONObject jsonObject = obj1.optJSONObject("last_admin");
//                    lst_admin_string_obj = jsonObject.toString();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                String first_check_in_date = obj1.optString("first_check_in_date");
//                int dispensed_quantity = obj1.optInt("dispensed_quantity");
//                int available_quantity = obj1.optInt("available_quantity");
//                int checked_in_quantity = obj1.optInt("checked_in_quantity");
//                int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
//                int returned_quantity = obj1.optInt("returned_quantity");
//                int destroyed_quantity = obj1.optInt("destroyed_quantity");
//                int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
//                int wasted_quantity = obj1.optInt("wasted_quantity");
//                int administered_quantity = obj1.optInt("administered_quantity");
//                int audited_quantity = obj1.optInt("audited_quantity");
//                String patient_absent_today = obj1.optString("patient_absent_today");
//                String is_controlled = obj1.optString("is_controlled");
//                String pil_url = obj1.optString("pil_url");
//
//                //  Parsing for meditation
//
//                String medication_string_obj = "";
//                try {
//                    JSONObject obj4 = obj1.optJSONObject("medication");
//                    medication_string_obj = obj4.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                // Parsing for branded_medication
//
//                String branded_medication_string_obj = "";
//                try {
//                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
//                    branded_medication_string_obj = obj5.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                // Parsing for time_slot_doses
//
//                JSONArray array2 = obj1.optJSONArray("time_slot_doses");
//                String TIME_SLOT_DOSES_array = array2.toString();
//
//                PRESCRIPTION_COMMON_Model new_checkIn_model = new PRESCRIPTION_COMMON_Model(id,
//                        indications, instructions, is_patch, is_special, non_blistered,
//                        prescription_type, quantity_dispensed, special_medication_name,
//                        start_date, status, suppress_non_blister_display, mandatory_instructions,
//                        can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
//                        patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
//                        dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
//                        returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
//                        administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
//                        medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", cycle_type, patientId, "");
//                newchecklist.add(new_checkIn_model);
////                    mydbobj.addAllFeed(PatientDetail.this, newchecklist);
//
//            }
//            mydbobj.addAllFeed(PatientsActivity.this, newchecklist);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }


    /*****************************************************************/
    private class SyncingData_AsyncTask extends AsyncTask<String, String, String> {
        Dialog dlg;
        int index;

        public SyncingData_AsyncTask(int count) {

            index = count;

            Log.e("SyncingData_AsyncTask", "SyncingData_AsyncTask--->" + index);

        }

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientsActivity.this, "Syncing..\nPlease Wait");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            String result = "";
            try {
                Log.e("SyncingData_AsyncTask", "SyncingData_AsyncTask--->" + offline_modelArrayList.get(index).getWeburl());
                Log.e("SyncingData_AsyncTask", "SyncingData_AsyncTask--->" + offline_modelArrayList.get(index).getPostdata());

                result = jsonParser.postdata_method(offline_modelArrayList.get(index).getWeburl(), offline_modelArrayList.get(index).getPostdata());

            } catch (Exception e) {
                e.printStackTrace();

            }

            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            Log.e("sync  ", "onPostExecute ---> " + index + " ---->" + result);


            if (offline_modelArrayList.size() > index) {
                mydbobj.delete_single_offline_record(offline_modelArrayList.get(index).getId());

                if (offline_modelArrayList.get(index).getType().equals("homely")) {

                    mydbobj.delete_todaymar_for_homely(offline_modelArrayList.get(index).getId_to_relate(), offline_modelArrayList.get(index).getPatient_id());

                    String pid = offline_modelArrayList.get(index).getPatient_id();
                    String homelyweburl = baseurl_value + pid + "/homely_remedies/?authentication_token=" + global_auth_token + "&note_type=General";
                    new Update_Patients_Related_all_Data(homelyweburl, "6", pid, "homelyweburl").execute();


                } else if (offline_modelArrayList.get(index).getType().equals("admin")) {

                    mydbobj.delete_todaymar_for_homely(offline_modelArrayList.get(index).getId_to_relate(), offline_modelArrayList.get(index).getPatient_id());

                    String pid = offline_modelArrayList.get(index).getPatient_id();
                    String checkin_this_cycle_weburl = baseurl_value + pid + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=this_cycle&type=check_ins";
                    new Update_Patients_Related_all_Data(checkin_this_cycle_weburl, "0", pid, "checkin_this_cycle_weburl").execute();


                } else if (offline_modelArrayList.get(index).getType().equals("check_in_this_cycle")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();

                    String checkin_this_cycle_weburl = baseurl_value + pid + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=this_cycle&type=check_ins";
                    new Update_Patients_Related_all_Data(checkin_this_cycle_weburl, "0", pid, "checkin_this_cycle_weburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("check_in_next_cycle")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();

                    String checkin_next_cycle_weburl = baseurl_value + pid + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=next_cycle&type=check_ins";
                    new Update_Patients_Related_all_Data(checkin_next_cycle_weburl, "1", pid, "checkin_next_cycle_weburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("inr_reading")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();
                    String patientrecordweburl = "" + baseurl_value + pid + "?authentication_token=" + global_auth_token;
                    new Update_Patients_Related_all_Data(patientrecordweburl, "11", pid, "patientrecordweburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("general_note")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();
                    String getnotes_weburl = baseurl_value + pid + "/notes?authentication_token=" + global_auth_token + "&note_type=General";

                    new Update_Patients_Related_all_Data(getnotes_weburl, "9", pid, "getnotes_weburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("measure")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();
                    String measureweburl = baseurl_value + pid + "/measurements/?authentication_token=" + global_auth_token + "&last=true&all_types=true";

                    new Update_Patients_Related_all_Data(measureweburl, "8", pid, "measureweburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("return")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();

                    String get_return_tab_weburl = baseurl_value + pid + "/prescriptions/?authentication_token=" + global_auth_token + "&type=returns";
                    new Update_Patients_Related_all_Data(get_return_tab_weburl, "7", pid, "get_return_tab_weburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("meds_this_cycle")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();

                    String meds_this_cycle_weburl = baseurl_value + pid + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=this_cycle&view=meds-list";
                    new Update_Patients_Related_all_Data(meds_this_cycle_weburl, "3", pid, "meds_this_cycle_weburl").execute();

                } else if (offline_modelArrayList.get(index).getType().equals("meds_last_cycle")) {

                    String pid = offline_modelArrayList.get(index).getPatient_id();

                    String meds_last_cycle_weburl = baseurl_value + pid + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=last_cycle&view=meds-list";
                    new Update_Patients_Related_all_Data(meds_last_cycle_weburl, "5", pid, "meds_last_cycle_weburl").execute();

                }


                index++;
                new SyncingData_AsyncTask(index).execute();
            }

        }
    }


//    class UpdatePatientList extends AsyncTask<String, String, String> {
//        Dialog dlg;
//        Context context;
//        HttpResponse loginResponse;
//        // List<PatientModel> patientModelArrayList = new ArrayList<PatientModel>();
////        List<ColorModel> colorList = new ArrayList<ColorModel>();
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
////            progDialog.show();
//            dlg = dialog(PatientsActivity.this, "Loading");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            String auth_token = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
//            String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
//            baseurl_value = baseurl_value + "/patients?authentication_token=";
//            String GetPatientList_url = baseurl_value + auth_token;
//            String result = "";
//            result = jsonParser.getjson_method(GetPatientList_url);
//            Log.e("Patient response", "----->" + result);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
////            progDialog.dismiss();
//            if (dlg.isShowing()) {
//                dlg.dismiss();
//            }
//            new_patient_Model_List.clear();
//
//            try {
//                JSONArray jsonArray = new JSONArray(result);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
//                    JSONObject jsonObject2 = jsonObject1.optJSONObject("patient");
//                    String address = jsonObject2.optString("address");
//                    String allergies = jsonObject2.optString("allergies");
//                    String careHomeId = jsonObject2.optString("care_home_id");
//                    String createdAt = jsonObject2.optString("created_at");
//                    String cycleBaseDate = jsonObject2.optString("cycle_base_date");
//                    String cycleDuration = jsonObject2.optString("cycle_duration");
//                    String deletedAt = jsonObject2.optString("deleted_at");
//                    String dob = jsonObject2.optString("dob");
//                    String firstName = jsonObject2.optString("first_name");
//                    String gpId = jsonObject2.optString("gp_id");
//                    String gpName = jsonObject2.optString("gp_name");
//                    String id = jsonObject2.optString("id");
//                    String inactive = jsonObject2.optString("inactive");
//                    String name = jsonObject2.optString("name");
//                    String nhsNumber = jsonObject2.optString("nhs_number");
//                    String patientNotes = jsonObject2.optString("patient_notes");
//                    String photoImage = jsonObject2.optString("photo_image");
//                    String room = jsonObject2.optString("room");
//                    String title = jsonObject2.optString("title");
//                    String updatedAt = jsonObject2.optString("updated_at");
//                    String fullName = jsonObject2.optString("fullname");
//                    String is_absent = jsonObject2.optString("is_absent");
//                    String current_absence_start = jsonObject2.optString("current_absence_start");
//                    String current_absence_end = jsonObject2.optString("current_absence_end");
//                    String current_absence_reason = jsonObject2.optString("current_absence_reason");
//
//                    JSONArray jsonArray1 = jsonObject2.optJSONArray("prescribed_time_slots_for_patient_list");
//                    String prescribed_time_slots_for_patient_list_array = jsonArray1.toString();
//
//                    New_Patient_Model npm = new New_Patient_Model(id, address, allergies, careHomeId, createdAt, cycleBaseDate, cycleDuration, deletedAt, dob, firstName, gpId, gpName, inactive, name, nhsNumber, patientNotes, photoImage, room, title, updatedAt, fullName, prescribed_time_slots_for_patient_list_array, is_absent, current_absence_start, current_absence_end, current_absence_reason);
//                    new_patient_Model_List.add(npm);
//                }
//
//
//                mydbobj.insert_patients_list(new_patient_Model_List);
//                adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "online");
//                lvPatients.setAdapter(adptr);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            }
//        }
//
//    }


    /*************************************************************************************************/

    private class Update_Patients_Related_all_Data extends AsyncTask<String, Void, String> {

        String weburl, patientId;
        String name_of_api;

        public Update_Patients_Related_all_Data(String web_url, String count, String pId, String apiname) {
            weburl = web_url;
            patientId = pId;
            name_of_api = apiname;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            try {
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "get_Data url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }

            PreferenceConnector.writeLong(PatientsActivity.this, PreferenceConnector.LAST_ACTIVITY_TIME, (new Date().getTime()));

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("Response", "get_Data_for---->" + result);

//            if (name_of_api.equalsIgnoreCase("checkin_this_cycle_weburl")) {
//                parsing_checkin_json("this_cycle", result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("checkin_next_cycle_weburl")) {
//                parsing_checkin_json("next_cycle", result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("meds_this_cycle_weburl")) {
//                parsing_meds_json("this_cycle", result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("meds_next_cycle_weburl")) {
//                parsing_meds_json("next_cycle", result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("meds_last_cycle_weburl")) {
//                parsing_meds_json("last_cycle", result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("admin_weburl")) {
//                parsing_admin_response(result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("homelyweburl")) {
//                parse_homely_response(result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("get_return_tab_weburl")) {
//                parse_returntab_response(result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("measureweburl")) {
//                parse_measure_response(result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("getnotes_weburl")) {
//                parse_notes_response(result, patientId);
//            } else if (name_of_api.equalsIgnoreCase("bsbiteweburl")) {
//                parse_BSSite_response(result, patientId);
////                new getPatients_Related_all_Data(urllist.get((urlcount + 1)).getApi_web_url(), (urlcount + 1), urllist.get((urlcount + 1)).getApi_patientid(), urllist.get((urlcount + 1)).getApi_name()).execute();
//            } else if (name_of_api.equalsIgnoreCase("patientrecordweburl")) {
//                parse_about_patient_detail_response(result, patientId);
//
//            }


        }
    }


    private class CompleteResponse extends AsyncTask<String, String, String> {

        String weburl;
        Dialog dlg;
        Context context;

        public CompleteResponse(String weburl) {
            this.weburl = weburl;
        }

        protected void onPreExecute() {
            super.onPreExecute();

            dlg = app_constants.dialog(PatientsActivity.this, "Loading");

        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                result = jsonParser.getjson_method(weburl);
                Log.e("Complete_Response", "get_Data url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                dlg.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Complete_Response", "CompelteResponse---->" + result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray patientarray = jsonObject.optJSONArray("patients");
                for (int i = 0; i < patientarray.length(); i++) {
                    JSONObject patient_detail_obj = patientarray.optJSONObject(i);
                    String id = patient_detail_obj.optString("id");
                    String fullname = patient_detail_obj.optString("fullname");
                    String title = patient_detail_obj.optString("title");
                    String first_name = patient_detail_obj.optString("first_name");
                    String name = patient_detail_obj.optString("name");
                    String address = patient_detail_obj.optString("address");
                    String dob = patient_detail_obj.optString("dob");
                    String nhs_number = patient_detail_obj.optString("nhs_number");
                    String allergies = patient_detail_obj.optString("allergies");
                    String gp_name = patient_detail_obj.optString("gp_name");
                    String current_absence_reason = patient_detail_obj.optString("current_absence_reason");
                    String room = patient_detail_obj.optString("room");
                    String cycle_duration = patient_detail_obj.optString("cycle_duration");
                    String patient_notes = patient_detail_obj.optString("patient_notes");
                    String is_absent = patient_detail_obj.optString("is_absent");
                    String current_absence_start = patient_detail_obj.optString("current_absence_start");
                    String current_absence_end = patient_detail_obj.optString("current_absence_end");
                    String inr_reading = patient_detail_obj.optString("inr_reading");
                    String inr_date = patient_detail_obj.optString("inr_date");
                    String warfarin_dose = patient_detail_obj.optString("warfarin_dose");
                    String photo_image = patient_detail_obj.optString("photo_image");
                    String updated_at = patient_detail_obj.optString("updated_at");
                    String last_bs_site = patient_detail_obj.optString("last_bs_site");
                    Log.e("Complete_Response", "inr_reading---->" + inr_reading);
                    //Last BS Site Data Parsing

                    try {
                        JSONObject last_bs_site_obj = new JSONObject(last_bs_site);
                        String last_bs_site_id = last_bs_site_obj.optString("id");
                        String last_bs_site_subject = last_bs_site_obj.optString("subject");
                        String last_bs_site_content = last_bs_site_obj.optString("content");
                        String last_bs_site_note_type = last_bs_site_obj.optString("note_type");
                        String last_bs_site_updated_at = last_bs_site_obj.optString("updated_at");
                        String last_bs_site_created_at = last_bs_site_obj.optString("created_at");
                        Log.e("Complete_Response", "last_bs_obj---->" + last_bs_site_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //prescribed_time_slots_for_patient_list Data parsing

                    JSONArray prescribed_time_slot_arr = null;
                    String prescribed_time_slots_for_patient_list_array = "";

                    try {
                        prescribed_time_slot_arr = patient_detail_obj.optJSONArray("prescribed_time_slots_for_patient_list");
                        prescribed_time_slots_for_patient_list_array = prescribed_time_slot_arr.toString();
                        for (int j = 0; j < prescribed_time_slot_arr.length(); j++) {
                            JSONObject prescribed_time_slot_obj = prescribed_time_slot_arr.optJSONObject(j);
                            String slot_time = prescribed_time_slot_obj.optString("slot_time");
                            String show_as = prescribed_time_slot_obj.optString("show_as");
                            String color = prescribed_time_slot_obj.optString("color");
                            String admin_today = prescribed_time_slot_obj.optString("admin_today");
                            Log.e("Complete_Response", "prescribed_time_slot_obj---->" + slot_time);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    Patient_Model_Phase2 pmh2 = new Patient_Model_Phase2(id, fullname, title, first_name, name, address,
                            dob, nhs_number, allergies, gp_name, room, cycle_duration, patient_notes, is_absent,
                            current_absence_start, current_absence_end, inr_reading, inr_date, warfarin_dose, photo_image,
                            updated_at, last_bs_site, prescribed_time_slots_for_patient_list_array, current_absence_reason);
                    new_patient_Model_List.add(pmh2);

                    //general_notes Data Parsing
                    try {
                        JSONArray general_notes_arr = patient_detail_obj.optJSONArray("general_notes");
                        for (int k = 0; k < general_notes_arr.length(); k++) {

                            JSONObject general_notes_obj = general_notes_arr.optJSONObject(k);
                            String general_notes_id = general_notes_obj.optString("id");
                            String general_notes_subject = general_notes_obj.optString("subject");
                            String general_notes_content = general_notes_obj.optString("content");
                            String general_notes_note_type = general_notes_obj.optString("note_type");
                            String general_notes_updated_at = general_notes_obj.optString("updated_at");
                            String general_notes_created_at = general_notes_obj.optString("created_at");
                            Log.e("Complete_Response", "general_notes_obj---->" + general_notes_id);
                            String general_notes_user = general_notes_obj.optString("user");

                            JSONObject general_notes_user_obj = new JSONObject(general_notes_user);
                            String general_notes_user_id = general_notes_user_obj.optString("id");
                            String general_notes_user_username = general_notes_user_obj.optString("username");
                            String general_notes_user_fullname = general_notes_user_obj.optString("fullname");
                            String general_notes_user_email = general_notes_user_obj.optString("email");

                            NotesModel_Phase2 nm = new NotesModel_Phase2(general_notes_id, general_notes_subject,
                                    general_notes_content, general_notes_note_type, general_notes_updated_at,
                                    general_notes_created_at, general_notes_user_id, general_notes_user_username,
                                    general_notes_user_fullname, general_notes_user_email, id);
                            notelist.add(nm);

                            Log.e("Complete_Response", "general_notes_user_obj---->" + general_notes_user_id);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //homely_remedies Data Parsing
                    ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> homely_Today_Mars_Mar_objects_list;
                    homely_Today_Mars_Mar_objects_list = new ArrayList<Homely_Today_Mars_Mar_Bean_Phase2>();
                    try {
                        JSONArray homely_remedies_arr = patient_detail_obj.optJSONArray("homely_remedies");
                        for (int l = 0; l < homely_remedies_arr.length(); l++) {
                            Log.e("Complete_Response", "homely_remedies_obj---->" + homely_remedies_arr.length());
                            JSONObject homely_remedies_obj = homely_remedies_arr.optJSONObject(l);
                            Log.e("Complete_Response", "homely_remedies_obj---->" + l);
                            String homely_remedies_id = homely_remedies_obj.optString("id");
                            String homely_remedies_mandatory_warnings = homely_remedies_obj.optString("mandatory_warnings");
                            String homely_remedies_name = homely_remedies_obj.optString("name");
                            String homely_remedies_updated_at = homely_remedies_obj.optString("updated_at");

                            Log.e("Complete_Response", "homely_remedies_obj---->" + homely_remedies_id);
                            //Todays Mars Parsing
                            try {
                                JSONArray homely_remedies_mars_arr = homely_remedies_obj.optJSONArray("todays_mars");

                                for (int m = 0; m < homely_remedies_mars_arr.length(); m++) {
                                    JSONObject todays_mars_obj = homely_remedies_mars_arr.optJSONObject(m);
                                    String todays_mars_id = todays_mars_obj.optString("id");
                                    String todays_mars_administered_at = todays_mars_obj.optString("administered_at");
                                    String todays_mars_dose = todays_mars_obj.optString("dose");
                                    String todays_mars_false_reason = todays_mars_obj.optString("false_reason");
                                    String todays_mars_is_waste = todays_mars_obj.optString("is_waste");
                                    String todays_mars_outcome = todays_mars_obj.optString("outcome");
                                    String todays_mars_quantity_wasted = todays_mars_obj.optString("quantity_wasted");
                                    String todays_mars_slot_time = todays_mars_obj.optString("slot_time");
                                    String todays_mars_updated_at = todays_mars_obj.optString("updated_at");
                                    String todays_mars_user = todays_mars_obj.optString("user");
                                    Log.e("Complete_Response", "todays_mars_obj---->" + todays_mars_id);
                                    JSONObject todays_mars_user_obj = new JSONObject(todays_mars_user);
                                    String todays_mars_user_id = todays_mars_user_obj.optString("id");
                                    String todays_mars_user_username = todays_mars_user_obj.optString("username");
                                    String todays_mars_user_fullname = todays_mars_user_obj.optString("fullname");
                                    String todays_mars_user_email = todays_mars_user_obj.optString("email");

                                    Homely_Today_Mars_Mar_Bean_Phase2 hm = new Homely_Today_Mars_Mar_Bean_Phase2(
                                            todays_mars_id, todays_mars_administered_at, todays_mars_dose,
                                            todays_mars_false_reason, todays_mars_is_waste, todays_mars_outcome, todays_mars_quantity_wasted, todays_mars_slot_time,
                                            todays_mars_updated_at, todays_mars_user_id, todays_mars_user_username, todays_mars_user_fullname, todays_mars_user_email,
                                            homely_remedies_id, homely_remedies_name, "", id);

                                    homely_Today_Mars_Mar_objects_list.add(hm);

                                    mydbobj.insert_today_marsData(homely_Today_Mars_Mar_objects_list, "online");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Homely_Model_Phase2 nhr_model = new Homely_Model_Phase2(homely_remedies_id, homely_remedies_mandatory_warnings,
                                    homely_remedies_name, homely_remedies_updated_at, homely_Today_Mars_Mar_objects_list, false, "" + 0, id);
                            new_homely_List.add(nhr_model);
                            mydbobj.insert_homelyData(new_homely_List);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Data parsing of Last Measurements

                    try {
                        JSONArray last_measurements_arr = patient_detail_obj.optJSONArray("last_measurements");

                        for (int n = 0; n < last_measurements_arr.length(); n++) {
                            JSONObject last_measurements_obj = last_measurements_arr.optJSONObject(n);
                            String last_measurements_id = last_measurements_obj.optString("id");
                            int last_measurements_value = last_measurements_obj.optInt("value");
                            String last_measurements_updated_at = last_measurements_obj.optString("updated_at");
                            String last_measurements_measurement_type = last_measurements_obj.optString("measurement_type");
                            Log.e("Complete_Response", "last_measurements_obj---->" + last_measurements_id);
                            JSONObject measurement_type_object = new JSONObject(last_measurements_measurement_type);
                            String measurement_type_id = measurement_type_object.optString("id");
                            String measurement_type_name = measurement_type_object.optString("name");
                            String measurement_type_units = measurement_type_object.optString("units");
                            String measurement_type_updated_at = measurement_type_object.optString("updated_at");
                            String measurement_type_created_at = measurement_type_object.optString("created_at");

                            MeasureModel_Phase2 mp2 = new MeasureModel_Phase2(last_measurements_id, last_measurements_value, last_measurements_updated_at, measurement_type_id, measurement_type_name, measurement_type_units, measurement_type_updated_at, measurement_type_created_at, id, "false");
                            measurearraylist.add(mp2);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Parsing Last Cycle Prescriptions


                    try {
                        JSONArray last_cycle_prescriptions_arr = patient_detail_obj.optJSONArray("last_cycle_prescriptions");
                        Log.e("Last cycle ", "SIZE:--" + last_cycle_prescriptions_arr.toString());

                        for (int p = 0; p < last_cycle_prescriptions_arr.length(); p++) {
                            JSONObject last_cycle_prescriptions_obj = last_cycle_prescriptions_arr.optJSONObject(p);
                            int last_cycle_prescriptions_id = last_cycle_prescriptions_obj.optInt("id");
                            Log.e("Last cycle ", "ID IS:--" + last_cycle_prescriptions_arr.toString());
                            String last_cycle_prescription_type = last_cycle_prescriptions_obj.optString("prescription_type");
                            String last_cycle_prescriptions_drug_name = last_cycle_prescriptions_obj.optString("drug_name");
                            String last_cycle_prescriptions_instructions = last_cycle_prescriptions_obj.optString("instructions");
                            String last_cycle_prescriptions_indications = last_cycle_prescriptions_obj.optString("indications");
                            String last_cycle_prescriptions_mandatory_instructions = last_cycle_prescriptions_obj.optString("mandatory_instructions");
                            String last_cycle_prescriptions_start_date = last_cycle_prescriptions_obj.optString("start_date");
                            String last_cycle_prescriptions_end_date = last_cycle_prescriptions_obj.optString("end_date");
                            String last_cycle_prescriptions_is_patch = last_cycle_prescriptions_obj.optString("is_patch");
                            String last_cycle_prescriptions_patch_location = last_cycle_prescriptions_obj.optString("patch_location");
                            String last_cycle_prescriptions_non_blistered = last_cycle_prescriptions_obj.optString("non_blistered");
                            String last_cycle_prescriptions_is_controlled = last_cycle_prescriptions_obj.optString("is_controlled");
                            String last_cycle_suppress_non_blister_display = last_cycle_prescriptions_obj.optString("suppress_non_blister_display");
                            String last_cycle_can_carry_forward = last_cycle_prescriptions_obj.optString("can_carry_forward");
                            String last_cycle_special_medication_name = last_cycle_prescriptions_obj.optString("special_medication_name");
                            String last_cycle_is_special = last_cycle_prescriptions_obj.optString("is_special");
                            String last_cycle_front_image_url = last_cycle_prescriptions_obj.optString("front_image_url");
                            int last_cycle_dispensed_quantity = last_cycle_prescriptions_obj.optInt("dispensed_quantity");
                            int last_cycle_available_quantity = last_cycle_prescriptions_obj.optInt("available_quantity");
                            int last_cycle_checked_in_quantity = last_cycle_prescriptions_obj.optInt("checked_in_quantity");
                            int last_cycle_brought_forward_quantity = last_cycle_prescriptions_obj.optInt("brought_forward_quantity");
                            int last_cycle_returned_quantity = last_cycle_prescriptions_obj.optInt("returned_quantity");
                            int last_cycle_destroyed_quantity = last_cycle_prescriptions_obj.optInt("destroyed_quantity");
                            int last_cycle_carried_forward_quantity = last_cycle_prescriptions_obj.optInt("carried_forward_quantity");
                            int last_cycle_wasted_quantity = last_cycle_prescriptions_obj.optInt("wasted_quantity");
                            int last_cycle_administered_quantity = last_cycle_prescriptions_obj.optInt("administered_quantity");
                            int last_cycle_audited_quantity = last_cycle_prescriptions_obj.optInt("audited_quantity");
                            String irregular_grid = last_cycle_prescriptions_obj.optString("irregular_grid");
                            JSONArray irregular_grid_array = null;
                            String irregular_grid_arr = "";
                            try {
                                irregular_grid_array = last_cycle_prescriptions_obj.optJSONArray("irregular_grid_array");

                                int length = irregular_grid_array.length();
                                if (length > 0) {
                                    irregular_grid_arr = irregular_grid_array.toString();
                                    String[] recipients = new String[length];
                                    for (int j = 0; j < length; j++) {
                                        recipients[j] = irregular_grid_array.getString(j);
                                        Log.e("Complete_Response", "irregular_grid_array---->" + recipients[j]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String last_admin = "";

                            try {
                                last_admin = last_cycle_prescriptions_obj.optString("last_admin");
                                JSONObject admin_obj = new JSONObject(last_admin);
                                String mar = admin_obj.optString("mar");
                                JSONObject mar_obj = new JSONObject(mar);
                                String patinetid = mar_obj.optString("patient_id");
                                Log.e("Complete_Response", "irregular_grid_array---->" + patinetid);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            JSONArray prescribed_time_slots_arr = null;
                            String prescribed_time_slots_array_str = "";

                            try {
                                prescribed_time_slots_arr = last_cycle_prescriptions_obj.optJSONArray("prescribed_time_slots");
                                prescribed_time_slots_array_str = prescribed_time_slots_arr.toString();
                                for (int e = 0; e < prescribed_time_slots_arr.length(); e++) {
                                    JSONObject last_cycle_prescribed_time_obj = prescribed_time_slots_arr.optJSONObject(e);
                                    String prescribed_time_slots_id = last_cycle_prescribed_time_obj.optString("id");
                                    String prescribed_time_slot_time = last_cycle_prescribed_time_obj.optString("slot_time");
                                    String prescribed_time_show_as = last_cycle_prescribed_time_obj.optString("show_as");
                                    String prescribed_time_color = last_cycle_prescribed_time_obj.optString("color");
                                    String prescribed_time_dose = last_cycle_prescribed_time_obj.optString("dose");
                                    String prescribed_time_updated_at = last_cycle_prescribed_time_obj.optString("updated_at");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {

                                JSONArray todays_mars_arr = last_cycle_prescriptions_obj.optJSONArray("todays_mars");
                                for (int l = 0; l < todays_mars_arr.length(); l++) {
                                    JSONObject today_mars_obj = todays_mars_arr.optJSONObject(l);
                                    String today_mars_id = today_mars_obj.optString("id");
                                    String today_mars_administered_at = today_mars_obj.optString("administered_at");
                                    String today_mars_dose = today_mars_obj.optString("dose");
                                    String today_mars_false_reason = today_mars_obj.optString("false_reason");
                                    String today_mars_is_waste = today_mars_obj.optString("is_waste");
                                    String today_mars_outcome = today_mars_obj.optString("outcome");
                                    String today_mars_quantity_wasted = today_mars_obj.optString("quantity_wasted");
                                    String today_mars_slot_time = today_mars_obj.optString("slot_time");
                                    String today_mars_updated_at = today_mars_obj.optString("updated_at");
                                    String today_mars_user = today_mars_obj.optString("user");

                                    String userid = "";
                                    String username1 = "";
                                    String fullname1 = "";
                                    String email1 = "";

                                    try {
                                        JSONObject user_obj = new JSONObject(today_mars_user);
                                        userid = user_obj.optString("id");
                                        username1 = user_obj.optString("username");
                                        fullname1 = user_obj.optString("fullname");
                                        email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    String sec_userid = "";
                                    String sec_username1 = "";
                                    String sec_fullname1 = "";
                                    String sec_email1 = "";

                                    try {
                                        String today_mars_secondary_user = today_mars_obj.optString("secondary_user");
                                        JSONObject user_obj = new JSONObject(today_mars_secondary_user);
                                        sec_userid = user_obj.optString("id");
                                        sec_username1 = user_obj.optString("username");
                                        sec_fullname1 = user_obj.optString("fullname");
                                        sec_email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    Today_Mars_Mar_Bean_Phase2 tm1 = new Today_Mars_Mar_Bean_Phase2(today_mars_id, today_mars_administered_at, today_mars_dose, today_mars_false_reason,
                                            today_mars_is_waste, today_mars_outcome, today_mars_quantity_wasted, today_mars_slot_time,
                                            today_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + last_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    today_mars_mar_bean_list.add(tm1);

                               /*     Yesterday_Mar_Bean tm2 = new Yesterday_Mar_Bean(today_mars_id, today_mars_administered_at, today_mars_dose,
                                            today_mars_false_reason,
                                            today_mars_is_waste, today_mars_outcome, today_mars_quantity_wasted, today_mars_slot_time,
                                            today_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + last_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    yesterday_mars_mar_bean_list.add(tm2);
                                */
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                JSONArray yesterdays_mars_arr = last_cycle_prescriptions_obj.optJSONArray("yesterdays_mars");
                                for (int l = 0; l < yesterdays_mars_arr.length(); l++) {
                                    JSONObject yesterdays_mars_obj = yesterdays_mars_arr.optJSONObject(l);
                                    String yesterdays_mars_id = yesterdays_mars_obj.optString("id");
                                    String yesterdays_mars_administered_at = yesterdays_mars_obj.optString("administered_at");
                                    String yesterdays_mars_dose = yesterdays_mars_obj.optString("dose");
                                    String yesterdays_mars_false_reason = yesterdays_mars_obj.optString("false_reason");
                                    String yesterdays_mars_is_waste = yesterdays_mars_obj.optString("is_waste");
                                    String yesterdays_mars_outcome = yesterdays_mars_obj.optString("outcome");
                                    String yesterdays_mars_quantity_wasted = yesterdays_mars_obj.optString("quantity_wasted");
                                    String yesterdays_mars_slot_time = yesterdays_mars_obj.optString("slot_time");
                                    String yesterdays_mars_updated_at = yesterdays_mars_obj.optString("updated_at");
                                    String yesterdays_mars_user = yesterdays_mars_obj.optString("user");


                                    String userid = "";
                                    String username1 = "";
                                    String fullname1 = "";
                                    String email1 = "";

                                    try {
                                        JSONObject user_obj = new JSONObject(yesterdays_mars_user);
                                        userid = user_obj.optString("id");
                                        username1 = user_obj.optString("username");
                                        fullname1 = user_obj.optString("fullname");
                                        email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    String sec_userid = "";
                                    String sec_username1 = "";
                                    String sec_fullname1 = "";
                                    String sec_email1 = "";

                                    try {
                                        String today_mars_secondary_user = yesterdays_mars_obj.optString("secondary_user");
                                        JSONObject user_obj = new JSONObject(today_mars_secondary_user);
                                        sec_userid = user_obj.optString("id");
                                        sec_username1 = user_obj.optString("username");
                                        Log.e("sec_username1", "sec_username1----->" + sec_username1);
                                        sec_fullname1 = user_obj.optString("fullname");
                                        sec_email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("YESTERDAY","YESTERDAY:--"+yesterdays_mars_id);
                                    Yesterday_Mar_Bean tm1 = new Yesterday_Mar_Bean(yesterdays_mars_id, yesterdays_mars_administered_at, yesterdays_mars_dose,
                                            yesterdays_mars_false_reason,
                                            yesterdays_mars_is_waste, yesterdays_mars_outcome, yesterdays_mars_quantity_wasted, yesterdays_mars_slot_time,
                                            yesterdays_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + last_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    yesterday_mars_mar_bean_list.add(tm1);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                JSONArray tomorrows_mars_arr = last_cycle_prescriptions_obj.optJSONArray("tomorrows_mars");
                                for (int l = 0; l < tomorrows_mars_arr.length(); l++) {

                                    JSONObject tomorrows_mars_obj = tomorrows_mars_arr.optJSONObject(l);
                                    String tomorrows_mars_id = tomorrows_mars_obj.optString("id");
                                    String tomorrows_mars_administered_at = tomorrows_mars_obj.optString("administered_at");
                                    String tomorrows_mars_dose = tomorrows_mars_obj.optString("dose");
                                    String tomorrows_mars_false_reason = tomorrows_mars_obj.optString("false_reason");
                                    String tomorrows_mars_is_waste = tomorrows_mars_obj.optString("is_waste");
                                    String tomorrows_mars_outcome = tomorrows_mars_obj.optString("outcome");
                                    String tomorrows_mars_quantity_wasted = tomorrows_mars_obj.optString("quantity_wasted");
                                    String tomorrows_mars_slot_time = tomorrows_mars_obj.optString("slot_time");
                                    String tomorrows_mars_updated_at = tomorrows_mars_obj.optString("updated_at");
                                    String tomorrows_mars_user = tomorrows_mars_obj.optString("user");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            PRESCRIPTION_Model_Phase2 pmp2 = new PRESCRIPTION_Model_Phase2(last_cycle_prescriptions_id,
                                    last_cycle_prescription_type, last_cycle_prescriptions_drug_name,
                                    last_cycle_prescriptions_instructions, last_cycle_prescriptions_indications,
                                    last_cycle_prescriptions_mandatory_instructions, last_cycle_prescriptions_start_date,
                                    last_cycle_prescriptions_end_date, last_cycle_prescriptions_is_patch,
                                    last_cycle_prescriptions_patch_location, last_cycle_prescriptions_non_blistered,
                                    last_cycle_prescriptions_is_controlled, last_cycle_suppress_non_blister_display,
                                    last_cycle_can_carry_forward, last_cycle_front_image_url, last_cycle_dispensed_quantity,
                                    last_cycle_available_quantity, last_cycle_checked_in_quantity,
                                    last_cycle_brought_forward_quantity, last_cycle_returned_quantity,
                                    last_cycle_destroyed_quantity, last_cycle_carried_forward_quantity,
                                    last_cycle_wasted_quantity, last_cycle_administered_quantity,
                                    last_cycle_audited_quantity, irregular_grid, irregular_grid_arr, last_admin,
                                    last_cycle_special_medication_name, last_cycle_is_special,
                                    prescribed_time_slots_array_str, 0, "", "last_cycle", id, "");
                            lastcycle_chklist.add(pmp2);
                            returndata_list.add(pmp2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //Parsing This cycle Prescription

                    try {

                        JSONArray this_cycle_prescriptions_arr = patient_detail_obj.optJSONArray("this_cycle_prescriptions");
                        for (int p = 0; p < this_cycle_prescriptions_arr.length(); p++) {
                            JSONObject this_cycle_prescriptions_obj = this_cycle_prescriptions_arr.optJSONObject(p);
                            int this_cycle_prescriptions_id = this_cycle_prescriptions_obj.optInt("id");
                            String this_cycle_prescription_type = this_cycle_prescriptions_obj.optString("prescription_type");
                            String this_cycle_prescriptions_drug_name = this_cycle_prescriptions_obj.optString("drug_name");
                            String this_cycle_prescriptions_instructions = this_cycle_prescriptions_obj.optString("instructions");
                            String this_cycle_prescriptions_indications = this_cycle_prescriptions_obj.optString("indications");
                            String this_cycle_prescriptions_mandatory_instructions = this_cycle_prescriptions_obj.optString("mandatory_instructions");
                            String this_cycle_prescriptions_start_date = this_cycle_prescriptions_obj.optString("start_date");
                            String this_cycle_prescriptions_end_date = this_cycle_prescriptions_obj.optString("end_date");
                            String this_cycle_prescriptions_is_patch = this_cycle_prescriptions_obj.optString("is_patch");
                            String this_cycle_prescriptions_patch_location = this_cycle_prescriptions_obj.optString("patch_location");
                            String this_cycle_prescriptions_non_blistered = this_cycle_prescriptions_obj.optString("non_blistered");
                            String this_cycle_prescriptions_is_controlled = this_cycle_prescriptions_obj.optString("is_controlled");
                            String this_cycle_suppress_non_blister_display = this_cycle_prescriptions_obj.optString("suppress_non_blister_display");
                            String this_cycle_can_carry_forward = this_cycle_prescriptions_obj.optString("can_carry_forward");
                            String this_cycle_special_medication_name = this_cycle_prescriptions_obj.optString("special_medication_name");
                            String this_cycle_is_special = this_cycle_prescriptions_obj.optString("is_special");
                            String this_cycle_front_image_url = this_cycle_prescriptions_obj.optString("front_image_url");
                            int this_cycle_dispensed_quantity = this_cycle_prescriptions_obj.optInt("dispensed_quantity");
                            int this_cycle_available_quantity = this_cycle_prescriptions_obj.optInt("available_quantity");
                            int this_cycle_checked_in_quantity = this_cycle_prescriptions_obj.optInt("checked_in_quantity");
                            int this_cycle_brought_forward_quantity = this_cycle_prescriptions_obj.optInt("brought_forward_quantity");
                            int this_cycle_returned_quantity = this_cycle_prescriptions_obj.optInt("returned_quantity");
                            int this_cycle_destroyed_quantity = this_cycle_prescriptions_obj.optInt("destroyed_quantity");
                            int this_cycle_carried_forward_quantity = this_cycle_prescriptions_obj.optInt("carried_forward_quantity");
                            int this_cycle_wasted_quantity = this_cycle_prescriptions_obj.optInt("wasted_quantity");
                            int this_cycle_administered_quantity = this_cycle_prescriptions_obj.optInt("administered_quantity");
                            int this_cycle_audited_quantity = this_cycle_prescriptions_obj.optInt("audited_quantity");
                            String irregular_grid = this_cycle_prescriptions_obj.optString("irregular_grid");
                            JSONArray irregular_grid_array = null;
                            String irregular_grid_arr = "";
                            try {
                                irregular_grid_array = this_cycle_prescriptions_obj.optJSONArray("irregular_grid_array");
                                irregular_grid_arr = irregular_grid_array.toString();
                                int length = irregular_grid_array.length();
                                if (length > 0) {
                                    String[] recipients = new String[length];
                                    for (int j = 0; j < length; j++) {
                                        recipients[j] = irregular_grid_array.getString(j);

                                        Log.e("Complete_Response", "irregular_grid_array---->" + recipients[j]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String last_admin = "";

                            try {
                                last_admin = this_cycle_prescriptions_obj.optString("last_admin");
                                JSONObject admin_obj = new JSONObject(last_admin);
                                String mar = admin_obj.optString("mar");
                                JSONObject mar_obj = new JSONObject(mar);
                                String patinetid = mar_obj.optString("patient_id");
                                Log.e("Complete_Response", "irregular_grid_array---->" + patinetid);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            JSONArray prescribed_time_slots_arr = null;
                            String prescribed_time_slots_array_str = "";
                            try {
                                prescribed_time_slots_arr = this_cycle_prescriptions_obj.optJSONArray("prescribed_time_slots");
                                prescribed_time_slots_array_str = prescribed_time_slots_arr.toString();
                                for (int e = 0; e < prescribed_time_slots_arr.length(); e++) {
                                    JSONObject last_cycle_prescribed_time_obj = prescribed_time_slots_arr.optJSONObject(e);
                                    String prescribed_time_slots_id = last_cycle_prescribed_time_obj.optString("id");
                                    String prescribed_time_slot_time = last_cycle_prescribed_time_obj.optString("slot_time");
                                    String prescribed_time_show_as = last_cycle_prescribed_time_obj.optString("show_as");
                                    String prescribed_time_color = last_cycle_prescribed_time_obj.optString("color");
                                    String prescribed_time_dose = last_cycle_prescribed_time_obj.optString("dose");
                                    String prescribed_time_updated_at = last_cycle_prescribed_time_obj.optString("updated_at");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {

                                JSONArray todays_mars_arr = this_cycle_prescriptions_obj.optJSONArray("todays_mars");
                                for (int l = 0; l < todays_mars_arr.length(); l++) {
                                    JSONObject today_mars_obj = todays_mars_arr.optJSONObject(l);
                                    String today_mars_id = today_mars_obj.optString("id");
                                    String today_mars_administered_at = today_mars_obj.optString("administered_at");
                                    String today_mars_dose = today_mars_obj.optString("dose");
                                    String today_mars_false_reason = today_mars_obj.optString("false_reason");
                                    String today_mars_is_waste = today_mars_obj.optString("is_waste");
                                    String today_mars_outcome = today_mars_obj.optString("outcome");
                                    String today_mars_quantity_wasted = today_mars_obj.optString("quantity_wasted");
                                    String today_mars_slot_time = today_mars_obj.optString("slot_time");
                                    String today_mars_updated_at = today_mars_obj.optString("updated_at");
                                    String today_mars_user = today_mars_obj.optString("user");

                                    String userid = "";
                                    String username1 = "";
                                    String fullname1 = "";
                                    String email1 = "";

                                    try {
                                        JSONObject user_obj = new JSONObject(today_mars_user);
                                        userid = user_obj.optString("id");
                                        username1 = user_obj.optString("username");
                                        fullname1 = user_obj.optString("fullname");
                                        email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    String sec_userid = "";
                                    String sec_username1 = "";
                                    String sec_fullname1 = "";
                                    String sec_email1 = "";

                                    try {
                                        String today_mars_secondary_user = today_mars_obj.optString("secondary_user");
                                        JSONObject user_obj = new JSONObject(today_mars_secondary_user);
                                        sec_userid = user_obj.optString("id");
                                        sec_username1 = user_obj.optString("username");
                                        sec_fullname1 = user_obj.optString("fullname");
                                        sec_email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    Today_Mars_Mar_Bean_Phase2 tm1 = new Today_Mars_Mar_Bean_Phase2(today_mars_id, today_mars_administered_at, today_mars_dose, today_mars_false_reason,
                                            today_mars_is_waste, today_mars_outcome, today_mars_quantity_wasted, today_mars_slot_time,
                                            today_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + this_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    today_mars_mar_bean_list.add(tm1);
/*
                                    Yesterday_Mar_Bean tm2 = new Yesterday_Mar_Bean(today_mars_id, today_mars_administered_at, today_mars_dose,
                                            today_mars_false_reason,
                                            today_mars_is_waste, today_mars_outcome, today_mars_quantity_wasted, today_mars_slot_time,
                                            today_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + this_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    yesterday_mars_mar_bean_list.add(tm2);*/



                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                JSONArray yesterdays_mars_arr = this_cycle_prescriptions_obj.optJSONArray("yesterdays_mars");
                                for (int l = 0; l < yesterdays_mars_arr.length(); l++) {
                                    JSONObject yesterdays_mars_obj = yesterdays_mars_arr.optJSONObject(l);
                                    String yesterdays_mars_id = yesterdays_mars_obj.optString("id");
                                    String yesterdays_mars_administered_at = yesterdays_mars_obj.optString("administered_at");
                                    String yesterdays_mars_dose = yesterdays_mars_obj.optString("dose");
                                    String yesterdays_mars_false_reason = yesterdays_mars_obj.optString("false_reason");
                                    String yesterdays_mars_is_waste = yesterdays_mars_obj.optString("is_waste");
                                    String yesterdays_mars_outcome = yesterdays_mars_obj.optString("outcome");
                                    String yesterdays_mars_quantity_wasted = yesterdays_mars_obj.optString("quantity_wasted");
                                    String yesterdays_mars_slot_time = yesterdays_mars_obj.optString("slot_time");
                                    String yesterdays_mars_updated_at = yesterdays_mars_obj.optString("updated_at");
                                    String yesterdays_mars_user = yesterdays_mars_obj.optString("user");

                                    String userid = "";
                                    String username1 = "";
                                    String fullname1 = "";
                                    String email1 = "";

                                    try {
                                        JSONObject user_obj = new JSONObject(yesterdays_mars_user);
                                        userid = user_obj.optString("id");
                                        username1 = user_obj.optString("username");
                                        fullname1 = user_obj.optString("fullname");
                                        email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    String sec_userid = "";
                                    String sec_username1 = "";
                                    String sec_fullname1 = "";
                                    String sec_email1 = "";

                                    try {
                                        String today_mars_secondary_user = yesterdays_mars_obj.optString("secondary_user");
                                        JSONObject user_obj = new JSONObject(today_mars_secondary_user);
                                        sec_userid = user_obj.optString("id");
                                        sec_username1 = user_obj.optString("username");
                                        Log.e("sec_username1", "sec_username1----->" + sec_username1);
                                        sec_fullname1 = user_obj.optString("fullname");
                                        sec_email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("YESTERDAY","YESTERDAY:--"+yesterdays_mars_id);
                                    Yesterday_Mar_Bean tm1 = new Yesterday_Mar_Bean(yesterdays_mars_id, yesterdays_mars_administered_at, yesterdays_mars_dose,
                                            yesterdays_mars_false_reason,
                                            yesterdays_mars_is_waste, yesterdays_mars_outcome, yesterdays_mars_quantity_wasted, yesterdays_mars_slot_time,
                                            yesterdays_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + this_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    yesterday_mars_mar_bean_list.add(tm1);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                JSONArray tomorrows_mars_arr = this_cycle_prescriptions_obj.optJSONArray("tomorrows_mars");
                                for (int l = 0; l < tomorrows_mars_arr.length(); l++) {

                                    JSONObject tomorrows_mars_obj = tomorrows_mars_arr.optJSONObject(l);
                                    String tomorrows_mars_id = tomorrows_mars_obj.optString("id");
                                    String tomorrows_mars_administered_at = tomorrows_mars_obj.optString("administered_at");
                                    String tomorrows_mars_dose = tomorrows_mars_obj.optString("dose");
                                    String tomorrows_mars_false_reason = tomorrows_mars_obj.optString("false_reason");
                                    String tomorrows_mars_is_waste = tomorrows_mars_obj.optString("is_waste");
                                    String tomorrows_mars_outcome = tomorrows_mars_obj.optString("outcome");
                                    String tomorrows_mars_quantity_wasted = tomorrows_mars_obj.optString("quantity_wasted");
                                    String tomorrows_mars_slot_time = tomorrows_mars_obj.optString("slot_time");
                                    String tomorrows_mars_updated_at = tomorrows_mars_obj.optString("updated_at");
                                    String tomorrows_mars_user = tomorrows_mars_obj.optString("user");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            PRESCRIPTION_Model_Phase2 pmp2 = new PRESCRIPTION_Model_Phase2(this_cycle_prescriptions_id,
                                    this_cycle_prescription_type, this_cycle_prescriptions_drug_name,
                                    this_cycle_prescriptions_instructions, this_cycle_prescriptions_indications,
                                    this_cycle_prescriptions_mandatory_instructions, this_cycle_prescriptions_start_date,
                                    this_cycle_prescriptions_end_date, this_cycle_prescriptions_is_patch,
                                    this_cycle_prescriptions_patch_location, this_cycle_prescriptions_non_blistered,
                                    this_cycle_prescriptions_is_controlled, this_cycle_suppress_non_blister_display,
                                    this_cycle_can_carry_forward, this_cycle_front_image_url, this_cycle_dispensed_quantity,
                                    this_cycle_available_quantity, this_cycle_checked_in_quantity,
                                    this_cycle_brought_forward_quantity, this_cycle_returned_quantity,
                                    this_cycle_destroyed_quantity, this_cycle_carried_forward_quantity,
                                    this_cycle_wasted_quantity, this_cycle_administered_quantity,
                                    this_cycle_audited_quantity, irregular_grid, irregular_grid_arr,
                                    last_admin, this_cycle_special_medication_name,
                                    this_cycle_is_special, prescribed_time_slots_array_str, 0, "", "this_cycle", id, "");
                            thiscycle_chklist.add(pmp2);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Parsing Of Next Cycle Prescription

                    try {
                        JSONArray next_cycle_prescriptions_arr = patient_detail_obj.optJSONArray("next_cycle_prescriptions");
                        for (int p = 0; p < next_cycle_prescriptions_arr.length(); p++) {
                            JSONObject next_cycle_prescriptions_obj = next_cycle_prescriptions_arr.optJSONObject(p);
                            int next_cycle_prescriptions_id = next_cycle_prescriptions_obj.optInt("id");
                            String next_cycle_prescription_type = next_cycle_prescriptions_obj.optString("prescription_type");
                            String next_cycle_prescriptions_drug_name = next_cycle_prescriptions_obj.optString("drug_name");
                            String next_cycle_prescriptions_instructions = next_cycle_prescriptions_obj.optString("instructions");
                            String next_cycle_prescriptions_indications = next_cycle_prescriptions_obj.optString("indications");
                            String next_cycle_prescriptions_mandatory_instructions = next_cycle_prescriptions_obj.optString("mandatory_instructions");
                            String next_cycle_prescriptions_start_date = next_cycle_prescriptions_obj.optString("start_date");
                            String next_cycle_prescriptions_end_date = next_cycle_prescriptions_obj.optString("end_date");
                            String next_cycle_prescriptions_is_patch = next_cycle_prescriptions_obj.optString("is_patch");
                            String next_cycle_prescriptions_patch_location = next_cycle_prescriptions_obj.optString("patch_location");
                            String next_cycle_prescriptions_non_blistered = next_cycle_prescriptions_obj.optString("non_blistered");
                            String next_cycle_prescriptions_is_controlled = next_cycle_prescriptions_obj.optString("is_controlled");
                            String next_cycle_suppress_non_blister_display = next_cycle_prescriptions_obj.optString("suppress_non_blister_display");
                            String next_cycle_can_carry_forward = next_cycle_prescriptions_obj.optString("can_carry_forward");
                            String next_cycle_special_medication_name = next_cycle_prescriptions_obj.optString("special_medication_name");
                            String next_cycle_is_special = next_cycle_prescriptions_obj.optString("is_special");
                            String next_cycle_front_image_url = next_cycle_prescriptions_obj.optString("front_image_url");
                            int next_cycle_dispensed_quantity = next_cycle_prescriptions_obj.optInt("dispensed_quantity");
                            int next_cycle_available_quantity = next_cycle_prescriptions_obj.optInt("available_quantity");
                            int next_cycle_checked_in_quantity = next_cycle_prescriptions_obj.optInt("checked_in_quantity");
                            int next_cycle_brought_forward_quantity = next_cycle_prescriptions_obj.optInt("brought_forward_quantity");
                            int next_cycle_returned_quantity = next_cycle_prescriptions_obj.optInt("returned_quantity");
                            int next_cycle_destroyed_quantity = next_cycle_prescriptions_obj.optInt("destroyed_quantity");
                            int next_cycle_carried_forward_quantity = next_cycle_prescriptions_obj.optInt("carried_forward_quantity");
                            int next_cycle_wasted_quantity = next_cycle_prescriptions_obj.optInt("wasted_quantity");
                            int next_cycle_administered_quantity = next_cycle_prescriptions_obj.optInt("administered_quantity");
                            int next_cycle_audited_quantity = next_cycle_prescriptions_obj.optInt("audited_quantity");
                            String irregular_grid = next_cycle_prescriptions_obj.optString("irregular_grid");
                            JSONArray irregular_grid_array = null;
                            String irregular_grid_arr = "";

                            try {
                                irregular_grid_array = next_cycle_prescriptions_obj.optJSONArray("irregular_grid_array");
                                irregular_grid_arr = irregular_grid_array.toString();
                                int length = irregular_grid_array.length();
                                if (length > 0) {
                                    String[] recipients = new String[length];
                                    for (int j = 0; j < length; j++) {
                                        recipients[j] = irregular_grid_array.getString(j);

                                        Log.e("Complete_Response", "irregular_grid_array---->" + recipients[j]);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String last_admin = "";

                            try {
                                last_admin = next_cycle_prescriptions_obj.optString("last_admin");
                                JSONObject admin_obj = new JSONObject(last_admin);
                                String mar = admin_obj.optString("mar");
                                JSONObject mar_obj = new JSONObject(mar);
                                String patinetid = mar_obj.optString("patient_id");
                                Log.e("Complete_Response", "irregular_grid_array---->" + patinetid);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            JSONArray prescribed_time_slots_arr = null;
                            String prescribed_time_slots_array_str = "";


                            try {

                                prescribed_time_slots_arr = next_cycle_prescriptions_obj.optJSONArray("prescribed_time_slots");
                                prescribed_time_slots_array_str = prescribed_time_slots_arr.toString();
                                for (int e = 0; e < prescribed_time_slots_arr.length(); e++) {
                                    JSONObject last_cycle_prescribed_time_obj = prescribed_time_slots_arr.optJSONObject(e);
                                    String prescribed_time_slots_id = last_cycle_prescribed_time_obj.optString("id");
                                    String prescribed_time_slot_time = last_cycle_prescribed_time_obj.optString("slot_time");
                                    String prescribed_time_show_as = last_cycle_prescribed_time_obj.optString("show_as");
                                    String prescribed_time_color = last_cycle_prescribed_time_obj.optString("color");
                                    String prescribed_time_dose = last_cycle_prescribed_time_obj.optString("dose");
                                    String prescribed_time_updated_at = last_cycle_prescribed_time_obj.optString("updated_at");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {

                                JSONArray todays_mars_arr = next_cycle_prescriptions_obj.optJSONArray("todays_mars");
                                for (int l = 0; l < todays_mars_arr.length(); l++) {
                                    JSONObject today_mars_obj = todays_mars_arr.optJSONObject(l);
                                    String today_mars_id = today_mars_obj.optString("id");
                                    String today_mars_administered_at = today_mars_obj.optString("administered_at");
                                    String today_mars_dose = today_mars_obj.optString("dose");
                                    String today_mars_false_reason = today_mars_obj.optString("false_reason");
                                    String today_mars_is_waste = today_mars_obj.optString("is_waste");
                                    String today_mars_outcome = today_mars_obj.optString("outcome");
                                    String today_mars_quantity_wasted = today_mars_obj.optString("quantity_wasted");
                                    String today_mars_slot_time = today_mars_obj.optString("slot_time");
                                    String today_mars_updated_at = today_mars_obj.optString("updated_at");
                                    String today_mars_user = today_mars_obj.optString("user");

                                    String userid = "";
                                    String username1 = "";
                                    String fullname1 = "";
                                    String email1 = "";

                                    try {
                                        JSONObject user_obj = new JSONObject(today_mars_user);
                                        userid = user_obj.optString("id");
                                        username1 = user_obj.optString("username");
                                        fullname1 = user_obj.optString("fullname");
                                        email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    String sec_userid = "";
                                    String sec_username1 = "";
                                    String sec_fullname1 = "";
                                    String sec_email1 = "";

                                    try {
                                        String today_mars_secondary_user = today_mars_obj.optString("secondary_user");
                                        JSONObject user_obj = new JSONObject(today_mars_secondary_user);
                                        sec_userid = user_obj.optString("id");
                                        sec_username1 = user_obj.optString("username");
                                        Log.e("sec_username1", "sec_username1----->" + sec_username1);
                                        sec_fullname1 = user_obj.optString("fullname");
                                        sec_email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    Today_Mars_Mar_Bean_Phase2 tm1 = new Today_Mars_Mar_Bean_Phase2(today_mars_id, today_mars_administered_at, today_mars_dose, today_mars_false_reason,
                                            today_mars_is_waste, today_mars_outcome, today_mars_quantity_wasted, today_mars_slot_time,
                                            today_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + next_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);
                                  /*  Yesterday_Mar_Bean tm2 = new Yesterday_Mar_Bean(today_mars_id, today_mars_administered_at, today_mars_dose,
                                            today_mars_false_reason,
                                            today_mars_is_waste, today_mars_outcome, today_mars_quantity_wasted, today_mars_slot_time,
                                            today_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + next_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                    yesterday_mars_mar_bean_list.add(tm2);*/

                                    today_mars_mar_bean_list.add(tm1);


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                JSONArray yesterdays_mars_arr = next_cycle_prescriptions_obj.optJSONArray("yesterdays_mars");
                                for (int l = 0; l < yesterdays_mars_arr.length(); l++) {
                                    JSONObject yesterdays_mars_obj = yesterdays_mars_arr.optJSONObject(l);
                                    String yesterdays_mars_id = yesterdays_mars_obj.optString("id");
                                    String yesterdays_mars_administered_at = yesterdays_mars_obj.optString("administered_at");
                                    String yesterdays_mars_dose = yesterdays_mars_obj.optString("dose");
                                    String yesterdays_mars_false_reason = yesterdays_mars_obj.optString("false_reason");
                                    String yesterdays_mars_is_waste = yesterdays_mars_obj.optString("is_waste");
                                    String yesterdays_mars_outcome = yesterdays_mars_obj.optString("outcome");
                                    String yesterdays_mars_quantity_wasted = yesterdays_mars_obj.optString("quantity_wasted");
                                    String yesterdays_mars_slot_time = yesterdays_mars_obj.optString("slot_time");
                                    String yesterdays_mars_updated_at = yesterdays_mars_obj.optString("updated_at");
                                    String yesterdays_mars_user = yesterdays_mars_obj.optString("user");



                                    String userid = "";
                                    String username1 = "";
                                    String fullname1 = "";
                                    String email1 = "";

                                    try {
                                        JSONObject user_obj = new JSONObject(yesterdays_mars_user);
                                        userid = user_obj.optString("id");
                                        username1 = user_obj.optString("username");
                                        fullname1 = user_obj.optString("fullname");
                                        email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    String sec_userid = "";
                                    String sec_username1 = "";
                                    String sec_fullname1 = "";
                                    String sec_email1 = "";

                                    try {
                                        String today_mars_secondary_user = yesterdays_mars_obj.optString("secondary_user");
                                        JSONObject user_obj = new JSONObject(today_mars_secondary_user);
                                        sec_userid = user_obj.optString("id");
                                        sec_username1 = user_obj.optString("username");
                                        Log.e("sec_username1", "sec_username1----->" + sec_username1);
                                        sec_fullname1 = user_obj.optString("fullname");
                                        sec_email1 = user_obj.optString("email");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("YESTERDAY","YESTERDAY:--"+yesterdays_mars_id);
                                    Yesterday_Mar_Bean tm1 = new Yesterday_Mar_Bean(yesterdays_mars_id, yesterdays_mars_administered_at, yesterdays_mars_dose,
                                            yesterdays_mars_false_reason,
                                            yesterdays_mars_is_waste, yesterdays_mars_outcome, yesterdays_mars_quantity_wasted, yesterdays_mars_slot_time,
                                            yesterdays_mars_updated_at, userid, username1, fullname1,
                                            email1, "", "", "" + next_cycle_prescriptions_id,
                                            id, sec_userid, sec_username1, sec_fullname1, sec_email1);

                                     yesterday_mars_mar_bean_list.add(tm1);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                JSONArray tomorrows_mars_arr = next_cycle_prescriptions_obj.optJSONArray("tomorrows_mars");
                                for (int l = 0; l < tomorrows_mars_arr.length(); l++) {

                                    JSONObject tomorrows_mars_obj = tomorrows_mars_arr.optJSONObject(l);
                                    String tomorrows_mars_id = tomorrows_mars_obj.optString("id");
                                    String tomorrows_mars_administered_at = tomorrows_mars_obj.optString("administered_at");
                                    String tomorrows_mars_dose = tomorrows_mars_obj.optString("dose");
                                    String tomorrows_mars_false_reason = tomorrows_mars_obj.optString("false_reason");
                                    String tomorrows_mars_is_waste = tomorrows_mars_obj.optString("is_waste");
                                    String tomorrows_mars_outcome = tomorrows_mars_obj.optString("outcome");
                                    String tomorrows_mars_quantity_wasted = tomorrows_mars_obj.optString("quantity_wasted");
                                    String tomorrows_mars_slot_time = tomorrows_mars_obj.optString("slot_time");
                                    String tomorrows_mars_updated_at = tomorrows_mars_obj.optString("updated_at");
                                    String tomorrows_mars_user = tomorrows_mars_obj.optString("user");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            PRESCRIPTION_Model_Phase2 pmp2 = new PRESCRIPTION_Model_Phase2(next_cycle_prescriptions_id,
                                    next_cycle_prescription_type, next_cycle_prescriptions_drug_name,
                                    next_cycle_prescriptions_instructions, next_cycle_prescriptions_indications,
                                    next_cycle_prescriptions_mandatory_instructions, next_cycle_prescriptions_start_date,
                                    next_cycle_prescriptions_end_date, next_cycle_prescriptions_is_patch,
                                    next_cycle_prescriptions_patch_location, next_cycle_prescriptions_non_blistered,
                                    next_cycle_prescriptions_is_controlled, next_cycle_suppress_non_blister_display,
                                    next_cycle_can_carry_forward, next_cycle_front_image_url, next_cycle_dispensed_quantity,
                                    next_cycle_available_quantity, next_cycle_checked_in_quantity, next_cycle_brought_forward_quantity,
                                    next_cycle_returned_quantity, next_cycle_destroyed_quantity, next_cycle_carried_forward_quantity,
                                    next_cycle_wasted_quantity, next_cycle_administered_quantity, next_cycle_audited_quantity,
                                    irregular_grid, irregular_grid_arr, last_admin, next_cycle_special_medication_name,
                                    next_cycle_is_special, prescribed_time_slots_array_str, 0, "", "next_cycle", id, "");
                            nextcycle_chklist.add(pmp2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                String caretaker_id = PreferenceConnector.readString(PatientsActivity.this, PreferenceConnector.LAST_LOGIN_CARETAKER_ID, "");
                mydbobj.insert_patients_list(new_patient_Model_List, caretaker_id);
                mydbobj.insertNotesData(notelist, "online", "General");
                Log.w("LAST_CYCLE", "BEFORE LAST_CYCLE");
                mydbobj.addAllFeed(PatientsActivity.this, lastcycle_chklist);
                Log.w("RETURN", "BEFORE RETURN");
                mydbobj.addAllFeed(PatientsActivity.this, returndata_list);
                Log.w("THIS CYCLE", "BEFORE THIS CYCLE");
                mydbobj.addAllFeed(PatientsActivity.this, thiscycle_chklist);
                Log.w("NEXT CYCLE", "BEFORE NEXT CYCLE");
                mydbobj.addAllFeed(PatientsActivity.this, nextcycle_chklist);
                mydbobj.insertMeasureData(measurearraylist, "online");
                mydbobj.insert_today_marsData_admin(today_mars_mar_bean_list, "online");
                mydbobj.insert_yesterday_marsData_admin(yesterday_mars_mar_bean_list, "online");
                adptr = new New_Patients_Adapter(PatientsActivity.this, new_patient_Model_List, "online");

                lvPatients.setAdapter(adptr);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}