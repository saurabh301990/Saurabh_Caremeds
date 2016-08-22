package com.syscraft.caremeds.Utility;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.syscraft.caremeds.LoginActivity;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.constants.App_Constants;
import com.syscraft.caremeds.service.Demoservice;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

import java.util.Date;

/**
 * Created by syscraft on 4/26/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(getApplicationContext(), "received", Toast.LENGTH_SHORT);
            Log.e("received", "received");
            blank_alert("Your session will expire in 5 minutes !");
        }
    };


    private BroadcastReceiver logoutreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(getApplicationContext(), "received", Toast.LENGTH_SHORT);
            Log.e("logoutreceiver", "received------------>$$$$$$$$$");
            logout();
        }
    };


    Toolbar toolbar;
    private long lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();
    }

    protected abstract int getLayoutResource();

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
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
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(App_Constants.TIMEOUT_ALERT_Action);
        registerReceiver(receiver, filter);

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(App_Constants.LOGOUT_Action);
        registerReceiver(logoutreceiver, filter2);


    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        unregisterReceiver(logoutreceiver);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm != null && fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        lastActivity = new Date().getTime();
//        lastActivity = lastActivity * 60 * 1000;
        PreferenceConnector.writeLong(BaseActivity.this, PreferenceConnector.LAST_ACTIVITY_TIME, (new Date().getTime()));
//        Log.e("lastActivity", "lastActivity---1->" + lastActivity);
//        long logtime=PreferenceConnector.readLong(BaseActivity.this,PreferenceConnector.LOGIN_TIME, (new Date().getTime()));
//        Log.e("lastActivity", "lastActivity---2->" + logtime);
//        long diff= lastActivity-logtime;
//        Log.e("lastActivity", "lastActivity---3->" + diff);
//
////        int seconds = (int) (diff / 1000) % 60;
//        int minutes = (int) ((diff / (1000 * 60)) % 60);
////        int hours = (int) ((diff / (1000 * 60 * 60)) % 24);
////
////
////        Log.e("seconds", "---->" + seconds);
//        Log.e("minutes", "lastActivity----m>" + minutes);
////        Log.e("hours", "---->" + hours);


        return super.dispatchTouchEvent(ev);
    }


    /***************************************************************/

    private void blank_alert(String myMessage) {

//        String myMessage = "You must enter a quantity\n";
        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getApplicationContext());
        // set title
        alertDialogBuilder2.setTitle("Alert");
        // set dialog message
        alertDialogBuilder2
                .setMessage("" + myMessage)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog2, int id) {
                        dialog2.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog2 = alertDialogBuilder2.create();
        alertDialog2.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        // show it
        alertDialog2.show();

    }


    public void logout() {

        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.IS_LOGIN, "false");

//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.USERNAME, "");
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.USERID, "");
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.FULLNAME, "");


//        PreferenceConnector.writeInteger(getApplicationContext(), PreferenceConnector.SESSION_TIMEOUT, 10);
//        PreferenceConnector.writeInteger(getApplicationContext(), PreferenceConnector.WARNING_TIME, 0);
//        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.AUTH_TOKEN, "");

        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SECONDRY_USERID, "");
        PreferenceConnector.writeString(getApplicationContext(), PreferenceConnector.SECONDRY_USERFULLNAME, "");

        stopService(new Intent(getApplicationContext(), Demoservice.class));
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
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


}



