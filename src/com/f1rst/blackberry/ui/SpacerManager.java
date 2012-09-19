package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 *
 * @author ivaylo
 */
public class SpacerManager extends VerticalFieldManager {

    int width = 0;
    int height = 0;

    boolean focusable = true;

    public SpacerManager(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public SpacerManager(int width, int height, boolean focusable) {
        this.width = width;
        this.height = height;
        this.focusable = focusable;
    }

    protected void sublayout(int maxWidth, int maxHeight) {
        super.sublayout(width, height);
    }

//    @Override
//    public boolean isFocus() {
//        return super.isFocus();
//    }

    public boolean isFocusable() {
        return focusable;
    }

    public void setFocusable(boolean focusable){
        this.focusable = focusable;
    }

}
