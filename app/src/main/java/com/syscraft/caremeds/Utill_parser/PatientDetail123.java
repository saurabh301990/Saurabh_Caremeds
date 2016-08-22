/*
package com.syscraft.caremeds.Utill_parser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.BaseActivity;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.adapters.MeasureAdapter;
import com.syscraft.caremeds.adapters.NewMedsAdapter;
import com.syscraft.caremeds.adapters.New_Admin_Adapter_By_Pankaj;
import com.syscraft.caremeds.adapters.New_CheckinAdapter;
import com.syscraft.caremeds.adapters.New_Homely_Adapter;
import com.syscraft.caremeds.adapters.New_Return_Adpater;
import com.syscraft.caremeds.adapters.NotesAdapter;
import com.syscraft.caremeds.constants.App_Constants;
import com.syscraft.caremeds.database.DatabaseHandler;
import com.syscraft.caremeds.dialogs.AddNoteDialog;
import com.syscraft.caremeds.dialogs.BMIDialog;
import com.syscraft.caremeds.dialogs.BPDiaDialog;
import com.syscraft.caremeds.dialogs.BPSysDialog;
import com.syscraft.caremeds.dialogs.BloodSugarDialog;
import com.syscraft.caremeds.dialogs.ConfirmPrnDialog;
import com.syscraft.caremeds.dialogs.ConfirmQuantityDialog;
import com.syscraft.caremeds.dialogs.Confirm_Admin_PRN_Dialog;
import com.syscraft.caremeds.dialogs.DoseDetailsDialog;
import com.syscraft.caremeds.dialogs.HeightDialog;
import com.syscraft.caremeds.dialogs.INRDialog;
import com.syscraft.caremeds.dialogs.LithiumDialog;
import com.syscraft.caremeds.dialogs.Patch_Dialog;
import com.syscraft.caremeds.dialogs.PrescribedQuantityDialog;
import com.syscraft.caremeds.dialogs.PulseDialog;
import com.syscraft.caremeds.dialogs.ReasonDialog;
import com.syscraft.caremeds.dialogs.ReturnDialog;
import com.syscraft.caremeds.dialogs.ShowHomelyRemedy;
import com.syscraft.caremeds.dialogs.ShowNoteDialog;
import com.syscraft.caremeds.dialogs.Warfarine_Quantity_Dialog;
import com.syscraft.caremeds.dialogs.WeightDialog;
import com.syscraft.caremeds.model.ADMIN_PRESCRIPTION_MODEL;
import com.syscraft.caremeds.model.BRANDED_MEDICATION;
import com.syscraft.caremeds.model.Homely_Today_Mars_Mar_Bean;
import com.syscraft.caremeds.model.LAST_ADMIN_MAR;
import com.syscraft.caremeds.model.MEDICATION;
import com.syscraft.caremeds.model.MeasureModel;
import com.syscraft.caremeds.model.NEW_ADMIN_MODEL;
import com.syscraft.caremeds.model.New_Homely_Remedy_Model;
import com.syscraft.caremeds.model.NotesModel;
import com.syscraft.caremeds.model.PRESCRIBED_TIME_SLOT;
import com.syscraft.caremeds.model.PRESCRIPTION_COMMON_Model;
import com.syscraft.caremeds.model.Remedy_Dose_Bean;
import com.syscraft.caremeds.model.TIME_SLOT_DOSES;
import com.syscraft.caremeds.model.Today_Mars_Mar_Bean;
import com.syscraft.caremeds.serverCommunication.NetworkAvailablity;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import com.syscraft.caremeds.database.CareMedsDatabase;
//import com.syscraft.caremeds.sharedPrefrns.SharedPrefrnceCareMeds;

//AdapterView.OnItemSelectedListener,

public class PatientDetail123 extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,
        AddNoteDialog.AddNoteListener, BloodSugarDialog.BloodSugarListener, BMIDialog.BMIListener, BPDiaDialog.BPDiaListener, BPSysDialog.BPSysListener,
        ConfirmQuantityDialog.ConfirmQuantityListener, DoseDetailsDialog.DoseDetailsListener, HeightDialog.HeightListener, INRDialog.INRListener,
        LithiumDialog.LithiumListener, PulseDialog.PulseListener, WeightDialog.WeightListener, ConfirmPrnDialog.ConfirmPrnListener, PrescribedQuantityDialog.PrescribedQuantityListener
        , ReasonDialog.ReasonListener, ReturnDialog.ReturnListner, Confirm_Admin_PRN_Dialog.ConfirmAdminPrnListener, Warfarine_Quantity_Dialog.Warfarine_QuantityListener, Patch_Dialog.Patch_Dialog_Listener {

    ArrayList<PRESCRIPTION_COMMON_Model> newchecklist;
    ArrayList<PRESCRIPTION_COMMON_Model> newmedsList;
    //    ArrayList<New_CheckIn_Model> newchecklist;
//    ArrayList<New_CheckIn_Model> newmedsList;
    ArrayList<ADMIN_PRESCRIPTION_MODEL> admin_prescription_modelArrayList;
    ArrayList<NEW_ADMIN_MODEL> new_admin_modelArrayList;
    ArrayList<String> headerlist, timedosenamelist;
    ArrayList<New_Homely_Remedy_Model> new_homely_List;
    ArrayList<Remedy_Dose_Bean> remedy_and_doselist;
    ArrayList<PRESCRIPTION_COMMON_Model> new_return_List;
    ArrayList<MeasureModel> measurearraylist;
    ArrayList<NotesModel> notesarraylist;
//    ArrayList<MeasureModel> BsList;
    ArrayList<NotesModel> bsSiteList;
    New_CheckinAdapter new_checkinAdapter;
    NewMedsAdapter newmedsAdapter;
    New_Admin_Adapter_By_Pankaj new_admin_adapter_by_pankaj;
    New_Homely_Adapter new_homely_Adapter;
    New_Return_Adpater new_return_Adpater;
    MeasureAdapter measureAdapter;
    NotesAdapter notesadapter;

    private ListView mylist;
    //    ProgressDialog progDialog;
    TextView tvcheck, tvmeds, txtAdmin, txtHome, txtReturn, tvmeasure, tvnotes;
    Spinner spCycle;
    ImageView imgPatient;
    List<String> spCategories = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    //    CareMedsDatabase db;
    DatabaseHandler mydbobj;
    LinearLayout llSave, llAddNote;
    Button btnSave;
    Button btnCancel;
    String prevouissite = "0";
    TextView txtPatientName, txtPatientDob, txtPatientNotes, txtPatientAllergy, txtPatientGp;
    String patientId, patientIs_absent, imc_met;
    String patient_img_name;
    int spnPosition = 0;
    int spnPosition1 = 1;
    public static String _global_inr_date, _global_warfarin_dose, _global_inr_reading;
    private String global_auth_token;
    private JSONParser jsonParser;
    Date_utility date_utility;
    App_Constants app_constants;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_patient_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Patient Detail");
        setSupportActionBar(toolbar);
//        db = new CareMedsDatabase(this);
        mydbobj = new DatabaseHandler(this);
        jsonParser = new JSONParser(PatientDetail123.this);
        date_utility = new Date_utility();
        app_constants = new App_Constants();


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        global_auth_token = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
//      progDialog = new ProgressDialog(this);
//      progDialog.setCancelable(false);
//      progDialog.setMessage("Please wait");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_ab_back_holo_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgPatient = (ImageView) findViewById(R.id.patient_pic);
        txtPatientName = (TextView) findViewById(R.id.txtPatientName);
        txtPatientDob = (TextView) findViewById(R.id.txtPatientDob);
        txtPatientNotes = (TextView) findViewById(R.id.txtPatientNotes);
        txtPatientAllergy = (TextView) findViewById(R.id.txtPatientAllergies);
        txtPatientGp = (TextView) findViewById(R.id.txtPatientGp);

        Intent i = getIntent();
        if (i.getStringExtra("name") != null && i.getStringExtra("dob") != null && i.getStringExtra("gp") != null
                && i.getStringExtra("allergies") != null && i.getStringExtra("notes") != null && i.getStringExtra("patientId") != null) {
            String name = i.getStringExtra("name");
            String dob = i.getStringExtra("dob");
            String gp = i.getStringExtra("gp");
            String allergies = i.getStringExtra("allergies");
            String notes = i.getStringExtra("notes");

            patient_img_name = i.getStringExtra("patientImagelink");
            // REmove below line when giving it to client
//            patient_img_name = "9ecd5b3b-3c5d-4589-84c2-af9c8001315c.jpg";
            txtPatientName.setText(name);
            txtPatientDob.setText(dob);
            txtPatientAllergy.setText(allergies);
            txtPatientGp.setText(gp);
            txtPatientNotes.setText(notes);
            patientIs_absent = i.getStringExtra("patientIs_absent");
            patientId = i.getStringExtra("patientId");
            Log.e("patientId", "---> " + patientId);

//          String PROFILE_PIC_URL = WebServiceDetails.PROFILE_PIC1 + patientId + "/image?" + patient_img_name + "=&authentication_token=" + SharedPrefrnceCareMeds.getSharedPrefData(PatientDetail.this, "auth_token") + "&disposition=inline";
            String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
            baseurl_value = baseurl_value + "/patients/";
//            String PROFILE_PIC_URL = WebServiceDetails.PROFILE_PIC1 + patientId + "/image?" + patient_img_name + "=&authentication_token=" + global_auth_token + "&disposition=inline";
            String PROFILE_PIC_URL = baseurl_value + patientId + "/image?" + patient_img_name + "=&authentication_token=" + global_auth_token + "&disposition=inline";
            Log.e("PROFILE_PIC_URL", "---> " + PROFILE_PIC_URL);
            Picasso.with(this).load(PROFILE_PIC_URL).into(imgPatient);

        }

        tvcheck = (TextView) findViewById(R.id.txtCheckIn);
        tvmeds = (TextView) findViewById(R.id.txtMeds);
        txtAdmin = (TextView) findViewById(R.id.txtAdmin);
        txtHome = (TextView) findViewById(R.id.txtHome);
        txtReturn = (TextView) findViewById(R.id.txtReturn);
        tvmeasure = (TextView) findViewById(R.id.txtMeasure);
        tvnotes = (TextView) findViewById(R.id.txtNotes);

        spCycle = (Spinner) findViewById(R.id.spCycle);
        spCycle.setOnItemSelectedListener(this);
        spCycle.setOnTouchListener(Spinner_OnTouch);

        llSave = (LinearLayout) findViewById(R.id.llSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        llAddNote = (LinearLayout) findViewById(R.id.llAddNote);

        Disable_save_and_cancel_button();

        llSave.setVisibility(View.VISIBLE);
        llAddNote.setVisibility(View.INVISIBLE);

        mylist = (ListView) findViewById(R.id.lvDetail);
//        newchecklist = new ArrayList<New_CheckIn_Model>();
        newchecklist = new ArrayList<PRESCRIPTION_COMMON_Model>();
        admin_prescription_modelArrayList = new ArrayList<ADMIN_PRESCRIPTION_MODEL>();
        timedosenamelist = new ArrayList<String>();
        newmedsList = new ArrayList<PRESCRIPTION_COMMON_Model>();
        measurearraylist = new ArrayList<MeasureModel>();
        notesarraylist = new ArrayList<NotesModel>();
        new_homely_List = new ArrayList<New_Homely_Remedy_Model>();
        remedy_and_doselist = new ArrayList<Remedy_Dose_Bean>();
        new_return_List = new ArrayList<PRESCRIPTION_COMMON_Model>();
        bsSiteList = new ArrayList<NotesModel>();
//        BsList = new ArrayList<MeasureModel>();

        tvcheck.setOnClickListener(this);
        tvmeds.setOnClickListener(this);
        txtAdmin.setOnClickListener(this);
        txtHome.setOnClickListener(this);
        txtReturn.setOnClickListener(this);
        tvmeasure.setOnClickListener(this);
        tvnotes.setOnClickListener(this);
        llSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        llAddNote.setOnClickListener(this);

        Enable_Admin_tab();

        spCycle.setVisibility(View.GONE);

        getAdminData();

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.e("on item click listener ", "click " + position);
                if (txtAdmin.isSelected()) {

                }
                if (tvmeds.isSelected()) {
                    if (newmedsList.size() > 0) {
                        String value = newmedsList.get(position).getDrug_name();
                        String imageUrl = newmedsList.get(position).getFront_image_url();
                        String instruction = newmedsList.get(position).getInstructions();
                        String ins_mendatory = newmedsList.get(position).getMandatory_instructions();
                        String indication = newmedsList.get(position).getIndications();


//                        String fullNmaeMar = null;
//                        try {
//                            fullNmaeMar = newmedsList.get(position).getLast_admin_object().getUser_fullname();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                        String lastadmin = newmedsList.get(position).getLast_admin();
                        String Prescribed_time_slots_array = newmedsList.get(position).getPrescribed_time_slots_array();

                        Bundle bld = new Bundle();
                        bld.putString("name", value);
                        bld.putString("instruction", instruction);
                        bld.putString("ins_mendatory", ins_mendatory);
                        bld.putString("indication", indication);
                        bld.putString("imageUrl", imageUrl);
//                        bld.putSerializable("PRES_TIME_SLOT_objects_list", newmedsList.get(position).getPRES_TIME_SLOT_objects_list());
//                        bld.putSerializable("Today_Mars_Mar_objects_list", newmedsList.get(position).getToday_Mars_Mar_objects_list());
//                        bld.putString("fullNmae", fullNmaeMar);
                        bld.putString("lastadmin", lastadmin);
                        bld.putString("Prescribed_time_slots_array", Prescribed_time_slots_array);
                        DoseDetailsDialog dialog = new DoseDetailsDialog();
                        dialog.setArguments(bld);

                        Fragment fr = getSupportFragmentManager().findFragmentByTag("Meds");

                        if (fr == null) {
                            dialog.show(getSupportFragmentManager(), "Meds");
                        }

                    }

                }
                if (tvmeasure.isSelected()) {
                    if (measurearraylist.size() > 0) {
                        openMeasureDialog(position, measurearraylist.get(position).getName());
                    }
                }
                if (tvnotes.isSelected()) {
                    if (notesarraylist.size() > 0) {
                        String sub = notesarraylist.get(position).getSubject();
                        String detail = notesarraylist.get(position).getContent();
                        Bundle bld = new Bundle();
                        bld.putString("sub", sub);
                        bld.putString("detail", detail);
                        ShowNoteDialog notesDialog = new ShowNoteDialog();
                        notesDialog.setArguments(bld);
                        Fragment fr = getSupportFragmentManager().findFragmentByTag("Notes");

                        if (fr == null) {
                            notesDialog.show(getSupportFragmentManager(), "Notes");
                        }

                    }
                }
                if (txtHome.isSelected()) {
                    if (new_homely_List.size() > 0) {
                        String name = new_homely_List.get(position).getName();
                        String mwarning = new_homely_List.get(position).getMandatory_warnings();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("mwarning", mwarning);
                        ShowHomelyRemedy homelyRemedy = new ShowHomelyRemedy();
                        homelyRemedy.setArguments(bundle);
                        Fragment fr = getSupportFragmentManager().findFragmentByTag("Homely Remedy");

                        if (fr == null) {
                            homelyRemedy.show(getSupportFragmentManager(), "Homely Remedy");
                        }

                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        global_auth_token = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
    }

    private void getCheckInData() {
        String cycle = spCycle.getSelectedItem().toString();
        Log.e("Cycle", "" + cycle);
        if ("This Cycle".equals(cycle)) {
            imc_met = "this_cycle";
            Log.e("NetworkAvailablity", "NetworkAvailablity------->" + NetworkAvailablity.chkStatus(PatientDetail123.this));
            if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                new getCheckin().execute(imc_met);
            } else {
                Log.e("NetworkAvailablity", "offline------->");
                newchecklist.clear();
                newchecklist = mydbobj.getallFeedsFromDB(patientId, imc_met);
                new_checkinAdapter = new New_CheckinAdapter(PatientDetail123.this, newchecklist);
                mylist.setAdapter(new_checkinAdapter);

//                App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
            }

        }
    }

    private void getAdminData() {
        if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
            if (!patientIs_absent.equalsIgnoreCase("true")) {
                new getHomelyRemedyData("1").execute();
//            new getAdminPresData().execute();
                new getPatientsRecord_Data().execute();
            }
        } else {
            App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail123.this, false);
        }
    }

    private void getMeds() {
        String cycle = spCycle.getSelectedItem().toString();
        Log.e("Cycle", "" + cycle);
        if ("This Cycle".equals(cycle)) {
            imc_met = "this_cycle";
            if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                new getMedsData().execute(imc_met);
            } else {


                newmedsList.clear();
                newmedsList = mydbobj.getallFeedsFromDB(patientId, imc_met);
                newmedsAdapter = new NewMedsAdapter(PatientDetail123.this, newmedsList);
                mylist.setAdapter(newmedsAdapter);

//                App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
            }
        }
    }

    private void getMeasure() {
        if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
            if (!patientIs_absent.equalsIgnoreCase("true")) {
                new getMeasurementData().execute();
                new getBSSiteData().execute();
            }
        } else {

            bsSiteList.clear();
            bsSiteList= mydbobj.getBS_Site_Notesfromdb(patientId);
            measurearraylist.clear();
            measurearraylist = mydbobj.getMeasureListfromdb(patientId);
            measureAdapter = new MeasureAdapter(PatientDetail123.this, measurearraylist);
            mylist.setAdapter(measureAdapter);
//            App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
        }

    }

    private void getNotes() {
        if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
            if (!patientIs_absent.equalsIgnoreCase("true")) {
                new getNotesData().execute();
            }
        } else {

            mydbobj.getGeneral_Notesfromdb(patientId);
            notesadapter = new NotesAdapter(PatientDetail123.this, notesarraylist);
            mylist.setAdapter(notesadapter);
//            App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
        }
    }

    private void getHomelyData() {
        if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
            if (!patientIs_absent.equalsIgnoreCase("true")) {
                new getHomelyRemedyData("0").execute();
            }
        } else {
            App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail123.this, false);
        }

    }

    private void getReturnData() {
        if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
            if (!patientIs_absent.equalsIgnoreCase("true")) {
                new getReturnTabData().execute();
            }
        } else {
            App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail123.this, false);
        }
    }


    private void openMeasureDialog(int position, String measuretype) {
        if ("BMI".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            BMIDialog md = new BMIDialog();
            md.setArguments(bld);
            Fragment fr = getSupportFragmentManager().findFragmentByTag("BMI");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "BMI");
            }

        } else if ("Pulse".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            PulseDialog md = new PulseDialog();
            md.setArguments(bld);

            Fragment fr = getSupportFragmentManager().findFragmentByTag("Pulse");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "Pulse");
            }

        } else if ("BP  Systolic".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            BPSysDialog md = new BPSysDialog();
            md.setArguments(bld);
            Fragment fr = getSupportFragmentManager().findFragmentByTag("BPSystolic");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "BPSystolic");
            }

        } else if ("BP Diastolic".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            BPDiaDialog md = new BPDiaDialog();
            md.setArguments(bld);
            Fragment fr = getSupportFragmentManager().findFragmentByTag("BPDiastolic");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "BPDiastolic");
            }

        } else if ("Weight".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            WeightDialog md = new WeightDialog();
            md.setArguments(bld);

            Fragment fr = getSupportFragmentManager().findFragmentByTag("Weight");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "Weight");
            }


        } else if ("INR".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            INRDialog md = new INRDialog();
            md.setArguments(bld);

            Fragment fr = getSupportFragmentManager().findFragmentByTag("INR");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "INR");
            }

        } else if ("Height".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            HeightDialog md = new HeightDialog();
            md.setArguments(bld);

            Fragment fr = getSupportFragmentManager().findFragmentByTag("Height");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "Height");
            }


        } else if ("Blood Sugar".equals(measuretype)) {
            Log.e("position=", "Blood Sugar position-->" + position);

            try {
                prevouissite = bsSiteList.get(position).getContent();
                Log.e("prevouissiteDEat=", "-->" + prevouissite);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bundle bld = new Bundle();
            bld.putInt("position", position);
            bld.putString("prevouissite", prevouissite);
            BloodSugarDialog md = new BloodSugarDialog();
            md.setArguments(bld);

            Fragment fr = getSupportFragmentManager().findFragmentByTag("BloodSugar");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "BloodSugar");
            }


        } else if ("Lithium".equals(measuretype)) {
            Bundle bld = new Bundle();
            bld.putInt("position", position);
            LithiumDialog md = new LithiumDialog();
            md.setArguments(bld);

            Fragment fr = getSupportFragmentManager().findFragmentByTag("Lithium");
            if (fr == null) {
                md.show(getSupportFragmentManager(), "Lithium");
            }


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtCheckIn:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {
                    Enable_Checkin_Tab();
                    llSave.setVisibility(View.VISIBLE);
                    llAddNote.setVisibility(View.INVISIBLE);
                    spCycle.setVisibility(View.VISIBLE);
                    // doselist.setVisibility(View.GONE);
                    mylist.setVisibility(View.VISIBLE);
                    spCategories.clear();
                    spCategories.add("This Cycle");
                    spCategories.add("Next Cycle");
                    // Creating adapter for spinner
                    //dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spCategories);
                    dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spCategories);
                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(R.layout.spinner_item);
                    // attaching data adapter to spinner
                    spCycle.setAdapter(dataAdapter);
                    spCycle.setSelection(spnPosition);
                    mylist.setAdapter(null);
                    getCheckInData();
                }
                break;
            case R.id.txtMeds:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {
//                    if (NetworkAvailablity.chkStatus(PatientDetail.this)) {
//                        new getMedicationsData().execute();
//                    } else {
//                        App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
//                    }

                    Enable_Meds_Tab();

                    spCycle.setVisibility(View.VISIBLE);
                    llSave.setVisibility(View.GONE);
                    llAddNote.setVisibility(View.GONE);
                    mylist.setVisibility(View.VISIBLE);
                    spCategories.clear();
                    spCategories.add("Last Cycle");
                    spCategories.add("This Cycle");
                    spCategories.add("Next Cycle");
                    // Creating adapter for spinner
                    dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spCategories);
                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(R.layout.spinner_item);
                    // attaching data adapter to spinner
                    spCycle.setAdapter(dataAdapter);
                    spCycle.setSelection(spnPosition1);
                    mylist.setAdapter(null);
                    getMeds();
                }
                break;
            case R.id.txtAdmin:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {

                    Enable_Admin_tab();

                    llSave.setVisibility(View.VISIBLE);
                    llAddNote.setVisibility(View.INVISIBLE);
                    mylist.setVisibility(View.VISIBLE);
                    spCycle.setVisibility(View.GONE);
                    mylist.setAdapter(null);
                    getAdminData();
//                    getpatient_detail_data();
                    //   getHomelyData();
                }
                break;
            case R.id.txtHome:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {

                    Enable_Homely_tab();
                    llSave.setVisibility(View.VISIBLE);
                    llAddNote.setVisibility(View.INVISIBLE);
                    spCycle.setVisibility(View.GONE);
                    mylist.setAdapter(null);
                    getHomelyData();
                }
                break;
            case R.id.txtReturn:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {

                    Enable_Return_Tab();

                    llSave.setVisibility(View.VISIBLE);
                    llAddNote.setVisibility(View.INVISIBLE);
//                    mylist.setVisibility(View.GONE);
                    spCycle.setVisibility(View.GONE);
                    mylist.setAdapter(null);
                    getReturnData();
                }
                break;
            case R.id.txtMeasure:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {

                    Enable_Measure_tab();

                    llSave.setVisibility(View.VISIBLE);
                    llAddNote.setVisibility(View.INVISIBLE);
                    mylist.setVisibility(View.VISIBLE);
                    spCycle.setVisibility(View.GONE);
                    mylist.setAdapter(null);
                    getMeasure();
                }
                break;
            case R.id.txtNotes:
                if (btnSave.isEnabled()) {
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                } else {

                    Enable_Notes_Tab();

                    llSave.setVisibility(View.INVISIBLE);
                    llAddNote.setVisibility(View.VISIBLE);
                    mylist.setVisibility(View.VISIBLE);
                    spCycle.setVisibility(View.GONE);
                    mylist.setAdapter(null);
                    getNotes();
                }
                break;
            case R.id.llAddNote:
                AddNoteDialog addDialog = new AddNoteDialog();
                addDialog.setCancelable(false);


                Fragment fr = getSupportFragmentManager().findFragmentByTag("AddNote");

                if (fr == null) {
                    addDialog.show(getSupportFragmentManager(), "AddNote");
                }


                break;
            case R.id.btnSave:
                Make_all_tabs_clickclable();
                Disable_save_and_cancel_button();

                if (tvcheck.isSelected()) {
                    String require_witness_on_checkin = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_WITNESS_CHECKIN, "false");
                    if (require_witness_on_checkin.equalsIgnoreCase("true")) {
                        open_witness_dialog("checkin");
                    } else {
                        for (int i = 0; i < newchecklist.size(); i++) {
                            PRESCRIPTION_COMMON_Model n1 = newchecklist.get(i);
                            if (n1.isSelected().equalsIgnoreCase("true")) {
                                Log.e("selected id", "---->" + n1.getId());
                                new saveCheckInData().execute("" + n1.getId(), "" + n1.getNew_checked_in_quantity_toupdate(), "0");
                            }
                        }
                    }

                } else if (txtAdmin.isSelected()) {

                    boolean _IS_SECONDRY_USER_AUTH_REQUIRED = _is_witness_required_on_admin_save_button();

                    if (_IS_SECONDRY_USER_AUTH_REQUIRED) {
                        open_witness_dialog("Admin");
                    } else {

                        for (int i = 0; i < new_admin_modelArrayList.size(); i++) {
                            NEW_ADMIN_MODEL nd1 = new_admin_modelArrayList.get(i);
                            Log.e("No witness Loop for---->", "" + i + " new_admin_modelArrayList---->" + new_admin_modelArrayList.size());
                            if (nd1.is_edited()) {
                                new saveAdminData(nd1).execute("0");
                            }
                            if (i == (new_admin_modelArrayList.size() - 1)) {
                                getAdminData();
                            }
                        }

                    }


                } else if (txtHome.isSelected()) {

                    for (int i = 0; i < new_homely_List.size(); i++) {
                        New_Homely_Remedy_Model h1 = new_homely_List.get(i);
                        if (h1.is_edited()) {
                            Log.e("is_edited id", "---->" + h1.getId());
                            new saveHomelyRemedytData().execute(h1.getId(), h1.getDose_to_update());
                        }
                    }

                } else if (txtReturn.isSelected()) {

                    for (int i = 0; i < new_return_List.size(); i++) {
                        PRESCRIPTION_COMMON_Model nr1 = new_return_List.get(i);
                        if (nr1.isSelected_inreturn().equalsIgnoreCase("true")) {
                            Log.e("selected id", "---->" + nr1.getId());
                            Log.e(" Position", i + " --->" + nr1.getAction_applied());
                            if ("Return".equals(nr1.getAction_applied())) {
                                Log.e(" case", " --->111111111");
                                String url_end = "return";
                                String qnty_parameter_name = "return";
                                new saveReturnData().execute(""+nr1.getId(), "" + nr1.getQuantity_to_update_on_server(), url_end, qnty_parameter_name);

                            } else if ("Carry Forward".equals(nr1.getAction_applied())) {
                                Log.e(" case", " --->22222222");
                                String url_end = "carry_forward";
                                String qnty_parameter_name = "carry_forward";
                                new saveReturnData().execute(""+nr1.getId(), "" + nr1.getQuantity_to_update_on_server(), url_end, qnty_parameter_name);

                            } else if ("Destroyed".equals(nr1.getAction_applied())) {
                                Log.e(" case", " --->3333333");
                                String url_end = "destroy_meds";
                                String qnty_parameter_name = "destroy";
                                new saveReturnData().execute(""+nr1.getId(), "" + nr1.getQuantity_to_update_on_server(), url_end, qnty_parameter_name);

                            }

                            if ("Return".equals(nr1.getWaste_Action_applied())) {
                                Log.e(" case", " --->4444444");
                                String url_end = "return";
                                String qnty_parameter_name = "return";
                                new saveReturnData().execute(""+nr1.getId(), "" + nr1.getWaste_qty_to_update_on_server(), url_end, qnty_parameter_name);

                            } else if ("Destroyed".equals(nr1.getWaste_Action_applied())) {
                                Log.e(" case", " --->5555555");
                                String url_end = "destroy_meds";
                                String qnty_parameter_name = "destroy";
                                new saveReturnData().execute(""+nr1.getId(), "" + nr1.getWaste_qty_to_update_on_server(), url_end, qnty_parameter_name);
                            }
                        }
                    }


                } else if (tvmeasure.isSelected()) {

                    for (int i = 0; i < measurearraylist.size(); i++) {
                        MeasureModel m1 = measurearraylist.get(i);
                        if ("BMI".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("Pulse".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("BP  Systolic".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("BP Diastolic".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("Weight".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("INR".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("Height".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        } else if ("Blood Sugar".equals(m1.getName())) {
                            new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            if (!new_bs_site_value.isEmpty()) {
                                new Update_sugar_measurement_Async().execute(new_bs_site_value);
                            }
                        } else if ("Lithium".equals(m1.getName())) {
                            if (m1.isedited().equalsIgnoreCase("true")) {
                                new saveMeasureData().execute(m1.getName(), "" + m1.getValue());
                            }
                        }
                    }
                }
                break;

            case R.id.btnCancel:
                Make_all_tabs_clickclable();
                Disable_save_and_cancel_button();
                if (tvcheck.isSelected()) {
                    getCheckInData();
                } else if (txtAdmin.isSelected()) {
                    getAdminData();
                } else if (txtHome.isSelected()) {
                    getHomelyData();
                } else if (txtReturn.isSelected()) {
                    getReturnData();
                } else if (tvmeasure.isSelected()) {
                    getMeasure();
                }
                break;
        }
    }

    private boolean _is_witness_required_on_admin_save_button() {

        boolean bool = false;
        for (int i = 0; i < new_admin_modelArrayList.size(); i++) {
            NEW_ADMIN_MODEL nd1 = new_admin_modelArrayList.get(i);
            if (nd1.is_edited()) {
                Log.e("Loop for" + i, "getPrecription_ID---->" + nd1.getPrecription_ID());
                if (nd1.getAdmin_prescription_model().getIs_controlled().equalsIgnoreCase("true")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return bool;
    }

    private void Disable_save_and_cancel_button() {
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
    }


    public void Enable_save_and_cancel_button() {
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }

    private void Make_all_tabs_clickclable() {
        tvcheck.setClickable(true);
        tvmeds.setClickable(true);
        txtAdmin.setClickable(true);
        txtHome.setClickable(true);
        txtReturn.setClickable(true);
        tvmeasure.setClickable(true);
        tvnotes.setClickable(true);
    }

    private void Enable_Notes_Tab() {
        tvcheck.setSelected(false);
        tvmeds.setSelected(false);
        txtAdmin.setSelected(false);
        txtHome.setSelected(false);
        txtReturn.setSelected(false);
        tvmeasure.setSelected(false);
        tvnotes.setSelected(true);
    }

    private void Enable_Measure_tab() {
        tvcheck.setSelected(false);
        tvmeds.setSelected(false);
        txtAdmin.setSelected(false);
        txtHome.setSelected(false);
        txtReturn.setSelected(false);
        tvmeasure.setSelected(true);
        tvnotes.setSelected(false);
    }

    private void Enable_Return_Tab() {
        tvcheck.setSelected(false);
        tvmeds.setSelected(false);
        txtAdmin.setSelected(false);
        txtHome.setSelected(false);
        txtReturn.setSelected(true);
        tvmeasure.setSelected(false);
        tvnotes.setSelected(false);
    }

    private void Enable_Homely_tab() {
        tvcheck.setSelected(false);
        tvmeds.setSelected(false);
        txtAdmin.setSelected(false);
        txtHome.setSelected(true);
        txtReturn.setSelected(false);
        tvmeasure.setSelected(false);
        tvnotes.setSelected(false);
    }


    private void Enable_Checkin_Tab() {
        tvcheck.setSelected(true);
        tvmeds.setSelected(false);
        txtAdmin.setSelected(false);
        txtHome.setSelected(false);
        txtReturn.setSelected(false);
        tvmeasure.setSelected(false);
        tvnotes.setSelected(false);
    }

    private void Enable_Meds_Tab() {
        tvcheck.setSelected(false);
        tvmeds.setSelected(true);
        txtAdmin.setSelected(false);
        txtHome.setSelected(false);
        txtReturn.setSelected(false);
        tvmeasure.setSelected(false);
        tvnotes.setSelected(false);
    }

    private void Enable_Admin_tab() {
        tvcheck.setSelected(false);
        tvmeds.setSelected(false);
        txtAdmin.setSelected(true);
        txtHome.setSelected(false);
        txtReturn.setSelected(false);
        tvmeasure.setSelected(false);
        tvnotes.setSelected(false);
    }


    private void open_witness_dialog(String from) {
        FragmentManager fragmentManager = ((FragmentActivity) PatientDetail123.this).getSupportFragmentManager();
        Witness_Dialog witnessDialog = new Witness_Dialog();
        witnessDialog.setCancelable(false);
        Bundle bld = new Bundle();
        bld.putString("from", from);
        witnessDialog.setArguments(bld);
        witnessDialog.show(fragmentManager, "witness");
    }

    private void insertMeasureValue() {
        Log.e("measurre", measurearraylist + "");

//        db.insertMeasureData(measurearraylist);

        //db.updateMeasureValue(type,newValue,bsSite);
//        Toast.makeText(PatientDetail.this, "Measure data save", Toast.LENGTH_SHORT).show();
    }

//    public void showCheckAlertDialog(final String title, String message,
//                                     final Context context, final boolean redirectToPreviousScreen) {
//        AlertDialog.Builder alertbox = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
//        alertbox.setMessage(message);
//        alertbox.setTitle(title);
//        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int arg1) {
//
//                Make_all_tabs_clickclable();
//                Disable_save_and_cancel_button();
//
//                if (tvmeasure.isSelected()) {
////                    MeasureValue = 0;
////                    MeasureType = "";
//                } else if (tvcheck.isSelected()) {
////                    checkPrescriptionValue = 0;
////                    checkPrescriptionId = "";
//                    getCheckInData();
//                }
//                dialog.dismiss();
//            }
//        });
//        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int arg1) {
//                dialog.dismiss();
//            }
//        });
//        alertbox.show();
//    }

    @Override
    public void onBackPressed() {
        if (btnSave.isEnabled()) {
            App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (tvcheck.isSelected()) {
            if (btnSave.isEnabled()) {
                App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
            } else {

                if (spCycle.getSelectedItem().toString().equals("This Cycle")) {
                    imc_met = "this_cycle";
                    Log.e("Cycle", "" + imc_met);
                    spnPosition = position;
                    if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                        new getCheckin().execute(imc_met);

                    } else {
                        Log.e("NetworkAvailablity", "offline------->");
                        newchecklist.clear();
                        newchecklist = mydbobj.getallFeedsFromDB(patientId, imc_met);
                        new_checkinAdapter = new New_CheckinAdapter(PatientDetail123.this, newchecklist);
                        mylist.setAdapter(new_checkinAdapter);
                    }

                } else if (spCycle.getSelectedItem().toString().equals("Next Cycle")) {
                    imc_met = "next_cycle";
                    Log.e("Cycle", "" + imc_met);
                    spnPosition = position;
                    if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                        new getCheckin().execute(imc_met);

                    } else {
                        Log.e("NetworkAvailablity", "offline------->");
                        newchecklist.clear();
                        newchecklist = mydbobj.getallFeedsFromDB(patientId, imc_met);
                        new_checkinAdapter = new New_CheckinAdapter(PatientDetail123.this, newchecklist);
                        mylist.setAdapter(new_checkinAdapter);
                    }
                }
            }
        } else if (tvmeds.isSelected()) {

            if (spCycle.getSelectedItem().toString().equals("This Cycle")) {
                imc_met = "this_cycle";
                Log.e("Cycle", "" + imc_met);
                spnPosition1 = position;
                Log.e("NetworkAvailablity", "NetworkAvailablity---1---->" +NetworkAvailablity.chkStatus(PatientDetail123.this));
                if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                    new getMedsData().execute(imc_met);
                } else {
                    newmedsList.clear();
                    newmedsList = mydbobj.getallFeedsFromDB(patientId, imc_met);
                    newmedsAdapter = new NewMedsAdapter(PatientDetail123.this, newmedsList);
                    mylist.setAdapter(newmedsAdapter);

//                App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
                }
//                new getMedsData().execute(imc_met);

            } else if (spCycle.getSelectedItem().toString().equals("Last Cycle")) {
                imc_met = "last_cycle";
                Log.e("Cycle", "" + imc_met);
                spnPosition1 = position;
                Log.e("NetworkAvailablity", "NetworkAvailablity---2---->" +NetworkAvailablity.chkStatus(PatientDetail123.this));
                if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                    new getMedsData().execute(imc_met);
                } else {
                    newmedsList.clear();
                    newmedsList = mydbobj.getallFeedsFromDB(patientId, imc_met);
                    newmedsAdapter = new NewMedsAdapter(PatientDetail123.this, newmedsList);
                    mylist.setAdapter(newmedsAdapter);

//                App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
                }

            } else if (spCycle.getSelectedItem().toString().equals("Next Cycle")) {
                imc_met = "next_cycle";
                Log.e("Cycle", "" + imc_met);
                spnPosition1 = position;
                Log.e("NetworkAvailablity", "NetworkAvailablity---3---->" +NetworkAvailablity.chkStatus(PatientDetail123.this));
                if (NetworkAvailablity.chkStatus(PatientDetail123.this)) {
                    new getMedsData().execute(imc_met);
                } else {
                    newmedsList.clear();
                    newmedsList = mydbobj.getallFeedsFromDB(patientId, imc_met);
                    newmedsAdapter = new NewMedsAdapter(PatientDetail123.this, newmedsList);
                    mylist.setAdapter(newmedsAdapter);

//                App_Constants.showAlertDialog("Alert", "Please check network connection!", PatientDetail.this, false);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    String new_bs_site_value = "";

    @Override
    public void BloodSugarUpdate(int sub, String detail, int position) {
//         Log.e("BloodSugarValues  ", "  " + sub + "  " + detail);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub) && "".equals(detail)) {
        } else {
            measurearraylist.set(position, new MeasureModel("Blood Sugar", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            new_bs_site_value = detail;
            Log.e("BloodSugarValues  ", "-----> " + sub + "  " + detail);
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void BMIUpdate(int sub, int position) {
        //  Log.e("BMIvalues  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("BMI", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void BPSysUpdate(int sub, int position) {
        //  Log.e("BPSysvalues  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("BP  Systolic", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void BPDiaUpdate(int sub, int position) {
        // Log.e("BPDiavalues  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("BP Diastolic", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void HeightUpdate(int sub, int position) {
        // Log.e("values  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("Height", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void INRUpdate(int sub, int position) {
        //  Log.e("values  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("INR", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void LithiumUpdate(int sub, int position) {
        //Log.e("values  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("Lithium", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void PulseUpdate(int sub, int position) {
        //  Log.e("values  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("Pulse", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void WeightUpdate(int sub, int position) {
        //  Log.e("values  ", "  " + sub);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            measurearraylist.set(position, new MeasureModel("Weight", sub, measurearraylist.get(position).getUnits(), date, "true"));
            measureAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    //--------------------------------------------------------------------------------------//

    @Override
    public void AddNoteUpdate(String sub, String content) {
        Log.e("values  ", "  " + sub + "  " + content);
        String date = date_utility.getMeasuredate();
        String patientId = "";
        Intent b = getIntent();
        if (b != null) {
            patientId = b.getStringExtra("patient_id");
        }
        */
