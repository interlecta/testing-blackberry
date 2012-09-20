package com.f1rst.blackberry.model;

import java.util.Vector;

import net.rim.device.api.util.Persistable;

/**
 *
 * @author ivaylo
 */
public class BasePersistableTable implements Persistable {

    Vector records = new Vector();

    public BasePersistableTable() {
    }

    public void setRecords(Vector records) {
        this.records = records;
    }

    public Vector getRecords() {
        return records;
    }

    
}
