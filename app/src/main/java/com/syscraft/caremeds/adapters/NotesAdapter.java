package com.syscraft.caremeds.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syscraft.caremeds.New_Model.NotesModel_Phase2;
import com.syscraft.caremeds.R;
import com.syscraft.caremeds.model.NotesModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Syscraft on 17-02-2016.
 */
public class NotesAdapter extends BaseAdapter {

    Context context;
    ArrayList<NotesModel_Phase2> notesarraylist = new ArrayList<NotesModel_Phase2>();
    LayoutInflater inflator;

    public NotesAdapter(Context context, ArrayList<NotesModel_Phase2> notesarraylist) {
        this.context = context;
        this.notesarraylist = notesarraylist;

    }

    @Override
    public int getCount() {

        return notesarraylist.size();
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
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflator.inflate(R.layout.notes_listitem, null);
            holder.textNote = (TextView) convertView.findViewById(R.id.tvnote_name);
            holder.textNote_time = (TextView) convertView.findViewById(R.id.tvnote_time);
            holder.admin = (TextView) convertView.findViewById(R.id.tvadmin_name);
            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (Holder) convertView.getTag();
        }

        String newDateStr = "";
        try {
            if (notesarraylist.get(position).getCreated_at() != null) {
                newDateStr = getDate(notesarraylist.get(position).getCreated_at());
            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        holder.textNote.setText(notesarraylist.get(position).getSubject());
        holder.textNote_time.setText(newDateStr);
        holder.admin.setText(notesarraylist.get(position).getFullname());

        return convertView;
    }

    public String getDate(String time) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(time);
            SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
            newDateStr = postFormater.format(date);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return newDateStr;
    }


    class Holder {
        protected TextView textNote;
        protected TextView textNote_time;
        protected TextView admin;
    }


}


