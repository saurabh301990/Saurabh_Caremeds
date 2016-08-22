package com.syscraft.caremeds.dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syscraft.caremeds.PatientDetail;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.Utill_parser.JSONParser;
import com.syscraft.caremeds.constants.App_Constants;
//import com.syscraft.caremeds.database.DatabaseHandler;
import com.syscraft.caremeds.database.CRMDS_Database_Handler;
import com.syscraft.caremeds.model.OFFLINE_DATA_MODEL;
import com.syscraft.caremeds.serverCommunication.NetworkAvailablity;
import com.syscraft.caremeds.serverCommunication.WebServiceDetails;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;
//import com.syscraft.caremeds.sharedPrefrns.SharedPrefrnceCareMeds;

import org.apache.http.HttpResponse;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;


public class Warfarine_Quantity_Dialog extends DialogFragment {

    Warfarine_QuantityListener callback;
    TextView cur_dose_value_textview;
    EditText tablet_qty_etdtxt, new_inr_reading_etdtxt, warfrine_dose_etdtxt;
    static TextView test_date_txtvw;
    TextView down_arrow_textvw, up_arrow_textvw;
    View dialoglayout;
    int Position;
    private JSONParser jsonParser;
    static final int DATE_DIALOG_ID = 999;
    private boolean _inrReadingRequired;
    private boolean _expectINR;
    private String patientId;
    private String Auth_token;
    LinearLayout below_3_box;
    private ArrayList<String> _warning_overrides_list;
    CRMDS_Database_Handler mydbobj;

    public interface Warfarine_QuantityListener {
        public void Warfarine_QuantityUpdate(String slot_time, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab, ArrayList<String> _warning_overrides_list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (Warfarine_QuantityListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        jsonParser = new JSONParser(getActivity());
        mydbobj = new CRMDS_Database_Handler(getActivity());

//        if (_inrReadingRequired) {
//            _expectINR = true;
//            Log.e("Go---->", "1111111");
////            blank_alert("A new INR reading is required !");
//
//            App_Constants.showAlertDialog("Alert","A new INR reading is required !", getContext(), false);
//
//        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Confirm Warfarin Quantity");
        builder1.setPositiveButton("Confirm", null);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.warfarine_dose_dialog_layout, null);
//        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        Bundle args = getArguments();
        String currentName = args.getString("name");
        Log.e("Warfarine_Quantity_Dialog", "currentName-->" + currentName);
        Position = args.getInt("position");
        Log.e("Warfarine_Quantity_Dialog", "Position-->" + Position);

        final String tab = args.getString("tab");
        Log.e("Warfarine_Quantity_Dialog", "tab-->" + tab);

        final String prescribed_slot_time = args.getString("pre_slot_time");
        Log.e("Warfarine_Quantity_Dialog", "prescribed_slot_time-->" + prescribed_slot_time);

        final String prescribed_slotDose = args.getString("_slotDose");
        Log.e("Warfarine_Quantity_Dialog", "prescribed_slotDose-->" + prescribed_slotDose);

        String _inr_date = args.getString("_inr_date");
        Log.e("Warfarine_Quantity_Dialog", "_inr_date-->" + _inr_date);
        String _warfarinDose = args.getString("_warfarinDose");
        Log.e("Warfarine_Quantity_Dialog", "_warfarinDose-->" + _warfarinDose);
        String _inr_reading = args.getString("_inr_reading");
        Log.e("Warfarine_Quantity_Dialog", "_inr_reading-->" + _inr_reading);

        patientId = args.getString("patientId");
        Log.e("Warfarine_Quantity_Dialog", "patientId-->" + patientId);

        _warning_overrides_list = args.getStringArrayList("warninglist");


        TextView txtQuanTitle = (TextView) dialoglayout.findViewById(R.id.txtQuanTitle);
        txtQuanTitle.setText(currentName);
        cur_dose_value_textview = (TextView) dialoglayout.findViewById(R.id.cur_dose_value_textview);

        cur_dose_value_textview.setText(_warfarinDose + " mg");

        //txtQuan.setText(quantity);
        //txtQuan.setSelection(txtQuan.getText().length());


        tablet_qty_etdtxt = (EditText) dialoglayout.findViewById(R.id.tablet_qty_etdtxt);
        test_date_txtvw = (TextView) dialoglayout.findViewById(R.id.test_date_txtvw);
        new_inr_reading_etdtxt = (EditText) dialoglayout.findViewById(R.id.new_inr_reading_etdtxt);
        warfrine_dose_etdtxt = (EditText) dialoglayout.findViewById(R.id.warfrine_dose_etdtxt);
        below_3_box = (LinearLayout) dialoglayout.findViewById(R.id.below_3_box);
        down_arrow_textvw = (TextView) dialoglayout.findViewById(R.id.down_arrow_textvw);
        up_arrow_textvw = (TextView) dialoglayout.findViewById(R.id.up_arrow_textvw);


        tablet_qty_etdtxt.setText(prescribed_slotDose);
        test_date_txtvw.setText("" + _inr_date);

        if (!_inr_reading.isEmpty()) {

            double inr = Double.parseDouble(_inr_reading);
//            int inrrrrr = inr.intValue();
            int inrrrrr = (int) inr;
            new_inr_reading_etdtxt.setText("" + inrrrrr);
        }

        warfrine_dose_etdtxt.setText("" + _warfarinDose);

        // do we need new INR readings ?
        _inrReadingRequired = ((Date_utility.Check_inr_date_diffrence(_inr_date)) || (_inr_date.isEmpty()) || (_warfarinDose.isEmpty())) ? true : false;
        _expectINR = false;

        if (_inrReadingRequired) {
            _expectINR = true;
//            Log.e("Go---->","1111111");
//            blank_alert("A new INR reading is required !");

        }

        if (_expectINR) {
            down_arrow_textvw.setVisibility(View.VISIBLE);
            up_arrow_textvw.setVisibility(View.GONE);
            below_3_box.setVisibility(View.GONE);
        } else {
            up_arrow_textvw.setVisibility(View.VISIBLE);
            down_arrow_textvw.setVisibility(View.GONE);
            below_3_box.setVisibility(View.VISIBLE);
        }


        down_arrow_textvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                up_arrow_textvw.setVisibility(View.VISIBLE);
                down_arrow_textvw.setVisibility(View.GONE);
                below_3_box.setVisibility(View.VISIBLE);
            }
        });


        up_arrow_textvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                up_arrow_textvw.setVisibility(View.GONE);
                down_arrow_textvw.setVisibility(View.VISIBLE);
                below_3_box.setVisibility(View.GONE);

            }
        });


        test_date_txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "datePicker");
