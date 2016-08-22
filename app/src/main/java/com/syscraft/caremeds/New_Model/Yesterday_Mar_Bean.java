package com.syscraft.caremeds.New_Model;

/**
 * Created by syscraft on 8/12/2016.
 */
public class Yesterday_Mar_Bean {


    String id;
    String administered_at;
    String dose;
    String false_reason;
    String is_waste;
    String outcome;
    String quantity_wasted;
    String slot_time;
    String updated_at;
    String userid;
    String username;
    String fullname;
    String email;
    String homely_id, homely_name, prescription_id;
    String patientid;
    String secondry_userid;
    String secondry_username;
    String secondry_fullname;
    String secondry_email;


    public Yesterday_Mar_Bean(String id, String administered_at, String dose, String false_reason, String is_waste, String outcome, String quantity_wasted, String slot_time, String updated_at, String userid, String username, String fullname, String email, String homely_id, String homely_name, String prescription_id, String patientid, String secondry_userid, String secondry_username, String secondry_fullname, String secondry_email) {
        this.id = id;
        this.administered_at = administered_at;
        this.dose = dose;
        this.false_reason = false_reason;
        this.is_waste = is_waste;
        this.outcome = outcome;
        this.quantity_wasted = quantity_wasted;
        this.slot_time = slot_time;
        this.updated_at = updated_at;
        this.userid = userid;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.homely_id = homely_id;
        this.homely_name = homely_name;
        this.prescription_id = prescription_id;
        this.patientid = patientid;
        this.secondry_userid = secondry_userid;
        this.secondry_username = secondry_username;
        this.secondry_fullname = secondry_fullname;
        this.secondry_email = secondry_email;
    }


    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdministered_at() {
        return administered_at;
    }

    public void setAdministered_at(String administered_at) {
        this.administered_at = administered_at;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getFalse_reason() {
        return false_reason;
    }

    public void setFalse_reason(String false_reason) {
        this.false_reason = false_reason;
    }

    public String getIs_waste() {
        return is_waste;
    }

    public void setIs_waste(String is_waste) {
        this.is_waste = is_waste;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getQuantity_wasted() {
        return quantity_wasted;
    }

    public void setQuantity_wasted(String quantity_wasted) {
        this.quantity_wasted = quantity_wasted;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomely_id() {
        return homely_id;
    }

    public String getHomely_name() {
        return homely_name;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public void setHomely_id(String homely_id) {
        this.homely_id = homely_id;
    }

    public void setHomely_name(String homely_name) {
        this.homely_name = homely_name;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }


    public String getSecondry_fullname() {
        return secondry_fullname;
    }

    public String getSecondry_userid() {
        return secondry_userid;
    }

    public String getSecondry_username() {
        return secondry_username;
    }

    public String getSecondry_email() {
        return secondry_email;
    }


}
