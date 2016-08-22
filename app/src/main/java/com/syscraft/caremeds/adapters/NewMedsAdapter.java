package com.syscraft.caremeds.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.dialogs.AuditDialog;

import java.util.ArrayList;

/**
 * Created by Syscraft on 17-02-2016.
 */
public class NewMedsAdapter extends BaseAdapter {

    Context context;
    ArrayList<PRESCRIPTION_Model_Phase2> newmedsList;
    LayoutInflater inflator;
    boolean audit_enabled;

    public NewMedsAdapter(Context context, ArrayList<PRESCRIPTION_Model_Phase2> medsarraylist, boolean audit) {
        this.context = context;
        this.newmedsList = medsarraylist;
        this.audit_enabled = audit;
    }

    @Override
    public int getCount() {

        return newmedsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // ViewHolder holder = null;
//        if (convertView == null) {
            convertView = inflator.inflate(R.layout.meds_listitem, null);
//        }
        TextView txttabletname = (TextView) convertView.findViewById(R.id.tvtab);
        TextView txtforward = (TextView) convertView.findViewById(R.id.txt_forward);
        TextView txtcheckin = (TextView) convertView.findViewById(R.id.txt_checkin);
        TextView txtwasted = (TextView) convertView.findViewById(R.id.txt_wasted);
        TextView txtadmin = (TextView) convertView.findViewById(R.id.txt_admin);
        TextView txtstock = (TextView) convertView.findViewById(R.id.txt_stock);
        TextView audit_btn = (TextView) convertView.findViewById(R.id.audit_btn);


        txttabletname.setText(newmedsList.get(position).getDrug_name());
        txtforward.setText("BFWD: " + newmedsList.get(position).getBrought_forward_quantity());
        txtcheckin.setText("CHKIN: " + newmedsList.get(position).getChecked_in_quantity());
        txtwasted.setText("W'ST: " + newmedsList.get(position).getWasted_quantity());
        txtadmin.setText("ADMIN: " + newmedsList.get(position).getAdministered_quantity());
        txtstock.setText("STOCK: " + newmedsList.get(position).getAvailable_quantity());

        if (newmedsList.get(position).isedited_in_meds()) {

            if (newmedsList.get(position).isMeds_Bforword_bool()) {
                txtforward.setTextColor(Color.parseColor("#39913b"));

            }else {
                txtforward.setTextColor(Color.parseColor("#FFFFFF"));
            }

            if (newmedsList.get(position).isMeds_Checkedin_bool()) {
                txtcheckin.setTextColor(Color.parseColor("#39913b"));

            }else {
                txtcheckin.setTextColor(Color.parseColor("#FFFFFF"));
            }

            if (newmedsList.get(position).isMeds_Waste_bool()) {
                txtwasted.setTextColor(Color.parseColor("#39913b"));

            }else {
                txtwasted.setTextColor(Color.parseColor("#FFFFFF"));
            }

            if (newmedsList.get(position).isMeds_stock_bool()) {
                txtstock.setTextColor(Color.parseColor("#39913b"));

            }else {
                txtstock.setTextColor(Color.parseColor("#FFFFFF"));
            }

//            holder.txtchecked.setTextColor(Color.parseColor("#39913b"));

        } else {
//            holder.txtchecked.setTextColor(Color.parseColor("#FFFFFF"));
//            holder.disCheck.setVisibility(View.GONE);
        }


        if (audit_enabled) {

            audit_btn.setVisibility(View.VISIBLE);

        } else {

            audit_btn.setVisibility(View.GONE);
        }


        audit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuditDialog auditDialog = new AuditDialog();
                Bundle bld = new Bundle();
                bld.putString("name", newmedsList.get(position).getDrug_name());
                bld.putInt("position", position);
                bld.putInt("Brought_forward_quantity", newmedsList.get(position).getBrought_forward_quantity());
                bld.putInt("Checked_in_quantity", newmedsList.get(position).getChecked_in_quantity());
                bld.putInt("Wasted_quantity", newmedsList.get(position).getWasted_quantity());
                bld.putInt("Administered_quantity", newmedsList.get(position).getAdministered_quantity());
                bld.putInt("Available_quantity", newmedsList.get(position).getAvailable_quantity());
                auditDialog.setArguments(bld);


                auditDialog.setCancelable(false);

                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                Fragment fr = fm.findFragmentByTag("Audit");

                if (fr == null) {
                    auditDialog.show(fm, "Audit");
                }
            }
        });


        return convertView;
    }


}
