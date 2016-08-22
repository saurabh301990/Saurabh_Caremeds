package com.syscraft.caremeds.New_Model;

/**
 * Created by Syscraft on 20-02-2016.
 */
public class MeasureModel_Phase2 {
    String id;
    int value;
    String updated_at;
    String measurement_type_id;
    String name;
    String units;
    String updated_at_second;
    String created_at_second;
    String patient_id;
    String isedited;

    public String isedited() {
        return isedited;
    }


    public MeasureModel_Phase2(String id, int value, String updated_at, String measurement_type_id, String name, String units, String updated_at_second, String created_at_second, String patient_id, String isedited) {
        this.id = id;
        this.value = value;
        this.updated_at = updated_at;
        this.measurement_type_id = measurement_type_id;
        this.name = name;
        this.units = units;
        this.updated_at_second = updated_at_second;
        this.created_at_second = created_at_second;
        this.patient_id = patient_id;
        this.isedited = isedited;
    }



    public MeasureModel_Phase2(String name, int sub, String units, String date, String is_edited) {
        this.name = name;
        this.value = sub;
        this.units = units;
        this.updated_at = date;
        this.isedited = is_edited;
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
