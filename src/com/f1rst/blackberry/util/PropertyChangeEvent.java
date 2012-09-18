package com.f1rst.blackberry.util;

public class PropertyChangeEvent {

    Object source;
    String propertyName;
    Object oldValue;
    Object newValue;
    
    //reserved
    Object propagationId;

    public PropertyChangeEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Object getSource() {
        return source;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    //The "propagationId" field is reserved for future use.
    public Object getPropagationId() {
        return propagationId;
    }

    void setPropagationId(Object propagationId) {
        this.propagationId = propagationId;
    }
}