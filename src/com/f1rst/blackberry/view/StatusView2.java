package com.f1rst.blackberry.view;

import java.util.Timer;
import java.util.TimerTask;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.ui.AnimatedGIFField;
import com.f1rst.blackberry.ui.FontManager;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * @author ivaylo
 */
public class StatusView2 implements AbstractViewPanel {

    Dialog d;
    String title = "";
    String message = "";
    OKDialogListener listener;
    boolean isDisplayed = false;
    LabelField messageF;

    public StatusView2(OKDialogListener listener) {
        this.listener = listener;
    }

    public void showDialog() {

    	Logger.log("showDialog()");
        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};
                        if (d == null) {
                            d = new Dialog(title, choices, values, 0, null) {

                                boolean escapePressed = false;

                                //helps to handle when escape is pressed and listener is not null
                                protected boolean keyChar(char key, int status, int time) {

                                    if (key == Keypad.KEY_ESCAPE) {
                                        escapePressed = true;
                                    }

                                    return super.keyChar(key, status, time);
                                }

                                protected void onUiEngineAttached(boolean attached) {
                                    if (attached) {
                                        //displayed
                                        isDisplayed = true;
                                    } else {
                                        //removed from display stack
                                        isDisplayed = false;
                                        if (escapePressed) {
                                            if (listener != null) {
                                                listener.performAction(new Boolean(true));
                                            }
                                            escapePressed = false;
                                            return;
                                        }
                                    }
                                }

                                public void paintBackground(Graphics g) {
//                                    g.setColor(0x1b5178);
//                                    g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
//                                    g.setGlobalAlpha(0);//230
//                                    g.fillRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50));
//                                    g.clear();
                                    g.setColor(0x3d6188);
                                    g.setGlobalAlpha(240);//230
                                    g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
//                                    g.fillRoundRect(1, 1, Math.max(getContentWidth()-2, 50), Math.max(getContentHeight()-2, 50), 8, 8);
                                    g.fillRoundRect(0, 0, Math.max(getContentWidth()+4, 50), Math.max(getContentHeight()+4, 50), 3, 3);
                                    g.setColor(Color.WHITESMOKE);
                                }
                            };

                            d.setFont(FontManager.getComponentFont());
                            d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));
//                            d.setBorder(BorderFactory.createRoundedBorder(new XYEdges(1,1,1,1), 0x1b5078, Border.STYLE_SOLID));

                            //create something like a border
                            d.getDelegate().setPadding(12, 12, 12, 12);
//                            d.getDelegate().setPadding(4, 4, 4, 4);

                            if(message == null || (message!=null && message.trim().length()==0)){
                                message = Labels.LBL_WAIT;
                            }
                            messageF = new LabelField(message, Field.FIELD_HCENTER);
                            messageF.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 20));

