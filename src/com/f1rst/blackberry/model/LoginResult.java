package com.f1rst.blackberry.model;

/**
 * {
	"Errors":[{
		"Description":"String content",
		"ErrorCode":0,
		"ErrorReference":2147483647
	}],
	"Token":{
		"ExpiresOn":"\/Date(928142400000+0200)\/",
		"LastRelease":"\/Date(928142400000+0200)\/",
		"Release":"\/Date(928142400000+0200)\/",
		"Token":"String content"
	},
	"LoginAttemptsLeft":32767,
	"Result":0
}
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
