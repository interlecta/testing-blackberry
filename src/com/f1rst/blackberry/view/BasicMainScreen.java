package com.f1rst.blackberry.view;

import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Model;

import net.rim.device.api.system.Memory;
import net.rim.device.api.system.MemoryStats;

/**
 * Main Screen for some UI components.
 *
 * - handle debug level one messages in the title
 * - key listener for main menu links, ... auto loading
 *
 * @author ivaylo
 */
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

//        if (sc.equalsIgnoreCase("s")) {//search categories
//            index = 0;
//            itemSelected();
//        }
//        if (sc.equalsIgnoreCase("b")) {//brands
//            index = 1;
//            itemSelected();
//        }
//        if (sc.equalsIgnoreCase("p")) {//promotions
//            index = 2;
//            itemSelected();
//        }
//        if (sc.equalsIgnoreCase("n")) {//news and events
//            index = 3;
//            itemSelected();
//        }
//        if (sc.equalsIgnoreCase("c")) {//cart
//            index = 4;
//            itemSelected();
//        }
//        if (sc.equalsIgnoreCase("f")) {//favorites
//            index = 5;
//            itemSelected();
//        }

        return super.keyChar(key, status, time);
    }

//    public boolean trackwheelClick(int status, int time) {
//        itemSelected();
//        invalidate();
//        return true;
//    }

    private void itemSelected() {
        switch (index) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:

                break;
            case 5:
                break;
        }
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