/*Print values on log*//*

        Log.e("date format", " date --------- " + date);
        Log.e("subject", " sub --------- " + sub);
        Log.e("content", " content --------- " + content);
        Log.e("patientId", " patientId --------- " + patientId);
        new CreateNote_Async().execute(content, sub);

//        db.insertNotesData(notesarraylist);
//        Log.e("Notes Values :", notesarraylist + "");

    }


    @Override
    public void ConfirmQuantityUpdate(int sub, int pos, String tab) {
        Log.e("values  ", "  " + sub + " " + pos);
        String date = date_utility.getMeasuredate();
        if ("".equals(date) && "".equals(sub)) {
        } else {
            // by pankaj
            PRESCRIPTION_COMMON_Model checkModel = newchecklist.get(pos);
            int value = newchecklist.get(pos).getChecked_in_quantity();
            value = value + sub;
            checkModel.setChecked_in_quantity(value);
            checkModel.setNew_checked_in_quantity_toupdate(sub);
            checkModel.setIsSelected("true");
            new_checkinAdapter.notifyDataSetChanged();
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void DoseDetailsUpdate(String sub, String detail) {
        Log.e("values  ", "  " + sub + "  " + detail);
    }

    @Override
    public void PrescribedQuantityUpdate(String prescribed_slot_time, int qty, int pos, String secondary_user_id, String quantity_wasted, String uid, ArrayList<String> _warning_overrides_list) {
        Log.e("values  ", "  " + qty + "  " + pos);

        JSONArray notesattr = null;
        Log.e("PrescribedQuantityUpdate  ", " PrescribedQuantityUpdate quantity----> " + qty);
        NEW_ADMIN_MODEL nadm = new_admin_modelArrayList.get(pos);
        String prescription_id = nadm.getAdmin_prescription_model().getId();
//        ArrayList<String> _warning_overrides_list = nadm.get_warning_overrides_list();
//       _warning_overrides_list.add("This med was given less than 4 hours ago, are you sure you wish to proceed?");
        nadm.set_warning_overrides_list(_warning_overrides_list);

        if (_warning_overrides_list.size() > 0) {
            notesattr = new JSONArray();
        }
        Log.e("_warning_overrides_list.size()", "PrescribedQuantityUpdate list.size()---->" + _warning_overrides_list.size());

        for (int i = 0; i < _warning_overrides_list.size(); i++) {

            try {
                JSONObject jo = new JSONObject();
                jo.put("content", nadm.get_warning_overrides_list().get(i));
                jo.put("note_type", "Warning_Override");
                jo.put("patient_id", patientId);
                jo.put("prescription_id", prescription_id);
                notesattr.put(jo);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Log.e("_warning_overrides_list.size()", "PrescribedQuantityUpdate notesattr---->" + notesattr);

        nadm.setIs_edited(true);
        nadm.setSlot_time(prescribed_slot_time);
        nadm.setOutcome("true");
        nadm.setReason("");
        nadm.setDose("" + qty);
        nadm.setSecondary_user_id(secondary_user_id);
        nadm.setIs_waste("false");
        nadm.setQuantity_wasted("0");
        nadm.setUid(uid);
        nadm.setNotesattr(notesattr);
        nadm.setPatient_ID(patientId);
        nadm.setPrecription_ID(prescription_id);
        nadm.setAction("Giving");

        new_admin_adapter_by_pankaj.notifyDataSetChanged();
        Log.e("PrescribedQuantityUpdate  ", " notifyDataSetChanged----> ");
        Enable_save_and_cancel_button();

    }


    @Override
    public void Patch_Dialog_Update(String ppslot_time, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab, ArrayList<String> _warning_overrides_list) {

        if (tab.equals("admin")) {
            JSONArray notesattr = null;
            Log.e("Patch_Dialog_Update  ", " quantity----> " + dose_qty);
            Log.e("Patch_Dialog_Update  ", " reason----> " + reason);
            NEW_ADMIN_MODEL nadm = new_admin_modelArrayList.get(pos);
            String prescription_id = nadm.getAdmin_prescription_model().getId();
//            ArrayList<String> _warning_overrides_list = nadm.get_warning_overrides_list();
//          _warning_overrides_list.add("This med was given less than 4 hours ago, are you sure you wish to proceed?");
            nadm.set_warning_overrides_list(_warning_overrides_list);

            try {
                JSONObject jo = new JSONObject();
                jo.put("content", reason);
                jo.put("note_type", "Patch_Location");
                jo.put("patient_id", patientId);
                jo.put("prescription_id", prescription_id);
                notesattr = new JSONArray();
                notesattr.put(jo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < _warning_overrides_list.size(); i++) {
                try {
                    JSONObject jo = new JSONObject();
                    jo.put("content", nadm.get_warning_overrides_list().get(i));
                    jo.put("note_type", "Warning_Override");
                    jo.put("patient_id", patientId);
                    jo.put("prescription_id", prescription_id);
                    notesattr.put(jo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            nadm.setIs_edited(true);
            nadm.setSlot_time(ppslot_time);
            nadm.setOutcome("true");
            nadm.setReason("");
            nadm.setDose(dose_qty);
            nadm.setSecondary_user_id(secondary_user_id);
            nadm.setIs_waste(is_waste);
            nadm.setQuantity_wasted(quantity_wasted);
            nadm.setUid(uid);
            nadm.setNotesattr(notesattr);
            nadm.setPatient_ID(patientId);
            nadm.setPrecription_ID(prescription_id);
            nadm.setAction("Giving");

            new_admin_adapter_by_pankaj.notifyDataSetChanged();
            Log.e("Patch_Dialog_Update  ", " notifyDataSetChanged----> ");
            Enable_save_and_cancel_button();
        }
    }

    @Override
    public void ConfirmAdminPrnUpdate(String adm_prn_slot_time, String adm_prn_outcome, String adm_prn_reason, String adm_prn_dose, String adm_prn_secondary_user_id, String adm_prn_is_waste, String adm_prn_quantity_wasted, String adm_prn_uid, int pos, String tab, ArrayList<String> _warning_overrides_list) {
        Log.e("ConfirmAdminPrnUpdate  ", " ----> " + adm_prn_dose + "  " + adm_prn_reason + " " + tab);

        if ("".equals(adm_prn_dose) && "".equals(adm_prn_reason)) {
        } else if (tab.equals("admin")) {
            JSONArray notesattr = null;
            Log.e("ConfirmAdminPrnUpdate  ", " quantity----> " + adm_prn_dose);
            Log.e("ConfirmAdminPrnUpdate  ", " reason----> " + adm_prn_reason);
            NEW_ADMIN_MODEL nadm = new_admin_modelArrayList.get(pos);
            String prescription_id = nadm.getAdmin_prescription_model().getId();
//            ArrayList<String> _warning_overrides_list = nadm.get_warning_overrides_list();
//            _warning_overrides_list.add("This med was given less than 4 hours ago, are you sure you wish to proceed?");
            nadm.set_warning_overrides_list(_warning_overrides_list);

            try {
                JSONObject jo = new JSONObject();
                jo.put("content", adm_prn_reason);
                jo.put("note_type", "MAR_Note");
                jo.put("patient_id", patientId);
                jo.put("prescription_id", prescription_id);
                notesattr = new JSONArray();
                notesattr.put(jo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < _warning_overrides_list.size(); i++) {
                try {
                    JSONObject jo = new JSONObject();
                    jo.put("content", nadm.get_warning_overrides_list().get(i));
                    jo.put("note_type", "Warning_Override");
                    jo.put("patient_id", patientId);
                    jo.put("prescription_id", prescription_id);
                    notesattr.put(jo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            nadm.setIs_edited(true);
            nadm.setSlot_time("");
            nadm.setOutcome("true");
            nadm.setReason("");
            nadm.setDose(adm_prn_dose);
            nadm.setSecondary_user_id(adm_prn_secondary_user_id);
            nadm.setIs_waste(adm_prn_is_waste);
            nadm.setQuantity_wasted(adm_prn_quantity_wasted);
            nadm.setUid(adm_prn_uid);
            nadm.setNotesattr(notesattr);
            nadm.setPatient_ID(patientId);
            nadm.setPrecription_ID(prescription_id);
            nadm.setAction("Giving");

            new_admin_adapter_by_pankaj.notifyDataSetChanged();
            Log.e("ConfirmAdminPrnUpdate  ", " notifyDataSetChanged----> ");
            Enable_save_and_cancel_button();

        }

    }


    @Override
    public void Warfarine_QuantityUpdate(String slot_time11, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab, ArrayList<String> _warning_overrides_list) {

        if (tab.equals("admin")) {
            JSONArray notesattr = null;
            Log.e("Warfarine_QuantityUpdate  ", " quantity----> " + dose_qty);
            Log.e("Warfarine_QuantityUpdate  ", " reason----> " + reason);
            NEW_ADMIN_MODEL nadm = new_admin_modelArrayList.get(pos);
            String prescription_id = nadm.getAdmin_prescription_model().getId();
//            ArrayList<String> _warning_overrides_list = nadm.get_warning_overrides_list();
//            _warning_overrides_list.add("This med was given less than 4 hours ago, are you sure you wish to proceed?");
            nadm.set_warning_overrides_list(_warning_overrides_list);

            for (int i = 0; i < _warning_overrides_list.size(); i++) {

                try {
                    JSONObject jo = new JSONObject();
                    jo.put("content", nadm.get_warning_overrides_list().get(i));
                    jo.put("note_type", "Warning_Override");
                    jo.put("patient_id", patientId);
                    jo.put("prescription_id", prescription_id);
                    notesattr.put(jo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            nadm.setIs_edited(true);
            nadm.setSlot_time(slot_time11);
            nadm.setOutcome("true");
            nadm.setReason("");
            nadm.setDose(dose_qty);
            nadm.setSecondary_user_id(secondary_user_id);
            nadm.setIs_waste(is_waste);
            nadm.setQuantity_wasted(quantity_wasted);
            nadm.setUid(uid);
            nadm.setNotesattr(notesattr);
            nadm.setPatient_ID(patientId);
            nadm.setPrecription_ID(prescription_id);
            nadm.setAction("Giving");
            new_admin_adapter_by_pankaj.notifyDataSetChanged();
            Log.e("Warfarine_QuantityUpdate  ", " notifyDataSetChanged----> ");
            Enable_save_and_cancel_button();

        }
    }


    @Override
    public void ReasonUpdate(String slot_time, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab, String editNotes_value, ArrayList<String> _warning_overrides_list) {

        JSONArray notesattr = null;
        Log.e("ReasonUpdate  ", " editNotes_value----> " + editNotes_value);
        Log.e("ReasonUpdate  ", " reason----> " + reason);
        Log.e("ReasonUpdate  ", " pos----> " + pos);
        NEW_ADMIN_MODEL nadm = new_admin_modelArrayList.get(pos);
        String prescription_id = nadm.getAdmin_prescription_model().getId();

        nadm.set_warning_overrides_list(_warning_overrides_list);

        if (!editNotes_value.isEmpty()) {
            try {
                JSONObject jo = new JSONObject();
                jo.put("content", editNotes_value);
                jo.put("note_type", "MAR_Note");
                notesattr = new JSONArray();
                notesattr.put(jo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        for (int i = 0; i < _warning_overrides_list.size(); i++) {

            try {
                JSONObject jo = new JSONObject();
                jo.put("content", nadm.get_warning_overrides_list().get(i));
                jo.put("note_type", "Warning_Override");
                jo.put("patient_id", patientId);
                jo.put("prescription_id", prescription_id);
                notesattr.put(jo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        nadm.setIs_edited(true);
        nadm.setSlot_time(slot_time);
        nadm.setOutcome("false");
        nadm.setReason(reason);
        nadm.setDose("");
        nadm.setSecondary_user_id(secondary_user_id);
        nadm.setIs_waste(is_waste);
        nadm.setQuantity_wasted(quantity_wasted);
        nadm.setUid(uid);
        nadm.setNotesattr(notesattr);
        nadm.setPatient_ID(patientId);
        nadm.setPrecription_ID(prescription_id);
        nadm.setAction("NotGiving");
        new_admin_adapter_by_pankaj.notifyDataSetChanged();
        Log.e("ConfirmAdminPrnUpdate  ", " notifyDataSetChanged----> ");
        Enable_save_and_cancel_button();
    }


    @Override
    public void ConfirmPrnUpdate(int sub, String reason, int pos, String tab) {
        Log.e("ConfirmPrnUpdate  ", " ----> " + sub + "  " + reason + " " + tab);
        if ("".equals(sub) && "".equals(reason)) {
        } else {
            if ("home".equals(tab)) {
                New_Homely_Remedy_Model hmModel = new_homely_List.get(pos);
                hmModel.setIs_edited(true);
                hmModel.setDose_to_update("" + sub);
                new_homely_Adapter.notifyDataSetChanged();
            } else if (tab.equals("admin")) {
                Log.e("ConfirmPrnUpdate  ", " quantity----> " + sub);
                Log.e("ConfirmPrnUpdate  ", " reason----> " + reason);
            }

            Enable_save_and_cancel_button();
        }


    }


    @Override
    public void ReturnUpdate(int quantityvalue, int wastevalue, int pos, String quant, String waste, int newavailable_stock, int quantityReturned, int quantityCfwd, int quantityDestroyed, int waste_qtyReturned, int waste_qtyDestroyed) {
//        ReturnModel returnModel = returnList.get(pos);

        PRESCRIPTION_COMMON_Model r1model = new_return_List.get(pos);
        Log.e("******", "**********Start***********");
        Log.e("quantityvalue:=", "" + quantityvalue);
        Log.e("wastevalue:=", "" + wastevalue);
        Log.e("newavailable_stock:=", "" + newavailable_stock);
        Log.e("pos:=", "" + pos);
        Log.e("quantSpn:=", "" + quant);
        Log.e("wasteSpn:=", "" + waste);

        if ("Return".equals(quant)) {
            int qty_to_update = r1model.getReturned_quantity() + quantityReturned;
//                returnModel.setReturned_quantity(quantityvalue);
            Log.e("Return case", "--->" + qty_to_update);
            r1model.setReturned_quantity(qty_to_update);
            r1model.setAction_applied("Return");
            r1model.setSelected_inreturn("true");
            r1model.setQuantity_to_update_on_server(quantityReturned);

//            int qty_to_update = r1model.getAvailable_quantity() - r1model.getReturned_quantity()-r1model.getReturned_quantity();
            r1model.setAvailable_quantity(newavailable_stock);
        } else if ("Carry Forward".equals(quant)) {
            int qty_to_update = r1model.getCarried_forward_quantity() + quantityCfwd;
//                returnModel.setCarried_forward_quantity(quantityvalue);
            Log.e("Carry Forward case ", "--->" + qty_to_update);
            r1model.setCarried_forward_quantity(qty_to_update);
            r1model.setAction_applied("Carry Forward");
            r1model.setSelected_inreturn("true");
            r1model.setQuantity_to_update_on_server(quantityCfwd);
            r1model.setAvailable_quantity(newavailable_stock);
        } else if ("Destroyed".equals(quant)) {
            int qty_to_update = r1model.getDestroyed_quantity() + quantityDestroyed;
//                returnModel.setDestroyed_quantity(quantityvalue);
            Log.e("Destroyed case", "--->" + qty_to_update);
            r1model.setDestroyed_quantity(qty_to_update);
            r1model.setAction_applied("Destroyed");
            r1model.setSelected_inreturn("true");
            r1model.setQuantity_to_update_on_server(quantityDestroyed);
            r1model.setAvailable_quantity(newavailable_stock);
        }


        if ("Return".equals(waste)) {
            int qty_to_update = r1model.getReturned_quantity() + waste_qtyReturned;
            Log.e("Waste Return case", "--->" + qty_to_update);
            r1model.setReturned_quantity(qty_to_update);
            r1model.setWaste_Action_applied("Return");
            r1model.setSelected_inreturn("true");
            r1model.setWaste_qty_to_update_on_server(waste_qtyReturned);
            r1model.setAvailable_quantity(newavailable_stock);
        } else if ("Destroyed".equals(waste)) {
            int qty_to_update = r1model.getDestroyed_quantity() + waste_qtyDestroyed;
            Log.e("Waste Destroyed case", "--->" + qty_to_update);
            r1model.setDestroyed_quantity(qty_to_update);
            r1model.setWaste_Action_applied("Destroyed");
            r1model.setSelected_inreturn("true");
            r1model.setWaste_qty_to_update_on_server(waste_qtyDestroyed);
            r1model.setAvailable_quantity(newavailable_stock);
        }

        new_return_Adpater.notifyDataSetChanged();
        Enable_save_and_cancel_button();
    }


    //--------------------------------------Web - Services--------------------------------------------//

    private class getMeasurementData extends AsyncTask<String, Void, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                // https://demo.caremeds.co.uk/api/patients/164/measurements/?authentication_token=BKdiJphcWGDPZFJqyoE7&last=true&all_types=true
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
//                String weburl = WebServiceDetails.MEASUREMENT_DATA + patientId + "/measurements/?authentication_token="
//                        + global_auth_token + "&last=true&all_types=true";

                String weburl = baseurl_value + patientId + "/measurements/?authentication_token="
                        + global_auth_token + "&last=true&all_types=true";

                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getMeasurementData url----->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(" Response", "getMeasurementData----->" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            measurearraylist.clear();
            new_bs_site_value = "";
            MeasureModel measureModel1 = null;
            try {
                JSONArray array = new JSONArray(result);
                for (int k = 0; k < array.length(); k++) {
                    JSONObject obj = array.optJSONObject(k);
                    JSONObject obj1 = obj.optJSONObject("measurement");
                    String created_at = obj1.optString("created_at");
                    String deleted_at = obj1.optString("deleted_at");
                    String id = obj1.optString("id");
                    String measurement_type_id = obj1.optString("measurement_type_id");
                    String patient_id = obj1.optString("patient_id");
                    String updated_at = obj1.optString("updated_at");
                    int value = obj1.optInt("value");

                    JSONObject obj2 = obj1.optJSONObject("measurement_type");
                    JSONObject obj3 = obj2.optJSONObject("measurement_type");

                    String created_at_second = obj3.optString("created_at");
                    String deleted_at_second = obj3.optString("deleted_at");
                    String id_second = obj3.optString("id");
                    String name = obj3.optString("name");
                    String units = obj3.optString("units");
                    String updated_at_second = obj3.optString("updated_at");
                    measureModel1 = new MeasureModel(id, created_at, deleted_at, measurement_type_id, patient_id, updated_at, value, created_at_second, deleted_at_second, id_second, name, units, updated_at_second, "false");
                    measurearraylist.add(measureModel1);
                    Log.e("measurearraylist name", "--->" + name);
                }
                measureAdapter = new MeasureAdapter(PatientDetail123.this, measurearraylist);
                mylist.setAdapter(measureAdapter);
                mydbobj.insertMeasureData(measurearraylist);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    private class getMedicationsData extends AsyncTask<String, Void, String> {
//        Dialog dlg;
//
//        protected void onPreExecute() {
//            super.onPreExecute();
////            progDialog.show();
//            dlg = app_constants.dialog(PatientDetail.this, "Loading");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            String result = "";
//            try {
//                String weburl = "" + WebServiceDetails.MEDICATIONS;
//                result = jsonParser.getjson_method(weburl);
//                Log.e("httpGet", "getMedicationsData url--->" + weburl);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Log.e("Response", "getMedicationsData---->" + result);
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
//            try {
//                JSONObject obj = new JSONObject(result);
//                JSONObject obj1 = obj.optJSONObject("medication");
//                String bCHM = obj1.optString("bCHM");
//                String bCanOverrideDoseFreq = obj1.optString("bCanOverrideDoseFreq");
//                String bCumlativeDosePrescribe = obj1.optString("bCumlativeDosePrescribe");
//                String bCumulativeDoseAdmin = obj1.optString("bCumulativeDoseAdmin");
//                String bEWNHSReimbursable = obj1.optString("bEWNHSReimbursable");
//                String bHighRisk = obj1.optString("bHighRisk");
//                String bIsEWDentist = obj1.optString("bIsEWDentist");
//                String bIsEWMASPrescribable = obj1.optString("bIsEWMASPrescribable");
//                String bIsEWNurse = obj1.optString("bIsEWNurse");
//                String bIsNIDentist = obj1.optString("bIsNIDentist");
//                String bIsNIMASPrescribable = obj1.optString("bIsNIMASPrescribable");
//                String bIsNINurse = obj1.optString("bIsNINurse");
//                String bIsScoDentist = obj1.optString("bIsScoDentist");
//                String bIsScoMASPrescribable = obj1.optString("bIsScoMASPrescribable");
//                String bIsScoNurse = obj1.optString("bIsScoNurse");
//                String bIsSupported = obj1.optString("bIsSupported");
//                String bIsUnlicensed = obj1.optString("bIsUnlicensed");
//                String bNINHSReimbursable = obj1.optString("bNINHSReimbursable");
//                String bParallelImport = obj1.optString("bParallelImport");
//                String bScotNHSReimbursable = obj1.optString("bScotNHSReimbursable");
//                String bSugarFree = obj1.optString("bSugarFree");
//                String bWordsAndFiguresRequired = obj1.optString("bWordsAndFiguresRequired");
//                String dDateOfChange = obj1.optString("dDateOfChange");
//                String dProdAddDate = obj1.optString("dProdAddDate");
//                String dProdDiscDate = obj1.optString("dProdDiscDate");
//                String fMaxDoseBeforeReview = obj1.optString("fMaxDoseBeforeReview");
//                String iBaseFormulationID = obj1.optString("iBaseFormulationID");
//                String iChangeTypeID = obj1.optString("iChangeTypeID");
//                String iClinicalSetID = obj1.optString("iClinicalSetID");
//                String iCompanyID = obj1.optString("iCompanyID");
//                String iComplementaryTherapyTypeID = obj1.optString("iComplementaryTherapyTypeID");
//                String iControlledDrugSchedule = obj1.optString("iControlledDrugSchedule");
//                String iCoreGenericProductID = obj1.optString("iCoreGenericProductID");
//                String iEWApplianceTariffHierID = obj1.optString("bSugarFiEWApplianceTariffHierIDree");
//                String iFormulationID = obj1.optString("iFormulationID");
//                String iLegacyLegalID = obj1.optString("iLegacyLegalID");
//                String iLegalID = obj1.optString("iLegalID");
//                String iPersonalAdminID = obj1.optString("iPersonalAdminID");
//                String iPickListOrderKey = obj1.optString("iPickListOrderKey");
//                String iPickListOrderKey2 = obj1.optString("iPickListOrderKey2");
//                String iPrescribeByID = obj1.optString("iPrescribeByID");
//                String iProductClassID = obj1.optString("iProductClassID");
//                String iProductID = obj1.optString("iProductID");
//                String iProductTypeID = obj1.optString("iProductTypeID");
//                String iQualifierID = obj1.optString("iQualifierID");
//                String iScotApplianceTariffHierID = obj1.optString("iScotApplianceTariffHierID");
//                String iStatusID = obj1.optString("iStatusID");
//                String iStrengthID = obj1.optString("iStrengthID");
//                String sDisplayName = obj1.optString("sDisplayName");
//                String sDisplayNameTM = obj1.optString("sDisplayNameTM");
//                String sLegacyDisplayName = obj1.optString("sLegacyDisplayName");
//                String sName = obj1.optString("sName");
//                String sNameTM = obj1.optString("sNameTM");
//                String sOrderNo = obj1.optString("sOrderNo");
//                Log.e("sOrderNo", sOrderNo);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


    */
