//package com.syscraft.caremeds.model;
//
//import java.util.ArrayList;
//
///**
// * Created by Deepika on 06-04-2016.
// */
//public class PRESCRIPTION_COMMON_Model {
//    int id;
//    String indications;
//    String instructions;
//    String is_patch;
//    String is_special;
//    String non_blistered;
//    String prescription_type;
//    String quantity_dispensed;
//    String special_medication_name;
//    String start_date;
//    String status;
//    String suppress_non_blister_display;
//    String mandatory_instructions;
//    String can_carry_forward;
//    String front_image_url;
//    String drug_name;
//    String detailed_drug_name;
//    String short_drug_name;
//    String patch_location;
//    String prescribed_time_slots_array;
//    String last_admin;
//    String first_check_in_date;
//    int dispensed_quantity;
//    int available_quantity;
//    int checked_in_quantity;
//
//    public void setBrought_forward_quantity(int brought_forward_quantity) {
//        this.brought_forward_quantity = brought_forward_quantity;
//    }
//
//    int brought_forward_quantity;
//    int returned_quantity;
//
//    public void setAvailable_quantity(int available_quantity) {
//        this.available_quantity = available_quantity;
//    }
//    public void setDestroyed_quantity(int destroyed_quantity) {
//        this.destroyed_quantity = destroyed_quantity;
//    }
//
//    public void setCarried_forward_quantity(int carried_forward_quantity) {
//        this.carried_forward_quantity = carried_forward_quantity;
//    }
//
//    public void setReturned_quantity(int returned_quantity) {
//        this.returned_quantity = returned_quantity;
//    }
//
//    int destroyed_quantity;
//    int carried_forward_quantity;
//
//    public void setWasted_quantity(int wasted_quantity) {
//        this.wasted_quantity = wasted_quantity;
//    }
//
//    int wasted_quantity;
//    int administered_quantity;
//    int audited_quantity;
//    String patient_absent_today;
//    String is_controlled;
//    String pil_url;
//    String Medication_object;
//    String brnd_medi_object;
//    String TIME_SLOT_DOSES_array;
//    int new_checked_in_quantity_toupdate;
//    String IsSelected;
//    String checkin_cycle_type;
//    String patient_id;
//
//    boolean IsSelected_inreturn;
//    String Action_applied;
//    int quantity_to_update_on_server;
//    String Waste_Action_applied;
//    int waste_qty_to_update_on_server;
//
//    public String getIs_for_admintab() {
//        return is_for_admintab;
//    }
//
//    String is_for_admintab;
//
//    boolean IsSelected_in_admin;
//
//    public void setIsedited_in_meds(boolean isedited_in_meds) {
//        Isedited_in_meds = isedited_in_meds;
//    }
//
//    public boolean isedited_in_meds() {
//        return Isedited_in_meds;
//    }
//
//    boolean Isedited_in_meds;
//
//    boolean meds_Bforword_bool, meds_Checkedin_bool, meds_Waste_bool,meds_stock_bool;
//    public boolean isMeds_Bforword_bool() {
//        return meds_Bforword_bool;
//    }
//
//    public void setMeds_Bforword_bool(boolean meds_Bforword_bool) {
//        this.meds_Bforword_bool = meds_Bforword_bool;
//    }
//
//    public boolean isMeds_Checkedin_bool() {
//        return meds_Checkedin_bool;
//    }
//
//    public void setMeds_Checkedin_bool(boolean meds_Checkedin_bool) {
//        this.meds_Checkedin_bool = meds_Checkedin_bool;
//    }
//
//    public boolean isMeds_Waste_bool() {
//        return meds_Waste_bool;
//    }
//
//    public void setMeds_Waste_bool(boolean meds_Waste_bool) {
//        this.meds_Waste_bool = meds_Waste_bool;
//    }
//
//    public boolean isMeds_stock_bool() {
//        return meds_stock_bool;
//    }
//
//    public void setMeds_stock_bool(boolean meds_stock_bool) {
//        this.meds_stock_bool = meds_stock_bool;
//    }
//
//
//
//
//
//    ArrayList<Today_Mars_Mar_Bean> Today_Mars_Mar_objects_list;
//
//    public ArrayList<Today_Mars_Mar_Bean> getToday_Mars_Mar_objects_list() {
//        return Today_Mars_Mar_objects_list;
//    }
//
//    public void setToday_Mars_Mar_objects_list(ArrayList<Today_Mars_Mar_Bean> today_Mars_Mar_objects_list) {
//        Today_Mars_Mar_objects_list = today_Mars_Mar_objects_list;
//    }
//
//     public boolean isSelected_in_admin() {
//        return IsSelected_in_admin;
//    }
//
//    public void setSelected_in_admin(boolean selected_in_admin) {
//        IsSelected_in_admin = selected_in_admin;
//    }
//
//
//    public void setQuantity_to_update_on_server(int quantity_to_update_on_server) {
//        this.quantity_to_update_on_server = quantity_to_update_on_server;
//    }
//
//    public void setSelected_inreturn(boolean selected_inreturn) {
//        IsSelected_inreturn = selected_inreturn;
//    }
//
//    public void setAction_applied(String action_applied) {
//        Action_applied = action_applied;
//    }
//
//    public void setWaste_Action_applied(String waste_Action_applied) {
//        Waste_Action_applied = waste_Action_applied;
//    }
//
//    public void setWaste_qty_to_update_on_server(int waste_qty_to_update_on_server) {
//        this.waste_qty_to_update_on_server = waste_qty_to_update_on_server;
//    }
//
//    public int getQuantity_to_update_on_server() {
//        return quantity_to_update_on_server;
//    }
//
//    public boolean isSelected_inreturn() {
//        return IsSelected_inreturn;
//    }
//
//    public String getAction_applied() {
//        return Action_applied;
//    }
//
//    public String getWaste_Action_applied() {
//        return Waste_Action_applied;
//    }
//
//    public int getWaste_qty_to_update_on_server() {
//        return waste_qty_to_update_on_server;
//    }
//
//
//    public String getPatient_id() {
//        return patient_id;
//    }
//
//    public void setPatient_id(String patient_id) {
//        this.patient_id = patient_id;
//    }
//
//    public String getCheckin_cycle_type() {
//        return checkin_cycle_type;
//    }
//
//    public void setCheckin_cycle_type(String checkin_cycle_type) {
//        this.checkin_cycle_type = checkin_cycle_type;
//    }
//
//    public void setIsSelected(String isSelected) {
//        IsSelected = isSelected;
//    }
//
//    public int getNew_checked_in_quantity_toupdate() {
//        return new_checked_in_quantity_toupdate;
//    }
//
//    public String getTIME_SLOT_DOSES_array() {
//        return TIME_SLOT_DOSES_array;
//    }
//
//    public void setNew_checked_in_quantity_toupdate(int new_checked_in_quantity_toupdate) {
//        this.new_checked_in_quantity_toupdate = new_checked_in_quantity_toupdate;
//    }
//
//    public void setChecked_in_quantity(int checked_in_quantity) {
//        this.checked_in_quantity = checked_in_quantity;
//    }
//
//    public String getPrescribed_time_slots_array() {
//        return prescribed_time_slots_array;
//    }
//
//    public String getLast_admin() {
//        return last_admin;
//    }
//
//    public PRESCRIPTION_COMMON_Model(int id,
//                                     String indications,
//                                     String instructions,
//                                     String is_patch,
//                                     String is_special,
//                                     String non_blistered,
//                                     String prescription_type,
//                                     String quantity_dispensed,
//                                     String special_medication_name,
//                                     String start_date,
//                                     String status,
//                                     String suppress_non_blister_display,
//                                     String mandatory_instructions,
//                                     String can_carry_forward,
//                                     String front_image_url,
//                                     String drug_name,
//                                     String detailed_drug_name,
//                                     String short_drug_name,
//                                     String patch_location,
//                                     String prescribed_time_slots_array,
//                                     String last_admin_object,
//                                     String first_check_in_date,
//                                     int dispensed_quantity,
//                                     int available_quantity,
//                                     int checked_in_quantity,
//                                     int brought_forward_quantity,
//                                     int returned_quantity,
//                                     int destroyed_quantity,
//                                     int carried_forward_quantity,
//                                     int wasted_quantity,
//                                     int administered_quantity,
//                                     int audited_quantity,
//                                     String patient_absent_today,
//                                     String is_controlled,
//                                     String pil_url,
//                                     String medication_object,
//                                     String brnd_medi_object,
//                                     String TIME_SLOT_DOSES_array,
//                                     int new_checked_in_quantity_toupdate,
//                                     String IsSelected,
//                                     String checkin_cycle_type,
//                                     String patient_id,
//                                     String is_for_admintab) {
//        this.id = id;
//        this.indications = indications;
//        this.instructions = instructions;
//        this.is_patch = is_patch;
//        this.is_special = is_special;
//        this.non_blistered = non_blistered;
//        this.prescription_type = prescription_type;
//        this.quantity_dispensed = quantity_dispensed;
//        this.special_medication_name = special_medication_name;
//        this.start_date = start_date;
//        this.status = status;
//        this.suppress_non_blister_display = suppress_non_blister_display;
//        this.mandatory_instructions = mandatory_instructions;
////        Today_Mars_Mar_objects_list = today_Mars_Mar_objects_list;
//        this.can_carry_forward = can_carry_forward;
//        this.front_image_url = front_image_url;
//        this.drug_name = drug_name;
//        this.detailed_drug_name = detailed_drug_name;
//        this.short_drug_name = short_drug_name;
//        this.patch_location = patch_location;
////        this.PRES_TIME_SLOT_objects_list = PRES_TIME_SLOT_objects_list;
////        this.last_admin_object = last_admin_object;
//
//        this.prescribed_time_slots_array = prescribed_time_slots_array;
//        this.last_admin = last_admin_object;
//        this.first_check_in_date = first_check_in_date;
//        this.dispensed_quantity = dispensed_quantity;
//        this.available_quantity = available_quantity;
//        this.checked_in_quantity = checked_in_quantity;
//        this.brought_forward_quantity = brought_forward_quantity;
//        this.returned_quantity = returned_quantity;
//        this.destroyed_quantity = destroyed_quantity;
//        this.carried_forward_quantity = carried_forward_quantity;
//        this.wasted_quantity = wasted_quantity;
//        this.administered_quantity = administered_quantity;
//        this.audited_quantity = audited_quantity;
//        this.patient_absent_today = patient_absent_today;
//        this.is_controlled = is_controlled;
//        this.pil_url = pil_url;
//        this.Medication_object = medication_object;
//        this.brnd_medi_object = brnd_medi_object;
////        this.TIME_SLOT_DOSES_objects_list = TIME_SLOT_DOSES_objects_list;
//
//        this.TIME_SLOT_DOSES_array = TIME_SLOT_DOSES_array;
//        this.IsSelected = IsSelected;
//
//        this.new_checked_in_quantity_toupdate = new_checked_in_quantity_toupdate;
//
//        this.checkin_cycle_type = checkin_cycle_type;
//        this.patient_id = patient_id;
//        this.is_for_admintab=is_for_admintab;
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public String getIndications() {
//        return indications;
//    }
//
//    public String getInstructions() {
//        return instructions;
//    }
//
//    public String getIs_patch() {
//        return is_patch;
//    }
//
//    public String getIs_special() {
//        return is_special;
//    }
//
//    public String getNon_blistered() {
//        return non_blistered;
//    }
//
//    public String getPrescription_type() {
//        return prescription_type;
//    }
//
//    public String getQuantity_dispensed() {
//        return quantity_dispensed;
//    }
//
//    public String getSpecial_medication_name() {
//        return special_medication_name;
//    }
//
//    public String getStart_date() {
//        return start_date;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public String getSuppress_non_blister_display() {
//        return suppress_non_blister_display;
//    }
//
//    public String getMandatory_instructions() {
//        return mandatory_instructions;
//    }
//
////    public ArrayList<Today_Mars_Mar_Bean> getToday_Mars_Mar_objects_list() {
////        return Today_Mars_Mar_objects_list;
////    }
//
//    public String getCan_carry_forward() {
//        return can_carry_forward;
//    }
//
//    public String getFront_image_url() {
//        return front_image_url;
//    }
//
//    public String getDrug_name() {
//        return drug_name;
//    }
//
//    public String getDetailed_drug_name() {
//        return detailed_drug_name;
//    }
//
//    public String getShort_drug_name() {
//        return short_drug_name;
//    }
//
//    public String getPatch_location() {
//        return patch_location;
//    }
//
////    public ArrayList<PRESCRIBED_TIME_SLOT> getPRES_TIME_SLOT_objects_list() {
////        return PRES_TIME_SLOT_objects_list;
////    }
////
////    public LAST_ADMIN_MAR getLast_admin_object() {
////        return last_admin_object;
////    }
//
//    public String getFirst_check_in_date() {
//        return first_check_in_date;
//    }
//
//    public int getDispensed_quantity() {
//        return dispensed_quantity;
//    }
//
//    public int getAvailable_quantity() {
//        return available_quantity;
//    }
//
//    public int getChecked_in_quantity() {
//        return checked_in_quantity;
//    }
//
//    public int getBrought_forward_quantity() {
//        return brought_forward_quantity;
//    }
//
//    public int getReturned_quantity() {
//        return returned_quantity;
//    }
//
//    public int getDestroyed_quantity() {
//        return destroyed_quantity;
//    }
//
//    public int getCarried_forward_quantity() {
//        return carried_forward_quantity;
//    }
//
//    public int getWasted_quantity() {
//        return wasted_quantity;
//    }
//
//    public int getAdministered_quantity() {
//        return administered_quantity;
//    }
//
//    public int getAudited_quantity() {
//        return audited_quantity;
//    }
//
//    public String getPatient_absent_today() {
//        return patient_absent_today;
//    }
//
//    public String getIs_controlled() {
//        return is_controlled;
//    }
//
//    public String getPil_url() {
//        return pil_url;
//    }
//
//    public String getMedication_object() {
//        return Medication_object;
//    }
//
//    public String getBrnd_medi_object() {
//        return brnd_medi_object;
//    }
//
////    public ArrayList<TIME_SLOT_DOSES> getTIME_SLOT_DOSES_objects_list() {
////        return TIME_SLOT_DOSES_objects_list;
////    }
//
//
//    public String isSelected() {
//        return IsSelected;
//    }
//
//}
//
