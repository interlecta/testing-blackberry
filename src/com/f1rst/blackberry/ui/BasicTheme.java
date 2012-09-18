package com.f1rst.blackberry.ui;

import com.f1rst.blackberry.util.DefaultController;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Font;

public class BasicTheme {

    public final static int FONT_COLOR_GREY444 = 0x444444;
    
    //-------------------------------------------------------
    // color constants
    public final static int COLOR_3d6188 = 0x3d6188;
    public final static int COLOR_fafafa = 0xfafafa;
    public final static int COLOR_202020 = 0x202020;//0x0;//can be 202020
    public final static int COLOR_808080 = 0x808080;
    public final static int COLOR_MID_GREY = 0x7F7F7F;
    public final static int COLOR_LIGHT_GRAY_BLUE = 0xeceff4;// as the main menu icons color
    
    public final static int COLOR_2c524f = 0x2c524f;
    //-------------------------------------------------------
        
    public final static int FONT_COLOR_BLACK = 0x0;//can be 202020
    public final static int FONT_COLOR_MID_GREY = COLOR_MID_GREY;
    
    // public final static int MAIN_TEXT_COLOR = FONT_COLOR_BLACK;
    // public final static int SECONDARY_TEXT_COLOR = FONT_COLOR_MID_GREY;
	
	public final static int primaryTextColor = COLOR_202020;//FONT_COLOR_BLACK;
	public final static int secondaryTextColor = FONT_COLOR_MID_GREY;
	public final static int thirdTextColor = COLOR_3d6188;
	
	public final static int forthTextColor = COLOR_fafafa;//product technical details header field text color
	
	public final static int listItemFocusColor = 0xc0c0c0;
	public final static int listItemFocusColor2 = COLOR_MID_GREY;// COLOR_2c524f;
	
	public final static int listItemBackgroundSelectedColor = 0x88ebebeb; //some gray
	public final static int listItemBackgroundNormalColor = 0xfefefe; // some lighter gray, almost white	
	
	public final static int backgroundHeaderFieldColor = COLOR_808080;
	
	public final static int screenBackground = COLOR_LIGHT_GRAY_BLUE;
	
	//custom items
	public final static int promotionsLabelFontColor = FONT_COLOR_GREY444;
	
	
	//-------------------------------------------------------
	// set up the font size properties
	public static Font title = DefaultController.getFont22();
	public static Font subtitle = DefaultController.getFont20();
	public static Font text = DefaultController.getFont18(); //getFont16();
	
	public static Font subtitleBold = DefaultController.getFontBold20();
	public static Font textBold = DefaultController.getFontBold16();
	
	static {    	    	
    	if(Display.getWidth() == 320) {
    		// change the default font size
            BasicTheme.title = DefaultController.getFont20();
            BasicTheme.subtitle = DefaultController.getFont16();
            BasicTheme.text = DefaultController.getFont14();
    	}
    }
	//-------------------------------------------------------
}
