package com.f1rst.blackberry.restapi;

import com.f1rst.blackberry.json.JSONException;
import com.f1rst.blackberry.json.JSONObject;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.net.ConnectionManager;
import com.f1rst.blackberry.net.RestException;


/**
 *
 * @author ivaylo
 */
public class User extends Rest {

    String session_token;
    String username;
    String password;
    String email;

//  "{"ApplicationId":"unifigroup","Password":"App123","UserName":"8075545009"}"
    public String login(String username, String password, String applicationId) throws RestException {

        if (ConnectionManager.getUrl().equals("device")) {
            return "{\"session_token\":\"123555123\"}";
        }
        //String url = ConnectionManager.getUrl() + "../LogOn";
        String url = "login string";
     
        String parameters = "";
//        try {
////            parameters;
//            		"?" + "Password=" + UrlEncoder.encode(password, "")
//                    + "&UserName="  + UrlEncoder.encode(username, "")
//                    + "&ApplicationId="  
//                    + UrlEncoder.encode("unifigrouptest", "");
//            
//                       
//        } catch (IOException ex) {
//        }
        
        try {
	        parameters = new JSONObject().put("ApplicationId", applicationId)//"unifigrouptest")
	    	.put("Password", password)
	    	.put("UserName", username).toString();
        } catch (JSONException e) {
		}
        

        		
//        url += parameters;
        url += ConnectionManager.getConnectionSuffix();

//        response = proceedPUTConnection(url, parameters.substring(1));
        response = proceedPUTConnection(url, parameters);

        if (error) {
            Logger.log((errorMessage == null ? "" : errorMessage));
            Logger.log(((response == null ? "" : response)));
            throw new RestException(errorMessage + ((response == null ? "" : response)));
        } else {
            Logger.log("login.json: " + response);
            return response;
        }
    }
       
}
