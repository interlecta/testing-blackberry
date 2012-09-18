package com.f1rst.blackberry.ui;

/**
 *
 * @author ivaylo
 */

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class BitmapButtonField extends CheckboxField {

    int width;
    int height;

    Bitmap selected;
    Bitmap notSelected;

    boolean isSelected = false;

    //enable toggle button behavior
    boolean isPressed = false;
    private boolean isToggledButton;
    private int backgroundColor;
    private int backgroundAlpha;

    public BitmapButtonField(Bitmap selected, Bitmap notSelected, String label, long style) {

        super(label, false, style);
        this.selected = selected;
        this.notSelected = notSelected;
        if (selected != null) {
            this.width = selected.getWidth();
            this.height = selected.getHeight();
        }       
        
    }

        protected boolean keyChar(char key, int status, int time) {

            if (key == Keypad.KEY_ENTER ) {
                if(this.getChangeListener() != null) {
                    this.getChangeListener().fieldChanged(this, 0);
                }
                return true;
            } else {
                return super.keyChar(key,status,time);
            }
        }

//    public boolean trackwheelClick(int status, int time) {
//        return true;
//    }

    public void paint(Graphics graphics) {

        if(isToggledButton){
            if(getChecked()) {
                setBackground(BackgroundFactory.createSolidTransparentBackground(
                        backgroundColor, backgroundAlpha));
            } else {
                setBackground(null);
            }
        }

        if (isSelected) {
            if (selected != null) {
                graphics.drawBitmap(0, 0, width, height, selected, 0, 0);
            }
        } else {
            if (notSelected != null) {
                graphics.drawBitmap(0, 0, width, height, notSelected, 0, 0);
            }
        }
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

    /**
     * override make context menu. it will not display any custom menu items like
     */
    protected void makeContextMenu(ContextMenu contextMenu){
    }

    public void setToggledButton(boolean toggled) {
        isToggledButton = toggled;
    }

    /**
     * color - 32-bit color in 0x00RRGGBB format
     * alpha - 0 (clear) to 255 (opaque). 
     */
    
    public void setBackgroundColor(int color, int alpha){
        backgroundColor = color;
        backgroundAlpha = alpha;
    }
    
    public void refresh() {
    	invalidate();
    }
}