//                            GIFEncodedImage _theImage = (GIFEncodedImage) EncodedImage.getEncodedImageResource("ajax-loader6.bin");
                            GIFEncodedImage _theImage = (GIFEncodedImage) EncodedImage.getEncodedImageResource("30-1.bin");

                            try {
                                d.getLabel().setFont(Font.getDefault().derive(Font.PLAIN, 2));
                            } catch (Throwable t) {
                            }


                            HorizontalFieldManager hM = new HorizontalFieldManager();
                            hM.add(new AnimatedGIFField(_theImage));
                            hM.add(messageF);
                            d.add(hM);

                            ButtonField b = new ButtonField(Labels.LBL_CANCEL, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
//
//                            BitmapButtonField b = new BitmapButtonField(
//                                Bitmap.getBitmapResource("button-cancel-selected-114x40.png"),
//                                Bitmap.getBitmapResource("button-cancel-114x40.png"),
//                                "Cancel", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                            b.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 20 - 6));
//                            b.setPadding(20, 2, 2, 2);
                            //b.setMargin(2, 2, 2, 2);
                            b.setChangeListener(new FieldChangeListener() {

                                public void fieldChanged(Field field, int context) {
                                    if (context != FieldChangeListener.PROGRAMMATIC) {
                                        if (listener != null) {
                                            listener.performAction(new Boolean(true));
                                        }
                                        closeStatus();
                                    }
                                }
                            });

                            d.add(b);
                            d.invalidate();
                        } else {
                            messageF.setText(message);
                        }

                        if (!isDisplayed) {
                            d.invalidate();
                            d.doModal();
                            d.invalidate();
                        }
                    }
                });
    }

    public void showDialogBlack() {

        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};
                        if (d == null) {
                            d = new Dialog(title, choices, values, 0, null) {

                                boolean escapePressed = false;

                                //helps to handle when escape is pressed and listener is not null
                                protected boolean keyChar(char key, int status, int time) {

                                    if (key == Keypad.KEY_ESCAPE) {
                                        escapePressed = true;
                                    }

                                    return super.keyChar(key, status, time);
                                }

                                protected void onUiEngineAttached(boolean attached) {
                                    if (attached) {
                                        //displayed
                                        isDisplayed = true;
                                    } else {
                                        //removed from display stack
                                        isDisplayed = false;
                                        if (escapePressed) {
                                            if (listener != null) {
                                                listener.performAction(new Boolean(true));
                                            }
                                            escapePressed = false;
                                            return;
                                        }
                                    }
                                }

                                public void paintBackground(Graphics g) {
                                    g.setColor(Color.BLACK);
                                    g.setGlobalAlpha(230);
                                    g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
                                    g.fillRoundRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50), 8, 8);
                                    g.setColor(Color.WHITESMOKE);
                                }
                            };

                            d.setFont(FontManager.getComponentFont());
                            d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));

                            //create something like a border
                            d.getDelegate().setPadding(12, 12, 12, 12);

                            messageF = new LabelField(message, Field.FIELD_HCENTER);
                            messageF.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 20));

                            GIFEncodedImage _theImage = (GIFEncodedImage) EncodedImage.getEncodedImageResource("ajax-loader6.bin");

                            try {
                                d.getLabel().setFont(Font.getDefault().derive(Font.PLAIN, 2));
                            } catch (Throwable t) {
                            }


                            HorizontalFieldManager hM = new HorizontalFieldManager();
                            hM.add(new AnimatedGIFField(_theImage));
                            hM.add(messageF);
                            d.add(hM);

                            ButtonField b = new ButtonField(
                                    Labels.LBL_CANCEL, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                            b.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 20 - 6));
