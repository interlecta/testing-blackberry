package com.f1rst.blackberry.view;

import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Model;

import net.rim.device.api.system.Memory;
import net.rim.device.api.system.MemoryStats;

public class BasicMainScreen extends ApplicationMainScreen {

    protected DefaultController controller;
    private int index;

    /**
     * 
     */
    public BasicMainScreen() {
    	
    }
    		
    public BasicMainScreen(DefaultController controller) {
        super();
        this.controller = controller;
    }
    
    public BasicMainScreen(DefaultController controller, long arguments) {
        super(arguments);
        this.controller = controller;
    }

    public void updateTitle(String newTitle) {
        if(newTitle == null) newTitle = "";

        if(Model.getModel().isDebugLevelOne()) {
            newTitle += " T:" + String.valueOf(Thread.activeCount()) + " A:"
                        + getAlocated()+ " F:" + getFreeRam();
        }

        super.updateTitle(newTitle);
    }

    protected boolean keyChar(char key, int status, int time) {

        char c[] = {key};
        String sc = new String(c);

        return super.keyChar(key, status, time);
    }

//    public boolean trackwheelClick(int status, int time) {
//        itemSelected();
//        invalidate();
//        return true;
//    }

    private void itemSelected() {
//        switch (index) {
//            
//        }
    }

    String getFreeRam() {
        MemoryStats memoryStats = Memory.getRAMStats();
        int i = memoryStats.getFree()/1024;

        return String.valueOf(i);
    }

    String getAlocated() {
        MemoryStats memoryStats = Memory.getRAMStats();
        int i = memoryStats.getAllocated()/1024;

        return String.valueOf(i);
    }
    
    public DefaultController getController() {
		return controller;
	}

}