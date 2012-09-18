package com.f1rst.blackberry.ui;

import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Graphics;

public class GIFFUtility {

    private GIFEncodedImage image;
    private int current;
    private int count;

    public GIFFUtility(GIFEncodedImage image) {

        this.image = image;
        count = image.getFrameCount();
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(
                image.getFrameLeft(current),
                image.getFrameTop(current),
                image.getFrameWidth(current),
                image.getFrameHeight(current),
                image,
                current,
                0,
                0);
    }

    public void resetPosition() {
        current = 0;
    }

    public void nextPosition() {
        ++current;

        if (current == count) {
            resetPosition();
        }
    }

    public boolean hasNextPosition() {
        ++current;

        if (current == count) {
            return false;
        }
        return true;
    }
}
