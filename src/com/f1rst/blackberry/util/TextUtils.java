package com.f1rst.blackberry.util;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.util.MathUtilities;

/**
 * 
 * @author ivaylo
 */
public class TextUtils {

	public static boolean isEmpty(String a) {
		if (a == null)
			return true;

		if (a.length() == 0)
			return true;

		return false;
	}

	/**
	 * format double numbers with len symbols after periodic char without any
	 * rounding 2.34, with 5 len will become 2.34000 2.3455555, with 5 len will
	 * become 2.34555
	 */
	public static String formatDouble(double f, int len) {
		String s = Double.toString(f);
		int i = s.indexOf(".");

		String temp = "";
		if (i < s.length() - 1)
			temp = s.substring(i + 1, s.length());

		if (temp.length() == len)
			return s;
		else {
			if (len < temp.length() && len > -1)
				return s.substring(0,
						(s.length() - Math.abs(temp.length() - len)));
			else
				// adding zeroes
				for (int j = 0; j < len - temp.length(); j++) {
					s += "0";
				}
			return s;
		}
	}

	/**
	 * format the given input as currency, putting ' ' if the input is more than
	 * 3 digits
	 * 
	 * @param input
	 *            currency to format
	 * @return
	 */
	public static String formatCurrency(double input) {

		int dollars;
		int centsI;

		char delimiter = ',';
		String negative = "";

		StringBuffer output;
		StringBuffer cents;

		if (input < 0) {
			input = Math.abs(input);
			negative = "-";
		}

		output = new StringBuffer();
		dollars = (int) input;

		String dol = Integer.toString(dollars);
		int i = 0;
		char c[] = dol.toCharArray();

		for (int j = c.length - 1; j >= 0; j--) {
			char d = c[j];
			output.append(d);
			i++;
			if (i % 3 == 0 && dol.length() > 3 && j != 0) {
				i = 0;
				output.append(delimiter);
			}
		}
		output.reverse();

		// output.append(dollars);
		double res = (input - dollars);
		centsI = (int) ((res) * 100.001);

		if (centsI == 0) {
			return negative + output.toString() + ".00";
		}

		output.append('.');
		cents = new StringBuffer(String.valueOf(centsI));

		while (cents.length() < 2) {
			// bug. Adding 0 should be before the cent value
			// cents.append('0');

			cents.insert(0, "0");
		}

		if (cents.length() > 2) {
			cents.toString().substring(0, 2);
		}

		output.append(cents);
		return negative + output.toString();
	}

	public static double columnRound(double arg, int places) {
		double tmp = (double) arg * (MathUtilities.pow(10, places));
		double tmp1 = Math.floor(tmp + 0.5);
		double tmp2 = (double) tmp1 / (MathUtilities.pow(10, places));
		return tmp2;
	}

	  
	  //format the 24/10/2012 to date
	  public static Date formatDate(String p) {
		  // Logger.log("formatDate: " + p);
		   //public static DateFormat myDF = new SimpleDateFormat("MM/dd/yy");
		  
//		  public static DateFormat myDF = new SimpleDateFormat("dd/MM/yyyy");
		  
		  try {	
	           String workstring = p;
	           String daystr= workstring.substring(0,2);
	           String monthstr = workstring.substring(3,5);
	           String yearstr = workstring.substring(6,10);
	           int monthnum = Integer.parseInt(monthstr);
	           int daynum = Integer.parseInt(daystr);
	           int yearnum = Integer.parseInt(yearstr);
	          
	           if (monthnum>0){
	        	   monthnum = monthnum -1;
	           }
	           // Logger.log("Day: " + daystr + " month: " + monthnum + " year: " + yearstr);
	           
	           Calendar getCal = Calendar.getInstance();
	           getCal.set(Calendar.MONTH, monthnum );
	           getCal.set(Calendar.DAY_OF_MONTH, daynum);
	           getCal.set(Calendar.YEAR, yearnum);
	           Date thedate = getCal.getTime();
	           //long workdate = thedate.getTime();
	           
	           return thedate;
		  } catch (NumberFormatException n) {			 
		  } catch (NullPointerException e) {
		  } catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		  
		  return null; 
	  }
}
