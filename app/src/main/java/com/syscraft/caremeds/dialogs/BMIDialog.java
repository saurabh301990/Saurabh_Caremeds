package com.syscraft.caremeds.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.syscraft.caremeds.R;

/**
 * Created by anubhav on 2/19/2016.
 */
public class BMIDialog extends DialogFragment {

    private BMIListener callback;
    View dialoglayout;
    EditText edtNewBmi;
    int position;

    public interface BMIListener {
        public void BMIUpdate(int sub,int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (BMIListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // return super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Update Measurement");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.update_bmi, null);
        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        //  builder1.show();

        Bundle args = getArguments();
        position = args.getInt("position");

        edtNewBmi = (EditText) dialoglayout.findViewById(R.id.edtNewBmi);

        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (edtNewBmi.getText().toString().length() > 0) {
                            int a=Integer.parseInt(edtNewBmi.getText().toString());
                            callback.BMIUpdate(a,position);
                            //callback.BMIUpdate(edtNewBmi.getText().toString(),position);
                        }
                        dialog.dismiss();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.setView(dialoglayout);
        return builder1.create();
    }


     /* AlertDialog.Builder builder1 = new AlertDialog.Builder(PatientDetail.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                    builder1.setCancelable(true);
                    builder1.setTitle("Update Measurement");

                    LayoutInflater inflater = getLayoutInflater();
                    View dialoglayout = inflater.inflate(R.layout.update_sugar, null);
                    builder1.setView(dialoglayout);
                    builder1.setInverseBackgroundForced(true);

                    //  builder1.show();

                    builder1.setPositiveButton(
                            "Confirm",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder1.setView(dialoglayout);
                    builder1.show();*/
}
