package com.syscraft.caremeds.model;

/**
 * Created by Deepika on 06-04-2016.
 */
public class MEDICATION {

    String iCompanyID;
    String iControlledDrugSchedule;
    String iProductID;
    String sDisplayName;
    String sDisplayNameTM;
    String sLegacyDisplayName;
    String sName;
    String sNameTM;
    String medication_mandatory_instructions;
    String medication_detailed_drug_name;
    String medication_is_controlled;


    public MEDICATION(String iCompanyID, String iControlledDrugSchedule, String iProductID, String sDisplayName, String sDisplayNameTM, String sLegacyDisplayName, String sName, String sNameTM, String medication_mandatory_instructions, String medication_detailed_drug_name, String medication_is_controlled) {
        this.iCompanyID = iCompanyID;
        this.iControlledDrugSchedule = iControlledDrugSchedule;
        this.iProductID = iProductID;
        this.sDisplayName = sDisplayName;
        this.sDisplayNameTM = sDisplayNameTM;
        this.sLegacyDisplayName = sLegacyDisplayName;
        this.sName = sName;
        this.sNameTM = sNameTM;
        this.medication_mandatory_instructions = medication_mandatory_instructions;
        this.medication_detailed_drug_name = medication_detailed_drug_name;
        this.medication_is_controlled = medication_is_controlled;
    }


    public String getiCompanyID() {
        return iCompanyID;
    }

    public String getiControlledDrugSchedule() {
        return iControlledDrugSchedule;
    }

    public String getiProductID() {
        return iProductID;
    }

    public String getsDisplayName() {
        return sDisplayName;
    }

    public String getsDisplayNameTM() {
        return sDisplayNameTM;
    }

    public String getsLegacyDisplayName() {
        return sLegacyDisplayName;
    }

    public String getsName() {
        return sName;
    }

    public String getsNameTM() {
        return sNameTM;
    }

    public String getMedication_mandatory_instructions() {
        return medication_mandatory_instructions;
    }

    public String getMedication_detailed_drug_name() {
        return medication_detailed_drug_name;
    }

    public String getMedication_is_controlled() {
        return medication_is_controlled;
    }


}
