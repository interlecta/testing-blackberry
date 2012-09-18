package com.f1rst.blackberry.model;

import net.rim.device.api.util.Persistable;

public class SettingsTable implements Persistable {

    private String settingOne = "0";
    private String accessToken;
    
    int updateInterval;
    
    public SettingsTable() {
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setSettingOne(String settingOne) {
        if(settingOne!=null && settingOne.length()>0)
            this.settingOne = settingOne;
    }
 
    public String getAccessToken() {
        return accessToken;
    }

    public String getSettingOne() {
        return settingOne;
    }   

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }
}