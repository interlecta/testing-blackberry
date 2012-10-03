package com.f1rst.blackberry.restapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.rim.device.api.i18n.*;
import net.rim.device.api.util.StringUtilities;

import java.util.Hashtable;
import java.util.Vector;

import com.f1rst.blackberry.json.JSONArray;
import com.f1rst.blackberry.json.JSONException;
import com.f1rst.blackberry.json.JSONObject;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.net.ConnectionManager;
import com.f1rst.blackberry.net.RestException;
import com.f1rst.blackberry.net.UrlEncoder;

import eu.f1rst.blackberry.model.Profile;
import eu.f1rst.blackberry.service.ServiceParams;


/**
 * 
 * @author ivaylo
 */
public class F1rstApiClient extends Rest {
 
    private /*Map<String, Object>*/Hashtable createParameters() {
		    return new /*HashMap<String, Object>*/Hashtable();
	  }
	  
	  
	private boolean hasErrorsInResponse(JSONObject response) throws JSONException {
		JSONArray errorArray = response.getJSONArray(ServiceParams.ERRORS);
		JSONObject json;
		for (int i = 0; i < errorArray.length(); i++) {
			json = errorArray.getJSONObject(i);
			if (json.getInt(ServiceParams.ERROR_CODE) != 0) {
				return false;
			}
		}
		return true;
	}
		 		 
	public String getUserInfo(String token) throws RestException {

		String url = ServiceParams.AUTH_URL + ServiceParams.GET_USER_INFO_URL;

		String parameters = "";
		try {
			parameters += "?" + /* "tokenId" */ServiceParams.TOKEN_ID_LOWER
					+ "=" + UrlEncoder.encode(token, "");
		} catch (IOException ex) {
		} 

		url += parameters;
		url += ConnectionManager.getConnectionSuffix();

		response = proceedConnection(url);

		if (error) {
			Logger.log((errorMessage == null ? "" : errorMessage));
			Logger.log(((response == null ? "" : response)));
			throw new RestException(errorMessage
					+ ((response == null ? "" : response)));
		} else {
			Logger.log("getUserInfo: " + response);
			return response;
		}
	}
	
	public Profile mapProfileFromResponse(JSONObject response) throws JSONException {
		JSONObject userData = response.getJSONObject("...");
		Profile profile = new Profile();

		// ... set the data
		
		return profile;
	}
}


