package com.f1rst.blackberry.view;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.ui.ApplicationMainScreen;
import com.f1rst.blackberry.ui.BitmapButtonField;
import com.f1rst.blackberry.ui.SpacerField;
import com.f1rst.blackberry.ui.component.ToolbarManager;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.PropertyChangeEvent;

public class MenuView extends BasicMainScreen implements FieldChangeListener, AbstractViewPanel {
	private DefaultController controller;
	
	public MenuView(DefaultController controller) { 
        this.controller = controller;
//        init();
    }

	private void init() {
		createFields();
		updateTitle(Labels.LBL_MENU);
		
	}

	private void createFields() {
		ToolbarManager toolbar = new ToolbarManager(controller);
		toolbar.setListener(this);
    	add(toolbar.getToolbarManager());
    	add(new SpacerField(20, 8));
    	
    	BitmapButtonField activity = null;
    	BitmapButtonField explore = null;
    	BitmapButtonField browse = null;
    	BitmapButtonField myProfile = null;
    	BitmapButtonField friends = null;
    	BitmapButtonField rewards = null;
    	BitmapButtonField following = null;
    	BitmapButtonField notifications = null;
    	BitmapButtonField settings = null;
    	
    	if(F1rstApplication.W == 640 && F1rstApplication.H == 480) {
        	activity = new BitmapButtonField(Bitmap.getBitmapResource("menu_activity_selected_640x480.png"), Bitmap.getBitmapResource("menu_activity_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	explore = new BitmapButtonField(Bitmap.getBitmapResource("menu_explore_selected_640x480.png"), Bitmap.getBitmapResource("menu_explore_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	browse = new BitmapButtonField(Bitmap.getBitmapResource("menu_browse_selected_640x480.png"), Bitmap.getBitmapResource("menu_browse_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	myProfile = new BitmapButtonField(Bitmap.getBitmapResource("menu_myprofile_selected_640x480.png"), Bitmap.getBitmapResource("menu_myprofile_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	friends = new BitmapButtonField(Bitmap.getBitmapResource("menu_friends_selected_640x480.png"), Bitmap.getBitmapResource("menu_friends_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	rewards = new BitmapButtonField(Bitmap.getBitmapResource("menu_rewards_selected_640x480.png"), Bitmap.getBitmapResource("menu_rewards_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	following = new BitmapButtonField(Bitmap.getBitmapResource("menu_following_selected_640x480.png"), Bitmap.getBitmapResource("menu_following_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	notifications = new BitmapButtonField(Bitmap.getBitmapResource("menu_notifications_selected_640x480.png"), Bitmap.getBitmapResource("menu_notifications_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	settings = new BitmapButtonField(Bitmap.getBitmapResource("menu_settings_selected_640x480.png"), Bitmap.getBitmapResource("menu_settings_640x480.png"), "", ButtonField.CONSUME_CLICK);
        }else { ///should be replaced with imgs for smaller res (480x360)
        	activity = new BitmapButtonField(Bitmap.getBitmapResource("menu_activity_selected_640x480.png"), Bitmap.getBitmapResource("menu_activity_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	explore = new BitmapButtonField(Bitmap.getBitmapResource("menu_explore_selected_640x480.png"), Bitmap.getBitmapResource("menu_explore_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	browse = new BitmapButtonField(Bitmap.getBitmapResource("menu_browse_selected_640x480.png"), Bitmap.getBitmapResource("menu_browse_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	myProfile = new BitmapButtonField(Bitmap.getBitmapResource("menu_myprofile_selected_640x480.png"), Bitmap.getBitmapResource("menu_myprofile_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	friends = new BitmapButtonField(Bitmap.getBitmapResource("menu_friends_selected_640x480.png"), Bitmap.getBitmapResource("menu_friends_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	rewards = new BitmapButtonField(Bitmap.getBitmapResource("menu_rewards_selected_640x480.png"), Bitmap.getBitmapResource("menu_rewards_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	following = new BitmapButtonField(Bitmap.getBitmapResource("menu_following_selected_640x480.png"), Bitmap.getBitmapResource("menu_following_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	notifications = new BitmapButtonField(Bitmap.getBitmapResource("menu_notifications_selected_640x480.png"), Bitmap.getBitmapResource("menu_notifications_640x480.png"), "", ButtonField.CONSUME_CLICK);
        	settings = new BitmapButtonField(Bitmap.getBitmapResource("menu_settings_selected_640x480.png"), Bitmap.getBitmapResource("menu_settings_640x480.png"), "", ButtonField.CONSUME_CLICK);
        }
    	
    	if(!controller.registered) {
    		activity.setEnabled(false);
    		activity.select(false);activity.setVisualState(Field.VISUAL_STATE_DISABLED|Field.VISUAL_STATE_DISABLED_FOCUS);
    		myProfile.setEnabled(false);
    		myProfile.select(false);
    		friends.setEnabled(false);
    		friends.select(false);
    		following.setEnabled(false);
    		following.select(false);
    		notifications.setEnabled(false);
    		notifications.select(false);
    		
    	}
    	
    	activity.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	explore.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	browse.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	myProfile.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	friends.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	rewards.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	following.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	notifications.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	settings.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field arg0, int arg1) {
				
			}
		});
    	
//    	activity.setVisualState(Field.VISUAL_STATE_DISABLED);
    	//when user is not loged in
//    	activity.select(false);
//    	activity.setEnabled(false);
    	//end
    	HorizontalFieldManager h = new HorizontalFieldManager(Field.FIELD_HCENTER);    
    	h.add(activity);
    	h.add(explore);
    	h.add(browse);
    	add(h);
    	
    	HorizontalFieldManager h0 = new HorizontalFieldManager(Field.FIELD_HCENTER);    
    	h0.add(myProfile);
    	h0.add(friends);
    	h0.add(rewards);
    	add(h0);
    	
    	HorizontalFieldManager h1 = new HorizontalFieldManager(Field.FIELD_HCENTER);    
    	h1.add(following);
    	h1.add(notifications);
    	h1.add(settings);
    	add(h1);
		
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(controller.SHOW_MENU_VIEW)) {
        	Runnable r = new Runnable() {
                public void run() {
                    init();
                }
            };
                controller.pushScreen(this);
                controller.invokeLater(r);
        }
		
	}

	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		
	}

}
