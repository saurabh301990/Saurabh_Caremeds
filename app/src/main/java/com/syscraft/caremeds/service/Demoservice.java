package com.syscraft.caremeds.service;

/**
 * Created by syscraft on 4/26/2016.
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import com.syscraft.caremeds.constants.App_Constants;
import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

public class Demoservice extends Service {

    private static final String TAG = "DemoService";
    private final int INTERVAL = 60 * 1000;
    private Timer timer = new Timer();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Service#onCreate() Called by the system when the service
     * is first created. Do not call this method directly.
     */
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d(TAG, "Service is created");
//        Toast.makeText(this, "Service is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        // Display the Toast Message
        Log.d(TAG, "Start Service");
//        Toast.makeText(this, "Start Service", Toast.LENGTH_SHORT).show();

        // Execute an action after period time
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Log.d(TAG, "Start to do an action");
                long lastActivity = PreferenceConnector.readLong(getApplicationContext(), PreferenceConnector.LAST_ACTIVITY_TIME, 0);
                Log.e("lastActivity", "lastActivity---lastActivity_time->" + lastActivity);
                // Print a log
//                long logtime = PreferenceConnector.readLong(getApplicationContext(), PreferenceConnector.LOGIN_TIME, 0);
//                Log.e("lastActivity", "lastActivity---logtime->" + logtime);
                long diff = lastActivity - new Date().getTime();
                Log.e("lastActivity", "lastActivity---diff->" + diff);

                int prewarntime = PreferenceConnector.readInteger(getApplicationContext(), PreferenceConnector.WARNING_TIME, 25);
                Log.e("lastActivity", "lastActivity---prewarntime->" + prewarntime);

                int session_timeout = PreferenceConnector.readInteger(getApplicationContext(), PreferenceConnector.SESSION_TIMEOUT, 30);
                Log.e("lastActivity", "lastActivity---session_timeout->" + session_timeout);


//        int seconds = (int) (diff / 1000) % 60;
                int minutes = (int) ((diff / (1000 * 60)) % 60);

                if (minutes == -prewarntime) {

                    Log.e("before", "lastActivity---before broadcast->");
                    Intent broadcast = new Intent();
                    broadcast.setAction(App_Constants.TIMEOUT_ALERT_Action);
                    getApplicationContext().sendBroadcast(broadcast);

//                    Handler mHandler = new Handler(Looper.getMainLooper()) {
//                        @Override
//                        public void handleMessage(Message message) {
//                            // This is where you do your work in the UI thread.
//                            // Your worker tells you in the message what to do.
//
//                            Intent broadcast = new Intent();
//                            broadcast.setAction("com.android.broadcasttest.SHOWTOAST");
//                            sendBroadcast(broadcast);
//
////                            blank_alert("Your session will expire in 5 minutes !");
//                        }
//                    };


                }

                if (minutes == -session_timeout) {

                    Log.e("before", "lastActivity---before broadcast->");
                    Intent broadcast = new Intent();
                    broadcast.setAction(App_Constants.LOGOUT_Action);
                    getApplicationContext().sendBroadcast(broadcast);

//                    Toast.makeText(getApplicationContext(), "here we have to do code for logout ", Toast.LENGTH_SHORT).show();
                }


//        int hours = (int) ((diff / (1000 * 60 * 60)) % 24);
//
//
//        Log.e("seconds", "---->" + seconds);
                Log.e("minutes", "lastActivity----m>" + minutes);


            }
        }, 0, INTERVAL);
        return super.onStartCommand(intent, flags, startId);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Service#onDestroy() Service is destroyed when user stop
     * the service
     */
    @Override
    public void onDestroy() {
        // Display the Toast Message
        Log.d(TAG, "Stop Service");
//        Toast.makeText(this, "Stop Service", Toast.LENGTH_SHORT).show();
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }


    /***************************************************************/

//    private void blank_alert(String myMessage) {
//
////        String myMessage = "You must enter a quantity\n";
//
//        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getApplicationContext());
//
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
//        alertDialog2.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        // show it
//        alertDialog2.show();
//
//    }


}