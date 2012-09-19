package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;

/**
 * A field with a custom width and height, basically used to show
 * where is the focus on the screen.
 *
 * @author ivaylo
 */
public class SpacerEditField extends LabelField {

    int width;
    int height;

    boolean isSelected;

//    public SpacerEditField(int width, int height) {
//        super("", "", 5, Field.NON_FOCUSABLE|Field.READONLY);
//        this.width = width;
//        this.height = height;
//    }

    public SpacerEditField(int width, int height, long style, int fontHeight) {
//        super("", " ", 5, style);
        super("", style);
        this.width = width;
        this.height = height;
        setFont(Font.getDefault().derive(Font.PLAIN, height));
    }   

    protected void layout(int maxWidth, int maxHeight) {
        setExtent(width, height);
    }

    public boolean isFocusable() {
        return true;
    }

    protected void drawFocus(Graphics graphics, boolean on) {
        // Don't draw the default focus
    }

    protected void onFocus(int direction) {
        super.onFocus(direction);
        isSelected = true;
        invalidate();
    }

    protected void onUnfocus() {
        super.onUnfocus();
        isSelected = false;
        invalidate();
    }

//    @Override
    protected void paint(Graphics graphics) {
        if(isSelected) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(0, 0, 20, 20);
        } else {
            graphics.clear();
        }
    }
}
