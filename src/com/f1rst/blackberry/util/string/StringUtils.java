/**
 * Copyright (c) E.Y. Baskoro, Research In Motion Limited.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * This License shall be included in all copies or substantial 
 * portions of the Software.
 * 
 * The name(s) of the above copyright holders shall not be used 
 * in advertising or otherwise to promote the sale, use or other 
 * dealings in this Software without prior written authorization.
 * 
 */
package com.f1rst.blackberry.util.string;

import java.util.Vector;

public class StringUtils {

	public static String[] split(String str, char sep, int maxNum) {
		if ((str == null) || (str.length() == 0)) {
			return new String[0];
		}

		Vector results = maxNum == 0 ? new Vector() : new Vector(maxNum);

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == sep) {
				if (maxNum != 0) {
					if ((results.size() + 1) == maxNum) {
						for (; i < str.length(); i++) {
							buf.append(str.charAt(i));
						}
						break;
					}
				}

				results.addElement(buf.toString());
				buf.setLength(0);
			} else {
				buf.append(c);
			}
		}

		if (buf.length() > 0) {
			results.addElement(buf.toString());
		}

		return toStringArray(results);
	}

	public static String[] toStringArray(Vector strings) {
		String[] result = new String[strings.size()];
		for (int i = 0; i < strings.size(); i++) {
			result[i] = strings.elementAt(i).toString();
		}
		return result;
	}

	public static String chomp(String inStr) {
		if ((inStr == null) || "".equals(inStr)) {
			return inStr;
		}

		char lastChar = inStr.charAt(inStr.length() - 1);
		if (lastChar == 13) {
			return inStr.substring(0, inStr.length() - 1);
		} else if (lastChar == 10) {
			String tmp = inStr.substring(0, inStr.length() - 1);
			if ("".equals(tmp)) {
				return tmp;
			}
			lastChar = tmp.charAt(tmp.length() - 1);
			if (lastChar == 13) {
				return tmp.substring(0, tmp.length() - 1);
			} else {
				return tmp;
			}
		} else {
			return inStr;
		}
	}

	public static String parentOf(String inStr) {
		String result = null;

		if ((inStr != null) && !inStr.trim().equals("")) {
			inStr = inStr.trim();
			int index = inStr.lastIndexOf('.');
			if (index != -1) {
				result = inStr.substring(0, index);
			}
		}

		return result;
	}
	
//	http://supportforums.blackberry.com/t5/Java-Development/String-Manipulation-split-replace-replaceAll/ta-p/620038
	public String[] split(String strString, String strDelimiter)
	{
		int iOccurrences = 0;
		int iIndexOfInnerString = 0;
		int iIndexOfDelimiter = 0;
		int iCounter = 0;

		// Check for null input strings.
		if (strString == null)
		{
			throw new NullPointerException("Input string cannot be null.");
		}
		// Check for null or empty delimiter
		// strings.
		if (strDelimiter.length() <= 0 || strDelimiter == null)
		{
			throw new NullPointerException("Delimeter cannot be null or empty.");
		}

		// If strString begins with delimiter
		// then remove it in
		// order
		// to comply with the desired format.

		if (strString.startsWith(strDelimiter))
		{
			strString = strString.substring(strDelimiter.length());
		}

		// If strString does not end with the
		// delimiter then add it
		// to the string in order to comply with
		// the desired format.
		if (!strString.endsWith(strDelimiter))
		{
			strString += strDelimiter;
		}

		// Count occurrences of the delimiter in
		// the string.
		// Occurrences should be the same amount
		// of inner strings.
		while((iIndexOfDelimiter= strString.indexOf(strDelimiter,iIndexOfInnerString))!=-1)
		{
			iOccurrences += 1;
			iIndexOfInnerString = iIndexOfDelimiter + strDelimiter.length();
		}

		// Declare the array with the correct
		// size.
		String[] strArray = new String[iOccurrences];

		// Reset the indices.
		iIndexOfInnerString = 0;
		iIndexOfDelimiter = 0;

		// Walk across the string again and this
		// time add the
		// strings to the array.
		while((iIndexOfDelimiter= strString.indexOf(strDelimiter,iIndexOfInnerString))!=-1)
		{

			// Add string to
			// array.
			strArray[iCounter] = strString.substring(iIndexOfInnerString, iIndexOfDelimiter);

			// Increment the
			// index to the next
			// character after
			// the next
			// delimiter.
			iIndexOfInnerString = iIndexOfDelimiter + strDelimiter.length();

			// Inc the counter.
			iCounter += 1;
		}
            return strArray;
	}
	
