package com.f1rst.blackberry.net;

public class RestException extends Exception {

    /**
     * http response code, returned from the server
     */
    int responseCode;

    /**
     * http response message, returned from the server
     */
    String reponseMessage;

    /**
     * api error message
     */
    String errorMessage;
    
    //store the last exception
    private Exception currentException;

    public RestException() {
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String uiMessage, int responseCode, String reponseMessage, String errorMessage) {
        super(uiMessage);
        this.responseCode = responseCode;
        this.reponseMessage = reponseMessage;
        this.errorMessage = errorMessage;
    }
    
//    public RestException(String message, int responseCode, String reponseMessage, String errorMessage) {
//	      super(message);
//	      this.responseCode = responseCode;
//	      this.reponseMessage = reponseMessage;
//	      this.errorMessage = errorMessage;
//	  }

    /**
     * http response message generated from server, that is in body
     * @return error as String
     */
    public String getErrorMessage() {
        if(errorMessage==null)
            return "";

        return errorMessage;
    }

    /**
     * standart http response message generated from server
     * @return error as String
     */
    public String getReponseMessage() {
        if (reponseMessage == null)
            return null;

        return reponseMessage;
    }

    /**
     * standart http response code generated from server
     * @return error as String
     */
    public int getResponseCode() {
        return responseCode;
    }
    
    public void setCurrentException(Exception currentException) {
		this.currentException = currentException;
	}
    
    public Exception getCurrentException() {
		return currentException;
	}
}
