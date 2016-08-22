package com.syscraft.caremeds.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.syscraft.caremeds.New_Model.Homely_Model_Phase2;
import com.syscraft.caremeds.New_Model.Homely_Today_Mars_Mar_Bean_Phase2;
import com.syscraft.caremeds.New_Model.MeasureModel_Phase2;
import com.syscraft.caremeds.New_Model.NotesModel_Phase2;
import com.syscraft.caremeds.New_Model.PRESCRIBED_TIME_SLOT_phase2;
import com.syscraft.caremeds.New_Model.Patient_Model_Phase2;
import com.syscraft.caremeds.New_Model.Today_Mars_Mar_Bean_Phase2;
import com.syscraft.caremeds.New_Model.Yesterday_Mar_Bean;
import com.syscraft.caremeds.Utility.Date_utility;
import com.syscraft.caremeds.model.ABOUT_PATIENT_INR;
import com.syscraft.caremeds.model.Homely_Today_Mars_Mar_Bean;
import com.syscraft.caremeds.model.MeasureModel;
import com.syscraft.caremeds.model.New_Homely_Remedy_Model;
import com.syscraft.caremeds.model.New_Patient_Model;
import com.syscraft.caremeds.model.NotesModel;
import com.syscraft.caremeds.model.OFFLINE_DATA_MODEL;

import com.syscraft.caremeds.New_Model.PRESCRIPTION_Model_Phase2;
import com.syscraft.caremeds.model.Remedy_Dose_Bean;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class CRMDS_Database_Handler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Namee
    private static final String DATABASE_NAME = "CareMeds_DataBase_Manager_";
    //  table name
    private static final String TABLE_PRESCRIPTION = "Prescription_Details";
    public static final String NOTES_TABLE = "notes";
    public static final String MEASURE_TABLE = "measure";
    public static final String HOMELY_TABLE = "homely_table";
    public static final String TODAY_MARS_TABLE = "today_mars_table";
    public static final String YESTERDAY_MARS_TABLE = "yesterday_mars_table";
    public static final String PATIENTS_TABLE = "Patients_table";
    public static final String OFFLINE_TABLE = "offline_table";
    public static final String ABOUT_PATIENTS_INR_DETAIL_TABLE = "about_patients_inr_detail_table";

    /* PRESCRIPTION Table Column Name */
    private static final String KEY_PRESCRIPTION_ID = "_id";
    private static final String KEY_PRESCRIPTION_TYPE = "prescription_type";
    private static final String KEY_DRUG_NAME = "drug_name";
    private static final String KEY_INSTRUCTION = "instructions";
    private static final String KEY_INDICATION = "indications";
    private static final String KEY_MANDATORY_INSTRUCTION = "mandatory_instructions";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_END_DATE = "end_date";
    private static final String KEY_IS_PATCH = "is_patch";
    private static final String KEY_PATCH_LOCATION = "patch_location";
    private static final String KEY_NON_BLISTERED = "non_blistered";
    private static final String KEY_IS_CONTROLLED = "is_controlled";
    private static final String KEY_SUPPRESS_NON_BISTER_DISPLAY = "suppress_non_blister_display";
    private static final String KEY_CAN_CARRY_FORWARD = "can_carry_forward";
    private static final String KEY_FRONT_IMAGE_URL = "front_image_url";
    private static final String KEY_DISPENSED_QUANTITY = "dispensed_quantity";
    private static final String KEY_AVAILABLE_QUANTITY = "available_quantity";
    private static final String KEY_CHECKED_IN_QUANTITY = "checked_in_quantity";
    private static final String KEY_BROUGHT_FORWARD_QUANTITY = "brought_forward_quantity";
    private static final String KEY_RETURNED_QUANTITY = "returned_quantity";
    private static final String KEY_DESTROYED_QUANTITY = "destroyed_quantity";
    private static final String KEY_CARRIED_FORWORD_QUANTITY = "carried_forward_quantity";
    private static final String KEY_WASTED_QUANTITY = "wasted_quantity";
    private static final String KEY_ADMINISTERED_QUANTITY = "administered_quantity";
    private static final String KEY_AUDITED_QUANTITY = "audited_quantity";
    private static final String KEY_IRREGULAR_GRID = "irregular_grid";
    private static final String KEY_IRREGULAR_GRID_ARRAY = "irregular_grid_array";
    private static final String KEY_LAST_ADMIN = "last_admin";
    private static final String KEY_SPECIAL_MEDICATION_NAME = "special_medication_name";
    private static final String KEY_IS_SPECIAL = "is_special";
    private static final String KEY_PRESCRIBED_TIME_SLOT_ARRAY = "prescribed_time_slots";

//    private static final String KEY_QUANTITY_DISPENSED = "quantity_dispensed";
//    private static final String KEY_STATUS = "status";
//    private static final String KEY_DETAIL_DRUG_NAME = "detailed_drug_name";
//    private static final String KEY_SHORT_DRUG_NAME = "short_drug_name";
//    private static final String KEY_FIRST_CHECKIN_DATE = "first_check_in_date";
//    private static final String KEY_PATIENT_ABSENT_TODAY = "patient_absent_today";
//    private static final String KEY_PIL_URL = "pil_url";
//    private static final String KEY_MEDICATION = "medication";
//    private static final String KEY_BRANDED_MEDICATION = "branded_medication";
//    private static final String KEY_TIME_SLOT_DOSES = "time_slot_doses";

    //below fields are not in response but we need it for our logics
    private static final String KEY_CHECKED_IN_QUANTITY_TOUPDATE = "new_checked_in_quantity_toupdate";
    private static final String KEY_IS_CHECKED_IN_SELECTED = "is_checked_in_selected";
    private static final String KEY_CHECKED_IN_CYCLE_TYPE = "checked_in_cycle_type";
    private static final String KEY_PATIENT_ID = "patient_id";
    private static final String KEY_IS_FOR_ADMIN = "is_for_admin";

    /* Notes Table Column Name */

    public static final String NOTES_DBID = "notes_dbid";
    public static final String NID = "nid";
    public static final String NOTE_SUBJECT = "note_subject";
    public static final String CONTENT = "content";
    public static final String NOTE_TYPE = "note_type";
    public static final String NOTE_UPDATED = "note_updated";
    public static final String NOTE_CREATED = "note_created";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String FULL_NAME = "fullname";
    public static final String EMAIL = "email";
    public static final String NOTES_PATIENT_ID = "patient_id";

   /* Measure Table Column Name */

    public static final String MEASURE_DBID = "measure_dbid";
    public static final String MID = "mid";
    public static final String MEASURE_VALUE = "measure_value";
    public static final String MEASURE_UPDATED = "measure_updated";
    public static final String MEASURENT_TYPE_ID = "measure_type_id";
    public static final String INSIDE_MEASURE_NAME = "inside_measure_name";
    public static final String INSIDE_MEASURE_UNITS = "inside_measure_units";
    public static final String INSIDE_MEASURE_UPDATED_AT = "inside_measure_updated_at";
    public static final String INSIDE_MEASURE_CREATED_AT = "inside_measure_created_at";

    //    public static final String MEASURE_CREATED = "measure_created";
//    public static final String MEASURE_DELETED = "measure_deleted";
    public static final String MEASURE_PATIENT_ID = "measure__patient_id";
//    public static final String INSIDE_MEASURE_DELETED_AT = "inside_measure_deleted_at";
//    public static final String INSIDE_MEASURE_ID = "inside_measure_id";

    public static final String IS_MEASURE_EDITED = "is_measure_edited";

    /* Homely_remedy Table Column Name */

    public static final String HOMELY_ID = "hid";
    //    public static final String HOMELY_CARE_HOME_ID = "care_home_id";
//    public static final String HOMELY_CREATED = "homely_created_at";
//    public static final String HOMELY_DELETED = "homely_deleted_at";
    public static final String HOMELY_MANDATORY_WARNINGS = "mandatory_warnings";
    public static final String HOMELY_NAME = "homely_name";
    public static final String HOMELY_UPDATED = "homely_update";
    //    public static final String HOMELY_TODAYSMARS_LIST = "todaymars_list";
    public static final String HOMELY_PATIENT_ID = "homely_patient_id";


    /* Todays_Mars Table Column Name */


    public static final String TID = "tid";
    public static final String TODAY_MARS_ID = "today_mar_id";
    public static final String TODAY_MARS_ADMINISTRATED_AT = "administrated_at";
    public static final String TODAY_MARS_DOSE = "dose";
    public static final String TODAY_MARS_FALSE_REASON = "false_reason";
    public static final String TODAY_MARS_IS_WASTE = "is_waste";
    public static final String TODAY_MARS_OUTCOME = "outcome";
    public static final String TODAY_MARS_QUANTITY_WASTED = "quantity_wasted";
    public static final String TODAY_MARS_SLOT_TIME = "slot_time";
    public static final String TODAY_MARS_UPDATED_AT = "updated_at";
    public static final String TODAY_MARS_USERID = "user_id";
    public static final String TODAY_MARS_USERNAME = "username";
    public static final String TODAY_MARS_USERFULLNAME = "user_fullname";
    public static final String TODAY_MARS_USEREMAIL = "email";

//    public static final String TODAY_MARS_CREATED = "created_at";
//    public static final String TODAY_MARS_DELETED = "deleted_at";
//    public static final String TODAY_MARS_GPS_LOCATION = "gps_location";


    public static final String TODAY_MARS_HOMELY_REMEDY_ID = "homely_remedy_id";
    public static final String TODAY_MARS_HOMELY_REMEDY_NAME = "homely_remedy_name";
    //    public static final String TODAY_MARS_MEDICATION_ID = "medication_id";
    public static final String TODAY_MARS_PRESCRIPTION_ID = "prescription_id";
    public static final String TODAY_MARS_PATIENT_ID = "patient_id";

    public static final String TODAY_MARS_SECONDRY_USERID = "secondary_user_id";
    public static final String TODAY_MARS_SECONDRY_USERNAME = "seconday_username";
    public static final String TODAY_MARS_SECONDRY_USERFULLNAME = "seconday_user_fullname";
    public static final String TODAY_MARS_SECONDRY_USEREMAIL = "seconday_user_email";


//    public static final String TODAY_MARS_SECONDRY_USER_ID = "secondary_user_id";
//    public static final String TODAY_MARS_SECONDRY_USER_FULLNAME = "seconday_user_fullname";
//    public static final String TODAY_MARS_UID = "uid";



     /* Yesterdays_Mars Table Column Name */

    public static final String YID = "yid";
    public static final String YESTERDAY_MARS_ID = "yesterday_mar_id";
    public static final String YESTERDAY_MARS_ADMINISTRATED_AT = "administrated_at";
    public static final String YESTERDAY_MARS_DOSE = "dose";
    public static final String YESTERDAY_MARS_FALSE_REASON = "false_reason";
    public static final String YESTERDAY_MARS_IS_WASTE = "is_waste";
    public static final String YESTERDAY_MARS_OUTCOME = "outcome";
    public static final String YESTERDAY_MARS_QUANTITY_WASTED = "quantity_wasted";
    public static final String YESTERDAY_MARS_SLOT_TIME = "slot_time";
    public static final String YESTERDAY_MARS_UPDATED_AT = "updated_at";
    public static final String YESTERDAY_MARS_USERID = "user_id";
    public static final String YESTERDAY_MARS_USERNAME = "username";
    public static final String YESTERDAY_MARS_USERFULLNAME = "user_fullname";
    public static final String YESTERDAY_MARS_USEREMAIL = "email";
    public static final String YESTERDAY_MARS_HOMELY_REMEDY_ID = "homely_remedy_id";
    public static final String YESTERDAY_MARS_HOMELY_REMEDY_NAME = "homely_remedy_name";
    public static final String YESTERDAY_MARS_PRESCRIPTION_ID = "prescription_id";
    public static final String YESTERDAY_MARS_PATIENT_ID = "patient_id";
    public static final String YESTERDAY_MARS_SECONDRY_USERID = "secondary_user_id";
    public static final String YESTERDAY_MARS_SECONDRY_USERNAME = "seconday_username";
    public static final String YESTERDAY_MARS_SECONDRY_USERFULLNAME = "seconday_user_fullname";
    public static final String YESTERDAY_MARS_SECONDRY_USEREMAIL = "seconday_user_email";


    /* Todays_Mars Table Column Name */

    public static final String P_ID = "p_id";
    public static final String PATIENT_FULLNAME = "fullname";
    public static final String PATIENT_TITLE = "title";
    public static final String PATIENT_FIRSTNAME = "first_name";
    public static final String PATIENT_NAME = "name";
    public static final String PATIENT_ADDRESS = "address";
    public static final String PATIENT_DOB = "dob";
    public static final String PATIENT_NHS_NUMBER = "nhs_number";
    public static final String PATIENT_ALLERGIES = "allergies";
    //    public static final String PATIENT_CARE_HOME_ID = "care_home_id";
//    public static final String PATIENT_CREATED_AT = "created_at";
//    public static final String PATIENT_CYCLE_BASE_DATE = "cycle_base_date";
    public static final String PATIENT_GP_NAME = "gp_name";
    public static final String PATIENT_ROOM = "room";
    public static final String PATIENT_CYCLE_DURATION = "cycle_duration";
    //    public static final String PATIENT_DELETED_AT = "deleted_at";
    public static final String PATIENT_NOTES = "patient_notes";
    public static final String PATIENT_IS_ABSENT = "is_absent";
    //    public static final String PATIENT_GP_ID = "gp_id";
