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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.constants.App_Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ABC on 4/19/2016.
 */
public class Confirm_Admin_PRN_Dialog extends DialogFragment {
    private ConfirmAdminPrnListener callback;
    EditText txtQuan, edtReason;
    View dialoglayout;
    int Position;
    private ArrayList<String> _warning_overrides_list;


    public interface ConfirmAdminPrnListener {
        public void ConfirmAdminPrnUpdate(String slot_time, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab ,ArrayList<String> _warning_overrides_list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (ConfirmAdminPrnListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Confirm Quantity");
        builder1.setPositiveButton("Confirm", null);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.confirm_prn_dialog, null);
        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        Bundle args = getArguments();
        String currentName = args.getString("name");
        final int post = args.getInt("position");
        final String tab = args.getString("tab");
        _warning_overrides_list = args.getStringArrayList("warninglist");
        final String prescribed_slot_time = args.getString("pre_slot_time");
        Log.e("Confirm_Admin_PRN_Dialog", "prescribed_slot_time-->" + prescribed_slot_time);


        TextView txtQuanTitle = (TextView) dialoglayout.findViewById(R.id.txtQuanTitle);

        if (currentName.equals("")) {
            txtQuanTitle.setText("");
        } else {
            txtQuanTitle.setText(currentName);
        }
        // String quantity = args.getString("value");

        // TextView txtQuanTitle = (TextView) dialoglayout.findViewById(R.id.txtQuanTitle);
        // txtQuanTitle.setText(currentName);

        txtQuan = (EditText) dialoglayout.findViewById(R.id.edtQuanValue);
        // txtQuan.setText(quantity);
        //txtQuan.setSelection(txtQuan.getText().length());

        edtReason = (EditText) dialoglayout.findViewById(R.id.edtQuantReason);
        // edtReason.setText(quantity);
        //edtReason.setSelection(edtReason.getText().length());

        Position = args.getInt("position");
        //  builder1.show();

//        builder1.setPositiveButton(
//                "Confirm",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        if (txtQuan.getText().toString().length() > 0) {
//                            if (edtReason.getText().toString().length() > 0) {
//                                int a = Integer.parseInt(txtQuan.getText().toString());
//                                String secondary_user_id = "0";
//                                String quantity_wasted = "0";
//
//                                String uid = UUID.randomUUID().toString();
//                                System.out.println("uuid = " + uid);
//
//                                callback.ConfirmAdminPrnUpdate(Date_utility.getCurrentTime(),  "true",  edtReason.getText().toString(),  ""+a,  secondary_user_id,  "false",  quantity_wasted,  uid, Position, tab);
//                            }
//                        } else {
//
//                            blank_alert();
//
//                        }
//
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
        builder1.setView(dialoglayout);


        final AlertDialog mAlertDialog = builder1.create();

        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (txtQuan.getText().toString().length() > 0) {
                            if (edtReason.getText().toString().length() > 0) {
//                                int a = Integer.parseInt(txtQuan.getText().toString());
                                String a = txtQuan.getText().toString();
                                String secondary_user_id = "0";

                                String quantity_wasted = "0";

                                String uid = UUID.randomUUID().toString();
                                System.out.println("uuid = " + uid);
                                dialog.dismiss();
                                callback.ConfirmAdminPrnUpdate(Date_utility.getCurrentTime(), "true", edtReason.getText().toString(), "" + a, secondary_user_id, "false", quantity_wasted, uid, Position, tab,_warning_overrides_list);
                            }
                        } else {

//                            blank_alert();

                            App_Constants.showAlertDialog("Alert", "You must enter a quantity\n" , getContext(), false);

                        }
                    }
                });
            }
        });


        return mAlertDialog;
    }

//    private void blank_alert() {
//
//        String myMessage = "You must enter a quantity\n";
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
//
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
