package com.syscraft.caremeds.model;

import android.util.Log;

/**
 * Created by Syscraft on 20-02-2016.
 */
public class MeasureModel {
    String id;
    String created_at;
    String deleted_at;
    String measurement_type_id;
    String patient_id;
    String updated_at;
    int value;
    String created_at_second;
    String deleted_at_second;
    String id_second;
    String name;
    String units;
    String updated_at_second;
    String isedited;


    public String isedited() {
        return isedited;
    }

    public MeasureModel(String id, String created_at, String deleted_at, String measurement_type_id, String patient_id, String updated_at, int value, String created_at_second, String deleted_at_second, String id_second, String name, String units, String updated_at_second, String is_edited) {
        this.id = id;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.measurement_type_id = measurement_type_id;
        this.patient_id = patient_id;
        this.updated_at = updated_at;
        this.value = value;
        this.created_at_second = created_at_second;
        this.deleted_at_second = deleted_at_second;
        this.id_second = id_second;
        this.name = name;
        this.units = units;
        this.updated_at_second = updated_at_second;
        this.isedited = is_edited;
    }

    public MeasureModel(String name, int sub, String units, String date, String is_edited) {
        this.name = name;
        this.value = sub;
        this.units = units;
        this.created_at = date;
        this.isedited = is_edited;
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

    public String getMeasurement_type_id() {
        return measurement_type_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getValue() {
        return value;
    }

    public String getCreated_at_second() {
        return created_at_second;
    }

    public String getDeleted_at_second() {
        return deleted_at_second;
    }

    public String getId_second() {
        return id_second;
    }

    public String getName() {
        return name;
    }

    public String getUnits() {
        return units;
    }

    public String getUpdated_at_second() {
        return updated_at_second;
    }


}
