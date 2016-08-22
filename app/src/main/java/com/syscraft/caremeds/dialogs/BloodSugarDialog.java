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
import android.widget.EditText;
import android.widget.TextView;

import com.syscraft.caremeds.R;
//import com.syscraft.caremeds.model.BsSiteModel;
import com.syscraft.caremeds.model.MeasureModel;

import java.util.ArrayList;

/**
 * Created by anubhav on 2/19/2016.
 */
public class BloodSugarDialog extends DialogFragment {

    private BloodSugarListener callback;
    View dialoglayout;
    EditText edtNewSugarValue, edtNewBsSite;
    int position;
    ArrayList<MeasureModel> measurearraylist;
    TextView site;
    String prevouissite;

    public interface BloodSugarListener {
        public void BloodSugarUpdate(int sub, String detail, int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (BloodSugarListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
        measurearraylist = new ArrayList<MeasureModel>();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // return super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Update Measurement");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.update_sugar, null);
        builder1.setView(dialoglayout);
        //builder1.setInverseBackgroundForced(true);

        //  builder1.show();
        Bundle args = getArguments();
        position = args.getInt("position");
        prevouissite = args.getString("prevouissite");
        //prevouissite=measurearraylist.get(position).getAbSite_content();

        Log.e("position", "" + position);

        site = (TextView) dialoglayout.findViewById(R.id.prevoious_site);
        edtNewSugarValue = (EditText) dialoglayout.findViewById(R.id.edtNewSugar);
        edtNewBsSite = (EditText) dialoglayout.findViewById(R.id.Bs_editText);
//        for (int i = 0; i <= measurearraylist.size(); i++) {
//             prevouissite=measurearraylist.get(i).getAbSite_content();
//        }
//        Log.e("prevouissite", "" + prevouissite);
        site.setText(prevouissite);
        Log.e("site", "" + site);

        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (edtNewSugarValue.getText().toString().length() > 0) {
                            if (edtNewBsSite.getText().toString().length() > 0) {
                                int a = Integer.parseInt(edtNewSugarValue.getText().toString());
                                callback.BloodSugarUpdate(a, edtNewBsSite.getText().toString(), position);
                                // callback.BloodSugarUpdate(edtNewSugarValue.getText().toString(), edtNewBsSite.getText().toString(), position);
                            }
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
