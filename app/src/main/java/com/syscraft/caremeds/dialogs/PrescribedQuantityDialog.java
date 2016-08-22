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
import com.syscraft.caremeds.constants.App_Constants;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by anubhav on 3/3/2016.
 */
public class PrescribedQuantityDialog extends DialogFragment {

    private PrescribedQuantityListener callback;
    TextView txtQuan;
    View dialoglayout;
    int Position;

    EditText edtQuant;
    private ArrayList<String> _warning_overrides_list;

    public interface PrescribedQuantityListener {
        public void PrescribedQuantityUpdate(String prescribed_slot_time, String sub, int pos, String secondary_user_id, String quantity_wasted, String uid,ArrayList<String> _warning_overrides_list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (PrescribedQuantityListener) getActivity();
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
        dialoglayout = inflater.inflate(R.layout.prescribed_dialog, null);
        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        Bundle args = getArguments();
        String currentName = args.getString("name");
        final String _slotDose = args.getString("value");

        final String prescribed_slot_time = args.getString("pre_slot_time");
        Log.e("PrescribedQuantityDialog", "PrescribedQuantityDialog prescribed_slot_time-->" + prescribed_slot_time);
        _warning_overrides_list = args.getStringArrayList("warninglist");
        Log.e("PrescribedQuantityDialog", "PrescribedQuantityDialog _warning_overrides_list size-->" + _warning_overrides_list.size());
        TextView txtQuanTitle = (TextView) dialoglayout.findViewById(R.id.txtQuanTitle);
        txtQuanTitle.setText(currentName);

        edtQuant = (EditText) dialoglayout.findViewById(R.id.edtQuant);

        txtQuan = (TextView) dialoglayout.findViewById(R.id.edtQuanValue);
        txtQuan.setText(_slotDose);
        //txtQuan.setSelection(txtQuan.getText().length());

        Position = args.getInt("position");
        //  builder1.show();

//        builder1.setPositiveButton(
//                "Confirm",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        String qty = edtQuant.getText().toString();
//
//
//                        boolean validationPassed = true;
//                        String validationMessage = "";
//
//                        if (qty.isEmpty()) {
//                            validationPassed = false;
//                            validationMessage = validationMessage + "You must enter a quantity\n";
//                        }
//                        if ((qty.equals("X") || qty.equals("x") || qty.equals("*")) && (!_slotDose.isEmpty())) {
//                            validationPassed = false;
//                            validationMessage = validationMessage + "The quantity * entered must be the same as the prescribed quantity. slotDose = '" + _slotDose + "'\n";
//                        } else if (!qty.equals(_slotDose) && (!_slotDose.isEmpty())) {
//                            validationPassed = false;
//                            validationMessage = validationMessage + "The quantity entered must be the same as the prescribed quantity.\n";
//                        }
//
//
//                        if (validationPassed) {
//                            int a = Integer.parseInt(qty);
//
//                            String secondary_user_id = "0";
//                            String quantity_wasted = "0";
//                            boolean is_waste=false;
////                            String uid = "";
//                            String uid = UUID.randomUUID().toString();
//                            System.out.println("Patch_Dialog uuid = " + uid);
//
//                            callback.PrescribedQuantityUpdate(prescribed_slot_time, a, Position, secondary_user_id, quantity_wasted, uid);
//
//                        } else {
//
////                            alert(validationMessage);
//                            App_Constants.showAlertDialog("Alert", ""+validationMessage, getContext(), false);
//
//                        }
//
//
////                        if (txtQuan.getText().toString().length() > 0) {
////                            int a = Integer.parseInt(txtQuan.getText().toString());
////                            callback.PrescribedQuantityUpdate(a, Position);
////                        }
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
                        String qty = edtQuant.getText().toString();
                        boolean validationPassed = true;
                        String validationMessage = "";

                        if (qty.isEmpty()) {
                            validationPassed = false;
                            validationMessage = validationMessage + "You must enter a quantity\n";
                        }
                        if ((qty.equals("X") || qty.equals("x") || qty.equals("*")) && (!_slotDose.isEmpty())) {
                            validationPassed = false;
                            validationMessage = validationMessage + "The quantity * entered must be the same as the prescribed quantity. slotDose = '" + _slotDose + "'\n";
                        } else if (!qty.equals(_slotDose) && (!_slotDose.isEmpty())) {
                            validationPassed = false;
                            validationMessage = validationMessage + "The quantity entered must be the same as the prescribed quantity.\n";
                        }


                        if (validationPassed) {
//                            int a = Integer.parseInt(qty);

                            String secondary_user_id = "0";
                            String quantity_wasted = "0";
                            boolean is_waste = false;
//                            String uid = "";
                            String uid = UUID.randomUUID().toString();
                            System.out.println("Patch_Dialog uuid = " + uid);

                            dialog.dismiss();
                            Log.e("PrescribedQuantityDialog", "PrescribedQuantityDialog callback size-->" + _warning_overrides_list.size());
                            callback.PrescribedQuantityUpdate(prescribed_slot_time, qty, Position, secondary_user_id, quantity_wasted, uid,_warning_overrides_list);

                        } else {

//                            alert(validationMessage);
                            App_Constants.showAlertDialog("Alert", "" + validationMessage, getContext(), false);

                        }


                    }
                });
            }
        });

        return mAlertDialog;
    }

}
