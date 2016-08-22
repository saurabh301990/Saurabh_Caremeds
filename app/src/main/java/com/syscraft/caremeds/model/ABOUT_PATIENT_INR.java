package com.syscraft.caremeds.model;

/**
 * Created by syscraft on 6/7/2016.
 */
public class ABOUT_PATIENT_INR {


    String id;
    String address;
    String allergies;
    String care_home_id;
    String created_at;
    String cycle_base_date;
    String cycle_duration;
    String deleted_at;
    String dob;
    String first_name;
    String gp_id;
    String gp_name;
    String inactive;
    String name;
    String nhs_number;
    String patient_notes;
    String photo_image;
    String room;
    String title;
    String updated_at;
    String _global_inr_reading;
    String _global_inr_date;
    String _global_warfarin_dose;


    public ABOUT_PATIENT_INR(String id, String address, String allergies, String care_home_id, String created_at, String cycle_base_date, String cycle_duration, String deleted_at, String dob, String first_name, String gp_id, String gp_name, String inactive, String name, String nhs_number, String patient_notes, String photo_image, String room, String title, String updated_at, String _global_inr_reading, String _global_inr_date, String _global_warfarin_dose) {
        this.id = id;
        this.address = address;
        this.allergies = allergies;
        this.care_home_id = care_home_id;
        this.created_at = created_at;
        this.cycle_base_date = cycle_base_date;
        this.cycle_duration = cycle_duration;
        this.deleted_at = deleted_at;
        this.dob = dob;
        this.first_name = first_name;
        this.gp_id = gp_id;
        this.gp_name = gp_name;
        this.inactive = inactive;
        this.name = name;
        this.nhs_number = nhs_number;
        this.patient_notes = patient_notes;
        this.photo_image = photo_image;
        this.room = room;
        this.title = title;
        this.updated_at = updated_at;
        this._global_inr_reading = _global_inr_reading;
        this._global_inr_date = _global_inr_date;
        this._global_warfarin_dose = _global_warfarin_dose;
    }



    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getCare_home_id() {
        return care_home_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getCycle_base_date() {
        return cycle_base_date;
    }

    public String getCycle_duration() {
        return cycle_duration;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getDob() {
        return dob;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getGp_id() {
        return gp_id;
    }

    public String getGp_name() {
        return gp_name;
    }

    public String getInactive() {
        return inactive;
    }

    public String getName() {
        return name;
    }

    public String getNhs_number() {
        return nhs_number;
    }

    public String getPatient_notes() {
        return patient_notes;
    }

    public String getPhoto_image() {
        return photo_image;
    }

    public String getRoom() {
        return room;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String get_global_inr_reading() {
        return _global_inr_reading;
    }

    public String get_global_inr_date() {
        return _global_inr_date;
    }

    public String get_global_warfarin_dose() {
        return _global_warfarin_dose;
    }


}
