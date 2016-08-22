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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.constants.App_Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReasonDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerSelect;
    ToggleButton toggle;
    EditText editNotes, editQuantity;
    TextView textQuantity, spinnertxt, txtReasonTitle;
    List<String> reasonCategory = new ArrayList<String>();

    List<String> reason_list_tosend = new ArrayList<String>();

    ArrayAdapter<String> adapter;
    String getQuantity, SpinnerItem;
    private ReasonListener callback;
    private String pre_slot_time;
    private ArrayList<String> _warning_overrides_list;

    public interface ReasonListener {
//        public void ReasonUpdate(String sub, int pos);

        public void ReasonUpdate(String slot_time, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab, String editNotes_value,ArrayList<String> _warning_overrides_list);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (ReasonListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Reason for not giving");
        builder1.setPositiveButton("Confirm", null);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.reason, null);
        spinnerSelect = (Spinner) dialoglayout.findViewById(R.id.spselect);
        textQuantity = (TextView) dialoglayout.findViewById(R.id.quantity);

        txtReasonTitle = (TextView) dialoglayout.findViewById(R.id.txtReasonTitle);

        toggle = (ToggleButton) dialoglayout.findViewById(R.id.toggle_stock);
        editNotes = (EditText) dialoglayout.findViewById(R.id.et_notes);
        editQuantity = (EditText) dialoglayout.findViewById(R.id.et_quantity);

        _warning_overrides_list= new ArrayList<String>();

        Bundle args = getArguments();
        String isPrn = args.getString("isPrn");
        String name = args.getString("name");
        final int position = args.getInt("position");
        String _slotDose = args.getString("_slotDose");
        pre_slot_time = args.getString("pre_slot_time");
        try {
            _warning_overrides_list = args.getStringArrayList("warninglist");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (_warning_overrides_list.isEmpty()){
            _warning_overrides_list= new ArrayList<String>();
        }

        txtReasonTitle.setText(name);
        toggle.setTextOff("RETAINED");
        toggle.setTextOn("WASTED");
        if ("true".equals(isPrn)) {
            toggle.setChecked(false);
            editQuantity.setEnabled(false);
            textQuantity.setVisibility(View.INVISIBLE);
        } else {
            toggle.setChecked(true);
            editQuantity.setEnabled(true);
            textQuantity.setVisibility(View.VISIBLE);
        }
        ///toggle.setChecked(true);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle.isChecked()) {
                    editQuantity.setEnabled(true);
                    textQuantity.setVisibility(View.VISIBLE);
                } else {
                    editQuantity.setEnabled(false);
                    textQuantity.setVisibility(View.INVISIBLE);
                }
            }
        });

        spinnerSelect.setOnItemSelectedListener(this);
        reasonCategory.add("Please Select");
        reasonCategory.add("Nausea");
        reasonCategory.add("On Leave");
        reasonCategory.add("Destroyed");
        reasonCategory.add("Sleeping");
        reasonCategory.add("Abnormal Pulse");
        reasonCategory.add("Not Required PRN");
        reasonCategory.add("Hospital");
        reasonCategory.add("Other");
        reasonCategory.add("Refused");
        reasonCategory.add("Self Medicating");

        reason_list_tosend.add("");
        reason_list_tosend.add("N");
        reason_list_tosend.add("L");
        reason_list_tosend.add("D");
        reason_list_tosend.add("S");
        reason_list_tosend.add("P");
        reason_list_tosend.add("N/R");
        reason_list_tosend.add("H");
        reason_list_tosend.add("O");
        reason_list_tosend.add("R");
        reason_list_tosend.add("SM");

        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, reasonCategory);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(R.layout.simple_spinner);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelect.setAdapter(adapter);
        spinnerSelect.setSelection(0);
        //
        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem = spinnerSelect.getSelectedItem().toString();
                spinnertxt = (TextView) view.findViewById(R.id.sp_text);
                if (reasonCategory.get(position) != null) {
                    ((TextView) view).setTextColor(getResources().getColorStateList(R.color.white));
                }

//                String str = reason_list_tosend.get(spinnerSelect.getSelectedItemPosition());
//                Log.e("str", "---->" + str);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder1.setView(dialoglayout);
//        builder1.setPositiveButton("Confirm",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        getQuantity = editQuantity.getText().toString();
//                        SpinnerItem = spinnerSelect.getSelectedItem().toString();
//
//                        String str = reason_list_tosend.get(spinnerSelect.getSelectedItemPosition());
//                        Log.e("str", "---->" + str);
//
//                        String editNotes_value = editNotes.getText().toString();
//
//                        boolean is_valid = true;
//                        String message = "";
//
//                        if (SpinnerItem.equals("Please Select")) {
//                            is_valid = false;
//                            message = "You must select a Reason. ";
//                        }
//
//                        if (toggle.isChecked() && getQuantity.isEmpty()) {
//                            is_valid = false;
//                            message = message + "You must add a waste quantity. ";
//                        }
//
//                        if (is_valid) {
//
//                            String secondary_user_id = "0";
//                            String uid = UUID.randomUUID().toString();
//                            System.out.println("ReasonDialog uuid = " + uid);
//                            callback.ReasonUpdate(Date_utility.getCurrentTime(), "true", str, "", secondary_user_id, "false", getQuantity, uid, position, "admin", editNotes_value);
//
//
//                        } else {
//                            blank_alert();
//                        }
//
//
//                    }
//                });

        builder1.setNegativeButton("Cancel",
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

                        getQuantity = editQuantity.getText().toString();
                        SpinnerItem = spinnerSelect.getSelectedItem().toString();

                        String str = reason_list_tosend.get(spinnerSelect.getSelectedItemPosition());
                        Log.e("str", "---->" + str);

                        String editNotes_value = editNotes.getText().toString();

                        boolean is_valid = true;
                        String message = "";

                        if (SpinnerItem.equals("Please Select")) {
                            is_valid = false;
                            message = "You must select a Reason. ";
                        }

                        if (toggle.isChecked() && getQuantity.isEmpty()) {
                            is_valid = false;
                            message = message + "You must add a waste quantity. ";
                        }

                        if (is_valid) {

                            String secondary_user_id = "0";
                            String uid = UUID.randomUUID().toString();
                            System.out.println("ReasonDialog uuid = " + uid);

                            dialog.dismiss();
                            callback.ReasonUpdate(pre_slot_time, "true", str, "", secondary_user_id, "false", getQuantity, uid, position, "admin", editNotes_value, _warning_overrides_list);


                        } else {
//                            blank_alert();

                            App_Constants.showAlertDialog("Alert", message, getContext(), false);
                        }


                    }
                });
            }
        });


        return mAlertDialog;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /***************************************************************/

//    private void blank_alert() {
//
//        String myMessage = "You must enter a quantity\n";
//
//        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(
//                getActivity());
//
//        // set title
//        alertDialogBuilder2.setTitle("Alert");
//
//        // set dialog message
//        alertDialogBuilder2
//                .setMessage("" + myMessage)
//                .setCancelable(false)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog2, int id) {
//
//
//                        dialog2.cancel();
//                    }
//                });
//
//
//        // create alert dialog
//        AlertDialog alertDialog2 = alertDialogBuilder2.create();
//
//        // show it
//        alertDialog2.show();
//
//    }


}
