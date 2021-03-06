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
package com.f1rst.blackberry.facebook;

import java.util.Enumeration;
import java.util.Hashtable;

import net.rim.device.api.io.transport.ConnectionFactory;
import net.rim.device.api.io.transport.TransportInfo;

//import com.blackberry.util.network.LoggableConnectionFactory;
import com.f1rst.blackberry.json.JSONObject;
import com.f1rst.blackberry.json.JSONTokener;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.util.network.HttpClient;

public class Facebook {
	protected static final String GRAPH_URL = "https://graph.facebook.com";
	protected static final String ACCESS_TOKEN_URL = "https://graph.facebook.com/oauth/access_token";
	protected static final String LOADING = "Connecting to Facebook";

	protected HttpClient http;
	protected ConnectionFactory cf;
//	protected LoggableConnectionFactory lcf;
	protected int[] preferredTransportTypes = { TransportInfo.TRANSPORT_TCP_WIFI, TransportInfo.TRANSPORT_TCP_CELLULAR, TransportInfo.TRANSPORT_WAP2 };
	protected int[] disallowedTransportTypes = { TransportInfo.TRANSPORT_BIS_B, TransportInfo.TRANSPORT_MDS, TransportInfo.TRANSPORT_WAP };

	protected ApplicationSettings appSettings;
	protected String accessToken;
	protected Object ACCESS_TOKEN_LOCK = new Object();

	protected String id = "";
	protected String pwd = "";
	protected boolean autoMode = false;

//	protected Logger log = Logger.getLogger(getClass());
	public static String API_URL = "https://graph.facebook.com";

	public Facebook() {
	}

	protected Facebook(ApplicationSettings pAppSettings) {
		appSettings = pAppSettings;
		cf = new ConnectionFactory();
		cf.setPreferredTransportTypes(preferredTransportTypes);
		cf.setDisallowedTransportTypes(disallowedTransportTypes);
//		lcf = new LoggableConnectionFactory();
//		lcf.setPreferredTransportTypes(preferredTransportTypes);
//		lcf.setDisallowedTransportTypes(disallowedTransportTypes);

//		http = new HttpClient();
	}
	
	public static Object read(String path, String accessToken) throws FacebookException {
		return read(path, null, accessToken);
	}

	public static Object read(String path, Parameters params, String accessToken) throws FacebookException {
		Hashtable args = new Hashtable();
		args.put("access_token", accessToken);
		args.put("format", "JSON");

		if ((params != null) && (params.getCount() > 0)) {
			Enumeration paramNamesEnum = params.getParameterNames();

			while (paramNamesEnum.hasMoreElements()) {
				String paramName = (String) paramNamesEnum.nextElement();
				String paramValue = params.get(paramName).getValue();
				args.put(paramName, paramValue);
			}
		}

		try {
			
			StringBuffer responseBuffer = HttpClient.getInstance().doGet(API_URL + '/' + path, args);

			if (responseBuffer.length() == 0) {
				throw new Exception("Empty response");
			}

			return new JSONObject(new JSONTokener(responseBuffer.toString()));

		} catch (Throwable t) {
			t.printStackTrace();
			throw new FacebookException(t.getMessage());
		}
	}

	public static Object write(String path, Object object, String accessToken) throws FacebookException {
		Hashtable data = new Hashtable();
		data.put("access_token", accessToken);
		data.put("format", "JSON");

		try {
			JSONObject jsonObject = (JSONObject) object;
			Enumeration keysEnum = jsonObject.keys();

			while (keysEnum.hasMoreElements()) {
				String key = (String) keysEnum.nextElement();
				Object val = jsonObject.get(key);

				if (!(val instanceof JSONObject)) {
					data.put(key, val.toString());
				}
			}

			StringBuffer responseBuffer = HttpClient.getInstance().doPost(API_URL + '/' + path, data);

			if (responseBuffer.length() == 0) {
				throw new FacebookException("Empty response");
			}

			return new JSONObject(new JSONTokener(responseBuffer.toString()));
		} catch (Exception e) {
			throw new FacebookException(e.getMessage());
		}
	}
	
