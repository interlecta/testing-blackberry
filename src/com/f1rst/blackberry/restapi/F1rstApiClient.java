package com.f1rst.blackberry.restapi;

import java.io.IOException;
import java.util.Hashtable;

import com.f1rst.blackberry.json.JSONArray;
import com.f1rst.blackberry.json.JSONException;
import com.f1rst.blackberry.json.JSONObject;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.net.ConnectionManager;
import com.f1rst.blackberry.net.RestException;
import com.f1rst.blackberry.net.UrlEncoder;
import com.f1rst.blackberry.service.ServiceParams;


public class F1rstApiClient extends Rest {
	
	private /*Map<String, Object>*/Hashtable createParameters() {
	    return new /*HashMap<String, Object>*/Hashtable();
	}
	
	public String getUserInfo(String token) throws RestException {

//		String url = ServiceParams.AUTH_URL + ServiceParams.GET_USER_INFO_URL;

		String url = "";
		String parameters = "";
		try {
			parameters += "?" + /* "tokenId" */ServiceParams.TOKEN_ID_LOWER
					+ "=" + UrlEncoder.encode(token, "");
		} catch (IOException ex) {
		}

		// try {
		// parameters = new JSONObject().put("ApplicationId", "unifigrouptest")
		// .put("Password", password)
		// .put("UserName", username).toString();
		// } catch (JSONException e) {
		// }

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

}
