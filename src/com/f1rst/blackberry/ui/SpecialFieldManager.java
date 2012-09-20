package com.f1rst.blackberry.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;


/**
 *
 * @author ivaylo
 * ------------------------------------
 * |       |     containter   |       |
 * |*space*|scrollable content|*space*|
 * |       |                  |       |
 * |       |                  |       |
 * |       |                  |       |
 * ------------------------------------
 */
public class SpecialFieldManager extends HorizontalFieldManager {

    VerticalFieldManager manager;

    public SpecialFieldManager(final int leftPadding, final int rightPadding, boolean border) {
        super();

        manager =  new VerticalFieldManager() {
             protected void sublayout( int maxWidth, int maxHeight ) {
                    int width = maxWidth;
                    int height = maxHeight;

                    if(maxHeight>20){
                        maxHeight-=10;
                    }
                    super.sublayout( maxWidth - leftPadding, maxHeight );
                    XYRect e = getExtent();
                    int min = Math.min(e.height, maxHeight);
                    if(min>20){
                        //removing a strange 10-15 pixs from the bottom
                        min = min - 10;
                    }
                    setExtent( maxWidth - leftPadding, min);
                }
        };

        super.add(new SpacerField(leftPadding, 1));
        super.add(manager);
        super.add(new SpacerField(rightPadding, 1));

        if(border){
            manager.setBorder(BorderFactory.createBitmapBorder(new XYEdges(14, 14, 14, 14),
                     Bitmap.getBitmapResource("bg-45x39.png")));
        }

        manager.setBackground(BackgroundFactory.createSolidBackground(0xffffff));
    }

    public SpecialFieldManager(final int leftPadding, final int rightPadding, boolean border, final int optHeight) {
        super();

        manager =  new VerticalFieldManager() {
             protected void sublayout( int maxWidth, int maxHeight ) {
                    int width = maxWidth;
                    int height = maxHeight;

                    if(optHeight>0) {
                        height = optHeight;
                    }
                    super.sublayout( maxWidth - leftPadding, height );
                    XYRect e = getExtent();
                    int min = Math.min(e.height, height);
                    setExtent( maxWidth - leftPadding, min);
                }
        };

        super.add(new SpacerField(leftPadding, 1));
        super.add(manager);
        super.add(new SpacerField(rightPadding, 1));

        if(border){
            manager.setBorder(BorderFactory.createBitmapBorder(new XYEdges(14, 14, 14, 14),
                     Bitmap.getBitmapResource("bg-45x39.png")));
        }

        manager.setBackground(BackgroundFactory.createSolidBackground(0xffffff));
    }

//    @Override
    public void add(Field field) {
        manager.add(field);
    }

//    @Override
    public void delete(Field field) {
        manager.delete(field);
    }

//    @Override
    public void deleteAll() {
        manager.deleteAll();
    }

}
