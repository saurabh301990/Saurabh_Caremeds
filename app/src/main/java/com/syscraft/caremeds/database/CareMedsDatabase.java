/*
package com.syscraft.caremeds.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.syscraft.caremeds.model.MeasureModel;
import com.syscraft.caremeds.model.NotesModel;
import com.syscraft.caremeds.model.PatientModel;

import java.util.ArrayList;

*/
/**
 * Created by anubhav on 2/23/2016.
 *//*

public class CareMedsDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "caremedsDB";
    public static final int DATABASE_VERSION = 1;

    */
/* Database Table Names *//*

    public static final String NOTES_TABLE = "notes";
    public static final String PATIENT_TABLE = "patient";
    public static final String MEASURE_TABLE = "measure";

    */
/* Notes Table Column Name *//*

    public static final String ID = "id";
    public static final String CONTENT = "content";
    public static final String NOTE_CREATED = "note_created";
    public static final String NOTE_DELETED = "note_deleted";
    public static final String NOTE_ID = "note_id";
    public static final String MAR_ID = "mar_id";
    public static final String NOTE_DATE = "note_date";
    public static final String NOTE_TYPE = "note_type";
    public static final String PRESCRIPTION_ID = "prescription_id";
    public static final String NOTE_SUBJECT = "note_name";
    public static final String NOTE_UPDATED = "note_updated";
    public static final String USER_ID = "user_id";
    public static final String CAN_MANAGE_USERS = "can_manage_users";
    public static final String CARE_HOME_ID = "care_home_id";
    public static final String EMAIL = "email";
    public static final String IS_ACTIVE = "is_active";
    public static final String USERNAME = "username";
    public static final String ADMIN_NAME = "admin_name";



    */
/* Patient Table Column Name ,Aman*//*

    public static final String PATIENT_ID = "patient_id";
    public static final String PATIENT_NAME = "patient_name";
    public static final String PATIENT_DOB = "patient_dob";
    public static final String PATIENT_ALLERGIES = "patient_allergies";
    public static final String PATIENT_NOTES = "patient_notes";
    public static final String PATIENT_GP_NAME = "patient_gp_name";
    public static final String PATIENT_IMAGE = "patient_image";
    public static final String PATIENT_CREATED_AT = "patient_created_at";
    public static final String PATIENT_CARE_HOME_ID = "patient_care_home_id";
    public static final String PATIENT_ROOM = "patient_room";
    public static final String PATIENT_CYCLE_BASE_DATE = "patient_cycle_base_date";
    public static final String PATIENT_ADDRESS = "patient_address";
    public static final String PATIENT_CYCLE_DURATION = "patient_cycle_duration";
    public static final String PATIENT_GP_ID = "patient_gp_id";
    public static final String PATIENT_UPDATED_AT = "patient_update_at";

    */
