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
public class INRDialog extends DialogFragment {

    private INRListener callback;
    View dialoglayout;
    EditText edtNewInr;
    int position;

    public interface INRListener {
        public void INRUpdate(int sub, int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (INRListener) getActivity();
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
        dialoglayout = inflater.inflate(R.layout.update_inr, null);
        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        Bundle args = getArguments();
        position = args.getInt("position");

        //  builder1.show();
        edtNewInr = (EditText) dialoglayout.findViewById(R.id.edtNewInr);
        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (edtNewInr.getText().toString().length() > 0) {
                            //callback.INRUpdate(edtNewInr.getText().toString(), position);
                            int a = Integer.parseInt(edtNewInr.getText().toString());
                            callback.INRUpdate(a, position);
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


}
