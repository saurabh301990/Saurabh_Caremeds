package com.syscraft.caremeds.model;

/**
 * Created by Deepika on 06-04-2016.
 */
public class BRANDED_MEDICATION {

    String br_medi_iCompanyID;
    String br_medi_iControlledDrugSchedule;
    String br_medi_iProductID;
    String br_medi_sDisplayName;
    String br_medi_sDisplayNameTM;
    String br_medi_sLegacyDisplayName;
    String br_medi_sName;
    String br_medi_sNameTM;
    String br_medi_mandatory_instructions;
    String br_medi_detailed_drug_name;


    String br_medi_is_controlled;


    public BRANDED_MEDICATION(String br_medi_iCompanyID, String br_medi_iControlledDrugSchedule, String br_medi_iProductID, String br_medi_sDisplayNameTM, String br_medi_sDisplayName, String br_medi_sLegacyDisplayName, String br_medi_sName, String br_medi_sNameTM, String br_medi_mandatory_instructions, String br_medi_detailed_drug_name, String br_medi_is_controlled) {
        this.br_medi_iCompanyID = br_medi_iCompanyID;
        this.br_medi_iControlledDrugSchedule = br_medi_iControlledDrugSchedule;
        this.br_medi_iProductID = br_medi_iProductID;
        this.br_medi_sDisplayNameTM = br_medi_sDisplayNameTM;
        this.br_medi_sDisplayName = br_medi_sDisplayName;
        this.br_medi_sLegacyDisplayName = br_medi_sLegacyDisplayName;
        this.br_medi_sName = br_medi_sName;
        this.br_medi_sNameTM = br_medi_sNameTM;
        this.br_medi_mandatory_instructions = br_medi_mandatory_instructions;
        this.br_medi_detailed_drug_name = br_medi_detailed_drug_name;
        this.br_medi_is_controlled = br_medi_is_controlled;
    }


    public String getBr_medi_iProductID() {
        return br_medi_iProductID;
    }

    public String getBr_medi_iControlledDrugSchedule() {
        return br_medi_iControlledDrugSchedule;
    }

    public String getBr_medi_iCompanyID() {
        return br_medi_iCompanyID;
    }

    public String getBr_medi_sDisplayName() {
        return br_medi_sDisplayName;
    }

    public String getBr_medi_sDisplayNameTM() {
        return br_medi_sDisplayNameTM;
    }

    public String getBr_medi_sLegacyDisplayName() {
        return br_medi_sLegacyDisplayName;
    }

    public String getBr_medi_sName() {
        return br_medi_sName;
    }

    public String getBr_medi_sNameTM() {
        return br_medi_sNameTM;
    }

    public String getBr_medi_mandatory_instructions() {
        return br_medi_mandatory_instructions;
    }

    public String getBr_medi_detailed_drug_name() {
        return br_medi_detailed_drug_name;
    }

    public String getBr_medi_is_controlled() {
        return br_medi_is_controlled;
    }
}
