package com.syscraft.caremeds.New_Model;

import com.syscraft.caremeds.model.Homely_Today_Mars_Mar_Bean;

import java.util.ArrayList;

/**
 * Created by Pankaj on 07-04-2016.
 */
public class Homely_Model_Phase2 {

    String id;
    String mandatory_warnings;
    String name;
    String updated_at;
    ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> homely_Today_Mars_Mar_objects_list;
    String dose_to_update;
    boolean is_edited;
    String Patient_id;

    public String getPatient_id() {
        return Patient_id;
    }

    public String getDose_to_update() {
        return dose_to_update;
    }

    public void setDose_to_update(String dose_to_update) {
        this.dose_to_update = dose_to_update;
    }

    public boolean is_edited() {
        return is_edited;
    }

    public void setIs_edited(boolean is_edited) {
        this.is_edited = is_edited;
    }


    public Homely_Model_Phase2(String id, String mandatory_warnings, String name, String updated_at, ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> homely_Today_Mars_Mar_objects_list, boolean is_edited, String dose_to_update, String Patient_id) {
        this.id = id;
        this.mandatory_warnings = mandatory_warnings;
        this.name = name;
        this.updated_at = updated_at;
        this.homely_Today_Mars_Mar_objects_list = homely_Today_Mars_Mar_objects_list;
        this.is_edited = is_edited;
        this.dose_to_update = dose_to_update;
        this.Patient_id = Patient_id;
    }

    public String getId() {
        return id;
    }

    public String getMandatory_warnings() {
        return mandatory_warnings;
    }

    public String getName() {
        return name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> getHomely_Today_Mars_Mar_objects_list() {
        return homely_Today_Mars_Mar_objects_list;
    }


}
