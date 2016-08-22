package com.syscraft.caremeds.model;

/**
 * Created by Deepika on 04-04-2016.
 */
public class Remedy_Dose_Bean {

    String remedy_name;
    String dose;


    public Remedy_Dose_Bean(String remedy_name, String dose) {
        this.remedy_name = remedy_name;
        this.dose = dose;
    }

    public String getDose() {
        return dose;
    }

    public String getRemedy_name() {
        return remedy_name;
    }


}
