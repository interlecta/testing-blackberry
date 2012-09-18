package com.f1rst.blackberry.ui;

import java.util.Vector;

import com.f1rst.blackberry.F1rstApplication;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

public abstract class AbstractListFieldCallBack implements ListFieldCallback {

    protected Bitmap bg = null;
    protected Bitmap bgSelected = null;
    protected static final int PADDING = 2;
    Font small = Font.getDefault().derive(Font.PLAIN, 12);
    Font normal = Font.getDefault().derive(Font.PLAIN, 18);
    Font normal14 = Font.getDefault().derive(Font.PLAIN, 14);
    Font bigger = Font.getDefault().derive(Font.BOLD, 28);
    Font plain22 = Font.getDefault().derive(Font.BOLD, 22);
    Font plain24 = Font.getDefault().derive(Font.BOLD, 24);
    Font plain28 = Font.getDefault().derive(Font.BOLD, 28);
    int horizontalPadding = 2;
    int verticalPadding = 2;
    
    int color_selected = BasicTheme.listItemBackgroundSelectedColor; // 0x88ebebeb; //some gray
    int color_normal = BasicTheme.listItemBackgroundNormalColor;     // 0xfefefe; // some lighter gray, almost white

    public AbstractListFieldCallBack() {
	}
    
    /**
     * @param graphical determine if image should be used for backgraound of the list item
     */
    public AbstractListFieldCallBack(boolean graphical) {
    	if(graphical) {
    		Bitmap bg = null;
            Bitmap bgSelected = null;
            
            if(F1rstApplication.W == 320) {
            	bg = Bitmap.getBitmapResource("list-bg-320x50.png");
                bgSelected = Bitmap.getBitmapResource("list-bg-selected-320x50.png");
            } else {
            	bg = Bitmap.getBitmapResource("list-bg-480x70.png");
                bgSelected = Bitmap.getBitmapResource("list-bg-selected-480x70.png");
            }

            setBg(bg);
            setBgSelected(bgSelected);
    	}
	}
    
    protected void drawImage(Graphics graphics, int x, int y, int height, EncodedImage image) {

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int imageTop = y + ((height - imageHeight) / 2);
        int imageLeft = x + ((height - imageWidth) / 2);

        graphics.drawImage(imageLeft, imageTop, imageWidth, imageHeight, image, 0, 0, 0);
    }

    protected void drawImage(Graphics graphics, int x, int y, int height, Bitmap image) {

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int imageTop = y + ((height - imageHeight) / 2);
        int imageLeft = x + ((height - imageWidth) / 2);

        graphics.drawBitmap(imageLeft, imageTop, imageWidth, imageHeight, image, 0, 0);
    }

    protected void drawBackground(Graphics graphics, int x, int y, int width, int height, boolean selected) {
        Bitmap bitmap = null;
        if (selected) {
            bitmap = bgSelected;
        } else {
            bitmap = bg;
        }

        if (bitmap != null) {
	        if (bitmap.getWidth() >= width) {
	            graphics.drawBitmap(x, y, width, height, bitmap, 0, 0);
	        } else {
	            int imageWidth = bitmap.getWidth();
	            while (width > -2) {
	                graphics.drawBitmap(x - 1, y - 1, width + 2, height + 1, bitmap, 0, 0);
	                width -= imageWidth;
	                x += imageWidth - 2;
	            }
	        }
        } else {
        	int oldColor = graphics.getColor();
        	int color = color_normal;
        	if(selected) {
        		color = color_selected;	
        	}
        	graphics.setColor(color);
            graphics.fillRect(x, y, width, height);
            graphics.setColor(oldColor);
        }                     
    }
    
//    onFocus()

