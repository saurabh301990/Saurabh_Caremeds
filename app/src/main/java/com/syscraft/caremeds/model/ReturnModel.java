package com.syscraft.caremeds.model;

/**
 * Created by Syscraft on 20-02-2016.
 */
public class ReturnModel {
    String id;
    String indications;
    String instructions;
    String is_patch;
    String is_special;
    String non_blistered;
    String prescription_type;

    String special_medication_name;
    String start_date;
    String status;
    String suppress_non_blister_display;
    String mandatory_instructions;
    String can_carry_forward;
    String front_image_url;
    String drug_name;
    String detailed_drug_name;
    String short_drug_name;
    String patch_location;
    String last_admin;
    String first_check_in_date;
    String dispensed_quantity;
    String available_quantity;
    int checked_in_quantity;
    int brought_forward_quantity;
    int returned_quantity;
    int destroyed_quantity;
    int carried_forward_quantity;
    int wasted_quantity;
    int administered_quantity;
    String audited_quantity;
    String patient_absent_today;
    String is_controlled;
    String pil_url;
    String color;
    String created_at;
    String deleted_at;
    String dose;
    String time_slot_dose_id;
    String prescription_id;
    String show_as;
    String slot_time;
    String updated_at;
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
    String branded_medication_iCompanyID;
    String branded_medication_iControlledDrugSchedule;
    String branded_medication_iProductID;
    String branded_medication_sDisplayName;
    String branded_medication_sDisplayNameTM;
    String branded_medication_sLegacyDisplayName;
    String branded_medication_sName;
    String branded_medication_sNameTM;
    String branded_medication_mandatory_instructions;
    String branded_medication_detailed_drug_name;
    String time_slot_doses_color;
    String time_slot_doses_dose;
    String time_slot_doses_id;
    String time_slot_doses_show_as;
    String time_slot_doses_slot_time;

    public ReturnModel(int sub, int returned_quantity) {
    }

    public ReturnModel() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIs_patch() {
        return is_patch;
    }

    public void setIs_patch(String is_patch) {
        this.is_patch = is_patch;
    }

    public String getIs_special() {
        return is_special;
    }

    public void setIs_special(String is_special) {
        this.is_special = is_special;
    }

    public String getNon_blistered() {
        return non_blistered;
    }

    public void setNon_blistered(String non_blistered) {
        this.non_blistered = non_blistered;
    }

    public String getPrescription_type() {
        return prescription_type;
    }

    public void setPrescription_type(String prescription_type) {
        this.prescription_type = prescription_type;
    }


    public String getSpecial_medication_name() {
        return special_medication_name;
    }