/********************************************************************************************//*



    private class getPatientsRecord_Data extends AsyncTask<String, Void, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
//                String weburl = "" + WebServiceDetails.GET_PATIENT_RECORD_DATA + patientId + "?authentication_token=" + global_auth_token;
                String weburl = "" + baseurl_value + patientId + "?authentication_token=" + global_auth_token;
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getPatientsRecord_Data url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Response", "getPatientsRecord_Data---->" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            try {
                JSONObject obj = new JSONObject(result);
                JSONObject obj1 = obj.optJSONObject("patient");
                String address = obj1.optString("address");
                String allergies = obj1.optString("allergies");
                String care_home_id = obj1.optString("care_home_id");
                String created_at = obj1.optString("created_at");
                String cycle_base_date = obj1.optString("cycle_base_date");
                String cycle_duration = obj1.optString("cycle_duration");
                String deleted_at = obj1.optString("deleted_at");
                String dob = obj1.optString("dob");
                String first_name = obj1.optString("first_name");
                String gp_id = obj1.optString("gp_id");
                String gp_name = obj1.optString("gp_name");
                String id = obj1.optString("id");
                String inactive = obj1.optString("inactive");
                String name = obj1.optString("name");
                String nhs_number = obj1.optString("nhs_number");
                String patient_notes = obj1.optString("patient_notes");
                String photo_image = obj1.optString("photo_image");
                String room = obj1.optString("room");
                String title = obj1.optString("title");
                String updated_at = obj1.optString("updated_at");
                _global_inr_reading = obj1.optString("inr_reading");
                _global_inr_date = obj1.optString("inr_date");
                _global_warfarin_dose = obj1.optString("warfarin_dose");

                Log.e("warfarin_dose", "----->" + _global_warfarin_dose);
                Log.e("inr_date", "----->" + _global_inr_date);
                Date_utility.date_diffrence(_global_inr_date);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    */
