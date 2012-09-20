package com.f1rst.blackberry.util;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.i18n.SimpleDateFormat;

/**
 *
 * @author ivaylo
 */
public class DateUtilExtendede extends com.f1rst.blackberry.util.DateUtil {

    /**
     * @param date now
     * @return day in the week
     */
    public final static String getDayInAWeek(long date) {
         if(date > 0) {
            //date = DateUtil.convertToLocalTime(date);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String tim = sdf.format(new Date(date));
            return tim;
        } else return "---";
    }

    /**
     * @param date now
     * @return day in the week like TUE
     */
    public final static String getDayInAWeekWWW(long date) {
         if(date > 0) {
            //date = DateUtil.convertToLocalTime(date);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE");
            String tim = sdf.format(new Date(date));
            return tim;
        } else return "---";
    }

    /**
     * @param date - in GMT time zone format //"\/Date(1288069200000)\/"
     * @return the a date string 1288069200000 in converted to local time long variable
     */
    public final static long getDateAsLong(String date) {

        String d = date;
        long l = 0;
        if (d != null && d.length() > 10) {
            d = date.substring(6, date.length() - 2);
            try {
                l = Long.parseLong(d);

//                if(l > 0) {
//                    l = DateUtil.convertToLocalTime(l);
//                    return l;
//                }
                return l;
            } catch(NumberFormatException n){
                return l;
            }
        }
        return l;
    }
    
    public final static String getDateEMD(long l) { 
            try {
  
                if(l > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("E MM/d");
                    String tim = sdf.format(new Date(l));
                    return tim;
                }
            } catch(NumberFormatException n){
                return "---";
            }
        return "---";
        
    }

    public final static String getDateMMDYYYY(long l) {

            try {

                if(l > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
                    String tim = sdf.format(new Date(l));
                    return tim;
                }
            } catch (Throwable t) {
                return "---";
            }

          return "---";
    }

    public final static String getFriendlyDate(long l) {

            try {

                if(l > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy HH:mm");
                    String tim = sdf.format(new Date(l));
                    return tim;
                }

            } catch (Throwable t) {
                return "---";
            }

          return "---";
    }

    public final static String getDateAMPM(long l) {

            try {
                if(l > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy h:mm");
                    String tim = sdf.format(new Date(l));
                    return tim;
                }
            } catch (Throwable t) {
                return "---";
            }

          return "---";
    }

    public final static String getDate(long l) {

            try {
                if(l > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("E MM/d HH:mm");
                    String tim = sdf.format(new Date(l));
                    return tim;
                }
            } catch (Throwable t) {
                return "---";
            }
          return "---";
    }

    public final static String getDateS(long l) {

        try {

            if(l > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("E M/d HH:mm");
                String tim = sdf.format(new Date(l));
                return tim;
            }
        } catch (Throwable t) {
            return "---";
        }
            
        return "---";
    }

    public final static String getDate_MD(long l) {

        if(l > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
            String tim = sdf.format(new Date(l));
            return tim;
        }

        try {
            Calendar c = Calendar.getInstance();
//            c.setTimeInMillis(l); j2se
            c.setTime(new Date(l));//j2me

            return String.valueOf(c.get(Calendar.MONTH)+1)
                    + "/" + String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        } catch (Throwable t) {
            return "---";
        }
    }

}
