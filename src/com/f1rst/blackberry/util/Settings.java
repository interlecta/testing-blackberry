package com.f1rst.blackberry.util;

import com.f1rst.blackberry.ImageCache;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.model.LoginResult;
import com.f1rst.blackberry.model.SettingsTable;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;

public class Settings implements net.rim.device.api.util.Persistable {

    private static final long MODELKEY = 0x4562bbb0731b594L;
    private static final long MODELKEY_IMAGE_CHACHE = 0x54176b312340bafL;

    private static Settings SINGLETONINSTANCE;

    //default value - English
    public int languageIndex = 0;
    
    //default value - Auto
    public int connectionSuffixIndex = 0;
    
//    private Permissions permissions;
    private SettingsTable settingsTable;

    //credentials
    private String userName;
    private String password;
    private boolean saveCredentials;

    private LoginResult loginResult;
    
    public void setLoginResult(LoginResult loginResult) {
		this.loginResult = loginResult;
	}
    
    public LoginResult getLoginResult() {
    	if(loginResult == null) {
    		loginResult = new LoginResult();
    	}
		return loginResult;
	}
    
    public int getConnectionSuffixIndex() {
        return connectionSuffixIndex;
    }

    public void setConnectionSuffixIndex(int connectionSuffixIndex) {
        this.connectionSuffixIndex = connectionSuffixIndex;
    }
 
//    public Permissions getPermissions() {
//        if(permissions==null){
//            permissions = new Permissions();
//        }
//        return permissions;
//    }

    public SettingsTable getSettingsTable() {
        if(settingsTable == null){
            settingsTable = new SettingsTable();
        }
        return settingsTable;
    }

//    public void setPermissions(Permissions permissions) {
//        this.permissions = permissions;
//    }

    public void setSettingsTable(SettingsTable settingsTable) {
        this.settingsTable = settingsTable;
    }
 
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Settings() {
    }

    private Settings(String dummy) {
        Object tempModel;
        
        //loading from persistent store
        PersistentObject persist = PersistentStore.getPersistentObject( MODELKEY );
        tempModel = persist.getContents();

        //load images from cache if exists
        restoreImageCache();

        //return it
        if(tempModel != null && tempModel instanceof Settings){
            Logger.log("tempModel != null && tempModel instanceof Settings");
            SINGLETONINSTANCE = (Settings) tempModel;
        }
        else {
            //create and return
            SINGLETONINSTANCE = new Settings();
        }

    }

    public static Settings getSettings() {
         if(SINGLETONINSTANCE == null){
             new Settings("dummy");
         }
         return SINGLETONINSTANCE;
    }
    
    public boolean isSaveCredentials() {
        return saveCredentials;
    }
    
    public void setSaveCredentials(boolean saveCredentials) {
        this.saveCredentials = saveCredentials;
    }

    public void commit() {
        PersistentObject persist = PersistentStore.getPersistentObject( MODELKEY );
        persist.setContents(SINGLETONINSTANCE);
        persist.commit();

        commitImageCache(ImageCache.getInstance());//.getData());
    }

    public void commitImageCache(ImageCache imageCache) {
        PersistentObject persist = PersistentStore.getPersistentObject( MODELKEY_IMAGE_CHACHE );
        persist.setContents(imageCache);
        persist.commit();
    }

    public void restoreImageCache() {
        PersistentObject persist = PersistentStore.getPersistentObject( MODELKEY_IMAGE_CHACHE );
        Object o = persist.getContents();
        if(o!=null && o instanceof ImageCache) {

            ImageCache.getInstance().setData(((ImageCache)o).getData());
        }
    }

}
