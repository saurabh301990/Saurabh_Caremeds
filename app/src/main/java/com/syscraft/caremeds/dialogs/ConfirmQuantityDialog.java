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
 * Created by anubhav on 2/20/2016.
 */
public class ConfirmQuantityDialog extends DialogFragment {

    private ConfirmQuantityListener callback;
    EditText txtQuan;
    View dialoglayout;
    int Position;
    String tab;

    public interface ConfirmQuantityListener {
        public void ConfirmQuantityUpdate(int sub, int pos, String tab);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (ConfirmQuantityListener) getActivity();
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
        dialoglayout = inflater.inflate(R.layout.confirm_quantity, null);
        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        Bundle args = getArguments();
        String currentName = args.getString("name");
        int quantity = args.getInt("cnfrmQuantity");
        final int Dispensed = args.getInt("Dispensed");
        // Log.e("quantity" + " " + quantity);
        tab = args.getString("tab");
        TextView txtQuanTitle = (TextView) dialoglayout.findViewById(R.id.txtQuanTitle);
        txtQuanTitle.setText(currentName);

        txtQuan = (EditText) dialoglayout.findViewById(R.id.edtQuanValue);
        // txtQuan.setText(quantity);
        txtQuan.setText(String.valueOf(quantity));

        // TextView.setText(String.valueOf(int))
        //txtQuan.setSelection(txtQuan.getText().length());

        Position = args.getInt("position");
        //  builder1.show();

//        builder1.setPositiveButton(
//                "Confirm",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//
//                       if (txtQuan.getText().toString().length() > 0) {
//                            int a = Integer.parseInt(txtQuan.getText().toString());
//                            callback.ConfirmQuantityUpdate(a, Position,tab);
//                        }
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

                            int qty = Integer.parseInt(txtQuan.getText().toString());

                            if (qty > Dispensed) {

                                String msg = "You cannot check in more than has been dispensed by the pharmacy";
                                App_Constants.showAlertDialog("Alert", msg, getActivity(), false);

                            } else {

                                dialog.dismiss();
                                callback.ConfirmQuantityUpdate(qty, Position, tab);
                            }
                        }
                    }
                });
            }
        });


        return mAlertDialog;
    }
}