//    public static final String PATIENT_INACTIVE = "inactive";
    public static final String PATIENT_CURRENT_ABSENCE_START = "current_absence_start";
    public static final String PATIENT_CURRENT_ABSENCE_END = "current_absence_end";
    public static final String PATIENT_CURRENT_ABSENCE_REASON = "current_absence_reason";
    public static final String PATIENT_INR_READING = "inr_reading";
    public static final String PATIENT_INR_DATE = "inr_date";
    public static final String PATIENT_WARFARINE_DOSE = "warfarine_dose";
    public static final String PATIENT_PHOTO_IMAGE = "photo_image";
    public static final String PATIENT_UPDATED_AT = "updated_at";
    public static final String PATIENT_LAST_BS_SITE = "last_bs_site";
    public static final String PATIENT_PRE_TIME_SLOTS_ARRAY = "prescribed_time_slots_for_patient_list";

    /****************************************************/


    public static final String ABOUT_P_ID = "p_id";
    public static final String ABOUT_PATIENT_ADDRESS = "address";
    public static final String ABOUT_PATIENT_ALLERGIES = "allergies";
    public static final String ABOUT_PATIENT_CARE_HOME_ID = "care_home_id";
    public static final String ABOUT_PATIENT_CREATED_AT = "created_at";
    public static final String ABOUT_PATIENT_CYCLE_BASE_DATE = "cycle_base_date";
    public static final String ABOUT_PATIENT_CYCLE_DURATION = "cycle_duration";
    public static final String ABOUT_PATIENT_DELETED_AT = "deleted_at";
    public static final String ABOUT_PATIENT_DOB = "dob";
    public static final String ABOUT_PATIENT_FIRSTNAME = "first_name";
    public static final String ABOUT_PATIENT_GP_ID = "gp_id";
    public static final String ABOUT_PATIENT_GP_NAME = "gp_name";
    public static final String ABOUT_PATIENT_INACTIVE = "inactive";
    public static final String ABOUT_PATIENT_NAME = "name";
    public static final String ABOUT_PATIENT_NHS_NUMBER = "nhs_number";
    public static final String ABOUT_PATIENT_NOTES = "patient_notes";
    public static final String ABOUT_PATIENT_PHOTO_IMAGE = "photo_image";
    public static final String ABOUT_PATIENT_ROOM = "room";
    public static final String ABOUT_PATIENT_TITLE = "title";
    public static final String ABOUT_PATIENT_UPDATED_AT = "updated_at";
    public static final String ABOUT_PATIENT_INR_READING = "inr_reading";
    public static final String ABOUT_PATIENT_INR_DATE = "inr_date";
    public static final String ABOUT_PATIENT_WARFARIE_DOSE = "warfarin_dose";

    /****************************************************/

    public static final String OFFLINE__ID = "o_id";
    public static final String OFFLINE_URL = "offline_url";
    public static final String OFFLINE_DATA_TO_POST = "offline_data_to_post";
    public static final String CARETAKER_ID = "caretaker_id";
    public static final String OPERATION_TYPE = "operation_type";
    public static final String ID_TO_RELATE = "id_to_relate";
    public static final String OFFLINE_PATIENT_ID = "offline_patient_id";

    public CRMDS_Database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e("onCreate", "--------->");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCRIPTION);

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRESCRIPTION + "("
                + KEY_PRESCRIPTION_ID + " INTEGER PRIMARY KEY,"
                + KEY_PRESCRIPTION_TYPE + " TEXT,"
                + KEY_DRUG_NAME + " TEXT,"
                + KEY_INSTRUCTION + " TEXT,"
                + KEY_INDICATION + " TEXT,"
                + KEY_MANDATORY_INSTRUCTION + " TEXT,"
                + KEY_START_DATE + " TEXT,"
                + KEY_END_DATE + " TEXT,"
                + KEY_IS_PATCH + " TEXT,"
                + KEY_PATCH_LOCATION + " TEXT,"
                + KEY_NON_BLISTERED + " TEXT,"
                + KEY_IS_CONTROLLED + " TEXT,"
                + KEY_SUPPRESS_NON_BISTER_DISPLAY + " TEXT,"
                + KEY_CAN_CARRY_FORWARD + " TEXT,"
                + KEY_FRONT_IMAGE_URL + " TEXT,"
                + KEY_DISPENSED_QUANTITY + " TEXT,"
                + KEY_AVAILABLE_QUANTITY + " TEXT,"
                + KEY_CHECKED_IN_QUANTITY + " TEXT,"
                + KEY_BROUGHT_FORWARD_QUANTITY + " TEXT,"
                + KEY_RETURNED_QUANTITY + " TEXT,"
                + KEY_DESTROYED_QUANTITY + " TEXT,"
                + KEY_CARRIED_FORWORD_QUANTITY + " TEXT,"
                + KEY_WASTED_QUANTITY + " TEXT,"
                + KEY_ADMINISTERED_QUANTITY + " TEXT,"
                + KEY_AUDITED_QUANTITY + " TEXT,"
                + KEY_IRREGULAR_GRID + " TEXT,"
                + KEY_IRREGULAR_GRID_ARRAY + " TEXT,"
                + KEY_LAST_ADMIN + " TEXT,"
                + KEY_SPECIAL_MEDICATION_NAME + " TEXT,"
                + KEY_IS_SPECIAL + " TEXT,"
                + KEY_PRESCRIBED_TIME_SLOT_ARRAY + " TEXT,"
                + KEY_CHECKED_IN_QUANTITY_TOUPDATE + " TEXT,"
                + KEY_IS_CHECKED_IN_SELECTED + " TEXT,"
                + KEY_CHECKED_IN_CYCLE_TYPE + " TEXT,"
                + KEY_PATIENT_ID + " TEXT,"
                + KEY_IS_FOR_ADMIN + " TEXT"
                + ")";


        String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTES_TABLE + "("
                + NOTES_DBID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NID + " TEXT, "
                + NOTE_SUBJECT + " TEXT, "
                + CONTENT + " TEXT, "
                + NOTE_TYPE + " TEXT, "
                + NOTE_UPDATED + " TEXT, "
                + NOTE_CREATED + " TEXT, "
                + USER_ID + " TEXT, "
                + USERNAME + " TEXT, "
                + FULL_NAME + " TEXT, "
                + EMAIL + " TEXT, "
                + NOTES_PATIENT_ID + " TEXT "
                + ");";


        String CREATE_MEASURE_TABLE = "CREATE TABLE " + MEASURE_TABLE + "("
                + MEASURE_DBID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MID + " TEXT, "
                + MEASURE_VALUE + " INTEGER, "
                + MEASURE_UPDATED + " TEXT, "
                + MEASURENT_TYPE_ID + " TEXT, "
                + INSIDE_MEASURE_NAME + " TEXT, "
                + INSIDE_MEASURE_UNITS + " TEXT, "
                + INSIDE_MEASURE_UPDATED_AT + " TEXT, "
                + INSIDE_MEASURE_CREATED_AT + " TEXT, "
                + MEASURE_PATIENT_ID + " TEXT, "
                + IS_MEASURE_EDITED + " TEXT " + ");";


        String CREATE_HOMELY_TABLE = "CREATE TABLE " + HOMELY_TABLE + "("
                + HOMELY_ID + " INTEGER PRIMARY KEY, "
                + HOMELY_MANDATORY_WARNINGS + " TEXT, "
                + HOMELY_NAME + " TEXT, "
                + HOMELY_UPDATED + " TEXT, "
                + HOMELY_PATIENT_ID + " TEXT " + ");";


//        String CREATE_TODAY_MARS_TABLE = "CREATE TABLE " + TODAY_MARS_TABLE + "("
//                + TID + " INTEGER PRIMARY KEY, "
//                + TODAY_MARS_ADMINISTRATED_AT  + " TEXT, "
//                + TODAY_MARS_DOSE + " TEXT, "
//                + TODAY_MARS_FALSE_REASON  + " TEXT, "
//                + TODAY_MARS_IS_WASTE + " TEXT, "
//                + TODAY_MARS_OUTCOME + " TEXT, "
//                + TODAY_MARS_QUANTITY_WASTED  + " TEXT, "
//                + TODAY_MARS_SLOT_TIME + " TEXT, "
//                + TODAY_MARS_UPDATED_AT + " TEXT, "
//                + TODAY_MARS_USERID + " TEXT, "
//                + TODAY_MARS_USERNAME + " TEXT, "
//                + TODAY_MARS_USERFULLNAME + " TEXT, "
//                + TODAY_MARS_USEREMAIL + " TEXT, "
//                + TODAY_MARS_PATIENT_ID + " TEXT "
//                + ");";


        String CREATE_TODAY_MARS_TABLE = "CREATE TABLE " + TODAY_MARS_TABLE + "("
                + TID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TODAY_MARS_ID + " TEXT, "
                + TODAY_MARS_ADMINISTRATED_AT + " TEXT, "
                + TODAY_MARS_DOSE + " TEXT, "
                + TODAY_MARS_FALSE_REASON + " TEXT, "
                + TODAY_MARS_IS_WASTE + " TEXT, "
                + TODAY_MARS_OUTCOME + " TEXT, "
                + TODAY_MARS_QUANTITY_WASTED + " TEXT, "
                + TODAY_MARS_SLOT_TIME + " TEXT, "
                + TODAY_MARS_UPDATED_AT + " TEXT, "
                + TODAY_MARS_USERID + " TEXT, "
                + TODAY_MARS_USERNAME + " TEXT, "
                + TODAY_MARS_USERFULLNAME + " TEXT, "
                + TODAY_MARS_USEREMAIL + " TEXT, "
                + TODAY_MARS_HOMELY_REMEDY_ID + " TEXT, "
                + TODAY_MARS_HOMELY_REMEDY_NAME + " TEXT, "
                + TODAY_MARS_PRESCRIPTION_ID + " TEXT, "
                + TODAY_MARS_PATIENT_ID + " TEXT, "
                + TODAY_MARS_SECONDRY_USERID + " TEXT, "
                + TODAY_MARS_SECONDRY_USERNAME + " TEXT, "
                + TODAY_MARS_SECONDRY_USERFULLNAME + " TEXT, "
                + TODAY_MARS_SECONDRY_USEREMAIL + " TEXT "
                + ");";


        String CREATE_YESTERDAY_MARS_TABLE = "CREATE TABLE " + YESTERDAY_MARS_TABLE + "("
                + YID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + YESTERDAY_MARS_ID + " TEXT, "
                + YESTERDAY_MARS_ADMINISTRATED_AT + " TEXT, "
                + YESTERDAY_MARS_DOSE + " TEXT, "
                + YESTERDAY_MARS_FALSE_REASON + " TEXT, "
                + YESTERDAY_MARS_IS_WASTE + " TEXT, "
                + YESTERDAY_MARS_OUTCOME + " TEXT, "
                + YESTERDAY_MARS_QUANTITY_WASTED + " TEXT, "
                + YESTERDAY_MARS_SLOT_TIME + " TEXT, "
                + YESTERDAY_MARS_UPDATED_AT + " TEXT, "
                + YESTERDAY_MARS_USERID + " TEXT, "
                + YESTERDAY_MARS_USERNAME + " TEXT, "
                + YESTERDAY_MARS_USERFULLNAME + " TEXT, "
                + YESTERDAY_MARS_USEREMAIL + " TEXT, "
                + YESTERDAY_MARS_HOMELY_REMEDY_ID + " TEXT, "
                + YESTERDAY_MARS_HOMELY_REMEDY_NAME + " TEXT, "
                + YESTERDAY_MARS_PRESCRIPTION_ID + " TEXT, "
                + YESTERDAY_MARS_PATIENT_ID + " TEXT, "
                + YESTERDAY_MARS_SECONDRY_USERID + " TEXT, "
                + YESTERDAY_MARS_SECONDRY_USERNAME + " TEXT, "
                + YESTERDAY_MARS_SECONDRY_USERFULLNAME + " TEXT, "
                + YESTERDAY_MARS_SECONDRY_USEREMAIL + " TEXT "
                + ");";


        String CREATE_PATIENTS_TABLE = "CREATE TABLE " + PATIENTS_TABLE + "("
                + P_ID + " INTEGER PRIMARY KEY, "
                + PATIENT_FULLNAME + " TEXT, "
                + PATIENT_TITLE + " TEXT, "
                + PATIENT_FIRSTNAME + " TEXT, "
                + PATIENT_NAME + " TEXT, "
                + PATIENT_ADDRESS + " TEXT, "
                + PATIENT_DOB + " TEXT, "
                + PATIENT_NHS_NUMBER + " TEXT, "
                + PATIENT_ALLERGIES + " TEXT, "
                + PATIENT_GP_NAME + " TEXT, "
                + PATIENT_ROOM + " TEXT, "
                + PATIENT_CYCLE_DURATION + " TEXT, "
                + PATIENT_NOTES + " TEXT, "
                + PATIENT_IS_ABSENT + " TEXT, "
                + PATIENT_CURRENT_ABSENCE_START + " TEXT, "
                + PATIENT_CURRENT_ABSENCE_END + " TEXT, "
                + PATIENT_INR_READING + " TEXT, "
                + PATIENT_INR_DATE + " TEXT, "
                + PATIENT_WARFARINE_DOSE + " TEXT, "
                + PATIENT_PHOTO_IMAGE + " TEXT, "
                + PATIENT_UPDATED_AT + " TEXT, "
                + PATIENT_LAST_BS_SITE + " TEXT, "
                + PATIENT_PRE_TIME_SLOTS_ARRAY + " TEXT, "
                + PATIENT_CURRENT_ABSENCE_REASON + " TEXT, "
                + USER_ID + " TEXT "
                + ");";


        String CREATE_ABOUT_PATIENTS_INR_DETAIL_TABLE = "CREATE TABLE " + ABOUT_PATIENTS_INR_DETAIL_TABLE + "("
                + ABOUT_P_ID + " INTEGER PRIMARY KEY, "
                + ABOUT_PATIENT_ADDRESS + " TEXT, "
                + ABOUT_PATIENT_ALLERGIES + " TEXT, "
                + ABOUT_PATIENT_CARE_HOME_ID + " TEXT, "
                + ABOUT_PATIENT_CREATED_AT + " TEXT, "
                + ABOUT_PATIENT_CYCLE_BASE_DATE + " TEXT, "
                + ABOUT_PATIENT_CYCLE_DURATION + " TEXT, "
                + ABOUT_PATIENT_DELETED_AT + " TEXT, "
                + ABOUT_PATIENT_DOB + " TEXT, "
                + ABOUT_PATIENT_FIRSTNAME + " TEXT, "
                + ABOUT_PATIENT_GP_ID + " TEXT, "
                + ABOUT_PATIENT_GP_NAME + " TEXT, "
                + ABOUT_PATIENT_INACTIVE + " TEXT, "
                + ABOUT_PATIENT_NAME + " TEXT, "
                + ABOUT_PATIENT_NHS_NUMBER + " TEXT, "
                + ABOUT_PATIENT_NOTES + " TEXT, "
                + ABOUT_PATIENT_PHOTO_IMAGE + " TEXT, "
                + ABOUT_PATIENT_ROOM + " TEXT, "
                + ABOUT_PATIENT_TITLE + " TEXT, "
                + ABOUT_PATIENT_UPDATED_AT + " TEXT, "
                + ABOUT_PATIENT_INR_READING + " TEXT, "
                + ABOUT_PATIENT_INR_DATE + " TEXT, "
                + ABOUT_PATIENT_WARFARIE_DOSE + " TEXT "
                + ");";


        String CREATE_OFFLINE_TABLE = "CREATE TABLE " + OFFLINE_TABLE + "("
                + OFFLINE__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OFFLINE_URL + " TEXT, "
                + OFFLINE_DATA_TO_POST + " TEXT, "
                + CARETAKER_ID + " TEXT, "
                + OPERATION_TYPE + " TEXT, "
                + ID_TO_RELATE + " TEXT, "
                + OFFLINE_PATIENT_ID + " TEXT "
                + ");";


        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_NOTES_TABLE);
        db.execSQL(CREATE_MEASURE_TABLE);
        db.execSQL(CREATE_HOMELY_TABLE);
        db.execSQL(CREATE_TODAY_MARS_TABLE);
        db.execSQL(CREATE_PATIENTS_TABLE);
        db.execSQL(CREATE_ABOUT_PATIENTS_INR_DETAIL_TABLE);
        db.execSQL(CREATE_OFFLINE_TABLE);
        db.execSQL(CREATE_YESTERDAY_MARS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCRIPTION);
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEASURE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HOMELY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TODAY_MARS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PATIENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ABOUT_PATIENTS_INR_DETAIL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OFFLINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + YESTERDAY_MARS_TABLE);
        // Create tables again
        onCreate(db);
    }


    /*******************************************************************************************/

    public void insert_patients_list(ArrayList<Patient_Model_Phase2> new_patient_Model_List, String caretakerid) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < new_patient_Model_List.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(P_ID, new_patient_Model_List.get(i).getId());
            values.put(PATIENT_FULLNAME, new_patient_Model_List.get(i).getFullname());
            values.put(PATIENT_TITLE, new_patient_Model_List.get(i).getTitle());
            values.put(PATIENT_FIRSTNAME, new_patient_Model_List.get(i).getFirst_name());
            values.put(PATIENT_NAME, new_patient_Model_List.get(i).getFullname());
            values.put(PATIENT_ADDRESS, new_patient_Model_List.get(i).getAddress());
            values.put(PATIENT_DOB, new_patient_Model_List.get(i).getDob());
            values.put(PATIENT_NHS_NUMBER, new_patient_Model_List.get(i).getNhs_number());

            values.put(PATIENT_ALLERGIES, new_patient_Model_List.get(i).getAllergies());
            values.put(PATIENT_GP_NAME, new_patient_Model_List.get(i).getGp_name());
            values.put(PATIENT_ROOM, new_patient_Model_List.get(i).getRoom());
