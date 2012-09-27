package com.f1rst.blackberry.util;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.model.LoginResult;
import com.f1rst.blackberry.model.SettingsTable;
import com.f1rst.blackberry.ui.component.ToolbarManager;

/**
 *
 * @author ivaylo
 */
public class Model extends AbstractModel {

    private static Model staticModel = null;

    public final static boolean DEBUG = true;
    public final static boolean SIMULATEINTERNET = true;
    public final static boolean DEVELOPMENT = true;

    /**
     * dinamically updated by the build script
     */
    public final static String VERSION  = "@VERSION@";   

    boolean isStatusShown = false;

    boolean isMainMenuShown = false;
 
    Settings settings;
    
    /**
     * for managing debug level one.
     * This level means that display some extra info on the display.
     * For example it will allow to see threadcounts and memory usage in title.
     * It will allow to see extra ui markers for ui elements, that implements it.
     */
    private boolean debugLevelOne;

	private String activeToolbarItem = ToolbarManager.ACTIVE_F1RST;

    private Model() {
        staticModel = this;
    }

    public synchronized static Model getModel() {
        if(staticModel == null){
            new Model();
        }
        return staticModel;
    }

    public SettingsTable getSettingsTable() {
        return getModel().getSettings().getSettingsTable();
    }

    public void setSettingsTable(SettingsTable settingsTable) {
        propertyChangeSupport.firePropertyChange(DefaultController.SET_SETTINGS_TABLE, this.getModel().getSettingsTable(), settingsTable);
        getModel().getSettings().setSettingsTable(settingsTable);
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public String getUserName() {
        return getModel().getSettings().getUserName();
    }

    public String getPassword() {
        return getModel().getSettings().getPassword();
    }

    public void setUserName(String userName) {
        propertyChangeSupport.firePropertyChange(DefaultController.SET_USERNAME, this.getModel().getUserName(), userName);
        getModel().getSettings().setUserName(userName);
    }

    public void setPassword(String password) {
        propertyChangeSupport.firePropertyChange(DefaultController.SET_PASSWORD, this.getModel().getPassword(), password);
        getModel().getSettings().setPassword(password);
    }

    public void setSettingOne(String id) {
        Logger.log("set SettingOne id: " + id);
        getModel().getSettings().getSettingsTable().setSettingOne(id);
        propertyChangeSupport.firePropertyChange(DefaultController.SET_SETTING_ONE, this.getModel().getSettingOne(), id);
    } 

    public void setAccessToken(String accessToken) {
        Logger.log("set Access Token: " + accessToken);
        getModel().getSettings().getSettingsTable().setAccessToken(accessToken);
        propertyChangeSupport.firePropertyChange(DefaultController.SET_ACCESS_TOKEN, this.getModel().getAccessToken(), accessToken);
    }

    public String getAccessToken() {
        return getModel().getSettings().getSettingsTable().getAccessToken();
    }

     

    public String getSettingOne() {
        return getModel().getSettings().getSettingsTable().getSettingOne();
    }

    

//    public void set****(String[] ***){
//        propertyChangeSupport.firePropertyChange(DefaultController.SET_***, null, ***);
//    }
    

    public void setStatusMessage(String statusMessage) {
        propertyChangeSupport.firePropertyChange(DefaultController.SET_STATUS_MESSAGE, null, statusMessage);
    }  

    public static String getVERSION() {
        return VERSION;
    }

    public boolean getIsStatusShown() {
        return getModel().isStatusShown;
    }

    public void setIsStatusShown(boolean isStatusShown) {
        propertyChangeSupport.firePropertyChange(DefaultController.SET_IS_STATUS_SHOWN,
                new Boolean(this.getModel().isStatusShown),
                new Boolean(isStatusShown));
        this.getModel().isStatusShown = isStatusShown;
    }


    public void setDebugLevelOne(boolean debugLevelOne) {
        this.debugLevelOne = debugLevelOne;
    }

    public boolean isDebugLevelOne() {
        return debugLevelOne;
    }

	public int getUrlIndex() {
		return 0;
	}
	
	public void setLoginResult(LoginResult lr){
        getModel().getSettings().getLoginResult().setResult(lr.getResult());
        propertyChangeSupport.firePropertyChange(DefaultController.SET_LOGIN_RESULT, null, lr);		
	}

	public String getActiveToolbarItem() {
		return getModel().activeToolbarItem;
	}
}
