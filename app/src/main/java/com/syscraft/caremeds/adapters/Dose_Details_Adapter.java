package com.syscraft.caremeds.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.PRESCRIBED_TIME_SLOT_phase2;
import com.syscraft.caremeds.R;


import java.util.ArrayList;


public class Dose_Details_Adapter extends BaseAdapter {

    Activity context;
    ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list;
    LayoutInflater inflator;
    int cnfrmQuantity;

    public Dose_Details_Adapter(Activity context, ArrayList<PRESCRIBED_TIME_SLOT_phase2> PRES_TIME_SLOT_objects_list) {
        this.context = context;
        this.PRES_TIME_SLOT_objects_list = PRES_TIME_SLOT_objects_list;
    }

    @Override
    public int getCount() {
        return PRES_TIME_SLOT_objects_list.size();
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
            view = inflator.inflate(R.layout.dose_detail_dailog_row, null);


            holder.dose_time_text = (TextView) view.findViewById(R.id.dose_time_text);
            holder.dose_qty = (TextView) view.findViewById(R.id.dose_qty);
            holder.dose_colrView = (View) view.findViewById(R.id.dose_colrView);
            holder.outer_Dose_llout = (LinearLayout) view.findViewById(R.id.outer_Dose_llout);

            view.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) view.getTag();
        }
        holder.dose_time_text.setText(PRES_TIME_SLOT_objects_list.get(position).getShow_as() + "(" +PRES_TIME_SLOT_objects_list.get(position).getSlot_time() + ")" );
        holder.dose_qty.setText("" + PRES_TIME_SLOT_objects_list.get(position).getDose());
        holder.dose_colrView.setBackgroundColor(Color.parseColor("#" + PRES_TIME_SLOT_objects_list.get(position).getColor()));


        return view;
    }

    private static class ViewHolder {

        protected TextView dose_time_text;
        protected View dose_colrView;
        protected TextView dose_qty;
        LinearLayout outer_Dose_llout;

    }
}
