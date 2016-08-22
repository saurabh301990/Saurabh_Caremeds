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
 * Created by Syscraft on 19-02-2016.
 */
public class ShowNoteDialog extends DialogFragment {

    TextView tvClose;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Show Note");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.show_note, null);
        tvClose = (TextView) dialoglayout.findViewById(R.id.tvclose);
        builder1.setView(dialoglayout);

        Bundle args = getArguments();
        String sub = args.getString("sub");
        String detail = args.getString("detail");

        TextView title = (TextView)dialoglayout.findViewById(R.id.txtNoteTitle);
        TextView txtDetail = (TextView)dialoglayout.findViewById(R.id.txtNoteDetail);
        title.setText(sub);
        txtDetail.setText(detail);

//        tvClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

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