//            values.put(PATIENT_CARE_HOME_ID, "");
//            values.put(PATIENT_CREATED_AT, "");
//            values.put(PATIENT_CYCLE_BASE_DATE, "");
            values.put(PATIENT_CYCLE_DURATION, new_patient_Model_List.get(i).getCycle_duration());
            values.put(PATIENT_NOTES, new_patient_Model_List.get(i).getPatient_notes());
//            values.put(PATIENT_DELETED_AT, "");
//            values.put(PATIENT_GP_ID, "");
//            values.put(PATIENT_INACTIVE, "");
            values.put(PATIENT_INR_READING, new_patient_Model_List.get(i).getInr_reading());
            values.put(PATIENT_INR_DATE, new_patient_Model_List.get(i).getInr_date());
            values.put(PATIENT_WARFARINE_DOSE, new_patient_Model_List.get(i).getWarfarin_dose());
            values.put(PATIENT_IS_ABSENT, new_patient_Model_List.get(i).getIs_absent());
            values.put(PATIENT_CURRENT_ABSENCE_START, new_patient_Model_List.get(i).getCurrent_absence_start());
            values.put(PATIENT_CURRENT_ABSENCE_END, new_patient_Model_List.get(i).getCurrent_absence_end());
            values.put(PATIENT_CURRENT_ABSENCE_REASON, new_patient_Model_List.get(i).getCurrent_absence_reason());
            values.put(PATIENT_PHOTO_IMAGE, new_patient_Model_List.get(i).getPhoto_image());
            values.put(PATIENT_UPDATED_AT, new_patient_Model_List.get(i).getUpdated_at());
            values.put(PATIENT_LAST_BS_SITE, new_patient_Model_List.get(i).getLast_bs_site());
            values.put(PATIENT_PRE_TIME_SLOTS_ARRAY, new_patient_Model_List.get(i).getPrescribed_time_slots_for_patient_list_array());
            values.put(USER_ID, caretakerid);


            // Inserting Row
            long inserted_id = db.insertWithOnConflict(PATIENTS_TABLE, null, values, 5);

            Log.e("PATIENTS_TABLE", "PATIENTS_TABLE inserted_id--------->" + inserted_id);
        }

    }


    public ArrayList<Patient_Model_Phase2> get_patients_fromdb() {
        ArrayList<Patient_Model_Phase2> new_patient_Model_List = new ArrayList<Patient_Model_Phase2>();

        String selectQuery = "SELECT * FROM " + PATIENTS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("PATIENTS_TABLE", "PATIENTS_Name count --------->" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {

                Patient_Model_Phase2 pmp2 = new Patient_Model_Phase2(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                        cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23));

//                New_Patient_Model npm = new New_Patient_Model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
//                        cursor.getString(4), cursor.getString(5), cursor.getString(6),
//                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
//                        cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
//                        cursor.getString(16), cursor.getString(17),
//                        cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22),
//                        cursor.getString(23), cursor.getString(24), cursor.getString(25));

                new_patient_Model_List.add(pmp2);

                Log.e("PATIENTS_TABLE", "PATIENTS_FullName --------->" + cursor.getString(20));

            } while (cursor.moveToNext());
        }
        return new_patient_Model_List;
    }

    public String get_inr_reading(String patient_id) {

        String inr_reading = "";
        String selectQuery = "SELECT inr_reading FROM " + PATIENTS_TABLE + " WHERE " + P_ID + " = " + "'" + patient_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("get_inr_reading", "get_inr_reading count --------->" + cursor.getCount());
        if (cursor.moveToLast()) {
            do {

                inr_reading = cursor.getString(0);
                Log.e("get_inr_reading", "get_inr_reading --------->" + cursor.getString(0));

            } while (cursor.moveToPrevious());
        }
        return inr_reading;
    }


    public String get_inr_date(String patient_id) {

        String inr_reading = "";
        String selectQuery = "SELECT inr_date FROM " + PATIENTS_TABLE + " WHERE " + P_ID + " = " + "'" + patient_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("get_inr_date", "get_inr_date count --------->" + cursor.getCount());
        if (cursor.moveToLast()) {
            do {


                inr_reading = cursor.getString(0);
                Log.e("get_inr_date", "get_inr_date --------->" + cursor.getString(0));

            } while (cursor.moveToPrevious());
        }
        return inr_reading;
    }

    public String get_warfarin_dose(String patient_id) {

        String inr_reading = "";
        String selectQuery = "SELECT warfarine_dose FROM " + PATIENTS_TABLE + " WHERE " + P_ID + " = " + "'" + patient_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("get_warfarin_dose", "get_warfarin_dose count --------->" + cursor.getCount());
        if (cursor.moveToLast()) {
            do {


                inr_reading = cursor.getString(0);
                Log.e("get_warfarin_dose", "get_warfarin_dose --------->" + cursor.getString(0));

            } while (cursor.moveToPrevious());
        }
        return inr_reading;
    }


    /*******************************************************************************************/
    public void addAllFeed(Context context, ArrayList<PRESCRIPTION_Model_Phase2> arrayList) {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.beginTransaction();

        Log.e("addAllFeed", "1--------->" + arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {

            PRESCRIPTION_Model_Phase2 prescription_common_model = arrayList.get(i);
            ContentValues values = new ContentValues();

            values.put(KEY_PRESCRIPTION_ID, prescription_common_model.getId());
            values.put(KEY_PRESCRIPTION_TYPE, prescription_common_model.getPrescription_type());
            values.put(KEY_DRUG_NAME, prescription_common_model.getDrug_name());
            values.put(KEY_INSTRUCTION, prescription_common_model.getInstructions());
            values.put(KEY_INDICATION, prescription_common_model.getIndications());
            values.put(KEY_MANDATORY_INSTRUCTION, prescription_common_model.getMandatory_instructions());
            values.put(KEY_START_DATE, prescription_common_model.getStart_date());
            values.put(KEY_END_DATE, prescription_common_model.getEnd_date());
            values.put(KEY_IS_PATCH, prescription_common_model.getIs_patch());
            values.put(KEY_PATCH_LOCATION, prescription_common_model.getPatch_location());

            values.put(KEY_NON_BLISTERED, prescription_common_model.getNon_blistered());
            values.put(KEY_IS_CONTROLLED, prescription_common_model.getIs_controlled());
            values.put(KEY_SUPPRESS_NON_BISTER_DISPLAY, prescription_common_model.getSuppress_non_blister_display());
            values.put(KEY_CAN_CARRY_FORWARD, prescription_common_model.getCan_carry_forward());
            values.put(KEY_FRONT_IMAGE_URL, prescription_common_model.getFront_image_url());
            values.put(KEY_DISPENSED_QUANTITY, prescription_common_model.getDispensed_quantity());
            values.put(KEY_AVAILABLE_QUANTITY, prescription_common_model.getAvailable_quantity());
            values.put(KEY_CHECKED_IN_QUANTITY, prescription_common_model.getChecked_in_quantity());

            values.put(KEY_BROUGHT_FORWARD_QUANTITY, prescription_common_model.getBrought_forward_quantity());
            values.put(KEY_RETURNED_QUANTITY, prescription_common_model.getReturned_quantity());
            values.put(KEY_DESTROYED_QUANTITY, prescription_common_model.getDestroyed_quantity());
            values.put(KEY_CARRIED_FORWORD_QUANTITY, prescription_common_model.getCarried_forward_quantity());
            values.put(KEY_WASTED_QUANTITY, prescription_common_model.getWasted_quantity());
            values.put(KEY_ADMINISTERED_QUANTITY, prescription_common_model.getAdministered_quantity());
            values.put(KEY_AUDITED_QUANTITY, prescription_common_model.getAudited_quantity());
            values.put(KEY_IRREGULAR_GRID_ARRAY, prescription_common_model.getIrregular_grid_array());
            values.put(KEY_LAST_ADMIN, prescription_common_model.getLast_admin());
            values.put(KEY_SPECIAL_MEDICATION_NAME, prescription_common_model.getSpecial_medication_name());
            values.put(KEY_IS_SPECIAL, prescription_common_model.getIs_special());
//            values.put(KEY_QUANTITY_DISPENSED, prescription_common_model.getQuantity_dispensed());
//            values.put(KEY_STATUS, prescription_common_model.getStatus());
//            values.put(KEY_DETAIL_DRUG_NAME, prescription_common_model.getDetailed_drug_name());
//            values.put(KEY_SHORT_DRUG_NAME, prescription_common_model.getShort_drug_name());

            values.put(KEY_PRESCRIBED_TIME_SLOT_ARRAY, prescription_common_model.getPrescribed_time_slots_array());

//            values.put(KEY_FIRST_CHECKIN_DATE, prescription_common_model.getFirst_check_in_date());
//            values.put(KEY_PATIENT_ABSENT_TODAY, prescription_common_model.getPatient_absent_today());
//            values.put(KEY_PIL_URL, prescription_common_model.getPil_url());
//            values.put(KEY_MEDICATION, prescription_common_model.getMedication_object().toString());
//            values.put(KEY_BRANDED_MEDICATION, prescription_common_model.getBrnd_medi_object().toString());
//            values.put(KEY_TIME_SLOT_DOSES, prescription_common_model.getTIME_SLOT_DOSES_array());
            values.put(KEY_CHECKED_IN_QUANTITY_TOUPDATE, prescription_common_model.getNew_checked_in_quantity_toupdate());
            values.put(KEY_IS_CHECKED_IN_SELECTED, prescription_common_model.isSelected());
            values.put(KEY_CHECKED_IN_CYCLE_TYPE, prescription_common_model.getCheckin_cycle_type());
            values.put(KEY_PATIENT_ID, prescription_common_model.getPatient_id());
            values.put(KEY_IS_FOR_ADMIN, prescription_common_model.getPatient_id());

            long inserted_id = db.insertWithOnConflict(TABLE_PRESCRIPTION, null, values, 5);

            Log.e("addAllFeed", "inserted_id--------->" + inserted_id);
        }
        // db.setTransactionSuccessful();
//        deleteOldFeeds(context);
    }


    // Updating single contact
    public int update_Return_Prescription(String preid, String patientId, String s1, String stocktoupdate, String qnty_parameter_name) {
        Log.e("method", "update_Return_Prescription method------>" + patientId);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        if (qnty_parameter_name.equals("return")) {
            values.put(KEY_RETURNED_QUANTITY, s1);

        } else if (qnty_parameter_name.equals("carry_forward")) {
            values.put(KEY_CARRIED_FORWORD_QUANTITY, s1);

        } else if (qnty_parameter_name.equals("destroy")) {
            values.put(KEY_DESTROYED_QUANTITY, s1);

        }

        values.put(KEY_AVAILABLE_QUANTITY, stocktoupdate);

        // updating row
        int updateval = db.update(TABLE_PRESCRIPTION, values, KEY_PATIENT_ID + " = ?  AND " + KEY_PRESCRIPTION_ID + " = ? ", new String[]{patientId, preid});

        Log.e("method", "update_measure method----update_measure--> " + updateval);
        return updateval;
    }


    // Getting All Contacts
    public ArrayList<PRESCRIPTION_Model_Phase2> getallFeedsFromDB(String patientId, String cycle_type) {
        ArrayList<PRESCRIPTION_Model_Phase2> prescription_common_modelArrayList = new ArrayList<PRESCRIPTION_Model_Phase2>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRESCRIPTION + " WHERE " + KEY_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + KEY_CHECKED_IN_CYCLE_TYPE + " = " + "'" + cycle_type + "'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("getallFeedsFromDB", "1----------->" + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PRESCRIPTION_Model_Phase2 contact = new PRESCRIPTION_Model_Phase2(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                        cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getString(14), Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                        Integer.parseInt(cursor.getString(18)), Integer.parseInt(cursor.getString(19)), Integer.parseInt(cursor.getString(20)), Integer.parseInt(cursor.getString(21)),
                        Integer.parseInt(cursor.getString(22)), Integer.parseInt(cursor.getString(23)), Integer.parseInt(cursor.getString(24)), cursor.getString(25),
                        cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                        Integer.parseInt(cursor.getString(31)), cursor.getString(32), cursor.getString(33), cursor.getString(34), cursor.getString(35));
                // Adding contact to list
                prescription_common_modelArrayList.add(contact);

            } while (cursor.moveToNext());
        }

        // return contact list
        return prescription_common_modelArrayList;
    }


    // Getting All Contacts
    public ArrayList<PRESCRIPTION_Model_Phase2> get_all_checkins_FromDB(String patientId, String cycle_type) {
        ArrayList<PRESCRIPTION_Model_Phase2> prescription_common_modelArrayList = new ArrayList<PRESCRIPTION_Model_Phase2>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRESCRIPTION + " WHERE " + KEY_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + KEY_CHECKED_IN_CYCLE_TYPE + " = " + "'" + cycle_type + "'";
        Log.e("getallFeedsFromDB", "1----------->" + selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("getallFeedsFromDB", "2------>" + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.e("dispensed", "---->" + Integer.parseInt(cursor.getString(15)));
                Log.e("checkedin", "---->" + Integer.parseInt(cursor.getString(17)));

                int remain = (Integer.parseInt(cursor.getString(15)) - Integer.parseInt(cursor.getString(17)));
                Log.e("dispensed-checkedin", "----->" + remain);

                if ((remain != 0)) {
                    Log.e("$$$$$$$$$", "$$$$$$$$----->" + remain);
                    PRESCRIPTION_Model_Phase2 contact = new PRESCRIPTION_Model_Phase2(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                            cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                            cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                            cursor.getString(14), Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                            Integer.parseInt(cursor.getString(18)), Integer.parseInt(cursor.getString(19)), Integer.parseInt(cursor.getString(20)), Integer.parseInt(cursor.getString(21)),
                            Integer.parseInt(cursor.getString(22)), Integer.parseInt(cursor.getString(23)), Integer.parseInt(cursor.getString(24)), cursor.getString(25),
                            cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                            Integer.parseInt(cursor.getString(31)), cursor.getString(32), cursor.getString(33), cursor.getString(34), cursor.getString(35));
                    // Adding contact to list
                    prescription_common_modelArrayList.add(contact);
                }
            } while (cursor.moveToNext());
        }
        Log.e("$$$$$$$$$", ">>>>>>>>>----->" + prescription_common_modelArrayList.size());
        // return contact list
        return prescription_common_modelArrayList;
    }


    // Getting All Contacts
    public ArrayList<PRESCRIPTION_Model_Phase2> get_admin_tab_prescriptions_FromDB(String patientId, String cycle_type) {
        ArrayList<PRESCRIPTION_Model_Phase2> prescription_common_modelArrayList = new ArrayList<PRESCRIPTION_Model_Phase2>();

        String val = "0";
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRESCRIPTION + " WHERE " + KEY_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + KEY_CHECKED_IN_CYCLE_TYPE + " = " + "'" + cycle_type + "'";
//               + " AND " + KEY_ADMINISTERED_QUANTITY + " != " + "'" + val + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("getallFeedsFromDB", "get_admin_tab_prescriptions_FromDB---->" + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

//                if (!cursor.getString(6).equals("Irregular")) {
                PRESCRIPTION_Model_Phase2 contact = new PRESCRIPTION_Model_Phase2(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                        cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getString(14), Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                        Integer.parseInt(cursor.getString(18)), Integer.parseInt(cursor.getString(19)), Integer.parseInt(cursor.getString(20)), Integer.parseInt(cursor.getString(21)),
                        Integer.parseInt(cursor.getString(22)), Integer.parseInt(cursor.getString(23)), Integer.parseInt(cursor.getString(24)), cursor.getString(25),
                        cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                        Integer.parseInt(cursor.getString(31)), cursor.getString(32), cursor.getString(33), cursor.getString(34), cursor.getString(35));

                Log.e("get_admin_tab_prescriptions_FromDB", "get_admin_tab_prescriptions_FromDB String(0)---->" + cursor.getString(0));

                ArrayList<Today_Mars_Mar_Bean_Phase2> Today_Mars_Mar_objects_list = get_admin_todaymar_fromdb(cursor.getString(0), patientId);

//                Log.e("getallFeedsFromDB", "get_admin_tab_prescriptions_FromDB Prescription_id---->" + cursor.getString(0));
//                Log.e("getallFeedsFromDB", "get_admin_tab_prescriptions_FromDB patientId---->" + patientId);
//                Log.e("getallFeedsFromDB", "get_admin_tab_prescriptions_FromDB  Today_Mars_Mar_objects_list---->" + Today_Mars_Mar_objects_list.size());
                contact.setToday_Mars_Mar_objects_list(Today_Mars_Mar_objects_list);
                // Adding contact to list
                prescription_common_modelArrayList.add(contact);
//                }

            } while (cursor.moveToNext());
        }

        // return contact list
        return prescription_common_modelArrayList;
    }


    /*********************************************************************************************************/

    // Getting All Contacts
    public ArrayList<PRESCRIPTION_Model_Phase2> getall_ReturnsFromDB(String patientId) {
        Log.e("getall_ReturnsFromDB", "patientId------>" + patientId);
        String type = "last_cycle";
        ArrayList<PRESCRIPTION_Model_Phase2> prescription_common_modelArrayList = new ArrayList<PRESCRIPTION_Model_Phase2>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRESCRIPTION + " WHERE " + KEY_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + KEY_CHECKED_IN_CYCLE_TYPE + " = " + "'" + type + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("getall_ReturnsFromDB", "" + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PRESCRIPTION_Model_Phase2 contact = new PRESCRIPTION_Model_Phase2(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                        cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getString(14), Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                        Integer.parseInt(cursor.getString(18)), Integer.parseInt(cursor.getString(19)), Integer.parseInt(cursor.getString(20)), Integer.parseInt(cursor.getString(21)),
                        Integer.parseInt(cursor.getString(22)), Integer.parseInt(cursor.getString(23)), Integer.parseInt(cursor.getString(24)), cursor.getString(25),
                        cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                        Integer.parseInt(cursor.getString(31)), cursor.getString(32), cursor.getString(33), cursor.getString(34), cursor.getString(35));
                // Adding contact to list
                if (Integer.parseInt(cursor.getString(16)) > 0) {
                    prescription_common_modelArrayList.add(contact);
                }
            } while (cursor.moveToNext());
        }

        // return contact list
        return prescription_common_modelArrayList;
    }


    /*******************************************************************************************/


    // Deleting single contact
    public void delete_general_notes(String patient_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(NOTES_TABLE, NOTES_PATIENT_ID + " = ?  AND " + NOTE_TYPE + " = ? ", new String[]{
                patient_id, "General"});

        Log.e("delete_general_notes", "delete_general_notes delete--------->" + delete);
//        db.close();
    }

    public boolean insertNotesData(ArrayList<NotesModel_Phase2> notesarraylist, String operation_type, String nottype) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        if (operation_type.equals("online") && nottype.equals("General")) {

            for (int i = 0; i < notesarraylist.size(); i++) {

                delete_general_notes(notesarraylist.get(i).getPatientid());
            }
        }


        for (int i = (notesarraylist.size() - 1); i >= 0; i--) {
            Log.e(" NOTES_TABLE for >>>> ", "----> " + notesarraylist.get(i).getSubject());
            ContentValues values = new ContentValues();
            values.put(NID, notesarraylist.get(i).getId());
            values.put(NOTE_SUBJECT, notesarraylist.get(i).getSubject());
            values.put(CONTENT, notesarraylist.get(i).getContent());
            values.put(NOTE_TYPE, notesarraylist.get(i).getNote_type());
            values.put(NOTE_UPDATED, notesarraylist.get(i).getUpdated_at());
            values.put(NOTE_CREATED, notesarraylist.get(i).getCreated_at());
            values.put(USER_ID, notesarraylist.get(i).getUser_id());
            values.put(USERNAME, notesarraylist.get(i).getUsername());
            values.put(FULL_NAME, notesarraylist.get(i).getFullname());
            values.put(EMAIL, notesarraylist.get(i).getEmail());
            values.put(NOTES_PATIENT_ID, notesarraylist.get(i).getPatientid());

//            values.put(NOTE_DELETED, notesarraylist.get(i).getDeleted_at());
//            values.put(MAR_ID, notesarraylist.get(i).getMar_Id());
//            values.put(NOTE_DATE, notesarraylist.get(i).getNote_date());
//            values.put(PRESCRIPTION_ID, notesarraylist.get(i).getPrescription_id());
//            values.put(CAN_MANAGE_USERS, notesarraylist.get(i).getCan_manage_users());
//            values.put(CARE_HOME_ID, notesarraylist.get(i).getCare_home_id());
//            values.put(IS_ACTIVE, notesarraylist.get(i).getIs_active());

            // Inserting Row
            long l = db.insertWithOnConflict(NOTES_TABLE, null, values, 5);
            Log.e("Inserted NOTES_TABLE id >>>> ", l + "");
        }

        return true;

    }

    public ArrayList<NotesModel_Phase2> getGeneral_Notesfromdb(String patientId) {
        ArrayList<NotesModel_Phase2> notesarraylist = new ArrayList<NotesModel_Phase2>();

        String notestype = "General";
        String selectQuery = "SELECT * FROM " + NOTES_TABLE + " WHERE " + NOTES_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + NOTE_TYPE + " = " + "'" + notestype + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToLast()) {
            do {
                Log.e("getGeneral_Notesfromdb  ", "dbid----->" + cursor.getString(0));
                Log.e("getGeneral_Notesfromdb  ", "subject----->" + cursor.getString(10));

//                NotesModel notesModel = new NotesModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
//                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18));

                NotesModel_Phase2 notesModel = new NotesModel_Phase2(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));

                notesarraylist.add(notesModel);

            } while (cursor.moveToPrevious());
        }
        return notesarraylist;
    }


    public ArrayList<NotesModel_Phase2> getBS_Site_Notesfromdb(String patientId) {
        ArrayList<NotesModel_Phase2> notesarraylist = new ArrayList<NotesModel_Phase2>();

        String notestype = "BS_Site";
        String selectQuery = "SELECT * FROM " + NOTES_TABLE + " WHERE " + NOTES_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + NOTE_TYPE + " = " + "'" + notestype + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

//                NotesModel notesModel = new NotesModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
//                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18));

                NotesModel_Phase2 nm2 = new NotesModel_Phase2(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));

                notesarraylist.add(nm2);

            } while (cursor.moveToNext());
        }
        return notesarraylist;
    }


    /*******************************************************************************************/


    // Deleting single contact
    public void delete_measure_for_patient(String patient_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(MEASURE_TABLE, MEASURE_PATIENT_ID + " = ? ", new String[]{patient_id});

        Log.e("delete_general_notes", "delete_general_notes delete--------->" + delete);
//        db.close();
    }


    public void insertMeasureData(ArrayList<MeasureModel_Phase2> MeasureList, String operation_type) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();


        if (operation_type.equals("online")) {

            for (int i = 0; i < MeasureList.size(); i++) {

                delete_measure_for_patient(MeasureList.get(i).getPatient_id());
            }
        }


        for (int i = 0; i < MeasureList.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(MID, MeasureList.get(i).getId());
            values.put(MEASURE_VALUE, MeasureList.get(i).getValue());
            values.put(MEASURE_UPDATED, MeasureList.get(i).getUpdated_at());
            values.put(MEASURENT_TYPE_ID, MeasureList.get(i).getMeasurement_type_id());
            values.put(INSIDE_MEASURE_NAME, MeasureList.get(i).getName());
            values.put(INSIDE_MEASURE_UNITS, MeasureList.get(i).getUnits());
            values.put(INSIDE_MEASURE_UPDATED_AT, MeasureList.get(i).getUpdated_at_second());
            values.put(INSIDE_MEASURE_CREATED_AT, MeasureList.get(i).getCreated_at_second());
            values.put(IS_MEASURE_EDITED, MeasureList.get(i).isedited());
            values.put(MEASURE_PATIENT_ID, MeasureList.get(i).getPatient_id());

//            values.put(MEASURE_CREATED, MeasureList.get(i).getCreated_at());
//            values.put(MEASURE_DELETED, MeasureList.get(i).getDeleted_at());
//            values.put(INSIDE_MEASURE_DELETED_AT, MeasureList.get(i).getDeleted_at_second());
//            values.put(INSIDE_MEASURE_ID, MeasureList.get(i).getId_second());


            // Inserting Row
            long l = db.insertWithOnConflict(MEASURE_TABLE, null, values, 5);
            Log.e("Inserted MEASURE_TABLE id >>>> ", l + "");
        }

    }


    // Updating single contact
    public int update_measure(String new_val, String p_id, String measurement_name) {
        Log.e("method", "update_measure method------>" + p_id);
        Log.e("method", "update_measure method---measurement_name--->" + measurement_name);
        String dtm = Date_utility.get_adminstarated_datetime_ukzone();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEASURE_VALUE, new_val);
