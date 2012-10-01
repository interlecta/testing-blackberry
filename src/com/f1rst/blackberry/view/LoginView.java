package com.f1rst.blackberry.view;

import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.BitmapButtonField;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.ui.SpacerField;
import com.f1rst.blackberry.ui.SpacerManager;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * sign in screen
 *
 * @author ivaylo
 */
public class LoginView extends ApplicationMainScreen implements AbstractViewPanel {
	private ColoredLabelField first;
	private BitmapButtonField signUp;
	private BitmapButtonField login;
	private ButtonField cont;
	private String res;
	private Bitmap bg;
	private BitmapField background;
	
	private DefaultController controller;
	private VerticalFieldManager mainManager;
    /**
     * Creates a new MyScreen object
     */
    public LoginView()
    {        
        // Set the displayed title of the screen       
        setTitle("MyTitle");
        createFields();
    }
    
    public LoginView(DefaultController controller) {   
    	super(NO_VERTICAL_SCROLL);
        this.controller = controller;
        init();
    }
	
	private void init() {
		createFields();
		updateTitle(Labels.LBL_MAIN_TITLE);
	}
    
    private void createFields() {
    	mainManager = new VerticalFieldManager(Manager.NO_VERTICAL_SCROLL|Field.USE_ALL_HEIGHT);
    	int spacer = 0;
    	if(F1rstApplication.W >= 640 && F1rstApplication.H >= 480) {
    		spacer = 350;
    		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource("bg-main-640x480.jpg")));
    		signUp = new BitmapButtonField(Bitmap.getBitmapResource("but_signup-640x480.png"), Bitmap.getBitmapResource("but_signup_selected-640x480.png"), "", ButtonField.CONSUME_CLICK);
    		login = new BitmapButtonField(Bitmap.getBitmapResource("but_login2_640x480.png"), Bitmap.getBitmapResource("but_login2_selected_640x480.png"), "", ButtonField.CONSUME_CLICK);
    		cont = new ButtonField(Labels.LBL_CONTINUE, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
    		cont.setBackground(BackgroundFactory.createSolidTransparentBackground(0xD9E6EC,0));
    		cont.setBorder(BorderFactory.createRoundedBorder(new XYEdges(0, 0, 0, 0), 0xD9E6EC, 0, Border.STYLE_TRANSPARENT));
    	}else {
    		spacer = 250;
    		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource("bg_main_480x360.jpg")));
    		signUp = new BitmapButtonField(Bitmap.getBitmapResource("but_signup_480x360.png"), Bitmap.getBitmapResource("but_signup_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
    		login = new BitmapButtonField(Bitmap.getBitmapResource("but_login2_480x360.png"), Bitmap.getBitmapResource("but_login2_selected_480x360.png"), "", ButtonField.CONSUME_CLICK);
    		cont = new ButtonField(Labels.LBL_CONTINUE, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
    		cont.setBackground(BackgroundFactory.createSolidTransparentBackground(0xD9E6EC,0));
    		cont.setBorder(BorderFactory.createRoundedBorder(new XYEdges(0, 0, 0, 0), 0xD9E6EC, 0, Border.STYLE_TRANSPARENT));
    	}
//    	res = "-640x480";
//    	background = new BitmapField(Bitmap.getBitmapResource("bg-main"+res+".jpg"));
    	
		
		signUp.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_SAMPLE_LOGIN, null, null));
			}
		});
		login.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_REGISTER_VIEW, null, null));
			}
		});
		cont.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU_VIEW, null, null));
			}
		});
		
//		mainManager.add(background);
		
//		HorizontalFieldManager h = new HorizontalFieldManager();
//		h.add(first);
//		add(h);
		
		mainManager.add(new HorizontalFieldManager(Field.USE_ALL_WIDTH));
		
		HorizontalFieldManager h0 = new HorizontalFieldManager(Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		h0.add(signUp);
		h0.add(new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, "or"));
		h0.add(login);
		mainManager.add(new SpacerField(5,spacer));
		mainManager.add(h0);
		
		HorizontalFieldManager h1 = new HorizontalFieldManager(Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		h1.add(cont);
		mainManager.add(h1);
		
		add(mainManager);
	}
	
	public void modelPropertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(DefaultController.SET_STATUS_MESSAGE)) {
            String value = (String) evt.getNewValue();
            if(value!=null && value.length()>0)
                setStatusField(value);
            else
                removeStatusField();
        }
		
//		else if (evt.getPropertyName().equals(controller.SHOW_MENU)) {
//        	Runnable r = new Runnable() {
//                public void run() {
//                    init();
////                    prepareSigninScreen();
//                    deleteAll();
////                    prepareSigninScreen();
//                }
//            };
//                controller.pushScreen(this);
//                controller.invokeLater(r);
//        }
		
	}
}
