package com.f1rst.blackberry.ui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;

public class FontManager {

    static Font componentFont;

    public static Font getComponentFont() {
        if(componentFont == null) {
            try {
                FontFamily ff =  FontFamily.forName("BBAlpha Sans");
                Font defaultFont = ff.getFont(Font.PLAIN, 20);
//  set the default font of the app but there are problems with menu items displaying.
//                Font.setDefaultFont(defaultFont);
                componentFont = defaultFont;
            } catch (ClassNotFoundException n){
                componentFont = Font.getDefault().derive(Font.PLAIN, 20);
            }

        }

        return componentFont;
    }

    public static Font getComponentFont(int size) {
        Font f = null;

        try {
            FontFamily ff = FontFamily.forName("BBAlpha Sans");
            f = ff.getFont(Font.PLAIN, size);
        } catch (ClassNotFoundException n) {
            f = Font.getDefault().derive(Font.PLAIN, size);
        }

        return f;
    }

    public static Font getComponentFont(int style, int size) {
        Font f = null;

        try {
            FontFamily ff = FontFamily.forName("BBAlpha Sans");
            f = ff.getFont(style, size);
        } catch (ClassNotFoundException n) {
            f = Font.getDefault().derive(Font.PLAIN, size);
        }

        return f;
    }

    public static Font getComponentFontTall(int size) {
        Font f = null;

        try {
            FontFamily ff = FontFamily.forName("BBMillbankTall");
            f = ff.getFont(Font.BOLD, size);
        } catch (ClassNotFoundException n) {
            f = Font.getDefault().derive(Font.PLAIN, size);
        }

        return f;
    }
}