	public static Object delete(String path, String accessToken) throws FacebookException {
		Hashtable data = new Hashtable();
		data.put("access_token", accessToken);
		data.put("format", "JSON");
		data.put("method", "delete");

		try {
			StringBuffer responseBuffer = HttpClient.getInstance().doPost(API_URL + '/' + path, data);

			if (responseBuffer.length() == 0) {
				throw new FacebookException("Empty response");
			}

			return new JSONObject(new JSONTokener(responseBuffer.toString()));
		} catch (Exception e) {
			throw new FacebookException(e.getMessage());
		}
	}
	
	public static class Permissions {

		// FacebookUser Data Permissions
		public static final String USER_ABOUT_ME = "user_about_me";
		public static final String USER_ACTIVITIES = "user_activities";
		public static final String USER_BIRTHDAY = "user_birthday";
		public static final String USER_EDUCATION_HISTORY = "user_education_history";
		public static final String USER_EVENTS = "user_events";
		public static final String USER_GROUPS = "user_groups";
		public static final String USER_HOMETOWN = "user_hometown";
		public static final String USER_INTERESTS = "user_interests";
		public static final String USER_LIKES = "user_likes";
		public static final String USER_LOCATION = "user_location";
		public static final String USER_NOTES = "user_notes";
		public static final String USER_ONLINE_PRESENCE = "user_online_presence";
		public static final String USER_PHOTO_VIDEO_TAGS = "user_photo_video_tags";
		public static final String USER_PHOTOS = "user_photos";
		public static final String USER_RELATIONSHIPS = "user_relationships";
		public static final String USER_RELATIONSHIP_DETAILS = "user_relationship_details";
		public static final String USER_RELIGION_POLITICS = "user_religion_politics";
		public static final String USER_STATUS = "user_status";
		public static final String USER_VIDEOS = "user_videos";
		public static final String USER_WEBSITE = "user_website";
		public static final String USER_WORK_HISTORY = "user_work_history";
		public static final String EMAIL = "email";
		public static final String READ_FRIENDLISTS = "read_friendlists";
		public static final String READ_INSIGHTS = "read_insights";
		public static final String READ_MAILBOX = "read_mailbox";
		public static final String READ_REQUESTS = "read_requests";
		public static final String READ_STREAM = "read_stream";
		public static final String XMPP_LOGIN = "xmpp_login";
		public static final String ADS_MANAGEMENT = "ads_management";
		public static final String USER_CHECKINS = "user_checkins";

		// Friends Data Permissions
		public static final String FRIENDS_ABOUT_ME = "friends_about_me";
		public static final String FRIENDS_ACTIVITIES = "friends_activities";
		public static final String FRIENDS_BIRTHDAY = "friends_birthday";
		public static final String FRIENDS_EDUCATION_HISTORY = "friends_education_history";
		public static final String FRIENDS_EVENTS = "friends_events";
		public static final String FRIENDS_GROUPS = "friends_groups";
		public static final String FRIENDS_HOMETOWN = "friends_hometown";
		public static final String FRIENDS_INTERESTS = "friends_interests";
		public static final String FRIENDS_LIKES = "friends_likes";
		public static final String FRIENDS_LOCATION = "friends_location";
		public static final String FRIENDS_NOTES = "friends_notes";
		public static final String FRIENDS_ONLINE_PRESENCE = "friends_online_presence";
		public static final String FRIENDS_PHOTO_VIDEO_TAGS = "friends_photo_video_tags";
		public static final String FRIENDS_PHOTOS = "friends_photos";
		public static final String FRIENDS_RELATIONSHIPS = "friends_relationships";
		public static final String FRIENDS_RELATIONSHIP_DETAILS = "friends_relationship_details";
		public static final String FRIENDS_RELIGION_POLITICS = "friends_religion_politics";
		public static final String FRIENDS_STATUS = "friends_status";
		public static final String FRIENDS_VIDEOS = "friends_videos";
		public static final String FRIENDS_WEBSITE = "friends_website";
		public static final String FRIENDS_WORK_HISTORY = "friends_work_history";
		public static final String MANAGE_FRIENDLISTS = "manage_friendlists";
		public static final String FRIENDS_CHECKINS = "friends_checkins";

