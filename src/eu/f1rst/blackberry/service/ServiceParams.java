package eu.f1rst.blackberry.service;

public class ServiceParams {

	public static final boolean USE_HTTPS = true;

	public static final String HTTP_VERSION = "http.protocol.version";
	public static final String TOKEN = "Token";

	public static final String ERRORS = "Errors";
	public static final String ERROR_CODE = "ErrorCode";

	public static final String NAME = "Name";
	public static final String KEY = "key";
	public static final String VALUE = "value";

	public static final String TOKEN_ID = "TokenId";
	public static final String TOKEN_ID_LOWER = "tokenId";

	public static final String SCHEMA = USE_HTTPS ? "https://" : "http://";
	public static final String BASE_URL = SCHEMA + "";
	public static final String BASE_IMAGE_URL = SCHEMA + "";
	public static final String LOGON_URL = SCHEMA + "";
	public static final String AUTH_URL = SCHEMA + "";

	public static final String PICTURES = "Pictures";

	public static final String GET_USER_INFO_URL = "...";

	public static final String UTF_8 = "UTF-8";
}
