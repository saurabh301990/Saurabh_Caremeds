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
import android.widget.EditText;
import android.widget.TextView;

import com.syscraft.caremeds.R;
import com.syscraft.caremeds.constants.App_Constants;

/**
 * Created by Syscraft on 19-02-2016.
 */
public class AuditDialog extends DialogFragment {
    TextView txtQuanTitle;

    private Audit_Listener callback;

    public interface Audit_Listener {
        public void Audit_Update(int post, int bfwdQuantity, int chkinQuantity, int wstQuantity, int newstock, boolean c_bool1, boolean bfrd_bool2, boolean wast_bool3, boolean stock_bool4);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (Audit_Listener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnAddFriendListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);
        builder1.setCancelable(false);
        builder1.setTitle("Audit");
        builder1.setPositiveButton("Confirm", null);
//        builder1.setNegativeButton("cancel", null);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.audit, null);


        Bundle args = getArguments();
        String currentName = args.getString("name");
        final int post = args.getInt("position");
        final int Brought_forward_quantity = args.getInt("Brought_forward_quantity");
        final int Checked_in_quantity = args.getInt("Checked_in_quantity");
        final int Wasted_quantity = args.getInt("Wasted_quantity");
        final int Administered_quantity = args.getInt("Administered_quantity");
        final int Available_quantity = args.getInt("Available_quantity");


        final EditText checkedin_edtQuanValue = (EditText) dialoglayout.findViewById(R.id.checkedin_edtQuanValue);
        final EditText bforward_edtQuanValue = (EditText) dialoglayout.findViewById(R.id.bforward_edtQuanValue);
        final EditText waste_edtQuanValue = (EditText) dialoglayout.findViewById(R.id.waste_edtQuanValue);

        TextView txtQuanTitle = (TextView) dialoglayout.findViewById(R.id.txtQuanTitle);


        builder1.setView(dialoglayout);

        txtQuanTitle.setText(currentName);

        checkedin_edtQuanValue.setText("" + Checked_in_quantity);
        bforward_edtQuanValue.setText("" + Brought_forward_quantity);
        waste_edtQuanValue.setText("" + Wasted_quantity);

        // Negative Button
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do something else
                dismiss();
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
                        // TODO Do something
                        String errorMsg = "There Were Problems !\n";
                        String error = "";

                        boolean updateFlag = false;
                        boolean c_bool1 = false;
                        boolean bfrd_bool2 = false;
                        boolean wast_bool3 = false;
                        boolean stock_bool4 = false;

                        String newbfrd = bforward_edtQuanValue.getText().toString();
                        String newcheckin = checkedin_edtQuanValue.getText().toString();
                        String newwaste = waste_edtQuanValue.getText().toString();

                        if ((newbfrd.length() > 0) && (newwaste.length() > 0)) {

                            int bfwdQuantity = Integer.parseInt(newbfrd);
                            int chkinQuantity = Integer.parseInt(newcheckin);
                            int wstQuantity = Integer.parseInt(newwaste);

                            if (chkinQuantity != Checked_in_quantity) {
                                updateFlag = true;
                                c_bool1 = true;
                            }

                            if (bfwdQuantity != Brought_forward_quantity) {
                                updateFlag = true;
                                bfrd_bool2 = true;
                            }

                            if (wstQuantity != Wasted_quantity) {
                                updateFlag = true;
                                wast_bool3 = true;
                            }


                            if (bfwdQuantity + chkinQuantity - wstQuantity - Administered_quantity != Available_quantity) {
                                int text = bfwdQuantity + chkinQuantity - wstQuantity - Administered_quantity;

                                stock_bool4 = true;
//                                Log.e("text", "text---->" + text);

                            }

                            if (updateFlag) {

                                dialog.dismiss();

                                Log.e("text", "bfwdQuantity---->" + bfwdQuantity);
                                Log.e("text", "chkinQuantity---->" + chkinQuantity);
                                Log.e("text", "wstQuantity---->" + wstQuantity);
                                int newstock = bfwdQuantity + chkinQuantity - wstQuantity - Administered_quantity;

                                Log.e("text", "newstock---->" + newstock);

                                callback.Audit_Update(post, bfwdQuantity, chkinQuantity, wstQuantity, newstock, c_bool1, bfrd_bool2, wast_bool3, stock_bool4);

                            }


                        } else {

                            error = error + "Values can't be blank,";
                            App_Constants.showAlertDialog("Alert", errorMsg + error, getContext(), false);
                        }

                    }
                });
            }
        });
        return mAlertDialog;
    }
}
