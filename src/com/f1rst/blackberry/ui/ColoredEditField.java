package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.EditField;

/**
 * @author ivaylo
 */
public class ColoredEditField extends EditField {

    int fontColor;

    int titleColor = -1;

    String title;

    boolean right = false;

    public ColoredEditField(int color) {
        super();
        fontColor = color;
    }

    public ColoredEditField(int color, String label) {
        super("",label);
        fontColor = color;
    }

    public ColoredEditField(int color, String title, String label) {
        super(title, label);
        fontColor = color;
    }
    
    public ColoredEditField(int color, String title, String text, long style) {
        super(title, text, 500, style);
        fontColor = color;
    }


    public ColoredEditField(int labelColor, int color, String title, String label, long style) {
        super(title, label, 500, style);
        fontColor = color;
    }

    public ColoredEditField(int color, String title, String text, int maxSize, long style) {
        super(title, text, maxSize, style);
        this.fontColor= color;
    }

    public void setRight(boolean right) {
        this.right = right;
        invalidate();
    }
    
    String getTextWithSameWidth(String s){
        String t = "";
        int w = getFont().getAdvance(s);
        for (int i = 0; s!=null && i < w; ) {
            t += " ";
            i = getFont().getAdvance(t);
        }
        return t;
    }
    public ColoredEditField(int titleColor, int color, String title, String text, int maxSize, long style) {
        super("", text, maxSize, style);
        String t = getTextWithSameWidth(title);
        setLabel(t);

        this.title = title;
        this.fontColor= color;
        this.titleColor = titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

////    @Override
//    protected void drawFocus(Graphics graphics, boolean on) {
//        if(on) {
//            graphics.setColor(Color.RED);
//            graphics.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
//        }
//      //  super.drawFocus(graphics, on);
//    }


     public void paint(Graphics g) {
//        //draws title with different color
//        if (titleColor == -1) {
//            titleColor = fontColor;
//        }
//
//        g.setColor(titleColor);
//        g.setFont(getFont());
//        if (getLabel() != null && getLabel().length() > 0) {
////            if(2>1){
////                g.setColor(Color.BLACK);
////                g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
////                g.setColor(Color.BLUE);
////                g.drawText(getLabel() + String.valueOf(150), 150/2, 0, g.RIGHT|g.TOP,150);
////            } else {
//                g.drawText(getLabel(), 0, 0);
////            }
//        } else if (title != null) {
//            g.drawText(title, 0, 0);
//        }

        g.setColor(fontColor);
//        if(!right)
            super.paint(g);
    }
//     public void paint(Graphics g) {
//        //draws title with different color
//        if (titleColor == -1) {
//            titleColor = fontColor;
//        }
//
//        g.setColor(titleColor);
//        g.setFont(getFont());
//        if (getLabel() != null && getLabel().length() > 0) {
////            if(2>1){
////                g.setColor(Color.BLACK);
////                g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
////                g.setColor(Color.BLUE);
////                g.drawText(getLabel() + String.valueOf(150), 150/2, 0, g.RIGHT|g.TOP,150);
////            } else {
//                g.drawText(getLabel(), 0, 0);
////            }
//        } else if (title != null) {
//            g.drawText(title, 0, 0);
//        }
//
//        g.setColor(fontColor);
////        if(!right)
//            super.paint(g);
//    }

    //@Override
    public void setText(String text) throws IllegalArgumentException {
        try {
            if (text == null) {
                super.setText("---");
            } else if (text.length() == 0) {
                super.setText("---");
            } else {
                super.setText(text);
            }
        } catch (IllegalArgumentException ia) {
            //when set --- text for field with Numeric or Decimal constrains
            super.setText("");
        }
    }

    /**
     * override make context menu. it will not display any custom menu items like
     * @param contextMenu
     */
    protected void makeContextMenu(ContextMenu contextMenu){
    }

}
