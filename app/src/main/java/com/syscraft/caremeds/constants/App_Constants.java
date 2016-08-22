package com.syscraft.caremeds.constants;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.syscraft.caremeds.R;


public class App_Constants {


    public static String TIMEOUT_ALERT_Action = "com.syscraft.caremeds.SHOW_TIMEOUT_ALERT";
    public static String LOGOUT_Action = "com.syscraft.caremeds.LOGOUT";
//    private static Dialog com_dialog;

    public static void showAlertDialog(final String title, String message,
                                       final Context context, final boolean redirectToPreviousScreen) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        alertbox.setMessage(message);
        alertbox.setTitle(title);

        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        alertbox.show();
    }

    public static void showTwoBtnAlertDialog(final String title, String message,
                                             final Context context, final boolean redirectToPreviousScreen) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        alertbox.setMessage(message);
        alertbox.setTitle(title);
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        alertbox.show();
    }


    /*****************************************************************/
    public Dialog comman_loading_dialog(Context context, String Message) {
        Dialog com_dialog = new Dialog(context);
        com_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        com_dialog.setContentView(R.layout.custom_dialog);
        com_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        com_dialog.setCancelable(false);

//      ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
//      imageView.setBackgroundResource(R.drawable.movie);
        TextView msg = (TextView) com_dialog.findViewById(R.id.text);
        msg.setText(Message);

//      final AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
//      anim.setOneShot(false);
//
//      imageView.post(new Runnable() {
//
//          @Override
//          public void run() {
//              // TODO Auto-generated method stub
//              anim.start();
//          }
//      });
        com_dialog.show();
        return com_dialog;
    }


    public Dialog dialog(Context context, String Message) {

        Dialog com_dialog = new Dialog(context);
        com_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        com_dialog.setContentView(R.layout.custom_dialog);
        com_dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        com_dialog.setCancelable(false);

//      ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
//      imageView.setBackgroundResource(R.drawable.movie);
        TextView msg = (TextView) com_dialog.findViewById(R.id.text);
        msg.setText(Message);

//      final AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
//      anim.setOneShot(false);
//
//      imageView.post(new Runnable() {
//
//          @Override
//          public void run() {
//              // TODO Auto-generated method stub
//              anim.start();
//          }
//      });
        com_dialog.show();
        return com_dialog;
    }

//    public void dismissDialog() {
//
//        Log.e("dialog.isShowing()", "--->" + com_dialog.isShowing());
//
////      if (dialog.isShowing()) {
//        Log.e("dialog.isShowing()  1111", "--->" + com_dialog.isShowing());
//        try {
//            com_dialog.dismiss();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        Log.e("dialog.isShowing()  2222", "--->" + com_dialog.isShowing());
////      }
//    }


    /******************************************************/

    private void alert(Context context, String myMessage) {

//        String myMessage = "You must enter a quantity\n";

        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder2.setTitle("Alert");

        // set dialog message
        alertDialogBuilder2.setMessage("" + myMessage).setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog2, int id) {
                        dialog2.cancel();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog2 = alertDialogBuilder2.create();

        // show it
        alertDialog2.show();

    }

}
