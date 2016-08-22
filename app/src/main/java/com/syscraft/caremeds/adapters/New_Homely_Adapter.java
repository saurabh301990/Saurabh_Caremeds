package com.syscraft.caremeds.adapters;

import android.app.Activity;
import android.content.Context;
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

import com.syscraft.caremeds.New_Model.Homely_Model_Phase2;
import com.syscraft.caremeds.New_Model.Homely_Today_Mars_Mar_Bean_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.constants.App_Constants;
import com.syscraft.caremeds.dialogs.ConfirmPrnDialog;
import com.syscraft.caremeds.model.Homely_Today_Mars_Mar_Bean;
import com.syscraft.caremeds.model.New_Homely_Remedy_Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Pankaj on 07/04/2016.
 */
public class New_Homely_Adapter extends BaseAdapter {

    Context context;
    ArrayList<Homely_Model_Phase2> new_homely_List;
    LayoutInflater inflator;

    public New_Homely_Adapter(Context context, ArrayList<Homely_Model_Phase2> new_homely_List) {
        this.context = context;
        this.new_homely_List = new_homely_List;

    }

    @Override
    public int getCount() {
        return new_homely_List.size();
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

        inflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            view = inflator.inflate(R.layout.homely_remedy, null);


        TextView txttablet = (TextView) view.findViewById(R.id.tvremendy);
        TextView txtdispensed = (TextView) view.findViewById(R.id.tvDose);
        LinearLayout lConfirmQuan = (LinearLayout) view.findViewById(R.id.llConfirmQuan);
        LinearLayout ll_dose_qnty_llout = (LinearLayout) view.findViewById(R.id.ll_dose_qnty_llout);
        TextView txt_Dose_Quan = (TextView) view.findViewById(R.id.txt_Dose_Quan);
        ImageView right_icon_imgvw = (ImageView) view.findViewById(R.id.right_icon_imgvw);

        txttablet.setText(new_homely_List.get(position).getName());

        if (new_homely_List.get(position).is_edited()) {
            right_icon_imgvw.setBackgroundResource(R.drawable.green_tick);
            txt_Dose_Quan.setText(new_homely_List.get(position).getDose_to_update());

        } else {

            right_icon_imgvw.setBackgroundResource(R.drawable.ic_cab_done_holo_dark);
            txt_Dose_Quan.setText("");
        }


        if (new_homely_List.get(position).getHomely_Today_Mars_Mar_objects_list().size() == 0) {
            txtdispensed.setVisibility(View.GONE);
        } else {


            int size = new_homely_List.get(position).getHomely_Today_Mars_Mar_objects_list().size();

            String ddoss = "";
            for (int i = 0; i < size; i++) {

                Homely_Today_Mars_Mar_Bean_Phase2 h1 = new_homely_List.get(position).getHomely_Today_Mars_Mar_objects_list().get(i);
                Log.e("TODAY_MAR_LIST","NAME :--"+h1.getFullname());
                if (ddoss.isEmpty()) {
                    ddoss = "" + "DOSE: " + h1.getDose() + " " + " " + "TIME: " +
                            Date_utility.getTime(h1.getUpdated_at()) + " " + "USER: " + h1.getFullname();
                } else {
                    ddoss = ddoss + "\n" + "DOSE: " + h1.getDose() + " " + " " + "TIME: " +
                            Date_utility.getTime(h1.getUpdated_at()) + " " + "USER: " + h1.getFullname();
                }

            }
            if (new_homely_List.get(position).getHomely_Today_Mars_Mar_objects_list().size() == 0) {
                txtdispensed.setVisibility(View.GONE);
            } else {
                txtdispensed.setVisibility(View.VISIBLE);
                txtdispensed.setText(ddoss);
            }
        }

        lConfirmQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!new_homely_List.get(position).is_edited()) {
                    String name = "";

                    Bundle bld = new Bundle();
                    bld.putString("name", name);
                    bld.putInt("position", position);
                    bld.putString("tab", "home");
                    FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
                    ConfirmPrnDialog dialog = new ConfirmPrnDialog();
                    dialog.setArguments(bld);

                    Fragment fr = fm.findFragmentByTag("Check Quantity");

                    if (fr == null) {
                        dialog.show(fm, "Check Quantity");
                    }

                }else{
                    App_Constants.showAlertDialog("Alert", "Please save or cancel your changes to continue.", context, false);

                }
            }
        });
        return view;
    }

    public String getDate(String createdate) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createdate);
            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
            newDateStr = postFormater.format(date);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return newDateStr;
    }
}