/****************************************************************************************//*

    private class getNotesData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/notes?authentication_token=" + global_auth_token + "&note_type=General";
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getNotesData url----->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(" Response", "getNotesData---->" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            notesarraylist.clear();
            try {

                JSONArray array = new JSONArray(result);
                for (int k = 0; k < array.length(); k++) {
                    JSONObject obj = array.optJSONObject(k);
                    JSONObject obj1 = obj.optJSONObject("note");
                    String content = obj1.optString("content");
                    String created_at = obj1.optString("created_at");
                    String deleted_at = obj1.optString("deleted_at");
                    String id = obj1.optString("id");
                    String mar_id = obj1.optString("mar_id");
                    String note_date = obj1.optString("note_date");
                    String note_type = obj1.optString("note_type");
                    String patient_id = obj1.optString("patient_id");
                    String prescription_id = obj1.optString("prescription_id");
                    String subject = obj1.optString("subject");
                    String updated_at = obj1.optString("updated_at");
                    String user_id = obj1.optString("user_id");
                    Log.e("user_id", user_id);

                    JSONObject obj2 = obj1.optJSONObject("user");
                    JSONObject obj3 = obj2.optJSONObject("user");

                    String can_manage_users = obj3.optString("can_manage_users");
                    String care_home_id = obj3.optString("care_home_id");
                    String email = obj3.optString("email");
                    String fullname = obj3.optString("fullname");
                    String is_active = obj3.optString("is_active");
                    String username = obj3.optString("username");
                    Log.e("username", username);

                    NotesModel notesModel = new NotesModel(id, content, created_at, deleted_at, mar_id, note_date, note_type,
                            patient_id, prescription_id, subject, updated_at, user_id, can_manage_users, care_home_id, email, fullname, is_active, username);
                    // notesModel.setNoteName(subject);
//                    notesModel.setContent(content);
//                    notesModel.setCreated_at(created_at);
//                    notesModel.setDeleted_at(deleted_at);
//                    notesModel.setId(id);
//                    notesModel.setMar_Id(mar_id);
//                    notesModel.setNote_date(note_date);
//                    notesModel.setNote_type(note_type);
//                    notesModel.setPatient_id(patient_id);
//                    notesModel.setPrescription_id(prescription_id);
//                    notesModel.setSubject(subject);
//                    notesModel.setUpdated_at(updated_at);
//                    notesModel.setUser_id(user_id);
//                    notesModel.setCan_manage_users(can_manage_users);
//                    notesModel.setCare_home_id(care_home_id);
//                    notesModel.setEmail(email);
//                    notesModel.setFullname(fullname);
//                    notesModel.setIs_active(is_active);
//                    notesModel.setUsername(username);
                    if ("General".equals(note_type)) {
                        notesarraylist.add(notesModel);
                    }
                }
                notesadapter = new NotesAdapter(PatientDetail123.this, notesarraylist);
                mylist.setAdapter(notesadapter);
                mydbobj.insertNotesData(notesarraylist);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class getAdminPresData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token;
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getAdminPresData url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Patient", "" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            admin_prescription_modelArrayList.clear();
            ArrayList<Today_Mars_Mar_Bean> admin_Today_Mars_Mar_objects_list;
            ArrayList<PRESCRIBED_TIME_SLOT> admin_PRES_TIME_SLOT_objects_list;
            ArrayList<TIME_SLOT_DOSES> admin_TIME_SLOT_DOSES_objects_list;
            try {
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    admin_Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean>();
                    admin_PRES_TIME_SLOT_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT>();
                    admin_TIME_SLOT_DOSES_objects_list = new ArrayList<TIME_SLOT_DOSES>();
                    timedosenamelist.clear();

                    JSONObject obj = array.optJSONObject(i);
                    JSONObject obj1 = obj.optJSONObject("prescription");
                    String id = obj1.optString("id");
                    String indications = obj1.optString("indications");
                    String instructions = obj1.optString("instructions");
                    String is_patch = obj1.optString("is_patch");
                    String is_special = obj1.optString("is_special");
                    String non_blistered = obj1.optString("non_blistered");
                    String prescription_type = obj1.optString("prescription_type");
                    String quantity_dispensed = obj1.optString("quantity_dispensed");
                    String special_medication_name = obj1.optString("special_medication_name");
                    String start_date = obj1.optString("start_date");
                    String status = obj1.optString("status");
                    String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
                    String mandatory_instructions = obj1.optString("mandatory_instructions");

                    try {
                        JSONArray todaysmars = obj1.optJSONArray("todays_mars");
                        for (int l = 0; l < todaysmars.length(); l++) {
                            JSONObject obj7 = todaysmars.getJSONObject(l);
                            JSONObject obj8 = obj7.optJSONObject("mar");
                            String mar_created_at = obj8.optString("created_at");
                            String mar_deleted_at = obj8.optString("deleted_at");
                            String mar_dose = obj8.optString("dose");
                            String mar_false_reason = obj8.optString("false_reason");
                            String mar_gps_location = obj8.optString("gps_location");
                            String mar_homely_remedy_id = obj8.optString("homely_remedy_id");
                            String mar_homely_remedy_name = obj8.optString("homely_remedy_name");
                            String mar_id = obj8.optString("id");
                            String mar_is_waste = obj8.optString("is_waste");
                            String mar_medication_id = obj8.optString("medication_id");
                            String mar_outcome = obj8.optString("outcome");
                            // newly added on 15-04-2016
                            String patient_id = obj8.optString("patient_id");
                            String prescription_id = obj8.optString("prescription_id");
                            //////////////
                            String mar_quantity_wasted = obj8.optString("quantity_wasted");
                            String mar_secondary_user_id = obj8.optString("secondary_user_id");
                            String mar_seconday_user_fullname = obj8.optString("seconday_user_fullname");
                            String mar_slot_time = obj8.optString("slot_time");
                            String mar_uid = obj8.optString("uid");
                            String mar_updated_at = obj8.optString("updated_at");

                            // newly added on 15-04-2016
                            String user_fullname = obj8.optString("user_fullname");
                            String user_id = obj8.optString("user_id");
                            //////////////
                            Today_Mars_Mar_Bean tdm1 = new Today_Mars_Mar_Bean(mar_created_at, mar_deleted_at, mar_dose, mar_false_reason, mar_gps_location, mar_homely_remedy_id, mar_id, mar_homely_remedy_name, mar_is_waste, mar_medication_id, mar_outcome, patient_id, prescription_id, mar_quantity_wasted, mar_secondary_user_id, mar_seconday_user_fullname, mar_slot_time, mar_uid, mar_updated_at, user_fullname, user_id);
                            admin_Today_Mars_Mar_objects_list.add(tdm1);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String can_carry_forward = obj1.optString("can_carry_forward");
                    String front_image_url = obj1.optString("front_image_url");
                    String drug_name = obj1.optString("drug_name");
                    String detailed_drug_name = obj1.optString("detailed_drug_name");
                    String short_drug_name = obj1.optString("short_drug_name");
                    String patch_location = obj1.optString("patch_location");

                    JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
                    for (int k = 0; k < array1.length(); k++) {
                        JSONObject obj2 = array1.optJSONObject(k);
                        JSONObject obj3 = obj2.optJSONObject("time_slot_dose");
                        String color = obj3.optString("color");
                        String created_at = obj3.optString("created_at");
                        String deleted_at = obj3.optString("deleted_at");
                        String Maindose = obj3.optString("dose");
                        String time_slot_dose_id = obj3.optString("id");
                        String prescription_id = obj3.optString("prescription_id");
                        String show_as = obj3.optString("show_as");
                        String slot_time = obj3.optString("slot_time");
                        String updated_at = obj3.optString("updated_at");
                        Log.e("slot_time", created_at + "  " + color + "  " + show_as);

                        PRESCRIBED_TIME_SLOT pts_obj = new PRESCRIBED_TIME_SLOT(color, created_at, deleted_at, Maindose, prescription_id, time_slot_dose_id, show_as, slot_time, updated_at);
                        admin_PRES_TIME_SLOT_objects_list.add(pts_obj);

                    }


                    LAST_ADMIN_MAR lastadmin_obj = null;
                    try {
                        JSONObject jsonObject = obj1.optJSONObject("last_admin");
                        JSONObject jsonobj = jsonObject.optJSONObject("mar");
                        String last_admin_created_at = jsonobj.optString("created_at");
                        String last_admin_deleted_at = jsonobj.optString("deleted_at");
                        String dose = jsonobj.optString("dose");
                        String false_reason = jsonobj.optString("false_reason");
                        String gps_location = jsonobj.optString("gps_location");
                        String homely_remedy_id = jsonobj.optString("homely_remedy_id");
                        String homely_remedy_name = jsonobj.optString("homely_remedy_name");
                        String mar_id = jsonobj.optString("id");
                        String is_waste = jsonobj.optString("is_waste");
                        String medication_id = jsonobj.optString("medication_id");
                        String outcome = jsonobj.optString("outcome");
                        String patient_id = jsonobj.optString("patient_id");
                        String prescription_id = jsonobj.optString("prescription_id");
                        //  String show_as = jsonobj.optString("show_as");
                        String quantity_wasted = jsonobj.optString("quantity_wasted");
                        String secondary_user_id = jsonobj.optString("secondary_user_id");
                        String seconday_user_fullname = jsonobj.optString("seconday_user_fullname");
                        String slot_time = jsonobj.optString("slot_time");
                        String updated_at = jsonobj.optString("updated_at");
                        String uid = jsonobj.optString("uid");
                        String user_fullname = jsonobj.optString("user_fullname");
                        Log.e("user_fullname", "" + user_fullname);
                        String user_id = jsonobj.optString("user_id");

                        lastadmin_obj = new LAST_ADMIN_MAR(last_admin_created_at, last_admin_deleted_at, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, mar_id, is_waste, medication_id, outcome, prescription_id, patient_id, quantity_wasted, secondary_user_id, seconday_user_fullname, updated_at, slot_time, uid, user_fullname, user_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    String last_admin = obj1.optString("last_admin");
                    String first_check_in_date = obj1.optString("first_check_in_date");
                    int dispensed_quantity = obj1.optInt("dispensed_quantity");
                    int available_quantity = obj1.optInt("available_quantity");
                    int checked_in_quantity = obj1.optInt("checked_in_quantity");
                    int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
                    int returned_quantity = obj1.optInt("returned_quantity");
                    int destroyed_quantity = obj1.optInt("destroyed_quantity");
                    int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
                    int wasted_quantity = obj1.optInt("wasted_quantity");
                    int administered_quantity = obj1.optInt("administered_quantity");
                    int audited_quantity = obj1.optInt("audited_quantity");

                    String patient_absent_today = obj1.optString("patient_absent_today");
                    String is_controlled = obj1.optString("is_controlled");
                    String pil_url = obj1.optString("pil_url");

                    //  Parsing for meditation
                    JSONObject obj4 = obj1.optJSONObject("medication");
                    String iCompanyID = obj4.optString("iCompanyID");
                    String iControlledDrugSchedule = obj4.optString("iControlledDrugSchedule");
                    String iProductID = obj4.optString("iProductID");
                    String sDisplayName = obj4.optString("sDisplayName");
                    String sDisplayNameTM = obj4.optString("sDisplayNameTM");
                    String sLegacyDisplayName = obj4.optString("sLegacyDisplayName");
                    String sName = obj4.optString("sName");
                    String sNameTM = obj4.optString("sNameTM");
                    String medication_mandatory_instructions = obj4.optString("mandatory_instructions");
                    String medication_detailed_drug_name = obj4.optString("detailed_drug_name");
                    String medication_is_controlled = obj4.optString("is_controlled");

                    MEDICATION medication_object = new MEDICATION(iCompanyID, iControlledDrugSchedule, iProductID, sDisplayName, sDisplayNameTM, sLegacyDisplayName, sName, sNameTM, medication_mandatory_instructions, medication_detailed_drug_name, medication_is_controlled);

                    // Parsing for branded_medication
                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
                    String branded_medication_iCompanyID = obj5.optString("iCompanyID");
                    String branded_medication_iControlledDrugSchedule = obj5.optString("iControlledDrugSchedule");
                    String branded_medication_iProductID = obj5.optString("iProductID");
                    String branded_medication_sDisplayName = obj5.optString("sDisplayName");
                    String branded_medication_sDisplayNameTM = obj5.optString("sDisplayNameTM");
                    String branded_medication_sLegacyDisplayName = obj5.optString("sLegacyDisplayName");
                    String branded_medication_sName = obj5.optString("sName");
                    String branded_medication_sNameTM = obj5.optString("sNameTM");
                    String branded_medication_mandatory_instructions = obj5.optString("mandatory_instructions");
                    String branded_medication_detailed_drug_name = obj5.optString("detailed_drug_name");
                    String branded_medication__is_controlled = obj5.optString("is_controlled");

                    BRANDED_MEDICATION brnd_medi_object = new BRANDED_MEDICATION(branded_medication_iCompanyID, branded_medication_iControlledDrugSchedule, branded_medication_iProductID, branded_medication_sDisplayName, branded_medication_sDisplayNameTM, branded_medication_sLegacyDisplayName, branded_medication_sName, branded_medication_sNameTM, branded_medication_mandatory_instructions, branded_medication_detailed_drug_name, branded_medication__is_controlled);

                    JSONArray array2 = obj1.optJSONArray("time_slot_doses");

                    for (int j = 0; j < array2.length(); j++) {
                        JSONObject obj6 = array2.optJSONObject(j);
                        String time_slot_doses_color = obj6.optString("color");
                        String time_slot_doses_dose = obj6.optString("dose");
                        String time_slot_doses_id = obj6.optString("id");
                        String time_slot_doses_show_as = obj6.optString("show_as");
                        String time_slot_doses_slot_time = obj6.optString("slot_time");
                        //Log.e("slot_time", time_slot_doses_slot_time + "  " + time_slot_doses_color + "  " + time_slot_doses_show_as);

                        TIME_SLOT_DOSES tsd_obj = new TIME_SLOT_DOSES(time_slot_doses_color, time_slot_doses_dose, time_slot_doses_id, time_slot_doses_show_as, time_slot_doses_slot_time);
                        admin_TIME_SLOT_DOSES_objects_list.add(tsd_obj);
                        timedosenamelist.add(time_slot_doses_show_as);
                    }

                    ADMIN_PRESCRIPTION_MODEL admin_pre_model = new ADMIN_PRESCRIPTION_MODEL(id, indications, instructions, is_patch, is_special, non_blistered, prescription_type, quantity_dispensed, special_medication_name, start_date, status, suppress_non_blister_display, mandatory_instructions, admin_Today_Mars_Mar_objects_list, can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name, patch_location, admin_PRES_TIME_SLOT_objects_list, lastadmin_obj, first_check_in_date, dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity, returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity, administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url, medication_object, brnd_medi_object, admin_TIME_SLOT_DOSES_objects_list, false);
                    admin_prescription_modelArrayList.add(admin_pre_model);


                }
                getAdmin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getAdmin() {
//        adminList = new ArrayList<AdminInterFace>();
        new_admin_modelArrayList = new ArrayList<NEW_ADMIN_MODEL>();
        headerlist = new ArrayList<String>();

        //----To Get the HOMELY REMEDY SCETION in ADMIN TAB ----//
        if (new_homely_List.size() > 0) {
            String homely = "";
            for (int l = 0; l < admin_prescription_modelArrayList.size(); l++) {
                try {
                    if (new_homely_List.get(l).getHomely_Today_Mars_Mar_objects_list().size() > 0) {
                        homely = "true";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (homely.equals("true")) {
                NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(true, "Homely Remedies", "000000", "");
                new_admin_modelArrayList.add(nam11);
                headerlist.add("Homely Remedies");
            }

            for (int i = 0; i < new_homely_List.size(); i++) {
                if (new_homely_List.get(i).getHomely_Today_Mars_Mar_objects_list().size() > 0) {
                    New_Homely_Remedy_Model homely_remedy_model5 = new_homely_List.get(i);
                    NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(false, "", "Homely Remedies", "", homely_remedy_model5, null, false, "000000");
                    new_admin_modelArrayList.add(nam11);

                }
            }
        }

        //----To Get the PRN SCETION in ADMIN TAB ----//
        String pnr = "";
        for (int l = 0; l < admin_prescription_modelArrayList.size(); l++) {
            if (admin_prescription_modelArrayList.get(l).getPrescription_type().contains("PRN")) {

//                if (!admin_prescription_modelArrayList.get(l).getDrug_name().contains("WARFARIN") && !admin_prescription_modelArrayList.get(l).getDrug_name().contains("warfarin")) {
                pnr = "true";
//                }
            }
        }
        if (pnr.equals("true")) {

            NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(true, "PRN", "000000", "");
            new_admin_modelArrayList.add(nam11);
            headerlist.add("PRN");
        }

        for (int i = 0; i < admin_prescription_modelArrayList.size(); i++) {
            if (admin_prescription_modelArrayList.get(i).getPrescription_type().contains("PRN")) {

//                if (!admin_prescription_modelArrayList.get(i).getDrug_name().contains("WARFARIN") && !admin_prescription_modelArrayList.get(i).getDrug_name().contains("warfarin")) {

                ADMIN_PRESCRIPTION_MODEL adminPrescriptionModel = admin_prescription_modelArrayList.get(i);
                NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(false, "", "PRN", "", null, adminPrescriptionModel, false, "000000");
                new_admin_modelArrayList.add(nam11);
//                }
            }
        }


//        //----To Get the WARFARIN SCETION in ADMIN TAB ----//
//        String _WARF = "";
//        for (int l = 0; l < admin_prescription_modelArrayList.size(); l++) {
//            if (admin_prescription_modelArrayList.get(l).getDrug_name().contains("WARFARIN") || admin_prescription_modelArrayList.get(l).getDrug_name().contains("warfarin")) {
//                _WARF = "true";
//            }
//
//        }
//        if (_WARF.equals("true")) {

//            NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(true, "WARFARIN", "000000", "");
//            new_admin_modelArrayList.add(nam11);
//            headerlist.add("WARFARIN");
//        }
//
//        for (int i = 0; i < admin_prescription_modelArrayList.size(); i++) {
//
//                if (admin_prescription_modelArrayList.get(i).getDrug_name().contains("WARFARIN") || admin_prescription_modelArrayList.get(i).getDrug_name().contains("warfarin")) {
//
//                    ADMIN_PRESCRIPTION_MODEL adminPrescriptionModel = admin_prescription_modelArrayList.get(i);
//                    NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(false, "", "WARFARIN", "", null, adminPrescriptionModel, false, "000000");
//                    new_admin_modelArrayList.add(nam11);
//                }
//
//        }


        //----To Get the COLORED DROG ROUNDS NAD THIER PRESCRIPTIONS in ADMIN TAB ----//

        for (int loop = 0; loop < timedosenamelist.size(); loop++) {
            for (int x = 0; x < admin_prescription_modelArrayList.size(); x++) {
                if (!admin_prescription_modelArrayList.get(x).getPrescription_type().contains("PRN")) {
                    ArrayList<TIME_SLOT_DOSES> Tsd_objects_list = admin_prescription_modelArrayList.get(x).getTIME_SLOT_DOSES_objects_list();
                    for (int j = 0; j < Tsd_objects_list.size(); j++) {
                        TIME_SLOT_DOSES timeSlotDoses = Tsd_objects_list.get(j);

                        if (timedosenamelist.get(loop).equals(timeSlotDoses.getTime_slot_doses_show_as())) {

                            if (timeSlotDoses.getTime_slot_doses_dose().length() > 0) {

                                if (!headerlist.contains(timeSlotDoses.getTime_slot_doses_show_as())) {

                                    NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(true, timeSlotDoses.getTime_slot_doses_show_as(), timeSlotDoses.getTime_slot_doses_color(), "");
                                    new_admin_modelArrayList.add(nam11);
                                    headerlist.add(timeSlotDoses.getTime_slot_doses_show_as());
                                    Log.e("loop & x & j  ", "loop----> " + loop + "x----> " + x + "j----> " + j);
                                    Log.e("headerlist.add", "--->" + timeSlotDoses.getTime_slot_doses_show_as());
                                }

                                Log.e("SSSSSSSSSSSSS  ", "loop----> " + loop + "x----> " + x + "j----> " + j);
                                ADMIN_PRESCRIPTION_MODEL adminPrescriptionModel = admin_prescription_modelArrayList.get(x);
                                NEW_ADMIN_MODEL nam11 = new NEW_ADMIN_MODEL(false, "", timeSlotDoses.getTime_slot_doses_show_as(), timeSlotDoses.getTime_slot_doses_slot_time(), null, adminPrescriptionModel, false, timeSlotDoses.getTime_slot_doses_color());
                                new_admin_modelArrayList.add(nam11);

                                Log.e("new_admin_modelList.add", "--->" + adminPrescriptionModel.getDetailed_drug_name());

                            }
                        }

                    }


                }
            }
        }

        Log.e("Before", "New_AdminSectionAdapter---->" + new_admin_modelArrayList.size());
        new_admin_adapter_by_pankaj = new New_Admin_Adapter_By_Pankaj(this, new_admin_modelArrayList, remedy_and_doselist, patientId);
        mylist.setAdapter(new_admin_adapter_by_pankaj);

    }


    private class getReturnTabData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&type=returns";
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getReturnTabData url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Response", "getReturnTabData---->" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            new_return_List.clear();

            ArrayList<Today_Mars_Mar_Bean> return_Today_Mars_Mar_objects_list;
            ArrayList<PRESCRIBED_TIME_SLOT> return_PRES_TIME_SLOT_objects_list;
            ArrayList<TIME_SLOT_DOSES> return_TIME_SLOT_DOSES_objects_list;

            try {

                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
//                    ReturnModel returnModel = new ReturnModel();
                    return_Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean>();
                    return_PRES_TIME_SLOT_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT>();
                    return_TIME_SLOT_DOSES_objects_list = new ArrayList<TIME_SLOT_DOSES>();

                    JSONObject obj = array.optJSONObject(i);
                    JSONObject obj1 = obj.optJSONObject("prescription");
                    String id = obj1.optString("id");
                    String indications = obj1.optString("indications");
                    String instructions = obj1.optString("instructions");
                    String is_patch = obj1.optString("is_patch");
                    String is_special = obj1.optString("is_special");
                    String non_blistered = obj1.optString("non_blistered");
                    String prescription_type = obj1.optString("prescription_type");
                    String quantity_dispensed = obj1.optString("quantity_dispensed");
                    String special_medication_name = obj1.optString("special_medication_name");
                    String start_date = obj1.optString("start_date");
                    String status = obj1.optString("status");
                    String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
                    String mandatory_instructions = obj1.optString("mandatory_instructions");

                    //skipping todays_mars data here

                    String can_carry_forward = obj1.optString("can_carry_forward");
                    String front_image_url = obj1.optString("front_image_url");
                    String drug_name = obj1.optString("drug_name");
                    String detailed_drug_name = obj1.optString("detailed_drug_name");
                    String short_drug_name = obj1.optString("short_drug_name");
                    String patch_location = obj1.optString("patch_location");

                    // parsing for prescribed_time_slots

                    JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
                    for (int k = 0; k < array1.length(); k++) {
                        JSONObject obj2 = array1.optJSONObject(k);
                        JSONObject obj3 = obj2.optJSONObject("time_slot_dose");
                        String color = obj3.optString("color");
                        String created_at = obj3.optString("created_at");
                        String deleted_at = obj3.optString("deleted_at");
                        String Maindose = obj3.optString("dose");
                        String time_slot_dose_id = obj3.optString("id");
                        String prescription_id = obj3.optString("prescription_id");
                        String show_as = obj3.optString("show_as");
                        String slot_time = obj3.optString("slot_time");
                        String updated_at = obj3.optString("updated_at");
                        Log.e("slot_time", created_at + "  " + color + "  " + show_as);


                        PRESCRIBED_TIME_SLOT pts_obj = new PRESCRIBED_TIME_SLOT(color, created_at, deleted_at, Maindose, prescription_id, time_slot_dose_id, show_as, slot_time, updated_at);

                        return_PRES_TIME_SLOT_objects_list.add(pts_obj);

                    }

                    LAST_ADMIN_MAR lastadmin_obj3 = null;
                    try {
                        JSONObject obj7 = obj1.optJSONObject("last_admin");
                        JSONObject obj8 = obj7.optJSONObject("mar");

                        String last_admin_created_at = obj8.optString("created_at");
                        String last_admin_deleted_at = obj8.optString("deleted_at");
                        String dose = obj8.optString("dose");
                        String false_reason = obj8.optString("false_reason");
                        String gps_location = obj8.optString("gps_location");
                        String homely_remedy_id = obj8.optString("homely_remedy_id");
                        String homely_remedy_name = obj8.optString("homely_remedy_name");
                        String mar_id = obj8.optString("id");
                        String is_waste = obj8.optString("is_waste");
                        String medication_id = obj8.optString("medication_id");
                        String outcome = obj8.optString("outcome");
                        String patient_id = obj8.optString("patient_id");
                        String prescription_id = obj8.optString("prescription_id");
                        //  String show_as = jsonobj.optString("show_as");
                        String quantity_wasted = obj8.optString("quantity_wasted");
                        String secondary_user_id = obj8.optString("secondary_user_id");
                        String seconday_user_fullname = obj8.optString("seconday_user_fullname");
                        String slot_time = obj8.optString("slot_time");
                        String updated_at = obj8.optString("updated_at");
                        String uid = obj8.optString("uid");
                        String user_fullname = obj8.optString("user_fullname");
                        Log.e("user_fullname", "" + user_fullname);
                        String user_id = obj8.optString("user_id");

                        lastadmin_obj3 = new LAST_ADMIN_MAR(last_admin_created_at, last_admin_deleted_at, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, mar_id, is_waste, medication_id, outcome, prescription_id, patient_id, quantity_wasted, secondary_user_id, seconday_user_fullname, updated_at, slot_time, uid, user_fullname, user_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    String first_check_in_date = obj1.optString("first_check_in_date");
                    int dispensed_quantity = obj1.optInt("dispensed_quantity");
                    int available_quantity = obj1.optInt("available_quantity");
                    int checked_in_quantity = obj1.optInt("checked_in_quantity");
                    int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
                    int returned_quantity = obj1.optInt("returned_quantity");
                    int destroyed_quantity = obj1.optInt("destroyed_quantity");
                    int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
                    int wasted_quantity = obj1.optInt("wasted_quantity");
                    int administered_quantity = obj1.optInt("administered_quantity");
                    int audited_quantity = obj1.optInt("audited_quantity");
                    String patient_absent_today = obj1.optString("patient_absent_today");
                    String is_controlled = obj1.optString("is_controlled");
                    String pil_url = obj1.optString("pil_url");


                    //  Parsing for meditation

                    JSONObject obj4 = obj1.optJSONObject("medication");
                    String iCompanyID = obj4.optString("iCompanyID");
                    String iControlledDrugSchedule = obj4.optString("iControlledDrugSchedule");
                    String iProductID = obj4.optString("iProductID");
                    String sDisplayName = obj4.optString("sDisplayName");
                    String sDisplayNameTM = obj4.optString("sDisplayNameTM");
                    String sLegacyDisplayName = obj4.optString("sLegacyDisplayName");
                    String sName = obj4.optString("sName");
                    String sNameTM = obj4.optString("sNameTM");
                    String medication_mandatory_instructions = obj4.optString("mandatory_instructions");
                    String medication_detailed_drug_name = obj4.optString("detailed_drug_name");
                    String medication_is_controlled = obj4.optString("is_controlled");

                    MEDICATION medication_object3 = new MEDICATION(iCompanyID, iControlledDrugSchedule, iProductID, sDisplayName, sDisplayNameTM, sLegacyDisplayName, sName, sNameTM, medication_mandatory_instructions, medication_detailed_drug_name, medication_is_controlled);

                    // Parsing for branded_medication

                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
                    String branded_medication_iCompanyID = obj5.optString("iCompanyID");
                    String branded_medication_iControlledDrugSchedule = obj5.optString("iControlledDrugSchedule");
                    String branded_medication_iProductID = obj5.optString("iProductID");
                    String branded_medication_sDisplayName = obj5.optString("sDisplayName");
                    String branded_medication_sDisplayNameTM = obj5.optString("sDisplayNameTM");
                    String branded_medication_sLegacyDisplayName = obj5.optString("sLegacyDisplayName");
                    String branded_medication_sName = obj5.optString("sName");
                    String branded_medication_sNameTM = obj5.optString("sNameTM");
                    String branded_medication_mandatory_instructions = obj5.optString("mandatory_instructions");
                    String branded_medication_detailed_drug_name = obj5.optString("detailed_drug_name");
                    String branded_medication__is_controlled = obj5.optString("is_controlled");
                    BRANDED_MEDICATION brnd_medi_object = new BRANDED_MEDICATION(branded_medication_iCompanyID, branded_medication_iControlledDrugSchedule, branded_medication_iProductID, branded_medication_sDisplayName, branded_medication_sDisplayNameTM, branded_medication_sLegacyDisplayName, branded_medication_sName, branded_medication_sNameTM, branded_medication_mandatory_instructions, branded_medication_detailed_drug_name, branded_medication__is_controlled);

                    // Parsing for time_slot_doses

                    JSONArray array2 = obj1.optJSONArray("time_slot_doses");
                    for (int j = 0; j < array2.length(); j++) {
                        JSONObject obj6 = array2.optJSONObject(j);
                        String time_slot_doses_color = obj6.optString("color");
                        String time_slot_doses_dose = obj6.optString("dose");
                        String time_slot_doses_id = obj6.optString("id");
                        String time_slot_doses_show_as = obj6.optString("show_as");
                        String time_slot_doses_slot_time = obj6.optString("slot_time");

                        TIME_SLOT_DOSES tsd_obj = new TIME_SLOT_DOSES(time_slot_doses_color, time_slot_doses_dose, time_slot_doses_id, time_slot_doses_show_as, time_slot_doses_slot_time);
                        return_TIME_SLOT_DOSES_objects_list.add(tsd_obj);

                    }

//                    New_Return_Model new_checkIn_model3 = new New_Return_Model(id, indications, instructions, is_patch, is_special, non_blistered, prescription_type, quantity_dispensed, special_medication_name, start_date, status, suppress_non_blister_display, mandatory_instructions, return_Today_Mars_Mar_objects_list, can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name, patch_location, return_PRES_TIME_SLOT_objects_list, lastadmin_obj3, first_check_in_date, dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity, returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity, administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url, medication_object3, brnd_medi_object, return_TIME_SLOT_DOSES_objects_list, false, "");
//                    new_return_List.add(new_checkIn_model3);

                    PRESCRIPTION_COMMON_Model new_return_model = new PRESCRIPTION_COMMON_Model(id,
                            indications, instructions, is_patch, is_special, non_blistered,
                            prescription_type, quantity_dispensed, special_medication_name,
                            start_date, status, suppress_non_blister_display, mandatory_instructions,
                            can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
                            patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
                            dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
                            returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
                            administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
                            medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", cycle_type, patientId);
                    new_return_List.add(new_return_model);

                }

                if (txtReturn.isSelected()) {
                    new_return_Adpater = new New_Return_Adpater(PatientDetail123.this, new_return_List);
                    mylist.setAdapter(new_return_Adpater);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private class getHomelyRemedyData extends AsyncTask<String, String, String> {
        Dialog dlg;
        String from;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        getHomelyRemedyData(String from) {
            this.from = from;
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
//                String weburl = WebServiceDetails.HOMELY_REMEDY + patientId + "/homely_remedies/?authentication_token=" + global_auth_token + "&note_type=General";
                String weburl = baseurl_value + patientId + "/homely_remedies/?authentication_token=" + global_auth_token + "&note_type=General";
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getHomelyRemedyData url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        App_Constants.showAlertDialog("Alert", "No peer certificate", PatientDetail123.this, false);
                    }
                });
//                App_Constants.showAlertDialog("Alert", "No peer certificate", contxt, false);
//                No peer certificate
            }
            Log.e("getHomelyRemedyData", "response--->" + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }

            remedy_and_doselist.clear();
            new_homely_List.clear();

            try {
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {

                    ArrayList<Homely_Today_Mars_Mar_Bean> homely_Today_Mars_Mar_objects_list;
                    homely_Today_Mars_Mar_objects_list = new ArrayList<Homely_Today_Mars_Mar_Bean>();

                    JSONObject obj = array.optJSONObject(i);
                    JSONObject obj1 = obj.optJSONObject("homely_remedy");
                    String care_home_id = obj1.optString("care_home_id");
                    String created_at = obj1.optString("created_at");
                    String deleted_at = obj1.optString("deleted_at");
                    String id = obj1.optString("id");
                    String mandatory_warnings = obj1.optString("mandatory_warnings");
                    String name = obj1.optString("name");
                    String updated_at = obj1.optString("updated_at");

                    JSONArray array1 = obj1.optJSONArray("todays_mars");

                    String ddoss = "";

                    for (int j = 0; j < array1.length(); j++) {

                        JSONObject obj2 = array1.optJSONObject(j);
                        JSONObject obj3 = obj2.optJSONObject("mar");
                        String created_at_mar = obj3.optString("created_at");
                        String deleted_at_mar = obj3.optString("deleted_at");
                        String dose = obj3.optString("dose");
                        String false_reason = obj3.optString("false_reason");
                        String gps_location = obj3.optString("gps_location");
                        String homely_remedy_id = obj3.optString("homely_remedy_id");
                        String homely_remedy_name = obj3.optString("homely_remedy_name");
                        String id_mar = obj3.optString("id");
                        String is_waste = obj3.optString("is_waste");
                        String medication_id = obj3.optString("medication_id");
                        String outcome = obj3.optString("outcome");
                        String patient_id = obj3.optString("patient_id");
                        String prescription_id = obj3.optString("prescription_id");
                        String quantity_wasted = obj3.optString("quantity_wasted");
                        String secondary_user_id = obj3.optString("secondary_user_id");
                        String seconday_user_fullname = obj3.optString("seconday_user_fullname");
                        String slot_time = obj3.optString("slot_time");
                        String uid = obj3.optString("uid");
                        String updated_at_mar = obj3.optString("updated_at");
                        String user_fullname = obj3.optString("user_fullname");
                        String user_id = obj3.optString("user_id");


                        Homely_Today_Mars_Mar_Bean hm = new Homely_Today_Mars_Mar_Bean(created_at_mar, deleted_at_mar, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, id_mar, is_waste, medication_id, outcome, patient_id, prescription_id, quantity_wasted, secondary_user_id, seconday_user_fullname, slot_time, uid, updated_at_mar, user_fullname, user_id);
                        homely_Today_Mars_Mar_objects_list.add(hm);

                        if (ddoss.isEmpty()) {

                            String a1 = "";
                            if (dose.isEmpty()) {
                                a1 = "REASON: " + false_reason + " ";

                            } else {
                                a1 = "DOSE: " + dose + " ";
                            }

                            ddoss = "" + a1 + "DATE: " +
                                    Date_utility.getDate(created_at_mar) + " " + "TIME: " +
                                    Date_utility.getTime(created_at_mar) + " " + "USER: " + user_fullname;
                        } else {

                            String a1 = "";
                            if (dose.isEmpty()) {
                                a1 = "REASON: " + false_reason + " ";
                            } else {
                                a1 = "DOSE: " + dose + " ";
                            }

                            ddoss = ddoss + "\n" + a1 + "DATE: " +
                                    Date_utility.getDate(created_at_mar) + " " + "TIME: " +
                                    Date_utility.getTime(created_at_mar) + " " + "USER: " + user_fullname;
                        }

                    }

                    if (!ddoss.isEmpty()) {
                        Remedy_Dose_Bean r1 = new Remedy_Dose_Bean(name, ddoss);
                        remedy_and_doselist.add(r1);
                    }

                    New_Homely_Remedy_Model nhr_model = new New_Homely_Remedy_Model(care_home_id, created_at, deleted_at, id, mandatory_warnings, name, updated_at, homely_Today_Mars_Mar_objects_list, false, "" + 0);
                    new_homely_List.add(nhr_model);
                }

                if (!from.equals("1")) {
                    new_homely_Adapter = new New_Homely_Adapter(PatientDetail123.this, new_homely_List);
                    mylist.setAdapter(new_homely_Adapter);
                }

                if (from.equals("1")) {
                    new getAdminPresData().execute();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class getCheckin extends AsyncTask<String, Void, String> {
        Dialog dlg;
        String cycle_type = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            cycle_type = params[0];
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=" + params[0] + "&type=check_ins";
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getCheckin url---> " + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Checkindata", "Response--->" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }

            parsing_checkin_json(cycle_type, result);


        }
    }

    private void parsing_checkin_json(String cycle_type, String result) {


        //            checklist.clear();
        newchecklist.clear();

//            ArrayList<Today_Mars_Mar_Bean> checkin_Today_Mars_Mar_objects_list;
//            ArrayList<PRESCRIBED_TIME_SLOT> checkin_PRES_TIME_SLOT_objects_list;
//            ArrayList<TIME_SLOT_DOSES> checkin_TIME_SLOT_DOSES_objects_list;

        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {

//                    checkin_Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean>();
//                    checkin_PRES_TIME_SLOT_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT>();
//                    checkin_TIME_SLOT_DOSES_objects_list = new ArrayList<TIME_SLOT_DOSES>();

                JSONObject obj = array.optJSONObject(i);
                JSONObject obj1 = obj.optJSONObject("prescription");

                int id = obj1.optInt("id");
                String indications = obj1.optString("indications");
                String instructions = obj1.optString("instructions");
                String is_patch = obj1.optString("is_patch");
                String is_special = obj1.optString("is_special");
                String non_blistered = obj1.optString("non_blistered");
                String prescription_type = obj1.optString("prescription_type");
                String quantity_dispensed = obj1.optString("quantity_dispensed");
                String special_medication_name = obj1.optString("special_medication_name");
                String start_date = obj1.optString("start_date");
                String status = obj1.optString("status");
                String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
                String mandatory_instructions = obj1.optString("mandatory_instructions");

                //skipping todays_mars data here

                String can_carry_forward = obj1.optString("can_carry_forward");
                String front_image_url = obj1.optString("front_image_url");
                String drug_name = obj1.optString("drug_name");
                String detailed_drug_name = obj1.optString("detailed_drug_name");
                String short_drug_name = obj1.optString("short_drug_name");
                String patch_location = obj1.optString("patch_location");

                // parsing for prescribed_time_slots

                JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
                String prescribed_time_slots_array = array1.toString();
//                    for (int k = 0; k < array1.length(); k++) {
//
//                        JSONObject obj2 = array1.optJSONObject(k);
//                        JSONObject obj3 = obj2.optJSONObject("time_slot_dose");
//                        String color = obj3.optString("color");
//                        String created_at = obj3.optString("created_at");
//                        String deleted_at = obj3.optString("deleted_at");
//                        String Maindose = obj3.optString("dose");
//                        String time_slot_dose_id = obj3.optString("id");
//                        String prescription_id = obj3.optString("prescription_id");
//                        String show_as = obj3.optString("show_as");
//                        String slot_time = obj3.optString("slot_time");
//                        String updated_at = obj3.optString("updated_at");
//
//                        PRESCRIBED_TIME_SLOT pts_obj = new PRESCRIBED_TIME_SLOT(color, created_at, deleted_at, Maindose, prescription_id, time_slot_dose_id, show_as, slot_time, updated_at);
//                        checkin_PRES_TIME_SLOT_objects_list.add(pts_obj);
//                        Log.e("slot_time :-", created_at + "  " + Maindose + "  " + show_as);
//
//                    }

                // parsing for last_admin

//                    LAST_ADMIN_MAR lastadmin_obj = null;
                String lst_admin_string_obj = "";

                try {
                    JSONObject jsonObject = obj1.optJSONObject("last_admin");
                    lst_admin_string_obj = jsonObject.toString();
//                        JSONObject jsonobj = jsonObject.optJSONObject("mar");
//                        String last_admin_created_at = jsonobj.optString("created_at");
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
//                        String slot_time = jsonobj.optString("slot_time");
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

                String first_check_in_date = obj1.optString("first_check_in_date");
                int dispensed_quantity = obj1.optInt("dispensed_quantity");
                int available_quantity = obj1.optInt("available_quantity");
                int checked_in_quantity = obj1.optInt("checked_in_quantity");
                int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
                int returned_quantity = obj1.optInt("returned_quantity");
                int destroyed_quantity = obj1.optInt("destroyed_quantity");
                int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
                int wasted_quantity = obj1.optInt("wasted_quantity");
                int administered_quantity = obj1.optInt("administered_quantity");
                int audited_quantity = obj1.optInt("audited_quantity");
                String patient_absent_today = obj1.optString("patient_absent_today");
                String is_controlled = obj1.optString("is_controlled");
                String pil_url = obj1.optString("pil_url");

                //  Parsing for meditation

                JSONObject obj4 = obj1.optJSONObject("medication");
                String medication_string_obj = obj4.toString();
//                    String iCompanyID = obj4.optString("iCompanyID");
//                    String iControlledDrugSchedule = obj4.optString("iControlledDrugSchedule");
//                    String iProductID = obj4.optString("iProductID");
//                    String sDisplayName = obj4.optString("sDisplayName");
//                    String sDisplayNameTM = obj4.optString("sDisplayNameTM");
//                    String sLegacyDisplayName = obj4.optString("sLegacyDisplayName");
//                    String sName = obj4.optString("sName");
//                    String sNameTM = obj4.optString("sNameTM");
//                    String medication_mandatory_instructions = obj4.optString("mandatory_instructions");
//                    String medication_detailed_drug_name = obj4.optString("detailed_drug_name");
//                    String medication_is_controlled = obj4.optString("is_controlled");
//                    MEDICATION medication_object = new MEDICATION(iCompanyID, iControlledDrugSchedule, iProductID, sDisplayName, sDisplayNameTM, sLegacyDisplayName, sName, sNameTM, medication_mandatory_instructions, medication_detailed_drug_name, medication_is_controlled);

                // Parsing for branded_medication

                JSONObject obj5 = obj1.optJSONObject("branded_medication");
                String branded_medication_string_obj = obj5.toString();
//                    String branded_medication_iCompanyID = obj5.optString("iCompanyID");
//                    String branded_medication_iControlledDrugSchedule = obj5.optString("iControlledDrugSchedule");
//                    String branded_medication_iProductID = obj5.optString("iProductID");
//                    String branded_medication_sDisplayName = obj5.optString("sDisplayName");
//                    String branded_medication_sDisplayNameTM = obj5.optString("sDisplayNameTM");
//                    String branded_medication_sLegacyDisplayName = obj5.optString("sLegacyDisplayName");
//                    String branded_medication_sName = obj5.optString("sName");
//                    String branded_medication_sNameTM = obj5.optString("sNameTM");
//                    String branded_medication_mandatory_instructions = obj5.optString("mandatory_instructions");
//                    String branded_medication_detailed_drug_name = obj5.optString("detailed_drug_name");
//                    String branded_medication__is_controlled = obj5.optString("is_controlled");
//                    BRANDED_MEDICATION brnd_medi_object = new BRANDED_MEDICATION(branded_medication_iCompanyID, branded_medication_iControlledDrugSchedule, branded_medication_iProductID, branded_medication_sDisplayName, branded_medication_sDisplayNameTM, branded_medication_sLegacyDisplayName, branded_medication_sName, branded_medication_sNameTM, branded_medication_mandatory_instructions, branded_medication_detailed_drug_name, branded_medication__is_controlled);

                // Parsing for time_slot_doses

                JSONArray array2 = obj1.optJSONArray("time_slot_doses");
                String TIME_SLOT_DOSES_array = array2.toString();
//                    for (int j = 0; j < array2.length(); j++) {
//                        JSONObject obj6 = array2.optJSONObject(j);
//                        String time_slot_doses_color = obj6.optString("color");
//                        String time_slot_doses_dose = obj6.optString("dose");
//                        String time_slot_doses_id = obj6.optString("id");
//                        String time_slot_doses_show_as = obj6.optString("show_as");
//                        String time_slot_doses_slot_time = obj6.optString("slot_time");
//                        Log.e("slot_time", time_slot_doses_slot_time + "  " + time_slot_doses_color + "  " + time_slot_doses_show_as);
//                        TIME_SLOT_DOSES tsd_obj = new TIME_SLOT_DOSES(time_slot_doses_color, time_slot_doses_dose, time_slot_doses_id, time_slot_doses_show_as, time_slot_doses_slot_time);
//                        checkin_TIME_SLOT_DOSES_objects_list.add(tsd_obj);
//                    }
//                    New_CheckIn_Model new_checkIn_model = new New_CheckIn_Model(id, indications, instructions, is_patch, is_special, non_blistered, prescription_type, quantity_dispensed, special_medication_name, start_date, status, suppress_non_blister_display, mandatory_instructions, checkin_Today_Mars_Mar_objects_list, can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name, patch_location, checkin_PRES_TIME_SLOT_objects_list, lastadmin_obj, first_check_in_date, dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity, returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity, administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url, medication_object, brnd_medi_object, checkin_TIME_SLOT_DOSES_objects_list, false);
//                    newchecklist.add(new_checkIn_model);

                PRESCRIPTION_COMMON_Model new_checkIn_model = new PRESCRIPTION_COMMON_Model(id,
                        indications, instructions, is_patch, is_special, non_blistered,
                        prescription_type, quantity_dispensed, special_medication_name,
                        start_date, status, suppress_non_blister_display, mandatory_instructions,
                        can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
                        patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
                        dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
                        returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
                        administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
                        medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", cycle_type, patientId);
                newchecklist.add(new_checkIn_model);

//                    mydbobj.addAllFeed(PatientDetail.this, newchecklist);

            }
            mydbobj.addAllFeed(PatientDetail123.this, newchecklist);
            new_checkinAdapter = new New_CheckinAdapter(PatientDetail123.this, newchecklist);
            mylist.setAdapter(new_checkinAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private class getMedsData extends AsyncTask<String, String, String> {

        public Dialog dlg;
        String meds_cycle_type = "";

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            meds_cycle_type = params[0];
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/?authentication_token=" + global_auth_token + "&filter=" + params[0] + "&view=meds-list";
                result = jsonParser.getjson_method(weburl);
                Log.e("httpGet", "getMedsData url--->" + weburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("Response", "getMedsData----->" + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();

            Log.e("dlg.isShowing()", "--->" + dlg.isShowing());
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            newmedsList.clear();
            String taken = "";

//            ArrayList<Today_Mars_Mar_Bean> meds_Today_Mars_Mar_objects_list;
//            ArrayList<PRESCRIBED_TIME_SLOT> meds_PRES_TIME_SLOT_objects_list;
//            ArrayList<TIME_SLOT_DOSES> meds_TIME_SLOT_DOSES_objects_list;

            try {
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {

//                                      meds_Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean>();
//                    meds_PRES_TIME_SLOT_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT>();
//                    meds_TIME_SLOT_DOSES_objects_list = new ArrayList<TIME_SLOT_DOSES>();

                    JSONObject obj = array.optJSONObject(i);
                    JSONObject obj1 = obj.optJSONObject("prescription");
                    int id = obj1.optInt("id");
                    String indications = obj1.optString("indications");
                    String instructions = obj1.optString("instructions");
                    String is_patch = obj1.optString("is_patch");
                    String is_special = obj1.optString("is_special");
                    String non_blistered = obj1.optString("non_blistered");
                    String prescription_type = obj1.optString("prescription_type");
                    String quantity_dispensed = obj1.optString("quantity_dispensed");
                    String special_medication_name = obj1.optString("special_medication_name");
                    String start_date = obj1.optString("start_date");
                    String status = obj1.optString("status");
                    String suppress_non_blister_display = obj1.optString("suppress_non_blister_display");
                    String mandatory_instructions = obj1.optString("mandatory_instructions");

                    //skipping todays_mars data here

                    String can_carry_forward = obj1.optString("can_carry_forward");
                    String front_image_url = obj1.optString("front_image_url");
                    String drug_name = obj1.optString("drug_name");
                    String detailed_drug_name = obj1.optString("detailed_drug_name");
                    String short_drug_name = obj1.optString("short_drug_name");
                    String patch_location = obj1.optString("patch_location");

                    // parsing for prescribed_time_slots

                    JSONArray array1 = obj1.optJSONArray("prescribed_time_slots");
                    String prescribed_time_slots_array = array1.toString();
//                    for (int k = 0; k < array1.length(); k++) {
//                        JSONObject obj2 = array1.optJSONObject(k);
//                        JSONObject obj3 = obj2.optJSONObject("time_slot_dose");
//                        String color = obj3.optString("color");
//                        String created_at = obj3.optString("created_at");
//                        String deleted_at = obj3.optString("deleted_at");
//                        String Maindose = obj3.optString("dose");
//                        String time_slot_dose_id = obj3.optString("id");
//                        String prescription_id = obj3.optString("prescription_id");
//                        String show_as = obj3.optString("show_as");
//                        String slot_time = obj3.optString("slot_time");
//                        String updated_at = obj3.optString("updated_at");
//
//                        PRESCRIBED_TIME_SLOT pts_obj1 = new PRESCRIBED_TIME_SLOT(color, created_at, deleted_at, Maindose, prescription_id, time_slot_dose_id, show_as, slot_time, updated_at);
//                        meds_PRES_TIME_SLOT_objects_list.add(pts_obj1);
//
//                        Log.e("slot_time", created_at + "  " + color + "  " + show_as);
//                    }
//                    String last_admin = obj1.optString("last_admin");

//                    LAST_ADMIN_MAR lastadmin_obj1 = null;
                    String lst_admin_string_obj = "";
                    try {
                        JSONObject jsonObject = obj1.optJSONObject("last_admin");
                        lst_admin_string_obj = jsonObject.toString();
//                        JSONObject jsonobj = jsonObject.optJSONObject("mar");
//                        String last_admin_created_at = jsonobj.optString("created_at");
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
//                        String slot_time = jsonobj.optString("slot_time");
//                        String updated_at = jsonobj.optString("updated_at");
//                        String uid = jsonobj.optString("uid");
//                        String user_fullname = jsonobj.optString("user_fullname");
//                        Log.e("user_fullname", "" + user_fullname);
//                        String user_id = jsonobj.optString("user_id");
//
//                        lastadmin_obj1 = new LAST_ADMIN_MAR(last_admin_created_at, last_admin_deleted_at, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, mar_id, is_waste, medication_id, outcome, prescription_id, patient_id, quantity_wasted, secondary_user_id, seconday_user_fullname, updated_at, slot_time, uid, user_fullname, user_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String first_check_in_date = obj1.optString("first_check_in_date");
                    int dispensed_quantity = obj1.optInt("dispensed_quantity");
                    int available_quantity = obj1.optInt("available_quantity");
                    int checked_in_quantity = obj1.optInt("checked_in_quantity");
                    int brought_forward_quantity = obj1.optInt("brought_forward_quantity");
                    int returned_quantity = obj1.optInt("returned_quantity");
                    int destroyed_quantity = obj1.optInt("destroyed_quantity");
                    int carried_forward_quantity = obj1.optInt("carried_forward_quantity");
                    int wasted_quantity = obj1.optInt("wasted_quantity");
                    int administered_quantity = obj1.optInt("administered_quantity");
                    int audited_quantity = obj1.optInt("audited_quantity");
                    String patient_absent_today = obj1.optString("patient_absent_today");
                    String is_controlled = obj1.optString("is_controlled");
                    String pil_url = obj1.optString("pil_url");

                    //  Parsing for meditation

                    JSONObject obj4 = obj1.optJSONObject("medication");
                    String medication_string_obj = obj4.toString();
//                    String iCompanyID = obj4.optString("iCompanyID");
//                    String iControlledDrugSchedule = obj4.optString("iControlledDrugSchedule");
//                    String iProductID = obj4.optString("iProductID");
//                    String sDisplayName = obj4.optString("sDisplayName");
//                    String sDisplayNameTM = obj4.optString("sDisplayNameTM");
//                    String sLegacyDisplayName = obj4.optString("sLegacyDisplayName");
//                    String sName = obj4.optString("sName");
//                    String sNameTM = obj4.optString("sNameTM");
//                    String medication_mandatory_instructions = obj4.optString("mandatory_instructions");
//                    String medication_detailed_drug_name = obj4.optString("detailed_drug_name");
//                    String medication_is_controlled = obj4.optString("is_controlled");
//                    MEDICATION medication_object1 = new MEDICATION(iCompanyID, iControlledDrugSchedule, iProductID, sDisplayName, sDisplayNameTM, sLegacyDisplayName, sName, sNameTM, medication_mandatory_instructions, medication_detailed_drug_name, medication_is_controlled);

                    // Parsing for branded_medication

                    JSONObject obj5 = obj1.optJSONObject("branded_medication");
                    String branded_medication_string_obj = obj5.toString();
//                    String branded_medication_iCompanyID = obj5.optString("iCompanyID");
//                    String branded_medication_iControlledDrugSchedule = obj5.optString("iControlledDrugSchedule");
//                    String branded_medication_iProductID = obj5.optString("iProductID");
//                    String branded_medication_sDisplayName = obj5.optString("sDisplayName");
//                    String branded_medication_sDisplayNameTM = obj5.optString("sDisplayNameTM");
//                    String branded_medication_sLegacyDisplayName = obj5.optString("sLegacyDisplayName");
//                    String branded_medication_sName = obj5.optString("sName");
//                    String branded_medication_sNameTM = obj5.optString("sNameTM");
//                    String branded_medication_mandatory_instructions = obj5.optString("mandatory_instructions");
//                    String branded_medication_detailed_drug_name = obj5.optString("detailed_drug_name");
//                    String branded_medication__is_controlled = obj5.optString("is_controlled");
//                    BRANDED_MEDICATION brnd_medi_object1 = new BRANDED_MEDICATION(branded_medication_iCompanyID, branded_medication_iControlledDrugSchedule, branded_medication_iProductID, branded_medication_sDisplayName, branded_medication_sDisplayNameTM, branded_medication_sLegacyDisplayName, branded_medication_sName, branded_medication_sNameTM, branded_medication_mandatory_instructions, branded_medication_detailed_drug_name, branded_medication__is_controlled);

                    // Parsing for time_slot_doses

                    JSONArray array2 = obj1.optJSONArray("time_slot_doses");
                    String TIME_SLOT_DOSES_array = array2.toString();
//                    for (int j = 0; j < array2.length(); j++) {
//                        JSONObject obj6 = array2.optJSONObject(j);
//                        String time_slot_doses_color = obj6.optString("color");
//                        String time_slot_doses_dose = obj6.optString("dose");
//                        String time_slot_doses_id = obj6.optString("id");
//                        String time_slot_doses_show_as = obj6.optString("show_as");
//                        String time_slot_doses_slot_time = obj6.optString("slot_time");
//                        //Log.e("slot_time", time_slot_doses_slot_time + "  " + time_slot_doses_color + "  " + time_slot_doses_show_as);
//                        TIME_SLOT_DOSES tsd_obj = new TIME_SLOT_DOSES(time_slot_doses_color, time_slot_doses_dose, time_slot_doses_id, time_slot_doses_show_as, time_slot_doses_slot_time);
//                        meds_TIME_SLOT_DOSES_objects_list.add(tsd_obj);
//
//                    }

//                    New_CheckIn_Model new_checkIn_model1 = new New_CheckIn_Model(id, indications, instructions, is_patch, is_special, non_blistered, prescription_type, quantity_dispensed, special_medication_name, start_date, status, suppress_non_blister_display, mandatory_instructions, meds_Today_Mars_Mar_objects_list, can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name, patch_location, meds_PRES_TIME_SLOT_objects_list, lastadmin_obj1, first_check_in_date, dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity, returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity, administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url, medication_object1, brnd_medi_object1, meds_TIME_SLOT_DOSES_objects_list, false);
//                    newmedsList.add(new_checkIn_model1);

                    PRESCRIPTION_COMMON_Model new_meds_model = new PRESCRIPTION_COMMON_Model(id,
                            indications, instructions, is_patch, is_special, non_blistered,
                            prescription_type, quantity_dispensed, special_medication_name,
                            start_date, status, suppress_non_blister_display, mandatory_instructions,
                            can_carry_forward, front_image_url, drug_name, detailed_drug_name, short_drug_name,
                            patch_location, prescribed_time_slots_array, lst_admin_string_obj, first_check_in_date,
                            dispensed_quantity, available_quantity, checked_in_quantity, brought_forward_quantity,
                            returned_quantity, destroyed_quantity, carried_forward_quantity, wasted_quantity,
                            administered_quantity, audited_quantity, patient_absent_today, is_controlled, pil_url,
                            medication_string_obj, branded_medication_string_obj, TIME_SLOT_DOSES_array, 0, "false", meds_cycle_type, patientId);
                    newmedsList.add(new_meds_model);

                }

                if (tvmeds.isSelected()) {
                    mydbobj.addAllFeed(PatientDetail123.this, newmedsList);
                    newmedsAdapter = new NewMedsAdapter(PatientDetail123.this, newmedsList);
                    mylist.setAdapter(newmedsAdapter);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

//    private String convertStreamToString(InputStream is) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append('\n');
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }

    //------------------------------------------------Save Api---------------------------------------------//

    private class saveHomelyRemedytData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }


        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/homely_remedies/" + params[0] + "/create_mar";
                Log.e("httpPost", "saveHomelyRemedytData url--->" + weburl);
                json.put("authentication_token", "" + global_auth_token);
                String newdate = "";
                try {
                    String date = date_utility.getMeasuredate();
                    newdate = date_utility.Patient_detail_getDate(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                json1.put("slot_time", newdate);
                json1.put("outcome", "true");
                json1.put("dose", params[1]);
                json.put("mar", json1);
                Log.e("saveHomelyRemedytData", "post data --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("savehomelyremedy", "saveHomelyRemedytData  Response----> " + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    getHomelyData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    */
