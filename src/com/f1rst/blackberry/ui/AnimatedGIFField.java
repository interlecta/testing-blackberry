package com.f1rst.blackberry.ui;

import com.f1rst.blackberry.log.Logger;

import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;

public class AnimatedGIFField extends BitmapField {

    private GIFEncodedImage image;
    private int currentFrame;

    private AnimatorThread animatorThread;

    public AnimatedGIFField(GIFEncodedImage image) {
        this(image, 0);
    }

    public AnimatedGIFField(GIFEncodedImage image, long style) {
        super(image.getBitmap(), style);

        this.image = image;
    }

    protected void paint(Graphics graphics) {
        super.paint(graphics);

        if (currentFrame != 0) {
            graphics.drawImage(image.getFrameLeft(currentFrame), image.getFrameTop(currentFrame),
                    image.getFrameWidth(currentFrame), image.getFrameHeight(currentFrame), image, currentFrame, 0, 0);
        }
    }

    protected void onUndisplay() {
        animatorThread.stop();
        super.onUndisplay();
    }

    protected void onDisplay() {
       animatorThread = new AnimatorThread(this);
       animatorThread.start();
       super.onDisplay();
    }

    private class AnimatorThread extends Thread {

        private AnimatedGIFField theField;
        private boolean keepGoing = true;
        private int totalFrames;               
        private int loopCount;                 
        private int totalLoops;               

        public AnimatorThread(AnimatedGIFField theField) {
            this.theField = theField;
            this.totalFrames = image.getFrameCount();
            this.totalLoops = image.getIterations();
            try {
                Logger.log("gif delay: " + image.getFrameDelay(0));
            } catch (Exception e) {
                Logger.log(e.getMessage());
            }

        }

        public synchronized void stop() {
            keepGoing = false;
        }

        public void run() {
            while (keepGoing) {
                UiApplication.getUiApplication().invokeAndWait(new Runnable() {

                    public void run() {
                        theField.invalidate();
                    }
                });

                try {
                    long l = image.getFrameDelay(currentFrame) * 10;
                    sleep (l);
                } catch (InterruptedException iex) {
                }

                ++currentFrame;

                if (currentFrame == totalFrames) {
                    currentFrame = 0;

                    ++loopCount;

                    if (loopCount == totalLoops) {
                        keepGoing = false;
                    }
                }
            }
        }
    }
}