		// Publishing Permissions
		public static final String PUBLISH_STREAM = "publish_stream";
		public static final String CREATE_EVENT = "create_event";
		public static final String RSVP_EVENT = "rsvp_event";
		// public static final String SMS = "sms";
		public static final String OFFLINE_ACCESS = "offline_access";
		public static final String PUBLISH_CHECKINS = "publish_checkins";

		// Page Permissions
		public static final String MANAGE_PAGES = "manage_pages";

		// Some canned permissions bundles
		public static final String[] USER_DATA_PERMISSIONS = new String[] { USER_ABOUT_ME, USER_ACTIVITIES, USER_BIRTHDAY, USER_EDUCATION_HISTORY, USER_EVENTS, USER_GROUPS, USER_HOMETOWN, USER_INTERESTS, USER_LIKES, USER_LOCATION, USER_NOTES, USER_ONLINE_PRESENCE, USER_PHOTO_VIDEO_TAGS, USER_PHOTOS, USER_RELATIONSHIPS, USER_RELATIONSHIP_DETAILS, USER_RELIGION_POLITICS, USER_STATUS, USER_VIDEOS, USER_WEBSITE, USER_WORK_HISTORY, EMAIL, READ_FRIENDLISTS, READ_INSIGHTS, READ_MAILBOX, READ_REQUESTS, READ_STREAM, XMPP_LOGIN, ADS_MANAGEMENT, USER_CHECKINS };
		public static final String[] FRIENDS_DATA_PERMISSIONS = new String[] { FRIENDS_ABOUT_ME, FRIENDS_ACTIVITIES, FRIENDS_BIRTHDAY, FRIENDS_EDUCATION_HISTORY, FRIENDS_EVENTS, FRIENDS_GROUPS, FRIENDS_HOMETOWN, FRIENDS_INTERESTS, FRIENDS_LIKES, FRIENDS_LOCATION, FRIENDS_NOTES, FRIENDS_ONLINE_PRESENCE, FRIENDS_PHOTO_VIDEO_TAGS, FRIENDS_PHOTOS, FRIENDS_RELATIONSHIPS, FRIENDS_RELATIONSHIP_DETAILS, FRIENDS_RELIGION_POLITICS, FRIENDS_STATUS, FRIENDS_VIDEOS, FRIENDS_WEBSITE, FRIENDS_WORK_HISTORY, MANAGE_FRIENDLISTS, FRIENDS_CHECKINS };
		// public static final String[] PUBLISHING_PERMISSIONS = new String[] { PUBLISH_STREAM, CREATE_EVENT, RSVP_EVENT, SMS, OFFLINE_ACCESS, PUBLISH_CHECKINS };
		public static final String[] PUBLISHING_PERMISSIONS = new String[] { PUBLISH_STREAM, CREATE_EVENT, RSVP_EVENT, OFFLINE_ACCESS, PUBLISH_CHECKINS };
		public static final String[] PAGE_PERMISSIONS = new String[] { MANAGE_PAGES };
		// public static final String[] ALL_PERMISSIONS = new String[] { USER_ABOUT_ME, USER_ACTIVITIES, USER_BIRTHDAY, USER_EDUCATION_HISTORY, USER_EVENTS, USER_GROUPS, USER_HOMETOWN, USER_INTERESTS, USER_LIKES, USER_LOCATION, USER_NOTES, USER_ONLINE_PRESENCE, USER_PHOTO_VIDEO_TAGS, USER_PHOTOS, USER_RELATIONSHIPS, USER_RELATIONSHIP_DETAILS, USER_RELIGION_POLITICS, USER_STATUS, USER_VIDEOS, USER_WEBSITE, USER_WORK_HISTORY, EMAIL, READ_FRIENDLISTS, READ_INSIGHTS, READ_MAILBOX, READ_REQUESTS, READ_STREAM, XMPP_LOGIN, ADS_MANAGEMENT, USER_CHECKINS, FRIENDS_ABOUT_ME, FRIENDS_ACTIVITIES, FRIENDS_BIRTHDAY, FRIENDS_EDUCATION_HISTORY, FRIENDS_EVENTS, FRIENDS_GROUPS, FRIENDS_HOMETOWN, FRIENDS_INTERESTS, FRIENDS_LIKES, FRIENDS_LOCATION, FRIENDS_NOTES, FRIENDS_ONLINE_PRESENCE, FRIENDS_PHOTO_VIDEO_TAGS, FRIENDS_PHOTOS, FRIENDS_RELATIONSHIPS, FRIENDS_RELATIONSHIP_DETAILS, FRIENDS_RELIGION_POLITICS, FRIENDS_STATUS, FRIENDS_VIDEOS, FRIENDS_WEBSITE, FRIENDS_WORK_HISTORY, MANAGE_FRIENDLISTS, FRIENDS_CHECKINS, PUBLISH_STREAM, CREATE_EVENT, RSVP_EVENT, SMS, OFFLINE_ACCESS, PUBLISH_CHECKINS, MANAGE_PAGES };
		public static final String[] ALL_PERMISSIONS = new String[] { USER_ABOUT_ME, USER_ACTIVITIES, USER_BIRTHDAY, USER_EDUCATION_HISTORY, USER_EVENTS, USER_GROUPS, USER_HOMETOWN, USER_INTERESTS, USER_LIKES, USER_LOCATION, USER_NOTES, USER_ONLINE_PRESENCE, USER_PHOTO_VIDEO_TAGS, USER_PHOTOS, USER_RELATIONSHIPS, USER_RELATIONSHIP_DETAILS, USER_RELIGION_POLITICS, USER_STATUS, USER_VIDEOS, USER_WEBSITE, USER_WORK_HISTORY, EMAIL, READ_FRIENDLISTS, READ_INSIGHTS, READ_MAILBOX, READ_REQUESTS, READ_STREAM, XMPP_LOGIN, ADS_MANAGEMENT, USER_CHECKINS, FRIENDS_ABOUT_ME, FRIENDS_ACTIVITIES, FRIENDS_BIRTHDAY, FRIENDS_EDUCATION_HISTORY, FRIENDS_EVENTS, FRIENDS_GROUPS, FRIENDS_HOMETOWN, FRIENDS_INTERESTS, FRIENDS_LIKES, FRIENDS_LOCATION, FRIENDS_NOTES, FRIENDS_ONLINE_PRESENCE, FRIENDS_PHOTO_VIDEO_TAGS, FRIENDS_PHOTOS, FRIENDS_RELATIONSHIPS, FRIENDS_RELATIONSHIP_DETAILS, FRIENDS_RELIGION_POLITICS, FRIENDS_STATUS, FRIENDS_VIDEOS, FRIENDS_WEBSITE, FRIENDS_WORK_HISTORY, MANAGE_FRIENDLISTS, FRIENDS_CHECKINS, PUBLISH_STREAM, CREATE_EVENT, RSVP_EVENT, OFFLINE_ACCESS, PUBLISH_CHECKINS, MANAGE_PAGES };

	}
	
	public static Facebook getInstance(ApplicationSettings pAppSettings) {
		return new Facebook(pAppSettings);
	}
	
	public String getAccessToken() {
		return accessToken;
	}


}
