package com.syscraft.caremeds.model;

import java.util.ArrayList;

/**
 * Created by Pankaj on 07-04-2016.
 */
public class New_Homely_Remedy_Model {

    String id;
    String care_home_id;
    String created_at;
    String deleted_at;
    String mandatory_warnings;
    String name;
    String updated_at;
    ArrayList<Homely_Today_Mars_Mar_Bean> homely_Today_Mars_Mar_objects_list;
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


    public New_Homely_Remedy_Model(String id, String care_home_id, String created_at, String deleted_at, String mandatory_warnings, String name, String updated_at, ArrayList<Homely_Today_Mars_Mar_Bean> homely_Today_Mars_Mar_objects_list, boolean is_edited, String dose_to_update, String Patient_id) {
        this.id = id;
        this.care_home_id = care_home_id;
        this.created_at = created_at;
        this.deleted_at = deleted_at;

        this.mandatory_warnings = mandatory_warnings;
        this.name = name;
        this.updated_at = updated_at;
        this.homely_Today_Mars_Mar_objects_list = homely_Today_Mars_Mar_objects_list;
        this.is_edited = is_edited;
        this.dose_to_update = dose_to_update;
        this.Patient_id = Patient_id;
    }


    public String getCare_home_id() {
        return care_home_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
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

    public ArrayList<Homely_Today_Mars_Mar_Bean> getHomely_Today_Mars_Mar_objects_list() {
        return homely_Today_Mars_Mar_objects_list;
    }


}
