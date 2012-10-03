package com.f1rst.blackberry.view;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.GaugeField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.ui.BasicTheme;
import com.f1rst.blackberry.ui.ColoredLabelField;
import com.f1rst.blackberry.ui.RoundedHorizontalFieldManager2;
import com.f1rst.blackberry.ui.RoundedVerticalFieldManager2;
import com.f1rst.blackberry.ui.SpacerField;
import com.f1rst.blackberry.ui.component.ToolbarManager;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

public class ProfileView extends BasicMainScreen implements FieldChangeListener, AbstractViewPanel {
	private DefaultController controller;
	
	public ProfileView(DefaultController controller) { 
		this.controller = controller;
//        init();
    }

	public void modelPropertyChange(PropertyChangeEvent evt) {
		Logger.log(evt.getPropertyName());
		if (evt.getPropertyName().equals(controller.SHOW_PROFILE_VIEW)) {			
        	Runnable r = new Runnable() {
                public void run() {                	
                    init();
                }
            };
                
            controller.pushScreen(this);
            controller.invokeLater(r);
        }
		
	}

	private void init() {
		createFields();
		updateTitle(Labels.LBL_PROFILE);		
	}

	private void createFields() {
		ToolbarManager toolbar = new ToolbarManager(controller);
		toolbar.setListener(this);
//		 toolbar.setActive(ToolbarManager.ACTIVE_PROFILE);
    	add(toolbar.getToolbarManager());
    	add(new SpacerField(20, 8));
    	
    	int checkins = 0;
    	int rewards = 0;
    	int reviews = 0;
    	int completion = 30;
    	int friends = 20;
    	
    	BitmapField profilePic = new BitmapField(Bitmap.getBitmapResource("test.png"));
    	ColoredLabelField name = new ColoredLabelField(BasicTheme.FONT_COLOR_BLACK, "Name Name");
    	name.setFont(BasicTheme.title);
    	ColoredLabelField home = new ColoredLabelField(BasicTheme.FONT_COLOR_MID_GREY, "City");
    	ColoredLabelField lastCheckin = new ColoredLabelField(BasicTheme.FONT_COLOR_MID_GREY, "Last Checkin");
    	ButtonField checkinsButton = new ButtonField(checkins+Labels.LBL_CHECKINS, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
    	ButtonField rewardsButton = new ButtonField(rewards+Labels.LBL_REWARDS, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
    	ButtonField reviewsButton = new ButtonField(reviews+Labels.LBL_REVIEWS, ButtonField.CONSUME_CLICK | Field.USE_ALL_WIDTH);
    	
    	ColoredLabelField progress = new ColoredLabelField(BasicTheme.FONT_COLOR_MID_GREY, Labels.LBL_PROGRESS+"("+completion+"%)");
    	ButtonField edit = new ButtonField(Labels.LBL_EDIT_PROFILE+">>", ButtonField.CONSUME_CLICK|Field.USE_ALL_WIDTH);
    	edit.setBackground(BackgroundFactory.createSolidTransparentBackground(0xD9E6EC,0));
    	edit.setBorder(BorderFactory.createRoundedBorder(new XYEdges(0, 0, 0, 0), 0xD9E6EC, 0, Border.STYLE_TRANSPARENT));
    	GaugeField progressBar = new GaugeField( "",0,  100, 30, Field.USE_ALL_WIDTH);
    	
    	ColoredLabelField friendsLBL = new ColoredLabelField(BasicTheme.FONT_COLOR_MID_GREY, friends+" "+Labels.LBL_FRIENDS);
//    	BitmapField pic = new BitmapField(Bitmap.getBitmapResource("test.png"));
    	ButtonField arrows = new ButtonField(">>", ButtonField.CONSUME_CLICK|Field.FIELD_RIGHT);
//    	edit.setFont(BasicTheme);
    	arrows.setBackground(BackgroundFactory.createSolidTransparentBackground(0xD9E6EC,0));
    	arrows.setBorder(BorderFactory.createRoundedBorder(new XYEdges(0, 0, 0, 0), 0xD9E6EC, 0, Border.STYLE_TRANSPARENT));
    	    	    	
    	HorizontalFieldManager h = new HorizontalFieldManager(Field.USE_ALL_WIDTH);
    	VerticalFieldManager v = new VerticalFieldManager();
    	h.add(profilePic);
    	v.add(name);
    	v.add(home);
    	v.add(lastCheckin);
    	h.add(v);
    	
    	HorizontalFieldManager h0 = new HorizontalFieldManager(Field.USE_ALL_WIDTH);
		h0.add(checkinsButton);
		h0.add(rewardsButton);
		h0.add(reviewsButton);
		
		RoundedVerticalFieldManager2 manager = new RoundedVerticalFieldManager2(10, 10);
		manager.add(h);
		manager.add(h0);
		add(manager);
		
		HorizontalFieldManager h1 = new HorizontalFieldManager();
		h1.add(progress);
		h1.add(edit);
		add(h1);
		
		RoundedVerticalFieldManager2 manager0 = new RoundedVerticalFieldManager2(10, 10);
		manager0.add(progressBar);
		add(manager0);
		
		add(friendsLBL);
		HorizontalFieldManager h2 = new HorizontalFieldManager(Field.USE_ALL_WIDTH);
		h2.add(new BitmapField(Bitmap.getBitmapResource("test.png")));
		h2.add(new SpacerField(5,5));
		h2.add(new BitmapField(Bitmap.getBitmapResource("test.png")));
		h2.add(arrows);
		
		RoundedVerticalFieldManager2 manager1 = new RoundedVerticalFieldManager2(10, 10);
		manager1.add(h2);
		add(manager1);
	}

	public void fieldChanged(Field arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