/*****************************************************************//*

    private class saveMeasureData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/measurements";
                Log.e("httpPost", "saveMeasureData url--->" + weburl);
                json.put("authentication_token", "" + global_auth_token);
                json1.put("measurement_type", params[0]);
                json1.put("value", params[1]);
                json.put("measurement", json1);
                Log.e("saveMeasureData", "post data --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("save measure ", "saveMeasureData----Response--->" + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }

            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    getMeasure();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    */
/*****************************************************************//*


    private class Update_sugar_measurement_Async extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/notes";
                Log.e("HttpPost ", "Update_sugar_ weburl --->" + weburl);
                json.put("authentication_token", "" + global_auth_token);
                JSONObject notedata = new JSONObject();
                try {
                    notedata.put("prescription_id", "0");
                    notedata.put("note_type", "BS_Site");
                    notedata.put("subject", "Blood Sugar Test Site");
                    notedata.put("content", params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                json.put("note", notedata);
                Log.e("Update_sugar_", "post data --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());
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
            Log.e("Update_sugar ", " -------Response------->" + result);

            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    getMeasure();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    */
/*****************************************************************//*


    private class CreateNote_Async extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/notes";
                Log.e("HttpPost ", "CREATE_GENERAL_NOTE_ weburl --->" + weburl);
                json.put("authentication_token", "" + global_auth_token);
                JSONObject notedata = new JSONObject();
                try {
                    notedata.put("prescription_id", "0");
                    notedata.put("note_type", "General");
                    notedata.put("subject", params[1]);
                    notedata.put("content", params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                json.put("note", notedata);
                Log.e("CREATE_GENERAL_NOTE_", "post data --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());

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

            Log.e("CREATE_GENERAL_NOTE_ ", " ------Response-------->" + result);
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    getNotes();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    */
/*****************************************************************//*

    private class saveCheckInData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/" + params[0] + "/check_in";
                Log.e("HttpPost ", "saveCheckInData weburl --->" + weburl);
                json.put("authentication_token", "" + global_auth_token);
                json.put("check_in", params[1]);
                json.put("secondary_user_id", params[2]);

                Log.e("saveCheckInData", "postdata --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());
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
            Log.e("save checkin ", "save checkin --------------" + result);

            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    Log.e("save measure result ", "inside save measure result ------------------------- " + result);
                    getCheckInData();
                } else {
                    open_alert("for_CheckIn");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    */
