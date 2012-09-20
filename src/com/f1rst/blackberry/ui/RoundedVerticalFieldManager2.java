package com.f1rst.blackberry.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * Latest version of all managers with padding from the left and right sides.
 * It contains border and background.
 * @author ivaylo
 */
public class RoundedVerticalFieldManager2 extends VerticalFieldManager {

    int borderColor = 0;
    
    VerticalFieldManager mainM = new VerticalFieldManager();

    int leftPadding;
    int rightPadding;

    public RoundedVerticalFieldManager2() {
        createManager();
    }

    public RoundedVerticalFieldManager2(int leftPadding, int rightPadding) {
        this.leftPadding = leftPadding;
        this.rightPadding = rightPadding;
        createManager();
    }

    private void createManager() {
        VerticalFieldManager mainO = new VerticalFieldManager(Manager.NO_VERTICAL_SCROLL|Manager.NO_HORIZONTAL_SCROLL);
//        mainO.setBorder(BorderFactory.createRoundedBorder(new XYEdges(18,18,18,18), borderColor , Border.STYLE_TRANSPARENT));
        mainO.setBorder(BorderFactory.createRoundedBorder(new XYEdges(18,leftPadding,18,leftPadding), borderColor , Border.STYLE_TRANSPARENT));
//        mainO.setBackground(BackgroundFactory.createSolidBackground(0xff0000));
        mainM.setBackground(BackgroundFactory.createSolidBackground(0xFFFFFF));

        mainM.setBorder(BorderFactory.createBitmapBorder(new XYEdges(14, 14, 14, 14),
                     Bitmap.getBitmapResource("bg-45x39.png")));
        mainO.add(mainM);
        super.add(mainO);
    }

//    @Override
    public void add(Field field) {
        mainM.add(field);
    }

//    @Override
    public void delete(Field field) {
        mainM.delete(field);
    }

//    @Override
    public void deleteAll() {
        mainM.deleteAll();
    }
}