//        values.put(MEASURE_CREATED, dtm);
        values.put(MEASURE_UPDATED, dtm);

//        int delete = db.delete(NOTES_TABLE, PATIENT_ID + " = ?  AND " + NOTE_TYPE + " = ? ", new String[]{
//                patient_id, "General"});

        // updating row
        int updateval = db.update(MEASURE_TABLE, values, MEASURE_PATIENT_ID + " = ?  AND " + INSIDE_MEASURE_NAME + " = ? ", new String[]{p_id, measurement_name});

        Log.e("method", "update_measure method----update_measure--> " + updateval);
        return updateval;
    }


    // Updating single contact
    public int update_BSSITE(String new_val, String p_id) {

        Log.e("method", "update_Inr_Reading method------>" + p_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTENT, new_val);
        // updating row
        return db.update(NOTES_TABLE, values, NOTES_PATIENT_ID + " = ?  AND " + NOTE_TYPE + " = ? ", new String[]{p_id, "BS_Site"});
    }


    public ArrayList<MeasureModel_Phase2> getMeasureListfromdb(String patientId) {
        ArrayList<MeasureModel_Phase2> MeasureModelarraylist = new ArrayList<MeasureModel_Phase2>();

        String selectQuery = "SELECT * FROM " + MEASURE_TABLE + " WHERE " + MEASURE_PATIENT_ID + " = " + "'" + patientId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

//                MeasureModel measureModel1 = new MeasureModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
//                        cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14));

                MeasureModel_Phase2 mmp2 = new MeasureModel_Phase2(cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));

                MeasureModelarraylist.add(mmp2);

            } while (cursor.moveToNext());
        }
        return MeasureModelarraylist;
    }


    /*******************************************************************************************/

    public void insert_homelyData(ArrayList<Homely_Model_Phase2> homely_List) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < homely_List.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(HOMELY_ID, homely_List.get(i).getId());
