package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.container.HorizontalFieldManager;

/**
 * A manager with specific width and height
 * @author ivaylo
 */
public class HorizontalSpacerManager extends HorizontalFieldManager {

    int width = 0;
    int height = 0;


    public HorizontalSpacerManager(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    protected void sublayout(int maxWidth, int maxHeight) {
        super.sublayout(width, height);
    }
}
