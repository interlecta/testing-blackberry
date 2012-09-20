package com.f1rst.blackberry.restapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.HttpConnection;
import javax.microedition.pki.CertificateException;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.net.RestException;

import net.rim.device.api.io.transport.ConnectionDescriptor;
import net.rim.device.api.io.transport.ConnectionFactory;
import net.rim.device.api.io.transport.TransportInfo;
import net.rim.device.api.system.RadioException;
import net.rim.device.api.system.RadioInfo;

/**
 * A class that will handle all network connections to the api
 * @author ivaylo
 */
public class Rest /*implements Persistable */{

    String response = "";
    String errorMessage = "";
    boolean error = false;
    Exception currentException;

    public final static String GET = "GET";

    public final static String POST = "POST";
    
    public final static String PUT = "PUT";

    public String proceedConnection(HttpConnection connection) throws IOException, RestException {

         int responseCode = connection.getResponseCode();
         String responseMessage = connection.getResponseMessage();
         Logger.log(String.valueOf(responseCode) + responseMessage);

         if (responseCode == HttpConnection.HTTP_OK) {
                String receivedText;
                InputStream inputstream = connection.openInputStream();
//                int length = (int) connection.getLength();
//                if (length != -1) {
//                    byte incomingData[] = new byte[length];
//                    inputstream.read(incomingData);
//                    receivedText = new String(incomingData);
//
//                    if(inputstream!=null)
//                        inputstream.close();
//                    if(connection!=null) connection.close();
//
//                    return receivedText;
//                } else
                {
                    ByteArrayOutputStream bytestream =
                            new ByteArrayOutputStream();
                    int ch;
                    while ((ch = inputstream.read()) != -1) {
                        bytestream.write(ch);
                    }
                    receivedText = new String(bytestream.toByteArray());
                    bytestream.close();

                    if(inputstream!=null)
                        inputstream.close();

                    if(connection!=null) connection.close();

//                    return bytestream.toString();
                    return formatUTF(bytestream.toString());
                }

            } else {
                //parsing response code id there is an api params error
                throw new RestException(String.valueOf(responseCode)+": "+responseMessage);
            }
        //return "";
    }

