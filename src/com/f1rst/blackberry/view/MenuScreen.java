package com.f1rst.blackberry.view;
import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.SpacerField;
import com.f1rst.blackberry.ui.SpacerManager;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.PropertyChangeEvent;
import com.f1rst.blackberry.util.Settings;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.EmailAddressEditField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * sign in screen
 *
 * @author ivaylo
 */
public class MenuScreen extends ApplicationMainScreen implements AbstractViewPanel {


    private static com.f1rst.blackberry.view.MenuScreen instance;

	private EmailAddressEditField userName;

    private PasswordEditField password;
    
    private ButtonField login;

    private CheckboxField save;
    
    private ObjectChoiceField language;


    private DefaultController controller;      

    public MenuScreen() {
    }

    public MenuScreen(DefaultController controller) {
        this.controller = controller;
    }

    private void init() {
        createFields();
        try {
            //controls font size
            int fontHeight = 18;
            FontFamily ff = FontFamily.forName("BBAlpha Sans");
            Font defaultFont = ff.getFont(Font.PLAIN, fontHeight);
            save.setFont(defaultFont);
            userName.setFont(defaultFont);
            password.setFont(defaultFont);

        } catch (ClassNotFoundException n) {
        }
        
        getMainManager().setBackground(
                BackgroundFactory.createSolidBackground(0xc7d5f5));

        updateTitle(Model.getVERSION());


        login = new ButtonField(Labels.LBL_SIGN_IN, ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);

        login.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
            	userSignin();
            }
        });       

 
    }

    private void prepareSigninScreen() {
        Logger.log("prepare sign in screen");
        
        updateTitle(Labels.LBL_SIGN_IN + "(" + Model.getVERSION()+")");        
        
        if(F1rstApplication.W == 320) {
        	add(new SpacerField(20, 10));           											  
            add(new SpacerField(10, 3));
            HorizontalFieldManager hu = new HorizontalFieldManager();
            SpacerManager man = new SpacerManager(F1rstApplication.W - 50,220);
            SpacerManager lsm = new SpacerManager(20, 20, false);
            SpacerManager rsm = new SpacerManager(20, 20, false);

            lsm.add(new SpacerField(20, 50));
            rsm.add(new SpacerField(20, 50));

            man.add(language);
            man.add(userName);
            man.add(password);
            
            man.add(save);
            man.add(login);

            hu.add(lsm);
            hu.add(man);
            hu.add(rsm);
            add(hu);
        } else {
        	add(new SpacerField(20, 20));
            add(new SpacerField(10, 10));
            HorizontalFieldManager hu = new HorizontalFieldManager();
            SpacerManager man = new SpacerManager(F1rstApplication.W - 200, 220);
            SpacerManager lsm = new SpacerManager(100, 100, false);
            SpacerManager rsm = new SpacerManager(100, 100, false);
            lsm.add(new SpacerField(100, 50));
            rsm.add(new SpacerField(100, 50));

            man.add(language);
            man.add(userName);
            man.add(password);
            
            man.add(save);
            man.add(login);

            hu.add(lsm);
            hu.add(man);
            hu.add(rsm);
            add(hu);
            add(new SpacerField(300, 10));
        }

        userName.setBorder(BorderFactory.createRoundedBorder(new XYEdges(6, 6, 6, 6),
                0xc0c0c0,Border.STYLE_SOLID));
        password.setBorder(BorderFactory.createRoundedBorder(new XYEdges(6, 6, 6, 6),
                0xc0c0c0,Border.STYLE_SOLID));
        
        login.setFont(BasicTheme.text);
        
//        userName.setText("8075545009");
//        password.setText("App123");
    }


    private void userSignin() {
        if(userName.getText().equalsIgnoreCase(Labels.LBL_EMAIL_ADDRESS)) {
            controller.inform(Labels.INF_SIGN_IN);
            return;
        }
        if(userName.getText().trim().equals("")) {
            controller.inform(Labels.INF_SIGN_IN);
            return;
        }
        controller.userNormalLogin(userName.getText(), password.getText(), save.getChecked());
    }

    public void modelPropertyChange(final PropertyChangeEvent evt) {
            
//        if (evt.getPropertyName() != null && evt.getPropertyName().equals(controller.SET_ERROR_MESSAGE)) {
//            //to display retry message
//            if (evt.getNewValue() != null && evt.getNewValue() instanceof String) {
//                String statusText = (String) evt.getNewValue();
//                displayError(statusText, 3000);
//            }
//        } else 
        if (evt.getPropertyName() != null && evt.getPropertyName().equals(controller.SET_STATUS_MESSAGE)) {
            if (evt.getNewValue() != null && evt.getNewValue() instanceof String) {
                String statusText = (String) evt.getNewValue();
                if (statusText != null && statusText.length() > 0) {
                    setStatusField(statusText);
                } else {
                    //remove status message
                    setStatusField("");
                    removeStatusField();
                }
            }


        } else if (evt.getPropertyName() != null && evt.getPropertyName().equals(controller.SET_SETTINGS)) {
            //when loading from persistent store
            UiApplication.getUiApplication().invokeLater(new Runnable() {

                public void run() {
                    if (evt.getNewValue() != null && evt.getNewValue() instanceof Settings) {
                        Settings set = (Settings) evt.getNewValue();
                    } else {
                        Logger.log("LoginView settings = null");
                    }
                }
            });
        
        } else if (evt.getPropertyName() != null && evt.getPropertyName().equals(controller.SHOW_LOGIN)) {
        	
            //clear the setting if !save
            if(!Model.getModel().getSettings().isSaveCredentials()) {
                Model.getModel().getSettingsTable().setSettingOne("0");
                Model.getModel().getSettings().setSaveCredentials(false);
                Model.getModel().getSettings().setUserName("");
                Model.getModel().getSettings().setPassword("");

                Model.getModel().getSettings().setSettingsTable(null);
            }

            if (this.isDisplayed()) {
                //already displayed
            } else {
                Runnable r = new Runnable() {
                    public void run() {
                        init();
//                        prepareSigninScreen();
                        deleteAll();
                        prepareSigninScreen();
                    }
                };
                controller.pushScreen(this);
                controller.invokeLater(r);
                
            }
        } else if (evt.getPropertyName() != null && evt.getPropertyName().equals(controller.HIDE_LOGIN)) {
            controller.popScreen(this);
        } 
//        else if (evt != null && evt.getPropertyName().equals(controller.SHOW_THROBBER)) {
//            showThrobber();
//        }
        else if (evt != null && evt.getPropertyName().equals(controller.HIDE_THROBBER)) {
            hideThrobber();
        }
    }

    //@Override
    protected void makeMenu(Menu menu, int instance) {

        menu.add(exitMenuItem);
    }

    public boolean onClose() {
    	controller.exit();
        return true;
    }

    MenuItem exitMenuItem = new MenuItem(Labels.LBL_EXIT, 10, 1001) {
//            @Override

        public void run() {
            controller.exit();
        }
    };
 
    public void invalidate() {
        super.invalidate();
    }

    protected void onUiEngineAttached(boolean attached) {
        if (attached) {
            hideThrobber();
            final Runnable r = new Runnable() {

                public void run() {
                    //show warning
                }
            };
            new Thread(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                    controller.invokeLater(r);
                }
            }).start();
        } else {
        }
    }

    private void createFields() {
        userName = new EmailAddressEditField("", Labels.LBL_NAME, 50, BasicEditField.NO_NEWLINE | BasicEditField.FILTER_EMAIL) {

            //todo add key listener if email is in use - WS63
            public boolean isFocusable() {
                return true;
            }

            protected void onFocus(int direction) {
                if (getText().equals(Labels.LBL_NAME)) {
                    setText("");
                }
                invalidate();
                super.onFocus(direction);
            }

            protected void onUnfocus() {
                if (getText().equals("")) {
                    setText(Labels.LBL_NAME);
                }
                invalidate();
                super.onUnfocus();
            }

            public void paint(Graphics g) {
                g.setColor(0x333333);
                super.paint(g);
            }
//        protected void layout(int maxWidth, int maxHeight) {
//            setExtent(280, 35);
//        }
        };       

        password = new PasswordEditField("", Labels.LBL_PASSWORD, 40, PasswordEditField.NO_NEWLINE) {

            public boolean isFocusable() {
                return true;
            }

            protected void onFocus(int direction) {
                if (getText().equals(Labels.LBL_PASSWORD)) {
                    setText("");
                }
                invalidate();
                super.onFocus(direction);
            }

            protected void onUnfocus() {
                if (getText().equals("")) {
                    setText(Labels.LBL_PASSWORD);
                }
                invalidate();
                super.onUnfocus();
            }

            public void paint(Graphics g) {
                g.setColor(0x333333);
                super.paint(g);
            }
        };

        save = new CheckboxField(Labels.LBL_SAVE, false, Field.FIELD_RIGHT);
        
    }
    
    public static MenuScreen getMenuScreen(DefaultController controller) {
        //if(instance == null){
           instance = new MenuScreen(controller);           
        //}
           
        return instance;
    }
}
