package com.syscraft.caremeds.Utility;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Deepika on 04-04-2016.
 */
public class Date_utility {


    public static String get_adminstarated_datetime_ukzone() {
        String date = "";
        try {
            // DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssz");
            df.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            date = df.format(Calendar.getInstance().getTime());
            // Log.e("date formate ", date);

            System.out.println("--->" + date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public String getMeasuredate() {
        String date = "";
        try {
            //DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
            date = df.format(Calendar.getInstance().getTime());
            Log.e("date formate ", date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDate(String createdate) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
//        form.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createdate);
            SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yy");
            postFormater.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            newDateStr = postFormater.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDateStr;
    }


    public String Patient_detail_getDate(String createdate) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createdate);
            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm");
            newDateStr = postFormater.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDateStr;
    }


    public static String getTime(String createtime) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createtime);
            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
//            postFormater.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            newDateStr = postFormater.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDateStr;
    }


    public static String getTime_in_24hour_format(String createtime) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createtime);
//            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
//            postFormater.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
//            newDateStr = postFormater.format(date);

            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss aa");
            newDateStr = displayFormat.format(date);
            System.out.println(" displayFormat------> " + newDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDateStr;
    }







    public static String getTime_in_24hour_format__222222(String createtime) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        java.util.Date date = null;
        String newDateStr = "";
        try {
            date = form.parse(createtime);
//            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
//            postFormater.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
//            newDateStr = postFormater.format(date);

            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss aa");
            newDateStr = displayFormat.format(date);
            System.out.println(" displayFormat------> " + newDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDateStr;
    }


    public static String getCurrentdate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String formattedDate2 = df2.format(c.getTime());
        System.out.println("getCurrentdate method => " + formattedDate2);
//        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
//        java.util.Date date = null;
//        String newDateStr = "";
//        try {
//            date = form.parse(createtime);
//            SimpleDateFormat postFormater = new SimpleDateFormat("hh:mm:ss aa");
//            newDateStr = postFormater.format(date);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
        return formattedDate2;
    }


    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        java.util.Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        String localTime = date.format(currentLocalTime);
        Log.e("@@@@@@@@@@ ", " getCurrentTime method => " + localTime);

