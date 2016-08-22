package com.syscraft.caremeds.New_Model;

import java.io.Serializable;

/**
 * Created by Deepika on 05-04-2016.
 */
public class PRESCRIBED_TIME_SLOT_phase2 implements Serializable{

    String id;
    String slot_time;
    String show_as;
    String color;
    String dose;
    String updated_at;
    //    String created_at;
//    String deleted_at;
    String prescription_id;


    public PRESCRIBED_TIME_SLOT_phase2(String id, String slot_time, String show_as, String color, String dose, String updated_at, String prescription_id) {
        this.id = id;
        this.slot_time = slot_time;
        this.show_as = show_as;
        this.color = color;
        this.dose = dose;
        this.updated_at = updated_at;
        this.prescription_id = prescription_id;
    }








//    public String getCreated_at() {
//        return created_at;
//    }

    public String getColor() {
        return color;
    }

    public String getDose() {
        return dose;
    }

//    public String getDeleted_at() {
//        return deleted_at;
//    }

    public String getId() {
        return id;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public String getShow_as() {
        return show_as;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public String getUpdated_at() {
        return updated_at;
    }


}