/*****************************************************************//*

    private void open_alert(final String from_val) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PatientDetail123.this);
        // set title
        alertDialogBuilder.setTitle("Alert");

        String msg = "";
        if (from_val.equalsIgnoreCase("for_CheckIn")) {
            msg = "You cannot check in more than has been dispensed by the pharmacy";
        } else if (from_val.equalsIgnoreCase("for_Return")) {
            msg = "Unprocessable Entity";
        }

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("from_val ", "----->" + from_val);
                        if (from_val.equalsIgnoreCase("for_CheckIn")) {
                            getCheckInData();
                        } else if (from_val.equalsIgnoreCase("for_Return")) {
                            getReturnData();
                        }
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

    */
/**********************************************************************************************************//*

    private class saveAdminData extends AsyncTask<String, String, String> {

        Dialog dlg;
        NEW_ADMIN_MODEL admin_model_to_save_on_server;

        public saveAdminData(NEW_ADMIN_MODEL nd1) {
            admin_model_to_save_on_server = nd1;
        }

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            JSONObject json1 = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/" + admin_model_to_save_on_server.getPrecription_ID() + "/mars";
                Log.e("HttpPost ", "saveAdminData weburl --->" + weburl);
                json.put("authentication_token", "" + global_auth_token);
                json1.put("slot_time", "" + admin_model_to_save_on_server.getSlot_time());
                json1.put("outcome", admin_model_to_save_on_server.getOutcome());
                json1.put("false_reason", admin_model_to_save_on_server.getReason());
                json1.put("dose", admin_model_to_save_on_server.getDose());
                json1.put("secondary_user_id", params[0]);
                json1.put("is_waste", admin_model_to_save_on_server.getIs_waste());
                json1.put("quantity_wasted", admin_model_to_save_on_server.getQuantity_wasted());
                json1.put("uid", admin_model_to_save_on_server.getUid());
                json1.put("notes_attributes", admin_model_to_save_on_server.getNotesattr());
                json.put("mar", json1);

                Log.e("saveAdminData ", "post data  --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());

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
            Log.e("saveAdminData result ", "saveAdminData result ----------> " + result);
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
//                    getAdminData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (progDialog != null) {
//            if (progDialog.isShowing()) {
//                progDialog.dismiss();
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (progDialog != null) {
//            if (progDialog.isShowing()) {
//                progDialog.dismiss();
//            }
//        }
    }

    private class getBSSiteData extends AsyncTask<String, Void, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
//                String weburl = WebServiceDetails.BSSITE_DATA + patientId + "/notes?authentication_token="
//                        + global_auth_token + "&note_type=BS_Site&limit=1";
                String weburl = baseurl_value + patientId + "/notes?authentication_token="
                        + global_auth_token + "&note_type=BS_Site&limit=1";
                Log.e("httpGet", "getBSSiteData url ---> " + weburl);
                result = jsonParser.getjson_method(weburl);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("getBSSiteData result ", "getBSSiteData result ----------> " + result);
//            progDialog.dismiss();
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {

                dlg.dismiss();
            }
            bsSiteList.clear();
            try {
                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.optJSONObject(i);
                    JSONObject obj1 = obj.optJSONObject("note");
                    String content = obj1.optString("content");
                    String created_at = obj1.optString("created_at");
                    String deleted_at = obj1.optString("deleted_at");
                    String id = obj1.optString("id");
                    String mar_id = obj1.optString("mar_id");
                    String note_date = obj1.optString("note_date");
                    String note_type = obj1.optString("note_type");
                    String patient_id = obj1.optString("patient_id");
                    String prescription_id = obj1.optString("prescription_id");
                    String subject = obj1.optString("subject");
                    String user_id = obj1.optString("user_id");
                    String updated_at = obj1.optString("updated_at");
                    Log.e("content", "" + content);
                    JSONObject obj2 = obj1.optJSONObject("user");
                    JSONObject obj3 = obj2.optJSONObject("user");

                    String can_manage_users = obj3.optString("can_manage_users");
                    String care_home_id = obj3.optString("care_home_id");
                    String email = obj3.optString("email");
                    String fullname = obj3.optString("fullname");
                    String is_active = obj3.optString("is_active");
                    String username = obj3.optString("username");

//                    BsSiteModel bs = new BsSiteModel(content, created_at, deleted_at, id, mar_id, note_date, note_type, patient_id, prescription_id, subject, updated_at, user_id, can_manage_users, email, care_home_id, fullname, is_active, username);
                    NotesModel notesModel = new NotesModel(id, content, created_at, deleted_at, mar_id, note_date, note_type,
                            patient_id, prescription_id, subject, updated_at, user_id, can_manage_users, care_home_id, email, fullname, is_active, username);

                    bsSiteList.add(notesModel);
                }

                mydbobj.insertNotesData(bsSiteList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    */
