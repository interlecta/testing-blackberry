package com.f1rst.blackberry.view;

import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.ui.AnimatedGIFField;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.ui.SpacerField;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.PropertyChangeEvent;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;


/**
 * A wait screen with logo, displayed when syncing with the server.
 *
 * @author ivaylo
 */
public class GlobalStatusScreen extends MainScreen implements AbstractViewPanel {

    private DefaultController controller;

    private int globalApha = 0;

    public GlobalStatusScreen(DefaultController controller) {
        super();
        this.controller = controller;
    }

    public void initScreen(final String message) {

        Runnable r = new Runnable() {

            public void run() {
                getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xc7d5f5));

                HorizontalFieldManager h = new HorizontalFieldManager(Field.FIELD_HCENTER | Field.FIELD_VCENTER);
                VerticalFieldManager v = new VerticalFieldManager(Field.FIELD_HCENTER | Field.FIELD_VCENTER);

                ColoredLabelField mes = new ColoredLabelField(0x3D6188, message);
                

                GIFEncodedImage spinner = (GIFEncodedImage) EncodedImage.getEncodedImageResource("1-1.bin");
                AnimatedGIFField b = new AnimatedGIFField(spinner);
                mes.setFont(DefaultController.getFont18());
                h.add(b);
                h.add(new SpacerField(5, 5));
                h.add(mes);

                int padding = 20;
                v.add(new SpacerField(20, padding));
                Bitmap sBitmap;
                if(F1rstApplication.W == 320) {
                    sBitmap = Bitmap.getBitmapResource("logo.png");
                } else {
                    sBitmap = Bitmap.getBitmapResource("logo.png");
                }
                v.add(new BitmapField(sBitmap,BitmapField.FIELD_HCENTER));
                v.add(new SpacerField(5, ((Display.getHeight()) - mes.getHeight()) / 2 - sBitmap.getHeight() - padding));
                v.add(h);
                add(v);
            }
        };
        controller.invokeLater(r);
    }

    public void modelPropertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals(controller.SHOW_GLOBAL_STATUS_SCREEN)) {
//            initScreen((String) evt.getNewValue());
//            controller.pushScreen(this);
//        } else if (evt.getPropertyName().equals(controller.HIDE_GLOBAL_STATUS_SCREEN)) {
//            onClose();
//        }
    }

    protected void makeMenu(Menu menu, int instance) {
        super.makeMenu(menu, instance);
    }

    protected void paint(Graphics graphics) {
        graphics.setGlobalAlpha(globalApha);
        super.paint(graphics);
    }

    public boolean onClose() {

        Runnable r = new Runnable() {

            public void run() {
                deleteAll();
            }
        };
        controller.invokeLater(r);

        controller.popScreen(this);
        return true;
    }

    protected void onUiEngineAttached(boolean attached) {
        if(attached){
             new Thread(new Runnable(){
                public void run(){
                    for (int i = 0; globalApha < 255; i++) {
                        globalApha += (255-globalApha)/2;
                        invalidate();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ie){}
                    }
                    globalApha = 255;
                }
            }).start();
        }
    }

}
