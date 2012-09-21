package com.f1rst.blackberry.view;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.model.LoginResult;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.PropertyChangeEvent;

/**
 * Screen View Controller
 * @author ivaylo
 */
public class ScreenViewController implements AbstractViewPanel {
    private DefaultController controller;

    private String id;
    private String at;

    public ScreenViewController(DefaultController controller) {
        this.controller = controller;
    }

    public void modelPropertyChange(PropertyChangeEvent evt) {
    	if(evt.getPropertyName().equals(controller.SET_LOGIN_RESULT)) {
    		//MenuScreen.getMenuScreen(controller);
            //MenuScreen.getMenuScreen(controller).setUpdates(u);
            controller.pushScreen(MenuScreen.getMenuScreen(controller));
    	} else        
        if(evt.getPropertyName().equals(controller.SET_SETTING_ONE)){
            //
        	id = (String) evt.getNewValue();
        } else
        if(evt.getPropertyName().equals(controller.SET_ACCESS_TOKEN)) {
        }
    }

}