    public void setSpecial_medication_name(String special_medication_name) {
        this.special_medication_name = special_medication_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuppress_non_blister_display() {
        return suppress_non_blister_display;
    }

    public void setSuppress_non_blister_display(String suppress_non_blister_display) {
        this.suppress_non_blister_display = suppress_non_blister_display;
    }

    public String getMandatory_instructions() {
        return mandatory_instructions;
    }

    public void setMandatory_instructions(String mandatory_instructions) {
        this.mandatory_instructions = mandatory_instructions;
    }

    public String getCan_carry_forward() {
        return can_carry_forward;
    }

    public void setCan_carry_forward(String can_carry_forward) {
        this.can_carry_forward = can_carry_forward;
    }

    public String getFront_image_url() {
        return front_image_url;
    }

    public void setFront_image_url(String front_image_url) {
        this.front_image_url = front_image_url;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDetailed_drug_name() {
        return detailed_drug_name;
    }

    public void setDetailed_drug_name(String detailed_drug_name) {
        this.detailed_drug_name = detailed_drug_name;
    }

    public String getShort_drug_name() {
        return short_drug_name;
    }

    public void setShort_drug_name(String short_drug_name) {
        this.short_drug_name = short_drug_name;
    }

    public String getPatch_location() {
        return patch_location;
    }

    public void setPatch_location(String patch_location) {
        this.patch_location = patch_location;
    }

    public String getLast_admin() {
        return last_admin;
    }

    public void setLast_admin(String last_admin) {
        this.last_admin = last_admin;
    }

    public String getFirst_check_in_date() {
        return first_check_in_date;
    }

    public void setFirst_check_in_date(String first_check_in_date) {
        this.first_check_in_date = first_check_in_date;
    }

    public String getDispensed_quantity() {
        return dispensed_quantity;
    }

    public void setDispensed_quantity(String dispensed_quantity) {
        this.dispensed_quantity = dispensed_quantity;
    }

    public String getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(String available_quantity) {
        this.available_quantity = available_quantity;
    }

    public int getChecked_in_quantity() {
        return checked_in_quantity;
    }

    public void setChecked_in_quantity(int checked_in_quantity) {
        this.checked_in_quantity = checked_in_quantity;
    }

    public int getBrought_forward_quantity() {
        return brought_forward_quantity;
    }

    public void setBrought_forward_quantity(int brought_forward_quantity) {
        this.brought_forward_quantity = brought_forward_quantity;
    }

    public int getReturned_quantity() {
        return returned_quantity;
    }

    public void setReturned_quantity(int returned_quantity) {
        this.returned_quantity = returned_quantity;
    }

    public int getDestroyed_quantity() {
        return destroyed_quantity;
    }

    public void setDestroyed_quantity(int destroyed_quantity) {
        this.destroyed_quantity = destroyed_quantity;
    }

    public int getCarried_forward_quantity() {
        return carried_forward_quantity;
    }

    public void setCarried_forward_quantity(int carried_forward_quantity) {
        this.carried_forward_quantity = carried_forward_quantity;
    }

    public int getWasted_quantity() {
        return wasted_quantity;
    }

    public void setWasted_quantity(int wasted_quantity) {
        this.wasted_quantity = wasted_quantity;
    }

    public int getAdministered_quantity() {
        return administered_quantity;
    }

    public void setAdministered_quantity(int administered_quantity) {
        this.administered_quantity = administered_quantity;
    }

    public String getAudited_quantity() {
        return audited_quantity;
    }

    public void setAudited_quantity(String audited_quantity) {
        this.audited_quantity = audited_quantity;
    }

    public String getPatient_absent_today() {
        return patient_absent_today;
    }

    public void setPatient_absent_today(String patient_absent_today) {
        this.patient_absent_today = patient_absent_today;
    }

    public String getIs_controlled() {
        return is_controlled;
    }

    public void setIs_controlled(String is_controlled) {
        this.is_controlled = is_controlled;
    }

    public String getPil_url() {
        return pil_url;
    }

    public void setPil_url(String pil_url) {
        this.pil_url = pil_url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTime_slot_dose_id() {
        return time_slot_dose_id;
    }

    public void setTime_slot_dose_id(String time_slot_dose_id) {
        this.time_slot_dose_id = time_slot_dose_id;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getShow_as() {
        return show_as;
    }

    public void setShow_as(String show_as) {
        this.show_as = show_as;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getiCompanyID() {
        return iCompanyID;
    }

    public void setiCompanyID(String iCompanyID) {
        this.iCompanyID = iCompanyID;
    }

    public String getiControlledDrugSchedule() {
        return iControlledDrugSchedule;
    }

    public void setiControlledDrugSchedule(String iControlledDrugSchedule) {
        this.iControlledDrugSchedule = iControlledDrugSchedule;
    }

    public String getiProductID() {
        return iProductID;
    }

    public void setiProductID(String iProductID) {
        this.iProductID = iProductID;
    }

    public String getsDisplayName() {
        return sDisplayName;
    }

    public void setsDisplayName(String sDisplayName) {
        this.sDisplayName = sDisplayName;
    }

    public String getsDisplayNameTM() {
        return sDisplayNameTM;
    }

    public void setsDisplayNameTM(String sDisplayNameTM) {
        this.sDisplayNameTM = sDisplayNameTM;
    }

    public String getsLegacyDisplayName() {
        return sLegacyDisplayName;
    }

    public void setsLegacyDisplayName(String sLegacyDisplayName) {
        this.sLegacyDisplayName = sLegacyDisplayName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsNameTM() {
        return sNameTM;
    }

    public void setsNameTM(String sNameTM) {
        this.sNameTM = sNameTM;
    }

    public String getMedication_mandatory_instructions() {
        return medication_mandatory_instructions;
    }

    public void setMedication_mandatory_instructions(String medication_mandatory_instructions) {
        this.medication_mandatory_instructions = medication_mandatory_instructions;
    }

    public String getMedication_detailed_drug_name() {
        return medication_detailed_drug_name;
    }

    public void setMedication_detailed_drug_name(String medication_detailed_drug_name) {
        this.medication_detailed_drug_name = medication_detailed_drug_name;
    }

    public String getMedication_is_controlled() {
        return medication_is_controlled;
    }

    public void setMedication_is_controlled(String medication_is_controlled) {
        this.medication_is_controlled = medication_is_controlled;
    }

    public String getBranded_medication_iCompanyID() {
        return branded_medication_iCompanyID;
    }

    public void setBranded_medication_iCompanyID(String branded_medication_iCompanyID) {
        this.branded_medication_iCompanyID = branded_medication_iCompanyID;
    }

    public String getBranded_medication_iControlledDrugSchedule() {
        return branded_medication_iControlledDrugSchedule;
    }

    public void setBranded_medication_iControlledDrugSchedule(String branded_medication_iControlledDrugSchedule) {
        this.branded_medication_iControlledDrugSchedule = branded_medication_iControlledDrugSchedule;
    }

    public String getBranded_medication_iProductID() {
        return branded_medication_iProductID;
    }

    public void setBranded_medication_iProductID(String branded_medication_iProductID) {
        this.branded_medication_iProductID = branded_medication_iProductID;
    }

    public String getBranded_medication_sDisplayName() {
        return branded_medication_sDisplayName;
    }

    public void setBranded_medication_sDisplayName(String branded_medication_sDisplayName) {
        this.branded_medication_sDisplayName = branded_medication_sDisplayName;
    }

    public String getBranded_medication_sDisplayNameTM() {
        return branded_medication_sDisplayNameTM;
    }

    public void setBranded_medication_sDisplayNameTM(String branded_medication_sDisplayNameTM) {
        this.branded_medication_sDisplayNameTM = branded_medication_sDisplayNameTM;
    }

    public String getBranded_medication_sLegacyDisplayName() {
        return branded_medication_sLegacyDisplayName;
    }

    public void setBranded_medication_sLegacyDisplayName(String branded_medication_sLegacyDisplayName) {
        this.branded_medication_sLegacyDisplayName = branded_medication_sLegacyDisplayName;
    }

    public String getBranded_medication_sName() {
        return branded_medication_sName;
    }

    public void setBranded_medication_sName(String branded_medication_sName) {
        this.branded_medication_sName = branded_medication_sName;
    }

    public String getBranded_medication_sNameTM() {
        return branded_medication_sNameTM;
    }

    public void setBranded_medication_sNameTM(String branded_medication_sNameTM) {
        this.branded_medication_sNameTM = branded_medication_sNameTM;
    }

    public String getBranded_medication_mandatory_instructions() {
        return branded_medication_mandatory_instructions;
    }

    public void setBranded_medication_mandatory_instructions(String branded_medication_mandatory_instructions) {
        this.branded_medication_mandatory_instructions = branded_medication_mandatory_instructions;
    }

    public String getBranded_medication_detailed_drug_name() {
        return branded_medication_detailed_drug_name;
    }

    public void setBranded_medication_detailed_drug_name(String branded_medication_detailed_drug_name) {
        this.branded_medication_detailed_drug_name = branded_medication_detailed_drug_name;
    }

    public String getTime_slot_doses_color() {
        return time_slot_doses_color;
    }

    public void setTime_slot_doses_color(String time_slot_doses_color) {
        this.time_slot_doses_color = time_slot_doses_color;
    }

    public String getTime_slot_doses_dose() {
        return time_slot_doses_dose;
    }

    public void setTime_slot_doses_dose(String time_slot_doses_dose) {
        this.time_slot_doses_dose = time_slot_doses_dose;
    }

    public String getTime_slot_doses_id() {
        return time_slot_doses_id;
    }

    public void setTime_slot_doses_id(String time_slot_doses_id) {
        this.time_slot_doses_id = time_slot_doses_id;
    }

    public String getTime_slot_doses_show_as() {
        return time_slot_doses_show_as;
    }

    public void setTime_slot_doses_show_as(String time_slot_doses_show_as) {
        this.time_slot_doses_show_as = time_slot_doses_show_as;
    }

    public String getTime_slot_doses_slot_time() {
        return time_slot_doses_slot_time;
    }

    public void setTime_slot_doses_slot_time(String time_slot_doses_slot_time) {
        this.time_slot_doses_slot_time = time_slot_doses_slot_time;
    }

    //------------------------------------------------------------------------------------------//


    private String m1slotTime;
    private String m1showAs;
    private String m1color;


    private String m2slotTime;
    private String m2showAs;
    private String m2color;


    private String lunslotTime;
    private String lunshowAs;
    private String luncolor;


    private String ngtslotTime;
    private String ngtshowAs;
    private String ngtcolor;


    private String pnrslotTime;
    private String pnrshowAs;
    private String pnrcolor;


    private String slotTime;
    private String showAs;
    //private String color;

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


    //--------------------------------------------------//

    String mar_created_at;
    String mar_deleted_at;
    String mar_dose;
    String mar_false_reason;
    String mar_gps_location;
    String mar_homely_remedy_id;
    String mar_homely_remedy_name;
    String mar_id;
    String mar_is_waste;
    String mar_medication_id;
    String mar_outcome;
    String mar_quantity_wasted;
    String mar_secondary_user_id;
    String mar_secondary_user_fullname;
    String mar_time_slot;
    String mar_uid;
    String mar_updated_at;

    public String getMar_created_at() {
        return mar_created_at;
    }

    public void setMar_created_at(String mar_created_at) {
        this.mar_created_at = mar_created_at;
    }

    public String getMar_deleted_at() {
        return mar_deleted_at;
    }

    public void setMar_deleted_at(String mar_deleted_at) {
        this.mar_deleted_at = mar_deleted_at;
    }

    public String getMar_dose() {
        return mar_dose;
    }

    public void setMar_dose(String mar_dose) {
        this.mar_dose = mar_dose;
    }

    public String getMar_false_reason() {
        return mar_false_reason;
    }

    public void setMar_false_reason(String mar_false_reason) {
        this.mar_false_reason = mar_false_reason;
    }

    public String getMar_gps_location() {
        return mar_gps_location;
    }

    public void setMar_gps_location(String mar_gps_location) {
        this.mar_gps_location = mar_gps_location;
    }

    public String getMar_homely_remedy_id() {
        return mar_homely_remedy_id;
    }

    public void setMar_homely_remedy_id(String mar_homely_remedy_id) {
        this.mar_homely_remedy_id = mar_homely_remedy_id;
    }

    public String getMar_homely_remedy_name() {
        return mar_homely_remedy_name;
    }

    public void setMar_homely_remedy_name(String mar_homely_remedy_name) {
        this.mar_homely_remedy_name = mar_homely_remedy_name;
    }

    public String getMar_id() {
        return mar_id;
    }

    public void setMar_id(String mar_id) {
        this.mar_id = mar_id;
    }

    public String getMar_is_waste() {
        return mar_is_waste;
    }

    public void setMar_is_waste(String mar_is_waste) {
        this.mar_is_waste = mar_is_waste;
    }

    public String getMar_medication_id() {
        return mar_medication_id;
    }

    public void setMar_medication_id(String mar_medication_id) {
        this.mar_medication_id = mar_medication_id;
    }

    public String getMar_outcome() {
        return mar_outcome;
    }

    public void setMar_outcome(String mar_outcome) {
        this.mar_outcome = mar_outcome;
    }

    public String getMar_quantity_wasted() {
        return mar_quantity_wasted;
    }

    public void setMar_quantity_wasted(String mar_quantity_wasted) {
        this.mar_quantity_wasted = mar_quantity_wasted;
    }

    public String getMar_secondary_user_id() {
        return mar_secondary_user_id;
    }

    public void setMar_secondary_user_id(String mar_secondary_user_id) {
        this.mar_secondary_user_id = mar_secondary_user_id;
    }

    public String getMar_secondary_user_fullname() {
        return mar_secondary_user_fullname;
    }

    public void setMar_secondary_user_fullname(String mar_secondary_user_fullname) {
        this.mar_secondary_user_fullname = mar_secondary_user_fullname;
    }

    public String getMar_time_slot() {
        return mar_time_slot;
    }

    public void setMar_time_slot(String mar_time_slot) {
        this.mar_time_slot = mar_time_slot;
    }

    public String getMar_uid() {
        return mar_uid;
    }

    public void setMar_uid(String mar_uid) {
        this.mar_uid = mar_uid;
    }

    public String getMar_updated_at() {
        return mar_updated_at;
    }

    public void setMar_updated_at(String mar_updated_at) {
        this.mar_updated_at = mar_updated_at;
    }

    //---------------------------------------10-03-16-------------------------------------------------------//

    String created_at_mar;
    String deleted_at_mar;
    String dose_mar;
    String time_slot_dose_id_mar;
    String false_reason_mar;
    String gps_location_mar;
    String homely_remedy_id_mar;
    String homely_remedy_name_mar;
    String id_mar;
    String is_waste_mar;
    String medication_id_mar;
    String outcome_mar;
    String patient_id_mar;
    String quantity_wasted_mar;
    String secondary_user_id_mar;
    String seconday_user_fullname_mar;
    String slot_time_mar;
    String uid_mar;
    String user_fullname_mar;

    public String getCreated_at_mar() {
        return created_at_mar;
    }

    public void setCreated_at_mar(String created_at_mar) {
        this.created_at_mar = created_at_mar;
    }

    public String getDeleted_at_mar() {
        return deleted_at_mar;
    }

    public void setDeleted_at_mar(String deleted_at_mar) {
        this.deleted_at_mar = deleted_at_mar;
    }

    public String getDose_mar() {
        return dose_mar;
    }

    public void setDose_mar(String dose_mar) {
        this.dose_mar = dose_mar;
    }

    public String getTime_slot_dose_id_mar() {
        return time_slot_dose_id_mar;
    }

    public void setTime_slot_dose_id_mar(String time_slot_dose_id_mar) {
        this.time_slot_dose_id_mar = time_slot_dose_id_mar;
    }

    public String getFalse_reason_mar() {
        return false_reason_mar;
    }

    public void setFalse_reason_mar(String false_reason_mar) {
        this.false_reason_mar = false_reason_mar;
    }

    public String getGps_location_mar() {
        return gps_location_mar;
    }

    public void setGps_location_mar(String gps_location_mar) {
        this.gps_location_mar = gps_location_mar;
    }

    public String getHomely_remedy_id_mar() {
        return homely_remedy_id_mar;
    }

    public void setHomely_remedy_id_mar(String homely_remedy_id_mar) {
        this.homely_remedy_id_mar = homely_remedy_id_mar;
    }

    public String getHomely_remedy_name_mar() {
        return homely_remedy_name_mar;
    }

    public void setHomely_remedy_name_mar(String homely_remedy_name_mar) {
        this.homely_remedy_name_mar = homely_remedy_name_mar;
    }

    public String getId_mar() {
        return id_mar;
    }

    public void setId_mar(String id_mar) {
        this.id_mar = id_mar;
    }

    public String getIs_waste_mar() {
        return is_waste_mar;
    }

    public void setIs_waste_mar(String is_waste_mar) {
        this.is_waste_mar = is_waste_mar;
    }

    public String getMedication_id_mar() {
        return medication_id_mar;
    }

    public void setMedication_id_mar(String medication_id_mar) {
        this.medication_id_mar = medication_id_mar;
    }

    public String getOutcome_mar() {
        return outcome_mar;
    }

    public void setOutcome_mar(String outcome_mar) {
        this.outcome_mar = outcome_mar;
    }

    public String getPatient_id_mar() {
        return patient_id_mar;
    }

    public void setPatient_id_mar(String patient_id_mar) {
        this.patient_id_mar = patient_id_mar;
    }

    public String getQuantity_wasted_mar() {
        return quantity_wasted_mar;
    }

    public void setQuantity_wasted_mar(String quantity_wasted_mar) {
        this.quantity_wasted_mar = quantity_wasted_mar;
    }

    public String getSecondary_user_id_mar() {
        return secondary_user_id_mar;
    }

    public void setSecondary_user_id_mar(String secondary_user_id_mar) {
        this.secondary_user_id_mar = secondary_user_id_mar;
    }

    public String getSeconday_user_fullname_mar() {
        return seconday_user_fullname_mar;
    }

    public void setSeconday_user_fullname_mar(String seconday_user_fullname_mar) {
        this.seconday_user_fullname_mar = seconday_user_fullname_mar;
    }

    public String getSlot_time_mar() {
        return slot_time_mar;
    }

    public void setSlot_time_mar(String slot_time_mar) {
        this.slot_time_mar = slot_time_mar;
    }

    public String getUid_mar() {
        return uid_mar;
    }

    public void setUid_mar(String uid_mar) {
        this.uid_mar = uid_mar;
    }

    public String getUser_fullname_mar() {
        return user_fullname_mar;
    }

    public void setUser_fullname_mar(String user_fullname_mar) {
        this.user_fullname_mar = user_fullname_mar;
    }

    public String getUser_id_mar() {
        return user_id_mar;
    }

    public void setUser_id_mar(String user_id_mar) {
        this.user_id_mar = user_id_mar;
    }

    public String getPrescription_id_mar() {
        return prescription_id_mar;
    }

    public void setPrescription_id_mar(String prescription_id_mar) {
        this.prescription_id_mar = prescription_id_mar;
    }

    public String getUpdated_at_mar() {
        return updated_at_mar;
    }

    public void setUpdated_at_mar(String updated_at_mar) {
        this.updated_at_mar = updated_at_mar;
    }

    String user_id_mar;
    String prescription_id_mar;
    String updated_at_mar;


}

