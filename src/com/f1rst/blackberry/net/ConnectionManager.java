package com.f1rst.blackberry.net;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;

import com.f1rst.blackberry.util.Model;

/**
 * @author ivaylo
 */

public class ConnectionManager {

    //public final static int CONNECTIONS_COUNT = 3;

    static int index;

//       S E R V E R S   U R L S
    static String urls[] = {
        //"device",
        "http://...",
        "http://..."
//                ,
//        "device"
    };

    static String urlsFriendlyNames[] = {     
        "",
        ""
    };

//     private static String connectionSuffix [] = {
//        //BES, WiFI, APN, Auto, WAP2
//        ";deviceside=false",
//        ";interface=wifi",//";deviceside=true;interface=wifi",
//        ";deviceside=true",
//        "",
//        ";ConnectionUID=" + getWap2Uid()
//    };
    
     private static String connectionSuffix [] = {
        //BES, WiFI, APN, Auto, WAP2
        ";deviceside=false",
        ";deviceside=true;interface=wifi",//";deviceside=true;interface=wifi",
        ";deviceside=true",
        "",
        ";deviceside=true;ConnectionUID=" + getWap2Uid()
    };

    public ConnectionManager() {
    }

    public static String getUrl() {
        return urls[Model.getModel().getUrlIndex()];
    }

    public static String getUrlFriendlyName() {
        return urlsFriendlyNames[Model.getModel().getUrlIndex()];
    }


    public static String getConnectionSuffix() {
        //start to use AutoConnectionManager
////        return "";
//        return (connectionSuffix[Model.getModel().getConnectionSuffixIndex()]
////                +
//             return ((Model.getModel().isTrustAll())? ";trustAll":"")
//                + ((Model.getModel().isEnableHttpsRedirect())? ";RdHTTPS":"")
//                + ((Model.getModel().isEndToEndRequired())? ";EndToEndDesired":"");
//             
////                + ((Model.getModel().isEndToEndRequired())? ";EndToEndRequired":"")
//                //+ ";EndToEndDesired"
////                );
    	
    	return "";
//    	return ";trustAll;RdHTTPS;EndToEndDesired";
    }


    //wap2 check
    private static String getWap2Uid() {
        ServiceBook sb = ServiceBook.getSB();
        ServiceRecord[] records = sb.findRecordsByCid("WPTCP");
        String uid = null;
        for (int i = 0; i < records.length; i++) {
            //Search through all service records to find the
            //valid non-Wi-Fi and non-MMS
            //WAP 2.0 Gateway Service Record.

            if (records[i].isValid() && !records[i].isDisabled()) {

                if (records[i].getUid() != null && records[i].getUid().length() != 0) {
                    if ((records[i].getUid().toLowerCase().indexOf("wifi") == -1)
                            && (records[i].getUid().toLowerCase().indexOf("mms") == -1)) {
                        uid = records[i].getUid();
                        break;
                    }
                }
            }
        }
        
        if (uid != null) {
            //open a WAP 2 connection
//            Connector.open(_url + ";ConnectionUID=" + uid);
            return uid;
        } else {
            return null;
        }
    }
}
