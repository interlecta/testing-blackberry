package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.EditField;

/**
 *
 * @author ivaylo
 */
public class FixedWidthEditField extends EditField {

    int color;
    int width;

    public FixedWidthEditField(int color, int width, String title, String text) {
        super(title, text);
        this.color = color;
        this.width = width;
    }      

    protected void layout(int maxWidth, int maxHeight) {
        super.layout(width, getHeight());
        setExtent(width, getHeight());
    }

    public int getPreferredWidth() {
        return width;
    }
    
    public void paint(Graphics graphics) {
        graphics.setColor(color);
        super.paint(graphics);
    }
}