//            values.put(HOMELY_CARE_HOME_ID, homely_List.get(i).getCare_home_id());
//            values.put(HOMELY_CREATED, homely_List.get(i).getCreated_at());
//            values.put(HOMELY_DELETED, homely_List.get(i).getDeleted_at());
            values.put(HOMELY_MANDATORY_WARNINGS, homely_List.get(i).getMandatory_warnings());
            values.put(HOMELY_NAME, homely_List.get(i).getName());
            values.put(HOMELY_UPDATED, homely_List.get(i).getUpdated_at());
//            values.put(HOMELY_TODAYSMARS_LIST,homely_List.get(i).getHomely_Today_Mars_Mar_objects_list());
            values.put(HOMELY_PATIENT_ID, homely_List.get(i).getPatient_id());
            // Inserting Row
            long inserted_id = db.insertWithOnConflict(HOMELY_TABLE, null, values, 5);
            Log.e("HOMELY_TABLE", "loop--> " + i + " HOMELY_TABLE inserted_id--------->" + inserted_id);
        }

    }


    public ArrayList<Homely_Model_Phase2> gethomelyListfromdb(String patientId) {
        ArrayList<Homely_Model_Phase2> new_homely_remedy_modelArrayList = new ArrayList<Homely_Model_Phase2>();

        String selectQuery = "SELECT * FROM " + HOMELY_TABLE + " WHERE " + HOMELY_PATIENT_ID + " = " + "'" + patientId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Log.e("HOMELY_TABLE", " gethomelyListfromdb cursor.getString(0)--------->" + cursor.getString(0));
                Log.e("HOMELY_TABLE", " gethomelyListfromdb patientId--------->" + patientId);
                ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> tmlist = new ArrayList<>();
                tmlist = get_homelytodaymar_fromdb(cursor.getString(0), patientId);
                Log.e("HOMELY_TABLE", " tmlist size--------->" + tmlist);

//                New_Homely_Remedy_Model nhr_model = new New_Homely_Remedy_Model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), get_homelytodaymar_fromdb(cursor.getString(0), patientId), false, "" + 0, patientId);
                Homely_Model_Phase2 nhm = new Homely_Model_Phase2(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), tmlist, false, "" + 0, patientId);

                new_homely_remedy_modelArrayList.add(nhm);

            } while (cursor.moveToNext());
        }
        return new_homely_remedy_modelArrayList;
    }


    public ArrayList<Remedy_Dose_Bean> get_remedy_and_doselist_fromdb(String patientId) {
        ArrayList<Remedy_Dose_Bean> remedy_and_doselist = new ArrayList<Remedy_Dose_Bean>();

        String selectQuery = "SELECT * FROM " + HOMELY_TABLE + " WHERE " + HOMELY_PATIENT_ID + " = " + "'" + patientId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> todaymar1 = get_homelytodaymar_fromdb(cursor.getString(0), patientId);

                if (todaymar1.size() > 0) {
//                    New_Homely_Remedy_Model nhr_model = new New_Homely_Remedy_Model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),todaymar1 , false, "" + 0, patientId);
//                    new_homely_remedy_modelArrayList.add(nhr_model);

                    String ddoss = "";

                    for (int j = 0; j < todaymar1.size(); j++) {

                        Homely_Today_Mars_Mar_Bean_Phase2 h1 = todaymar1.get(j);

                        if (ddoss.isEmpty()) {

                            String a1 = "";
                            if (h1.getDose().isEmpty()) {
                                a1 = "REASON: " + h1.getFalse_reason() + " ";

                            } else {
                                a1 = "DOSE: " + h1.getDose() + " ";
                            }

                            ddoss = "" + a1 + "DATE: " +
                                    Date_utility.getDate(h1.getAdministered_at()) + " " + "TIME: " +
                                    Date_utility.getTime(h1.getAdministered_at()) + " " + "USER: " + h1.getFullname();
                        } else {

                            String a1 = "";
                            if (h1.getDose().isEmpty()) {
                                a1 = "REASON: " + h1.getFalse_reason() + " ";
                            } else {
                                a1 = "DOSE: " + h1.getDose() + " ";
                            }

                            ddoss = ddoss + "\n" + a1 + "DATE: " +
                                    Date_utility.getDate(h1.getAdministered_at()) + " " + "TIME: " +
                                    Date_utility.getTime(h1.getAdministered_at()) + " " + "USER: " + h1.getFullname();
                        }

                    }

                    if (!ddoss.isEmpty()) {
                        Log.e("remedy name", "remedy name----->" + cursor.getString(2));
                        Remedy_Dose_Bean r1 = new Remedy_Dose_Bean(cursor.getString(2), ddoss);
                        remedy_and_doselist.add(r1);
                    }
                }

            } while (cursor.moveToNext());
        }
        return remedy_and_doselist;
    }


    /*******************************************************************************************/

    public void insert_today_marsData(ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> homely_today_mars_mar_bean_list, String status) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("insert_today_marsData", "insert_today_marsData --------->" + homely_today_mars_mar_bean_list.size());

        if (status.equals("online")) {

            for (int i = 0; i < homely_today_mars_mar_bean_list.size(); i++) {
                delete_todaymar_record(homely_today_mars_mar_bean_list.get(i).getId());
            }
        }

        for (int i = 0; i < homely_today_mars_mar_bean_list.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(TODAY_MARS_ID, homely_today_mars_mar_bean_list.get(i).getId());
            values.put(TODAY_MARS_ADMINISTRATED_AT, homely_today_mars_mar_bean_list.get(i).getAdministered_at());
            values.put(TODAY_MARS_DOSE, homely_today_mars_mar_bean_list.get(i).getDose());
            values.put(TODAY_MARS_FALSE_REASON, homely_today_mars_mar_bean_list.get(i).getFalse_reason());
            values.put(TODAY_MARS_IS_WASTE, homely_today_mars_mar_bean_list.get(i).getIs_waste());
            values.put(TODAY_MARS_OUTCOME, homely_today_mars_mar_bean_list.get(i).getOutcome());
            values.put(TODAY_MARS_QUANTITY_WASTED, homely_today_mars_mar_bean_list.get(i).getQuantity_wasted());
            values.put(TODAY_MARS_SLOT_TIME, homely_today_mars_mar_bean_list.get(i).getSlot_time());
            values.put(TODAY_MARS_UPDATED_AT, homely_today_mars_mar_bean_list.get(i).getUpdated_at());
            values.put(TODAY_MARS_USERID, homely_today_mars_mar_bean_list.get(i).getUserid());
            values.put(TODAY_MARS_USERNAME, homely_today_mars_mar_bean_list.get(i).getUsername());
            values.put(TODAY_MARS_USERFULLNAME, homely_today_mars_mar_bean_list.get(i).getFullname());
            values.put(TODAY_MARS_USEREMAIL, homely_today_mars_mar_bean_list.get(i).getEmail());
            values.put(TODAY_MARS_HOMELY_REMEDY_ID, homely_today_mars_mar_bean_list.get(i).getHomely_id());
            values.put(TODAY_MARS_HOMELY_REMEDY_NAME, homely_today_mars_mar_bean_list.get(i).getHomely_name());
            values.put(TODAY_MARS_PRESCRIPTION_ID, homely_today_mars_mar_bean_list.get(i).getPrescription_id());
            values.put(TODAY_MARS_PATIENT_ID, homely_today_mars_mar_bean_list.get(i).getPatientid());

//            int count = check_for_TODAY_MARS_ID(TODAY_MARS_ID);
//            Log.e("count", "count --------->" + count);
//            if (count <= 0) {
            // Inserting Row
            long inserted_id = db.insert(TODAY_MARS_TABLE, null, values);

            Log.e("TODAY_MARS_TABLE", "TODAY_MARS_TABLE inserted_id--------->" + inserted_id);

//            } else {
//
//                Log.e("TODAY_MARS_TABLE", "cant insert duplicate TODAY_MARS_TABLE --------->");
//
//            }
        }

    }


    /*******************************************************************************************/

//    public int check_for_TODAY_MARS_ID(String pid) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
////        String query = "select count(*) from today_mars_table where today_mar_id = ?";
////        Cursor cursor = db.rawQuery(query, new String[]{pid});
//        Cursor cursor = db.query(TODAY_MARS_TABLE,
//                new String[]{TODAY_MARS_ID},
//                TODAY_MARS_ID + "=?", new String[]{String.valueOf(pid)}, null, null, null, null);
//
//        Log.e("check_for_TODAY_MARS_ID", "check_for_TODAY_MARS_ID---------->" + cursor.getCount());
//
//        return cursor.getCount();
//
//    }


    // Deleting single contact
    public void delete_todaymar_record(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TODAY_MARS_TABLE, TODAY_MARS_ID + " = ?", new String[]{
                String.valueOf(id)});
