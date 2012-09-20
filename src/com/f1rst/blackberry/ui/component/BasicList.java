package com.f1rst.blackberry.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;

import com.f1rst.blackberry.util.DefaultController;

public abstract class BasicList {

	static int height = 70;
	static int ICON_IMAGE_WIDTH = 50;
	static int ICON_IMAGE_HEIGHT = 50;
   
	static {    	    	
    	if(Display.getWidth() == 320) {
    		height = 50;
    		ICON_IMAGE_WIDTH = 40;
    		ICON_IMAGE_HEIGHT = 40;
    	}
    }
    
    final static int MAX_ROWS_ON_SCREEN = Display.getHeight()/height + 2;

    protected DefaultController controller;

    final static int MAX_IMAGE_WIDTH = 22; 
    final static int MAX_IMAGE_DISPLAYED = 7;
        
    protected boolean debugon;
    
    protected int SIDE_PADDING = 20;
    
    public BasicList() {	
	}
    
    
    protected abstract void listItemClicked();
    
    protected void drawImageLocal(Graphics graphics, int x, int y, int height, Bitmap image) {
        if(image == null) return;

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        imageWidth = imageWidth < ICON_IMAGE_WIDTH ? imageWidth : ICON_IMAGE_WIDTH;
        imageHeight = imageHeight < ICON_IMAGE_HEIGHT ? imageHeight : ICON_IMAGE_HEIGHT;
        int imageTop = y + ((height - imageHeight) / 2);
        int imageLeft = x;
        imageLeft = x + ((ICON_IMAGE_WIDTH - imageWidth) / 2);
        graphics.drawBitmap(imageLeft, imageTop, imageWidth, imageHeight, image, 0, 0);
//      graphics.drawBitmap(x, imageTop, imageWidth, imageHeight, image, 0, 0);

    }
    
    protected void drawIcon(Graphics graphics, int x, int y, int height, Bitmap image) {
        if(image == null) return;

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int MAX = this.height;
        imageWidth = imageWidth < MAX ? imageWidth : MAX;
        imageHeight = imageHeight < MAX ? imageHeight : MAX;
        int imageTop = y + ((height - imageHeight) / 2);

        graphics.drawBitmap(x, imageTop, imageWidth, imageHeight, image, 0, 0);
    }

//    protected void drawImageLocal(Graphics graphics, int x, int y, int height, Bitmap image) {
//        if(image == null) return;
//
//        int imageWidth = image.getWidth();
//        int imageHeight = image.getHeight();
//
//        imageWidth = imageWidth<MAX_IMAGE_WIDTH ? imageWidth : MAX_IMAGE_WIDTH;
//        imageHeight = imageHeight<MAX_IMAGE_WIDTH ? imageHeight : MAX_IMAGE_WIDTH;
//        int imageTop = y + ((height - imageHeight) / 2);
//
//        graphics.drawBitmap(x, imageTop, imageWidth, imageHeight, image, 0, 0);
//    } 
    
//    int getListItemHeight() {
//    	return super.h
//    }
}
