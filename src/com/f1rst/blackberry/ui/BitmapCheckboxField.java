package com.f1rst.blackberry.ui;

/**
 *
 * @author ivaylo
 */


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.CheckboxField;

public class BitmapCheckboxField extends CheckboxField {

    int width;
    int height;

    Bitmap selectedBackground;
    Bitmap notSelectedBackground;

    private Bitmap selectedIcon;
    private Bitmap notSelectedIcon;

    boolean isSelected = false;

    private String label;

    public BitmapCheckboxField(String label, boolean checked, long style) {
        super(label, false, style);

        this.label = label;

        if (selectedBackground != null) {
            this.width = selectedBackground.getWidth();
            this.height = selectedBackground.getHeight();
        }

    }

    public void setSelectedBackground(Bitmap selectedBackground) {
        this.selectedBackground = selectedBackground;
    }

    public void setSelectedIcon(Bitmap selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public void setNotSelectedIcon(Bitmap notSelectedIcon) {
        this.notSelectedIcon = notSelectedIcon;
    }

    public void setNotSelectedBackground(Bitmap notSelectedBackground) {
        this.notSelectedBackground = notSelectedBackground;
    }

    protected boolean keyChar(char key, int status, int time) {

        if (key == Keypad.KEY_ENTER) {
            if (this.getChangeListener() != null) {
                this.getChangeListener().fieldChanged(this, 0);
            }
            return true;
        } else {
            return super.keyChar(key, status, time);
        }
    }

    /*public boolean trackwheelClick(int status, int time) {
        return true;
    }*/
        
    public void paint(Graphics graphics) {

        graphics.setColor(0x444444);
        graphics.fillRect(0, 0, width - 1, height - 1);

        if (isSelected) {
            if (selectedBackground != null) {
                graphics.drawBitmap(0, 0, width, height, selectedBackground, 0, 0);
            }
        } else {
            if (notSelectedBackground != null) {
                graphics.drawBitmap(0, 0, width, height, notSelectedBackground, 0, 0);
            }
        }

        //draw icon for checked stated
        if(selectedIcon != null && notSelectedIcon != null) {
            if (getChecked()) {
                graphics.drawBitmap(20, (height - selectedIcon.getHeight()) / 2, selectedIcon.getWidth(), selectedIcon.getHeight(), selectedIcon, 0, 0);
            } else {
                graphics.drawBitmap(20, (height - selectedIcon.getHeight()) / 2, selectedIcon.getWidth(), selectedIcon.getHeight(), notSelectedIcon, 0, 0);
            }
        }

        //draw label
        Font f = Font.getDefault().derive(Font.PLAIN, 24);
        graphics.setFont(f);
        graphics.setColor(Color.WHITESMOKE);
        graphics.drawText(label, 60, (height - f.getHeight()) / 2);
    }

    protected void layout(int maxWidth, int maxHeight) {
        setExtent(width, height);
    }

    public boolean isFocusable() {
        return true;
    }

//    protected void drawFocus(Graphics graphics, boolean on) {
//        // Don't draw the default focus
//    }

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
}
