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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.dialogs.ConfirmQuantityDialog;
import com.syscraft.caremeds.dialogs.DoseDetailsDialog;

import java.util.ArrayList;

/**
 * Created by Syscraft on 17-02-2016.
 */
public class New_CheckinAdapter extends BaseAdapter {

    Context context;
    ArrayList<PRESCRIPTION_Model_Phase2> newchecklist;
    LayoutInflater inflator;
    int cnfrmQuantity;

    public New_CheckinAdapter(Context context, ArrayList<PRESCRIPTION_Model_Phase2> newchecklist) {
        this.context = context;
        this.newchecklist = newchecklist;
    }

    @Override
    public int getCount() {
        return newchecklist.size();
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
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            holder = new ViewHolder();
            view = inflator.inflate(R.layout.checkin_listitem, null);

            holder.txttablet = (TextView) view.findViewById(R.id.tvtablet);
            holder.txtdispensed = (TextView) view.findViewById(R.id.tvdispensed);
            holder.txtavailable = (TextView) view.findViewById(R.id.tvavailable);
            holder.txtchecked = (TextView) view.findViewById(R.id.tvchecked);
            holder.llConfirmQuan = (RelativeLayout) view.findViewById(R.id.llConfirmQuan);
            holder.llCheckInQuan = (LinearLayout) view.findViewById(R.id.llCheckIn);
            holder.disCheck = (View) view.findViewById(R.id.disCheck);
            view.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) view.getTag();
        }
        holder.txttablet.setText(newchecklist.get(position).getDrug_name());
        holder.txtdispensed.setText("Dispensed - " + newchecklist.get(position).getDispensed_quantity());
        holder.txtavailable.setText("Available - " + newchecklist.get(position).getAvailable_quantity());

        if (newchecklist.get(position).isSelected().equalsIgnoreCase("true")) {
            holder.txtchecked.setTextColor(Color.parseColor("#39913b"));
            holder.disCheck.setVisibility(View.VISIBLE);
        } else {
            holder.txtchecked.setTextColor(Color.parseColor("#FFFFFF"));
            holder.disCheck.setVisibility(View.GONE);
        }
        holder.txtchecked.setText("Checked In - " + newchecklist.get(position).getChecked_in_quantity());

        holder.llConfirmQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!newchecklist.get(position).isSelected().equalsIgnoreCase("true")) {
                    String name = newchecklist.get(position).getDrug_name();
                    int value = newchecklist.get(position).getDispensed_quantity();
                    int checkinVal = newchecklist.get(position).getChecked_in_quantity();
                    cnfrmQuantity = value - checkinVal;

                    Bundle bld = new Bundle();
                    bld.putString("name", name);
                    bld.putInt("cnfrmQuantity", cnfrmQuantity);
                    bld.putInt("position", position);
                    bld.putString("tab", "checkin");
                    bld.putInt("Dispensed", value);
                    FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                    ConfirmQuantityDialog dialog = new ConfirmQuantityDialog();
                    dialog.setArguments(bld);
                    Fragment fr = fm.findFragmentByTag("Check Quantity");

                    if (fr == null) {
                        dialog.show(fm, "Check Quantity");
                    }

                }
            }
        });

        holder.llCheckInQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newchecklist.get(position).getDrug_name();
                String instruction = newchecklist.get(position).getInstructions();
                String ins_mendatory = newchecklist.get(position).getMandatory_instructions();
                String indication = newchecklist.get(position).getIndications();
                String imageUrl = newchecklist.get(position).getFront_image_url();


//                String fullNmae = null;
//                try {
//                    fullNmae = newchecklist.get(position).getLast_admin_object().getUser_fullname();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                ArrayList<PRESCRIBED_TIME_SLOT> PRES_TIME_SLOT_objects_list = newchecklist.get(position).getPRES_TIME_SLOT_objects_list();
//                ArrayList<Today_Mars_Mar_Bean> Today_Mars_Mar_objects_list = newchecklist.get(position).getToday_Mars_Mar_objects_list();
//                LAST_ADMIN_MAR l_ad_mar = newchecklist.get(position).getLast_admin_object();

                String lastadmin = newchecklist.get(position).getLast_admin();
                String Prescribed_time_slots_array = newchecklist.get(position).getPrescribed_time_slots_array();



                Bundle bld = new Bundle();

                bld.putString("name", value);
                bld.putString("instruction", instruction);
                bld.putString("ins_mendatory", ins_mendatory);
                bld.putString("indication", indication);
//                bld.putString("show_as", show_as);
                bld.putInt("position", position);
                bld.putString("imageUrl", imageUrl);
                bld.putString("lastadmin", lastadmin);
                bld.putString("Prescribed_time_slots_array", Prescribed_time_slots_array);
//                bld.putSerializable("PRES_TIME_SLOT_objects_list", PRES_TIME_SLOT_objects_list);
//                bld.putSerializable("Today_Mars_Mar_objects_list", Today_Mars_Mar_objects_list);
//                bld.putSerializable("last_admin_mar", l_ad_mar);
//                bld.putString("fullNmae", fullNmae);

                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                DoseDetailsDialog dialog = new DoseDetailsDialog();
                dialog.setArguments(bld);
                Fragment fr = fm.findFragmentByTag("Check");

                if (fr == null) {
                    dialog.show(fm, "Check");
                }

            }
        });
        return view;
    }

    private static class ViewHolder {

        protected TextView txttablet;
        protected TextView txtdispensed;
        protected TextView txtavailable;
        protected TextView txtchecked;
        RelativeLayout llConfirmQuan;
        LinearLayout llCheckInQuan;
        View disCheck;
    }
}
