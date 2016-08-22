package com.syscraft.caremeds.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.constants.App_Constants;

/**
 * Created by Syscraft on 19-02-2016.
 */
public class AddNoteDialog extends DialogFragment {
    TextView Cancel, addNote;

    private AddNoteListener callback;

    public interface AddNoteListener {
        public void AddNoteUpdate(String sub, String detail);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (AddNoteListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(false);
        builder1.setTitle("New Note");
        builder1.setPositiveButton("Add Note", null);
//        builder1.setNegativeButton("cancel", null);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.new_note, null);
        Cancel = (TextView) dialoglayout.findViewById(R.id.tvCancel);
        addNote = (TextView) dialoglayout.findViewById(R.id.tv_addnote);
        final EditText edtNoteDetail = (EditText) dialoglayout.findViewById(R.id.edtNoteDetail);
        final EditText edtNoteSub = (EditText) dialoglayout.findViewById(R.id.edtNoteSub);
        builder1.setView(dialoglayout);

//        builder1.setPositiveButton("Add Note", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//
//                String errorMsg = "There Were Problems !\n";
//                String error = "";
//
//                // Do something else
//                if ((edtNoteSub.getText().toString().length() > 0) && (edtNoteDetail.getText().toString().length() > 0)) {
//
//                    callback.AddNoteUpdate(edtNoteSub.getText().toString(), edtNoteDetail.getText().toString());
//
//                } else {
//
//                    if (edtNoteSub.getText().toString().length() > 0) {
//
//                    } else {
//                        error = "subject: can't be blank,";
//
//                    }
//
//                    if (edtNoteDetail.getText().toString().length() > 0) {
//
//                    } else {
//
//                        error = error + "content: can't be blank,";
//
//                    }
////                    App_Constants.showAlertDialog("Alert", errorMsg + error, getContext(), false);
//                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
//                    Comman_Alert comman_alert = new Comman_Alert();
//                    Bundle bld = new Bundle();
//                    bld.putString("msg", errorMsg + error);
//                    comman_alert.setArguments(bld);
//                    comman_alert.show(fragmentManager, "c_alert");
////
//                }
//
////                dismiss();
//            }
//        });

        // Negative Button
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do something else
                dismiss();
            }
        });
        builder1.setView(dialoglayout);

        final AlertDialog mAlertDialog= builder1.create();

        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        String errorMsg = "There Were Problems !\n";
                        String error = "";

                        // Do something else
                        if ((edtNoteSub.getText().toString().length() > 0) && (edtNoteDetail.getText().toString().length() > 0)) {

                            dialog.dismiss();

                            callback.AddNoteUpdate(edtNoteSub.getText().toString(), edtNoteDetail.getText().toString());

                        } else {

                            if (edtNoteSub.getText().toString().length() > 0) {

                            } else {
                                error = "subject: can't be blank,";

                            }

                            if (edtNoteDetail.getText().toString().length() > 0) {

                            } else {

                                error = error + "content: can't be blank,";

                            }
                    App_Constants.showAlertDialog("Alert", errorMsg + error, getContext(), false);
//                            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
//                            Comman_Alert comman_alert = new Comman_Alert();
//                            Bundle bld = new Bundle();
//                            bld.putString("msg", errorMsg + error);
//                            comman_alert.setArguments(bld);
//                            comman_alert.show(fragmentManager, "c_alert");


                        }
                    }
                });
            }
        });
        return mAlertDialog;
    }
}
