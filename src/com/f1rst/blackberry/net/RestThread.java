package com.f1rst.blackberry.net;

/**
 * 
 * @author ivaylo
 */
public class RestThread extends Thread {

	private boolean isWorking = true;

	public RestThread() {
		super();
		isWorking = true;
	}

	public synchronized boolean isWorking() {
		return isWorking;
	}

	public synchronized void stopConn() {
		if (!isWorking) {
			return;
		}

		isWorking = false;
		try {
			interrupt();
		} catch (Exception e) {
		} finally {
		}

	}
}
