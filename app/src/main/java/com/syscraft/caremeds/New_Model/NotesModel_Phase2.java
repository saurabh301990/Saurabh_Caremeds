package com.syscraft.caremeds.New_Model;

/**
 * Created by Syscraft on 20-02-2016.
 */
public class NotesModel_Phase2 {

    String id;
    String subject;
    String content;
    String note_type;
    String updated_at;
    String created_at;
    String user_id;
    String username;
    String fullname;
    String email;
    String Patientid;

    public String getPatientid() {
        return Patientid;
    }



    public NotesModel_Phase2(String id, String subject, String content, String note_type, String updated_at, String created_at, String user_id, String username, String fullname, String email, String patientid) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.note_type = note_type;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.user_id = user_id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.Patientid=patientid;
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


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
