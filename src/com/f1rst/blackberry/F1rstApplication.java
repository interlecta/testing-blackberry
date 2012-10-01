package com.f1rst.blackberry;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.UiApplication;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.Settings;
import com.f1rst.blackberry.view.GlobalStatusScreen;
import com.f1rst.blackberry.view.LoginView;
import com.f1rst.blackberry.view.MenuScreen;
import com.f1rst.blackberry.view.MenuView;
import com.f1rst.blackberry.view.ProfileView;
import com.f1rst.blackberry.view.RegisterView;
import com.f1rst.blackberry.view.SampleLoginView;
import com.f1rst.blackberry.view.ScreenViewController;
import com.f1rst.blackberry.view.StatusView2;
import com.f1rst.blackberry.view.Test;

public class F1rstApplication extends UiApplication {

    public final static int W = Display.getWidth();
    public final static int H = Display.getHeight();        

    public static void main(String[] args) {

//        if ( args != null && args.length > 0  && args[0].equals("gui") ) {
            F1rstApplication theApp = new F1rstApplication();
            theApp.enterEventDispatcher();
    }
    
    static DefaultController controller;

    public F1rstApplication() {

//        new Thread(new Runnable() {
//            public void run(){
                createApplicationComponents();
//    	pushScreen(new LoginView());
//            }
//        }).start();
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
                	
                	StatusView2 sv2 = new StatusView2(controller);
                	
                    LoginView loginView = new LoginView(controller);
                    SampleLoginView slv = new SampleLoginView(controller);
                    ScreenViewController svc = new ScreenViewController(controller);
                    
                    GlobalStatusScreen sssv = new GlobalStatusScreen(controller);
                    RegisterView rv = new RegisterView(controller);
                    MenuView mv = new MenuView(controller);
                    ProfileView pv = new ProfileView(controller);
//                    Test t = new Test(controller);
//                    MenuScreen ms = new MenuScreen(controller);
                    
                    
                    controller.addView(sv2);
                    
                    controller.addView(loginView);
                    controller.addView(slv);
                    controller.addView(svc);
                                       
                    controller.addView(sssv);
                    controller.addView(rv);
                    controller.addView(mv);
                    controller.addView(pv);
//                    controller.addView(ms);
                    //controller.addView(related_pdv);
                    
//                    controller.addView(MenuScreen.getMenuScreen(controller));
                    
//                    controller.addView(t);
                    
                    controller.addModel(model);

                    model.setSettings(settings);

                    final UiApplication ui = UiApplication.getUiApplication();
            		
            			Logger.log("log");
            			ui.pushScreen(loginView);
                }
            };
            invokeLater(r2);
    }
}
