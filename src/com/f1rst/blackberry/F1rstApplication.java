package com.f1rst.blackberry;

import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.Settings;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.UiApplication;

public class F1rstApplication extends UiApplication {

    public final static int W = Display.getWidth();
    public final static int H = Display.getHeight();
    
    static DefaultController controller;

    public static void main(String[] args) {
    	F1rstApplication theApp = new F1rstApplication();
        theApp.enterEventDispatcher();      
    }
    
    public F1rstApplication() {
    	new Thread(new Runnable() {
            public void run(){
                createApplicationComponents();
            }
        }).start();
    }
    
    public static boolean isSimulator() {
        return DeviceInfo.isSimulator();
    }
    
    public static void setController(DefaultController controller) {
    	F1rstApplication.controller = controller;
    }
    
    public static DefaultController getController() {
        return controller;
    }
    
    private void createApplicationComponents() {
        final Settings settings = Settings.getSettings();

        final Model model = Model.getModel();
        model.setSettings(settings);
        final DefaultController controller = new DefaultController();
        F1rstApplication.setController(controller);
        Runnable r2 = new Runnable() {
        	public void run() {
//        		add views, in example:
//        		SomeView sv = new SomeView(controller);
//        		controller.addView(sv);
//              controller.addView(MenuScreen.getMenuScreen(controller));
                    
                controller.addModel(model);

                model.setSettings(settings);

                controller.show();
                
             }
         };
         new Thread(r2).start();
    }

}