/********************************************************************************//*

    private class saveReturnData extends AsyncTask<String, String, String> {
        Dialog dlg;

        protected void onPreExecute() {
            super.onPreExecute();
//            progDialog.show();
            dlg = app_constants.dialog(PatientDetail123.this, "Loading");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject json = new JSONObject();
            String result = "";
            try {
                String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");
                baseurl_value = baseurl_value + "/patients/";
                String weburl = baseurl_value + patientId + "/prescriptions/" + params[0] + "/" + params[2];
                Log.e("HttpPost ", "saveReturnData weburl --->" + weburl);
                json.put("authentication_token", global_auth_token);
                json.put(params[3], params[1]);
                Log.e("saveReturnData", "postdata --->" + json.toString());
                result = jsonParser.postdata_method(weburl, json.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("saveReturnData  error", "saveReturnData  error ---------------- " + e.toString());
            }
            Log.e("saveReturnData ", "saveReturnData --------------" + result);
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            progDialog.dismiss();
            if (dlg.isShowing()) {
                dlg.dismiss();
            }
            Log.e("saveReturnData result ", "saveReturnData result ------------------------- " + result);
            try {
                JSONObject json = new JSONObject(result);
                String value = json.optString("success");
                if ("true".equals(value)) {
                    Log.e("saveReturnData result ", "inside saveReturnData result ------------------------- " + result);
                    getReturnData();
                } else {
                    open_alert("for_Return");
                }
//

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    */
/***************************************************//*


    private View.OnTouchListener Spinner_OnTouch = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (tvcheck.isSelected()) {
                    if (btnSave.isEnabled()) {
                        App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail123.this, false);
                    } else {
//                        spCycle.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                        spCycle.performClick();
                    }
                }
                if (tvmeds.isSelected()) {
//                    spCycle.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                    spCycle.performClick();
                }
            }
            return true;
        }
    };


    */