//	http://supportforums.blackberry.com/t5/Java-Development/String-Manipulation-split-replace-replaceAll/ta-p/620038
	public String replace(String source, String pattern, String replacement)
	{	
	
		//If source is null then Stop
		//and return empty String.
		if (source == null)
		{
			return "";
		}

		StringBuffer sb = new StringBuffer();
		//Intialize Index to -1
		//to check against it later 
		int idx = -1;
		//Intialize pattern Index
		int patIdx = 0;
		//Search source from 0 to first occurrence of pattern
		//Set Idx equal to index at which pattern is found.
		idx = source.indexOf(pattern, patIdx);
		//If Pattern is found, idx will not be -1 anymore.
		if (idx != -1)
		{
			//append all the string in source till the pattern starts.
			sb.append(source.substring(patIdx, idx));
			//append replacement of the pattern.
			sb.append(replacement);
			//Increase the value of patIdx
			//till the end of the pattern
			patIdx = idx + pattern.length();
			//Append remaining string to the String Buffer.
			sb.append(source.substring(patIdx));
		}
		//Return StringBuffer as a String

                if ( sb.length() == 0)
                {
                    return source;
                }
                else
                {
                    return sb.toString();
                }
	}
	
	//http://supportforums.blackberry.com/t5/Java-Development/String-Manipulation-split-replace-replaceAll/ta-p/620038
	public static String replaceAll(String source, String pattern, String replacement)
	{    
	    //If source is null then Stop
	    //and retutn empty String.
	    if (source == null)
	    {
	        return "";
	    }

	    StringBuffer sb = new StringBuffer();
	    //Intialize Index to -1
	    //to check agaist it later 
	    int idx = 0;
	    //Search source from 0 to first occurrence of pattern
	    //Set Idx equal to index at which pattern is found.

	    String workingSource = source;
	    
	    //Iterate for the Pattern till idx is not be -1.
	    while ((idx = workingSource.indexOf(pattern, idx)) != -1)
	    {
	        //append all the string in source till the pattern starts.
	        sb.append(workingSource.substring(0, idx));
	        //append replacement of the pattern.
	        sb.append(replacement);
	        //Append remaining string to the String Buffer.
	        sb.append(workingSource.substring(idx + pattern.length()));
	        
	        //Store the updated String and check again.
	        workingSource = sb.toString();
	        
	        //Reset the StringBuffer.
	        sb.delete(0, sb.length());
	        
	        //Move the index ahead.
	        idx += replacement.length();
	    }

	    return workingSource;
	}
	
	public static String replaceAllF1rst(String source, String pattern, String replacement)
	{    
	    //If source is null then Stop
	    //and retutn empty String.
	    if (source == null)
	    {
	        return "";
	    }

	    StringBuffer sb = new StringBuffer();
	    //Intialize Index to -1
	    //to check agaist it later 
	    int idx = 0;
	    //Search source from 0 to first occurrence of pattern
	    //Set Idx equal to index at which pattern is found.

	    String workingSource = source;
	    	    
	    String lt = "<";
	    String gt = ">";
	    
	    //Iterate for the Pattern till idx is not be -1.
//	    while ((idx = workingSource.indexOf(lt, idx)) != -1)
//	    {
//	        //append all the string in source till the pattern starts.
//	        sb.append(workingSource.substring(0, idx));
//	        //append replacement of the pattern.
//	        //sb.append(replacement);
//	        //Append remaining string to the String Buffer.
//	        sb.append(workingSource.substring(idx + pattern.length()));
//	        
//	        //Store the updated String and check again.
//	        workingSource = sb.toString();
//	        
//	        //Reset the StringBuffer.
//	        sb.delete(0, sb.length());
//	        
//	        //Move the index ahead.
//	        idx += replacement.length();
//	    }
	    
//	    //Iterate for the Pattern till idx is not be -1.
//	    while ((idx = workingSource.indexOf(pattern, idx)) != -1)
//	    {
//	        //append all the string in source till the pattern starts.
//	        sb.append(workingSource.substring(0, idx));
//	        //append replacement of the pattern.
//	        sb.append(replacement);
//	        //Append remaining string to the String Buffer.
//	        sb.append(workingSource.substring(idx + pattern.length()));
//	        
//	        //Store the updated String and check again.
//	        workingSource = sb.toString();
//	        
//	        //Reset the StringBuffer.
//	        sb.delete(0, sb.length());
//	        
//	        //Move the index ahead.
//	        idx += replacement.length();
//	    }

	    return workingSource;
	}

}