//                            b.setPadding(20, 2, 2, 2);
                            //b.setMargin(2, 2, 2, 2);
                            b.setChangeListener(new FieldChangeListener() {

                                public void fieldChanged(Field field, int context) {
                                    if (context != FieldChangeListener.PROGRAMMATIC) {
                                        if (listener != null) {
                                            listener.performAction(new Boolean(true));
                                        }
                                        closeStatus();
                                    }
                                }
                            });

                            d.add(b);
                        } else {
                            messageF.setText(message);
                        }

                        if (!isDisplayed) {
                            d.invalidate();
                            d.doModal();
                        }
                    }
                });
    }

    public static void showInfoDialog(final String message) {

        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};

                        final Dialog d = new Dialog(message, choices, values, 0, null) {

                            boolean escapePressed = false;

                            //helps to handle when escape is pressed and listener is not null
                            protected boolean keyChar(char key, int status, int time) {

                                if (key == Keypad.KEY_ESCAPE) {
                                    //escapePressed = true;
                                }

                                return super.keyChar(key, status, time);
                            }

                            protected void onUiEngineAttached(boolean attached) {
                                if (attached) {
                                    //displayed
                                    //isDisplayed = true;
                                } else {
                                    //removed from display stack
                                    //isDisplayed = false;
//                                   if(escapePressed){
//                                       if (listener != null) {
//                                           listener.performAction(new Boolean(true));
//                                       }
//                                       escapePressed = false;
//                                       return;
//                                   }
                                }
                            }

                            public void paintBackground(Graphics g) {
                                g.setColor(Color.BLACK);
                                g.setGlobalAlpha(230);
                                g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
                                g.fillRoundRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50), 8, 8);
                                g.setColor(Color.WHITESMOKE);
                            }
                        };

                        d.setFont(FontManager.getComponentFont());
                        d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));

                        //fix an issue with incorrect update of transparent regions
                        d.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLUE, 240));
                        //create something like a border
                        d.getDelegate().setPadding(12, 12, 12, 12);

                        ButtonField b = new ButtonField(
                                "OK", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                        b.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 22));
                        b.setChangeListener(new FieldChangeListener() {

                            public void fieldChanged(Field field, int context) {
                                if (context != FieldChangeListener.PROGRAMMATIC) {
//                                          if(listener!=null)
//                                            listener.performAction(new Boolean (true));
                                    d.close();
                                }

                                //if (context == FieldChangeListener.PROGRAMMATIC){}
                            }
                        });

                        d.add(b);
                        d.invalidate();
                        d.show();
                    }
                });
    }

    public static void showInfoDialog(final String message, final OKDialogListener listener) {
        showInfoDialog(message, listener, true);
    }

    public static void showInfoDialog(final String message, final OKDialogListener listener, final boolean neverShowAgain) {

        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};

                        final CheckboxField checkBox = new CheckboxField(Labels.INF_NEVER_SHOW, false);
                        final Dialog d = new Dialog(message, choices, values, 0, null) {

                            boolean escapePressed = false;

                            //helps to handle when escape is pressed and listener is not null
                            protected boolean keyChar(char key, int status, int time) {

                                if (key == Keypad.KEY_ESCAPE) {
                                    escapePressed = true;
                                }

                                return super.keyChar(key, status, time);
                            }

                            protected void onUiEngineAttached(boolean attached) {
                                if (attached) {
                                    //displayed
                                    //isDisplayed = true;
                                } else {
                                    //removed from display stack
                                    //isDisplayed = false;
                                    if (escapePressed) {
                                        if (listener != null) {
                                            listener.performAction(new Boolean(checkBox.getChecked()));
                                        }
                                        escapePressed = false;
                                        return;
                                    }
                                }
                            }

                            public void paintBackground(Graphics g) {
                                g.setColor(Color.BLACK);
                                g.setGlobalAlpha(230);
                                g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
                                g.fillRoundRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50), 8, 8);
                                g.setColor(Color.WHITESMOKE);
                            }
                        };

                        d.setFont(FontManager.getComponentFont());
                        d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));
                        //create something like a border
                        d.getDelegate().setPadding(12, 12, 12, 12);

                        ButtonField b = new ButtonField(
                                "OK", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                        b.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 18));
//                        b.setPadding(10, 0, 0, 0);
                        //b.setMargin(0, 0, 0, 0);

                        b.setChangeListener(new FieldChangeListener() {

                            public void fieldChanged(Field field, int context) {
                                if (context != FieldChangeListener.PROGRAMMATIC) {
                                    if (listener != null) {
                                        listener.performAction(new Boolean(checkBox.getChecked()));
                                    }
                                    d.close();
                                }
                            }
                        });

                        if (neverShowAgain) {
                            d.add(checkBox);
                        }

                        d.add(b);
                        b.setFocus();
                        d.invalidate();
                        d.show();
                    }
                });
    }

    public static void show12DialogTimer(final String title, final String message, final OKDialogListener listener, final boolean neverShowAgain) {

        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};

                        final Dialog d = new Dialog(title, choices, values, 0, null) {

                            boolean escapePressed = false;

                            //helps to handle when escape is pressed and listener is not null
                            protected boolean keyChar(char key, int status, int time) {
                                return true;
                            }

                            protected void onUiEngineAttached(boolean attached) {
                                if (attached) {
                                } else {
                                    if (escapePressed) {
                                        escapePressed = false;
                                        return;
                                    }
                                }
                            }

                            public void paintBackground(Graphics g) {
                                g.setColor(Color.BLACK);
                                g.setGlobalAlpha(230);
                                g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
                                g.fillRoundRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50), 8, 8);
                                g.setColor(Color.WHITESMOKE);
                            }
                        };

                        d.setFont(FontManager.getComponentFont());
                        d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));
                        d.getDelegate().setPadding(12, 12, 12, 12);

                        LabelField m = new LabelField();