    protected void drawBorder(Graphics graphics, int x, int y, int width, int height) {

        graphics.setColor(Color.AQUA);
        //graphics.drawLine(x, y - 1, x + width, y - 1);
        //graphics.drawLine(x, y + height - 1, x + width, y + height - 1);
        graphics.drawRect(x, y, width, height);
    }

//    SingleLine of Text in the row
    protected int drawText(Graphics graphics, int x, int y, int width, int height, String text, boolean selected, int state) {
        int fontHeight = ((int) ((3 * height) / 5)) - (PADDING * 2);
        graphics.setFont(Font.getDefault().derive(Font.BOLD, fontHeight));

        if (selected) {
            graphics.setColor(Color.BLACK);
        } else {
            if (state == 1) {
                graphics.setColor(Color.RED);
            } else if (state == 2) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.GRAY);
            }
        }

        if (text != null) {
            return graphics.drawText(
                    text, x + PADDING + 3, y + PADDING + 2 + (fontHeight / 2), DrawStyle.LEFT | DrawStyle.TOP | DrawStyle.ELLIPSIS, width - x - (PADDING * 2));
        }

        return 0;
    }

    public int indexOfList(ListField listField, String prefix, int start) {
        return listField.getSelectedIndex();
    }

    public int getPreferredWidth(ListField listField) {
        return Display.getWidth();
    }

    public void setBg(Bitmap bg) {
        this.bg = bg;
    }

    public void setBgSelected(Bitmap bgSelected) {
        this.bgSelected = bgSelected;
    }

    public void draw2LineText(Graphics g, String text, int maxWidth, Font f, int x, int y, /*int width,*/ int height) {
          //g.drawRect(x, y, maxWidth, height);

//        Logger.log("draw2LineText");
//        Logger.log(text);
//        Logger.log("MaxtWidth: " + String.valueOf(maxWidth));
//        Logger.log("x: " + String.valueOf(x));
//        Logger.log("heigth: " + String.valueOf(height));
//        Logger.log("text len: " + String.valueOf(text.length()));
//        try {
            g.setFont(f);
            String loc1 = "";
            String loc2 = "";
            if (text == null) {
                g.drawText("---", x, (height - f.getHeight()) / 2);
            }
            int twidth = f.getAdvance(text);
//            Logger.log("twidth: " + String.valueOf(twidth));
            if (twidth > maxWidth) {
                //trimming
//                if(twidth > (2 * maxWidth)) {
//
//                    int charw = f.getAdvance("w");
//                    int charsToTrim = text.length() - (twidth - 2*maxWidth) / charw;
//                    try {
//                        int index = text.length() - (text.length()-charsToTrim -3);
//                        Logger.log("text.len: " + String.valueOf(text.length())
//                            + "\nchars to trim1: " + String.valueOf(charsToTrim)
//                            + "\nindex: " + String.valueOf(index));
//                        text = text.substring(0, index);
//                        text +="...";
//                        twidth = f.getAdvance(text);
//                    } catch (IndexOutOfBoundsException i){
//                        text = text.substring(0, text.length()/2);
//                        text +="...";
//                        twidth = f.getAdvance(text);
//                    }
//                }
//                Logger.log("new twidth: " + String.valueOf(twidth)
//                        + " new text len: " + String.valueOf(text.length())
//                        + "\ntext: " + text);

//                int d = twidth - maxWidth;                Logger.log("d: " + String.valueOf(d));
//                int charw = f.getAdvance("w");            Logger.log("charw: " + String.valueOf(charw));
//
//                int charsToTrim = text.length() - d / charw;         Logger.log("chartToTrim: " + String.valueOf(charsToTrim));
//
//                int midi = charsToTrim;//chars to move on the second line
//                int cutIndex = midi;
//                for (int i = midi; i >= 0; i--) {
//                    if (text.charAt(i) == ' ' || text.charAt(i) == '.'
//                            || text.charAt(i) == '!' || text.charAt(i) == '?') {
//                        cutIndex = i;
//                        break;
//                    }
//                }
//                loc1 = text.substring(0, cutIndex); Logger.log("loc1: " + String.valueOf(loc1));
//                loc2 = text.substring(cutIndex, text.length()); Logger.log("loc2: " + String.valueOf(loc2));

                //g.setColor(Color.RED);
//                g.fillRect(x, y + (height - 2 * f.getHeight()) + 2, 2, 1 );
//                g.fillRect(x + maxWidth, y + (height - 2 * f.getHeight()) + 2, 2,1);
                Vector v = getText(text, f, maxWidth, loc1, loc2, g, x, y);
                if(v.size()>=2){
                    loc1 = (String)v.elementAt(0);
                    loc2 = (String)v.elementAt(1);
                } else if (v.size() == 1){
                    loc1 = (String)v.elementAt(0);
                }
//                Logger.log("loc1: " + loc1);
//                Logger.log("loc2: " + loc2);
                
                if (height > 2 * f.getHeight()) {
                    //Logger.log("height> 2*:fontheight" );
                    g.drawText(loc1, x, y +  (height - 2 * f.getHeight()) + 1);
                    g.drawText(loc2, x, y + (height - f.getHeight()) + 2);
//                    g.setColor(Color.RED);
//                    g.drawText(String.valueOf(f.getHeight())
//                            + " " + String.valueOf(f.getAdvance(loc1))
//                            + " " + String.valueOf(f.getAdvance(loc2))
//                            , x, y+2);
//                    g.fillRect(x + f.getAdvance(loc1), y + (height - 2 * f.getHeight()) + 2, 2,2 );
//                    g.fillRect(x + f.getAdvance(loc2), y + (height - 2 * f.getHeight()) + 2, 2,2 );
//                    g.fillRect(x, y + (height - 2 * f.getHeight()) + 2, 2,2 );
//                    g.fillRect(x, y + (height - f.getHeight()) + 2, 2,2 );
                } else {        //Logger.log("height< 2*:fontheight" );
                    Font f2 = f.derive(Font.PLAIN, 12);
                    g.setFont(f2);
                    g.drawText(loc1, x, y + 0 + 2);
                    g.drawText(loc2, x, y + height / 2);
                    g.setColor(Color.RED);
                    g.fillRect(x, 0 + 2, 2,2 );
                    g.fillRect(x, (height) + 2, 2,2 );
                }
            } else {
                g.drawText(text, x, y + (height - f.getHeight()) / 2);
            }
//        } catch (IndexOutOfBoundsException t) {
//            Logger.log("throwable: " + t.getMessage());
//            if(text.length()>30) {
//                text = text.substring(0,30);
//                text +="...";
//            }
//
//            g.drawText(text, x, y + (height - f.getHeight()) / 2);
//        }
    }

    private Vector getText(String s, Font f, int maxWidth, String s1, String s2, Graphics g, int x, int y) {
        Vector v = new Vector();
        if(s.length()>0) {
            int w =0;
            int lastSpace = 0;
            int rows = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                w+= f.getAdvance(c);
                if (c == ' ' || c == '.' || c == '!' || c == '?') {
                     lastSpace = i;
                }
                //Logger.log(String.valueOf(w));
                if(w>=maxWidth) {
                    //new elements
                    try {
//                        g.fillRect(x, y+2 + rows * 10, w, 1);
                        String el1 = s.substring(0,lastSpace);                        
                        rows += 1;

                        //draw el1
                        if(rows == 1) {
                            v.addElement(el1);
                            //s1 = new String(el1);
//                            Logger.log(s1);
//                            v.ad
                        } else if(rows ==2){
                            //s2 = new String(el1);
                            if(s.length()!= i+1)
                                el1 += "...";

                            v.addElement(el1);
//                            Logger.log(s2);
                            break;
                        }
                        
                        s = s.substring(lastSpace);
                        w = 0;
                        lastSpace = 0;
                        i = 0;
                    } catch (IndexOutOfBoundsException e) {
                        if(s.length()>30) {
                            s1 = s.substring(0, 30);
                        }
                        break;
                    }
                }
               
            }
            if(rows == 1) {
                // draw last symbols in s
                //s2 = new String(s);
                v.addElement(s);
//                Logger.log(s2);
            }
        }
        return v;
    }

    public String getText1Row(String s, Font f, int maxWidth) {
        if(s!= null && s.length()>0) {

            if(s.equals("---")) return "";
            
            int w =0;
            int lastSpace = 0;
            int rows = 0;
            if(f.getAdvance(s)< maxWidth){
                return s;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                w+= f.getAdvance(c);
                if (c == ' ' || c == '.' || c == '!' || c == '?') {
                     lastSpace = i;
                }
                if(w>=maxWidth) {
                    //new elements
                    try {
//                        String el1 = s.substring(0,lastSpace);
                        String el1 = s.substring(0,(i-3)); 
                        rows += 1;

                        //draw el1
                        if(rows == 1) {
                            return el1 + "...";
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if(s.length()>30) {
                            return s.substring(0, 30);
                        } else if (s.length()>20){
                            return s.substring(0, 20);
                        } else if (s.length()>10){
                            return s.substring(0, 10);
                        }
                    }
                }

            }
            if(rows == 1) {
                return s;
            }
        }
        return "";
    }

    public void drawLineText(Graphics g, String text, int maxWidth, Font f, int x, int y, int height) {
            //g.drawRect(x, y, maxWidth, height);
        
            g.setFont(f);
            if (text == null) {
                g.drawText("---", x, (height - f.getHeight()) / 2);
            }
            int twidth = f.getAdvance(text);
            if (twidth > maxWidth) {
               
                text = getText1Row(text, f, maxWidth);
            }
            g.drawText(text, x, y + (height - f.getHeight()) / 2);
    }
    
    public void drawLineTextRight(Graphics g, String text, int maxWidth, Font f, int x, int y, int height) {
        //g.drawRect(x, y, maxWidth, height);
    
        g.setFont(f);
        if (text == null) {
            g.drawText("---", x, (height - f.getHeight()) / 2);
        }
        int twidth = f.getAdvance(text);
        if (twidth > maxWidth) {
           
            text = getText1Row(text, f, maxWidth);
        }
        
        g.drawText(text, x, y + (height - f.getHeight()) / 2, g.RIGHT, maxWidth);
    }
}
