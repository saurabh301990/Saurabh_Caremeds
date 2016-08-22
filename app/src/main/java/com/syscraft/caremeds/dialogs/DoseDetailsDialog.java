package com.syscraft.caremeds.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syscraft.caremeds.New_Model.PRESCRIBED_TIME_SLOT_phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.adapters.Dose_Details_Adapter;
import com.syscraft.caremeds.model.LAST_ADMIN_MAR;

import com.syscraft.caremeds.model.PrescriptionModel;

import com.syscraft.caremeds.serverCommunication.WebServiceDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DoseDetailsDialog extends DialogFragment {

    private DoseDetailsListener callback;
    PrescriptionModel prescriptionModel;
    TextView instructionTxt, mend_insTxt, indicationTxt, dose, takeLast;
    //    TextView instructionTxt, mend_insTxt, indicationTxt, doseTxt, dose, morningTxt, lunchtxt, nightTxt, takeLast;
    ImageView doseimage;
    View imvSlot;
    String taken;    //"https://demo.caremeds.co.uk/"

    public interface DoseDetailsListener {
        public void DoseDetailsUpdate(String sub, String detail);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (DoseDetailsListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder.setCancelable(true);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Bundle args = getArguments();
        String currentName = args.getString("name");
        String instruction = args.getString("instruction");
        String mend_ins = args.getString("ins_mendatory");
        String indication = args.getString("indication");
        int position = args.getInt("position");
        String frontImg = args.getString("imageUrl");


        String imageUrl = "https://demo.caremeds.co.uk/" + frontImg;
//
//        ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = (ArrayList<PRESCRIBED_TIME_SLOT>) args.getSerializable("PRES_TIME_SLOT_objects_list");
//        ArrayList<Today_Mars_Mar_Bean> Today_Mars_Mar_objects_list = (ArrayList<Today_Mars_Mar_Bean>) args.getSerializable("Today_Mars_Mar_objects_list");
//
//        String last_admin_mar_Dose = "";
//        String last_admin_mar_username = "";
//        String last_admin_mar_created_Date = "";
//
//        try {
//
//            LAST_ADMIN_MAR last_admin_mar = (LAST_ADMIN_MAR) args.getSerializable("last_admin_mar");
//
//            last_admin_mar_Dose = last_admin_mar.getDose();
//            last_admin_mar_username = last_admin_mar.getUser_fullname();
//
//            last_admin_mar_created_Date = last_admin_mar.getCreated_at();
//
//            Log.e("last_admin_mar_Dose", "--->" + last_admin_mar_Dose);
//            Log.e("last_admin_mar_username", "--->" + last_admin_mar_username);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String fullname = args.getString("fullNmae");


//        String imageUrl = WebServiceDetails.IMAGE_BASE_URL + frontImg;

        String lastadmin = args.getString("lastadmin");
        String Prescribed_time_slots_array = args.getString("Prescribed_time_slots_array");

        String last_admin_mar_Dose = "";
        String last_admin_mar_username = "";
        String last_admin_mar_created_Date = "";
        ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT_phase2>();

        try {
            JSONObject obj7 = new JSONObject(lastadmin);
            JSONObject obj8 = obj7.optJSONObject("mar");

            last_admin_mar_created_Date = obj8.optString("created_at");
//            String last_admin_deleted_at = obj8.optString("deleted_at");
            last_admin_mar_Dose = obj8.optString("dose");
//            String false_reason = obj8.optString("false_reason");
//            String gps_location = obj8.optString("gps_location");
//            String homely_remedy_id = obj8.optString("homely_remedy_id");
//            String homely_remedy_name = obj8.optString("homely_remedy_name");
//            String mar_id = obj8.optString("id");
//            String is_waste = obj8.optString("is_waste");
//            String medication_id = obj8.optString("medication_id");
//            String outcome = obj8.optString("outcome");
//            String patient_id = obj8.optString("patient_id");
//            String prescription_id = obj8.optString("prescription_id");
//            //  String show_as = jsonobj.optString("show_as");
//            String quantity_wasted = obj8.optString("quantity_wasted");
//            String secondary_user_id = obj8.optString("secondary_user_id");
//            String seconday_user_fullname = obj8.optString("seconday_user_fullname");
//            String slot_time = obj8.optString("slot_time");
//            String updated_at = obj8.optString("updated_at");
//            String uid = obj8.optString("uid");
            last_admin_mar_username = obj8.optString("user_fullname");
//            Log.e("user_fullname", "" + user_fullname);
//            String user_id = obj8.optString("user_id");

            Log.e("last_admin_mar_Dose", "--->" + last_admin_mar_Dose);
            Log.e("last_admin_mar_username", "--->" + last_admin_mar_username);


        } catch (Exception e) {
            e.printStackTrace();
        }


//        ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = (ArrayList<PRESCRIBED_TIME_SLOT>) args.getSerializable("PRES_TIME_SLOT_objects_list");
//        ArrayList<Today_Mars_Mar_Bean> Today_Mars_Mar_objects_list = (ArrayList<Today_Mars_Mar_Bean>) args.getSerializable("Today_Mars_Mar_objects_list");


        try {
            JSONArray array1 = new JSONArray(Prescribed_time_slots_array);
            for (int k = 0; k < array1.length(); k++) {
                try {
                    JSONObject obj2 = array1.optJSONObject(k);
//                    JSONObject obj3 = obj2.optJSONObject("time_slot_dose");

//                    String created_at = obj3.optString("created_at");
//                    String deleted_at = obj3.optString("deleted_at");

                    String time_slot_dose_id = obj2.optString("id");
                    String slot_time = obj2.optString("slot_time");
                    String show_as = obj2.optString("show_as");
                    String color = obj2.optString("color");
                    String Maindose = obj2.optString("dose");
                    String updated_at = obj2.optString("updated_at");

                    String prescription_id = obj2.optString("prescription_id");
                    Log.e("slot_time", updated_at + "  " + color + "  " + show_as);

                    PRESCRIBED_TIME_SLOT_phase2 pts_obj = new PRESCRIBED_TIME_SLOT_phase2(time_slot_dose_id, slot_time, show_as, color, Maindose, updated_at, prescription_id);

                    PRES_TIME_SLOT_objects_list.add(pts_obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String time = "";
        try {
            if (!last_admin_mar_created_Date.isEmpty()) {
                time = getTime(last_admin_mar_created_Date);
                Log.e("doseTime", "--->" + time);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        String date = "";
        try {
            if (!last_admin_mar_created_Date.isEmpty()) {
                date = getDate(last_admin_mar_created_Date);
                Log.e("doseDate", "--->" + date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        taken = "DOSE: " + last_admin_mar_Dose + System.getProperty("line.separator") + "DATE: "
                + date + System.getProperty("line.separator") + "TIME: " + time +
                System.getProperty("line.separator") + "USER: " + last_admin_mar_username;

        Log.e("position + taken", "" + position + "" + taken);
        View v = inflater.inflate(R.layout.dose_details, null);
        builder.setView(v);
        builder.setTitle(currentName);
        doseimage = (ImageView) v.findViewById(R.id.tabimage);
        instructionTxt = (TextView) v.findViewById(R.id.instruction);
        takeLast = (TextView) v.findViewById(R.id.lsttaken);
        mend_insTxt = (TextView) v.findViewById(R.id.mend_instruction);
        indicationTxt = (TextView) v.findViewById(R.id.indication);
//        doseTxt = (TextView) v.findViewById(R.id.dosenumber);
        dose = (TextView) v.findViewById(R.id.doseText);

        ListView lv_for_dose_Detail = (ListView) v.findViewById(R.id.lv_for_dose_Detail);

        Picasso.with(getContext()).load(imageUrl).into(doseimage);
        instructionTxt.setText(instruction);
        mend_insTxt.setText(mend_ins);
        indicationTxt.setText(indication);

        if (!last_admin_mar_Dose.isEmpty()) {
            takeLast.setText(taken);
        } else {
            takeLast.setText("Never");
        }
        if (indication.equals("")) {
            indicationTxt.setText("None");
        } else {
            indicationTxt.setText(indication);
        }


        if (PRES_TIME_SLOT_objects_list.size() > 0) {
            dose.setVisibility(View.VISIBLE);
        } else {
            dose.setVisibility(View.GONE);
        }

        Dose_Details_Adapter doseAdapter = new Dose_Details_Adapter(getActivity(), PRES_TIME_SLOT_objects_list);
        lv_for_dose_Detail.setAdapter(doseAdapter);

        builder.setNegativeButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setView(v);
        return builder.create();

    }

    public String getDate(String createdate) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createdate);
            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy");
            newDateStr = postFormater.format(date);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return newDateStr;
    }

    public String getTime(String createdate) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createdate);
            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
            newDateStr = postFormater.format(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return newDateStr;
    }
}
