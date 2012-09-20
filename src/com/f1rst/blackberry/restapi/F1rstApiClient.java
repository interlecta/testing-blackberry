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

//    String session_token;
//    String username;
//    String password;
//    String email;

//    List<SearchResult>
//    public SearchResult[] mapResultsFromResponse(JSONObject response) throws JSONException {
////        List<SearchResult> result = new ArrayList<SearchResult>();        
//        JSONArray items = response.getJSONArray(ServiceParams.RESULT.toLowerCase());
//        SearchResult result[] = new SearchResult[items.length()];
////        for (int i = 0; i < items.length(); i++) {
////          SearchResult res = new SearchResult();
////          Category category = new Category();
////          category.setName(items.getJSONObject(i).getString(ServiceParams.CATEGORY.toLowerCase()));
////          category.setCode(items.getJSONObject(i).getString(ServiceParams.CATEGORY_CODE_LOWER));
////          res.setCategory(category);
////          res.setCount(items.getJSONObject(i).getInt(ServiceParams.PRODUCTS_FOUND));
//////          result.add(res);          
////          result[i] = res;
////        }
//        return result;
//      }

    
    /* //Product  getProductDetails */
    public String getDataSheet(String token, String productCode) throws RestException {
		return productCode;
//    	 String url = ServiceParams.BASE_URL + ServiceParams.PRODUCT_DATASHEET_URL;
//         
//    	 url += "?p=1";
//    	 
//         String parameters = "";
//         
//         try { 
// 	        parameters = new JSONObject()
//	 	    	.put(ServiceParams.TOKEN_ID, token)
//	 	    	.put(ServiceParams.SEARCH_TERM, productCode).toString();
//         } catch (JSONException e) {
// 		}
//         		
////         url += parameters;
//         url += ConnectionManager.getConnectionSuffix();
//         response = proceedConnection(url, parameters);
//
//         if (error) {
//             Logger.log((errorMessage == null ? "" : errorMessage));
//             Logger.log(((response == null ? "" : response)));
//             throw new RestException(errorMessage + ((response == null ? "" : response)));
//         } else {
//             Logger.log("getDataSheet: " + response);
//             return response;
//         }
    }
    
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
	
	public Profile mapProfileFromResponse(JSONObject response) throws JSONException {
		JSONObject userData = response.getJSONObject(ServiceParams.USER_DATA);
		Profile profile = new Profile();

		profile.setCustomerCode(userData.getString(ServiceParams.CUSTOMER_CODE));
		profile.setDisplayName(userData.getString(ServiceParams.DISPLAY_NAME));
		profile.setEmail(userData.getString(ServiceParams.EMAIL));
		profile.setUserCode(userData.getString(ServiceParams.USER_CODE));
		profile.setDefaultLanguage(userData
				.getInt(ServiceParams.USER_DEFAULT_LANGUAGE));
		profile.setLanguage(userData.getInt(ServiceParams.USER_LANGUAGE));

		return profile;
	}
}


