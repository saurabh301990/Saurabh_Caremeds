package com.syscraft.caremeds.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.syscraft.caremeds.R;

/**
 * Created by Syscraft on 19-02-2016.
 */
public class AboutDialog extends DialogFragment {

    Button closeButton;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        alertDialog.setCancelable(true);
        //alertDialog.setTitle("Update Measurement");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.about, null);
        closeButton = (Button) dialoglayout.findViewById(R.id.btnclose);

        alertDialog.setView(dialoglayout);
        alertDialog.setInverseBackgroundForced(true);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        alertDialog.setView(dialoglayout);
        return alertDialog.create();
    }

}
