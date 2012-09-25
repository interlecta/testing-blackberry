package com.f1rst.blackberry.view;

import net.rim.device.api.ui.component.BitmapField;

import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

public class RegisterView extends ApplicationMainScreen implements AbstractViewPanel {
	private BitmapField logo;
	private ColoredLabelField signup;
	
	private DefaultController controller;
	
	public RegisterView()
    {        
        // Set the displayed title of the screen       
        setTitle("MyTitle");
        createFields();
    }
    
    private void createFields() {
    	signup = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, Labels.LBL_SIGNUP_WITH_SOCIAL_ACC);
		
	}

	public RegisterView(DefaultController controller) {   
//    	super(NO_VERTICAL_SCROLL);
        this.controller = controller;
        init();
    }
	
	private void init() {
		createFields();
		updateTitle(Labels.LBL_REGISTER);
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
