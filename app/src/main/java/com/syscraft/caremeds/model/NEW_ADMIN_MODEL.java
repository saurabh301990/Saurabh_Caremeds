package com.syscraft.caremeds.model;

import com.syscraft.caremeds.New_Model.Homely_Model_Phase2;
import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Deepika on 13-04-2016.
 */
public class NEW_ADMIN_MODEL {


    boolean is_header;
    String header_title;
    String type;

    public String getPrescribed_time_slot() {
        return prescribed_time_slot;
    }

    String prescribed_time_slot;
    Homely_Model_Phase2 new_homely_remedy_model;
    PRESCRIPTION_Model_Phase2 admin_prescription_model;
    boolean IsSelected;

    public String getHeader_background_color() {
        return header_background_color;
    }

    String header_background_color;


    boolean is_edited;

    public void setIs_edited(boolean is_edited) {
        this.is_edited = is_edited;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setSecondary_user_id(String secondary_user_id) {
        this.secondary_user_id = secondary_user_id;
    }

    public void setIs_waste(String is_waste) {
        this.is_waste = is_waste;
    }

    public void setQuantity_wasted(String quantity_wasted) {
        this.quantity_wasted = quantity_wasted;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNotesattr(JSONArray notesattr) {
        this.notesattr = notesattr;
    }

    public void setPatient_ID(String patient_ID) {
        this.patient_ID = patient_ID;
    }

    public void setPrecription_ID(String precription_ID) {
        Precription_ID = precription_ID;
    }

    public void setAction(String action) {
        Action = action;
    }
//    String taking_action, note_type;
//    int giving_quantity, giving_reason;

    String slot_time, outcome, reason, dose, secondary_user_id, is_waste, quantity_wasted, uid;
    JSONArray notesattr;

    public String getSlot_time() {
        return slot_time;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getReason() {
        return reason;
    }

    public String getDose() {
        return dose;
    }

    public String getSecondary_user_id() {
        return secondary_user_id;
    }

    public String getIs_waste() {
        return is_waste;
    }

    public String getQuantity_wasted() {
        return quantity_wasted;
    }

    public String getUid() {
        return uid;
    }

    public JSONArray getNotesattr() {
        return notesattr;
    }

    public String getPatient_ID() {
        return patient_ID;
    }

    public String getPrecription_ID() {
        return Precription_ID;
    }

    String patient_ID, Precription_ID;

    public String getAction() {
        return Action;
    }

    String Action;

    public ArrayList<String> get_warning_overrides_list() {
        return _warning_overrides_list;
    }

    public void set_warning_overrides_list(ArrayList<String> _warning_overrides_list) {
        this._warning_overrides_list = _warning_overrides_list;
    }

    public ArrayList<String> _warning_overrides_list;

    public boolean is_edited() {
        return is_edited;
    }


//    public NEW_ADMIN_MODEL(boolean is_edited, String slot_time, String outcome, String reason, String dose, String secondary_user_id, String is_waste, String quantity_wasted, String uid, JSONArray notesattr, String pat_id, String Pre_id,String Action) {
//        this.is_edited = is_edited;
//        this.slot_time = slot_time;
//        this.outcome = outcome;
//        this.reason = reason;
//        this.dose = dose;
//        this.secondary_user_id = secondary_user_id;
//        this.is_waste = is_waste;
//        this.quantity_wasted = quantity_wasted;
//        this.uid = uid;
//        this.notesattr = notesattr;
//        this.patient_ID = pat_id;
//        this.Precription_ID = Pre_id;
//        this.Action=Action;
//    }


    public NEW_ADMIN_MODEL(boolean is_header, String header_title, String header_background_color, String prescribed_time_slot) {
        this.is_header = is_header;
        this.header_title = header_title;
        this.header_background_color = header_background_color;
        this.prescribed_time_slot = prescribed_time_slot;
        this.Action = "";
    }


    public NEW_ADMIN_MODEL(boolean is_header, String header_title, String type, String prescribed_time_slot, Homely_Model_Phase2 new_homely_remedy_model, PRESCRIPTION_Model_Phase2 admin_prescription_model, boolean isSelected, String header_background_color) {
        this.is_header = is_header;
        this.header_title = header_title;
        this.type = type;
        this.prescribed_time_slot = prescribed_time_slot;
        this.new_homely_remedy_model = new_homely_remedy_model;
        this.admin_prescription_model = admin_prescription_model;
        IsSelected = isSelected;
        this.header_background_color = header_background_color;
        this.Action = "";

        _warning_overrides_list = new ArrayList<String>();
    }


    public boolean is_header() {
        return is_header;
    }

    public String getHeader_title() {
        return header_title;
    }

    public String getType() {
        return type;
    }

    public Homely_Model_Phase2 getNew_homely_remedy_model() {
        return new_homely_remedy_model;
    }

    public PRESCRIPTION_Model_Phase2 getAdmin_prescription_model() {
        return admin_prescription_model;
    }

    public boolean isSelected() {
        return IsSelected;
    }


}
