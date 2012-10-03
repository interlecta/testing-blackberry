package com.f1rst.blackberry.model;

/**
 * 
 * @author ivaylo
 *
 */
public class LoginResult  implements net.rim.device.api.util.Persistable {

	private String result;
	
	public LoginResult() {
	}
	
	public LoginResult(String result) {
		this.result = result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	
}