//        db.close();
    }


    // Deleting single contact
    public void delete_todaymar_for_homely(String id, String patient_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TODAY_MARS_TABLE, TODAY_MARS_HOMELY_REMEDY_ID + " = ?  AND " + TODAY_MARS_PATIENT_ID + "=? ", new String[]{
                id, patient_id});

        Log.e("delete_todaymar_for_homely", "delete_todaymar_for_homely delete--------->" + delete);
//        db.close();
    }


    public void insert_today_marsData_admin(ArrayList<Today_Mars_Mar_Bean_Phase2> today_mars_mar_bean_list, String status) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        if (status.equals("online")) {

            for (int i = 0; i < today_mars_mar_bean_list.size(); i++) {

                delete_todaymar_record(today_mars_mar_bean_list.get(i).getId());
            }
        }


        for (int i = 0; i < today_mars_mar_bean_list.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(TODAY_MARS_ID, today_mars_mar_bean_list.get(i).getId());
            values.put(TODAY_MARS_ADMINISTRATED_AT, today_mars_mar_bean_list.get(i).getAdministered_at());
            values.put(TODAY_MARS_DOSE, today_mars_mar_bean_list.get(i).getDose());
            values.put(TODAY_MARS_FALSE_REASON, today_mars_mar_bean_list.get(i).getFalse_reason());
            values.put(TODAY_MARS_IS_WASTE, today_mars_mar_bean_list.get(i).getIs_waste());
            values.put(TODAY_MARS_OUTCOME, today_mars_mar_bean_list.get(i).getOutcome());
            values.put(TODAY_MARS_QUANTITY_WASTED, today_mars_mar_bean_list.get(i).getQuantity_wasted());
            values.put(TODAY_MARS_SLOT_TIME, today_mars_mar_bean_list.get(i).getSlot_time());
            values.put(TODAY_MARS_UPDATED_AT, today_mars_mar_bean_list.get(i).getUpdated_at());
            values.put(TODAY_MARS_USERID, today_mars_mar_bean_list.get(i).getUserid());
            values.put(TODAY_MARS_USERNAME, today_mars_mar_bean_list.get(i).getUsername());
            values.put(TODAY_MARS_USERFULLNAME, today_mars_mar_bean_list.get(i).getFullname());
            values.put(TODAY_MARS_USEREMAIL, today_mars_mar_bean_list.get(i).getEmail());
            values.put(TODAY_MARS_HOMELY_REMEDY_ID, today_mars_mar_bean_list.get(i).getHomely_id());
            values.put(TODAY_MARS_HOMELY_REMEDY_NAME, today_mars_mar_bean_list.get(i).getHomely_name());
            values.put(TODAY_MARS_PRESCRIPTION_ID, today_mars_mar_bean_list.get(i).getPrescription_id());
            values.put(TODAY_MARS_PATIENT_ID, today_mars_mar_bean_list.get(i).getPatientid());

            values.put(TODAY_MARS_SECONDRY_USERID, today_mars_mar_bean_list.get(i).getSecondry_userid());
            values.put(TODAY_MARS_SECONDRY_USERNAME, today_mars_mar_bean_list.get(i).getSecondry_username());
            values.put(TODAY_MARS_SECONDRY_USERFULLNAME, today_mars_mar_bean_list.get(i).getSecondry_fullname());
            values.put(TODAY_MARS_SECONDRY_USEREMAIL, today_mars_mar_bean_list.get(i).getSecondry_email());


//            values.put(TODAY_MARS_MEDICATION_ID, homely_today_mars_mar_bean_list.get(i).getMedication_id());
//            values.put(TODAY_MARS_SECONDRY_USER_ID, homely_today_mars_mar_bean_list.get(i).getSecondary_user_id());
//            values.put(TODAY_MARS_SECONDRY_USER_FULLNAME, homely_today_mars_mar_bean_list.get(i).getSeconday_user_fullname());
//            values.put(TODAY_MARS_UID, homely_today_mars_mar_bean_list.get(i).getUid());
//            int count = check_for_TODAY_MARS_ID(TODAY_MARS_ID);
//            Log.e("count", "count --------->" + count);
//            if (count <= 0) {
            // Inserting Row

            long inserted_id = db.insert(TODAY_MARS_TABLE, null, values);
//            Log.e("TODAY_MARS_TABLE Values >>>> ", values + "");
            Log.e("TODAY_MARS_TABLE", "TODAY_MARS_TABLE inserted_id--------->" + inserted_id);

//        } else {
//
//                Log.e("TODAY_MARS_TABLE", "cant insert duplicate TODAY_MARS_TABLE --------->");
//
//            }
//
        }

    }


    public ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> get_homelytodaymar_fromdb(String homelyid, String patientId) {
        ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> homely_Today_Mars_Mar_objects_list = new ArrayList<Homely_Today_Mars_Mar_Bean_Phase2>();

        String selectQuery = "SELECT * FROM " + TODAY_MARS_TABLE + " WHERE " + TODAY_MARS_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + TODAY_MARS_HOMELY_REMEDY_ID + " = " + "'" + homelyid + "'";

        Log.e("TODAY_MARS_TABLE", "TODAY_MARS_TABLE homelyid--------->" + homelyid);

//        String selectQuery = "SELECT * FROM " + TODAY_MARS_TABLE + " WHERE " +TODAY_MARS_HOMELY_REMEDY_ID + " = " + "'" + homelyid + "'";
//        String selectQuery = "SELECT * FROM " + TODAY_MARS_TABLE ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

//                Homely_Today_Mars_Mar_Bean hm = new Homely_Today_Mars_Mar_Bean(created_at_mar, deleted_at_mar, dose, false_reason, gps_location, homely_remedy_id, homely_remedy_name, id_mar, is_waste, medication_id, outcome, patient_id, prescription_id, quantity_wasted, secondary_user_id, seconday_user_fullname, slot_time, uid, updated_at_mar, user_fullname, user_id);
//                homely_Today_Mars_Mar_objects_list.add(hm);

                Homely_Today_Mars_Mar_Bean_Phase2 hm = new Homely_Today_Mars_Mar_Bean_Phase2(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17));

                Log.e("TODAY_MARS_TABLE", "TODAY_MARS_TABLE  cursor.getString(14)--------->" + cursor.getString(14));

                homely_Today_Mars_Mar_objects_list.add(hm);
                Log.e("get_homelytodaymar_fromdb", "homely_Today_Mars_Mar_objects_list:---------->" + homely_Today_Mars_Mar_objects_list.size());
            } while (cursor.moveToNext());
        }
        return homely_Today_Mars_Mar_objects_list;
    }


    public ArrayList<Today_Mars_Mar_Bean_Phase2> get_admin_todaymar_fromdb(String prescription_id, String patientId) {
        ArrayList<Today_Mars_Mar_Bean_Phase2> Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean_Phase2>();

        String selectQuery = "SELECT * FROM " + TODAY_MARS_TABLE + " WHERE " + TODAY_MARS_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + TODAY_MARS_PRESCRIPTION_ID + " = " + "'" + prescription_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

//                if (Date_utility.getCurrentdate().equals(Date_utility.getDate(cursor.getString(2)))) {

                Today_Mars_Mar_Bean_Phase2 hm = new Today_Mars_Mar_Bean_Phase2(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21));

                Today_Mars_Mar_objects_list.add(hm);
//                }

            } while (cursor.moveToNext());
        }

        Log.e("prescription_id", "a1---->" + prescription_id);
        Log.e("patientId", "a2---->" + patientId);
        Log.e("Today_Mars_Mar_objects_list", "a3---->" + Today_Mars_Mar_objects_list.size());

        return Today_Mars_Mar_objects_list;
    }


    public boolean check_admin_todaymar_fromdb(String prescription_id, String patientId, String slot_time) {
//        ArrayList<Today_Mars_Mar_Bean> Today_Mars_Mar_objects_list = new ArrayList<Today_Mars_Mar_Bean>();

        String selectQuery = "SELECT * FROM " + TODAY_MARS_TABLE + " WHERE " + TODAY_MARS_PATIENT_ID + " = " + "'" + patientId + "'" + " AND " + TODAY_MARS_PRESCRIPTION_ID + " = " + "'" + prescription_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (Date_utility.getCurrentdate().equals(Date_utility.getDate(cursor.getString(9)))) {

                    Log.e("check_admin_todaymar_fromdb", "check_admin_todaymar_fromdb  --------->" + cursor.getString(9));
                    Log.e("check_admin_todaymar_fromdb", "check_admin_todaymar_fromdb  --------->" + cursor.getString(17));
                    Log.e("check_admin_todaymar_fromdb", "check_admin_todaymar_fromdb  --------->" + slot_time);
                    if (cursor.getString(8).equals(slot_time)) {
                        return true;

                    }

                }

            } while (cursor.moveToNext());
        }
        return false;
    }

    /*******************************************************************************************/

    public void insert_about_patients_inr_detail_table(ABOUT_PATIENT_INR abt_pt_inr) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
//        for (int i = 0; i < new_patient_Model_List.size(); i++) {

        ContentValues values = new ContentValues();
        values.put(ABOUT_P_ID, abt_pt_inr.getId());
        values.put(ABOUT_PATIENT_ADDRESS, abt_pt_inr.getAddress());
        values.put(ABOUT_PATIENT_ALLERGIES, abt_pt_inr.getAllergies());
        values.put(ABOUT_PATIENT_CARE_HOME_ID, abt_pt_inr.getCare_home_id());
        values.put(ABOUT_PATIENT_CREATED_AT, abt_pt_inr.getCreated_at());
        values.put(ABOUT_PATIENT_CYCLE_BASE_DATE, abt_pt_inr.getCycle_base_date());
        values.put(ABOUT_PATIENT_CYCLE_DURATION, abt_pt_inr.getCycle_duration());
        values.put(ABOUT_PATIENT_DELETED_AT, abt_pt_inr.getDeleted_at());
        values.put(ABOUT_PATIENT_DOB, abt_pt_inr.getDob());
        values.put(ABOUT_PATIENT_FIRSTNAME, abt_pt_inr.getFirst_name());
        values.put(ABOUT_PATIENT_GP_ID, abt_pt_inr.getGp_id());
        values.put(ABOUT_PATIENT_GP_NAME, abt_pt_inr.getGp_name());
        values.put(ABOUT_PATIENT_INACTIVE, abt_pt_inr.getInactive());
        values.put(ABOUT_PATIENT_NAME, abt_pt_inr.getName());
        values.put(ABOUT_PATIENT_NHS_NUMBER, abt_pt_inr.getNhs_number());
        values.put(ABOUT_PATIENT_NOTES, abt_pt_inr.getPatient_notes());
        values.put(ABOUT_PATIENT_PHOTO_IMAGE, abt_pt_inr.getPhoto_image());
        values.put(ABOUT_PATIENT_ROOM, abt_pt_inr.getRoom());
        values.put(ABOUT_PATIENT_TITLE, abt_pt_inr.getTitle());
        values.put(ABOUT_PATIENT_UPDATED_AT, abt_pt_inr.getUpdated_at());
        values.put(ABOUT_PATIENT_INR_READING, abt_pt_inr.get_global_inr_reading());
        values.put(ABOUT_PATIENT_INR_DATE, abt_pt_inr.get_global_inr_date());
        values.put(ABOUT_PATIENT_WARFARIE_DOSE, abt_pt_inr.get_global_warfarin_dose());

        // Inserting Row
        long inserted_id = db.insertWithOnConflict(ABOUT_PATIENTS_INR_DETAIL_TABLE, null, values, 5);
//            Log.e("TODAY_MARS_TABLE Values >>>> ", values + "");
        Log.e("ABOUT_PATIENTS_INR_DETAIL_TABLE", "ABOUT_PATIENTS_INR_DETAIL_TABLE inserted_id--------->" + inserted_id);
