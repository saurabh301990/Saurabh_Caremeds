package com.syscraft.caremeds.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.Homely_Model_Phase2;
import com.syscraft.caremeds.New_Model.PRESCRIBED_TIME_SLOT_phase2;
import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;
import com.syscraft.caremeds.New_Model.Today_Mars_Mar_Bean_Phase2;
import com.syscraft.caremeds.PatientDetail;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.dialogs.Confirm_Admin_PRN_Dialog;
import com.syscraft.caremeds.dialogs.DoseDetailsDialog;
import com.syscraft.caremeds.dialogs.INR_Alert;
import com.syscraft.caremeds.dialogs.Patch_Dialog;
import com.syscraft.caremeds.dialogs.PrescribedQuantityDialog;
import com.syscraft.caremeds.dialogs.ReasonDialog;
import com.syscraft.caremeds.dialogs.Warfarine_Quantity_Dialog;
//import com.syscraft.caremeds.model.ADMIN_PRESCRIPTION_MODEL;
import com.syscraft.caremeds.model.NEW_ADMIN_MODEL;

//import com.syscraft.caremeds.model.PRESCRIPTION_COMMON_Model;

import com.syscraft.caremeds.model.Remedy_Dose_Bean;
//import com.syscraft.caremeds.model.Today_Mars_Mar_Bean;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class New_Admin_Adapter_By_Pankaj extends BaseAdapter {

    Context context;

    ArrayList<NEW_ADMIN_MODEL> new_admin_modelArrayList;
    ArrayList<Remedy_Dose_Bean> remedy_and_doselist;
    LayoutInflater mInflater;
    private ArrayList<Today_Mars_Mar_Bean_Phase2> today_mars_obj_list;
    String patient_id;

    public New_Admin_Adapter_By_Pankaj(Context context, ArrayList<NEW_ADMIN_MODEL> new_admin_modelArrayList, ArrayList<Remedy_Dose_Bean> remedy_and_doselist, String patientid) {
        this.context = context;
        this.new_admin_modelArrayList = new_admin_modelArrayList;
        this.remedy_and_doselist = remedy_and_doselist;
//        Log.e("patient_id", "---->" + patientid);
        this.patient_id = patientid;
    }

    @Override
    public int getCount() {
        return new_admin_modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//final int position=position1-1;
//        final AdminInterFace i = adminInterFace.get(position);

        final NEW_ADMIN_MODEL newAdminModel = new_admin_modelArrayList.get(position);

//        if (i != null) {
        if (newAdminModel.is_header()) {

            convertView = mInflater.inflate(R.layout.dose_headeritem, null);
            convertView.setOnClickListener(null);
            convertView.setOnLongClickListener(null);
            convertView.setLongClickable(false);

            TextView textHeader = (TextView) convertView.findViewById(R.id.header_textview);
            View viewChild = (View) convertView.findViewById(R.id.viewHeader);
            if (newAdminModel.getHeader_title().equals("Homely Remedies")) {
                textHeader.setTextColor(context.getResources().getColor(android.R.color.white));
                textHeader.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
            } else if (newAdminModel.getHeader_title().equals("PRN")) {
                textHeader.setTextColor(context.getResources().getColor(android.R.color.white));
                textHeader.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
            }
//            else if (newAdminModel.getHeader_title().equals("WARFARIN")) {
//                textHeader.setTextColor(context.getResources().getColor(android.R.color.white));
//                textHeader.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
//            }
            else {
                textHeader.setTextColor(context.getResources().getColor(android.R.color.black));
                textHeader.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
                viewChild.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
                viewChild.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
            }

            textHeader.setTag(position);
            textHeader.setText(newAdminModel.getHeader_title());
        } else {

            convertView = mInflater.inflate(R.layout.dose_childitem, null);
            View viewChild = (View) convertView.findViewById(R.id.viewChild);

            final PRESCRIPTION_Model_Phase2 ad_pres_model = newAdminModel.getAdmin_prescription_model();
            Homely_Model_Phase2 new_homely_remedy_model = newAdminModel.getNew_homely_remedy_model();
            if ("PRN".equals(newAdminModel.getType())) {
                viewChild.setBackgroundResource(R.color.black);
            } else {
//                Log.e("aaaaaa222", "--->" + newAdminModel.getHeader_background_color());
                viewChild.setBackgroundColor(Color.parseColor("#" + newAdminModel.getHeader_background_color()));
            }

            LinearLayout dose_detail_linearlout = (LinearLayout) convertView.findViewById(R.id.dose_detail_linearlout);
            LinearLayout llStock = (LinearLayout) convertView.findViewById(R.id.llStock);
            LinearLayout llAvail = (LinearLayout) convertView.findViewById(R.id.llAvail);
            LinearLayout llCancelDose = (LinearLayout) convertView.findViewById(R.id.llCancelDose);
            TextView txtblister = (TextView) convertView.findViewById(R.id.tvblister);
            TextView tabletName = (TextView) convertView.findViewById(R.id.tablet_type);
            TextView tabletIns = (TextView) convertView.findViewById(R.id.instruction_type);
            TextView detail = (TextView) convertView.findViewById(R.id.detail);
            //    detail.setVisibility(View.INVISIBLE);
            final TextView tDoseValue = (TextView) convertView.findViewById(R.id.txtDoseQuan);
            final TextView prescribed_DoseQuan = (TextView) convertView.findViewById(R.id.prescribed_DoseQuan);
            TextView time_value_textview = (TextView) convertView.findViewById(R.id.time_value_textview);

            TextView dose_heading_txt = (TextView) convertView.findViewById(R.id.dose_heading_txt);
            TextView dose_value_textview = (TextView) convertView.findViewById(R.id.dose_value_textview);
            TextView username_textview = (TextView) convertView.findViewById(R.id.username_textview);

            ImageView imvSelect = (ImageView) convertView.findViewById(R.id.imvSelect);
            ImageView imvUnSelect = (ImageView) convertView.findViewById(R.id.imvUnSelect);
//            Log.e("newAdminModel.getAction()", "---->" + newAdminModel.getAction());

            if (newAdminModel.getAction().equals("Giving")) {
//                Log.e("newAdminModel.getdose", "---->" + newAdminModel.getDose());
                imvSelect.setBackgroundResource(R.drawable.tick_on);
                imvUnSelect.setBackgroundResource(R.drawable.cross_off);
            } else if (newAdminModel.getAction().equals("NotGiving")) {

                imvSelect.setBackgroundResource(R.drawable.tick_off);
                imvUnSelect.setBackgroundResource(R.drawable.cross_on);

            } else {
                imvSelect.setBackgroundResource(R.drawable.tick_off);
                imvUnSelect.setBackgroundResource(R.drawable.cross_off);
            }
            dose_detail_linearlout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!"Homely Remedies".equals(newAdminModel.getType())) {

                        String value = ad_pres_model.getDrug_name();
                        String instruction = ad_pres_model.getInstructions();
                        String ins_mendatory = ad_pres_model.getMandatory_instructions();
                        String indication = ad_pres_model.getIndications();
                        String imageUrl = ad_pres_model.getFront_image_url();
//                        String fullNmae = null;
//                        try {
//                            fullNmae = ad_pres_model.getLast_admin_object().getUser_fullname();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = ad_pres_model.getPRES_TIME_SLOT_objects_list();
//                        ArrayList<Today_Mars_Mar_Bean> Today_Mars_Mar_objects_list = ad_pres_model.getToday_Mars_Mar_objects_list();
//                        LAST_ADMIN_MAR l_ad_mar = ad_pres_model.getLast_admin_object();

                        String lastadmin = ad_pres_model.getLast_admin();
                        String Prescribed_time_slots_array = ad_pres_model.getPrescribed_time_slots_array();

                        Bundle bld = new Bundle();
                        bld.putString("name", value);
                        bld.putString("instruction", instruction);
                        bld.putString("ins_mendatory", ins_mendatory);
                        bld.putString("indication", indication);
//                      bld.putString("show_as", show_as);
                        bld.putInt("position", position);
                        bld.putString("imageUrl", imageUrl);
                        bld.putString("lastadmin", lastadmin);
                        bld.putString("Prescribed_time_slots_array", Prescribed_time_slots_array);
//                        bld.putSerializable("PRES_TIME_SLOT_objects_list", PRES_TIME_SLOT_objects_list);
//                        bld.putSerializable("Today_Mars_Mar_objects_list", Today_Mars_Mar_objects_list);
//                        bld.putSerializable("last_admin_mar", l_ad_mar);
//                        bld.putString("fullNmae", fullNmae);
                        FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                        DoseDetailsDialog dialog = new DoseDetailsDialog();
                        dialog.setArguments(bld);
                        Fragment fr = fm.findFragmentByTag("admin");

                        if (fr == null) {
                            dialog.show(fm, "admin");
                        }
                    }
                }
            });


            imvSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if (date == 1) {
                    if (newAdminModel.getType().equals("PRN")) {
                        boolean is_valid_PRN = isvalidPRN(ad_pres_model);
                        Log.e("is_valid_PRN", "---->" + is_valid_PRN);
                        if (is_valid_PRN) {
                            do_admin(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel.getPrescribed_time_slot(), newAdminModel);
                        } else {

                            Dialog dialog = onCreateDialogSingleChoice22222(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel);
                            dialog.show();
                        }
                    }
//                    else if (newAdminModel.getType().equals("WARFARIN")) {
//
//                        do_admin(ad_pres_model, position, tDoseValue.getText().toString(), newAdminModel.getPrescribed_time_slot());
//
//                    }
                    else {

//                        Log.e("checkForCrossTimeSlots", "b123------->" + newAdminModel.getType());
                        boolean isCrossTimeSlot = checkForCrossTimeSlots(newAdminModel);
                        Log.e("checkForCrossTimeSlots", "b123------->" + isCrossTimeSlot);
//                        get_previous_time_slot(newAdminModel);
//                        get_last_admin_dateand_time(newAdminModel);

                        if (check_last_admin_is_oncorrect_time_or_not(newAdminModel)) {

//                          App_Constants.showAlertDialog("Alert", "This med was given later than the specified time previously, you may need to delay this administration", context, false);
                            Dialog dialog = onCreateDialogSingleChoice55555(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel);
                            dialog.show();


                        } else {

                            if (isCrossTimeSlot) {

                                Dialog dialog = onCreateDialogSingleChoice33333(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel);
                                dialog.show();

                            } else {
                                if (!isValidTime(newAdminModel, ad_pres_model)) {


                                    Dialog dialog = onCreateDialogSingleChoice(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel, "tick_click");
                                    dialog.show();

                                } else {

                                    do_admin(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel.getPrescribed_time_slot(), newAdminModel);

                                }
                            }
                        }

                    }

                }
            });


            imvUnSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (newAdminModel.getType().equals("PRN")) {
                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        ReasonDialog proceedDialog = new ReasonDialog();
                        Bundle bld = new Bundle();
                        bld.putString("name", ad_pres_model.getDrug_name());
                        bld.putString("isPrn", "true");
                        bld.putInt("position", position);
                        bld.putString("_slotDose", prescribed_DoseQuan.getText().toString());
                        bld.putString("pre_slot_time", newAdminModel.getPrescribed_time_slot());
                        bld.putStringArrayList("warninglist", newAdminModel.get_warning_overrides_list());
                        //  bld.putString("value", desc);
                        proceedDialog.setArguments(bld);

                        Fragment fr = fragmentManager.findFragmentByTag("Reason");

                        if (fr == null) {
                            proceedDialog.show(fragmentManager, "Reason");
                        }


                    } else {

                        if (!isValidTime(newAdminModel, ad_pres_model)) {

                            Dialog dialog = onCreateDialogSingleChoice(ad_pres_model, position, prescribed_DoseQuan.getText().toString(), newAdminModel, "cross_click");
                            dialog.show();

                        } else {

                            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                            ReasonDialog proceedDialog = new ReasonDialog();
                            Bundle bld = new Bundle();
                            bld.putString("name", ad_pres_model.getDrug_name());
                            bld.putString("isPrn", "false");
                            bld.putInt("position", position);
                            bld.putString("_slotDose", prescribed_DoseQuan.getText().toString());
                            bld.putString("pre_slot_time", newAdminModel.getPrescribed_time_slot());
                            bld.putStringArrayList("warninglist", newAdminModel.get_warning_overrides_list());
                            //  bld.putString("value", desc);
                            proceedDialog.setArguments(bld);
                            Fragment fr = fragmentManager.findFragmentByTag("Reason");

                            if (fr == null) {
                                proceedDialog.show(fragmentManager, "Reason");
                            }
                        }
                    }

                }
            });

            int available_value = 0;
            try {
                if ("" + ad_pres_model.getAvailable_quantity() != null) {
                    available_value = ad_pres_model.getAvailable_quantity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if ("Homely Remedies".equals(newAdminModel.getType())) {

                tabletName.setText(new_homely_remedy_model.getName());
                llStock.setVisibility(View.GONE);
                llAvail.setVisibility(View.GONE);
                llCancelDose.setVisibility(View.GONE);
                tabletIns.setVisibility(View.GONE);
                convertView.setOnClickListener(null);
                convertView.setOnLongClickListener(null);
                convertView.setLongClickable(false);

                txtblister.setVisibility(View.INVISIBLE);
                try {
                    String ddoss = remedy_and_doselist.get(position - 1).getDose();
                    detail.setText(remedy_and_doselist.get(position - 1).getDose());
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("Homely Remedies ddoss=", "" + ddoss);

                tDoseValue.setVisibility(View.INVISIBLE);

            } else if ("PRN".equals(newAdminModel.getType())) {

                tabletName.setText(ad_pres_model.getDrug_name());
                tabletIns.setText(ad_pres_model.getInstructions());

//                Log.e("PRN available_value", "--->" + available_value);

                if (available_value == 0) {
                    llStock.setVisibility(View.VISIBLE);
//                    llAvail.setVisibility(View.GONE);
//                    llStock.setVisibility(View.INVISIBLE);
//                    llCancelDose.setVisibility(View.INVISIBLE);
                } else if (available_value > 0) {
//                    llStock.setVisibility(View.INVISIBLE);
                    llAvail.setVisibility(View.VISIBLE);
//                    llCancelDose.setVisibility(View.INVISIBLE);
                } else if (available_value < 0) {
//                    llAvail.setVisibility(View.INVISIBLE);
//                    llCancelDose.setVisibility(View.INVISIBLE);
                    llStock.setVisibility(View.VISIBLE);
                }

                try {
                    ArrayList<Today_Mars_Mar_Bean_Phase2> Today_Mars_obj_list = ad_pres_model.getToday_Mars_Mar_objects_list();

                    Log.e("8888888888", "PRN Today_Mars_obj_list size--->" + Today_Mars_obj_list.size());
                    String ddoss = "";

                    for (int j = 0; j < Today_Mars_obj_list.size(); j++) {

                        Today_Mars_Mar_Bean_Phase2 tm_object = Today_Mars_obj_list.get(j);
                        String dose = tm_object.getDose();
                        String administered_at_mar = tm_object.getAdministered_at();
                        String user_fullname = tm_object.getFullname();
                        String false_reason = tm_object.getFalse_reason();
//                        Log.e("PRN dose", "--->" + dose);
//                        Log.e("PRN created_at_mar", "--->" + created_at_mar);
//                        Log.e("PRN user_fullname", "--->" + user_fullname);
//                        Log.e("PRN converted date", "--->" + Date_utility.getCurrentdate());

                        if (ddoss.isEmpty()) {
                            String a1 = "", secndry_user = "";
                            if (dose.isEmpty()) {
                                a1 = "REASON: " + false_reason + " ";
                            } else {
                                a1 = "DOSE: " + dose + " ";
                            }
                            if (ad_pres_model.getIs_controlled().equalsIgnoreCase("true")) {
                                secndry_user = " WITNESS: " + tm_object.getSecondry_fullname();
//                                secndry_user = " WITNESS: " + "";
                            }
                            ddoss = "" + a1 + "DATE: " +
                                    Date_utility.getDate(administered_at_mar) + " " + "TIME: " +
                                    Date_utility.getTime_in_24hour_format__222222(administered_at_mar) + " " + "USER: " + user_fullname + secndry_user;
                        } else {

                            String a1 = "", secndry_user = "";
                            if (dose.isEmpty()) {
                                a1 = "REASON: " + false_reason + " ";
                            } else {
                                a1 = "DOSE: " + dose + " ";
                            }
                            if (ad_pres_model.getIs_controlled().equalsIgnoreCase("true")) {
//                                secndry_user = " WITNESS: " + "";
                                secndry_user = " WITNESS: " + tm_object.getSecondry_fullname();
                            }


                            ddoss = ddoss + "\n" + a1 + "DATE: " +
                                    Date_utility.getDate(administered_at_mar) + " " + "TIME: " +
                                    Date_utility.getTime_in_24hour_format__222222(administered_at_mar) + " " + "USER: " + user_fullname + secndry_user;
                        }


                    }
//                    Log.e("PRN ddoss", "--->" + ddoss);
                    detail.setText(ddoss);
                    detail.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Log.e("tDoseValue", "PRN  $$$$--->" + newAdminModel.getDose());
                tDoseValue.setText(newAdminModel.getDose());


            } else {
                // for others

                tabletName.setText(ad_pres_model.getDrug_name());
                tabletIns.setText(ad_pres_model.getInstructions());

                if (available_value == 0) {
                    llStock.setVisibility(View.VISIBLE);
//                    llAvail.setVisibility(View.INVISIBLE);
//                    llCancelDose.setVisibility(View.INVISIBLE);
                } else if (available_value > 0) {
//                    llStock.setVisibility(View.INVISIBLE);
                    llAvail.setVisibility(View.VISIBLE);
//                    llCancelDose.setVisibility(View.INVISIBLE);

                } else if (available_value < 0) {
//                    llAvail.setVisibility(View.INVISIBLE);
//                    llCancelDose.setVisibility(View.INVISIBLE);
                    llStock.setVisibility(View.VISIBLE);
                }

                if ("true".equals(ad_pres_model.getNon_blistered())) {
                    txtblister.setVisibility(View.VISIBLE);
                } else {
                    txtblister.setVisibility(View.INVISIBLE);
                }
                boolean given_today = false;

                try {
                    ArrayList<Today_Mars_Mar_Bean_Phase2> Today_Mars_obj_list = ad_pres_model.getToday_Mars_Mar_objects_list();
                    String ddoss = "";
//                    Log.e("Admin_prescription_model()", "getDrug_name--->" + newAdminModel.getAdmin_prescription_model().getDrug_name() + "---->" + Today_Mars_obj_list.size());
                    // if  Today_Mars_obj_list size is grateer than 0 means we have to show dose for paticular time slot
                    if (Today_Mars_obj_list.size() > 0) {

//                        get_dose_for_today

                        for (int kkk = 0; kkk < Today_Mars_obj_list.size(); kkk++) {

                            Today_Mars_Mar_Bean_Phase2 tm_object = Today_Mars_obj_list.get(kkk);

//                            ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = ad_pres_model.getPRES_TIME_SLOT_objects_list();
                            ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list = getPRES_TIME_SLOT_objects_list_string_array(ad_pres_model.getPrescribed_time_slots_array(), ad_pres_model.getId());
                            for (int jj = 0; jj < PRES_TIME_SLOT_objects_list.size(); jj++) {
                                PRESCRIBED_TIME_SLOT_phase2 p1 = PRES_TIME_SLOT_objects_list.get(jj);

                                if (p1.getShow_as().equals(newAdminModel.getType())) {
//                                    Log.e("Current converted date", "@#@#@--->" + Date_utility.getCurrentdate());

                                    if (Date_utility.getCurrentdate().equals(Date_utility.getDate(tm_object.getAdministered_at()))) {
                                        Log.e("Current converted date", "@#@#@--->" + Date_utility.getCurrentdate());
                                        if (p1.getSlot_time().equals(tm_object.getSlot_time())) {
                                            given_today = true;

                                            String a1 = "", secndry_user = "";

                                            if (tm_object.getDose().isEmpty()) {
                                                a1 = "" + tm_object.getFalse_reason() + " ";
                                                dose_heading_txt.setText("REASON: ");
                                                detail.setText("** NOT TAKEN **");
                                                detail.setVisibility(View.VISIBLE);
                                                txtblister.setVisibility(View.INVISIBLE);

                                            } else {
                                                a1 = "" + tm_object.getDose() + " ";
                                                dose_heading_txt.setText("DOSE: ");
                                                detail.setText("");
                                                detail.setVisibility(View.INVISIBLE);
                                            }

                                            dose_value_textview.setText("" + a1);
                                            time_value_textview.setText(Date_utility.getTime_in_24hour_format__222222("" + tm_object.getAdministered_at()));

                                            if (ad_pres_model.getIs_controlled().equalsIgnoreCase("true")) {
//                                                secndry_user = "\nWITNESS: " + "";
                                                secndry_user = " WITNESS: " + tm_object.getSecondry_fullname();

                                            }

                                            username_textview.setText("USER: " + tm_object.getFullname() + secndry_user);
                                            llCancelDose.setVisibility(View.VISIBLE);
//                                            Log.e("secndry_user", "secndry_user other--->" + secndry_user);
                                        }
                                    }
//                                    Log.e("prescribed_DoseQuan", "$$$$--->" + prescribed_DoseQuan);
                                    prescribed_DoseQuan.setText(p1.getDose());
                                    tDoseValue.setText(p1.getDose());
                                }
                            }
                        }
                    } else {
                        // if  Today_Mars_obj_list size is 0 means we have to show priscription quantity and right and cross icon
//                        ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = ad_pres_model.getPRES_TIME_SLOT_objects_list();

                        ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list = getPRES_TIME_SLOT_objects_list_string_array(ad_pres_model.getPrescribed_time_slots_array(), ad_pres_model.getId());
                        for (int jj = 0; jj < PRES_TIME_SLOT_objects_list.size(); jj++) {
                            PRESCRIBED_TIME_SLOT_phase2 p1 = PRES_TIME_SLOT_objects_list.get(jj);

                            if (p1.getShow_as().equals(newAdminModel.getType())) {

//                                Log.e("tDoseValue", "$$$$--->" + p1.getDose());
                                prescribed_DoseQuan.setText(p1.getDose());
                                tDoseValue.setText(p1.getDose());
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (newAdminModel.getAction().equals("Giving")) {
//                    Log.e("newAdminModel dose", "newAdminModel222--->" + newAdminModel.getDose());
                    tDoseValue.setText(newAdminModel.getDose());
                } else if (newAdminModel.getAction().equals("NotGiving")) {
                    tDoseValue.setText("");
                }

//                Log.e("Admin_prescription_model().getDrug_name()", "getDrug_name--->" + newAdminModel.getAdmin_prescription_model().getDrug_name() + "---->" + given_today);
                if (given_today) {
                    llAvail.setVisibility(View.GONE);
                    tDoseValue.setVisibility(View.GONE);
                    llStock.setVisibility(View.INVISIBLE);
                } else {
                    if (llStock.getVisibility() == View.VISIBLE) {
                        llAvail.setVisibility(View.GONE);
                        tDoseValue.setVisibility(View.GONE);
                    } else {
                        llAvail.setVisibility(View.VISIBLE);
                        tDoseValue.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        return convertView;
    }

    private ArrayList<PRESCRIBED_TIME_SLOT_phase2> getPRES_TIME_SLOT_objects_list_string_array(String prescribed_time_slots_array, int pid) {
        ArrayList<PRESCRIBED_TIME_SLOT_phase2> admin_PRES_TIME_SLOT_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT_phase2>();
        try {
            JSONArray array1 = new JSONArray(prescribed_time_slots_array);

            for (int k = 0; k < array1.length(); k++) {
                JSONObject obj2 = array1.optJSONObject(k);
//                JSONObject obj3 = obj2.optJSONObject("time_slot_dose");
                String time_slot_dose_id = obj2.optString("id");
                String slot_time = obj2.optString("slot_time");
                String show_as = obj2.optString("show_as");

                String color = obj2.optString("color");
                String Maindose = obj2.optString("dose");
                String updated_at = obj2.optString("updated_at");

//                String created_at = obj3.optString("created_at");
//                String deleted_at = obj3.optString("deleted_at");
//                String prescription_id = obj3.optString("prescription_id");

                String prescription_id = "" + pid;
                Log.e("slot_time", updated_at + "  " + color + "  " + show_as);

                PRESCRIBED_TIME_SLOT_phase2 pts_obj = new PRESCRIBED_TIME_SLOT_phase2(time_slot_dose_id, slot_time, show_as, color, Maindose, updated_at, prescription_id);
                admin_PRES_TIME_SLOT_objects_list.add(pts_obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin_PRES_TIME_SLOT_objects_list;

    }

    private boolean check_last_admin_is_oncorrect_time_or_not(NEW_ADMIN_MODEL newAdminModel) {
        boolean bool = false;

        try {


            String lastadmindate = "";
            String lastadmin_slot_time = "";
            try {
                JSONObject jsonObject = new JSONObject(newAdminModel.getAdmin_prescription_model().getLast_admin());

                JSONObject jsonobj = jsonObject.optJSONObject("mar");
                lastadmindate = jsonobj.optString("administered_at");
//                        String last_admin_deleted_at = jsonobj.optString("deleted_at");
//                        String dose = jsonobj.optString("dose");
//                        String false_reason = jsonobj.optString("false_reason");
//                        String gps_location = jsonobj.optString("gps_location");
//                        String homely_remedy_id = jsonobj.optString("homely_remedy_id");
//                        String homely_remedy_name = jsonobj.optString("homely_remedy_name");
//                        String mar_id = jsonobj.optString("id");
//                        String is_waste = jsonobj.optString("is_waste");
//                        String medication_id = jsonobj.optString("medication_id");
//                        String outcome = jsonobj.optString("outcome");
//                        String patient_id = jsonobj.optString("patient_id");
//                        String prescription_id = jsonobj.optString("prescription_id");
//                        //  String show_as = jsonobj.optString("show_as");
//                        String quantity_wasted = jsonobj.optString("quantity_wasted");
//                        String secondary_user_id = jsonobj.optString("secondary_user_id");
//                        String seconday_user_fullname = jsonobj.optString("seconday_user_fullname");
                lastadmin_slot_time = jsonobj.optString("slot_time");
//                        String updated_at = jsonobj.optString("updated_at");
//                        String uid = jsonobj.optString("uid");
//                        String user_fullname = jsonobj.optString("user_fullname");
//                        Log.e("user_fullname", "" + user_fullname);
//                        String user_id = jsonobj.optString("user_id");
//
//                        lastadmin_obj = new LAST_ADMIN_MAR(last_admin_created_at, last_admin_deleted_at, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, mar_id, is_waste, medication_id, outcome, prescription_id, patient_id, quantity_wasted, secondary_user_id, seconday_user_fullname, updated_at, slot_time, uid, user_fullname, user_id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Date_utility.date_diffrence(lastadmindate)) {

                String pre_slot_time = get_previous_time_slot(newAdminModel);
                Log.e("pre_slot_time  ", "??? pre_slot_time--->" + pre_slot_time);
                String last_given_time = Date_utility.getTime_in_24hour_format(lastadmindate);
                last_given_time = last_given_time.substring(0, last_given_time.indexOf(" "));
                Log.e("last_given_time  ", "??? last_given_time--->" + last_given_time);
//                bool = Date_utility.isTimeBetweenTwoTime(pre_slot_time + ":00", newAdminModel.getPrescribed_time_slot() + ":00", last_given_time.trim());
//                bool = Date_utility.isTimeBetweenTwoTime(pre_slot_time + ":00", last_given_time.trim(), newAdminModel.getPrescribed_time_slot() + ":00");
//                long timediffrence = Date_utility.diffrence_between_giventime_and_pre_slot_time(pre_slot_time + ":00", newAdminModel.getPrescribed_time_slot() + ":00", last_given_time.trim());


//                String lastadmin_slot_time = newAdminModel.getAdmin_prescription_model().getLast_admin_object().getSlot_time();
                Log.e("lastadmin_slot_time  ", "??? lastadmin_slot_time--->" + lastadmin_slot_time);
                if (lastadmin_slot_time.equals(pre_slot_time)) {

                    long timediffrence = Date_utility.get_time_diffrence_2(pre_slot_time + ":00", last_given_time.trim());
                    String delay_time = PreferenceConnector.readString(context, PreferenceConnector.SETTING_ALLOWABLE_DELAY_MINUTES, "");
                    long dtime = Long.parseLong(delay_time);

                    Log.e("timediffrence  ", "timediffrence--->" + timediffrence);
                    Log.e("dtime  ", "dtime--->" + dtime);

                    if (timediffrence < dtime) {
                        return false;
                    } else {
                        return true;
                    }


                }
//                long timediffrence =Date_utility. get_time_diffrence(pre_slot_time + ":00", Date_utility.getTime("" + lastadmindate));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }


//    private void diffrence_between_giventime_and_pre_slot_time(String pre_slot_time, String last_given_time) {
//        Log.e("lastadmindate  ", "@@@@@@--->" + pre_slot_time);
//        Log.e("lastadmindate  ", "@@@@@@--->" + last_given_time);
//    }

    private String get_previous_time_slot(NEW_ADMIN_MODEL newAdminModel) {
        String prevous_slot_time = "";
        PRESCRIPTION_Model_Phase2 ad1111 = newAdminModel.getAdmin_prescription_model();

//        ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = ad1111.getPRES_TIME_SLOT_objects_list();
        ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list = getPRES_TIME_SLOT_objects_list_string_array(ad1111.getPrescribed_time_slots_array(), ad1111.getId());
        for (int jj = 0; jj < PRES_TIME_SLOT_objects_list.size(); jj++) {
            PRESCRIBED_TIME_SLOT_phase2 p1 = PRES_TIME_SLOT_objects_list.get(jj);

            if (p1.getShow_as().equals(newAdminModel.getType())) {
//                Log.e("Previous  ", "Previous---cur>" + newAdminModel.getPrescribed_time_slot());
                try {
//                    Log.e("Previous  ", "Previous---22222>" + PRES_TIME_SLOT_objects_list.get(jj - 1).getSlot_time());
                    prevous_slot_time = PRES_TIME_SLOT_objects_list.get(jj - 1).getSlot_time();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return prevous_slot_time;

    }

    private void do_admin(PRESCRIPTION_Model_Phase2 ad_pres_model, int position, String _slotDose, String p_slot_time, NEW_ADMIN_MODEL newAdminModel33) {

        Log.e("p_slot_time", "p_slot_time--->" + p_slot_time);
        Log.e("_slotDose", "_slotDose--->" + _slotDose);

        // clear doses of 'X'
        if (_slotDose.equals("x") || _slotDose.equals("X") || _slotDose.equals("*")) {
            _slotDose = "";
        }

        // display the admin dialog
        if (ad_pres_model.getDrug_name().contains("warfarin") || ad_pres_model.getDrug_name().contains("WARFARIN")) {

            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            Warfarine_Quantity_Dialog proceedDialog = new Warfarine_Quantity_Dialog();
            Bundle bld = new Bundle();
            bld.putString("name", ad_pres_model.getDrug_name());
            bld.putInt("position", position);
            bld.putString("tab", "admin");
            Log.e("warfarin patient_id", "---->" + patient_id);
            bld.putString("patientId", patient_id);
            bld.putString("_slotDose", _slotDose);
            bld.putString("pre_slot_time", p_slot_time);
            bld.putString("_inr_date", PatientDetail._global_inr_date);
            bld.putString("_warfarinDose", PatientDetail._global_warfarin_dose);
            bld.putString("_inr_reading", PatientDetail._global_inr_reading);
            bld.putStringArrayList("warninglist", newAdminModel33.get_warning_overrides_list());
            //  bld.putString("value", desc);
            proceedDialog.setArguments(bld);

            Fragment fr = fragmentManager.findFragmentByTag("Confirm");

            if (fr == null) {
                proceedDialog.show(fragmentManager, "Confirm");
            }


            boolean _inrReadingRequired = ((Date_utility.Check_inr_date_diffrence(PatientDetail._global_inr_date)) || (PatientDetail._global_inr_reading.isEmpty()) || (PatientDetail._global_warfarin_dose.isEmpty())) ? true : false;
            Log.e("Go---->", "Check_inr_date_diffrence--->" + _inrReadingRequired);

            if (_inrReadingRequired) {

                Log.e("Go---->", "1111111--->");
//                blank_alert("A new INR reading is required !");

                INR_Alert inrDialog = new INR_Alert();

                Fragment fr1 = fragmentManager.findFragmentByTag("inr");

                if (fr1 == null) {
                    inrDialog.show(fragmentManager, "inr");
                }

            }


        } else if (ad_pres_model.getIs_patch().equalsIgnoreCase("true")) {

            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            Patch_Dialog proceedDialog = new Patch_Dialog();
            Bundle bld = new Bundle();
            bld.putString("name", ad_pres_model.getDrug_name());
//            bld.putString("isPrn", "true");
            bld.putInt("position", position);
            bld.putString("_slotDose", _slotDose);
            bld.putString("pre_slot_time", p_slot_time);
            bld.putString("_patchLocation", ad_pres_model.getPatch_location());
            bld.putStringArrayList("warninglist", newAdminModel33.get_warning_overrides_list());
            proceedDialog.setArguments(bld);

            Fragment fr = fragmentManager.findFragmentByTag("Patch");

            if (fr == null) {
                proceedDialog.show(fragmentManager, "Patch");
            }


        } else if (ad_pres_model.getPrescription_type().indexOf("PRN") >= 0) {

            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//          ConfirmPrnDialog proceedDialog = new ConfirmPrnDialog();
            Confirm_Admin_PRN_Dialog proceedDialog = new Confirm_Admin_PRN_Dialog();
            Bundle bld = new Bundle();
            bld.putString("name", ad_pres_model.getDrug_name());
            bld.putInt("position", position);
            bld.putString("pre_slot_time", p_slot_time);
            bld.putString("tab", "admin");
            bld.putString("value", _slotDose);
            bld.putString("_patchLocation", ad_pres_model.getPatch_location());
            bld.putStringArrayList("warninglist", newAdminModel33.get_warning_overrides_list());
            proceedDialog.setArguments(bld);

            Fragment fr = fragmentManager.findFragmentByTag("Admin_PRN");

            if (fr == null) {
                proceedDialog.show(fragmentManager, "Admin_PRN");
            }


        } else {
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            PrescribedQuantityDialog proceedDialog = new PrescribedQuantityDialog();
            Bundle bld = new Bundle();
            bld.putString("name", ad_pres_model.getDrug_name());
            bld.putInt("position", position);
            bld.putString("pre_slot_time", p_slot_time);
            bld.putString("value", _slotDose);
            bld.putStringArrayList("warninglist", newAdminModel33.get_warning_overrides_list());
            proceedDialog.setArguments(bld);

            Fragment fr = fragmentManager.findFragmentByTag("Confirm1");

            if (fr == null) {
                proceedDialog.show(fragmentManager, "Confirm1");
            }

        }

    }

    private boolean checkForCrossTimeSlots(NEW_ADMIN_MODEL newAdminModel_66) {
        boolean ret = false;
        Log.e("Admin ", "Prescrition model id--->" + newAdminModel_66.getType());
        Log.e("Admin ", "Prescrition model Drug_name--->" + newAdminModel_66.getAdmin_prescription_model().getDrug_name());

        try {
            for (int i = 0; i < new_admin_modelArrayList.size(); i++) {

                NEW_ADMIN_MODEL nd1 = new_admin_modelArrayList.get(i);

                Log.e("No witness Loop for---->", "" + i + " new_admin_modelArrayList---->" + new_admin_modelArrayList.size());

                if (nd1.is_edited()) {

                    if (!newAdminModel_66.getType().equals("PRN")) {
                        if (!nd1.getType().equals("PRN")) {
                            Log.e("checkForCrossTimeSlots", "b123-----n1.getType-->" + nd1.getType());
                            if (!nd1.getType().equals(newAdminModel_66.getType())) {
                                ret = true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }


    private boolean isvalidPRN(PRESCRIPTION_Model_Phase2 ad_pres_model_11) {
        boolean ret = true;
        Log.e("Admin ", "Prescrition model id--->" + ad_pres_model_11.getId());
        Log.e("Admin ", "Prescrition model Drug_name--->" + ad_pres_model_11.getDrug_name());

        try {
            ArrayList<Today_Mars_Mar_Bean_Phase2> today_mars_obj_list = ad_pres_model_11.getToday_Mars_Mar_objects_list();
            String ddoss = "";

            for (int j = 0; j < today_mars_obj_list.size(); j++) {
                Today_Mars_Mar_Bean_Phase2 tm_object11 = today_mars_obj_list.get(j);
                int diff = getMeasurediff(tm_object11.getAdministered_at());
                // warn if PRN's are administered with 4 hours
                if (diff < 4) {
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }


    private boolean isValidTime(NEW_ADMIN_MODEL newAdminModel_11, PRESCRIPTION_Model_Phase2 ad_pres_model_11) {

        boolean b1 = true;

//        ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = ad_pres_model_11.getPRES_TIME_SLOT_objects_list();
        ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list = getPRES_TIME_SLOT_objects_list_string_array(ad_pres_model_11.getPrescribed_time_slots_array(), ad_pres_model_11.getId());
        for (int jj = 0; jj < PRES_TIME_SLOT_objects_list.size(); jj++) {
            PRESCRIBED_TIME_SLOT_phase2 p1 = PRES_TIME_SLOT_objects_list.get(jj);

            if (p1.getShow_as().equals(newAdminModel_11.getType())) {
                Log.e("kjkjhkjhkh", "$$$$--->" + p1.getSlot_time());

//                b1 = Date_utility.slot_time_diffrence(p1.getSlot_time());

                int now = (new Date().getHours() * 60) + new Date().getMinutes();
                int slotStart = (Integer.parseInt(p1.getSlot_time().substring(0, 2)) * 60) + Integer.parseInt(p1.getSlot_time().substring(3, 4));

                if ((slotStart - now) < 60) {
                    b1 = true;
//            System.out.println("\n" + "true");
                } else {
                    b1 = false;
//            System.out.println("\n" + "false");
                }


            }
        }
        return b1;
    }

    public int getMeasurediff(String dateString) {
        int compareToNow = 0;
        try {
            //DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy");
            // final String dateString = cursor.getString(4);            //
            // final String timeString = cursor.getString(5);
            DateFormat df = new SimpleDateFormat("HH:mm");
            String timeString = df.format(Calendar.getInstance().getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            //Date dbDate = sdf.parse(dateString + " " + timeString);

            Date inTime = sdf.parse(timeString);
            Date outTime = sdf.parse(dateString);

            compareToNow = inTime.compareTo(outTime);
            //DateFormat df = new SimpleDateFormat("HH:mm");
            // date = sdf.format(Calendar.getInstance().getTime());
            Log.e("compareToNow time ", " " + compareToNow);
        } catch (Exception e) {

        }
        return compareToNow;
    }

    public Dialog onCreateDialogSingleChoice(final PRESCRIPTION_Model_Phase2 admin_prescription, final int position, final String ddosse, final NEW_ADMIN_MODEL newAdminModel_55, final String on_click_of) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        final CharSequence[] array = {"Yes", "No"};
        builder.setTitle("This med is not due yet, Are you sure you want to proceed?").setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (array[which].equals("Yes")) {
                    newAdminModel_55._warning_overrides_list.add("This med is not due yet, Are you sure you want to proceed?");
                    if (on_click_of.equals("tick_click")) {
                        do_admin(admin_prescription, position, ddosse, newAdminModel_55.getPrescribed_time_slot(), newAdminModel_55);
                    } else if (on_click_of.equals("cross_click")) {

                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        ReasonDialog proceedDialog = new ReasonDialog();
                        Bundle bld = new Bundle();
                        bld.putString("name", admin_prescription.getDrug_name());
                        bld.putString("isPrn", "false");
                        bld.putInt("position", position);
                        bld.putStringArrayList("warninglist", newAdminModel_55.get_warning_overrides_list());
                        //  bld.putString("value", desc);
                        proceedDialog.setArguments(bld);

                        Fragment fr = fragmentManager.findFragmentByTag("Reason");

                        if (fr == null) {
                            proceedDialog.show(fragmentManager, "Reason");
                        }


                    }
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        });
        return builder.create();
    }


    public Dialog onCreateDialogSingleChoice55555(final PRESCRIPTION_Model_Phase2 admin_prescription, final int position, final String ddosse, final NEW_ADMIN_MODEL newAdminModel_22) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        TextView textView = new TextView(context);
        textView.setTextColor(Color.parseColor("#34B7E3"));
        textView.setPadding(10, 10, 10, 10);
        textView.setTextSize(15);
        textView.setText("This med was given later than the specified time previously, you may need to delay this administration, or you still wish to proceed?");
        builder.setCustomTitle(textView);
        final CharSequence[] array = {"Yes", "No"};
        builder
//                .setTitle("This med was given later than the specified time previously, you may need to delay this administration, or you still wish to proceed?")
                .setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (array[which].equals("Yes")) {
                            if (!newAdminModel_22._warning_overrides_list.contains("This med was given later than the specified time previously, you may need to delay this administration, or you still wish to proceed?")) {
                                newAdminModel_22._warning_overrides_list.add("This med was given later than the specified time previously, you may need to delay this administration, or you still wish to proceed?");
                            }
                            do_admin(admin_prescription, position, ddosse, newAdminModel_22.getPrescribed_time_slot(), newAdminModel_22);

                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                        }
                    }
                });
        return builder.create();
    }


    public Dialog onCreateDialogSingleChoice22222(final PRESCRIPTION_Model_Phase2 admin_prescription, final int position, final String ddosse, final NEW_ADMIN_MODEL newAdminModel_22) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        final CharSequence[] array = {"Yes", "No"};
        builder.setTitle("This med was given less than 4 hours ago, are you sure you wish to proceed?").setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (array[which].equals("Yes")) {
                    newAdminModel_22._warning_overrides_list.add("This med was given less than 4 hours ago, are you sure you wish to proceed?");
                    do_admin(admin_prescription, position, ddosse, newAdminModel_22.getPrescribed_time_slot(), newAdminModel_22);

                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        });
        return builder.create();
    }


    public Dialog onCreateDialogSingleChoice33333(final PRESCRIPTION_Model_Phase2 admin_prescription, final int position, final String ddosse, final NEW_ADMIN_MODEL newAdminModel_22) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        final CharSequence[] array = {"Yes", "No"};
        builder.setTitle("This Med is in a different round to others already selected, do you wish to proceed?").setSingleChoiceItems(array, 1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (array[which].equals("Yes")) {
                    newAdminModel_22._warning_overrides_list.add("This Med is in a different round to others already selected, do you wish to proceed?");
                    if (!isValidTime(newAdminModel_22, admin_prescription)) {
                        Dialog dialog33 = onCreateDialogSingleChoice(admin_prescription, position, ddosse, newAdminModel_22, "tick_click");
                        dialog33.show();

                    } else {
                        do_admin(admin_prescription, position, ddosse, newAdminModel_22.getPrescribed_time_slot(), newAdminModel_22);
                    }

//                    do_admin(admin_prescription, position, ddosse, newAdminModel_22.getPrescribed_time_slot());

                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        });
        return builder.create();
    }


//    public String getDate(String createdate) {
//        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
//        Date date = null;
//        String newDateStr = "";
//        try {
//            date = form.parse(createdate);
//            SimpleDateFormat postFormater = new SimpleDateFormat("dd:MM:yy");
//            newDateStr = postFormater.format(date);
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//        }
//        return newDateStr;
//    }
//
//    public String getTime(String createtime) {
//        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
//        Date date = null;
//        String newDateStr = "";
//        try {
//            date = form.parse(createtime);
//            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
//            newDateStr = postFormater.format(date);
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//        }
//        return newDateStr;
//    }


    /***************************************************************/

//    private void blank_alert(String myMessage) {
//
////        String myMessage = "A new INR reading is required !\n";
//
//        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);
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


}