//              showDialog(DATE_DIALOG_ID);

            }
        });


//        builder1.setPositiveButton(
//                "Confirm",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        String date_value = test_date_txtvw.getText().toString();
//                        String tablet_qty_value = tablet_qty_etdtxt.getText().toString();
//                        String new_inr_reading_value = new_inr_reading_etdtxt.getText().toString();
//                        String warfrine_dose_value = warfrine_dose_etdtxt.getText().toString();
//
//                        Log.e("Warfarine_Quantity_Dialog ", "ConfirmBtn---->");
//                        boolean validationPassed = true;
//                        String validationMessage = "";
//
//                        if (tablet_qty_value.isEmpty()) {
//                            validationPassed = false;
//                            validationMessage = validationMessage + "You must enter a quantity\n";
//                        }
//
//
//                        // check new INR test date is in the future
//                        if ((Date_utility.Check_inr_date_diffrence(date_value))) {
//                            validationPassed = false;
//                            validationMessage = validationMessage + "New INR test date must be in the future\n";
//                        }
//
//                        Log.e("Warfarine_Quantity_Dialog ", "validationPassed---->" + validationPassed);
//
//
//                        if (validationPassed) {
//                            String secondary_user_id = "0";
//                            String quantity_wasted = "0";
//                            String uid = UUID.randomUUID().toString();
////                          System.out.println("uuid = " + uid);
//
//                            Log.e("Warfarine_Quantity_Dialog ", "uuid---->" + uid);
//
//                            Log.e("_inrReadingRequired ", "_inrReadingRequired---->" + _inrReadingRequired);
//
//                            // write new values to API
////                                _api.createMeasurement(_patientId, 'INR', inrReading, function() {});
//                            new saveMeasureData().execute("INR", "" + new_inr_reading_value);
////                                _api.createNote(_patientId, null, 'Warfarin_Dose', 'New Warfarin Dose', warfarinDose, function() {});
//                            new CreateNote_Async().execute(warfrine_dose_value, "New Warfarin Dose", "Warfarin_Dose");
////                                _api.createNote(_patientId, null, 'INR_Test_Date', 'New INR Test Date', inrDate, function() {});
//                            new CreateNote_Async().execute(date_value, "New INR Test Date", "INR_Test_Date");
//
//                            callback.Warfarine_QuantityUpdate(prescribed_slot_time, "true", "", "" + tablet_qty_value, secondary_user_id, "false", quantity_wasted, uid, Position, tab);
//
//                        } else {
//
//                            Log.e("Warfarine_Quantity_Dialog ", "validationfailed---->");
////                            blank_alert(validationMessage);
//                            App_Constants.showAlertDialog("Alert", validationMessage, getContext(), false);
//                        }
//
//                        dialog.dismiss();
//                    }
//                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        Log.e("Go---->", "2222222");
        builder1.setView(dialoglayout);

        final AlertDialog mAlertDialog = builder1.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String date_value = test_date_txtvw.getText().toString();
                        String tablet_qty_value = tablet_qty_etdtxt.getText().toString();
                        String new_inr_reading_value = new_inr_reading_etdtxt.getText().toString();
                        String warfrine_dose_value = warfrine_dose_etdtxt.getText().toString();

                        Log.e("Warfarine_Quantity_Dialog ", "ConfirmBtn---->");
                        boolean validationPassed = true;
                        String validationMessage = "";

                        if (tablet_qty_value.isEmpty()) {
                            validationPassed = false;
                            validationMessage = validationMessage + "You must enter a quantity\n";
                        }


                        // check new INR test date is in the future
                        if (!(Date_utility.confirm_btn_Check_inr_date_diffrence(date_value))) {
                            validationPassed = false;
                            validationMessage = validationMessage + "New INR test date must be in the future\n";
                        }

                        Log.e("Warfarine_Quantity_Dialog ", "validationPassed---->" + validationPassed);


                        if (validationPassed) {
                            String secondary_user_id = "0";
                            String quantity_wasted = "0";
                            String uid = UUID.randomUUID().toString();
//                          System.out.println("uuid = " + uid);

                            Log.e("Warfarine_Quantity_Dialog ", "uuid---->" + uid);

                            Log.e("_inrReadingRequired ", "_inrReadingRequired---->" + _inrReadingRequired);

                            boolean is_anything_remains_to_sync = check_is_anything_remains_to_sync();

                            if (NetworkAvailablity.chkStatus(getActivity()) && !is_anything_remains_to_sync) {

                                // write new values to API
//                                _api.createMeasurement(_patientId, 'INR', inrReading, function() {});
                                new saveMeasureData().execute("INR", "" + new_inr_reading_value);
//                                _api.createNote(_patientId, null, 'Warfarin_Dose', 'New Warfarin Dose', warfarinDose, function() {});
                                new CreateNote_Async(getActivity()).execute(warfrine_dose_value, "New Warfarin Dose", "Warfarin_Dose");
//                                _api.createNote(_patientId, null, 'INR_Test_Date', 'New INR Test Date', inrDate, function() {});
                                new CreateNote_Async2222(getActivity()).execute(date_value, "New INR Test Date", "INR_Test_Date");

                            } else {

                                save_measuredata_offline("INR", "" + new_inr_reading_value);
                                save_warfarine_dose_offline(warfrine_dose_value, "New Warfarin Dose", "Warfarin_Dose", getActivity());
                                save_INRdate_offline(date_value, "New INR Test Date", "INR_Test_Date", getActivity());

                            }

                            mAlertDialog.dismiss();
                            callback.Warfarine_QuantityUpdate(prescribed_slot_time, "true", "", "" + tablet_qty_value, secondary_user_id, "false", quantity_wasted, uid, Position, tab, _warning_overrides_list);

                        } else {

                            Log.e("Warfarine_Quantity_Dialog ", "validationfailed---->");
//                            blank_alert(validationMessage);
                            App_Constants.showAlertDialog("Alert", validationMessage, getContext(), false);

                        }
                    }
                });
            }
        });

        return mAlertDialog;
    }


    private void save_INRdate_offline(String content_value, String sub, String ntype, Context contxt) {

        String caretaker_id = PreferenceConnector.readString(getActivity(), PreferenceConnector.USERID, "");
        JSONObject json = new JSONObject();

        try {
            String baseurl_value = PreferenceConnector.readString(contxt, PreferenceConnector.SETTING_BASE_URL, "");
            Log.e("baseurl_value ", "baseurl_value  --->" + baseurl_value);
            baseurl_value = baseurl_value + "/patients/";
            String weburl = baseurl_value + patientId + "/notes";
            Log.e("HttpPost ", "CREATE_GENERAL_NOTE_ weburl --->" + weburl);
            Auth_token = PreferenceConnector.readString(contxt, PreferenceConnector.AUTH_TOKEN, "");
            Log.e("Auth_token ", "Auth_token  --->" + Auth_token);
            json.put("authentication_token", Auth_token);

            JSONObject notedata = new JSONObject();
            try {
                notedata.put("prescription_id", "");
                notedata.put("note_type", ntype);
                notedata.put("subject", sub);
                notedata.put("content", content_value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.put("note", notedata);
            Log.e("CREATE_GENERAL_NOTE_", "post data --->" + json.toString());
//            result = jsonParser.postdata_method(weburl, json.toString());

            mydbobj.insert_offline_Data(weburl, json.toString(), caretaker_id, "inr_reading", "", patientId);
            mydbobj.update_inr_date(content_value, patientId);
            PatientDetail._global_inr_date = content_value;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CREATE_GENERAL_NOTE_ ", "error-------------->" + e.toString());
        }


    }


    private void save_warfarine_dose_offline(String warfrine_dose_value, String sub, String ntype, Context contxt) {

        String caretaker_id = PreferenceConnector.readString(getActivity(), PreferenceConnector.USERID, "");
        JSONObject json = new JSONObject();

        try {
            String baseurl_value = PreferenceConnector.readString(contxt, PreferenceConnector.SETTING_BASE_URL, "");
            Log.e("baseurl_value ", "baseurl_value  --->" + baseurl_value);
            baseurl_value = baseurl_value + "/patients/";
            String weburl = baseurl_value + patientId + "/notes";
            Log.e("HttpPost ", "CREATE_GENERAL_NOTE_ weburl --->" + weburl);
            Auth_token = PreferenceConnector.readString(contxt, PreferenceConnector.AUTH_TOKEN, "");
            Log.e("Auth_token ", "Auth_token  --->" + Auth_token);
            json.put("authentication_token", Auth_token);

            JSONObject notedata = new JSONObject();
            try {
                notedata.put("prescription_id", "");
                notedata.put("note_type", ntype);
                notedata.put("subject", sub);
                notedata.put("content", warfrine_dose_value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.put("note", notedata);
            Log.e("CREATE_GENERAL_NOTE_", "post data --->" + json.toString());
//            result = jsonParser.postdata_method(weburl, json.toString());

            mydbobj.insert_offline_Data(weburl, json.toString(), caretaker_id, "inr_reading", "", patientId);
            mydbobj.update_warfarin_dose(warfrine_dose_value, patientId);
            PatientDetail._global_warfarin_dose = warfrine_dose_value;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CREATE_GENERAL_NOTE_ ", "error-------------->" + e.toString());
        }


    }

    private void save_measuredata_offline(String type, String inrval) {

        String caretaker_id = PreferenceConnector.readString(getActivity(), PreferenceConnector.USERID, "");
        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();

        try {
            String baseurl_value = PreferenceConnector.readString(getActivity(), PreferenceConnector.SETTING_BASE_URL, "");
            baseurl_value = baseurl_value + "/patients/";
            String weburl = baseurl_value + patientId + "/measurements";
            Log.e("httpPost", "saveMeasureData url--->" + weburl);
            Auth_token = PreferenceConnector.readString(getActivity(), PreferenceConnector.AUTH_TOKEN, "");
            json.put("authentication_token", Auth_token);
            json1.put("measurement_type", type);
            json1.put("value", inrval);
            json.put("measurement", json1);
            Log.e("saveMeasureData", "post data --->" + json.toString());
//            result = jsonParser.postdata_method(weburl, json.toString());

            mydbobj.insert_offline_Data(weburl, json.toString(), caretaker_id, "inr_reading", "", patientId);
            mydbobj.update_Inr_Reading(inrval, patientId);
            PatientDetail._global_inr_reading = inrval;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Save measure error", "" + e.toString());
        }


    }


    /*******************************************************/
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

// Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//            return new DatePickerDialog(getActivity(), this, year, month, day);

            return datePickerDialog;

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(c.getTime());


            // set selected date into textview
            test_date_txtvw.setText(new StringBuilder().append(year)
                    .append("-").append(month + 1).append("-").append(day)
                    .append(""));


        }
    }


    /***************************************************************/

//    private void blank_alert(String myMessage) {
//
////        String myMessage = "A new INR reading is required !\n";
//
//        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(
//                getActivity());
//
//        // set title
//        alertDialogBuilder2.setTitle("Alert");
//
//        // set dialog message
//        alertDialogBuilder2
//                .setMessage("" + myMessage)
//                .setCancelable(false)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog2, int id) {
//                        dialog2.cancel();
//                    }
//                });
//
//
//        // create alert dialog
//        AlertDialog alertDialog2 = alertDialogBuilder2.create();
//
//        // show it
//        alertDialog2.show();
//
//    }


    /****************************************************************/

    private class saveMeasureData extends AsyncTask<String, String, String> {

        String new_inrrrr;

//      Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
//          dlg = dialog(getActivity(), "Loading");
        }

        HttpResponse loginResponse;

        @Override
        protected String doInBackground(String... params) {
//            HttpClient client = new DefaultHttpClient();
//            HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                //https://demo.caremeds.co.uk/api/patients/164/measurements
                String baseurl_value = PreferenceConnector.readString(getActivity(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/measurements";
//                HttpPost post = new HttpPost(weburl);
                Log.e("httpPost", "saveMeasureData url--->" + weburl);


//                Auth_token = SharedPrefrnceCareMeds.getSharedPrefData(getActivity(), "auth_token");
                Auth_token = PreferenceConnector.readString(getActivity(), PreferenceConnector.AUTH_TOKEN, "");
                json.put("authentication_token", Auth_token);
                json1.put("measurement_type", params[0]);
                json1.put("value", params[1]);
                json.put("measurement", json1);
                new_inrrrr = params[1];
                Log.e("saveMeasureData", "post data --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());
//                StringEntity se = new StringEntity(json.toString());
//                se.setContentType(new BasicHeader("Content-type", "application/json"));
//                post.setEntity(se);
//                loginResponse = client.execute(post);
//              /*Checking response */
//                if (loginResponse != null) {
//                    InputStream in = loginResponse.getEntity().getContent(); //Get the data in the entity
//                    result = convertStreamToString(in);
//                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Save measure error", "" + e.toString());
            }
            Log.e("save measure ", "save measure --------------" + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
//          if (dlg.isShowing()) {
//
//              dlg.dismiss();
//          }
            Log.e("save measure result ", "save measure result ------------------------- " + result);
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    Log.e("save measure result ", "inside save measure result ------------------------- " + result);
//                  getMeasure();

                    PatientDetail._global_inr_reading = new_inrrrr;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /*****************************************************************/

    private class CreateNote_Async extends AsyncTask<String, String, String> {
        private final Context contxt;

        String typ, value;
//        Dialog dlg;

        public CreateNote_Async(Context context) {

            this.contxt = context;
        }

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
//            dlg = dialog(PatientDetail.this, "Loading");
        }

        HttpResponse response;

        @Override
        protected String doInBackground(String... params) {
//            HttpClient client = new DefaultHttpClient();
//            HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                //https://demo.caremeds.co.uk/api/patients/164/measurements
                String baseurl_value = PreferenceConnector.readString(contxt, PreferenceConnector.SETTING_BASE_URL, "");
                Log.e("baseurl_value ", "baseurl_value  --->" + baseurl_value);
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/notes";
                Log.e("HttpPost ", "CREATE_GENERAL_NOTE_ weburl --->" + weburl);
                Auth_token = PreferenceConnector.readString(contxt, PreferenceConnector.AUTH_TOKEN, "");
                Log.e("Auth_token ", "Auth_token  --->" + Auth_token);
//                HttpPost post = new HttpPost(weburl);
                json.put("authentication_token", Auth_token);

                JSONObject notedata = new JSONObject();

                typ = params[2];
                value = params[0];
                try {
                    notedata.put("prescription_id", "");
                    notedata.put("note_type", params[2]);
                    notedata.put("subject", params[1]);
                    notedata.put("content", params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                json.put("note", notedata);
//                json1.put("value", params[1]);
//                json.put("measurement", json1);

                Log.e("CREATE_GENERAL_NOTE_", "post data --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());
//                StringEntity se = new StringEntity(json.toString());
//                se.setContentType(new BasicHeader("Content-type", "application/json"));
//                post.setEntity(se);
//                response = client.execute(post);
              /*Checking response */
//                if (response != null) {
//                    InputStream in = response.getEntity().getContent(); //Get the data in the entity
//                    result = convertStreamToString(in);
//                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("CREATE_GENERAL_NOTE_ ", "error-------------->" + e.toString());
            }
            Log.e("CREATE_GENERAL_NOTE_ ", "doInBackground response -------------->" + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
//            if (dlg.isShowing()) {
//
//                dlg.dismiss();
//            }
            Log.e("CREATE_GENERAL_NOTE_  ", " -----------onPostExecute--------------> " + result);
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    Log.e("CREATE_GENERAL_NOTE_  ", "inside CREATE_GENERAL_NOTE_ result ------------------------- " + result);
//                    getNotes();

                    if (typ.equals("Warfarin_Dose")) {
                        PatientDetail._global_warfarin_dose = value;
                        Log.e("PatientDetail._global_warfarin_dose", "PatientDetail._global_warfarin_dose---->" + PatientDetail._global_warfarin_dose);

                    } else if (typ.equals("INR_Test_Date")) {

                        PatientDetail._global_inr_date = value;
                        Log.e("PatientDetail._global_inr_date", "PatientDetail._global_inr_date---->" + PatientDetail._global_inr_date);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /*****************************************************************/

    private class CreateNote_Async2222 extends AsyncTask<String, String, String> {

        String typ, value;
        Context context;

        public CreateNote_Async2222(Context ctx) {

            context = ctx;
        }
//        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
//            dlg = dialog(PatientDetail.this, "Loading");
        }

        HttpResponse response;

        @Override
        protected String doInBackground(String... params) {
//            HttpClient client = new DefaultHttpClient();
//            HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                //https://demo.caremeds.co.uk/api/patients/164/measurements
                String baseurl_value = PreferenceConnector.readString(context, PreferenceConnector.SETTING_BASE_URL, "");
                Log.e("baseurl_value ", "baseurl_value  --->" + baseurl_value);
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/notes";
                Log.e("HttpPost ", "CREATE_GENERAL_NOTE_ weburl --->" + weburl);
//                HttpPost post = new HttpPost(weburl);
                Auth_token = PreferenceConnector.readString(context, PreferenceConnector.AUTH_TOKEN, "");
                Log.e("Auth_token ", "Auth_token  --->" + Auth_token);
                json.put("authentication_token", Auth_token);

                JSONObject notedata = new JSONObject();

                typ = params[2];
                value = params[0];
                try {
                    notedata.put("prescription_id", "");
                    notedata.put("note_type", params[2]);
                    notedata.put("subject", params[1]);
                    notedata.put("content", params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                json.put("note", notedata);
//                json1.put("value", params[1]);
//                json.put("measurement", json1);

                Log.e("CREATE_GENERAL_NOTE_", "post data --->" + json.toString());

                result = jsonParser.postdata_method(weburl, json.toString());
//                StringEntity se = new StringEntity(json.toString());
//                se.setContentType(new BasicHeader("Content-type", "application/json"));
//                post.setEntity(se);
//                response = client.execute(post);
              /*Checking response */
//                if (response != null) {
//                    InputStream in = response.getEntity().getContent(); //Get the data in the entity
//                    result = convertStreamToString(in);
//                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("CREATE_GENERAL_NOTE_ ", "error-------------->" + e.toString());
            }
            Log.e("CREATE_GENERAL_NOTE_ ", "doInBackground response -------------->" + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
//            if (dlg.isShowing()) {
//
//                dlg.dismiss();
//            }
            Log.e("CREATE_GENERAL_NOTE_  ", " -----------onPostExecute--------------> " + result);
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    Log.e("CREATE_GENERAL_NOTE_  ", "inside CREATE_GENERAL_NOTE_ result ------------------------- " + result);
//                    getNotes();

                    if (typ.equals("Warfarin_Dose")) {
                        PatientDetail._global_warfarin_dose = value;
                        Log.e("PatientDetail._global_warfarin_dose", "PatientDetail._global_warfarin_dose---->" + PatientDetail._global_warfarin_dose);

                    } else if (typ.equals("INR_Test_Date")) {

                        PatientDetail._global_inr_date = value;
                        Log.e("PatientDetail._global_inr_date", "PatientDetail._global_inr_date---->" + PatientDetail._global_inr_date);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /***********************************************************************************************/

    private boolean check_is_anything_remains_to_sync() {

        boolean b1 = false;

        ArrayList<OFFLINE_DATA_MODEL> offline_modelArrayList = new ArrayList<OFFLINE_DATA_MODEL>();
        String caretaker_id = PreferenceConnector.readString(getActivity(), PreferenceConnector.LAST_LOGIN_CARETAKER_ID, "");
        offline_modelArrayList = mydbobj.get_offline_Listfromdb(caretaker_id);
        Log.e("sync...", "offline_modelArrayList size --->" + offline_modelArrayList.size());

        if (offline_modelArrayList.size() > 0) {
            b1 = true;
        } else {

            b1 = false;
        }


        return b1;
    }


}
