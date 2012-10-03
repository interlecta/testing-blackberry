package com.f1rst.blackberry.model;

import java.util.Vector;

import net.rim.device.api.util.Persistable;

/**
 * A special vector with max elements specified by LIMIT parameter. If you try
 * to add LIMIT + 1 element the elementAt(0) will be removed and the new size
 * will be again LIMIT elements.
 * 
 * @author ivaylo
 * 
 */
public class SizedVector extends Vector implements Persistable {

	private int LIMIT = 15;

	public SizedVector() {
	}

	public SizedVector(int limit) {
		this.LIMIT = limit;
	}

	// @Override
	public void addElement(Object object) {
		if (!contains(object)) {
			if (size() < LIMIT) {
				super.addElement(object);
			} else {
				// removing 1-st element
				super.removeElementAt(0);
				super.addElement(object);
			}
		}
	}

	// @Override
	public boolean contains(Object object) {
		return false;
	}

}