/* Measure Table Column Name*//*

    public static final String MEASURE_ID = "measure_id";
    public static final String MEASURE_CREATED = "measure_created";
    public static final String MEASURE_DELETED = "measure_deleted";
    public static final String MEASURE_UPDATED = "measure_updated";
    public static final String MEASURE_NAME = "measure_name";
    public static final String MEASURE_UNITS = "measure_units";
    public static final String MEASURE_BSSITE_VALUE = "measure_bssite_value";

    Context context;
    String MeasureType;

    public CareMedsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTES_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PATIENT_ID + " INTEGER, " + NOTE_SUBJECT +
                " TEXT, " + NOTE_CREATED + " TEXT, " + ADMIN_NAME
                + " TEXT, " + CONTENT + " TEXT, " + NOTE_DELETED + " TEXT, "
                + MAR_ID + " INTEGER, " + NOTE_ID + " INTEGER, " + NOTE_DATE
                + " TEXT, " + NOTE_TYPE + " TEXT, " + PRESCRIPTION_ID + " INTEGER, "
                + NOTE_UPDATED + " TEXT, " + USER_ID + " INTEGER, " + CAN_MANAGE_USERS
                + " TEXT, " + CARE_HOME_ID + " INTEGER, " + EMAIL + " TEXT, " + USERNAME
                + " TEXT, " + IS_ACTIVE + " TEXT " + ");";

        String CREATE_MEASURE_TABLE = "CREATE TABLE " + MEASURE_TABLE + "("
                + MEASURE_ID + " INTEGER PRIMARY KEY, " + MEASURE_NAME + " TEXT, "
                + MEASURE_UNITS + " TEXT, " + MEASURE_CREATED + " TEXT, " +
                MEASURE_DELETED + " TEXT, " + MEASURE_UPDATED + " TEXT, " +MEASURE_BSSITE_VALUE + " TEXT " + ");";


        String CREATE_PATIENT_TABLE = "CREATE TABLE " + PATIENT_TABLE + "("
                + PATIENT_ID + " INTEGER PRIMARY KEY, " + PATIENT_IMAGE + " TEXT, "
                + PATIENT_CREATED_AT + " TEXT, " + PATIENT_CARE_HOME_ID + " INTEGER, " + PATIENT_DOB
                + " TEXT, " + PATIENT_GP_NAME + " TEXT, " + PATIENT_ROOM + " TEXT, " + PATIENT_ALLERGIES +
                " TEXT, " + PATIENT_CYCLE_BASE_DATE + " TEXT, "
                + PATIENT_ADDRESS + " TEXT, " + PATIENT_CYCLE_DURATION + " TEXT, " + PATIENT_GP_ID
                + " INTEGER, " + PATIENT_NAME + " TEXT, " + PATIENT_UPDATED_AT + " TEXT " + ");";


        db.execSQL(CREATE_NOTES_TABLE);
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_MEASURE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    */
/*insert patient data*//*

    public void insertPatientData(ArrayList<PatientModel> dataList) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < dataList.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(PATIENT_NAME, dataList.get(i).getPatientName());
            values.put(PATIENT_DOB, dataList.get(i).getPatientDob());
            values.put(PATIENT_ALLERGIES, dataList.get(i).getPatientAllergies());
            // values.put(PATIENT_NOTES, dataList.get(i).getPatientnotes());
            values.put(PATIENT_GP_ID, dataList.get(i).getPatientGpName());
            values.put(PATIENT_IMAGE, dataList.get(i).getPatientImage());
            Log.e("Value", values + "");

            // Inserting Row
            db.insert(PATIENT_TABLE, null, values);
        }
    }

    public void insertMeasureData(ArrayList<MeasureModel> MeasureList) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < MeasureList.size(); i++) {

            ContentValues values = new ContentValues();
           // values.put(MEASURE_UNITS, MeasureList.get(i).getMeasureValue());
           // values.put(MEASURE_NAME, MeasureList.get(i).getMeasureType());
            // values.put(MEASURE_BSSITE_VALUE, MeasureList.get(i).getMeasurebssite());
           // values.put(MEASURE_CREATED, MeasureList.get(i).getMeasureTime());

//            public static final String MEASURE_ID = "measure_id";
//            public static final String MEASURE_CREATED = "measure_created";
//            public static final String MEASURE_DELETED = "measure_deleted";
//            public static final String MEASURE_UPDATED = "measure_updated";
//            public static final String MEASURE_NAME = "measure_name";
//            public static final String MEASURE_UNITS = "measure_units";
//            public static final String MEASURE_BSSITE_VALUE = "measure_bssite_value";

            Log.e("Value", values + "");

            // Inserting Row
           // db.insert(MEASURE_TABLE, null, values);
        }
    }

    */
