package com.f1rst.blackberry.ui;

import java.util.Timer;
import java.util.TimerTask;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.NullField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class ApplicationMainScreen extends TransitionMainScreen {

    HorizontalFieldManager hStatus = new HorizontalFieldManager(Field.NON_FOCUSABLE);
    LabelField statusLabel = new LabelField("", Field.NON_FOCUSABLE | Field.READONLY){
        public void paint(Graphics g){
            g.setColor(Color.WHITESMOKE);
            super.paint(g);
        }
    };

    String statusText = "";

    //title
    HorizontalFieldManager titleManager = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
    ColoredEditField titleField = new ColoredEditField(0xdddddd, "","", Field.READONLY|Field.NON_FOCUSABLE);

    private boolean showThrobber = false;
    private boolean showThrobberTitleHeader = false;

    Bitmap header;

    /**
     * bg color, something similar to dark grey
     */
    public static int STATUS_BG_COLOR = 0xfcfcfc;

//    Timer textBlendingTimer = new Timer();
//    TimerTask textBlendingTimerTask = new AlphaBlendingAnimationTimerTask();

    Timer timer = new Timer();
    TimerTask task;
    GIFFUtility throbberRenderer;

    /**
     * screen width and height
     */
    int w = Display.getWidth();
    int h = Display.getHeight();

    public ApplicationMainScreen() {

        super();
        
//        getMainManager().setBackground(BackgroundFactory.createBitmapBackground(
//        		Bitmap.getBitmapResource("screen-bg-p8.png")));
        getMainManager().setBackground(BackgroundFactory.createSolidBackground(BasicTheme.screenBackground));
        
        createTitledManager();

         hStatus.add(statusLabel);
         hStatus.setBackground(BackgroundFactory.createSolidTransparentBackground(0x0, 150));

         statusLabel.setFont(FontManager.getComponentFont().derive(Font.PLAIN,14));

        //for displaying throbber
        timer = new Timer();
        EncodedImage thImage= EncodedImage.getEncodedImageResource("ajax-loader3.bin");
        throbberRenderer = new GIFFUtility((GIFEncodedImage) thImage);
    }

    public ApplicationMainScreen(long settings) {

        super(settings);

//        getMainManager().setBackground(BackgroundFactory.createBitmapBackground(
//                Bitmap.getBitmapResource("screen-bg.jpg")));
        
        getMainManager().setBackground(BackgroundFactory.createSolidBackground(BasicTheme.screenBackground));
        
        createTitledManager();

         hStatus.add(statusLabel);
         hStatus.setBackground(BackgroundFactory.createSolidTransparentBackground(0x0, 150));

         statusLabel.setFont(FontManager.getComponentFont().derive(Font.PLAIN,14));

        //for displaying throbber
        timer = new Timer();
        EncodedImage thImage= EncodedImage.getEncodedImageResource("ajax-loader3.bin");
        throbberRenderer = new GIFFUtility((GIFEncodedImage) thImage);
    }

    public void setStatusField(final String message) {
         if(!isErrorDisplayed){
            UiApplication.getUiApplication().invokeLater(new Runnable(){

    //            @Override
                public void run() {
                	statusText = message;
                    showGraphicStatus = true;
                    invalidate();
//                    startTextBlending();
                    ALPHA = ALPHA_MAX;
                }
            });
         }
    }

      boolean isErrorDisplayed = false;
      /**
       *
       * @param message
       * @param interval in milliseconds
       */
      public void displayError(final String message, final int interval) {
         if(!isErrorDisplayed) {
            UiApplication.getUiApplication().invokeLater(new Runnable() {
    //            @Override
                public void run() {
                    ALPHA = ALPHA_MAX;
                    statusText = message;
                    isErrorDisplayed = true;
                    showGraphicStatus = true;

                    new Timer().schedule(new TimerTask() {

//                        @Override
                        public void run() {
                            isErrorDisplayed = false;
                            removeStatusField();
                        }
                    }, interval);
                    invalidate();
                }
            });
         }
    }

      public void displayError(final String message, final int interval, final String newMessage) {
         if(!isErrorDisplayed) {
            UiApplication.getUiApplication().invokeLater(new Runnable(){
    //            @Override
                public void run() {
                    ALPHA = ALPHA_MAX;
                    statusText = message;
                    isErrorDisplayed = true;
                    showGraphicStatus = true;

                    new Timer().schedule(new TimerTask() {

//                        @Override
                        public void run() {
                            isErrorDisplayed = false;
                            removeStatusField();
                            setStatusField(newMessage);
                        }
                    }, interval);
                    invalidate();
                }
            });
         }
    }

    public void removeStatusField() {
        if(!isErrorDisplayed) {
                UiApplication.getUiApplication().invokeLater(new Runnable(){
    //            @Override
                public void run() {
                    ALPHA = ALPHA_MAX;
                    showGraphicStatus = false;
                    setStatus(new NullField(Field.NON_FOCUSABLE));
                    invalidate();
                }
            });
        }
    }

//    private void startTextBlending() {
//        ALPHA = 0;
//        stopTextBlending();
//          if(textBlendingTimerTask == null)
//                textBlendingTimerTask = new AlphaBlendingAnimationTimerTask();
//            if(textBlendingTimer==null)
//                textBlendingTimer = new Timer();
//
//        textBlendingTimer.schedule(textBlendingTimerTask,100);
//    }
//
//    private void stopTextBlending() {
//         if(textBlendingTimerTask!=null)
//                textBlendingTimerTask.cancel();
//            if(textBlendingTimer!=null){
//                textBlendingTimer.cancel();
//            }
//            textBlendingTimer = null;
//            textBlendingTimerTask = null;
//    }

    boolean showGraphicStatus = false;

    private int ALPHA_MAX = 240;
    private int ALPHA = 0;

    public void paint(Graphics g) {

           super.paint(g);

           if(HorizontalOffset!= 0 ) {
           }


           if(showThrobber) {
               if(showThrobberTitleHeader && header != null) {
                   g.drawBitmap(0,0, header.getWidth(), header.getHeight(), header, 0, 0);
               }
               g.setGlobalAlpha(245);//250);//240
               g.setColor(STATUS_BG_COLOR);
//               g.setColor(0x0);
               if(showThrobberTitleHeader) {
                    g.fillRect(0, header.getHeight(), 480, Display.getHeight()-header.getHeight());
               } else {
                    g.fillRect(0, titleManager.getHeight(), 480, Display.getHeight()-titleManager.getHeight());
               }
               g.pushRegion((w-32)/2, (h-32)/2, 32, 32, 0, 0);
    	       throbberRenderer.paint(g);
               g.popContext();
           }
           
           if(showGraphicStatus)
           {
                g.setColor(0x0);
                Font f = FontManager.getComponentFont();//g.getFont()
                f = f.derive(Font.PLAIN, 20);//15//14);
                g.setFont(f);

                int textWidth = f.getAdvance(statusText);
                int textHeight = f.getAscent();


                {
                    //draw status;
                    //bg of status field
                    if( textWidth < w) {
                        g.setGlobalAlpha(ALPHA - 60);

                        //ligh grey for top outline
                        g.setColor(0x999999);
                        g.fillRoundRect(0, h - textHeight - 8 - 2, w, 1, 5, 5);//2px
                        g.setColor(0x0);
                        g.fillRoundRect(0, h - textHeight - 8, w, textHeight + 8, 0, 0);

                        //text
                        g.setGlobalAlpha(ALPHA);//240);
                        g.setColor(0xFFFFFF);
                        g.drawText(statusText, (w - textWidth) / 2, h - 1, g.BOTTOM | g.HCENTER);

                    } else {
                        statusLabel.setText(statusText);
                        setStatus(hStatus);
                    }
                }
           } else {
           }        
    }

    public void updateTitle (final String newTitle) {
        Runnable r = new Runnable(){

            public void run() {
                int c = UiApplication.getUiApplication().getScreenCount();
                titleField.setText(newTitle);
            }
        };
        UiApplication.getUiApplication().invokeLater(r);
    }

      boolean titleManagerCreated  = false;
      private void createTitledManager() {
          if(!titleManagerCreated) {
              try {
                titleField.setFont(FontManager.getComponentFont().derive(Font.PLAIN,18));
                titleManager.add(titleField);
                //titleManager.add(new ButtonField("b"));
                setTitle(titleManager);
                titleManagerCreated = true;
              } catch (IllegalArgumentException i) {}
          }
    }

    public void showThrobber() {
        if(!showThrobber){
            showThrobber = true;
            showThrobberTitleHeader = true;
            if(task == null)
                task = new AnimationTimerTask();
            if(timer==null)
                timer = new Timer();

            timer.schedule(task, 200,100);
            invalidate();
        }
    }
    public void showThrobberPlain() {
        if(!showThrobber) {
            showThrobber = true;
            showThrobberTitleHeader = false;
            if(task == null)
                task = new AnimationTimerTask();
            if(timer==null)
                timer = new Timer();

            timer.schedule(task, 200,100);
            invalidate();
        }
    }

    public void hideThrobber() {
        if(showThrobber) {
            showThrobber = false;
            if(task!=null)
                task.cancel();
            if(timer!=null){
                timer.cancel();
            }
            timer = null;
            task = null;
            invalidate();
        }
    }


    private class AnimationTimerTask extends TimerTask {

    	public void run() {

            throbberRenderer.nextPosition();

            invalidate((w-32)/2, (h-32)/2, 32, 32);

    	}
    }

//    private class AlphaBlendingAnimationTimerTask extends TimerTask {
//
//    	public void run() {
//
//            while(ALPHA<ALPHA_MAX) {
//                ALPHA += (int)ALPHA_MAX/2;
//                invalidate(0,h-35,w,35);
//                try {
//                    Thread.sleep(130);
//                } catch (InterruptedException ex) {
//                }
//            }
//
//            ALPHA = ALPHA_MAX;
//            invalidate(0,h-25,w,25);
//    	}
//    }

    public boolean onClose() {
        UiApplication.getUiApplication().invokeLater(new Runnable(){
            public void run() {
                titleManager.deleteAll();
                deleteAll();
            }
        });

        return super.onClose();        
    }

    public HorizontalFieldManager getTitleManager() {
        return titleManager;
    }

/**
 * 1. Screen title (no line or shading)
 * 2. Buttons are invisible (no rectangle or focus color, just disappear when focused)
 * 3. Focus color is black instead of something nicer like blue.
 */
    //override any theme settings
    //change title of the title bg color
//    protected void applyTheme() {
//    }

//    protected void applyTheme(Graphics arg0, boolean arg1){
//
//}

    public boolean isActiveScreen() {

        if (UiApplication.getUiApplication().getActiveScreen() != null
                && UiApplication.getUiApplication().getActiveScreen().equals(this)) {
            return true;
        } else {
            return false;
        }
    }
    
}
