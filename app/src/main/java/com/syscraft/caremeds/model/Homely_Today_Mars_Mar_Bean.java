package com.syscraft.caremeds.model;

import java.io.Serializable;

/**
 * Created by Pankaj on 07-04-2016.
 */
public class Homely_Today_Mars_Mar_Bean implements Serializable {
    String id;
    String created_at;
    String deleted_at;
    String dose;
    String false_reason;
    String gps_location;
    String homely_remedy_id;
    String homely_remedy_name;
    String is_waste;
    String medication_id;
    String outcome;
    String patient_id;
    String prescription_id;
    String quantity_wasted;
    String secondary_user_id;
    String seconday_user_fullname;
    String slot_time;
    String uid;
    String updated_at;
    String user_fullname;
    String user_id;

    public String getAdministered_at() {
        return administered_at;
    }

    String administered_at;


    public Homely_Today_Mars_Mar_Bean(String id, String created_at, String deleted_at, String dose, String false_reason, String gps_location, String homely_remedy_id, String homely_remedy_name, String is_waste, String medication_id, String outcome, String patient_id, String prescription_id, String quantity_wasted, String secondary_user_id, String seconday_user_fullname, String slot_time, String uid, String updated_at, String user_fullname, String user_id, String administered_at) {
        this.id = id;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.dose = dose;
        this.false_reason = false_reason;
        this.gps_location = gps_location;
        this.homely_remedy_id = homely_remedy_id;
        this.homely_remedy_name = homely_remedy_name;
        this.is_waste = is_waste;
        this.medication_id = medication_id;
        this.outcome = outcome;
        this.patient_id = patient_id;
        this.prescription_id = prescription_id;
        this.quantity_wasted = quantity_wasted;
        this.secondary_user_id = secondary_user_id;
        this.seconday_user_fullname = seconday_user_fullname;
        this.slot_time = slot_time;
        this.uid = uid;
        this.updated_at = updated_at;
        this.user_fullname = user_fullname;
        this.user_id = user_id;
        this.administered_at = administered_at;
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

    public String getFalse_reason() {
        return false_reason;
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

    public String getId() {
        return id;
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

    public String getUid() {
        return uid;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public String getUser_id() {
        return user_id;
    }


}
