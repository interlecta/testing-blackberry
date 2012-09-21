package com.f1rst.blackberry;

import java.util.Timer;
import java.util.TimerTask;

import net.rim.device.api.system.Application;

import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.Settings;

/**
 * Background application that will pool for updates of news/promotions.
 * @author ivaylo
 */
public class F1rstBackgroundApplication extends Application {

    private static Timer timer;
    private static TimerTask task;

    public static int period = 15 * 60 * 1000;


    public F1rstBackgroundApplication() {
//        launchPollingThread();
    }

    private static void checkForUpdate() {
        // on start up - start the timer
        //read updateInterval
        int interval = 15 * 60 * 1000; //15 min
        final Settings settings = Settings.getSettings();
        final Model model = Model.getModel();
        model.setSettings(settings);
        int i = Model.getModel().getSettings().getSettingsTable().getUpdateInterval();
        if (i == 1) {
            interval += interval; //30 min
        } else if (i == 2) {
            interval *= 4; //60 min
        }

        period = interval;

        //invokeService //news/promotions
//        DefaultController d = new DefaultController();
//        d.pollForUpdates(new OKDialogListener() {
//
//            public void performAction(Object o) {
//                if (o instanceof Integer) {
//                    int i = ((Integer) o).intValue();
//                    if (i == 1) {
//                        //new updates
//                        NotificationFactory.registerIndicator();
//                        NotificationFactory.updateValue(1);
//                    } else if (i == 0) {
//                        //no updates
//                        NotificationFactory.registerIndicator();
//                        NotificationFactory.updateValue(0);
//                    }
//                }
//            }
//        });
        //notify if neccessery
    }

    static void launchPollingThread() {
        getTimer().schedule(getTask(), 0, period);
    }

    static Timer getTimer() {
        if(timer == null){
            timer = new Timer();
        }

        return timer;
    }

    static TimerTask getTask() {
        if(task == null){
            task = new TimerTask() {
                public void run() {
                    checkForUpdate();
                }
            };
        }

        return task;
    }

}
