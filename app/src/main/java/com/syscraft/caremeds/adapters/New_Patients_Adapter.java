package com.syscraft.caremeds.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.Patient_Model_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.database.CRMDS_Database_Handler;
//import com.syscraft.caremeds.database.DatabaseHandler;
import com.syscraft.caremeds.model.New_Patient_Model;
import com.syscraft.caremeds.model.PATIENTS_TIME_SLOT;
import com.syscraft.caremeds.model.PatientModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by anubhav on 2/16/2016.
 */
public class New_Patients_Adapter extends BaseAdapter {

    private int resource;
    //    private List<PatientModel> items;
    ArrayList<Patient_Model_Phase2> patient_search_List = null;
    //    List<PatientModel> patientList;
    ArrayList<Patient_Model_Phase2> new_patient_Model_List;
    Context context;
    String type;
    CRMDS_Database_Handler mydbobj;

    public New_Patients_Adapter(Context context, ArrayList<Patient_Model_Phase2> patientList, String state) {
        this.context = context;
        this.patient_search_List = patientList;
        this.new_patient_Model_List = new ArrayList<Patient_Model_Phase2>();
        this.new_patient_Model_List.addAll(patientList);
        this.type = state;
        mydbobj = new CRMDS_Database_Handler(context);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return patient_search_List.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator;
//        final String item = items.get(position).getName();
//        if (convertView == null) {
        inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflator.inflate(R.layout.patients_item, null);
//        }
        Patient_Model_Phase2 new_patient_model = patient_search_List.get(position);
        TextView txtPname = (TextView) convertView.findViewById(R.id.txtPname);
        txtPname.setText(new_patient_model.getFullname());
        TextView absent_line_txtvw = (TextView) convertView.findViewById(R.id.absent_line_txtvw);
        LinearLayout parent_color_linear_lout = (LinearLayout) convertView.findViewById(R.id.color_boxes_lout);

//        ArrayList<PATIENTS_TIME_SLOT> pts_list_22 = patient_search_List.get(position).getPts_list();

        ArrayList<PATIENTS_TIME_SLOT> pts_list_22 = get_color_box_list(patient_search_List.get(position).getPrescribed_time_slots_for_patient_list_array());

//        Log.e("PatientName", "--->" + patient_search_List.get(position).getPatientName());

        if (new_patient_model.getIs_absent().equalsIgnoreCase("true")) {

            absent_line_txtvw.setVisibility(View.VISIBLE);
            parent_color_linear_lout.setVisibility(View.GONE);
            String str = "Absent from: " + Date_utility.getDate(new_patient_model.getCurrent_absence_start()) + " " + Date_utility.getTime(new_patient_model.getCurrent_absence_start()) + " to " + Date_utility.getDate(new_patient_model.getCurrent_absence_end()) + " " + Date_utility.getTime(new_patient_model.getCurrent_absence_end()) + " (" + new_patient_model.getCurrent_absence_reason() + " )";
            Log.e("str", "str----->" + str);
            absent_line_txtvw.setText(str);

        } else {

            absent_line_txtvw.setVisibility(View.GONE);
            parent_color_linear_lout.setVisibility(View.VISIBLE);
        }

        // Layout inflater
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View color_row_view;


        for (int i = 0; i < pts_list_22.size(); i++) {

            LinearLayout.LayoutParams lparams;

            if (pts_list_22.get(i).getSlot_time().equals("PRN")) {

                // Add the text layout to the parent layout
                color_row_view = layoutInflater.inflate(R.layout.warf_dose_layout, parent_color_linear_lout, false);
                String Color_code = "#" + pts_list_22.get(i).getColor();
//                boolean given_today = pts_list_22.get(i).isAdmin_today();
                // In order to get the view we have to use the new view with text_layout in it
                LinearLayout slot_border_lout = (LinearLayout) color_row_view.findViewById(R.id.border_lout22);
                slot_border_lout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                TextView textView = (TextView) color_row_view.findViewById(R.id.dose_view_22);
                textView.setText("PRN");
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);

                // Add the text view to the parent layout
                parent_color_linear_lout.addView(color_row_view);


            } else if (pts_list_22.get(i).getSlot_time().equals("Warfarin")) {

                // Add the text layout to the parent layout
                color_row_view = layoutInflater.inflate(R.layout.warf_dose_layout, parent_color_linear_lout, false);
                String Color_code = "#" + pts_list_22.get(i).getColor();
                boolean given_today = pts_list_22.get(i).isAdmin_today();
                // In order to get the view we have to use the new view with text_layout in it
                LinearLayout slot_border_lout = (LinearLayout) color_row_view.findViewById(R.id.border_lout22);
                slot_border_lout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                TextView textView = (TextView) color_row_view.findViewById(R.id.dose_view_22);
                textView.setText("WARF");
                if (given_today) {
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);

                } else {
                    textView.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                }
                // Add the text view to the parent layout
                parent_color_linear_lout.addView(color_row_view);


            } else if (pts_list_22.get(i).getSlot_time().equals("Insulin")) {

                // Add the text layout to the parent layout
                color_row_view = layoutInflater.inflate(R.layout.warf_dose_layout, parent_color_linear_lout, false);
                String Color_code = "#" + pts_list_22.get(i).getColor();
//                boolean given_today = pts_list_22.get(i).isAdmin_today();
                // In order to get the view we have to use the new view with text_layout in it
                LinearLayout slot_border_lout = (LinearLayout) color_row_view.findViewById(R.id.border_lout22);
                slot_border_lout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                TextView textView = (TextView) color_row_view.findViewById(R.id.dose_view_22);
                textView.setText("INS");
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                // Add the text view to the parent layout
                parent_color_linear_lout.addView(color_row_view);

            } else {
                // Add the text layout to the parent layout
                color_row_view = layoutInflater.inflate(R.layout.dose_color_box_layout, parent_color_linear_lout, false);
                String Color_code = "#" + pts_list_22.get(i).getColor();

                boolean given_today = false;

                Log.e("type", patient_search_List.get(position).getId() + "type---->" + type);

                if (type.equals("online")) {

                    given_today = pts_list_22.get(i).isAdmin_today();

                } else if (type.equals("offline")) {

//                    if (patient_search_List.get(position).getId().equals("175")) {

                        given_today = mydbobj.get_admin_for_this_time_slot_and_patient(patient_search_List.get(position).getId(), pts_list_22.get(i).getSlot_time());
                        Log.e("bbbbbb", patient_search_List.get(position).getId() + "--> time_slot " + pts_list_22.get(i).getSlot_time() + " " + "given_today------->" + given_today);

//                    }


                }

                // In order to get the view we have to use the new view with text_layout in it
                LinearLayout slot_border_lout = (LinearLayout) color_row_view.findViewById(R.id.slot_border_lout);
                slot_border_lout.setBackgroundColor(Color.parseColor(Color_code));
                TextView textView = (TextView) color_row_view.findViewById(R.id.inner_dose_view);
                if (given_today) {
                    textView.setBackgroundColor(Color.parseColor("#000000"));
                } else {
                    textView.setBackgroundColor(Color.parseColor(Color_code));
                }
                // Add the text view to the parent layout
                parent_color_linear_lout.addView(color_row_view);
            }
        }


        return convertView;
    }

    private ArrayList<PATIENTS_TIME_SLOT> get_color_box_list(String prescribed_time_slots_for_patient_list_array) {

        ArrayList<PATIENTS_TIME_SLOT> p_timeslot_list = new ArrayList<PATIENTS_TIME_SLOT>();

        try {
            JSONArray jsonArray1 = new JSONArray(prescribed_time_slots_for_patient_list_array);
            for (int j = 0; j < jsonArray1.length(); j++) {
                JSONObject jsonObject3 = jsonArray1.optJSONObject(j);
                String slotTime = jsonObject3.optString("slot_time");
                String showAs = jsonObject3.optString("show_as");
                String color = jsonObject3.optString("color");
                boolean adminToday = jsonObject3.optBoolean("admin_today");

                PATIENTS_TIME_SLOT pt5 = new PATIENTS_TIME_SLOT(showAs, slotTime, color, adminToday);
                p_timeslot_list.add(pt5);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return p_timeslot_list;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        patient_search_List.clear();
        if (charText.length() == 0) {
            patient_search_List.addAll(new_patient_Model_List);
        } else {
            for (Patient_Model_Phase2 wp : new_patient_Model_List) {
                if (wp.getFullname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    patient_search_List.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}
