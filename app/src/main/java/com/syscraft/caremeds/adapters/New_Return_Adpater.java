package com.syscraft.caremeds.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.dialogs.DoseDetailsDialog;
import com.syscraft.caremeds.dialogs.ReturnDialog;

import java.util.ArrayList;

/**
 * Created by Syscraft on 3/14/2016.
 */

public class New_Return_Adpater extends BaseAdapter {
    LayoutInflater inflator;
    Context context;
    ArrayList<PRESCRIPTION_Model_Phase2> new_return_List;

    public New_Return_Adpater(Context context, ArrayList<PRESCRIPTION_Model_Phase2> new_return_List) {
        this.context = context;
        this.new_return_List = new_return_List;
    }

    @Override
    public int getCount() {
        return new_return_List.size();
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
        convertView = inflator.inflate(R.layout.return_item, null);
        TextView txttabletname = (TextView) convertView.findViewById(R.id.tvtab);
        TextView txtforward = (TextView) convertView.findViewById(R.id.txt_forward);
        TextView txtcheckin = (TextView) convertView.findViewById(R.id.txt_disp);
        TextView txtwasted = (TextView) convertView.findViewById(R.id.txt_wasted);
        TextView txtadmin = (TextView) convertView.findViewById(R.id.txt_admin);
        TextView txtstock = (TextView) convertView.findViewById(R.id.txt_stock);
        TextView carriedforard = (TextView) convertView.findViewById(R.id.txt_cfwd);
        TextView txtret = (TextView) convertView.findViewById(R.id.txt_ret);
        TextView txtdest = (TextView) convertView.findViewById(R.id.txt_dest);
        LinearLayout lldoseDialog = (LinearLayout) convertView.findViewById(R.id.llReturn);
        LinearLayout confirmDialog = (LinearLayout) convertView.findViewById(R.id.llConfirmQuan);
        View disCheck = (View) convertView.findViewById(R.id.disCheck);

        ImageView return_tick_icon = (ImageView) convertView.findViewById(R.id.return_tick_icon);

        final PRESCRIPTION_Model_Phase2 n1 = new_return_List.get(position);
//        Double STOCK = Double.parseDouble("" + n1.getDispensed_quantity()) + Double.parseDouble("" + n1.getBrought_forward_quantity()) - Double.parseDouble("" + n1.getWasted_quantity()) - Double.parseDouble("" + n1.getAdministered_quantity()) - Double.parseDouble("" + n1.getCarried_forward_quantity()) - Double.parseDouble("" + n1.getReturned_quantity()) - Double.parseDouble("" + n1.getDestroyed_quantity());

        txttabletname.setText(n1.getDrug_name());
        txtforward.setText("BFWD: " + n1.getBrought_forward_quantity());
        txtcheckin.setText("DISP: " + n1.getDispensed_quantity());
        txtwasted.setText("W'ST: " + n1.getWasted_quantity());
        txtadmin.setText("ADMIN: " + n1.getAdministered_quantity());
        txtstock.setText("STOCK: " + n1.getAvailable_quantity());
//        txtstock.setText("STOCK: " + STOCK);
        carriedforard.setText("C'FWD:" + n1.getCarried_forward_quantity());
        txtret.setText("RET:" + n1.getReturned_quantity());
        txtdest.setText("DEST:" + n1.getDestroyed_quantity());

        if (n1.isSelected_inreturn()) {
            Log.e(" Position", position+ " --->" + n1.getAction_applied());
            if ("Return".equals(n1.getAction_applied())) {
                Log.e(" case",  " --->111111111");
                txtret.setTextColor(Color.parseColor("#39913b"));
            } else if ("Carry Forward".equals(n1.getAction_applied())) {
                Log.e(" case",  " --->22222222");
                carriedforard.setTextColor(Color.parseColor("#39913b"));
            } else if ("Destroyed".equals(n1.getAction_applied())) {
                Log.e(" case",  " --->3333333");
                txtdest.setTextColor(Color.parseColor("#39913b"));
            }


            if ("Return".equals(n1.getWaste_Action_applied())) {
                Log.e(" case",  " --->111111111");
                txtret.setTextColor(Color.parseColor("#39913b"));
            }  else if ("Destroyed".equals(n1.getWaste_Action_applied())) {
                Log.e(" case",  " --->3333333");
                txtdest.setTextColor(Color.parseColor("#39913b"));
            }

            return_tick_icon.setBackgroundResource(R.drawable.grey_tick);

            disCheck.setVisibility(View.VISIBLE);
        } else {
            txtret.setTextColor(Color.parseColor("#ffffff"));
            return_tick_icon.setBackgroundResource(R.drawable.ic_cab_done_holo_dark);
            disCheck.setVisibility(View.GONE);
        }


        lldoseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = n1.getDrug_name();
                String imageUrl = n1.getFront_image_url();
                String instruction = n1.getInstructions();
                String ins_mendatory = n1.getMandatory_instructions();
                String indication = n1.getIndications();

                String lastadmin = n1.getLast_admin();
                String Prescribed_time_slots_array = n1.getPrescribed_time_slots_array();


//                String dose_mar = "";
//                try {
//                    dose_mar = n1.getLast_admin_object().getDose();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
////                String adminLast = new_return_List.get(position).getLast_admin_object().toString();
//                String createdAt_mar = "";
//                try {
//                    createdAt_mar = n1.getLast_admin_object().getCreated_at();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                String fullNmaeMar = "";
//                try {
//                    fullNmaeMar = n1.getLast_admin_object().getUser_fullname();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                Bundle bld = new Bundle();
                bld.putString("name", value);
                bld.putString("imageUrl", imageUrl);
                bld.putString("instruction", instruction);
                bld.putString("ins_mendatory", ins_mendatory);
                bld.putString("indication", indication);
                bld.putString("lastadmin", lastadmin);
                bld.putString("Prescribed_time_slots_array", Prescribed_time_slots_array);
//                bld.putSerializable("PRES_TIME_SLOT_objects_list", n1.getPRES_TIME_SLOT_objects_list());
//                bld.putSerializable("Today_Mars_Mar_objects_list", n1.getToday_Mars_Mar_objects_list());
//                bld.putSerializable("last_admin_mar", n1.getLast_admin_object());
//                bld.putString("dose", dose_mar);
//                bld.putString("createdAt", createdAt_mar);
//                bld.putString("fullNmae", fullNmaeMar);
                DoseDetailsDialog dialog = new DoseDetailsDialog();
                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                dialog.setArguments(bld);
                Fragment fr = fm.findFragmentByTag("Return");

                if (fr == null) {
                    dialog.show(fm, "Return");
                }

            }
        });

        confirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!n1.isSelected_inreturn()) {
                    String value = n1.getDrug_name();
                    int stock = n1.getAvailable_quantity();
                    int waste = n1.getWasted_quantity();
                    Bundle bld = new Bundle();
                    bld.putString("name", value);
                    bld.putString("stock", "" + stock);
                    bld.putInt("waste", waste);
                    bld.putInt("position", position);
                    bld.putInt("prescriptionId", Integer.parseInt("" + n1.getId()));
                    FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                    ReturnDialog dialog = new ReturnDialog();
                    dialog.setArguments(bld);
                    // dialog.show(fm,"Check");

                    Fragment fr1 = fm.findFragmentByTag("Return123");

                    if (fr1 == null) {
                        dialog.show(fm, "Return123");
                    }

                }
            }
        });


        return convertView;
    }
}
