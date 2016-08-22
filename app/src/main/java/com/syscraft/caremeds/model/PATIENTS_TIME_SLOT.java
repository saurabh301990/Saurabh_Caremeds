package com.syscraft.caremeds.model;

/**
 * Created by Deepika on 12-04-2016.
 */
public class PATIENTS_TIME_SLOT {


    String show_as;
    String slot_time;
    String color;
    boolean admin_today;

    public PATIENTS_TIME_SLOT(String show_as, String slot_time, String color, boolean admin_today) {
        this.show_as = show_as;
        this.slot_time = slot_time;
        this.color = color;
        this.admin_today = admin_today;
    }

    public String getShow_as() {
        return show_as;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public String getColor() {
        return color;
    }

    public boolean isAdmin_today() {
        return admin_today;
    }



}
