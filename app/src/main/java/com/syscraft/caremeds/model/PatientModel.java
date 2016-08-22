package com.syscraft.caremeds.model;

/**
 * Created by anubhav on 2/16/2016.
 */
public class PatientModel {

    String PatientImage;
    String PatientCreated;
    String PatientCareHomeId;
    String PatientDob;
    String PatientGpName;
    String PatientRoom;
    String PatientAllergies;
    String PatientCycleBaseDate;
    String PatientAddress;
    String PatientCycle_duration;
    String PatientGpId;
    String PatientId;
    String PatientUpdatedAt;
    String PatientName;
    String PatientFirstName;

    public String getPatientFullName() {
        return PatientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        PatientFullName = patientFullName;
    }

    String PatientFullName;
    String PatientDeletedAt;
    String Inactive;
    String Nhs_number;
    String PatientNotes;
    String PatientTittle;
    String is_absent;
    String current_absence_start;
    String current_absence_end;
    String current_absence_reason;


    public String getPatientFirstName() {
        return PatientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        PatientFirstName = patientFirstName;
    }

    public String getPatientDeletedAt() {
        return PatientDeletedAt;
    }

    public void setPatientDeletedAt(String patientDeletedAt) {
        PatientDeletedAt = patientDeletedAt;
    }

    public String getInactive() {
        return Inactive;
    }

    public void setInactive(String inactive) {
        Inactive = inactive;
    }

    public String getNhs_number() {
        return Nhs_number;
    }

    public void setNhs_number(String nhs_number) {
        Nhs_number = nhs_number;
    }

    public String getPatientNotes() {
        return PatientNotes;
    }

    public void setPatientNotes(String patientNotes) {
        PatientNotes = patientNotes;
    }

    public String getPatientTittle() {
        return PatientTittle;
    }

    public void setPatientTittle(String patientTittle) {
        PatientTittle = patientTittle;
    }

    public String getIs_absent() {
        return is_absent;
    }

    public void setIs_absent(String is_absent) {
        this.is_absent = is_absent;
    }

    public String getCurrent_absence_start() {
        return current_absence_start;
    }

    public void setCurrent_absence_start(String current_absence_start) {
        this.current_absence_start = current_absence_start;
    }

    public String getCurrent_absence_end() {
        return current_absence_end;
    }

    public void setCurrent_absence_end(String current_absence_end) {
        this.current_absence_end = current_absence_end;
    }

    public String getCurrent_absence_reason() {
        return current_absence_reason;
    }

    public void setCurrent_absence_reason(String current_absence_reason) {
        this.current_absence_reason = current_absence_reason;
    }

    public PatientModel() {

    }