        return localTime;
    }

    public static boolean date_diffrence(String dateStart) {
        Calendar cal = Calendar.getInstance();
//        String dateStart = "2016-04-21";
//        String dateStop = "" + cal.getTime();
//        System.out.print("current date time --->" + dateStop + "\n\n\n");
        // This is how to get today's date in Java
        Date today = new Date();

        //If you print Date, you will get un formatted output
//        System.out.println("Today is : " + today);

        //HH converts hour in 24 hours format (0-23), day calculation
//		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            System.out.print("d1 --->" + d1 + "\n");
//			d2 = format.parse(dateStop);
            d2 = cal.getTime();
            System.out.print("d2 --->" + d2 + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
//            long aa= getDateDiff(d1,d2,TimeUnit.DAYS);
//            System.out.print(aa  + " Days " + "\n\n\n");
        //in milliseconds
        long diff = d2.getTime() - d1.getTime();
//			long diff = cal.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");


        if (diffDays == 0) {
            return true;
//            System.out.println("\n" + "true");
        } else {
            return false;
//            System.out.println("\n" + "false");
        }


    }

    public static boolean slot_time_diffrence(String slot_time) {

        int now = (new Date().getHours() * 60) + new Date().getMinutes();
        System.out.println(("\n now---> " + now));
        System.out.println(("\n substring1---> " + slot_time.substring(0, 2)));
        System.out.println("\n substring2---> " + (slot_time.substring(3, 4)));
        int slotStart = (Integer.parseInt(slot_time.substring(0, 2)) * 60) + Integer.parseInt(slot_time.substring(3, 4));
        System.out.println("\n slotStart----->" + slotStart);
        if ((slotStart - now) < 60) {
            return true;
//            System.out.println("\n" + "true");
        } else {
            return false;
//            System.out.println("\n" + "false");
        }

    }

    public static boolean Check_inr_date_diffrence(String inr_date) {

//        inr_date="2016-5-11";
        System.out.print("inr_date Check_inr_date_diffrence--->" + inr_date + "\n");
        Calendar cal = Calendar.getInstance();
//        String dateStop = "" + cal.getTime();
//        System.out.print("current date time  Check_inr_date_diffrence--->" + dateStop + "\n\n\n");

        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

        // This is how to get today's date in Java
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
//        d1 = cal.getTime();

        try {
            d1 = format.parse(modifiedDate);
            d2 = format.parse(inr_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("d1 Check_inr_date_diffrence --->" + d1 + "\n");
        System.out.print("d2 Check_inr_date_diffrence --->" + d2 + "\n");
        long diff = d2.getTime() - d1.getTime();
        System.out.print(diff + " diff, ");
        long diffDays = diff / (24 * 60 * 60 * 1000);
//            System.out.print(diffDays + " days, ");

        Log.e("diffDays", "Check_inr_date_diffrence ---@@@@->" + diffDays + "days");
        if (diffDays > 0) {
            return false;
//            System.out.println("\n" + "true");
        } else {
            return true;
//            System.out.println("\n" + "false");
        }


    }


    public static boolean confirm_btn_Check_inr_date_diffrence(String inr_date) {
        Calendar cal = Calendar.getInstance();
//        String dateStop = "" + cal.getTime();
//        System.out.print("current date time  Check_inr_date_diffrence--->" + dateStop + "\n\n\n");

        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

        // This is how to get today's date in Java
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
//        d1 = cal.getTime();
        try {
            d1 = format.parse(modifiedDate);
            d2 = format.parse(inr_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("d1 Check_inr_date_diffrence --->" + d1 + "\n");
        System.out.print("d2 Check_inr_date_diffrence --->" + d2 + "\n");
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
//            System.out.print(diffDays + " days, ");
        Log.e("diffDays", "Check_inr_date_diffrence ---->" + diffDays + "days");
        if (diffDays >= 0) {
            return true;
//            System.out.println("\n" + "true");
        } else {
            return false;
//            System.out.println("\n" + "false");
        }

    }


    public static boolean isTimeBetweenTwoTime(String argStartTime,
                                               String argEndTime, String argCurrentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        //
        if (argStartTime.matches(reg) && argEndTime.matches(reg)
                && argCurrentTime.matches(reg)) {
            boolean valid = false;
            // Start Time
            java.util.Date startTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argStartTime);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);

            // Current Time
            java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argCurrentTime);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentTime);

            // End Time
            java.util.Date endTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argEndTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            //
            if (currentTime.compareTo(endTime) < 0) {
                currentCalendar.add(Calendar.DATE, 1);
                currentTime = currentCalendar.getTime();
            }
            if (startTime.compareTo(endTime) < 0) {
                startCalendar.add(Calendar.DATE, 1);
                startTime = startCalendar.getTime();
            }
            //
            if (currentTime.before(startTime)) {
//                System.out.println(" Time is Lesser ");

                Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Time is Lesser ");

                valid = false;
            } else {

                if (currentTime.after(endTime)) {
                    endCalendar.add(Calendar.DATE, 1);
                    endTime = endCalendar.getTime();
                }

                long diff = (currentTime.getTime() / 60000) - (startTime.getTime() / 60000);
//                long diffMinutes = diff / (60 * 1000) % 60;
//                long diffHours = diff / (60 * 60 * 1000) % 24;
                Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Comparing , Start Time /n " + startTime);
                Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   End Time /n  " + endTime);
                Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Current Time /n " + currentTime);
                Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   diffMinutes " + diff);

//                System.out.println("Comparing , Start Time /n " + startTime);
//                System.out.println("Comparing , End Time /n " + endTime);
//                System.out.println("Comparing , Current Time /n " + currentTime);

                if (currentTime.before(endTime)) {
//                    System.out.println("RESULT, Time lies b/w");
                    Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Time lies b/w ");
                    valid = true;

                } else {
                    valid = false;
//                    System.out.println("RESULT, Time does not lies b/w");
                    Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Time does not lies b/w");
                }


            }
            return valid;

        } else {
            throw new IllegalArgumentException(
                    "Not a valid time, expecting HH:MM:SS format");
        }

    }


    public static long diffrence_between_giventime_and_pre_slot_time(String argStartTime,
                                                                     String argEndTime, String argCurrentTime)
            throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        //
        if (argStartTime.matches(reg) && argEndTime.matches(reg)
                && argCurrentTime.matches(reg)) {
//            boolean valid = false;
            long diff = 0;

            // Start Time

            java.util.Date startTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argStartTime);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);

            // Current Time
            java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argCurrentTime);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentTime);

            // End Time
            java.util.Date endTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argEndTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            //
            if (currentTime.compareTo(endTime) < 0) {
                currentCalendar.add(Calendar.DATE, 1);
                currentTime = currentCalendar.getTime();
            }

            if (startTime.compareTo(endTime) < 0) {
                startCalendar.add(Calendar.DATE, 1);
                startTime = startCalendar.getTime();
            }
            //
            if (currentTime.before(startTime)) {
//                System.out.println(" Time is Lesser ");

                Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Time is Lesser ");
//                valid = false;
            } else {
                if (currentTime.after(endTime)) {
                    endCalendar.add(Calendar.DATE, 1);
                    endTime = endCalendar.getTime();
                }

                diff = (currentTime.getTime() / 60000) - (startTime.getTime() / 60000);
//                long diffMinutes = diff / (60 * 1000) % 60;
//                long diffHours = diff / (60 * 60 * 1000) % 24;
                Log.e("isTimeBetweenTwoTime", "diffrence_between_giventime_and_pre_slot_time---->   Comparing , Start Time /n " + startTime);
                Log.e("isTimeBetweenTwoTime", "diffrence_between_giventime_and_pre_slot_time---->   End Time /n  " + endTime);
                Log.e("isTimeBetweenTwoTime", "diffrence_between_giventime_and_pre_slot_time---->   Current Time /n " + currentTime);
                Log.e("isTimeBetweenTwoTime", "diffrence_between_giventime_and_pre_slot_time---->   diffMinutes " + diff);
//                System.out.println("Comparing , Start Time /n " + startTime);
//                System.out.println("Comparing , End Time /n " + endTime);
//                System.out.println("Comparing , Current Time /n " + currentTime);
//                if (currentTime.before(endTime)) {
////                    System.out.println("RESULT, Time lies b/w");
//                    Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Time lies b/w ");
//
//                    valid = true;
//
//                } else {
//                    valid = false;
////                    System.out.println("RESULT, Time does not lies b/w");
//                    Log.e("isTimeBetweenTwoTime", "isTimeBetweenTwoTime---->   Time does not lies b/w");
//                }
            }
            return diff;

        } else {
            throw new IllegalArgumentException(
                    "Not a valid time, expecting HH:MM:SS format");
        }

    }


