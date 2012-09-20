package com.f1rst.blackberry.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.f1rst.blackberry.log.Logger;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.util.DateTimeUtilities;

/**
 *
 * @author ivaylo
 */
public class DateUtil {

    public static String formatDateMMDDYYYY(long date) {
        //mm/dd/yyyy
        if (date > 0) {
            //the bb java way :)
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//            String tim = sdf.format(new Date(l.getTimestamp()));
            String tim = sdf.format(new Date(date));
            return tim;
        } else {
            return "---";
        }

//        try {
//            Calendar c = Calendar.getInstance();
////            c.setTimeInMillis(l); j2se
//            c.setTime(new Date(date));//j2me
//
//            return to2Digits(String.valueOf(c.get(Calendar.MONTH)+1))
//                    + "/" + to2Digits(String.valueOf( (c.get(Calendar.DAY_OF_MONTH)) ))
//                    + "/" + to2Digits(String.valueOf(c.get(Calendar.YEAR)));
//        } catch (Throwable t) {
//            return "---";
//        }
    }

    public static String getDateFriendly() {
        //10/28/2010 15:42
        String d = "";
        try {
            Calendar c = Calendar.getInstance();
//            c.setTimeInMillis(l); j2se
//            c.setTime(new Date(l));//j2me
//            System.out.println(c.toString());
//            System.out.println(c.get(Calendar.HOUR));
//            System.out.println(c.get(Calendar.MINUTE));
//            System.out.println(c.get(Calendar.SECOND));
//
//            System.out.println("---");
//            System.out.println(c.get(Calendar.YEAR));
//            System.out.println(c.get(Calendar.MONTH));
//            System.out.println(c.get(Calendar.DAY_OF_MONTH));
//
//            return String.valueOf(c.get(Calendar.DAY_OF_MONTH))
//                    + "/" + String.valueOf(c.get(Calendar.MONTH)+1)
//                    + "/" + String.valueOf(c.get(Calendar.YEAR))
//                    + " " + String.valueOf(c.get(Calendar.HOUR))
//                    + ":" + String.valueOf(c.get(Calendar.MINUTE));

            //the bb java way :)
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            String tim = sdf.format(new Date());
            return tim;
        } catch (Throwable t) {
            return "---";
        }       
    }

    /**
     *
     * @param date in the folowing format "MM/dd/yyyy HH:mm"
     * @return
     */
    public static String getDateFriendly(long date) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            String tim = sdf.format(new Date(date));
            return tim;
        } catch (Throwable t) {
            return "---";
        }
    }

    private static String to2Digits(String s){
        if(s == null)
            return "--";
        if(s.length()==1)
            return "0" + s;
        else if(s.length()==2)
            return s;
        else return s;
    }

    public static long convertToLocalTime(long GMTTime) {
//        Logger.log("convertToLocalTime");

//        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//        c.setTime(new Date(System.currentTimeMillis())); //now
//        Logger.log(getDateFriendly(c.getTime().getTime()));

//        Logger.log("convertToLocalTime");
//        Logger.log("GMTTime: " + String.valueOf(GMTTime));
//        Logger.log("gmt cal: " + String.valueOf(GMTCalendar.getTime().getTime()));
//        Logger.log("local cal: " + String.valueOf(localCalendar.getTime().getTime()));
//        Logger.log("local cal: " + String.valueOf(localCalendar.getTime().getTime()));
//        Logger.log("mod gmt cal: " + String.valueOf(GMTCalendar.getTime().getTime()));

        Calendar GMTCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Calendar localCalendar = Calendar.getInstance();
        Date workDate = new Date();

        Calendar rightnow = Calendar.getInstance(TimeZone.getDefault());
        rightnow.setTime(new Date(GMTTime));
        final long offsett = TimeZone.getDefault().getOffset(1, rightnow.get(Calendar.YEAR), rightnow.get(Calendar.MONTH), rightnow.get(Calendar.DAY_OF_MONTH), rightnow.get(Calendar.DAY_OF_WEEK), rightnow.get(Calendar.MILLISECOND));
        GMTTime += offsett;
        workDate.setTime(GMTTime);


        GMTCalendar.setTime(workDate);

        int [] calendarFields = null;
        long returnTime = -1;

        calendarFields = DateTimeUtilities.getCalendarFields(GMTCalendar, calendarFields);

        DateTimeUtilities.setCalendarFields(localCalendar, calendarFields);
        returnTime = localCalendar.getTime().getTime();
//        Logger.log("return: " + String.valueOf(returnTime));

        return returnTime;
    }

    public static long convertToGMTTime(long localTime/*, final MainScreen m*/) {
        Logger.log("convertToGMTTime");
        Calendar GMTCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        Calendar localCalendar = Calendar.getInstance();
        Date workDate = new Date();


//        Calendar rightnow = Calendar.getInstance(TimeZone.getDefault());
//        rightnow.setTime(new Date(localTime));
//        final long offsett = TimeZone.getDefault().getOffset(1, rightnow.get(Calendar.YEAR), rightnow.get(Calendar.MONTH), rightnow.get(Calendar.DAY_OF_MONTH), rightnow.get(Calendar.DAY_OF_WEEK), rightnow.get(Calendar.MILLISECOND));
//        long offsett = TimeZone.getDefault().getOffset(rightnow.getTime().getTime());


//        UiApplication.getUiApplication().invokeLater(new Runnable(){
//            public void run(){
//                m.add(new EditField("Offset:", String.valueOf(offsett/1000/60/60)));
//            }
//        });

//        Logger.log("offset: " + String.valueOf(offsett));
        //localTime -= offsett;
        workDate.setTime(localTime);

        int [] calendarFields = null;
        long returnTime = -1;

        GMTCalendar.setTime(workDate);

        calendarFields = DateTimeUtilities.getCalendarFields(GMTCalendar, calendarFields);
        DateTimeUtilities.setCalendarFields(localCalendar, calendarFields);
        returnTime = localCalendar.getTime().getTime();

        return returnTime;

    }
}