//        }

    }

    // Updating single contact
    public int update_Inr_Reading(String new_val, String p_id) {
        Log.e("method", "update_Inr_Reading method------>" + p_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ABOUT_PATIENT_INR_READING, new_val);
        // updating row
        return db.update(ABOUT_PATIENTS_INR_DETAIL_TABLE, values, ABOUT_P_ID + " = ?", new String[]{p_id});
    }


    // Updating single contact
    public int update_warfarin_dose(String new_val, String p_id) {
        Log.e("method", "update_Inr_Reading method------>" + p_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ABOUT_PATIENT_WARFARIE_DOSE, new_val);
        // updating row
        return db.update(ABOUT_PATIENTS_INR_DETAIL_TABLE, values, ABOUT_P_ID + " = ?", new String[]{p_id});
    }


    // Updating single contact
    public int update_inr_date(String new_val, String p_id) {
        Log.e("method", "update_Inr_Reading method------>" + p_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ABOUT_PATIENT_INR_DATE, new_val);
        // updating row
        return db.update(ABOUT_PATIENTS_INR_DETAIL_TABLE, values, ABOUT_P_ID + " = ?", new String[]{p_id});
    }


    public ArrayList<ABOUT_PATIENT_INR> get_about_patients_inr_fromdb_(String patientId) {
        ArrayList<ABOUT_PATIENT_INR> new_patient_Model_List = new ArrayList<ABOUT_PATIENT_INR>();

        String selectQuery = "SELECT * FROM " + ABOUT_PATIENTS_INR_DETAIL_TABLE + " WHERE " + ABOUT_P_ID + " = " + "'" + patientId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("ABOUT_PATIENTS_INR_DETAIL_TABLE", "PATIENTS_Name count --------->" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {

                ABOUT_PATIENT_INR npm = new ABOUT_PATIENT_INR(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                        cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22));
                new_patient_Model_List.add(npm);

                Log.e("ABOUT_PATIENTS_INR_DETAIL_TABLE", "PATIENTS_FullName --------->" + cursor.getString(20));

            } while (cursor.moveToNext());
        }
        return new_patient_Model_List;
    }


    public ArrayList<ABOUT_PATIENT_INR> get_patients_inr_reading_fromdb_(String patientId) {
        ArrayList<ABOUT_PATIENT_INR> new_patient_Model_List = new ArrayList<ABOUT_PATIENT_INR>();

        String selectQuery = "SELECT * FROM " + ABOUT_PATIENTS_INR_DETAIL_TABLE + " WHERE " + ABOUT_P_ID + " = " + "'" + patientId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("ABOUT_PATIENTS_INR_DETAIL_TABLE", "PATIENTS_Name count --------->" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {

                ABOUT_PATIENT_INR npm = new ABOUT_PATIENT_INR(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                        cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22));

                new_patient_Model_List.add(npm);

                Log.e("ABOUT_PATIENTS_INR_DETAIL_TABLE", "PATIENTS_FullName --------->" + cursor.getString(20));

            } while (cursor.moveToNext());
        }
        return new_patient_Model_List;
    }


    // // Getting single contact
    // NewsFeed getContact(int id) {
    // SQLiteDatabase db = this.getReadableDatabase();
    //
    // Cursor cursor = db.query(TABLE_NEWSFEED,
    // new String[] { KEY_ID, KEY_PROJECT_ID, KEY_NEWS_HEADING,
    // KEY_NEWS_IMAGE_URL, KEY_NEWS_CREATE_DATE,
    // KEY_BUILDER_NAME, KEY_PROJECT_NAME, KEY_NEWSID, KEY_BUILDER_MOBILE,
    // KEY_TOWER_NAME,
    // KEY_TOWER_ID, KEY_LOCATION, KEY_BUILDER_ID, KEY_TYPE },
    // KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null,
    // null);
    // if (cursor != null)
    // cursor.moveToFirst();
    //
    // NewsFeed contact = new NewsFeed(Integer.parseInt(cursor.getString(0)),
    // cursor.getString(1), cursor.getString(2),
    // cursor.getString(3), cursor.getString(4), cursor.getString(5),
    // cursor.getString(6), cursor.getString(7),
    // cursor.getString(8), cursor.getString(9), cursor.getString(10),
    // cursor.getString(11),
    // cursor.getString(12), cursor.getString(13));
    // // return contact
    // return contact;
    // }

    // Updating single contact
    public int update_Last_admin(String last_admin_val, String precription_id) {
        Log.e("method", "update_Last_admin method------>" + precription_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAST_ADMIN, last_admin_val);
        // updating row
        return db.update(TABLE_PRESCRIPTION, values, KEY_PRESCRIPTION_ID + " = ?", new String[]{precription_id});
    }

    // Updating single contact
    public int update_available_quantity(String available_quantity, String precription_id) {
        Log.e("method", "update_available_quantity method------>" + precription_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AVAILABLE_QUANTITY, available_quantity);
        // updating row
        return db.update(TABLE_PRESCRIPTION, values, KEY_PRESCRIPTION_ID + " = ?", new String[]{precription_id});
    }


    // Updating single contact
    public int update_new_checked_in_quantity_toupdate(String new_checked_in_quantity_toupdate, String precription_id) {
        Log.e("method", "update_new_checked_in_quantity_toupdate method------>" + precription_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHECKED_IN_QUANTITY_TOUPDATE, new_checked_in_quantity_toupdate);
        // updating row
        return db.update(TABLE_PRESCRIPTION, values, KEY_PRESCRIPTION_ID + " = ?", new String[]{precription_id});
    }


    // Updating single contact
    public int update_checked_in_quantity(String checked_in_quantity, String precription_id) {
        Log.e("method", "update_checked_in_quantity method------>" + checked_in_quantity);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHECKED_IN_QUANTITY, checked_in_quantity);
        // updating row
        return db.update(TABLE_PRESCRIPTION, values, KEY_PRESCRIPTION_ID + " = ?", new String[]{precription_id});
    }

    // Updating single contact
    public int update_administered_quantity(String last_admin_val, String precription_id) {
        Log.e("method", "update_administered_quantity method------>" + precription_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ADMINISTERED_QUANTITY, last_admin_val);
        // updating row
        return db.update(TABLE_PRESCRIPTION, values, KEY_PRESCRIPTION_ID + " = ?", new String[]{precription_id});
    }


    // Updating single contact
    public int update_Audit_prescription(String precription_id, String stock, String bfrd_qty, String chk_qty, String wst_qty) {
        Log.e("method", "update_Audit_prescription method------>" + precription_id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AVAILABLE_QUANTITY, stock);
        values.put(KEY_BROUGHT_FORWARD_QUANTITY, bfrd_qty);
        values.put(KEY_CHECKED_IN_QUANTITY, chk_qty);
        values.put(KEY_WASTED_QUANTITY, wst_qty);
        values.put(KEY_CHECKED_IN_QUANTITY_TOUPDATE, "0");

        // updating row
        return db.update(TABLE_PRESCRIPTION, values, KEY_PRESCRIPTION_ID + " = ?", new String[]{precription_id});
    }


    /*****************************************************************************************/

    public void insert_offline_Data(String weburl, String data_to_send, String caretaker_id, String type, String id, String pat_id) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OFFLINE_URL, weburl);
        values.put(OFFLINE_DATA_TO_POST, data_to_send);
        values.put(CARETAKER_ID, caretaker_id);
        values.put(OPERATION_TYPE, type);
        values.put(ID_TO_RELATE, id);
        values.put(OFFLINE_PATIENT_ID, pat_id);


        // Inserting Row
        long inserted_id = db.insert(OFFLINE_TABLE, null, values);