//                       RichTextField m = d.getLabel();
                        m.setText(message);
                        interval = 12;
                        d.add(m);
                        d.show();
                        final LabelField mf = m;
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {

                            public void run() {
                                UiApplication.getUiApplication().invokeLater(new Runnable() {

                                    public void run() {
                                        if (interval < 0) {
                                            t.cancel();
                                            d.close();
                                            if(listener!=null){
                                                listener.performAction(null);
                                            }
                                        }
                                        mf.setText(message + String.valueOf(interval--));
                                    }
                                });
                            }
                        }, 100, 1000);
                    }
                });
    }
    static int interval = 12;

    public static void showInfoYesNoDialog(final String message, final OKDialogListener listener) {

        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};

                        final CheckboxField checkBox = new CheckboxField(Labels.INF_DONT_SHOW, false);
                        final Dialog d = new Dialog(message, choices, values, 0, null) {

                            boolean escapePressed = false;

                            //helps to handle when escape is pressed and listener is not null
                            protected boolean keyChar(char key, int status, int time) {

                                if (key == Keypad.KEY_ESCAPE) {
                                    escapePressed = true;
                                }

                                return super.keyChar(key, status, time);
                            }

                            protected void onUiEngineAttached(boolean attached) {
                                if (attached) {
                                    //displayed
                                    //isDisplayed = true;
                                } else {
                                    //removed from display stack
                                    //isDisplayed = false;
                                    if (escapePressed) {
                                        if (listener != null) {
                                            Boolean result[] = {new Boolean(false), new Boolean(checkBox.getChecked())};
                                            listener.performAction(result);
                                            //return result;
                                        }
                                        escapePressed = false;
                                        return;
                                    }
                                }
                            }

                            public void paintBackground(Graphics g) {
                                g.setColor(Color.BLACK);
                                g.setGlobalAlpha(230);
                                g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
                                g.fillRoundRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50), 8, 8);
                                g.setColor(Color.WHITESMOKE);
                            }
                        };

                        d.setFont(FontManager.getComponentFont());
                        d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));
                        //create something like a border
                        d.getDelegate().setPadding(12, 12, 12, 12);

                        ButtonField bYes = new ButtonField(
                                Labels.LBL_YES, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                        ButtonField bNo = new ButtonField(
                                Labels.LBL_NO, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                        bYes.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 18));
                        //bYes.setPadding(10, 0, 8, 0);
                        //bYes.setMargin(0, 0, 0, 0);
                        bNo.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 18));
                        //bNo.setPadding(10, 0, 8, 0);
                        //bNo.setMargin(0, 0, 0, 0);

                        bYes.setChangeListener(new FieldChangeListener() {

                            public void fieldChanged(Field field, int context) {
                                if (context != FieldChangeListener.PROGRAMMATIC) {
                                    if (listener != null) {
                                        Boolean result[] = {new Boolean(true), new Boolean(checkBox.getChecked())};
                                        listener.performAction(result);
                                    }
                                    d.close();
                                }

                                //if (context == FieldChangeListener.PROGRAMMATIC){}
                            }
                        });

                        bNo.setChangeListener(new FieldChangeListener() {

                            public void fieldChanged(Field field, int context) {
                                if (context != FieldChangeListener.PROGRAMMATIC) {
                                    if (listener != null) {
                                        Boolean result[] = {new Boolean(false), new Boolean(checkBox.getChecked())};
                                        listener.performAction(result);
                                    }
                                    d.close();
                                }

                                //if (context == FieldChangeListener.PROGRAMMATIC){}
                            }
                        });

                        d.add(bYes);
                        d.add(bNo);
                        d.add(checkBox);
                        bYes.setFocus();
                        d.invalidate();
                        d.show();
                    }
                });
    }

    public static void showYesNoDialog(final String message, final OKDialogListener listener) {

        UiApplication.getUiApplication().invokeLater(
                new Runnable() {

                    public synchronized void run() {
                        Object choices[] = {};
                        int values[] = {Dialog.CANCEL};

                        final Dialog d = new Dialog(message, choices, values, 0, null) {

                            boolean escapePressed = false;

                            //helps to handle when escape is pressed and listener is not null
                            protected boolean keyChar(char key, int status, int time) {

                                if (key == Keypad.KEY_ESCAPE) {
                                    escapePressed = true;
                                }

                                return super.keyChar(key, status, time);
                            }

                            protected void onUiEngineAttached(boolean attached) {
                                if (attached) {
                                    //displayed
                                    //isDisplayed = true;
                                } else {
                                    //removed from display stack
                                    //isDisplayed = false;
                                    if (escapePressed) {
                                        if (listener != null) {
                                            listener.performAction(new Boolean(false));
                                        }
                                        escapePressed = false;
                                        return;
                                    }
                                }
                            }

                            public void paintBackground(Graphics g) {
                                g.setColor(Color.BLACK);
                                g.setGlobalAlpha(230);
                                g.setDrawingStyle(Graphics.DRAWSTYLE_AALINES, true);
                                g.fillRoundRect(0, 0, Math.max(getContentWidth(), 50), Math.max(getContentHeight(), 50), 8, 8);
                                g.setColor(Color.WHITESMOKE);
                            }
                        };

                        d.setFont(FontManager.getComponentFont());
                        d.setBorder(BorderFactory.createSimpleBorder(new XYEdges(1, 1, 1, 1), Border.STYLE_TRANSPARENT));

                        //create something like a border
                        d.getDelegate().setPadding(12, 12, 12, 12);

                        ButtonField bYes = new ButtonField(
                                Labels.LBL_YES, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                        ButtonField bNo = new ButtonField(
                                Labels.LBL_NO, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
                        bYes.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 18));
                        //bYes.setPadding(10, 0, 2, 0);
                        //bYes.setMargin(0, 0, 0, 0);
                        bNo.setFont(FontManager.getComponentFont().derive(Font.PLAIN, 18));
                        //bNo.setPadding(2, 0, 2, 0);
                        //bNo.setMargin(0, 0, 0, 0);

                        bYes.setChangeListener(new FieldChangeListener() {

                            public void fieldChanged(Field field, int context) {
                                if (context != FieldChangeListener.PROGRAMMATIC) {
                                    if (listener != null) {
                                        listener.performAction(new Boolean(true));
                                    }
                                    d.close();
                                }
                            }
                        });

                        bNo.setChangeListener(new FieldChangeListener() {

                            public void fieldChanged(Field field, int context) {
                                if (context != FieldChangeListener.PROGRAMMATIC) {
                                    if (listener != null) {
                                        listener.performAction(new Boolean(false));
                                    }
                                    d.close();
                                }
                            }
                        });

                        d.add(bYes);
                        d.add(bNo);
                        bYes.setFocus();
                        d.invalidate();
                        d.show();
                    }
                });
    }

    public synchronized void setMessage(String mess) {
        if (mess != null) {
            this.message = "  " + mess;
        }

        if(mess!=null && mess.trim().length() == 0) {
            this.message = Labels.LBL_WAIT;
        }
    }

    public synchronized void setTitle(String mess) {
        this.title = mess;
    }

    //@Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
//    	while stesting
//        Logger.log("StatusView2 " + evt.getPropertyName() + String.valueOf(evt.getNewValue()));
//        if (evt.getPropertyName().endsWith("setStatusMessage")) {
//            this.setMessage((String) evt.getNewValue());
//        } 
//        else 
        	if (evt.getPropertyName().equals(DefaultController.SET_IS_STATUS_SHOWN)) {// "isStatusShown"

            if (evt.getNewValue() != null && evt.getNewValue() instanceof Boolean && evt.getNewValue().equals(new Boolean(false))) {
                if (d != null) {
                    closeStatus();
                }
            } else {
                if (d != null && !UiApplication.getUiApplication().getActiveScreen().equals(d)) {
                    showDialog();
                } else {
                    if (d == null) {
                        showDialog();
                    }
                }
            }
        }
    }

    private void closeStatus() {
        final UiApplication ui = UiApplication.getUiApplication();
        ui.invokeLater(new Runnable() {

//            @Override
            public void run() {
                d.close();
            }
        });
    }
}
