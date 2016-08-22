package com.syscraft.caremeds.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.MeasureModel_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.model.MeasureModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Syscraft on 17-02-2016.
 */
public class MeasureAdapter extends BaseAdapter {

    Context context;
    ArrayList<MeasureModel_Phase2> measurearraylist = new ArrayList<MeasureModel_Phase2>();
    LayoutInflater inflator;

    public MeasureAdapter(Context context, ArrayList<MeasureModel_Phase2> measurearraylist) {
        this.context = context;
        this.measurearraylist = measurearraylist;
    }

    @Override
    public int getCount() {
        return measurearraylist.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      /*  ViewHolder holder = null;*/
//        if (convertView == null) {
            //holder = new ViewHolder();
            convertView = inflator.inflate(R.layout.measure_listitem, null);
            // convertView.setTag(holder);
//        }

        TextView textType = (TextView) convertView.findViewById(R.id.tvtype);
        TextView textQuantity = (TextView) convertView.findViewById(R.id.tvquantity);
        TextView textTime = (TextView) convertView.findViewById(R.id.tvtime);


        if (measurearraylist.get(position).isedited().equalsIgnoreCase("true")) {
            textQuantity.setTextColor(Color.parseColor("#39913b"));

        } else {

            textQuantity.setTextColor(Color.parseColor("#ffffff"));
        }

        String time = measurearraylist.get(position).getUpdated_at();

        Log.e("time", position + "getCreated_at-->" + time);
        try {
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
            String newDateStr = "";
            Log.e("time", position + "-->" + time);
            if (time != null) {
                Date date = form.parse(time);
                SimpleDateFormat postFormater = new SimpleDateFormat("EEE MMM dd yyyy");
                newDateStr = postFormater.format(date);
                textTime.setText(newDateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        textType.setText(measurearraylist.get(position).getName());
        Log.e("time", position + "getName-->" + measurearraylist.get(position).getName());

//        if ("Blood Sugar".equals(measurearraylist.get(position).getName())) {
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("BMI".equals(measurearraylist.get(position).getName())) {
//            textQuantity.setText(String.valueOf(measurearraylist.get(position).getValue()));
//        } else if ("BP  Systolic".equals(measurearraylist.get(position).getName())) {
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("BP Diastolic".equals(measurearraylist.get(position).getName())) {
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("Height".equals(measurearraylist.get(position).getName())) {
//
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("INR".equals(measurearraylist.get(position).getName())) {
//
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("Lithium".equals(measurearraylist.get(position).getName())) {
//
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("Pulse".equals(measurearraylist.get(position).getName())) {
//
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        } else if ("Weight".equals(measurearraylist.get(position).getName())) {
//
//            textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
//        }
        textQuantity.setText(measurearraylist.get(position).getValue() + " " + measurearraylist.get(position).getUnits());
        Log.e("time", position + "getValue-->" + measurearraylist.get(position).getValue());
        Log.e("time", position + "getUnits-->" + measurearraylist.get(position).getUnits());
        //  textTime.setText(measurearraylist.get(position).getMeasureTime());
        return convertView;
    }

    class ViewHolder {
        // protected ImageView itemName;
        protected TextView textType;
        protected TextView textQuantity;
        protected TextView textTime;

    }


}

