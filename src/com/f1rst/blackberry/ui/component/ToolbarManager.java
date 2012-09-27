package com.f1rst.blackberry.ui.component;

import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.ui.BitmapButtonField;
import com.f1rst.blackberry.ui.SpacerField;
import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.PropertyChangeEvent;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class ToolbarManager implements FieldChangeListener, AbstractViewPanel {
	DefaultController controller;
//
	BitmapButtonField f1rst;
	BitmapButtonField activity;
	BitmapButtonField friendRequests;
	BitmapButtonField profile;
	BitmapButtonField notifications;
	BitmapButtonField search;
	BitmapButtonField checkin;
//
	public final static String ACTIVE_F1RST  = "f";
	public final static String ACTIVE_ACTIVITY  = "a";
	public final static String ACTIVE_NOTIFICATIONS = "n";
	public final static String ACTIVE_PROFILE  = "p";
	public final static String ACTIVE_FRIEND_REQUESTS  = "fq";
	public final static String ACTIVE_SEARCH  = "s";
	public final static String ACTIVE_CHECKIN  = "c";
//	
	FieldChangeListener outerListener;	
//	
	int numIcons = 7;
//	
	public ToolbarManager(DefaultController controller) {
		this.controller = controller;			
	}
	
	public void setListener(FieldChangeListener listener) {
		this.outerListener = listener;
	}
//
	public Manager getToolbarManager() {
		VerticalFieldManager toolbarContainer = new VerticalFieldManager(Manager.NO_VERTICAL_SCROLL | Manager.NO_HORIZONTAL_SCROLL);
		
		String res = "";
    	if(F1rstApplication.W == 640 && F1rstApplication.H == 480) {
    		res = "_640x480";//toolbar_bg_387_50.jpg
//    		//toolbar_bg_387_50.jpg
    	}
		toolbarContainer.setBackground(BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource("bg_toolbar" + res + ".jpg")));
//
//		
		HorizontalFieldManager h = new HorizontalFieldManager(Field.USE_ALL_WIDTH);
//
		int spacer = 0;
		if(F1rstApplication.W == 640 && F1rstApplication.H == 480) {    		
    		// tab_search_63x46.png
    		// tab_search_63x46_selected2.png
			res = "_640x480";
			spacer = 225;
    	} 
//			else {
//    		// Bitmap.getBitmapResource("tab_home_80x58_selected2.png"),
//			// Bitmap.getBitmapResource("tab_home_80x58.png"), "",
//    		res = "_80x58";
//    	}
//		
		Bitmap bitmap = Bitmap.getBitmapResource("but_f1rst" + res + ".jpg");
//		
		f1rst = new BitmapButtonField(Bitmap.getBitmapResource("but_f1rst" + res + ".jpg"),Bitmap.getBitmapResource("but_f1rst_selected" + res + ".jpg"), "",ButtonField.CONSUME_CLICK);
		activity = new BitmapButtonField(Bitmap.getBitmapResource("but_activity" + res + ".png"),Bitmap.getBitmapResource("but_activity_selected" + res + ".png"), "",ButtonField.CONSUME_CLICK);
		checkin = new BitmapButtonField(Bitmap.getBitmapResource("but_checkin" + res + ".png"),Bitmap.getBitmapResource("but_checkin_selected" + res + ".png"), "",ButtonField.CONSUME_CLICK);
		friendRequests = new BitmapButtonField(Bitmap.getBitmapResource("but_friends" + res + ".png"),Bitmap.getBitmapResource("but_friends_selected" + res + ".png"), "",ButtonField.CONSUME_CLICK);
		notifications = new BitmapButtonField(Bitmap.getBitmapResource("but_notifications" + res + ".png"),Bitmap.getBitmapResource("but_notifications_selected" + res + ".png"), "",ButtonField.CONSUME_CLICK);
		profile = new BitmapButtonField(Bitmap.getBitmapResource("but_profile" + res + ".png"),Bitmap.getBitmapResource("but_profile_selected" + res + ".png"), "",ButtonField.CONSUME_CLICK);
		search = new BitmapButtonField(Bitmap.getBitmapResource("but_search" + res + ".png"),Bitmap.getBitmapResource("but_search_selected" + res + ".png"), "",ButtonField.CONSUME_CLICK);
		//home.setChecked(true);
//
		f1rst.setToggledButton(true);
		search.setToggledButton(true);
		activity.setToggledButton(true);
		profile.setToggledButton(true);
		checkin.setToggledButton(true);
		friendRequests.setToggledButton(true);
		notifications.setToggledButton(true);
//
//		home.setBackgroundColor(0x808080, 90);
//		search.setBackgroundColor(0x808080, 90);
//		cart.setBackgroundColor(0x808080, 90);
//		profile.setBackgroundColor(0x808080, 90);
//		history.setBackgroundColor(0x808080, 90);
//
//		
		int LEFT_PADDING = 35;// 480 screen width
//		LEFT_PADDING = (int)((F1rstApplication.W - home.getWidth() * numIcons) / 2);
		LEFT_PADDING = (int)((F1rstApplication.W - bitmap.getWidth() * numIcons) / 2);
//		Logger.log("LeftPadding: " + String.valueOf(LEFT_PADDING)
//				+" W: " + String.valueOf(F1rstApplication.W)
//				+" home W: " + String.valueOf(home.getWidth()) // -> 0
//				+" homeB W: " + String.valueOf(homeBitmap.getWidth()) // -> 63 :)
//				);
//		if(F1rstApplication.W == 320 && F1rstApplication.H == 240) {    		
//			LEFT_PADDING = 1;
//    	} else {
//    		LEFT_PADDING = 35;
//    	}
//
//		h.add(new SpacerField(LEFT_PADDING, 5));
		h.add(f1rst);
		h.add(new SpacerField(spacer, 5));
		h.add(activity);
		h.add(friendRequests);
		h.add(profile);
		h.add(notifications);
		h.add(search);
		h.add(checkin);
		// add(h);
		toolbarContainer.add(h);
//
		//home.setChecked(true);
//
		if(outerListener== null) {
			f1rst.setChangeListener(this);
			search.setChangeListener(this);
			activity.setChangeListener(this);
			profile.setChangeListener(this);
			friendRequests.setChangeListener(this);
			notifications.setChangeListener(this);
			checkin.setChangeListener(this);
		} else {
			f1rst.setChangeListener(outerListener);
			search.setChangeListener(outerListener);
			activity.setChangeListener(outerListener);
			profile.setChangeListener(outerListener);
			friendRequests.setChangeListener(outerListener);
			notifications.setChangeListener(outerListener);
			checkin.setChangeListener(outerListener);
		}
//
//				
		setActive(Model.getModel().getActiveToolbarItem());
//		
		//register this class as a view and it will receive components 
		controller.addView(this);
		return toolbarContainer;
	}
