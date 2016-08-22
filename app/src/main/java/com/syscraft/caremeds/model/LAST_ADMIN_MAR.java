package com.syscraft.caremeds.model;

import java.io.Serializable;

/**
 * Created by Deepika on 06-04-2016.
 */
public class LAST_ADMIN_MAR implements Serializable {

    String created_at;
    String deleted_at;
    String dose;
    String false_reason;
    String gps_location;
    String homely_remedy_id;
    String homely_remedy_name;
    String mar_id;
    String is_waste;
    String medication_id;
    String outcome;
    String patient_id;
    String prescription_id;
    String quantity_wasted;
    String secondary_user_id;
    String seconday_user_fullname;
    String slot_time;
    String updated_at;
    String uid;
    String user_fullname;
    String user_id;


    public LAST_ADMIN_MAR(String created_at, String deleted_at, String dose, String false_reason, String gps_location, String homely_remedy_id, String homely_remedy_name, String mar_id, String is_waste, String medication_id, String outcome, String prescription_id, String patient_id, String quantity_wasted, String secondary_user_id, String seconday_user_fullname, String updated_at, String slot_time, String uid, String user_fullname, String user_id) {
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.dose = dose;
        this.false_reason = false_reason;
        this.gps_location = gps_location;
        this.homely_remedy_id = homely_remedy_id;
        this.homely_remedy_name = homely_remedy_name;
        this.mar_id = mar_id;
        this.is_waste = is_waste;
        this.medication_id = medication_id;
        this.outcome = outcome;
        this.prescription_id = prescription_id;
        this.patient_id = patient_id;
        this.quantity_wasted = quantity_wasted;
        this.secondary_user_id = secondary_user_id;
        this.seconday_user_fullname = seconday_user_fullname;
        this.updated_at = updated_at;
        this.slot_time = slot_time;
        this.uid = uid;
        this.user_fullname = user_fullname;
        this.user_id = user_id;
    }


    public String getUser_id() {
        return user_id;
    }

    public String getFalse_reason() {
        return false_reason;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getDose() {
        return dose;
    }

    public String getGps_location() {
        return gps_location;
    }

    public String getHomely_remedy_id() {
        return homely_remedy_id;
    }

    public String getHomely_remedy_name() {
        return homely_remedy_name;
    }

    public String getMar_id() {
        return mar_id;
    }

    public String getIs_waste() {
        return is_waste;
    }

    public String getMedication_id() {
        return medication_id;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public String getQuantity_wasted() {
        return quantity_wasted;
    }

    public String getSecondary_user_id() {
        return secondary_user_id;
    }

    public String getSeconday_user_fullname() {
        return seconday_user_fullname;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getUid() {
        return uid;
    }

    public String getUser_fullname() {
        return user_fullname;
    }


}
