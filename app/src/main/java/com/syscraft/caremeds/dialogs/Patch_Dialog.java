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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.constants.App_Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patch_Dialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerSelect;
    TextView drugNameTitle_txvwt, presc_dose_value_textview;
    EditText patch_qty_etdtxt;
    TextView cur_patch_location_textview, spinnertxt;
    List<String> patch_loc_list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String getQuantity, selected_SpinnerItem;
    private Patch_Dialog_Listener callback;
    private ArrayList<String> _warning_overrides_list;

    public interface Patch_Dialog_Listener {

        public void Patch_Dialog_Update(String slot_time, String outcome, String reason, String dose_qty, String secondary_user_id, String is_waste, String quantity_wasted, String uid, int pos, String tab ,ArrayList<String> _warning_overrides_list);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (Patch_Dialog_Listener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Confirm Patch");
        builder1.setPositiveButton("Confirm", null);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.patch_layout, null);
        drugNameTitle_txvwt = (TextView) dialoglayout.findViewById(R.id.drugNameTitle_txvwt);
        presc_dose_value_textview = (TextView) dialoglayout.findViewById(R.id.presc_dose_value_textview);
        patch_qty_etdtxt = (EditText) dialoglayout.findViewById(R.id.patch_qty_etdtxt);
        spinnerSelect = (Spinner) dialoglayout.findViewById(R.id.spnr_patch_location);
        cur_patch_location_textview = (TextView) dialoglayout.findViewById(R.id.cur_patch_location_textview);

        patch_loc_list.add("Please Select");
        patch_loc_list.add("1 - Left Upper Arm");
        patch_loc_list.add("2 - Right Upper Arm");
        patch_loc_list.add("3 - Right Chest");
        patch_loc_list.add("4 - Left Chest");
        patch_loc_list.add("5 - Left Upper Back");
        patch_loc_list.add("6 - Right Upper Back");
        patch_loc_list.add("7 - Left Lower Back");
        patch_loc_list.add("8 - Right Lower Back");

        Bundle args = getArguments();
//        String isPrn = args.getString("isPrn");
        String name = args.getString("name");
        final int position = args.getInt("position");
        final String _slotDose = args.getString("_slotDose");
        String _patchLocation = args.getString("_patchLocation");
        Log.e("Patch_Dialog", "_patchLocation-->" + _patchLocation);

        _warning_overrides_list = args.getStringArrayList("warninglist");
        
        drugNameTitle_txvwt.setText(name);
        presc_dose_value_textview.setText(_slotDose);
        final String prescribed_slot_time = args.getString("pre_slot_time");
        Log.e("Patch_Dialog", "prescribed_slot_time-->" + prescribed_slot_time);

        if (_patchLocation.isEmpty() || _patchLocation.equalsIgnoreCase("null")) {
            cur_patch_location_textview.setText("No Location Recorded");
        } else {
            try {
                int index = Integer.parseInt(_patchLocation);

                cur_patch_location_textview.setText(patch_loc_list.get(index));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        spinnerSelect.setOnItemSelectedListener(this);


        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, patch_loc_list);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(R.layout.simple_spinner);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelect.setAdapter(adapter);
        spinnerSelect.setSelection(0);
        //
        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_SpinnerItem = spinnerSelect.getSelectedItem().toString();
                String s1 = selected_SpinnerItem.substring(0, 1);
                Log.e("Patch_Dialog", "s1-->" + s1);
//                Toast.makeText(getActivity(),"--->" + s1,Toast.LENGTH_LONG).show();
                spinnertxt = (TextView) view.findViewById(R.id.sp_text);
                if (patch_loc_list.get(position) != null) {
                    ((TextView) view).setTextColor(getResources().getColorStateList(R.color.white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder1.setView(dialoglayout);
//        builder1.setPositiveButton("Confirm",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        getQuantity = patch_qty_etdtxt.getText().toString();
//                        selected_SpinnerItem = spinnerSelect.getSelectedItem().toString();
//                        boolean validationPassed = true;
//                        String message = "";
//
//
//
//
//                        if (getQuantity.isEmpty()) {
//                            validationPassed = false;
//                            message = message + "You must enter a quantity\n";
//                        }
//
//                        if (!getQuantity.equals(_slotDose)) {
//                            validationPassed = false;
//                            message = message + "The quantity entered must be the same as the prescribed quantity.\n";
//                        }
//
//                        if (selected_SpinnerItem.equals("Please Select")) {
//                            validationPassed = false;
//                            message = message + "You must select a new location\n";
//                        }
//
//                        if (validationPassed) {
//
//                            String secondary_user_id = "0";
//                            String quantity_wasted = "0";
//                            boolean is_waste=false;
////                            String uid = "";
//                            String uid = UUID.randomUUID().toString();
//                            System.out.println("Patch_Dialog uuid = " + uid);
//
//
//                            String s1=  selected_SpinnerItem.substring(0,1);
//                            Log.e("Patch_Dialog", "s1-->" + s1);
//
//
//                            callback.Patch_Dialog_Update(prescribed_slot_time, "true", s1, getQuantity, secondary_user_id, ""+is_waste, quantity_wasted, uid, position, "admin");
//
//
//                        } else {
////                            blank_alert(message);
//
//                            App_Constants.showAlertDialog("Alert", message, getContext(), false);
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
                        getQuantity = patch_qty_etdtxt.getText().toString();
                        selected_SpinnerItem = spinnerSelect.getSelectedItem().toString();
                        boolean validationPassed = true;
                        String message = "";


                        if (getQuantity.isEmpty()) {
                            validationPassed = false;
                            message = message + "You must enter a quantity\n";
                        }

                        if (!getQuantity.equals(_slotDose)) {
                            validationPassed = false;
                            message = message + "The quantity entered must be the same as the prescribed quantity.\n";
                        }

                        if (selected_SpinnerItem.equals("Please Select")) {
                            validationPassed = false;
                            message = message + "You must select a new location\n";
                        }

                        if (validationPassed) {

                            String secondary_user_id = "0";
                            String quantity_wasted = "0";
                            boolean is_waste = false;
//                            String uid = "";
                            String uid = UUID.randomUUID().toString();
                            System.out.println("Patch_Dialog uuid = " + uid);


                            String s1 = selected_SpinnerItem.substring(0, 1);
                            Log.e("Patch_Dialog", "s1-->" + s1);

                            dialog.dismiss();
                            callback.Patch_Dialog_Update(prescribed_slot_time, "true", s1, getQuantity, secondary_user_id, "" + is_waste, quantity_wasted, uid, position, "admin",_warning_overrides_list);


                        } else {
//                            blank_alert(message);

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

//    private void blank_alert(String myMessage) {
//
////        String myMessage = "You must enter a quantity\n";
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