/*Select patient data*//*

    public ArrayList<PatientModel> getPatientData(String name) {
        ArrayList<PatientModel> PatientDataList = new ArrayList<PatientModel>();
        String selectQuery = "SELECT " + PATIENT_NAME + " , " + PATIENT_DOB
                + " , " + PATIENT_ALLERGIES + " , " + PATIENT_NOTES + " ,  " + PATIENT_GP_ID + " , " +
                PATIENT_IMAGE + " FROM " + PATIENT_TABLE + " WHERE " + PATIENT_NAME + " = " + "'" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PatientModel PatientDataModel = new PatientModel("Mr. Andrew Smith");
                String pet_name = cursor.getString(cursor
                        .getColumnIndex(PATIENT_NAME));
                String pet_dob = cursor.getString(cursor
                        .getColumnIndex(PATIENT_DOB));
                String pet_allergies = cursor
                        .getString(cursor.getColumnIndex(PATIENT_ALLERGIES));
                String pet_notes = cursor.getString(cursor.getColumnIndex(PATIENT_NOTES));
                String pet_gp = cursor
                        .getString(cursor.getColumnIndex(PATIENT_GP_ID));
                String pet_image = cursor
                        .getString(cursor.getColumnIndex(PATIENT_IMAGE));

                PatientDataModel.setPatientName(pet_name);
                PatientDataModel.setPatientDob(pet_dob);
                PatientDataModel.setPatientAllergies(pet_allergies);
                //   PatientDataModel.setPatientnotes(pet_notes);
                PatientDataModel.setPatientGpName(pet_gp);
                PatientDataModel.setPatientImage(pet_image);
                PatientDataList.add(PatientDataModel);

            } while (cursor.moveToNext());
        }
        return PatientDataList;
    }

    public void updateMeasureValue(String type, String v1, String v2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MEASURE_NAME, v1);
        values.put(MEASURE_BSSITE_VALUE, v2);
        db.update(MEASURE_TABLE, values, MeasureType + "=" + type, null);
    }

    public void insertNotesData(ArrayList<NotesModel> notesarraylist) {
        //  SQLiteDatabase db = this.getWritableDatabase();

        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < notesarraylist.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(PATIENT_ID, notesarraylist.get(i).getPatient_id());
            values.put(NOTE_SUBJECT, notesarraylist.get(i).getSubject());
            values.put(NOTE_CREATED, notesarraylist.get(i).getCreated_at());
            values.put(ADMIN_NAME, notesarraylist.get(i).getFullname());
            values.put(CONTENT, notesarraylist.get(i).getContent());

            values.put(NOTE_DELETED, notesarraylist.get(i).getDeleted_at());
            values.put(NOTE_ID, notesarraylist.get(i).getId());
            values.put(MAR_ID, notesarraylist.get(i).getMar_Id());
            values.put(NOTE_DATE, notesarraylist.get(i).getNote_date());
            values.put(NOTE_TYPE, notesarraylist.get(i).getNote_type());
            values.put(PRESCRIPTION_ID, notesarraylist.get(i).getPrescription_id());
            values.put(NOTE_UPDATED, notesarraylist.get(i).getUpdated_at());
            values.put(USER_ID, notesarraylist.get(i).getUser_id());
            values.put(CAN_MANAGE_USERS, notesarraylist.get(i).getCan_manage_users());
            values.put(CARE_HOME_ID, notesarraylist.get(i).getCare_home_id());
            values.put(EMAIL, notesarraylist.get(i).getEmail());
            values.put(IS_ACTIVE, notesarraylist.get(i).getIs_active());
            values.put(USERNAME, notesarraylist.get(i).getUsername());

            // Inserting Row
            db.insert(NOTES_TABLE, null, values);
            Log.e("Notes Values >>>> ", values + "");
        }

    }

    public ArrayList<NotesModel> getNotesData(int id) {
        ArrayList<NotesModel> notesarraylist = new ArrayList<NotesModel>();

        String selectQuery = "SELECT " + NOTE_SUBJECT + " , " + NOTE_CREATED
                + " , " + ADMIN_NAME + " , " + CONTENT
                + " FROM " + NOTES_TABLE + "WHERE" + PATIENT_ID + "=" + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                NotesModel notesModel = new NotesModel();
                String note_sub = cursor.getString(cursor.getColumnIndex(NOTE_SUBJECT));
                String note_time = cursor.getString(cursor.getColumnIndex(NOTE_CREATED));
                String admin_name = cursor.getString(cursor.getColumnIndex(ADMIN_NAME));
                String note_type = cursor.getString(cursor.getColumnIndex(CONTENT));

                notesModel.setSubject(note_sub);
                notesModel.setCreated_at(note_time);
                notesModel.setFullname(admin_name);
                notesModel.setNote_type(note_type);

                notesarraylist.add(notesModel);

            } while (cursor.moveToNext());
        }
        return notesarraylist;
    }

    public void deleteTableData(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);

    }
}
*/
