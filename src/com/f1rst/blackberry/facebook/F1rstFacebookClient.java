/**
 * Copyright (c) E.Y. Baskoro, Research In Motion Limited.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * This License shall be included in all copies or substantial 
 * portions of the Software.
 * 
 * The name(s) of the above copyright holders shall not be used 
 * in advertising or otherwise to promote the sale, use or other 
 * dealings in this Software without prior written authorization.
 * 
 */
package com.f1rst.blackberry.facebook;


import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.facebook.ui.Action;
import com.f1rst.blackberry.facebook.ui.ActionListener;
import com.f1rst.blackberry.facebook.ui.LoginScreen;
import com.f1rst.blackberry.facebook.ui.LogoutScreen;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.util.DefaultController;

import net.rim.device.api.applicationcontrol.ApplicationPermissions;
import net.rim.device.api.applicationcontrol.ApplicationPermissionsManager;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.VirtualKeyboard;
import net.rim.device.api.ui.component.Dialog;

public class F1rstFacebookClient implements ActionListener {

    // Constants
    public final static String NEXT_URL = "http://www.facebook.com/connect/login_success.html";
    public final static String APPLICATION_ID = "445031458872299";
    private final static long persistentObjectId = 0xa54a1b1a0abda903L;
    private static PersistentObject store;
    private LoginScreen loginScreen;
    private LogoutScreen logoutScreen;
    private FacebookContext fbc;
    private UiApplication app;
    private DefaultController controller;

    public final static String F1RST_PERMISSIONS = "offline_access,user_events,friends_events,email";
    public final static String F1RST_FACEBOOK_DISPLAY = VirtualKeyboard.isSupported()?"touch":"wap";


    public F1rstFacebookClient(UiApplication app, DefaultController controller, boolean logout) {

        this.app = app;
        this.controller = controller;

        checkPermissions();
        init();

//        if ((fbc != null) && fbc.hasValidAccessToken()) {
//
////			homeScreen = new HomeScreen(fbc);
////			homeScreen.addActionListener(this);
////			pushScreen(homeScreen);
//            app.getApplication().invokeLater(new Runnable() {
//
//                public void run() {
//                    FBUser u = new FBUser("me", fbc.getAccessToken());
//                    String id = u.getId();
//                    F1rstApplication.getController().setModelProperty(
//                            DefaultController.SET_FACEBOOK_ID,
//                            id);
//                    F1rstApplication.getController().setModelProperty(
//                            DefaultController.SET_FACEBOOK_ACCESS_TOKEN,
//                            fbc.getAccessToken());
//                }
//            });
//
//
//        } else
        {
            if(!logout) {
                loginScreen = new LoginScreen(fbc);
                loginScreen.addActionListener(this);
                app.pushScreen(loginScreen);
            }
        }

    }

    private void init() {
        store = PersistentStore.getPersistentObject(persistentObjectId);
//        synchronized (store) {
//            //if (store.getContents() == null) {
//                store.setContents(new FacebookContext(NEXT_URL, APPLICATION_ID));
//                store.commit();
//            //}
//        }
//        fbc = (FacebookContext) store.getContents();
        fbc = new FacebookContext(NEXT_URL, APPLICATION_ID);
    }

    private void checkPermissions() {

        ApplicationPermissionsManager apm = ApplicationPermissionsManager.getInstance();
        ApplicationPermissions original = apm.getApplicationPermissions();

        if ((original.getPermission(ApplicationPermissions.PERMISSION_INPUT_SIMULATION) == ApplicationPermissions.VALUE_ALLOW) && (original.getPermission(ApplicationPermissions.PERMISSION_DEVICE_SETTINGS) == ApplicationPermissions.VALUE_ALLOW) && (original.getPermission(ApplicationPermissions.PERMISSION_CROSS_APPLICATION_COMMUNICATION) == ApplicationPermissions.VALUE_ALLOW) && (original.getPermission(ApplicationPermissions.PERMISSION_INTERNET) == ApplicationPermissions.VALUE_ALLOW) && (original.getPermission(ApplicationPermissions.PERMISSION_SERVER_NETWORK) == ApplicationPermissions.VALUE_ALLOW) && (original.getPermission(ApplicationPermissions.PERMISSION_EMAIL) == ApplicationPermissions.VALUE_ALLOW)) {
            return;
        }

        ApplicationPermissions permRequest = new ApplicationPermissions();
        permRequest.addPermission(ApplicationPermissions.PERMISSION_INPUT_SIMULATION);
        permRequest.addPermission(ApplicationPermissions.PERMISSION_DEVICE_SETTINGS);
        permRequest.addPermission(ApplicationPermissions.PERMISSION_CROSS_APPLICATION_COMMUNICATION);
        permRequest.addPermission(ApplicationPermissions.PERMISSION_INTERNET);
        permRequest.addPermission(ApplicationPermissions.PERMISSION_SERVER_NETWORK);
        permRequest.addPermission(ApplicationPermissions.PERMISSION_EMAIL);

        boolean acceptance = ApplicationPermissionsManager.getInstance().invokePermissionsRequest(permRequest);

        if (acceptance) {
            // User has accepted all of the permissions.
            return;
        } else {
        }
    }

    public static void saveContext(FacebookContext pfbc) {
//        if(store == null){
//            store = PersistentStore.getPersistentObject(persistentObjectId);
//        }
//        synchronized (store) {
//            store.setContents(pfbc);
//            store.commit();
//        }
    }

    public void logoutAndExit() {
        saveContext(null);

        logoutScreen = new LogoutScreen(fbc);
        logoutScreen.addActionListener(this);
        logoutScreen.fetch();
    }

    public void saveAndExit() {
        saveContext(fbc);
        exit();
    }

    private void exit() {
//        AppenderFactory.close();
        System.exit(0);
    }

    public void onAction(Action event) {

        if (event.getSource() == loginScreen) {
            if (event.getAction().equals(LoginScreen.ACTION_LOGGED_IN)) {
                try {
                    Logger.log("on Action: " + event.getAction() +  " data:" + (String) event.getData());
                    fbc.setAccessToken((String) event.getData());

//						if (homeScreen == null) {
//							homeScreen = new HomeScreen(fbc);
//							homeScreen.addActionListener(this);
//						}
//						pushScreen(homeScreen);


                    Dialog.inform("The application is now connected with facebook.");

                    // passing these arguments to shizzlr app.
                    FBUser u = new FBUser("me", fbc.getAccessToken());
                    String id = u.getId();
                    F1rstApplication.getController().setModelProperty(
                            DefaultController.SET_FACEBOOK_ID,
                            id);
                    F1rstApplication.getController().setModelProperty(
                            DefaultController.SET_FACEBOOK_ACCESS_TOKEN,
                            fbc.getAccessToken());

                } catch (Throwable t) {
                    Dialog.alert("Error: " + t.getMessage());
                }

            } else if (event.getAction().equals(LoginScreen.ACTION_ERROR)) {
                Dialog.alert("Error: " + event.getData());
            }

        } else if (event.getSource() == logoutScreen) {
            if (event.getAction().equals(LogoutScreen.ACTION_LOGGED_OUT)) {
                exit();
            }

        }
    }
}
