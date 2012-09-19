package com.f1rst.blackberry;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.Settings;
import com.f1rst.blackberry.view.LoginView;
import com.f1rst.blackberry.view.MainView;
import com.f1rst.blackberry.view.MyScreen;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.UiApplication;

public class F1rstApplication extends UiApplication {

    public final static int W = Display.getWidth();
    public final static int H = Display.getHeight();        

    public static void main(String[] args) {

//        if ( args != null && args.length > 0  && args[0].equals("gui") ) {
    	F1rstApplication theApp = new F1rstApplication();
            theApp.enterEventDispatcher();
//        } else {
//            b.enterEventDispatcher();
//        }
    }
    
    static DefaultController controller;
    
//    private SplashScreen splashScreen;

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
                	
                	MainView mv = new MainView(controller);
                	
                    LoginView loginView = new LoginView(controller);
                    
                    
                    controller.addView(mv);
                    
                    controller.addView(loginView);
                    //controller.addView(related_pdv);
                    
                    controller.addModel(model);

                    model.setSettings(settings);

                    controller.show();
                }
            };
            new Thread(r2).start();
    }
}