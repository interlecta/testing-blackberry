package com.f1rst.blackberry.net;

import java.io.IOException;
import java.io.InterruptedIOException;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.CoverageStatusListener;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.WLANConnectionListener;
import net.rim.device.api.system.WLANInfo;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.PropertyChangeEvent;

/**
 *
 * @author ivaylo
 */
public class AutoConnectionManager implements CoverageStatusListener,
		WLANConnectionListener, AbstractViewPanel {

	public final static int NB_OF_TRIES = 6;

	public final static String CONNECTION_TIMEOUT = ";ConnectionTimeout=";

	private static final int TIMEOUT = 20000;

	public static final int CONNECTION_AUTOMATIC = 0;  //Auto     automatic
	public static final int CONNECTION_BIS_B = 1;      //BIS-B    bis-b
	public static final int CONNECTION_WAP = 2;//      //WAP2     wap2 this is wap2 gateway
	public static final int CONNECTION_DIRECT_TCP = 3; //Direct   TCP direct tcp
	public static final int CONNECTION_MDS = 4;        //MDS(BES)
    public static final int CONNECTION_WAP10 = 5;  //Default  wap 1.0 gateway, without any special params to the url, //wap 1.0 is missing as static parameter
        

	public static String connectionSuffix;

	private static AutoConnectionManager instance;

	public AutoConnectionManager() {
		update();
	}

	public static AutoConnectionManager getInstance() {
		if (instance == null) {
			instance = new AutoConnectionManager();
		}
		return instance;
	}

	public static void update() {
		connectionSuffix = ""; 
//				AutoConnectionManager
//				.getConnectionSuffix(CONNECTION_AUTOMATIC);
////				.getConnectionSuffix(Model.getModel().getConnectionSuffixIndex());
////				
//				
////				.getConnectionSuffix(777);
	}

	public static void update(int index) {
		connectionSuffix = AutoConnectionManager
				.getConnectionSuffix(index);
	}

	public void coverageStatusChanged(int newCoverage) {
		update();
	}

	public void networkConnected() {
		update();
	}

	public void networkDisconnected(int reason) {
		update();
	}

	/**
	 * Gets UID for a WAP connection if exists
	 *
	 * @return
	 */
	private static String getUID() {
		String uid = null;
		ServiceBook sb = ServiceBook.getSB();
		ServiceRecord[] records = sb.findRecordsByCid("WPTCP");
		for (int i = 0; i < records.length; i++) {
			if (records[i].isValid() && !records[i].isDisabled()) {
				if (records[i].getUid() != null
						&& records[i].getUid().length() != 0) {
					if ((records[i].getUid().toLowerCase().indexOf("wptcp") != -1)
							&& (records[i].getUid().toLowerCase().indexOf(
									"wifi") == -1)
							&& (records[i].getUid().toLowerCase()
									.indexOf("mms") == -1)) {
						uid = records[i].getUid();
						break;
					}
				}
			}
		}
		return uid;
	}

	/**
	 * Return connection suffix for specified a connection type
	 *
	 * @param connectionType
	 * @param checkWiFi
	 *            - if true checks the WiFi connection first
	 * @return
	 */
	public static String getConnectionSuffix(int connectionType) {

		if (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED
				&& RadioInfo.areWAFsSupported(RadioInfo.WAF_WLAN)) {
			return ";interface=wifi";
		}

		try {
			switch (connectionType) {
			case CONNECTION_AUTOMATIC:
				if (CoverageInfo
						.isCoverageSufficient(CoverageInfo.COVERAGE_DIRECT)) {
					String uid = getUID();
					if (uid != null) {
						// WAP2 Connection
						return (";ConnectionUID=" + uid);
					}
				}

				if (CoverageInfo
						.isCoverageSufficient(CoverageInfo.COVERAGE_BIS_B)) {
					// BIS-B Connection
					return ";deviceside=false;ConnectionType=mds-public";
				}

				if (CoverageInfo
						.isCoverageSufficient(CoverageInfo.COVERAGE_MDS)) {
					// MDS Connection
					return ";deviceside=false";
				}

				return ";deviceside=true"; // Direct TCP Connection
			case CONNECTION_BIS_B:
				return ";deviceside=false;ConnectionType=mds-public";
			case CONNECTION_WAP:
				// WAP2 Connection
				return ";ConnectionUID=" + getUID();
			case CONNECTION_DIRECT_TCP: // Direct TCP Connection
				return ";deviceside=true";
				// : ";deviceside=true" + ";apn="
				// + Settings.getAPNServer()
				// + ";tunnelauthusername="
				// + Settings.getAPNUsername()
				// + ";tunnelauthpassword="
				// + Settings.getAPNPassword(); // Direct TCP
				// Connection
			case CONNECTION_MDS:
				return ";deviceside=false"; // MDS Connection
			default:
				return ""; // Default Connection, AUTO or
			}
		} catch (Exception e) {
		}
		return ""; // Default Connection
	}

        /**
         * modified to return HTTPS and not to set connection headers or method
         * @param url
         * @return
         * @throws Exception
         */
	public static /*HttpsConnection*/HttpConnection request(String url) throws InterruptedIOException, IOException, Exception {
		/*HttpsConnection*/HttpConnection con = null;
		try {
			String fullURL = url //+ (CONNECTION_TIMEOUT + TIMEOUT)
					+ AutoConnectionManager.connectionSuffix;

            Logger.log("Full URL: " + fullURL);
			int tries = 0;
			while (tries < NB_OF_TRIES) {
				try {
					if (con != null) {
						con.close();
					}
					Logger.log("openning...");
					con = (/*HttpsConnection*/HttpConnection) Connector.open(fullURL,
							Connector.READ_WRITE);
					Logger.log("after openning...");
					if (con == null) {
						++tries;
						continue;
					}
//					con.setRequestMethod(HttpConnection.GET);
//					int code = con.getResponseCode();
//					if (code != HttpConnection.HTTP_OK) {
//						++tries;
//						continue;
//					}
					
					return con;
					//break;
				} catch (IllegalArgumentException e) {
					Logger.log("IllegalArgumentException " + e.getMessage());
					if (con == null) {
						++tries;
						continue;
					} else {
						throw new Exception("IllegalArgumentException " + e.getMessage());
					}
				} catch (ConnectionNotFoundException e) {
					Logger.log("ConnectionNotFoundException " + e.getMessage());
					if (con == null) {
						++tries;
						continue;
					} else {
						throw new Exception("ConnectionNotFoundException " + e.getMessage());
					}
				}  catch (InterruptedIOException e) {
					Logger.log("InterruptedIOException " + e.getMessage());
					if (con == null) {
						++tries;
						continue;
					} else {
						throw e;
					}
					
//					break;
				} catch (IOException e) {
					Logger.log("IOException " + e.getMessage());
					if (con == null) {
						++tries;
						continue;
					} else {
						throw e;
					}
				} 
			}
			return con;
		} catch (Exception e) {
			throw e;
		}
	}

//    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(evt!=null && evt.getPropertyName().equals("setConnectionSuffixIndex") && evt.getNewValue()!=null && evt.getNewValue() instanceof Integer ){
            update(((Integer)evt.getNewValue()).intValue());
        }
    }
}
