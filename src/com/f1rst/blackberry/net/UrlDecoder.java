package com.f1rst.blackberry.net;
//
///*
// *  Licensed to the Apache Software Foundation (ASF) under one or more
// *  contributor license agreements.  See the NOTICE file distributed with
// *  this work for additional information regarding copyright ownership.
// *  The ASF licenses this file to You under the Apache License, Version 2.0
// *  (the "License"); you may not use this file except in compliance with
// *  the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// */
//
//
//import java.io.UnsupportedEncodingException;
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.nio.charset.Charset;
//import java.nio.charset.IllegalCharsetNameException;
//import java.nio.charset.UnsupportedCharsetException;
//
////import org.apache.harmony.luni.internal.nls.Messages;
//
///**
// * This class is used to decode a string which is encoded in the {@code
// * application/x-www-form-urlencoded} MIME content type.
// */
//public class UrlDecoder {
//
//    static Charset defaultCharset;
//
//    /**
//     * Decodes the argument which is assumed to be encoded in the {@code
//     * x-www-form-urlencoded} MIME content type.
//     * <p>
//     *'+' will be converted to space, '%' and two following hex digit
//     * characters are converted to the equivalent byte value. All other
//     * characters are passed through unmodified. For example "A+B+C %24%25" ->
//     * "A B C $%".
//     *
//     * @param s
//     *            the encoded string.
//     * @return the decoded clear-text representation of the given string.
//     * @deprecated use {@link #decode(String, String)} instead.
//     */
////    @Deprecated
//    public static String decode(String s) {
//
//        if (defaultCharset == null) {
//            try {
//                defaultCharset = Charset.forName(
//                        System.getProperty("file.encoding")); //$NON-NLS-1$
//            } catch (IllegalCharsetNameException e) {
//                // Ignored
//            } catch (UnsupportedCharsetException e) {
//                // Ignored
//            }
//
//            if (defaultCharset == null) {
//                defaultCharset = Charset.forName("ISO-8859-1"); //$NON-NLS-1$
//            }
//        }
//        return decode(s, defaultCharset);
//    }
//
//    /**
//     * Decodes the argument which is assumed to be encoded in the {@code
//     * x-www-form-urlencoded} MIME content type using the specified encoding
//     * scheme.
//     * <p>
//     *'+' will be converted to space, '%' and two following hex digit
//     * characters are converted to the equivalent byte value. All other
//     * characters are passed through unmodified. For example "A+B+C %24%25" ->
//     * "A B C $%".
//     *
//     * @param s
//     *            the encoded string.
//     * @param enc
//     *            the encoding scheme to be used.
//     * @return the decoded clear-text representation of the given string.
//     * @throws UnsupportedEncodingException
//     *             if the specified encoding scheme is invalid.
//     */
//    public static String decode(String s, String enc)
//            throws UnsupportedEncodingException {
//
//        if (enc == null) {
//            throw new NullPointerException();
//        }
//
//        // If the given encoding is an empty string throw an exception.
////        if (enc.length() == 0) {
////            throw new UnsupportedEncodingException(
////                    // luni.99=Invalid parameter - {0}
////                    //Messages.getString("luni.99", "enc")
////                    ); //$NON-NLS-1$ //$NON-NLS-2$
////        }
//
//        if (s.indexOf('%') == -1) {
//            if (s.indexOf('+') == -1)
//                return s;
//            char str[] = s.toCharArray();
//            for (int i = 0; i < str.length; i++) {
//                if (str[i] == '+')
//                    str[i] = ' ';
//            }
//            return new String(str);
//        }
//
//        Charset charset = null;
//        try {
//            charset = Charset.forName(enc);
//        } catch (IllegalCharsetNameException e) {
//            throw (UnsupportedEncodingException) (new UnsupportedEncodingException(
//                    enc).initCause(e));
//        } catch (UnsupportedCharsetException e) {
//            throw (UnsupportedEncodingException) (new UnsupportedEncodingException(
//                    enc).initCause(e));
//        }
//
//        return decode(s, charset);
//    }
//
//    private static String decode(String s, Charset charset) {
//
//        char str_buf[] = new char[s.length()];
//        byte buf[] = new byte[s.length() / 3];
//        int buf_len = 0;
//
//        for (int i = 0; i < s.length();) {
//            char c = s.charAt(i);
//            if (c == '+') {
//                str_buf[buf_len] = ' ';
//            } else if (c == '%') {
//
//                int len = 0;
//                do {
//                    if (i + 2 >= s.length()) {
//                        throw new IllegalArgumentException(
//                                // luni.80=Incomplete % sequence at\: {0}
//                                //Messages.getString("luni.80", i)
//                                ); //$NON-NLS-1$
//                    }
//                    int d1 = Character.digit(s.charAt(i + 1), 16);
//                    int d2 = Character.digit(s.charAt(i + 2), 16);
//                    if (d1 == -1 || d2 == -1) {
//                        throw new IllegalArgumentException(
//                                // luni.81=Invalid % sequence ({0}) at\: {1}
////                                Messages.getString(
////                                        "luni.81", //$NON-NLS-1$
////                                        s.substring(i, i + 3),
////                                        String.valueOf(i))
//                                        );
//                    }
//                    buf[len++] = (byte) ((d1 << 4) + d2);
//                    i += 3;
//                } while (i < s.length() && s.charAt(i) == '%');
//
//                CharBuffer cb = charset.decode(ByteBuffer.wrap(buf, 0, len));
//                len = cb.length();
//                System.arraycopy(cb.array(), 0, str_buf, buf_len, len);
//                buf_len += len;
//                continue;
//            } else {
//                str_buf[buf_len] = c;
//            }
//            i++;
//            buf_len++;
//        }
//        return new String(str_buf, 0, buf_len);
//    }
//}

    /*
  * Copyright 1998-2006 Sun Microsystems, Inc.  All Rights Reserved.
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  *
  * This code is free software; you can redistribute it and/or modify it
  * under the terms of the GNU General Public License version 2 only, as
  * published by the Free Software Foundation.  Sun designates this
  * particular file as subject to the "Classpath" exception as provided
  * by Sun in the LICENSE file that accompanied this code.
  *
  * This code is distributed in the hope that it will be useful, but WITHOUT
  * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
  * version 2 for more details (a copy is included in the LICENSE file that
  * accompanied this code).
  *
  * You should have received a copy of the GNU General Public License version
  * 2 along with this work; if not, write to the Free Software Foundation,
  * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
  *
  * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
  * CA 95054 USA or visit www.sun.com if you need additional information or
  * have any questions.
  */

