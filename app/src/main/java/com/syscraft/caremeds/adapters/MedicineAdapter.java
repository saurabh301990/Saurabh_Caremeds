package com.syscraft.caremeds.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by Syscraft on 18-02-2016.
 */
public class MedicineAdapter extends ArrayAdapter<String> {
    private int resource;
    private List<String> medicineitems;

    public MedicineAdapter(Context context, int resource, List<String> medicineitems) {
        super(context, resource);
        this.resource = resource;
        this.medicineitems = medicineitems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        final String item = getItem(position);

        if (convertView == null) {
            itemView = new LinearLayout(getContext());

            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            layoutInflater.inflate(resource, itemView, true);
        } else {
            itemView = (LinearLayout) convertView;
        }

        return itemView;
    }
}