    public PatientModel(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getPatientImage() {
        return PatientImage;
    }

    public void setPatientImage(String patientImage) {
        PatientImage = patientImage;
    }

    public String getPatientCreated() {
        return PatientCreated;
    }

    public void setPatientCreated(String patientCreated) {
        PatientCreated = patientCreated;
    }

    public String getPatientCareHomeId() {
        return PatientCareHomeId;
    }

    public void setPatientCareHomeId(String patientCareHomeId) {
        PatientCareHomeId = patientCareHomeId;
    }

    public String getPatientDob() {
        return PatientDob;
    }

    public void setPatientDob(String patientDob) {
        PatientDob = patientDob;
    }

    public String getPatientRoom() {
        return PatientRoom;
    }

    public void setPatientRoom(String patientRoom) {
        PatientRoom = patientRoom;
    }

    public String getPatientGpName() {
        return PatientGpName;
    }

    public void setPatientGpName(String patientGpName) {
        PatientGpName = patientGpName;
    }

    public String getPatientAllergies() {
        return PatientAllergies;
    }

    public void setPatientAllergies(String patientAllergies) {
        PatientAllergies = patientAllergies;
    }

    public String getPatientCycleBaseDate() {
        return PatientCycleBaseDate;
    }

    public void setPatientCycleBaseDate(String patientCycleBaseDate) {
        PatientCycleBaseDate = patientCycleBaseDate;
    }

    public String getPatientUpdatedAt() {
        return PatientUpdatedAt;
    }

    public void setPatientUpdatedAt(String patientUpdatedAt) {
        PatientUpdatedAt = patientUpdatedAt;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPatientGpId() {
        return PatientGpId;
    }

    public void setPatientGpId(String patientGpId) {
        PatientGpId = patientGpId;
    }

    public String getPatientCycle_duration() {
        return PatientCycle_duration;
    }

    public void setPatientCycle_duration(String patientCycle_duration) {
        PatientCycle_duration = patientCycle_duration;
    }

    public String getPatientAddress() {
        return PatientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        PatientAddress = patientAddress;
    }

    private String m1slotTime;
    private String m1showAs;
    private String m1color;
    private String m1adminToday;

    private String m2slotTime;
    private String m2showAs;
    private String m2color;
    private String m2adminToday;

    private String lunslotTime;
    private String lunshowAs;
    private String luncolor;
    private String lunadminToday;

    private String ngtslotTime;
    private String ngtshowAs;
    private String ngtcolor;
    private String ngtadminToday;

    private String pnrslotTime;
    private String pnrshowAs;
    private String pnrcolor;
    private String pnradminToday;

    private String warfslotTime;
    private String warfshowAs;
    private String warfcolor;
    private String warfadminToday;

    public String getWarfslotTime() {
        return warfslotTime;
    }

    public void setWarfslotTime(String warfslotTime) {
        this.warfslotTime = warfslotTime;
    }

    public String getWarfshowAs() {
        return warfshowAs;
    }

    public void setWarfshowAs(String warfshowAs) {
        this.warfshowAs = warfshowAs;
    }

    public String getWarfcolor() {
        return warfcolor;
    }

    public void setWarfcolor(String warfcolor) {
        this.warfcolor = warfcolor;
    }

    public String getWarfadminToday() {
        return warfadminToday;
    }

    public void setWarfadminToday(String warfadminToday) {
        this.warfadminToday = warfadminToday;
    }

    public String getInsslotTime() {
        return insslotTime;
    }

    public void setInsslotTime(String insslotTime) {
        this.insslotTime = insslotTime;
    }

    public String getInsshowAs() {
        return insshowAs;
    }

    public void setInsshowAs(String insshowAs) {
        this.insshowAs = insshowAs;
    }

    public String getInscolor() {
        return inscolor;
    }

    public void setInscolor(String inscolor) {
        this.inscolor = inscolor;
    }

    public String getInsadminToday() {
        return insadminToday;
    }

    public void setInsadminToday(String insadminToday) {
        this.insadminToday = insadminToday;
    }

    private String insslotTime;
    private String insshowAs;
    private String inscolor;
    private String insadminToday;

    private String slotTime;
    private String showAs;
    private String color;
    private String adminToday;


    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getShowAs() {
        return showAs;
    }

    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAdminToday() {
        return adminToday;
    }

    public void setAdminToday(String adminToday) {
        this.adminToday = adminToday;
    }

    public String getM2slotTime() {
        return m2slotTime;
    }

    public void setM2slotTime(String m2slotTime) {
        this.m2slotTime = m2slotTime;
    }

    public String getM2showAs() {
        return m2showAs;
    }

    public void setM2showAs(String m2showAs) {
        this.m2showAs = m2showAs;
    }

    public String getM2color() {
        return m2color;
    }

    public void setM2color(String m2color) {
        this.m2color = m2color;
    }

    public String getM2adminToday() {
        return m2adminToday;
    }

    public void setM2adminToday(String m2adminToday) {
        this.m2adminToday = m2adminToday;
    }

    public String getM1slotTime() {
        return m1slotTime;
    }

    public void setM1slotTime(String m1slotTime) {
        this.m1slotTime = m1slotTime;
    }

    public String getM1color() {
        return m1color;
    }

    public void setM1color(String m1color) {
        this.m1color = m1color;
    }

    public String getM1showAs() {
        return m1showAs;
    }

    public void setM1showAs(String m1showAs) {
        this.m1showAs = m1showAs;
    }

    public String getM1adminToday() {
        return m1adminToday;
    }

    public void setM1adminToday(String m1adminToday) {
        this.m1adminToday = m1adminToday;
    }


    public String getLunslotTime() {
        return lunslotTime;
    }

    public void setLunslotTime(String lunslotTime) {
        this.lunslotTime = lunslotTime;
    }

    public String getLunshowAs() {
        return lunshowAs;
    }

    public void setLunshowAs(String lunshowAs) {
        this.lunshowAs = lunshowAs;
    }

    public String getLuncolor() {
        return luncolor;
    }

    public void setLuncolor(String luncolor) {
        this.luncolor = luncolor;
    }

    public String getLunadminToday() {
        return lunadminToday;
    }

    public void setLunadminToday(String lunadminToday) {
        this.lunadminToday = lunadminToday;
    }

    public String getNgtslotTime() {
        return ngtslotTime;
    }

    public void setNgtslotTime(String ngtslotTime) {
        this.ngtslotTime = ngtslotTime;
    }

    public String getNgtshowAs() {
        return ngtshowAs;
    }

    public void setNgtshowAs(String ngtshowAs) {
        this.ngtshowAs = ngtshowAs;
    }

    public String getNgtcolor() {
        return ngtcolor;
    }

    public void setNgtcolor(String ngtcolor) {
        this.ngtcolor = ngtcolor;
    }

    public String getPnrslotTime() {
        return pnrslotTime;
    }

    public void setPnrslotTime(String pnrslotTime) {
        this.pnrslotTime = pnrslotTime;
    }

    public String getNgtadminToday() {
        return ngtadminToday;
    }

    public void setNgtadminToday(String ngtadminToday) {
        this.ngtadminToday = ngtadminToday;
    }

    public String getPnrshowAs() {
        return pnrshowAs;
    }

    public void setPnrshowAs(String pnrshowAs) {
        this.pnrshowAs = pnrshowAs;
    }

    public String getPnrcolor() {
        return pnrcolor;
    }

    public void setPnrcolor(String pnrcolor) {
        this.pnrcolor = pnrcolor;
    }

    public String getPnradminToday() {
        return pnradminToday;
    }

    public void setPnradminToday(String pnradminToday) {
        this.pnradminToday = pnradminToday;
    }
}