/******************************************************************//*


//    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
//
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            if (tvcheck.isSelected()) {
//                if (btnSave.isEnabled()) {
//                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", PatientDetail.this, false);
//
//                } else {
//
//                    if (spCycle.getSelectedItem().toString().equals("This Cycle")) {
//                        imc_met = "this_cycle";
//                        Log.e("Cycle", "" + imc_met);
//                        spnPosition = position;
//
//                        new getCheckin().execute(imc_met);
//
//                    } else if (spCycle.getSelectedItem().toString().equals("Next Cycle")) {
//                        imc_met = "next_cycle";
//                        Log.e("Cycle", "" + imc_met);
//                        spnPosition = position;
//                        new getCheckin().execute(imc_met);
//                    }
//                }
//            } else if (tvmeds.isSelected()) {
//
//                if (spCycle.getSelectedItem().toString().equals("This Cycle")) {
//                    imc_met = "this_cycle";
//                    Log.e("Cycle", "" + imc_met);
//                    spnPosition1 = position;
//                    new getMedsData().execute(imc_met);
//
//                } else if (spCycle.getSelectedItem().toString().equals("Last Cycle")) {
//                    imc_met = "last_cycle";
//                    Log.e("Cycle", "" + imc_met);
//                    spnPosition1 = position;
//                    new getMedsData().execute(imc_met);
//
//                } else if (spCycle.getSelectedItem().toString().equals("Next Cycle")) {
//                    imc_met = "next_cycle";
//                    Log.e("Cycle", "" + imc_met);
//                    spnPosition1 = position;
//                    new getMedsData().execute(imc_met);
//                }
//            }
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> arg0) {
//            // TODO Auto-generated method stub
//        }
//
//    }


    */
/**********************************************************************//*


    class Secondry_LoginWebService extends AsyncTask<String, String, String> {
        String from2;

        Secondry_LoginWebService(String from3) {
            from2 = from3;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                result = jsonParser.postdata_method(weburl, json1.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("login error", "" + e.toString());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("login", "Secondry_LoginWebService---->" + result);
            JSONObject dataObj = null;
            try {
                dataObj = new JSONObject(result);
                String success = dataObj.optString("success");
                if (success.equals("true")) {
                    try {
                        String username = dataObj.optString("username");
                        String s_userId = dataObj.optString("user_id");
                        String fullname = dataObj.optString("fullname");
                        Log.e("Secondry username", username);
                        Log.e("SecondryuserId", s_userId);
                        Log.e("Secondry fullname", fullname);
                        String cur_userid = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.USERID, "");

                        if (!s_userId.equals(cur_userid)) {
//                        SharedPrefrnceCareMeds.setDataInSharedPrefrence(PatientDetail.this, "secondry_userId", s_userId);
//                        SharedPrefrnceCareMeds.setDataInSharedPrefrence(PatientDetail.this, "secondry_userfullname", fullname);
                            PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SECONDRY_USERID, "" + s_userId);
                            PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SECONDRY_USERFULLNAME, "" + fullname);

                            if (from2.equals("checkin")) {
                                for (int i = 0; i < newchecklist.size(); i++) {
                                    PRESCRIPTION_COMMON_Model n1 = newchecklist.get(i);
                                    if (n1.isSelected().equalsIgnoreCase("true")) {
                                        Log.e("selected id", "---->" + n1.getId());
                                        new saveCheckInData().execute("" + n1.getId(), "" + n1.getNew_checked_in_quantity_toupdate(), s_userId);
                                    }
                                }

                            } else if (from2.equals("Admin")) {

                                for (int i = 0; i < new_admin_modelArrayList.size(); i++) {
                                    NEW_ADMIN_MODEL nd1 = new_admin_modelArrayList.get(i);
                                    Log.e("witness Loop for---->" + i, "new_admin_modelArrayList---->" + new_admin_modelArrayList.size());
                                    if (nd1.is_edited()) {
                                        new saveAdminData(nd1).execute(s_userId);
                                    }
                                    if (i == (new_admin_modelArrayList.size() - 1)) {
                                        getAdminData();
                                    }
                                }
                            }
                        } else {
                            Enable_save_and_cancel_button();
                            blank_alert("Sorry, you can't authorise this.");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Enable_save_and_cancel_button();
                    App_Constants.showAlertDialog("Authentication failed", "The credentials you entered could not be authenticated.", PatientDetail123.this, false);
                }

            } catch (Exception e) {
                Enable_save_and_cancel_button();
                e.printStackTrace();
            }
        }
    }

    */
/***************************************************************//*


    private void blank_alert(String myMessage) {
        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(PatientDetail123.this);
        // set title
        alertDialogBuilder2.setTitle("Alert");
        // set dialog message
        alertDialogBuilder2.setMessage("" + myMessage).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog2, int id) {
                        dialog2.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog2 = alertDialogBuilder2.create();
        // show it
        alertDialog2.show();
    }

    */
/*********************************************************************//*


    public class Witness_Dialog extends DialogFragment {
        View dialoglayout;
        EditText s_username, s_password;
        int position;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // return super.onCreateDialog(savedInstanceState);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
            builder1.setCancelable(false);

            //builder1.setInverseBackgroundForced(true);

            Bundle args = getArguments();
            final String from = args.getString("from");
            Log.e("Warfarine_Quantity_Dialog", "from-->" + from);

            if (from.equals("checkin")) {
                builder1.setTitle("Confirm Medication Check In");

            } else {
                builder1.setTitle("Witness controlled drug administration");
            }


            builder1.setPositiveButton("Witness", null);
            LayoutInflater inflater = getActivity().getLayoutInflater();
            dialoglayout = inflater.inflate(R.layout.witness_login, null);
            builder1.setView(dialoglayout);

            //  builder1.show();
            s_username = (EditText) dialoglayout.findViewById(R.id.s_username);
            s_password = (EditText) dialoglayout.findViewById(R.id.s_password);
//            builder1.setPositiveButton("Witness", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//
//                    String sec_userName = s_username.getText().toString();
//                    String sec_password = s_password.getText().toString();
//
//                    if (sec_userName.length() > 0 && sec_password.length() > 0) {
//                        Log.e("s_username", "--->" + sec_userName);
//                        Log.e("s_password", "--->" + sec_password);
//                        Secondry_LoginWebService s111 = new Secondry_LoginWebService(from);
//                        s111.execute(sec_userName, sec_password);
//
//                    } else {
//                        blank_alert("Please enter a username and password");
//                    }
////                            dialog.dismiss();
//                }
//            });

            builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    Enable_save_and_cancel_button();
                }
            });
            builder1.setView(dialoglayout);


            final AlertDialog mAlertDialog = builder1.create();

            mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(final DialogInterface dialog) {
                    Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String sec_userName = s_username.getText().toString();
                            String sec_password = s_password.getText().toString();

                            if (sec_userName.length() > 0 && sec_password.length() > 0) {
                                Log.e("s_username", "--->" + sec_userName);
                                Log.e("s_password", "--->" + sec_password);
                                Secondry_LoginWebService s111 = new Secondry_LoginWebService(from);
                                s111.execute(sec_userName, sec_password);
                                dialog.dismiss();
                            } else {
                                blank_alert("Please enter a username and password");
                            }


                        }
                    });
                }
            });

            return mAlertDialog;


//            return builder1.create();
        }
    }
}

*/
