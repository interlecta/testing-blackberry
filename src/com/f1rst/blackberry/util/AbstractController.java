package com.f1rst.blackberry.util;

import java.util.Vector;


/**
 *
 * @author ivaylo
 */
public abstract class AbstractController implements PropertyChangeListener {

    private Vector registeredViews;
    private Vector registeredModels;

    public AbstractController() {
        registeredViews = new Vector();
        registeredModels = new Vector();
    }

    public void addModel(Model model) {
        registeredModels.addElement(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(Model model) {
        registeredModels.removeElement(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractViewPanel view) {
        registeredViews.addElement(view);
    }

    public void removeView(AbstractViewPanel view) {
        registeredViews.removeElement(view);
    }


    //  Use this to observe property changes from registered models
    //  and propagate them on to all the views.


    public void propertyChange(PropertyChangeEvent evt) {

        for (int i = 0; i < registeredViews.size(); i++) {
            AbstractViewPanel view = (AbstractViewPanel)registeredViews.elementAt(i);
            view.modelPropertyChange(evt);
        }
    }


    /**
     * This is a convenience method that subclasses can call upon
     * to fire property changes back to the models. This method
     * uses reflection to inspect each of the model classes
     * to determine whether it is the owner of the property
     * in question. If it isn't, a NoSuchMethodException is thrown,
     * which the method ignores.
     *
     * @param propertyName = The name of the property.
     * @param newValue = An object that represents the new value
     * of the property.
     */
    protected void setModelProperty(String propertyName, Object newValue) { 
    }

    public Vector getRegisteredModels() {
        return registeredModels;
    }

    public Vector getRegisteredViews() {
        return registeredViews;
    }

}