    public String proceedConnection(String url) throws RestException {

        /*HttpsConnection*/HttpConnection  connection = null;
        InputStream inputstream = null;
        OutputStream outputstream = null;

        //http response code and message
        int responseCode = 0;
        String responseMessage = "";

        error = false;
        errorMessage = "";
        response = "";
        currentException = null;

        try {

            Logger.log(url);
            Logger.log("connecting");
            
            if (!isCoverageSufficient()) {
            	throw new RadioException("Insufficient network coverage");
            }
            //connection = (HttpsConnection) Connector.open(url, Connector.READ_WRITE, true);
//            connection = AutoConnectionManager.request(url);
            connection = getConnection(url);

            if(connection == null){
                error = true;
                errorMessage = "Unable to open connection.";
                throw new IOException(errorMessage);
            } else {

                    Logger.log("opened");

                    //HTTP Request
                    connection.setRequestMethod("GET");
//                    connection.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
                    connection.setRequestProperty("Content-Type", "application/json");
//                    connection.setRequestProperty("Connection", "Keep-Alive");
//                    connection.setRequestProperty("Accept", "*/*");
                    
//                    connection.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
//                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                    connection.setRequestProperty("Connection", "Keep-Alive");
//                    connection.setRequestProperty("Accept", "*/*");
//                    //connection.setRequestProperty("Content-Type","//text plain");
////                    connection.setRequestProperty("User-Agent", "blackberry");
////                    connection.setRequestProperty("Content-Type", "text/html");
////                    connection.setRequestProperty("If-Modified-Since", "29 Oct 1999 19:43:31 GMT");
//                    //connection.setRequestProperty("Keep-Alive", "115");
//                    //connection.setRequestProperty("Connection", "close");

                    Logger.log("set requested properties and waiting reponse code");

                    responseCode = connection.getResponseCode();
                    responseMessage = connection.getResponseMessage();
                    Logger.log(String.valueOf(responseCode) + responseMessage);

//                    outputstream = connection.openOutputStream();
//                    outputstream.write('\r');
//                    outputstream.write('\n');
//                    outputstream.flush();
                    //outputstream.close();

//                    Logger.log("output written");

                    Logger.log("waiting input stream...");
                    inputstream = connection.openInputStream();

                    // HTTP Response
                    //return proceedConnection(connection);
                    if (responseCode == HttpConnection.HTTP_OK || isError(responseCode)) {
                        String receivedText;
//                        outputstream = connection.openOutputStream();
                        //outputstream.write(-1);
//                        outputstream.flush();
//                        inputstream = connection.openInputStream();
                        int length = (int) connection.getLength();
//                        Logger.log("connection is length:"  + String.valueOf(length));
//                        if (length != -1) {
//                            byte incomingData[] = new byte[length];
//                            inputstream.read(incomingData);
//                            receivedText = new String(incomingData);
//                            response = receivedText;
//                        } else {
                            ByteArrayOutputStream bytestream =
                                    new ByteArrayOutputStream();
                            int ch;
                            while ((ch = inputstream.read()) != -1) {
                                bytestream.write(ch);
                            }
                            receivedText = new String(bytestream.toByteArray());
                            bytestream.close();

//                            Logger.log("connection baos length:"  + String.valueOf(bytestream.size()));
                            response = bytestream.toString();
//                        }
                    } else {
                        //parsing response code id there is an api params error
                        Logger.log(String.valueOf(responseCode)+":"+responseMessage);
                        error = true;
                        errorMessage = String.valueOf(responseCode)+": "+responseMessage;
                    }

            }

        } catch (RadioException io) {
            error = true;
            errorMessage +="RadioException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("RadioException: " + io.getMessage());
        } 
        catch (InterruptedIOException io) {
            error = true;
            errorMessage +="InterruptedIOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("InterruptedIOException: " + io.getMessage());            
        } catch (ConnectionNotFoundException cn) {
            error = true;
            errorMessage +="ConnectionNotFoundException: ";
            errorMessage +=cn.getMessage();
            currentException = cn;
            Logger.log("ConnectionNotFoundException: " + cn.getMessage());
        } catch (CertificateException ce) {
            error = true;
            errorMessage +="CertificateException: ";
            errorMessage +=ce.getMessage();
            errorMessage +=" ";
            errorMessage +=String.valueOf(ce.getReason());
            currentException = ce;
            Logger.log("CertificateException: " + ce.getMessage());
        } catch (IOException io) {
            error = true;
            errorMessage +="IOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("IOException: " + io.getMessage());
        } catch(Exception e){
            error = true;
            errorMessage += "Exception: ";
            errorMessage += e.getMessage();
            currentException = e;
            Logger.log("Exception: " + e.getMessage());
        } catch(Throwable t){
            error = true;
            errorMessage += "Throwable: ";
            errorMessage += t.getMessage();
            Logger.log("Throable(Serious rest exception) : " + t.getMessage());
        } finally {
            try {
                if ( inputstream != null ) {
                    inputstream.close();
                    Logger.log("inpustream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("error in closing input stream");
            }
            try {
                if ( outputstream != null ) {
                    outputstream.close();
                    Logger.log("outputstream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing output stream");
            }

            try {
                if ( connection != null ) {
                    connection.close();
                    Logger.log("connections closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing connections");
            }
        }

        if(error) {
            Logger.log("response code: "   + String.valueOf(responseCode)
                    + " response message: " + ((responseMessage==null?"":responseMessage))
                    + " error message: "    + errorMessage + ((response==null?"":response)));

            //throw  new RestException(errorMessage + ((response.equals(null)?"":response)));
            //errorMessage = response;
            RestException re = new RestException(
                    errorMessage + ((response==null?"":response)),
                    responseCode, 
                    responseMessage,
                    response);
            
            re.setCurrentException(currentException);
            
            throw re;

        } else {

            Logger.log("" + response);

            return formatUTF(response);
            
        }
    }

    /**
     * This method will post encodedData to the specified url
     */
    public String proceedConnection(String url, String encodedData) throws RestException {

        /*HttpsConnection*/HttpConnection  connection = null;
        InputStream inputstream = null;
        OutputStream outputstream = null;

        //http response code and message
        int responseCode = 0;
        String responseMessage = "";

        error = false;
        errorMessage = "";
        response = "";
        currentException = null;
        
        try {

            Logger.log(url);
            Logger.log("connecting");
            
            if (!isCoverageSufficient()) {
            	throw new RadioException("Insufficient network coverage");
            }
            
//            connection = AutoConnectionManager.request(url);
            connection = getConnection(url);

            if(connection == null){
                error = true;
                errorMessage = "Unable to open connection.";
                throw new IOException(errorMessage);                
            } else {

                    //HTTP Request
                    connection.setRequestMethod("POST");
//                    connection.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
//                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-Type", "application/json");
//                    connection.setRequestProperty("Connection", "Keep-Alive");
//                    connection.setRequestProperty("Accept", "*/*");
                    connection.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));

                    outputstream = connection.openOutputStream();
                    outputstream.write( encodedData.getBytes() );
                    outputstream.flush();

                    responseCode = connection.getResponseCode();
                    responseMessage = connection.getResponseMessage();

                    inputstream = connection.openInputStream();

                    // HTTP Response
                    if (responseCode == HttpConnection.HTTP_OK || isError(responseCode)) {
                        String receivedText;
//                        int length = (int) connection.getLength();
//                        if (length != -1) {
//                            byte incomingData[] = new byte[length];
//                            inputstream.read(incomingData);
//                            receivedText = new String(incomingData);
//                            response = receivedText;
//                        } else {
                            ByteArrayOutputStream bytestream =
                                    new ByteArrayOutputStream();
                            int ch;
                            while ((ch = inputstream.read()) != -1) {
                                bytestream.write(ch);
                            }
                            receivedText = new String(bytestream.toByteArray());
                            bytestream.close();

                            response = bytestream.toString();
//                        }
                    } else {
                        //parsing response code id there is an api params error
                        Logger.log(String.valueOf(responseCode)+":"+responseMessage);
                        error = true;
                        errorMessage = String.valueOf(responseCode)+": "+responseMessage;
                    }

            }

        } catch (RadioException io) {
            error = true;
            errorMessage +="RadioException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("RadioException: " + io.getMessage());   
        } catch (InterruptedIOException io) {
            error = true;
            errorMessage +="InterruptedIOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("InterruptedIOException: " + io.getMessage());
        } catch (ConnectionNotFoundException cn) {
            error = true;
            errorMessage +="ConnectionNotFoundException: ";
            errorMessage +=cn.getMessage();
            currentException = cn;
            Logger.log("ConnectionNotFoundException: " + cn.getMessage());
        } catch (CertificateException ce) {
            error = true;
            errorMessage +="CertificateException: ";
            errorMessage +=ce.getMessage();
            errorMessage +=" ";
            errorMessage +=String.valueOf(ce.getReason());
            currentException = ce;
            Logger.log("CertificateException: " + ce.getMessage());
        } catch (IOException io) {
            error = true;
            errorMessage +="IOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("IOException: " + io.getMessage());
        } catch(Exception e){
            error = true;
            errorMessage += "Exception: ";
            errorMessage += e.getMessage();
            currentException = e;
            Logger.log("Exception: " + e.getMessage());
        } catch(Throwable t){
            error = true;
            errorMessage += "Throwable: ";
            errorMessage += t.getMessage();
            Logger.log("Throable(Serious rest exception) : " + t.getMessage());
        } finally {
            try {
                if ( inputstream != null ) {
                    inputstream.close();
                    Logger.log("inpustream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("error in closing input stream");
            }
            try {
                if ( outputstream != null ) {
                    outputstream.close();
                    Logger.log("outputstream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing output stream");
            }

            try {
                if ( connection != null ) {
                    connection.close();
                    Logger.log("connections closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing connections");
            }
        }

        if(error) {
            Logger.log("response code: "   + String.valueOf(responseCode)
                    + " response message: " + ((responseMessage==null?"":responseMessage))
                    + " error message: "    + errorMessage + ((response==null?"":response)));
            RestException re = new RestException(
                    errorMessage + ((response==null?"":response)),
                    responseCode,
                    responseMessage,
                    response);
            re.setCurrentException(currentException);
            
            throw re;

        } else {

            Logger.log("" + response);

            return formatUTF(response);
        }
    }
    
    public HttpConnection getConnection(String url) {
    	// make a list of transport types ordered according to preference (they will be tried in succession)
    	int[] preferredTransportTypes = {TransportInfo.TRANSPORT_MDS,
    			TransportInfo.TRANSPORT_TCP_WIFI,
    			TransportInfo.TRANSPORT_WAP2,
    			TransportInfo.TRANSPORT_TCP_CELLULAR,
    			TransportInfo.TRANSPORT_WAP,
    			TransportInfo.TRANSPORT_BIS_B};

    	// Create ConnectionFactory
    	ConnectionFactory factory = new ConnectionFactory();

    	// Configure the factory
    	factory.setPreferredTransportTypes( preferredTransportTypes );

    	// use the factory to get a connection
    	ConnectionDescriptor conDescriptor = factory.getConnection(url);//"http://www.blackberry.com");

    	HttpConnection  httpCon = null;
    	if ( conDescriptor != null ) {

    	   // connection suceeded
    	   int transportUsed = conDescriptor.getTransportDescriptor().getTransportType();

    	   // using the connection
    	   /*HttpConnection*/  httpCon = (HttpConnection) conDescriptor.getConnection();
    	   return httpCon;
    	} 
//    	else {
//    		return null;
//    	}
    	
    	return httpCon;
    }
    
    public String proceedPUTConnection(String url, String encodedData) throws RestException {

        /*HttpsConnection*/HttpConnection connection = null;
        InputStream inputstream = null;
        OutputStream outputstream = null;

        //http response code and message
        int responseCode = 0;
        String responseMessage = "";

        error = false;
        errorMessage = "";
        response = "";
        currentException = null;
        
        try {
            Logger.log(url);
            Logger.log("connecting");
            
            if (!isCoverageSufficient()) {
            	throw new RadioException("Insufficient network coverage");
            }
//            connection = AutoConnectionManager.getInstance().request(url);
            connection = getConnection(url);

            if(connection == null){
                error = true;
                errorMessage = "Unable to open connection.";
                throw new IOException(errorMessage);
            } else {

                    //HTTP PUT Request 
                    connection.setRequestMethod("PUT");
                    Logger.log("setRequestedMethod: PUT" );
//                    connection.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
                    //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");                                       
                    //connection.setRequestProperty("Connection", "Keep-Alive");
                    //connection.setRequestProperty("Accept", "*/*");
                    
                    connection.setRequestProperty("Content-Type", "application/json");                    
                    connection.setRequestProperty("Content-Length", String.valueOf(encodedData.length()) );
                    Logger.log("setContentLength: " + String.valueOf(encodedData.length()) );
                    Logger.log("data: " + encodedData);
                    
                    outputstream = connection.openOutputStream();
                    outputstream.write( encodedData.getBytes() );
                    outputstream.flush();

                    responseCode = connection.getResponseCode();
                    responseMessage = connection.getResponseMessage();

                    inputstream = connection.openInputStream();

                    // HTTP Response
                    if (responseCode == HttpConnection.HTTP_OK || isError(responseCode)) {
                    	Logger.log("reading");
                        String receivedText;
                        int length = (int) connection.getLength();
                        Logger.log("length: " + String.valueOf(length));
//                        if (length != -1) {
//                            byte incomingData[] = new byte[length];
//                            inputstream.read(incomingData);
//                            receivedText = new String(incomingData);
//                            response = receivedText;
//                        } else {
                            ByteArrayOutputStream bytestream =
                                    new ByteArrayOutputStream();
                            int ch;
                            while ((ch = inputstream.read()) != -1) {
                                bytestream.write(ch);
                            }
                            receivedText = new String(bytestream.toByteArray());
                            bytestream.close();

                            response = bytestream.toString();
//                        }
                    } else {
                        //parsing response code id there is an api params error
                        Logger.log(String.valueOf(responseCode)+":"+responseMessage);
                        error = true;
                        errorMessage = String.valueOf(responseCode)+": "+responseMessage;
                    }

            }

        } catch (RadioException io) {
            error = true;
            errorMessage +="RadioException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("RadioException: " + io.getMessage());
        } catch (InterruptedIOException io) {
            error = true;
            errorMessage +="InterruptedIOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("InterruptedIOException: " + io.getMessage());
        } catch (ConnectionNotFoundException cn) {
            error = true;
            errorMessage +="ConnectionNotFoundException: ";
            errorMessage +=cn.getMessage();
            currentException = cn;
            Logger.log("ConnectionNotFoundException: " + cn.getMessage());
        } catch (CertificateException ce) {
            error = true;
            errorMessage +="CertificateException: ";
            errorMessage +=ce.getMessage();
            errorMessage +=" ";
            errorMessage +=String.valueOf(ce.getReason());
            currentException = ce;
            Logger.log("CertificateException: " + ce.getMessage());
        } catch (IOException io) {
            error = true;
            errorMessage +="IOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("IOException: " + io.getMessage());
        } catch(Exception e){
            error = true;
            errorMessage += "Exception: ";
            errorMessage += e.getMessage();
            currentException = e;
            Logger.log("Exception: " + e.getMessage());
        } catch(Throwable t){
            error = true;
            errorMessage += "Throwable: ";
            errorMessage += t.getMessage();
            Logger.log("Throable(Serious rest exception) : " + t.getMessage());
        } finally {
            try {
                if ( inputstream != null ) {
                    inputstream.close();
                    Logger.log("inpustream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("error in closing input stream");
            }
            try {
                if ( outputstream != null ) {
                    outputstream.close();
                    Logger.log("outputstream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing output stream");
            }

            try {
                if ( connection != null ) {
                    connection.close();
                    Logger.log("connections closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing connections");
            }
        }

        if(error) {
            Logger.log("response code: "   + String.valueOf(responseCode)
                    + " response message: " + ((responseMessage==null?"":responseMessage))
                    + " error message: "    + errorMessage + ((response==null?"":response)));
            RestException re = new RestException(
                    errorMessage /*+ ((response==null?"":response))*/,
                    responseCode,
                    responseMessage,
                    response);
            
            re.setCurrentException(currentException);
            
            throw re;

        } else {

            Logger.log("" + response);

            return formatUTF(response);
        }
    }

    /**
     * suitable for large data
     * return byte []
     */
    public Object proceedConnectionToByteArray(String url) throws RestException {

        Object reponse;//shadowing

        /*HttpsConnection*/HttpConnection  connection = null;
        InputStream inputstream = null;
        OutputStream outputstream = null;

        //http response code and message
        int responseCode = 0;
        String responseMessage = "";

        error = false;
        errorMessage = "";
        response = "";
        currentException = null;
        
        try {

            Logger.log(url);
            Logger.log("connecting");
            
            if (!isCoverageSufficient()) {
            	throw new RadioException("Insufficient network coverage");
            }
            
            //connection = (HttpsConnection) Connector.open(url, Connector.READ_WRITE, true);
//            connection = AutoConnectionManager.request(url);
            connection = getConnection(url);

            if(connection == null){
                error = true;
                errorMessage = "Unable to open connection.";
                throw new IOException(errorMessage);
            } else {

                    Logger.log("opened");

                    //HTTP Request
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Accept", "*/*");
                    //connection.setRequestProperty("Content-Type","//text plain");
//                    connection.setRequestProperty("User-Agent", "blackberry");
//                    connection.setRequestProperty("Content-Type", "text/html");
//                    connection.setRequestProperty("If-Modified-Since", "29 Oct 1999 19:43:31 GMT");
                    //connection.setRequestProperty("Keep-Alive", "115");
                    //connection.setRequestProperty("Connection", "close");

                    Logger.log("set requested properties and waiting reponse code");

                    responseCode = connection.getResponseCode();
                    responseMessage = connection.getResponseMessage();
                    Logger.log(String.valueOf(responseCode) + responseMessage);

//                    outputstream = connection.openOutputStream();
//                    outputstream.write('\r');
//                    outputstream.write('\n');
//                    outputstream.flush();
                    //outputstream.close();

//                    Logger.log("output written");

                    Logger.log("waiting input stream...");
                    inputstream = connection.openInputStream();

                    // HTTP Response
                    //return proceedConnection(connection);
                    if (responseCode == HttpConnection.HTTP_OK || isError(responseCode)) {

                            ByteArrayOutputStream bytestream =
                                    new ByteArrayOutputStream();
                            int ch;
                            while ((ch = inputstream.read()) != -1) {
                                bytestream.write(ch);
                            }

                            response = bytestream.toString();
                            bytestream.close();
                            return bytestream.toByteArray();
//                        }
                    } else {
                        //parsing response code id there is an api params error
                        Logger.log(String.valueOf(responseCode)+":"+responseMessage);
                        error = true;
                        errorMessage = String.valueOf(responseCode)+": "+responseMessage;
                    }

            }

        } catch (RadioException io) {
            error = true;
            errorMessage +="RadioException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("RadioException: " + io.getMessage());
        } catch (InterruptedIOException io) {
            error = true;
            errorMessage +="InterruptedIOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("InterruptedIOException: " + io.getMessage());
        } catch (ConnectionNotFoundException cn) {
            error = true;
            errorMessage +="ConnectionNotFoundException: ";
            errorMessage +=cn.getMessage();
            currentException = cn;
            Logger.log("ConnectionNotFoundException: " + cn.getMessage());
        } catch (CertificateException ce) {
            error = true;
            errorMessage +="CertificateException: ";
            errorMessage +=ce.getMessage();
            errorMessage +=" ";
            errorMessage +=String.valueOf(ce.getReason());
            currentException = ce;
            Logger.log("CertificateException: " + ce.getMessage());
        } catch (IOException io) {
            error = true;
            errorMessage +="IOException: ";
            errorMessage +=io.getMessage();
            currentException = io;
            Logger.log("IOException: " + io.getMessage());
        } catch(Exception e){
            error = true;
            errorMessage += "Exception: ";
            errorMessage += e.getMessage();
            currentException = e;
            Logger.log("Exception: " + e.getMessage());
        } catch(Throwable t){
            error = true;
            errorMessage += "Throwable: ";
            errorMessage += t.getMessage();
            Logger.log("Throable(Serious rest exception) : " + t.getMessage());
        } finally {
            try {
                if ( inputstream != null ) {
                    inputstream.close();
                    Logger.log("inpustream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("error in closing input stream");
            }
            try {
                if ( outputstream != null ) {
                    outputstream.close();
                    Logger.log("outputstream closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing output stream");
            }

            try {
                if ( connection != null ) {
                    connection.close();
                    Logger.log("connections closed");
                }
            } catch ( IOException ioe ) {
                ioe.printStackTrace ();
                Logger.log("login.json: error in closing connections");
            }
        }

        if(error) {
            Logger.log("response code: "   + String.valueOf(responseCode)
                    + " response message: " + ((responseMessage==null?"":responseMessage))
                    + " error message: "    + errorMessage + ((response==null?"":response)));

            //throw  new RestException(errorMessage + ((response.equals(null)?"":response)));
            //errorMessage = response;
            RestException re = new RestException(
                    errorMessage /*+ ((response==null?"":response))*/,
                    responseCode,
                    responseMessage,
                    response);
            re.setCurrentException(currentException);
            
            throw re;

        } else {
        	
//          Logger.log("" + response);

        	return formatUTF(response);
        }
    }
    

    boolean isError(int responseCode){
        if(responseCode == 400 
                || responseCode == 403
                || responseCode == 500
                || responseCode == 404)
        {
            error = true;
            return true;
        } else {
            return false;
        }
    }
    
    private String formatUTF(String response){
    	try {
			return new String(response.getBytes(), "UTF-8");
		} catch (IllegalArgumentException e) {
			return response;
		} catch (UnsupportedEncodingException e) {
			return response;
		}
    }
    
    /**
     * Make sure the radio is on and there is data coverage
     * @return boolean - true if has radio and data coverage, false otherwise
     */
    public static boolean /*hasRadioAndNetworkCoverage*/isCoverageSufficient() {
        int state = RadioInfo.getState() & RadioInfo.STATE_ON;
        int data = RadioInfo.getNetworkService() & RadioInfo.NETWORK_SERVICE_DATA;
        
        if(isWiFiActive()) {
        	return true;
        }
        if ((state > 0) && (data > 0)){
            return true;
        }
        
        return false;
    }

    public static boolean isWiFiActive(){
    	if ( ( RadioInfo.getActiveWAFs() & RadioInfo.WAF_WLAN ) != 0 )
        {
          
    		return true;
//            if(CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_DIRECT, RadioInfo.WAF_WLAN, true))
//               {
//                     System.out.println("Wi-fi connected");
//                st = ";deviceside=true;interface=wifi";
//            }
            
//        //4.5
////        if ( (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED) && RadioInfo.areWAFsSupported(RadioInfo.WAF_WLAN)) {
//        //4.2.1
//        if(((RadioInfo.getActiveWAFs() & RadioInfo.WAF_WLAN) != 0)
//              //in os 4.5 COVERAGE_CARRIER is replaced by COVERAGE_DIRECT
////            && (CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_CARRIER,RadioInfo.WAF_WLAN, false))){
//              && (CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_DIRECT, RadioInfo.WAF_WLAN, false))){
//
//            //        if(CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_CARRIER,RadioInfo.WAF_WLAN, false)){
//            // coverageWiFi = true;
//
//            return true;
//
        } 
    		else return false;
    }


}
