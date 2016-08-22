package com.syscraft.caremeds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

import java.util.ArrayList;
import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    EditText baseUrl, loginName;
    private CheckBox patient_cache_chkbox, witness_while_checkin_chkbox;
    private Spinner spnr_time_in_minute;
    List<String> time_list;
    ArrayAdapter<String> adapter;
    private String selected_SpinnerItem;
    TextView spinnertxt;
    //https://demo.caremeds.co.uk/api

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_ab_back_holo_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        time_list = new ArrayList<String>();

        baseUrl = (EditText) findViewById(R.id.edtBaseUrl);
        loginName = (EditText) findViewById(R.id.edtLoginName);
        patient_cache_chkbox = (CheckBox) findViewById(R.id.patient_cache_chkbox);
        witness_while_checkin_chkbox = (CheckBox) findViewById(R.id.witness_while_checkin_chkbox);
        spnr_time_in_minute = (Spinner) findViewById(R.id.spnr_time_in_minute);


        time_list.add("10");
        time_list.add("20");
        time_list.add("30");
        time_list.add("40");
        time_list.add("50");
        time_list.add("60");
        time_list.add("70");
        time_list.add("80");
        time_list.add("90");
        time_list.add("100");
        time_list.add("110");
        time_list.add("120");
        time_list.add("200");

//        spnr_time_in_minute.setOnItemSelectedListener(this);


        adapter = new ArrayAdapter<String>(SettingsActivity.this,
                android.R.layout.simple_spinner_item, time_list);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(R.layout.simple_spinner);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_time_in_minute.setAdapter(adapter);

        String delay_time = PreferenceConnector.readString(SettingsActivity.this, PreferenceConnector.SETTING_ALLOWABLE_DELAY_MINUTES, "");

        if (delay_time.isEmpty()) {

            spnr_time_in_minute.setSelection(0);
        } else {

            int indx = time_list.indexOf(delay_time);
            spnr_time_in_minute.setSelection(indx);
        }


        //
        spnr_time_in_minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_SpinnerItem = spnr_time_in_minute.getSelectedItem().toString();

                spinnertxt = (TextView) view.findViewById(R.id.sp_text);
                if (time_list.get(position) != null) {
                    ((TextView) view).setTextColor(getResources().getColorStateList(R.color.white));
                }
                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SETTING_ALLOWABLE_DELAY_MINUTES, selected_SpinnerItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String witness_while_checkin_status = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_WITNESS_CHECKIN, "");
        if (witness_while_checkin_status.equalsIgnoreCase("true")) {
            witness_while_checkin_chkbox.setChecked(true);
        } else {
            witness_while_checkin_chkbox.setChecked(false);
        }


        String Cache_Patients_status = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.CACHE_PATIENT_LIST, "");
        if (Cache_Patients_status.equalsIgnoreCase("true")) {
            patient_cache_chkbox.setChecked(true);
        } else {
            patient_cache_chkbox.setChecked(false);
        }

//        if ("true".equals(SharedPrefrnceCareMeds.getSharedPrefData(SettingsActivity.this, "Cache_Patients"))) {
//            patient_cache_chkbox.setChecked(true);
//        }else {
//            patient_cache_chkbox.setChecked(false);
//        }

        String is_login_status = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.IS_LOGIN, "false");
        String login_acc_name = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LOGIN_ACCOUNT_NAME, "");
        if (is_login_status.equalsIgnoreCase("true")) {
            loginName.setText(login_acc_name);
        }


//        if ("true".equals(SharedPrefrnceCareMeds.getSharedPrefData(SettingsActivity.this, "IsLogin"))) {
//            loginName.setText(SharedPrefrnceCareMeds.getSharedPrefData(SettingsActivity.this, "LoginName"));
//        }

        try {
//            String baseurl_value = SharedPrefrnceCareMeds.getSharedPrefData(SettingsActivity.this, "BaseUrl");

            String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");

            if (!baseurl_value.isEmpty()) {
                baseUrl.setText("" + baseurl_value);
            }

            boolean b1 = PreferenceConnector.readBoolean(getApplicationContext(), PreferenceConnector.IS_USER_DONE_LOGIN_ONCE, false);
            if (b1) {

                baseUrl.setEnabled(false);
            }else {
                baseUrl.setEnabled(true);
            }

//            String LoginName_value = SharedPrefrnceCareMeds.getSharedPrefData(SettingsActivity.this, "LoginName");
            String LoginName_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.LOGIN_ACCOUNT_NAME, "");

            if (!LoginName_value.isEmpty()) {
                loginName.setText("" + LoginName_value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        baseUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                SharedPrefrnceCareMeds.setDataInSharedPrefrence(SettingsActivity.this, "BaseUrl", baseUrl.getText().toString().trim());

                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, baseUrl.getText().toString().trim());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                SharedPrefrnceCareMeds.setDataInSharedPrefrence(SettingsActivity.this, "LoginName", loginName.getText().toString().trim());

                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.LOGIN_ACCOUNT_NAME, loginName.getText().toString().trim());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        patient_cache_chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // update your model (or other business logic) based on isChecked
//                SharedPrefrnceCareMeds.setDataInSharedPrefrence(SettingsActivity.this, "Cache_Patients", "" + isChecked);
                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.CACHE_PATIENT_LIST, "" + isChecked);
            }
        });


        witness_while_checkin_chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SETTING_WITNESS_CHECKIN, "" + isChecked);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
