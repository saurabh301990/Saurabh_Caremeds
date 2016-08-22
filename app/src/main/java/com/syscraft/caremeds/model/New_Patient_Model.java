package com.syscraft.caremeds.model;

import java.util.ArrayList;

/**
 * Created by anubhav on 2/16/2016.
 */
public class New_Patient_Model {

    String PatientAddress;
    String PatientAllergies;
    String PatientCareHomeId;
    String PatientCreated;
    String PatientCycleBaseDate;
    String PatientCycle_duration;
    String PatientDeletedAt;
    String PatientDob;
    String PatientFirstName;
    String PatientGpId;
    String PatientGpName;
    String PatientId;
    String Inactive;
    String PatientName;
    String Nhs_number;
    String PatientNotes;
    String PatientImage;
    String PatientRoom;
    String PatientTittle;
    String PatientUpdatedAt;
    String PatientFullName;



    public String getPrescribed_time_slots_for_patient_list_array() {
        return prescribed_time_slots_for_patient_list_array;
    }

    String prescribed_time_slots_for_patient_list_array;
    //    ArrayList<PATIENTS_TIME_SLOT> pts_list;
    String is_absent;
    String current_absence_start;
    String current_absence_end;
    String current_absence_reason;


    public New_Patient_Model(String patientId, String patientAddress, String patientAllergies, String patientCareHomeId, String patientCreated, String patientCycleBaseDate, String patientCycle_duration, String patientDeletedAt, String patientDob, String patientFirstName, String patientGpId, String patientGpName, String inactive, String patientName, String nhs_number, String patientNotes, String patientImage, String patientRoom, String patientTittle, String patientUpdatedAt, String patientFullName, String prescribed_time_slots_for_patient_list_array, String is_absent, String current_absence_start, String current_absence_end, String current_absence_reason) {
        PatientId = patientId;
        PatientAddress = patientAddress;
        PatientAllergies = patientAllergies;
        PatientCareHomeId = patientCareHomeId;
        PatientCreated = patientCreated;
        PatientCycleBaseDate = patientCycleBaseDate;
        PatientCycle_duration = patientCycle_duration;
        PatientDeletedAt = patientDeletedAt;
        PatientDob = patientDob;
        PatientFirstName = patientFirstName;
        PatientGpId = patientGpId;
        PatientGpName = patientGpName;
        Inactive = inactive;
        PatientName = patientName;
        Nhs_number = nhs_number;
        PatientNotes = patientNotes;
        PatientImage = patientImage;
        PatientRoom = patientRoom;
        PatientTittle = patientTittle;
        PatientUpdatedAt = patientUpdatedAt;
        PatientFullName = patientFullName;
        this.prescribed_time_slots_for_patient_list_array = prescribed_time_slots_for_patient_list_array;
//        this.pts_list = pts_list;
        this.is_absent = is_absent;
        this.current_absence_start = current_absence_start;
        this.current_absence_end = current_absence_end;
        this.current_absence_reason = current_absence_reason;

    }


    public String getPatientAddress() {
        return PatientAddress;
    }

    public String getPatientAllergies() {
        return PatientAllergies;
    }

    public String getPatientCareHomeId() {
        return PatientCareHomeId;
    }

    public String getPatientCreated() {
        return PatientCreated;
    }

    public String getPatientCycleBaseDate() {
        return PatientCycleBaseDate;
    }

    public String getPatientCycle_duration() {
        return PatientCycle_duration;
    }

    public String getPatientDeletedAt() {
        return PatientDeletedAt;
    }

    public String getPatientDob() {
        return PatientDob;
    }

    public String getPatientFirstName() {
        return PatientFirstName;
    }

    public String getPatientGpId() {
        return PatientGpId;
    }

    public String getPatientGpName() {
        return PatientGpName;
    }

    public String getPatientId() {
        return PatientId;
    }

    public String getInactive() {
        return Inactive;
    }

    public String getPatientName() {
        return PatientName;
    }

    public String getNhs_number() {
        return Nhs_number;
    }

    public String getPatientNotes() {
        return PatientNotes;
    }

    public String getPatientImage() {
        return PatientImage;
    }

    public String getPatientRoom() {
        return PatientRoom;
    }

    public String getPatientTittle() {
        return PatientTittle;
    }

    public String getPatientUpdatedAt() {
        return PatientUpdatedAt;
    }

    public String getPatientFullName() {
        return PatientFullName;
    }

//    public ArrayList<PATIENTS_TIME_SLOT> getPts_list() {
//        return pts_list;
//    }

    public String getIs_absent() {
        return is_absent;
    }

    public String getCurrent_absence_start() {
        return current_absence_start;
    }

    public String getCurrent_absence_end() {
        return current_absence_end;
    }

    public String getCurrent_absence_reason() {
        return current_absence_reason;
    }


}