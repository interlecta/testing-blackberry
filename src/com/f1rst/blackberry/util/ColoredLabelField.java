package com.f1rst.blackberry.util;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;

public class ColoredLabelField extends LabelField {

    int fontColor;

    public ColoredLabelField(int color) {
        super();
        fontColor = color;
    }

    public ColoredLabelField(int color, String label) {
        super(label);
        fontColor = color;
    }
    
    public ColoredLabelField(int color, String label, long style) {
        super(label, style);
        fontColor = color;
        //this.setFont(Font.getDefault().derive(Font.BOLD));
    }

    public void paint(Graphics graphics) {
        graphics.setColor(fontColor);
        super.paint(graphics);
    }
    
}