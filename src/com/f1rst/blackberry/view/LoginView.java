package com.f1rst.blackberry.view;

import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

/**
 * sign in screen
 *
 * @author ivaylo
 */
public class LoginView extends ApplicationMainScreen implements AbstractViewPanel {
	private ColoredLabelField first;
	private ButtonField signUp;
	private ButtonField login;
	private ButtonField cont;
	
	private DefaultController controller;
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
        this.controller = controller;
        init();
    }
	
	private void init() {
		createFields();
		updateTitle(Labels.LBL_MAIN_TITLE);
	}
    
    private void createFields() {
		first = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, "F1rst", Field.FIELD_HCENTER);
		signUp = new ButtonField("Sign Up", ButtonField.CONSUME_CLICK | Field.FIELD_LEFT);
		login = new ButtonField("Login", ButtonField.CONSUME_CLICK | Field.FIELD_RIGHT);
		cont = new ButtonField(Labels.LBL_CONTINUE, ButtonField.CONSUME_CLICK | Field.FIELD_BOTTOM);
		
		signUp.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				signUp();
			}
		});
		login.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				login();
			}
		});
		cont.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				cont();
			}
		});
		
		HorizontalFieldManager h = new HorizontalFieldManager();
		h.add(first);
		add(h);
		
		HorizontalFieldManager h0 = new HorizontalFieldManager();
		h0.add(signUp);
		h0.add(login);
		add(h0);
		
		HorizontalFieldManager h1 = new HorizontalFieldManager();
		h1.add(cont);
		add(h1);
	}

	protected void cont() {
//		controller.showScreen(DefaultController.SHOW_MENU_VIEW);
	}

	protected void login() {
		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_SAMPLE_LOGIN, null, null));
		
	}

	protected void signUp() {
//		controller.showScreen(DefaultController.SHOW_SIGNUP_VIEW);
		
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