//    public static long get_time_diffrence(String slot_time, String given_time) {
//        Log.e("get_time_diffrence  ", "??? get_time_diffrence--->" + slot_time);
//        Log.e("get_time_diffrence  ", "??? get_time_diffrence--->" + given_time);
//        long diffMinutes = 0;
//
//        try {
//
//            System.out.println(("\n substring1---> " + slot_time.substring(0, 2)));
//            int hour = Integer.parseInt(slot_time.substring(0, 2));
//            String string1 = slot_time;
//
//            if (hour >= 12 || hour < 24) {
//                string1 = string1 + " pm";
//            } else {
//                string1 = string1 + " am";
//
//            }
//
//            // String string1 = "20:00:00";
//            Date time1 = new SimpleDateFormat("hh:mm:ss aa").parse(string1);
//            Calendar calendar1 = Calendar.getInstance();
//            calendar1.setTime(time1);
//
//
//            System.out.println(("\n substring2---> " + given_time.substring(0, 2)));
//            int given_timehour = Integer.parseInt(slot_time.substring(0, 2));
//            String string2 = given_time;
//
//            if (given_timehour >= 12 || given_timehour < 24) {
//                string2 = string2 + " pm";
//            } else {
//                string2 = string2 + " am";
//
//            }
//
//            // String string2 = "03:00:00";
//            Date time2 = new SimpleDateFormat("hh:mm:ss aa").parse(string2);
//            Calendar calendar2 = Calendar.getInstance();
//            calendar2.setTime(time2);
//            // calendar2.add(Calendar.DATE, 1);
//
//            Date x = calendar1.getTime();
//            Date xy = calendar2.getTime();
//            long diff = xy.getTime() - x.getTime();
//            diffMinutes = diff / (60 * 1000);
//            System.out.println("diff Minutes" + diffMinutes);
////			float diffHours = diffMinutes / 60;
////			System.out.println("diff hours" + diffHours);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        Log.e("get_time_diffrence  ", "??? get_time_diffrence--->" + diffMinutes);
//
//        return diffMinutes;
//    }


    public static long get_time_diffrence_2(String slot_time, String given_time) {

        Log.e("get_time_diffrence  ", "??? get_time_diffrence--->" + slot_time);
        Log.e("get_time_diffrence  ", "??? get_time_diffrence--->" + given_time);
        long diffMinutes = 0;
        try {

//			System.out.println(("\n substring1---> " + slot_time.substring(0, 2)));
//			int hour = Integer.parseInt(slot_time.substring(0, 2));
            String string1 = slot_time;
//
//			if (hour > 12 && 24 > hour) {
//				string1 = string1 + " pm";
//			} else {
//				string1 = string1 + " am";
//
//			}
            System.out.println(("\n substring1---> " + string1));

            // String string1 = "20:00:00";
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);


//			System.out.println(("\n substring2---> " + given_time.substring(0, 2)));
//			int given_timehour = Integer.parseInt(slot_time.substring(0, 2));
            String string2 = given_time;
//
//			if (given_timehour > 12 && 24 > given_timehour) {
//				string2 = string2 + " pm";
//			} else {
//				string2 = string2 + " am";
//
//			}
            System.out.println(("\n substring2---> " + string2));
            // String string2 = "03:00:00";
            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            // calendar2.add(Calendar.DATE, 1);

            Date x = calendar1.getTime();
            Date xy = calendar2.getTime();
            long diff = xy.getTime() - x.getTime();
            diffMinutes = diff / (60 * 1000);
            System.out.println("diff Minutes" + diffMinutes);
            // float diffHours = diffMinutes / 60;
            // System.out.println("diff hours" + diffHours);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return diffMinutes;
    }


}





