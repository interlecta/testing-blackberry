package com.f1rst.blackberry.view;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.BitmapButtonField;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

public class RegisterView extends ApplicationMainScreen implements AbstractViewPanel {
	private BitmapField logo;
	private ColoredLabelField signup;
	
	private DefaultController controller;
	
//	public RegisterView()
//    {        
//        // Set the displayed title of the screen       
//        setTitle("MyTitle");
//        createFields();
//    }
//    
    private void createFields() {
    	signup = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, Labels.LBL_SIGNUP_WITH_SOCIAL_ACC, Field.FIELD_TOP|Field.FIELD_HCENTER);
    	BitmapButtonField facebook = null;
    	BitmapButtonField tweeter = null;
    	BitmapButtonField google = null;
    	BitmapButtonField ms = null;
    	BitmapButtonField yahoo = null;
    	ButtonField newF1rst = null;
    	BitmapField header = null;
    	if(F1rstApplication.W == 640 && F1rstApplication.H == 480) {
//    		res = "-640x480";
    		header = new BitmapField(Bitmap.getBitmapResource("header_640x480.png"));
    		facebook = new BitmapButtonField(Bitmap.getBitmapResource("but_facebook_640x480.png"), Bitmap.getBitmapResource("but_facebook_selected_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	tweeter = new BitmapButtonField(Bitmap.getBitmapResource("but_tweeter_640x480.png"), Bitmap.getBitmapResource("but_tweeter_selected_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	google = new BitmapButtonField(Bitmap.getBitmapResource("but_google_640x480.png"), Bitmap.getBitmapResource("but_google_selected_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	ms = new BitmapButtonField(Bitmap.getBitmapResource("but_ms_640x480.png"), Bitmap.getBitmapResource("but_ms_selected_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	yahoo = new BitmapButtonField(Bitmap.getBitmapResource("but_yahoo_640x480.png"), Bitmap.getBitmapResource("but_yahoo_selected_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	
        	newF1rst = new ButtonField(Labels.LBL_CREATE_ACCOUNT, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
        	newF1rst.setBackground(BackgroundFactory.createLinearGradientBackground(0xE9610B, 0xE9610B, 0xE22811, 0xE22811));
        	newF1rst.setBorder(BorderFactory.createRoundedBorder(new XYEdges(0, 0, 0, 0), 0xE22811, 255));
//        	newF1rst = new BitmapButtonField(Bitmap.getBitmapResource("test.png"), Bitmap.getBitmapResource("test.png"), "", ButtonField.CONSUME_CLICK);
        	newF1rst.setMinimalWidth(640);
    	} else {///should be replaced with imgs for smaller res (480x360)
    		header = new BitmapField(Bitmap.getBitmapResource("header_640x480.png"));
    		facebook = new BitmapButtonField(Bitmap.getBitmapResource("but_facebook_480x360.png"), Bitmap.getBitmapResource("but_facebook_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
        	tweeter = new BitmapButtonField(Bitmap.getBitmapResource("but_tweeter_480x360.png"), Bitmap.getBitmapResource("but_tweeter_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
        	google = new BitmapButtonField(Bitmap.getBitmapResource("but_google_480x360.png"), Bitmap.getBitmapResource("but_google_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
        	ms = new BitmapButtonField(Bitmap.getBitmapResource("but_ms_480x360.png"), Bitmap.getBitmapResource("but_ms_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
        	yahoo = new BitmapButtonField(Bitmap.getBitmapResource("but_yahoo_480x360.png"), Bitmap.getBitmapResource("but_yahoo_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
        	
        	newF1rst = new ButtonField(Labels.LBL_CREATE_ACCOUNT, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
        	newF1rst.setBackground(BackgroundFactory.createLinearGradientBackground(0xE9610B, 0xE9610B, 0xE22811, 0xE22811));
        	newF1rst.setBorder(BorderFactory.createRoundedBorder(new XYEdges(0, 0, 0, 0), 0xE22811, 255));
        	
        	newF1rst.setMinimalWidth(480);
    	}
    	
    	facebook.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	tweeter.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	google.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	ms.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	yahoo.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	newF1rst.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
			
			}
		});
    	
    	add(header);
    	
    	VerticalFieldManager mainManager = new VerticalFieldManager(Field.USE_ALL_HEIGHT|Manager.NO_VERTICAL_SCROLL);
    	mainManager.add(signup);
    	
    	HorizontalFieldManager h = new HorizontalFieldManager(Manager.NO_VERTICAL_SCROLL);
    	h.add(facebook);
    	h.add(tweeter);
    	h.add(google);
    	h.add(ms);
    	h.add(yahoo);
    	
    	mainManager.add(h);
    	mainManager.add(new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, "or", Field.FIELD_HCENTER));
    	mainManager.add(newF1rst);
    	add(mainManager);
	}

	public RegisterView(DefaultController controller) {   
    	super(NO_VERTICAL_SCROLL);
        this.controller = controller;
        init();
    }
	
	private void init() {
		createFields();
		updateTitle(Labels.LBL_REGISTER);
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(controller.SHOW_REGISTER_VIEW)) {
        	Runnable r = new Runnable() {
                public void run() {
                    init();
                }
            };
                controller.pushScreen(this);
                controller.invokeLater(r);
        }
		
	}

}