//
	public void fieldChanged(Field field, int context) {
//
//		
		if(field.equals(f1rst)) {
			
		} else if(field.equals(search)) {
//			
		} else
		if (field.equals(profile)) {
		 
		} 
		else if (/* field.equals(brand) || */field.equals(activity)) {
//			brandClicked();
			
		} else if (field.equals(friendRequests)) {
			
		}
		 else if (field.equals(notifications)) {
		 
		 }else if (field.equals(checkin)) {
		 
		 }
	}
	public void setFocusedItem() {
//		
		final String a = Model.getModel().getActiveToolbarItem();
		// Logger.log("ToolbarManager-setFocusedItem() " + String.valueOf(a));
		if(a == null) {
			return;
		}
//		controller.invokeLater(new Runnable(){
//			public void run() {
//		
		try {
				if(f1rst!=null && a.equalsIgnoreCase(ACTIVE_F1RST)) {		
		    		f1rst.setFocus();    		
				} else if(search!=null && a.equalsIgnoreCase(ACTIVE_SEARCH)) {
		    		search.setFocus();
				} else if(profile!=null && a.equalsIgnoreCase(ACTIVE_PROFILE)) {
		    		profile.setFocus();
				} else if(activity!=null && a.equalsIgnoreCase(ACTIVE_ACTIVITY)) {
					activity.setFocus();
				} else if(friendRequests!=null && a.equalsIgnoreCase(ACTIVE_FRIEND_REQUESTS)) {
					friendRequests.setFocus();
				}else if(notifications!=null && a.equalsIgnoreCase(ACTIVE_NOTIFICATIONS)) {
					notifications.setFocus();
				}else if(checkin!=null && a.equalsIgnoreCase(ACTIVE_CHECKIN)) {
					checkin.setFocus();
				}
//				
		} catch (IllegalStateException e) {
		} catch (Throwable t) {}
//			}
//		});
//		
	}
