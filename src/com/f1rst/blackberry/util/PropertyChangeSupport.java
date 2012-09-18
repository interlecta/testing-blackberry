package com.f1rst.blackberry.util;

import java.util.Vector;

public class PropertyChangeSupport {

    Vector listeners = new Vector();

    Object source;

    public PropertyChangeSupport() {
    }

    public PropertyChangeSupport(Object source) {
        this.source = source;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addElement(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removeElement(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        for (int i = 0; i < listeners.size(); i++) {
            PropertyChangeListener lis = (PropertyChangeListener) listeners.elementAt(i);
            lis.propertyChange(new PropertyChangeEvent(null, propertyName, oldValue, newValue));
        }
    }
}
