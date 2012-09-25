package com.f1rst.blackberry.view;

import com.f1rst.blackberry.facebook.FBUser;
import com.f1rst.blackberry.facebook.FacebookException;
import com.f1rst.blackberry.facebook.User;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

public class Test extends ApplicationMainScreen implements AbstractViewPanel {
	private DefaultController controller;
	
	public Test(DefaultController controller) {    	
        this.controller = controller;
//        init();
    }

	private void init() {
		createFields();
		updateTitle("facebook test");
		
	}

	private void createFields() {

//		try {
			FBUser user = new FBUser("me", controller.getFacebook().getAccessToken());
			Logger.log(user.toString());
			User[] friends = user.getFriends();
			Logger.log("facebook test friends: "+friends.length);
			
//		} catch (FacebookException e) {
//			Logger.log("facebook error: "+e.toString());
//		}
		
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(DefaultController.SET_STATUS_MESSAGE)) {
            String value = (String) evt.getNewValue();
            if(value!=null && value.length()>0)
                setStatusField(value);
            else
                removeStatusField();
        }
		
		else if (evt.getPropertyName().equals(controller.SHOW_TEST)) {
        	Runnable r = new Runnable() {
                public void run() {
                    init();
//                    prepareSigninScreen();
//                    deleteAll();
//                    prepareSigninScreen();
                }
            };
                controller.pushScreen(this);
                controller.invokeLater(r);
        }
		
	}

}
