package com.f1rst.blackberry.net;

/**
 * an optional interface for network operation
 * 
 * @author ivaylo
 */
public interface RestListener {

	public void success(Object o);

	public void error(String error);
}
