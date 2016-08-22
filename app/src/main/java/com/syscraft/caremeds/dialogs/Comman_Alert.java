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
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;

import java.util.UUID;

/**
 * Created by syscraft on 4/26/2016.
 */
public class Comman_Alert extends DialogFragment {

    TextView alert_mesage;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        alertDialog.setCancelable(true);
        alertDialog.setTitle("Alert");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.comman_alert, null);
        alert_mesage = (TextView) dialoglayout.findViewById(R.id.alert_mesage);


        Bundle args = getArguments();
        String mymessage = args.getString("msg");

        alert_mesage.setText(mymessage);

//        alertDialog.setView(dialoglayout);
//        alertDialog.setInverseBackgroundForced(true);
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        alertDialog.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });


        alertDialog.setView(dialoglayout);
        return alertDialog.create();
    }
}
