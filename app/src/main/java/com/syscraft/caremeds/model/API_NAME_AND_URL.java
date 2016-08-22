package com.syscraft.caremeds.model;

/**
 * Created by syscraft on 6/24/2016.
 */
public class API_NAME_AND_URL {

    String api_web_url;
    String api_name;
    String api_patientid;


    public API_NAME_AND_URL(String api_web_url, String api_name, String api_patientid) {
        this.api_web_url = api_web_url;
        this.api_name = api_name;
        this.api_patientid = api_patientid;
    }


    public String getApi_patientid() {
        return api_patientid;
    }

    public String getApi_name() {
        return api_name;
    }

    public String getApi_web_url() {
        return api_web_url;
    }


}
