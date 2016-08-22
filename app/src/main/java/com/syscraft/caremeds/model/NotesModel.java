package com.syscraft.caremeds.model;

/**
 * Created by Syscraft on 20-02-2016.
 */
public class NotesModel {
    String id;
    String content;
    String created_at;
    String deleted_at;
    String mar_Id;
    String note_date;
    String note_type;
    String patient_id;
    String prescription_id;
    String subject;
    String updated_at;
    String user_id;
    String can_manage_users;
    String care_home_id;
    String email;
    String fullname;
    String is_active;
    String username;

    public NotesModel(String id, String content, String created_at, String deleted_at, String mar_Id, String note_date, String note_type, String patient_id, String prescription_id, String subject, String updated_at, String user_id, String can_manage_users, String care_home_id, String email, String fullname, String is_active, String username) {
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.mar_Id = mar_Id;
        this.note_date = note_date;
        this.note_type = note_type;
        this.patient_id = patient_id;
        this.prescription_id = prescription_id;
        this.subject = subject;
        this.updated_at = updated_at;
        this.user_id = user_id;
        this.can_manage_users = can_manage_users;
        this.care_home_id = care_home_id;
        this.email = email;
        this.fullname = fullname;
        this.is_active = is_active;
        this.username = username;
    }


    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCan_manage_users() {
        return can_manage_users;
    }

    public void setCan_manage_users(String can_manage_users) {
        this.can_manage_users = can_manage_users;
    }

    public String getCare_home_id() {
        return care_home_id;
    }

    public void setCare_home_id(String care_home_id) {
        this.care_home_id = care_home_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote_type() {
        return note_type;
    }

    public void setNote_type(String note_type) {
        this.note_type = note_type;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMar_Id() {
        return mar_Id;
    }

    public void setMar_Id(String mar_Id) {
        this.mar_Id = mar_Id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
}
