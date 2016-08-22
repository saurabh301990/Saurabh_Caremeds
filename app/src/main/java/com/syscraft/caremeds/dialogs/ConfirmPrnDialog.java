package com.syscraft.caremeds.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.constants.App_Constants;

/**
 * Created by anubhav on 3/3/2016.
 */
public class ConfirmPrnDialog extends DialogFragment {

    private ConfirmPrnListener callback;
    EditText txtQuan, edtReason;
    View dialoglayout;
    int Position;

    public interface ConfirmPrnListener {
        public void ConfirmPrnUpdate(int sub, String reason, int pos, String tab);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (ConfirmPrnListener) getActivity();
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

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.confirm_prn_dialog, null);
        builder1.setView(dialoglayout);
        builder1.setPositiveButton("Confirm", null);
        //builder1.setInverseBackgroundForced(true);

        Bundle args = getArguments();
        String currentName = args.getString("name");
        final int post = args.getInt("position");
        final String tab = args.getString("tab");

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
//                                callback.ConfirmPrnUpdate(a, edtReason.getText().toString(), Position, tab);
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
                                int a = Integer.parseInt(txtQuan.getText().toString());
                                dialog.dismiss();
                                callback.ConfirmPrnUpdate(a, edtReason.getText().toString(), Position, tab);
                            }
                        } else {

//                            blank_alert();
                            App_Constants.showAlertDialog("Alert", "You must enter a quantity\n", getContext(), false);

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
