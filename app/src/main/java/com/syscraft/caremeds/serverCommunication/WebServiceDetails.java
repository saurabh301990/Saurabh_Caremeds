package com.syscraft.caremeds.serverCommunication;

import com.syscraft.caremeds.sharedPrefrns.PreferenceConnector;

public interface WebServiceDetails {

    public static final int CONNECTION_TIMEOUT = 300000;


//    String baseurl_value = PreferenceConnector.readString(getApplicationContext(), PreferenceConnector.SETTING_BASE_URL, "");

//    public static final String LOGIN_URL = "https://demo.caremeds.co.uk/api";
//    public static final String ADMIN1 = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String MEDICATIONS = "https://demo.caremeds.co.uk/api/medications/1012550?authentication_token=BKdiJphcWGDPZFJqyoE7";
//    public static final String GET_PATIENT_RECORD_DATA = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String MEASUREMENT_DATA = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String HOMELY_REMEDY = "https://demo.caremeds.co.uk/api/patients/";
//    static final String NOTES1 = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String BSSITE_DATA = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String PATIENT = "https://demo.caremeds.co.uk/api/patients?authentication_token=";
//    public static final String PROFILE_PIC1 = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String RETURN1 = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String SAVE_HOMELY_REMEDY = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String SAVE_MEASURE = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String SAVE_CHECKIN = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String SAVE_RETURN = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String CHECKIN = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String MEDS = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String CREATE_SUGAR_NOTE = "https://demo.caremeds.co.uk/api/patients/";
//    public static final String CREATE_GENERAL_NOTE = "https://demo.caremeds.co.uk/api/patients/";

    // public static final String PATIENT = "https://demo.caremeds.co.uk/api/patients?authentication_token=BKdiJphcWGDPZFJqyoE7";
    //public static final String LOGIN_URL = "https://demo.caremeds.co.uk/api/users/sign_in";
    //public static final String PATIENT_ITEM = "https://demo.caremeds.co.uk/api/patients/164/prescriptions?authentication_token=BKdiJphcWGDPZFJqyoE7";

    //public static final String MEASUREMENT_DATA = "https://demo.caremeds.co.uk/api/patients/164/measurements/?authentication_token=BKdiJphcWGDPZFJqyoE7";
    // https://demo.caremeds.co.uk/api/patients/164/measurements/?authentication_token=BKdiJphcWGDPZFJqyoE7&last=true&all_types=true
//https://demo.caremeds.co.uk/api/patients/164/notes?authentication_token=BKdiJphcWGDPZF&note_type=BS_Site&limit=1
    //    public static final String MEDICATIONS = "https://demo.caremeds.co.uk/api/medications/1012550?authentication_token=BKdiJphcWGDPZFJqyoE7";

    // public static final String PRESCRIPTION = "https://demo.caremeds.co.uk/api/patients/164/prescriptions?authentication_token=BKdiJphcWGDPZFJqyoE7";
    //public static final String HOMELY_REMEDY = "https://demo.caremeds.co.uk/api/patients/164/homely_remedies/?authentication_token=BKdiJphcWGDPZFJqyoE7&note_type=General";

    // ************** below urls are Not in used **********************
//    public static final String PROFILE_PIC = "https://demo.caremeds.co.uk/api/patients/164/image?9ecd5b3b-3c5d-4589-84c2-af9c8001315c.jpg=&authentication_token=BKdiJphcWGDPZFJqyoE7&disposition=inline";
    // public static final String MEDS = "https://demo.caremeds.co.uk/api/patients/164/prescriptions/?authentication_token=BKdiJphcWGDPZFJqyoE7&filter=this_cycle&view=meds-list";
//    public static final String RETURN = "https://demo.caremeds.co.uk/api/patients/164/prescriptions?authentication_token=BKdiJphcWGDPZFJqyoE7&type=returns";
//    public static final String ADMIN = "https://demo.caremeds.co.uk/api/patients/164/prescriptions?authentication_token=BKdiJphcWGDPZFJqyoE7";
//    public static final String NOTES = "https://demo.caremeds.co.uk/api/patients/164/notes/?authentication_token=BKdiJphcWGDPZFJqyoE7";
    //public static final String SAVE_HOMELY_REMEDY = "https://demo.caremeds.co.uk/api/patients/164/homely_remedies/9/create_mar";
    //public static final String SAVE_HOMELY_REMEDY = "https://demo.caremeds.co.uk/api/patients/164/homely_remedies/";
    //public static final String SAVE_MEASURE = "https://demo.caremeds.co.uk/api/patients/164/measurements";
    //public static final String SAVE_CHECKIN = "https://demo.caremeds.co.uk/api/patients/164/prescriptions/4026/check_in";
    //public static final String CHECKIN = "https://demo.caremeds.co.uk/api/patients/164/prescriptions/?authentication_token=BKdiJphcWGDPZFJqyoE7&filter=this_cycle&type=check_ins";
    //public static final String MEDS = "https://demo.caremeds.co.uk/api/patients/164/prescriptions/?authentication_token=BKdiJphcWGDPZFJqyoE7&filter=this_cycle&view=meds-list";
    //public static final String PATIENT_ITEM = "https://demo.caremeds.co.uk/api/patients/164/prescriptions/3811?filter=this_cycle&authentication_token=BKdiJphcWGDPZFJqyoE7";
}



