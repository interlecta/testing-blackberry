package com.f1rst.blackberry.view;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EmailAddressEditField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.BitmapButtonField;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

public class SampleLoginView extends ApplicationMainScreen implements AbstractViewPanel {
	private DefaultController controller;
	
	private ColoredLabelField loginInf;
	private ColoredLabelField loginInf2;
	
	private ButtonField login;
	
	private BitmapButtonField facebook;
	private BitmapButtonField tweeter;
	private BitmapButtonField google;
	private BitmapButtonField ms;
	private BitmapButtonField yahoo;
	
	private EmailAddressEditField userName;

    private PasswordEditField password;
	
    
    public SampleLoginView()
    {        
        // Set the displayed title of the screen       
        setTitle("MyTitle");
        createFields();
    }

	public SampleLoginView(DefaultController controller) {    	
        this.controller = controller;
        init();
    }

	private void init() {
		createFields();
		updateTitle(Labels.LBL_LOGIN);
		
	}
    
    private void createFields() {
    	loginInf = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, Labels.LBL_LOGIN_INF);
    	loginInf2 = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, Labels.LBL_LOGIN_INF2);
    	
    	userName = new EmailAddressEditField("", Labels.LBL_NAME, 50, BasicEditField.NO_NEWLINE) {
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
        
        userName.setBorder(BorderFactory.createRoundedBorder(new XYEdges(6, 6, 6, 6),
                0xc0c0c0,Border.STYLE_SOLID));
        password.setBorder(BorderFactory.createRoundedBorder(new XYEdges(6, 6, 6, 6),
                0xc0c0c0,Border.STYLE_SOLID));
        
//        facebook = new BitmapButtonField(Bitmap.getBitmapResource(""), Bitmap.getBitmapResource(""), "F", ButtonField.CONSUME_CLICK);
//        tweeter = new BitmapButtonField(Bitmap.getBitmapResource(""), Bitmap.getBitmapResource(""), "T", ButtonField.CONSUME_CLICK);
//        google = new BitmapButtonField(Bitmap.getBitmapResource(""), Bitmap.getBitmapResource(""), "G", ButtonField.CONSUME_CLICK);
//        ms = new BitmapButtonField(Bitmap.getBitmapResource(""), Bitmap.getBitmapResource(""), "MS", ButtonField.CONSUME_CLICK);
//        yahoo = new BitmapButtonField(Bitmap.getBitmapResource(""), Bitmap.getBitmapResource(""), "Y", ButtonField.CONSUME_CLICK);
        
        login = new ButtonField("Login", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
        
//        facebook.setChangeListener(new FieldChangeListener() {
//			public void fieldChanged(Field arg0, int arg1) {
//				facebookClicked();
//			}
//		});
//        tweeter.setChangeListener(new FieldChangeListener() {
//			public void fieldChanged(Field arg0, int arg1) {
//				tweeterClicked();
//			}
//		});
//        google.setChangeListener(new FieldChangeListener() {
//			public void fieldChanged(Field arg0, int arg1) {
//				googleClicked();
//			}
//		});
//        ms.setChangeListener(new FieldChangeListener() {
//			public void fieldChanged(Field arg0, int arg1) {
//				msClicked();
//			}
//		});
//        yahoo.setChangeListener(new FieldChangeListener() {
//			public void fieldChanged(Field arg0, int arg1) {
//				yahooClicked();
//			}
//		});
        
        login.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				loginClicked();
			}
		});
        
        VerticalFieldManager v = new VerticalFieldManager();
        v.add(loginInf);
        v.add(userName);
        v.add(password);
        v.add(login);
        v.add(loginInf2);
        add(v);
        
//        HorizontalFieldManager h = new HorizontalFieldManager();
//        h.add(facebook);
//        h.add(tweeter);
//        h.add(google);
//        h.add(ms);
//        h.add(yahoo);
//        add(h);
    }
    
    protected void loginClicked() {
		controller.userNormalLogin("test", "test", true);
		
	}

	protected void yahooClicked() {
		// TODO Auto-generated method stub
		
	}

	protected void msClicked() {
		// TODO Auto-generated method stub
		
	}

	protected void googleClicked() {
		// TODO Auto-generated method stub
		
	}

	protected void tweeterClicked() {
		// TODO Auto-generated method stub
		
	}

	protected void facebookClicked() {
		// TODO Auto-generated method stub
		
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(DefaultController.SET_STATUS_MESSAGE)) {
            String value = (String) evt.getNewValue();
            if(value!=null && value.length()>0)
                setStatusField(value);
            else
                removeStatusField();
        }
		
		else if (evt.getPropertyName().equals(controller.SHOW_SAMPLE_LOGIN)) {
        	Runnable r = new Runnable() {
                public void run() {
                    init();
//                    prepareSigninScreen();
//                    deleteAll();
//                    prepareSigninScreen();
                }
            };
                controller.pushScreen(this);
                controller.invokeLater(r);
        }
		 else if (evt.getPropertyName() != null && evt.getPropertyName().equals(controller.HIDE_LOGIN)) {
	            controller.popScreen(this);
	     } 
		
	}

}
