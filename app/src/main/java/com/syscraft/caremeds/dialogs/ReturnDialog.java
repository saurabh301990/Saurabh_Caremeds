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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.constants.App_Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syscraft on 3/16/2016.
 */
public class ReturnDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {
    Spinner quantitySpinner, wasteSpinner;
    EditText editwaste, editQuantity;
    private ReturnListner callback;
    TextView txtReasonTitle, spinnertxt;
    List<String> quantityCategory = new ArrayList<String>();
    List<String> wasteCategory = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> wasteadapter;
    String quantityspinner_val, wastespinner_val, getQuantityvalue_val, getWasteValue_val, spnWaste_val;
    LinearLayout wasteLayout, stockLayout;
    int position;
    private int prescriptionId;
    private String stock;

    public interface ReturnListner {
        public void ReturnUpdate(int quantityvalue, int wastevalue, int pos, String quant, String waste, int newavailable_stock, int quantityReturned, int quantityCfwd, int quantityDestroyed, int waste_qtyReturned, int waste_qtyDestroyed);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (ReturnListner) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(true);
        builder1.setTitle("Confirm Quantity");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.return_confirm, null);
        wasteSpinner = (Spinner) dialoglayout.findViewById(R.id.wasteSpn);
        quantitySpinner = (Spinner) dialoglayout.findViewById(R.id.spselect1);

        wasteLayout = (LinearLayout) dialoglayout.findViewById(R.id.wasteLayout);
        stockLayout = (LinearLayout) dialoglayout.findViewById(R.id.stockLayout);
        txtReasonTitle = (TextView) dialoglayout.findViewById(R.id.txtTitle);


        editwaste = (EditText) dialoglayout.findViewById(R.id.edtWasteValue);
        editQuantity = (EditText) dialoglayout.findViewById(R.id.edtQuanValue);

        Bundle args = getArguments();
        stock = args.getString("stock");
        String name = args.getString("name");
        position = args.getInt("position");
        prescriptionId = args.getInt("prescriptionId");
        final String waste = String.valueOf(args.getInt("waste"));

        Log.e("wasteVal", "" + waste);
        txtReasonTitle.setText(name);
        editQuantity.setText(stock);
        editwaste.setText(waste);

        quantitySpinner.setOnItemSelectedListener(this);
        quantityCategory.add("Select Action");
        quantityCategory.add("Return");
        quantityCategory.add("Carry Forward");
        quantityCategory.add("Destroyed");

