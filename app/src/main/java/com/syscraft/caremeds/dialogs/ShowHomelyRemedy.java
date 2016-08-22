package com.syscraft.caremeds.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.syscraft.caremeds.R;

/**
 * Created by Syscraft on 11-03-2016.
 */
public class ShowHomelyRemedy extends DialogFragment {

    TextView txtclose;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Show Homely Remedy");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.show_homely_remedy, null);
        txtclose = (TextView) dialoglayout.findViewById(R.id.close);
        builder1.setView(dialoglayout);

        Bundle args = getArguments();
        String sub = args.getString("name");
        String detail = args.getString("mwarning");

        TextView title = (TextView)dialoglayout.findViewById(R.id.title);
        TextView txtDetail = (TextView)dialoglayout.findViewById(R.id.txtwarning);
        title.setText(sub);
        txtDetail.setText(detail);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder1.setNegativeButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //  builder1.setView(v);
        builder1.setView(dialoglayout);
        return builder1.create();
    }
    }

