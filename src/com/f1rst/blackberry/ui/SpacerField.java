package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;

/**
 * A field with a custom width and height
 *
 * @author ivaylo
 */
public class SpacerField extends LabelField {

    int width;
    int height;

    public SpacerField(int width, int height) {
//        super();
        super("", Field.NON_FOCUSABLE|Field.READONLY);
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics graphics) {
    }

    protected void layout(int maxWidth, int maxHeight) {
        setExtent(width, height);
    }

    public boolean isFocusable() {
        return false;
    }

    protected void drawFocus(Graphics graphics, boolean on) {
        // Don't draw the default focus
    }
}
