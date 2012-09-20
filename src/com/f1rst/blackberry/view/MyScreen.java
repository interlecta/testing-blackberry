package com.f1rst.blackberry.view;

import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.ColoredLabelField;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.MainScreen;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class MyScreen extends ApplicationMainScreen implements AbstractViewPanel
{
	private ColoredLabelField first;
	private ButtonField signUp;
	private ButtonField login;
	private ButtonField cont;
	
	private DefaultController controller;
    /**
     * Creates a new MyScreen object
     */
    public MyScreen()
    {        
        // Set the displayed title of the screen       
        setTitle("MyTitle");
        createFields();
    }
    
    public MyScreen(DefaultController controller) {
        this.controller = controller;
    }
	
	private void init() {
		createFields();
		updateTitle("test");
	}
    
    private void createFields() {
		first = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, "first", Field.FIELD_TOP);
		signUp = new ButtonField("sign up", ButtonField.CONSUME_CLICK | Field.FIELD_LEFT);
		login = new ButtonField("login", ButtonField.CONSUME_CLICK | Field.FIELD_RIGHT);
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
		
		add(first);
		add(signUp);
		add(login);
		add(cont);
	}

	protected void cont() {
//		controller.showScreen(DefaultController.SHOW_MENU_VIEW);
	}

	protected void login() {
//		controller.showScreen(DefaultController.SHOW_LOGIN_VIEW);
		
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
		
		else if (evt.getPropertyName().equals(controller.SHOW_MENU)) {
        	Runnable r = new Runnable() {
                public void run() {
                    init();
//                    prepareSigninScreen();
                    deleteAll();
//                    prepareSigninScreen();
                }
            };
                controller.pushScreen(this);
                controller.invokeLater(r);
        }
		
	}
}
