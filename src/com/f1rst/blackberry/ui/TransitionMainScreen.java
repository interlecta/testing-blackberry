package com.f1rst.blackberry.ui;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class TransitionMainScreen extends MainScreen {

    //screen width and height
    int w = Display.getWidth();
    int h = Display.getHeight();

    public TransitionMainScreen() {

        super();
    }

    public TransitionMainScreen(long settings) {

        super(settings);
    }

    //screen transition parameters
    private int initialStart = 270;
    public int HorizontalOffset = w;

    private final static long animationTime = 150;//300;
    public long animationStart = 0;
    protected void sublayout(int width, int height) {
        super.sublayout(width, height);

        if (HorizontalOffset > 0) {
            if (animationStart == 0) {
                // start the animation
                animationStart = System.currentTimeMillis();
            }
            else {
                long timeElapsed = System.currentTimeMillis() - animationStart;
                if (timeElapsed >= animationTime) {
                    HorizontalOffset = 0;
                }
                else {
                    float percentDone = (float)timeElapsed / (float)animationTime;
                    HorizontalOffset = w - initialStart - (int)(percentDone * w - initialStart);
                }
            }
        }
        setPosition(HorizontalOffset, 0);
        if (HorizontalOffset > 0) {
            UiApplication.getUiApplication().invokeLater(new Runnable() {
                public void run() {
                    updateLayout();
                }
            });
        }

    }

    public boolean onClose() {
        animationStart = 0;
        return super.onClose();
    }

    public boolean onSavePrompt(){
        return true;
    }
}