//            Log.e("TODAY_MARS_TABLE Values >>>> ", values + "");
        Log.e("OFFLINE_TABLE", "OFFLINE_TABLE inserted_id--------->" + inserted_id);


    }


    public ArrayList<OFFLINE_DATA_MODEL> get_offline_Listfromdb(String caretaker_id) {

//        String caretaker_id = PreferenceConnector.readString(ctx, PreferenceConnector.USERID, "");
        Log.e("get_offline_Listfromdb", " caretaker_id --------->" + caretaker_id);
        ArrayList<OFFLINE_DATA_MODEL> offline_modelArrayList = new ArrayList<OFFLINE_DATA_MODEL>();

        String selectQuery = "SELECT * FROM " + OFFLINE_TABLE + " WHERE " + CARETAKER_ID + " = " + "'" + caretaker_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Log.e("get_offline_Listfromdb", " dbid --------->" + cursor.getString(0));
                OFFLINE_DATA_MODEL off_model = new OFFLINE_DATA_MODEL(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                offline_modelArrayList.add(off_model);

            } while (cursor.moveToNext());
        }
        return offline_modelArrayList;
    }


    // Deleting single contact
    public void delete_single_offline_record(String count) {
        Log.e("delete_single_offline_record", "delete_single_offline_record count-------->" + count);
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted_id = db.delete(OFFLINE_TABLE, OFFLINE__ID + " = ?", new String[]{count});
        Log.e("delete_single_offline_record", "delete_single_offline_record deleted_id--------->" + deleted_id);

//        db.close();
    }

    public boolean get_admin_for_this_time_slot_and_patient(String pid, String s1) {

        ArrayList<String> prelist = new ArrayList<String>();
        ArrayList<Boolean> boollist = new ArrayList<Boolean>();

        Log.e("get_admin_for_this_time_slot_and_patient", "get_admin_for_this_time_slot_and_patient---->" + pid);
        Log.e("get_admin_for_this_time_slot_and_patient", "get_admin_for_this_time_slot_and_patient---->" + s1);

        ArrayList<PRESCRIPTION_Model_Phase2> prescription_common_modelArrayList = new ArrayList<PRESCRIPTION_Model_Phase2>();

        String selectQuery = "SELECT * FROM " + TABLE_PRESCRIPTION + " WHERE " + KEY_PATIENT_ID + " = " + "'" + pid + "'" + " AND " + KEY_CHECKED_IN_CYCLE_TYPE + " = " + "'" + "this_cycle" + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("get_admin_for_this_time_slot_and_patient", "get_admin_for_this_time_slot_and_patient---->" + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(1).equals("Irregular")) {

                    boolean b = check_if_current_date_is_in_Irregular_array(cursor.getString(26));

                    if (b == false) {

                        if (Integer.parseInt(cursor.getString(16)) > 0) {

                            PRESCRIPTION_Model_Phase2 contact = new PRESCRIPTION_Model_Phase2(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                                    cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                                    cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                                    cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                                    cursor.getString(14), Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                                    Integer.parseInt(cursor.getString(18)), Integer.parseInt(cursor.getString(19)), Integer.parseInt(cursor.getString(20)), Integer.parseInt(cursor.getString(21)),
                                    Integer.parseInt(cursor.getString(22)), Integer.parseInt(cursor.getString(23)), Integer.parseInt(cursor.getString(24)), cursor.getString(25),
                                    cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                                    Integer.parseInt(cursor.getString(31)), cursor.getString(32), cursor.getString(33), cursor.getString(34), cursor.getString(35));

                            ArrayList<Today_Mars_Mar_Bean_Phase2> Today_Mars_Mar_objects_list = get_admin_todaymar_fromdb(cursor.getString(0), pid);

                            Log.e("if get_admin_for_this_time_slot_and_patient", "Prescription id ---->" + cursor.getString(0));

                            contact.setToday_Mars_Mar_objects_list(Today_Mars_Mar_objects_list);
                            prescription_common_modelArrayList.add(contact);
                        }

                    }
                } else {

                    if (Integer.parseInt(cursor.getString(16)) > 0) {

                        PRESCRIPTION_Model_Phase2 contact = new PRESCRIPTION_Model_Phase2(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9),
                                cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                                cursor.getString(14), Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                                Integer.parseInt(cursor.getString(18)), Integer.parseInt(cursor.getString(19)), Integer.parseInt(cursor.getString(20)), Integer.parseInt(cursor.getString(21)),
                                Integer.parseInt(cursor.getString(22)), Integer.parseInt(cursor.getString(23)), Integer.parseInt(cursor.getString(24)), cursor.getString(25),
                                cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29), cursor.getString(30),
                                Integer.parseInt(cursor.getString(31)), cursor.getString(32), cursor.getString(33), cursor.getString(34), cursor.getString(35));

                        ArrayList<Today_Mars_Mar_Bean_Phase2> Today_Mars_Mar_objects_list = get_admin_todaymar_fromdb(cursor.getString(0), pid);

                        Log.e("else get_admin_for_this_time_slot_and_patient", "Prescription id ---->" + cursor.getString(0));

                        contact.setToday_Mars_Mar_objects_list(Today_Mars_Mar_objects_list);
                        prescription_common_modelArrayList.add(contact);
                    }
                }

            } while (cursor.moveToNext());
        }


        for (int x = 0; x < prescription_common_modelArrayList.size(); x++) {

            if (!prescription_common_modelArrayList.get(x).getPrescription_type().contains("PRN")) {

//           ArrayList<TIME_SLOT_DOSES> Tsd_objects_list = admin_prescription_modelArrayList.get(x).getTIME_SLOT_DOSES_objects_list();

                String TIME_SLOT_DOSES_array = prescription_common_modelArrayList.get(x).getPrescribed_time_slots_array();
                String pres_id = "" + prescription_common_modelArrayList.get(x).getId();
                ArrayList<PRESCRIBED_TIME_SLOT_phase2> Tsd_objects_list = get_pres_timeslot_list(TIME_SLOT_DOSES_array, pres_id);

                for (int j = 0; j < Tsd_objects_list.size(); j++) {
                    PRESCRIBED_TIME_SLOT_phase2 timeSlotDoses = Tsd_objects_list.get(j);

                    if (timeSlotDoses.getSlot_time().equals(s1)) {
//                        if (!timeSlotDoses.getTime_slot_doses_dose().isEmpty()) {
                        prelist.add("" + prescription_common_modelArrayList.get(x).getId());
                        Log.e("------>", "aaaaaaaaaa-------->" + prescription_common_modelArrayList.get(x).getDrug_name());
                        Log.e("------>", "iddddddddd-------->" + prescription_common_modelArrayList.get(x).getId());
//                        }
                    }
                }
            }
        }


        for (int z = 0; z < prelist.size(); z++) {

            Log.e("------>", "prelist.get(z)------>" + prelist.get(z));
            boolean b = check_admin_todaymar_fromdb("" + prelist.get(z), pid, s1);

            Log.e("------>", "s1------>" + s1);
            Log.e("------>", "b------>" + b);
            boollist.add(b);

        }

        if (boollist.size() > 0) {
            if (boollist.contains(false)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    private boolean check_if_current_date_is_in_Irregular_array(String abc) {

        boolean b1 = false;

        final Calendar c = Calendar.getInstance();

        int dd = c.get(Calendar.DAY_OF_MONTH);

//        Log.e("recipients[i]", "dd------>" + dd);

//        Log.e("recipients[i]", "check_if_current_date_is_in_Irregular_array------>" + abc);
        try {
            JSONArray temp = new JSONArray(abc);
            int length = temp.length();

            if (length > 0) {
                String[] recipients = new String[length];
                for (int i = 0; i < length; i++) {
                    recipients[i] = temp.getString(i);
//                    Log.e("recipients[i]", "recipients[i]------>" + recipients[i]);

                    if (dd == Integer.parseInt(recipients[i])) {

                        b1 = true;
//                        Log.e("recipients[i]", "b1------>" + b1);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b1;
    }

//    private ArrayList<TIME_SLOT_DOSES> gettimeslotlist(String TIME_SLOT_DOSES_array) {
//
//        ArrayList<TIME_SLOT_DOSES> admin_TIME_SLOT_DOSES_objects_list = new ArrayList<TIME_SLOT_DOSES>();
//
//        try {
//            JSONArray array2 = new JSONArray(TIME_SLOT_DOSES_array);
//
//            for (int j = 0; j < array2.length(); j++) {
//                JSONObject obj6 = array2.optJSONObject(j);
//                String time_slot_doses_color = obj6.optString("color");
//                String time_slot_doses_dose = obj6.optString("dose");
//                String time_slot_doses_id = obj6.optString("id");
//                String time_slot_doses_show_as = obj6.optString("show_as");
//                String time_slot_doses_slot_time = obj6.optString("slot_time");
//                TIME_SLOT_DOSES tsd_obj = new TIME_SLOT_DOSES(time_slot_doses_color, time_slot_doses_dose, time_slot_doses_id, time_slot_doses_show_as, time_slot_doses_slot_time);
//                admin_TIME_SLOT_DOSES_objects_list.add(tsd_obj);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return admin_TIME_SLOT_DOSES_objects_list;
//
//    }


    private ArrayList<PRESCRIBED_TIME_SLOT_phase2> get_pres_timeslot_list(String TIME_SLOT_DOSES_array, String pres_id) {

        ArrayList<PRESCRIBED_TIME_SLOT_phase2> admin_TIME_SLOT_DOSES_objects_list = new ArrayList<PRESCRIBED_TIME_SLOT_phase2>();

        try {
            JSONArray array2 = new JSONArray(TIME_SLOT_DOSES_array);

            for (int j = 0; j < array2.length(); j++) {
                JSONObject obj6 = array2.optJSONObject(j);
                String time_slot_doses_id = obj6.optString("id");
                String time_slot_doses_slot_time = obj6.optString("slot_time");
                String time_slot_doses_show_as = obj6.optString("show_as");
                String time_slot_doses_color = obj6.optString("color");
                String time_slot_doses_dose = obj6.optString("dose");
                String updated_at = obj6.optString("updated_at");

                PRESCRIBED_TIME_SLOT_phase2 tsd_obj = new PRESCRIBED_TIME_SLOT_phase2(time_slot_doses_id, time_slot_doses_slot_time, time_slot_doses_show_as, time_slot_doses_color, time_slot_doses_dose, updated_at, pres_id);
//                TIME_SLOT_DOSES tsd_obj = new TIME_SLOT_DOSES(time_slot_doses_color, time_slot_doses_dose, time_slot_doses_id, time_slot_doses_show_as, time_slot_doses_slot_time);
                admin_TIME_SLOT_DOSES_objects_list.add(tsd_obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin_TIME_SLOT_DOSES_objects_list;

    }


    // // Deleting single contact
    // public void deleteContact(NewsFeed contact) {
    // SQLiteDatabase db = this.getWritableDatabase();
    // db.delete(TABLE_NEWSFEED, KEY_ID + " = ?", new String[] {
    // String.valueOf(contact.getDbid()) });
    // db.close();
    // }

    // // Getting contacts Count
    // public int getContactsCount() {
    // String countQuery = "SELECT * FROM " + TABLE_NEWSFEED;
    // SQLiteDatabase db = this.getReadableDatabase();
    // Cursor cursor = db.rawQuery(countQuery, null);
    // cursor.close();
    //
    // // return count
    // return cursor.getCount();
    // }


//    public void deleteOldFeeds(Context context) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try {
//            db.execSQL(
//                    "DELETE FROM NewsFeed_Table WHERE id not in (SELECT id FROM NewsFeed_Table ORDER BY id desc LIMIT 10)");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void insert_yesterday_marsData_admin(ArrayList<Yesterday_Mar_Bean> yesterday_mar_bean_list, String status) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            if (status.equals("online")) {

                for (int i = 0; i < yesterday_mar_bean_list.size(); i++) {

                    delete_yesterdaymar_record(yesterday_mar_bean_list.get(i).getId());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < yesterday_mar_bean_list.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(YESTERDAY_MARS_ID, yesterday_mar_bean_list.get(i).getId());
            values.put(YESTERDAY_MARS_ADMINISTRATED_AT, yesterday_mar_bean_list.get(i).getAdministered_at());
            values.put(YESTERDAY_MARS_DOSE, yesterday_mar_bean_list.get(i).getDose());
            values.put(YESTERDAY_MARS_FALSE_REASON, yesterday_mar_bean_list.get(i).getFalse_reason());
            values.put(YESTERDAY_MARS_IS_WASTE, yesterday_mar_bean_list.get(i).getIs_waste());
            values.put(YESTERDAY_MARS_OUTCOME, yesterday_mar_bean_list.get(i).getOutcome());
            values.put(YESTERDAY_MARS_QUANTITY_WASTED, yesterday_mar_bean_list.get(i).getQuantity_wasted());
            values.put(YESTERDAY_MARS_SLOT_TIME, yesterday_mar_bean_list.get(i).getSlot_time());
            values.put(YESTERDAY_MARS_UPDATED_AT, yesterday_mar_bean_list.get(i).getUpdated_at());
            values.put(YESTERDAY_MARS_USERID, yesterday_mar_bean_list.get(i).getUserid());
            values.put(YESTERDAY_MARS_USERNAME, yesterday_mar_bean_list.get(i).getUsername());
            values.put(YESTERDAY_MARS_USERFULLNAME, yesterday_mar_bean_list.get(i).getFullname());
            values.put(YESTERDAY_MARS_USEREMAIL, yesterday_mar_bean_list.get(i).getEmail());
            values.put(YESTERDAY_MARS_HOMELY_REMEDY_ID, yesterday_mar_bean_list.get(i).getHomely_id());
            values.put(YESTERDAY_MARS_HOMELY_REMEDY_NAME, yesterday_mar_bean_list.get(i).getHomely_name());
            values.put(YESTERDAY_MARS_PRESCRIPTION_ID, yesterday_mar_bean_list.get(i).getPrescription_id());
            values.put(YESTERDAY_MARS_PATIENT_ID, yesterday_mar_bean_list.get(i).getPatientid());
            values.put(YESTERDAY_MARS_SECONDRY_USERID, yesterday_mar_bean_list.get(i).getSecondry_userid());
            values.put(YESTERDAY_MARS_SECONDRY_USERNAME, yesterday_mar_bean_list.get(i).getSecondry_username());
            values.put(YESTERDAY_MARS_SECONDRY_USERFULLNAME, yesterday_mar_bean_list.get(i).getSecondry_fullname());
            values.put(YESTERDAY_MARS_SECONDRY_USEREMAIL, yesterday_mar_bean_list.get(i).getSecondry_email());


            //            values.put(TODAY_MARS_MEDICATION_ID, homely_today_mars_mar_bean_list.get(i).getMedication_id());
            //            values.put(TODAY_MARS_SECONDRY_USER_ID, homely_today_mars_mar_bean_list.get(i).getSecondary_user_id());
            //            values.put(TODAY_MARS_SECONDRY_USER_FULLNAME, homely_today_mars_mar_bean_list.get(i).getSeconday_user_fullname());
            //            values.put(TODAY_MARS_UID, homely_today_mars_mar_bean_list.get(i).getUid());
            //            int count = check_for_TODAY_MARS_ID(TODAY_MARS_ID);
            //            Log.e("count", "count --------->" + count);
            //            if (count <= 0) {
            // Inserting Row

            long inserted_id = db.insert(YESTERDAY_MARS_TABLE, null, values);
            //            Log.e("TODAY_MARS_TABLE Values >>>> ", values + "");
            Log.e("YESTERDAY_MARS_TABLE", "YESTERDAY_MARS_TABLE inserted_id--------->" + inserted_id);

            //        } else {
            //
            //                Log.e("TODAY_MARS_TABLE", "cant insert duplicate TODAY_MARS_TABLE --------->");
            //
            //            }
            //
        }
    }


    public void insert_yesterday_marsData(ArrayList<Homely_Today_Mars_Mar_Bean_Phase2> homely_today_mars_mar_bean_list, String status) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("insert_today_marsData", "insert_today_marsData --------->" + homely_today_mars_mar_bean_list.size());

        if (status.equals("online")) {

            for (int i = 0; i < homely_today_mars_mar_bean_list.size(); i++) {
                delete_todaymar_record(homely_today_mars_mar_bean_list.get(i).getId());
            }
        }

        for (int i = 0; i < homely_today_mars_mar_bean_list.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(YESTERDAY_MARS_ID, homely_today_mars_mar_bean_list.get(i).getId());
            values.put(YESTERDAY_MARS_ADMINISTRATED_AT, homely_today_mars_mar_bean_list.get(i).getAdministered_at());
            values.put(YESTERDAY_MARS_DOSE, homely_today_mars_mar_bean_list.get(i).getDose());
            values.put(YESTERDAY_MARS_FALSE_REASON, homely_today_mars_mar_bean_list.get(i).getFalse_reason());
            values.put(YESTERDAY_MARS_IS_WASTE, homely_today_mars_mar_bean_list.get(i).getIs_waste());
            values.put(YESTERDAY_MARS_OUTCOME, homely_today_mars_mar_bean_list.get(i).getOutcome());
            values.put(YESTERDAY_MARS_QUANTITY_WASTED, homely_today_mars_mar_bean_list.get(i).getQuantity_wasted());
            values.put(YESTERDAY_MARS_SLOT_TIME, homely_today_mars_mar_bean_list.get(i).getSlot_time());
            values.put(YESTERDAY_MARS_UPDATED_AT, homely_today_mars_mar_bean_list.get(i).getUpdated_at());
            values.put(YESTERDAY_MARS_USERID, homely_today_mars_mar_bean_list.get(i).getUserid());
            values.put(YESTERDAY_MARS_USERNAME, homely_today_mars_mar_bean_list.get(i).getUsername());
            values.put(YESTERDAY_MARS_USERFULLNAME, homely_today_mars_mar_bean_list.get(i).getFullname());
            values.put(YESTERDAY_MARS_USEREMAIL, homely_today_mars_mar_bean_list.get(i).getEmail());
            values.put(YESTERDAY_MARS_HOMELY_REMEDY_ID, homely_today_mars_mar_bean_list.get(i).getHomely_id());
            values.put(YESTERDAY_MARS_HOMELY_REMEDY_NAME, homely_today_mars_mar_bean_list.get(i).getHomely_name());
            values.put(YESTERDAY_MARS_PRESCRIPTION_ID, homely_today_mars_mar_bean_list.get(i).getPrescription_id());
            values.put(YESTERDAY_MARS_PATIENT_ID, homely_today_mars_mar_bean_list.get(i).getPatientid());

//            int count = check_for_TODAY_MARS_ID(TODAY_MARS_ID);
//            Log.e("count", "count --------->" + count);
//            if (count <= 0) {
            // Inserting Row
            long inserted_id = db.insert(YESTERDAY_MARS_TABLE, null, values);

            Log.e("YESTERDAY_MARS_TABLE", "YESTERDAY_MARS_TABLE inserted_id--------->" + inserted_id);

//            } else {
//
//                Log.e("TODAY_MARS_TABLE", "cant insert duplicate TODAY_MARS_TABLE --------->");
//
//            }
        }

    }


    // Deleting single contact
    public void delete_yesterdaymar_record(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(YESTERDAY_MARS_TABLE, YESTERDAY_MARS_ID + " = ?", new String[]{
                String.valueOf(id)});
//        db.close();
    }


    public boolean getYesterdayMar(String presecriptionid, String patientid, String timeslot) {
        ArrayList<Yesterday_Mar_Bean> yesterdayarraylist = new ArrayList<Yesterday_Mar_Bean>();


        String selectQuery = "SELECT * FROM " + YESTERDAY_MARS_TABLE + " WHERE " + YESTERDAY_MARS_PRESCRIPTION_ID + " = " + "'" + presecriptionid + "'" + " AND " + YESTERDAY_MARS_PATIENT_ID + " = " + "'" + patientid + "'" + " AND " + YESTERDAY_MARS_SLOT_TIME + " = " + "'" + timeslot + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

//                NotesModel notesModel = new NotesModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
//                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18));

                Yesterday_Mar_Bean ym2 = new Yesterday_Mar_Bean(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14)
                        , cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21));

                yesterdayarraylist.add(ym2);

            } while (cursor.moveToNext());
        }

        if (yesterdayarraylist.size() > 0)
            return true;

        return false;
    }


}