//	
	public void setActive(String a) {
//		
		//Logger.log("ToolbarManager-setActive: " + a);
//		
		//we have to remove all the listeners before we change the fields
		f1rst.setChangeListener(null);
		search.setChangeListener(null);
		activity.setChangeListener(null);
		profile.setChangeListener(null);
		friendRequests.setChangeListener(null);
		notifications.setChangeListener(null);
		checkin.setChangeListener(null);
//		
		if(a.equalsIgnoreCase(ACTIVE_F1RST)) {
			//f1rst			
			activity.setChecked(false);
    		search.setChecked(false);
    		checkin.setChecked(false);
    		profile.setChecked(false);
    		friendRequests.setChecked(false);
    		notifications.setChecked(false);
//    		
    		f1rst.setChecked(true);    		
		} else if(a.equalsIgnoreCase(ACTIVE_SEARCH)) {
			//search
			f1rst.setChecked(false);
			activity.setChecked(false);
    		checkin.setChecked(false);
    		profile.setChecked(false);
    		friendRequests.setChecked(false);
    		notifications.setChecked(false);
//			
    		search.setChecked(true);
		} else if(a.equalsIgnoreCase(ACTIVE_ACTIVITY)) {
			f1rst.setChecked(false);
    		checkin.setChecked(false);
    		profile.setChecked(false);
    		friendRequests.setChecked(false);
    		notifications.setChecked(false);
    		search.setChecked(false);
//			
    		activity.setChecked(true);
		} else if(a.equalsIgnoreCase(ACTIVE_PROFILE)) {
			//profile
			f1rst.setChecked(false);
    		checkin.setChecked(false);
    		friendRequests.setChecked(false);
    		notifications.setChecked(false);
    		search.setChecked(false);			
    		activity.setChecked(false);
//    		
			profile.setChecked(true);
		} else if(a.equalsIgnoreCase(ACTIVE_CHECKIN)) {
			f1rst.setChecked(false);
			profile.setChecked(false);
    		friendRequests.setChecked(false);
    		notifications.setChecked(false);
    		search.setChecked(false);			
    		activity.setChecked(false);
//    		
    		checkin.setChecked(true);
		}else if(a.equalsIgnoreCase(ACTIVE_FRIEND_REQUESTS)) {
			f1rst.setChecked(false);
			profile.setChecked(false);
			checkin.setChecked(false);
    		notifications.setChecked(false);
    		search.setChecked(false);			
    		activity.setChecked(false);
//    		
    		friendRequests.setChecked(true);
		}else if(a.equalsIgnoreCase(ACTIVE_NOTIFICATIONS)) {
			f1rst.setChecked(false);
			profile.setChecked(false);
			checkin.setChecked(false);
			friendRequests.setChecked(false);
    		search.setChecked(false);			
    		activity.setChecked(false);
//    		
    		notifications.setChecked(true);
		}
//			
		//we set the listeners back
		if(outerListener== null) {
			f1rst.setChangeListener(this);
			search.setChangeListener(this);
			activity.setChangeListener(this);
			profile.setChangeListener(this);
			friendRequests.setChangeListener(this);
			notifications.setChangeListener(this);
			checkin.setChangeListener(this);
		} else {
			f1rst.setChangeListener(outerListener);
			search.setChangeListener(outerListener);
			activity.setChangeListener(outerListener);
			profile.setChangeListener(outerListener);
			friendRequests.setChangeListener(outerListener);
			notifications.setChangeListener(outerListener);
			checkin.setChangeListener(outerListener);
		}
	}

//	public BitmapButtonField getCart() {
//		return cart;
//	}
//	
//	public BitmapButtonField getProfile() {
//		return profile;
//	}
//	
//	public BitmapButtonField getHistory() {
//		return history;
//	}
//	
//	public BitmapButtonField getHome() {
//		return home;
//	}
//	
//	public BitmapButtonField getSearch() {
//		return search;
//	}
//	
	public void modelPropertyChange(final PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(DefaultController.SET_ACTIVE_TOOLBAR_ITEM)) {
        	if(evt.getNewValue()!= null && evt.getNewValue() instanceof String) {
        		//setActive((String)evt.getNewValue());
        	}
        }
	}
}