// package org.not.java.net;

 	import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;


 /**
  * Utility class for HTML form decoding. This class contains static methods
  * for decoding a String from the <CODE>application/x-www-form-urlencoded</CODE>
  * MIME format.
  * <p>
  * The conversion process is the reverse of that used by the URLEncoder class. It is assumed
  * that all characters in the encoded string are one of the following:
  * &quot;<code>a</code>&quot; through &quot;<code>z</code>&quot;,
  * &quot;<code>A</code>&quot; through &quot;<code>Z</code>&quot;,
  * &quot;<code>0</code>&quot; through &quot;<code>9</code>&quot;, and
  * &quot;<code>-</code>&quot;, &quot;<code>_</code>&quot;,
  * &quot;<code>.</code>&quot;, and &quot;<code>*</code>&quot;. The
  * character &quot;<code>%</code>&quot; is allowed but is interpreted
  * as the start of a special escaped sequence.
  * <p>
  * The following rules are applied in the conversion:
  * <p>
  * <ul>
  * <li>The alphanumeric characters &quot;<code>a</code>&quot; through
  *     &quot;<code>z</code>&quot;, &quot;<code>A</code>&quot; through
  *     &quot;<code>Z</code>&quot; and &quot;<code>0</code>&quot;
  *     through &quot;<code>9</code>&quot; remain the same.
  * <li>The special characters &quot;<code>.</code>&quot;,
  *     &quot;<code>-</code>&quot;, &quot;<code>*</code>&quot;, and
  *     &quot;<code>_</code>&quot; remain the same.
  * <li>The plus sign &quot;<code>+</code>&quot; is converted into a
  *     space character &quot;<code>&nbsp;</code>&quot; .
  * <li>A sequence of the form "<code>%<i>xy</i></code>" will be
  *     treated as representing a byte where <i>xy</i> is the two-digit
  *     hexadecimal representation of the 8 bits. Then, all substrings
  *     that contain one or more of these byte sequences consecutively
  *     will be replaced by the character(s) whose encoding would result
  *     in those consecutive bytes.
  *     The encoding scheme used to decode these characters may be specified,
  *     or if unspecified, the default encoding of the platform will be used.
  * </ul>
  * <p>
  * There are two possible ways in which this decoder could deal with
  * illegal strings.  It could either leave illegal characters alone or
  * it could throw an <tt>{@link java.lang.IllegalArgumentException}</tt>.
  * Which approach the decoder takes is left to the
  * implementation.
  *
  * @author  Mark Chamness
  * @author  Michael McCloskey
  * @since   1.2
  */

 public class UrlDecoder
 {

     /**
      * Decodes a <code>application/x-www-form-urlencoded</code> string using a specific
      * encoding scheme.
      * The supplied encoding is used to determine
      * what characters are represented by any consecutive sequences of the
      * form "<code>%<i>xy</i></code>".
      * <p>
      * <em><strong>Note:</strong> The <a href=
      * "http://www.w3.org/TR/html40/appendix/notes.html#non-ascii-chars">
      * World Wide Web Consortium Recommendation</a> states that
      * UTF-8 should be used. Not doing so may introduce
      * incompatibilites.</em>
      *
      * @param s the <code>String</code> to decode
      * @param enc   The name of a supported
      *    <a href="../lang/package-summary.html#charenc">character
      *    encoding</a>.
      * @return the newly decoded <code>String</code>
      * @exception  UnsupportedEncodingException
      *             If character encoding needs to be consulted, but
      *             named character encoding is not supported
      * @see URLEncoder#encode(java.lang.String, java.lang.String)
      * @since 1.4
      */
     public static String decode(String s, String enc)
         throws UnsupportedEncodingException{

         boolean needToChange = false;
         int numChars = s.length();
         StringBuffer sb = new StringBuffer(numChars > 500 ? numChars / 2 : numChars);
         int i = 0;

         if (enc.length() == 0) {
             throw new UnsupportedEncodingException ("URLDecoder: empty string enc parameter");
         }

         char c;
         byte[] bytes = null;
         while (i < numChars) {
             c = s.charAt(i);
             switch (c) {
             case '+':
                 sb.append(' ');
                 i++;
                 needToChange = true;
                 break;
             case '%':
                 /*
                  * Starting with this instance of %, process all
                  * consecutive substrings of the form %xy. Each
                  * substring %xy will yield a byte. Convert all
                  * consecutive  bytes obtained this way to whatever
                  * character(s) they represent in the provided
                  * encoding.
                  */

                 try {

                     // (numChars-i)/3 is an upper bound for the number
                     // of remaining bytes
                     if (bytes == null)
                         bytes = new byte[(numChars-i)/3];
                     int pos = 0;

                     while ( ((i+2) < numChars) &&
                             (c=='%')) {
                         int v = Integer.parseInt(s.substring(i+1,i+3),16);
                         if (v < 0)
                             throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - negative value");
                         bytes[pos++] = (byte) v;
                         i+= 3;
                         if (i < numChars)
                             c = s.charAt(i);
                     }

                     // A trailing, incomplete byte encoding such as
                     // "%x" will cause an exception to be thrown

                     if ((i < numChars) && (c=='%'))
                         throw new IllegalArgumentException(
                          "URLDecoder: Incomplete trailing escape (%) pattern");

                     sb.append(new String(bytes, 0, pos, enc));
                 } catch (NumberFormatException e) {
                     throw new IllegalArgumentException(
                     "URLDecoder: Illegal hex characters in escape (%) pattern - "
                     + e.getMessage());
                 }
                 needToChange = true;
                 break;
             default:
                 sb.append(c);
                 i++;
                 break;
             }
         }

         return (needToChange? sb.toString() : s);
     }
 }
