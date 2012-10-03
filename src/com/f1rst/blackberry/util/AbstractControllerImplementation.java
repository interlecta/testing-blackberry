package com.f1rst.blackberry.util;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.model.LoginResult;

/**
 *
 * @author ivaylo
 */
public class AbstractControllerImplementation extends AbstractController {

     protected void setModelProperty(String propertyName, Object newValue) {

    	if(propertyName.equals(DefaultController.SET_LOGIN_RESULT)) {
    		Logger.log("0");
             for (int i = 0; i < getRegisteredModels().size(); i++) {
                 if(getRegisteredModels().elementAt(i) instanceof Model){
                     Model object = (Model)getRegisteredModels().elementAt(i);
                     object.setLoginResult((LoginResult)newValue);
                 }
             }
        } else        	       
        if(propertyName.equals(DefaultController.SET_STATUS_MESSAGE)) {
            for (int i = 0; i < getRegisteredModels().size(); i++) {
                if(getRegisteredModels().elementAt(i) instanceof Model){
                    Model object = (Model)getRegisteredModels().elementAt(i);
                    object.setStatusMessage(((String) newValue));
                }
            }
        } else
        if(propertyName.equals(DefaultController.SET_IS_STATUS_SHOWN)) {
            for (int i = 0; i < getRegisteredModels().size(); i++) {
                if(getRegisteredModels().elementAt(i) instanceof Model){
                    Model object = (Model)getRegisteredModels().elementAt(i);
                    object.setIsStatusShown(((Boolean) newValue).booleanValue());
                }
            }
        }
    	
    }
}