        wasteSpinner.setOnItemSelectedListener(this);
        wasteCategory.add("Select Action");
        wasteCategory.add("Return");
        wasteCategory.add("Destroyed");
        wasteadapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, wasteCategory);
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, quantityCategory);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(R.layout.simple_spinner);
        wasteadapter.setDropDownViewResource(R.layout.simple_spinner);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setSelection(0);
        wasteSpinner.setAdapter(wasteadapter);
        wasteSpinner.setSelection(0);

        if (!waste.equals("0") && waste != null) {
            wasteLayout.setVisibility(View.VISIBLE);
            stockLayout.setVisibility(View.VISIBLE);
        } else {
            wasteLayout.setVisibility(View.GONE);
            stockLayout.setVisibility(View.VISIBLE);
        }

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantityspinner_val = quantitySpinner.getSelectedItem().toString();
                spinnertxt = (TextView) view.findViewById(R.id.sp_text);

                if (quantityCategory.get(position) != null) {
                    ((TextView) view).setTextColor(getResources().getColorStateList(R.color.white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wasteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wastespinner_val = wasteSpinner.getSelectedItem().toString();
                spinnertxt = (TextView) view.findViewById(R.id.sp_text);
                if (wasteCategory.get(position) != null) {
                    ((TextView) view).setTextColor(getResources().getColorStateList(R.color.white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        builder1.setView(dialoglayout);
        builder1.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String validation_message = "";
                        boolean validation_passed = true;

                        getQuantityvalue_val = editQuantity.getText().toString();
                        getWasteValue_val = editwaste.getText().toString();
                        quantityspinner_val = quantitySpinner.getSelectedItem().toString();
                        wastespinner_val = wasteSpinner.getSelectedItem().toString();
                        Log.e("quantityspinner_val", "setPositiveButton 1--->" + quantityspinner_val);
                        Log.e("wastespinner_val", "setPositiveButton 2--->" + wastespinner_val);

                        if (getQuantityvalue_val.length() > 0) {

                        } else {

//                            App_Constants.showAlertDialog("Alert", "You must enter a quantity", getContext(), false);
                            validation_passed = false;
                            validation_message = validation_message + "You must enter a quantity. ";
                        }


                        if (quantityspinner_val.equalsIgnoreCase("Select Action")) {
                            validation_passed = false;
                            validation_message = validation_message + "You must select an action. ";

                        }

                        if (!waste.equals("0") && waste != null) {

                            if (getWasteValue_val.length() > 0) {


                            } else {

                                validation_passed = false;
                                validation_message = validation_message + "You must enter a waste quantity. ";
//                            App_Constants.showAlertDialog("Alert", "You must enter waste quantity", getContext(), false);
                            }


                            if (wastespinner_val.equalsIgnoreCase("Select Action")) {
                                validation_passed = false;
                                validation_message = validation_message + "You must select a waste action. ";

                            }

                        }


                        if (validation_passed) {
                            int quantityvalue = Integer.parseInt(getQuantityvalue_val);
                            int wastevalue = Integer.parseInt(getWasteValue_val);
                            dialog.dismiss();

                            int quantityReturned = 0;
                            int quantityCfwd = 0;
                            int quantityDestroyed = 0;
                            int waste_qtyReturned = 0;
                            int waste_qtyDestroyed = 0;

                            if (quantityspinner_val.equals("Return")) {
                                quantityReturned = quantityReturned + (quantityvalue);
                            } else if (quantityspinner_val.equals("Carry Forward")) {
                                quantityCfwd = quantityCfwd + (quantityvalue);
                            } else if (quantityspinner_val.equals("Destroyed")) {
                                quantityDestroyed = quantityDestroyed + (quantityvalue);
                            }


                            if (!waste.equals("0") && waste != null) {
                                if (wastespinner_val.equals("Return")) {
                                    waste_qtyReturned = waste_qtyReturned + (wastevalue);
                                } else if (wastespinner_val.equals("Destroyed")) {
                                    waste_qtyDestroyed = waste_qtyDestroyed + (wastevalue);
                                }
                            }


                            int newavailable_stock = Integer.parseInt(stock) - quantityReturned - quantityCfwd - quantityDestroyed;
                            callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val, newavailable_stock, quantityReturned, quantityCfwd, quantityDestroyed, waste_qtyReturned, waste_qtyDestroyed);

//                            // Return
//                            if (quantityReturned > 0) {
//                                Log.e("quantityReturned", "--->" + quantityReturned);
//                                int newavailable_stock = Integer.parseInt(stock) - quantityReturned - quantityCfwd - quantityDestroyed;
//                                callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val, quantityReturned, "Return", newavailable_stock);
//                            }
//
//                            // Carry Forward
//                            if (quantityCfwd > 0) {
//                                Log.e("quantityCfwd", "--->" + quantityCfwd);
//                                int newavailable_stock = Integer.parseInt(stock) - quantityReturned - quantityCfwd - quantityDestroyed;
//                                callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val, quantityCfwd, "Carry Forward", newavailable_stock);
//                            }
//
//                            // Destroy
//                            if (quantityDestroyed > 0) {
//                                Log.e("quantityDestroyed", "--->" + quantityDestroyed);
//
//                                int newavailable_stock = Integer.parseInt(stock) - quantityReturned - quantityCfwd - quantityDestroyed;
//                                callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val, quantityDestroyed, "Destroyed", newavailable_stock);
//
//                            }
//
//
//                            // Waste Return
//                            if (waste_qtyReturned > 0) {
//                                Log.e("quantityReturned", "--->" + quantityReturned);
//                                int newavailable_stock = Integer.parseInt(stock) - quantityReturned - quantityCfwd - quantityDestroyed;
//                                callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val, waste_qtyReturned, "Return", newavailable_stock);
//                            }
//
//                            // Waste  Destroy
//                            if (waste_qtyDestroyed > 0) {
//                                Log.e("quantityDestroyed", "--->" + quantityDestroyed);
//
//                                int newavailable_stock = Integer.parseInt(stock) - quantityReturned - quantityCfwd - quantityDestroyed;
//                                callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val, waste_qtyDestroyed, "Destroyed", newavailable_stock);
//
//                            }


//                            callback.ReturnUpdate(quantityvalue, wastevalue, position, quantityspinner_val, wastespinner_val);
                        } else {
                            App_Constants.showAlertDialog("Alert", "" + validation_message, getContext(), false);

                        }


//                        if (confValue.equals("Select Action") && getQuantity.equals("")) {
//                            //  App_Constants.showAlertDialog("Alert", "You must select a reason.", getContext(), false);
//                            App_Constants.showAlertDialog("Alert", "You must enter a quantity. You must select an action", getContext(), false);
//                        } else if (!confValue.equals("Select Action") && getQuantity.equals("")) {
//
//                            App_Constants.showAlertDialog("Alert", "You must enter a quantity", getContext(), false);
//                        } else if (confValue.equals("Select Action") && !getQuantity.equals("")) {
//
//                            App_Constants.showAlertDialog("Alert", "You must select an action.", getContext(), false);
//                        }

//                            if (confValue.equals("Please Select")) {
//                                App_Constants.showAlertDialog("Alert", "You must select a reason.", getContext(), false);
//                            } else {


                    }
                    //  }
                });

        builder1.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.setView(dialoglayout);
        return builder1.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
