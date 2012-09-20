package eu.f1rst.blackberry.service;

public class ServiceParams {
  public static final boolean USE_HTTPS                    = true;
  public static final String  HTTP_VERSION                 = "http.protocol.version";
  public static final String  TOKEN                        = "Token";
  public static final String  ERRORS                       = "Errors";
  public static final String  ERROR_CODE                   = "ErrorCode";
 
  public static final String  NAME                         = "Name";
  public static final String  KEY                          = "key";
  public static final String  VALUE                        = "value";
  
  public static final String  SEARCH_REFERENCE             = "SearchReference";
  
  public static final String  TOKEN_ID                     = "TokenId";
  public static final String  TOKEN_ID_LOWER               = "tokenId";
  public static final String  RESULT                       = "Result";
  public static final String  RESULT_LOWER                 = "result";
  public static final String  PASSWORD                     = "Password";
  public static final String  USER_NAME                    = "UserName";
  public static final String  APPLICATION_ID               = "ApplicationId";
 
  public static final String  PICTURE                      = "Picture";
  
  public static final String  SCHEMA                       = USE_HTTPS ? "https://" : "http://";
  public static final String  BASE_URL                     = SCHEMA + "";
  public static final String  BASE_IMAGE_URL               = SCHEMA + "";
  public static final String  LOGON_URL                    = SCHEMA + "";
  public static final String  AUTH_URL                     = SCHEMA + "";
  
  public static final String  PICTURES                     = "Pictures";
  

  // PROFILE
  public static final String  GET_USER_INFO_URL            = "/GetUserInfo";
  public static final String  USER_DATA                    = "UserData";
  public static final String  CUSTOMER_CODE                = "CustomerCode";
  public static final String  DISPLAY_NAME                 = "DisplayName";
  public static final String  EMAIL                        = "Email";
  public static final String  USER_CODE                    = "UserCode";
  public static final String  USER_DEFAULT_LANGUAGE        = "UserDefaultLanguage";
  public static final String  USER_LANGUAGE                = "UserLanguage";


  public static final String  UTF_8                        = "UTF-8";
}
