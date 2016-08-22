package com.syscraft.caremeds.New_Model;


public class Patient_Model_Phase2 {

    String id;
    String fullname;
    String title;
    String first_name;
    String name;
    String address;
    String dob;
    String nhs_number;
    String allergies;
    String gp_name;
    String room;
    String cycle_duration;
    String patient_notes;
    String is_absent;
    String current_absence_start;
    String current_absence_end;
    String inr_reading;
    String inr_date;
    String warfarin_dose;
    String photo_image;
    String updated_at;
    String last_bs_site;
    String prescribed_time_slots_for_patient_list_array;
    String current_absence_reason;

    public String getPrescribed_time_slots_for_patient_list_array() {
        return prescribed_time_slots_for_patient_list_array;
    }


    public Patient_Model_Phase2(String id, String fullname, String title, String first_name, String name, String address, String dob, String nhs_number, String allergies, String gp_name, String room, String cycle_duration, String patient_notes, String is_absent, String current_absence_start, String current_absence_end, String inr_reading, String inr_date, String warfarin_dose, String photo_image, String updated_at, String last_bs_site, String prescribed_time_slots_for_patient_list_array, String current_absence_reason) {
        this.id = id;
        this.fullname = fullname;
        this.title = title;
        this.first_name = first_name;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.nhs_number = nhs_number;
        this.allergies = allergies;
        this.gp_name = gp_name;
        this.room = room;
        this.cycle_duration = cycle_duration;
        this.patient_notes = patient_notes;
        this.is_absent = is_absent;
        this.current_absence_start = current_absence_start;
        this.current_absence_end = current_absence_end;
        this.current_absence_reason=current_absence_reason;
        this.inr_reading = inr_reading;
        this.inr_date = inr_date;
        this.warfarin_dose = warfarin_dose;
        this.photo_image = photo_image;
        this.updated_at = updated_at;
        this.last_bs_site = last_bs_site;
        this.prescribed_time_slots_for_patient_list_array = prescribed_time_slots_for_patient_list_array;
    }


    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getNhs_number() {
        return nhs_number;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getGp_name() {
        return gp_name;
    }

    public String getRoom() {
        return room;
    }

    public String getCycle_duration() {
        return cycle_duration;
    }

    public String getPatient_notes() {
        return patient_notes;
    }

    public String getIs_absent() {
        return is_absent;
    }

    public String getCurrent_absence_start() {
        return current_absence_start;
    }

    public String getCurrent_absence_end() {
        return current_absence_end;
    }

    public String getInr_reading() {
        return inr_reading;
    }

    public String getInr_date() {
        return inr_date;
    }

    public String getWarfarin_dose() {
        return warfarin_dose;
    }

    public String getPhoto_image() {
        return photo_image;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getLast_bs_site() {
        return last_bs_site;
    }

    public String getCurrent_absence_reason() {
        return current_absence_reason;
    }



}