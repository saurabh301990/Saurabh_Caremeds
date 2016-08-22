package com.syscraft.caremeds.model;

/**
 * Created by syscraft on 6/13/2016.
 */
public class OFFLINE_DATA_MODEL {


    String id;
    String weburl;
    String postdata;
    String caretakerid;
    String type;
    String id_to_relate;

    public String getPatient_id() {
        return patient_id;
    }

    String patient_id;

    public OFFLINE_DATA_MODEL(String id, String weburl, String postdata, String caretakerid, String type, String id_to_relate, String patient_id) {
        this.id = id;
        this.weburl = weburl;
        this.postdata = postdata;
        this.caretakerid = caretakerid;
        this.type = type;
        this.id_to_relate = id_to_relate;
        this.patient_id = patient_id;
    }


    public String getId() {
        return id;
    }

    public String getWeburl() {
        return weburl;
    }

    public String getPostdata() {
        return postdata;
    }

    public String getCaretakerid() {
        return caretakerid;
    }

    public String getType() {
        return type;
    }

    public String getId_to_relate() {
        return id_to_relate;
    }
}
