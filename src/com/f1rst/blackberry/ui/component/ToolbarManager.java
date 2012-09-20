package com.f1rst.blackberry.ui.component;

import com.f1rst.blackberry.F1rstApplication;
import com.f1rst.blackberry.util.AbstractViewPanel;
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

	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		
	}

//	DefaultController controller;
//
//	BitmapButtonField home;
//	BitmapButtonField search;
//	BitmapButtonField cart;
//	BitmapButtonField profile;
//	BitmapButtonField history;
//
//	public final static String ACTIVE_HOME  = "h";
//	public final static String ACTIVE_SEARCH  = "s";
//	public final static String ACTIVE_CART  = "c";
//	public final static String ACTIVE_PROFILE  = "p";
//	public final static String ACTIVE_HISTORY  = "hi";
//	
//	FieldChangeListener outerListener;	
//	
//	int numIcons = 5;
//	
//	public ToolbarManager(DefaultController controller) {
//		this.controller = controller;			
//	}
//	
//	public void setListener(FieldChangeListener listener) {
//		this.outerListener = listener;
//	}
//
//	public Manager getToolbarManager() {
//		VerticalFieldManager toolbarContainer = new VerticalFieldManager(
//				Manager.NO_VERTICAL_SCROLL | Manager.NO_HORIZONTAL_SCROLL);
//		
//		String res = "";
//    	if(F1rstApplication.W == 320 && F1rstApplication.H == 240) {
//    		res = "_320x50";//toolbar_bg_387_50.jpg
//    		//toolbar_bg_387_50.jpg
//    	}
//		toolbarContainer.setBackground(BackgroundFactory
//				.createBitmapBackground(Bitmap.getBitmapResource("toolbar_bg" + res + ".png")));
//
//		
//		HorizontalFieldManager h = new HorizontalFieldManager(
//				Field.USE_ALL_WIDTH);
//
//		if(F1rstApplication.W == 320 && F1rstApplication.H == 240) {    		
//    		// tab_search_63x46.png
//    		// tab_search_63x46_selected2.png
//			res = "_63x46";
//    	} else {
//    		// Bitmap.getBitmapResource("tab_home_80x58_selected2.png"),
//			// Bitmap.getBitmapResource("tab_home_80x58.png"), "",
//    		res = "_80x58";
//    	}
//		
//		Bitmap homeBitmap = Bitmap.getBitmapResource("tab_home" + res + ".png");
//		
//		home = new BitmapButtonField(
//				Bitmap.getBitmapResource("tab_home" + res + "_selected2.png"),
//				homeBitmap, "",
//				ButtonField.CONSUME_CLICK);
//		search = new BitmapButtonField(
//				Bitmap.getBitmapResource("tab_search" + res + "_selected2.png"),
//				Bitmap.getBitmapResource("tab_search" + res + ".png"), "",
//				ButtonField.CONSUME_CLICK);
//		cart = new BitmapButtonField(
//				Bitmap.getBitmapResource("tab_cart" + res + "_selected2.png"),
//				Bitmap.getBitmapResource("tab_cart" + res + ".png"), "",
//				ButtonField.CONSUME_CLICK);
//		profile = new BitmapButtonField(
//				Bitmap.getBitmapResource("tab_profile" + res + "_selected2.png"),
//				Bitmap.getBitmapResource("tab_profile" + res + ".png"), "",
//				ButtonField.CONSUME_CLICK);
//		history = new BitmapButtonField(
//				Bitmap.getBitmapResource("tab_history" + res + "_selected2.png"),
//				Bitmap.getBitmapResource("tab_history" + res + ".png"), "",
//				ButtonField.CONSUME_CLICK);
//
//		//home.setChecked(true);
//
//		home.setToggledButton(true);
//		search.setToggledButton(true);
//		cart.setToggledButton(true);
//		profile.setToggledButton(true);
//		history.setToggledButton(true);
//
//		home.setBackgroundColor(0x808080, 90);
//		search.setBackgroundColor(0x808080, 90);
//		cart.setBackgroundColor(0x808080, 90);
//		profile.setBackgroundColor(0x808080, 90);
//		history.setBackgroundColor(0x808080, 90);
//
//		
//		int LEFT_PADDING = 35;// 480 screen width
////		LEFT_PADDING = (int)((F1rstApplication.W - home.getWidth() * numIcons) / 2);
//		LEFT_PADDING = (int)((F1rstApplication.W - homeBitmap.getWidth() * numIcons) / 2);
////		Logger.log("LeftPadding: " + String.valueOf(LEFT_PADDING)
////				+" W: " + String.valueOf(F1rstApplication.W)
////				+" home W: " + String.valueOf(home.getWidth()) // -> 0
////				+" homeB W: " + String.valueOf(homeBitmap.getWidth()) // -> 63 :)
////				);
////		if(F1rstApplication.W == 320 && F1rstApplication.H == 240) {    		
////			LEFT_PADDING = 1;
////    	} else {
////    		LEFT_PADDING = 35;
////    	}
//
//		h.add(new SpacerField(LEFT_PADDING, 5));
//		h.add(home);
//		h.add(search);
//		h.add(cart);
//		h.add(profile);
//		h.add(history);
//		// add(h);
//		toolbarContainer.add(h);
//
//		//home.setChecked(true);
//
//		if(outerListener== null) {
//			home.setChangeListener(this);
//			search.setChangeListener(this);
//			cart.setChangeListener(this);
//			profile.setChangeListener(this);
//			history.setChangeListener(this);
//		} else {
//			home.setChangeListener(outerListener);
//			search.setChangeListener(outerListener);
//			cart.setChangeListener(outerListener);
//			profile.setChangeListener(outerListener);
//			history.setChangeListener(outerListener);
//		}
//
//				
//		setActive(Model.getModel().getActiveToolbarItem());
//		
//		//register this class as a view and it will receive components 
//		controller.addView(this);
//		return toolbarContainer;
//	}
//
//	public void fieldChanged(Field field, int context) {
//
//		
//		if(field.equals(history)) {
//			historyClicked();
//		} else
//		if(field.equals(home)) {
//			homeClicked();
//		} else
//		if (field.equals(profile)) {
//			profileClicked();
//			 
//		} 
//		else if (/* field.equals(brand) || */field.equals(search)) {
////			brandClicked();
//			searchClicked();
//		} else if (/*
//					 * field.equals(promotions)) { promotionsClicked(); } else
//					 * if (field.equals(newsEvents)) { newsEventsClicked(); }
//					 * else if (field.equals(cartLink) ||
//					 */field.equals(cart)) {
//			cartClicked();
//		}
//		// else if (field.equals(favorites)) {
//		// favoritesClicked();
//		// }
//	}
//
////	private void brandClicked() {
////
////		// start search by manufacture - it is same like search toolbar icon
////		// functions
////
////		// all brands
////
////		// brands by letter
////		// show the view
////
////		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_SEARCH_MANIFACTURE_VIEW, null, null));
////	}
//
//	private void homeClicked() {
////		Model.getModel().setActiveToolbarItem(ACTIVE_HOME);
//		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU, null, ACTIVE_HOME));
//	}
//
//	private void profileClicked() {
//		// controller.propertyChange(new PropertyChangeEvent(null,
//		// controller.SHOW_PROFILE_VIEW, null, null));
////		controller.getUserInfo("");
////		Model.getModel().setActiveToolbarItem(ACTIVE_PROFILE);
//		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU, null, ACTIVE_PROFILE));
//	}
//
//	private void cartClicked() {		
//		//
////		Model.getModel().setActiveToolbarItem(ACTIVE_CART);
//		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU, null, ACTIVE_CART));
//		
////		Logger.log("cart clicked!");
////		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_CART_VIEW, null, null));
////		controller.getCart("");
//
//	}
//
//	private void searchClicked() {
////		brandClicked();
////		Model.getModel().setActiveToolbarItem(ACTIVE_SEARCH);
//		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU, null, ACTIVE_SEARCH));
//	}
//	
//	private void historyClicked() {		
////		Model.getModel().setActiveToolbarItem(ACTIVE_HISTORY);
//		controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU, null, ACTIVE_HISTORY));
//		//controller.propertyChange(new PropertyChangeEvent(null, controller.SHOW_MENU, null, ACTIVE_HISTORY));
//	}
//	
//	
//	//set the focus on an item
//	public void setFocusedItem() {
//		
//		final String a = Model.getModel().getActiveToolbarItem();
//		// Logger.log("ToolbarManager-setFocusedItem() " + String.valueOf(a));
//		if(a == null) {
//			return;
//		}
////		controller.invokeLater(new Runnable(){
////			public void run() {
//		
//		try {
//				if(home!=null && a.equalsIgnoreCase(ACTIVE_HOME)) {		
//		    		home.setFocus();    		
//				} else if(search!=null && a.equalsIgnoreCase(ACTIVE_SEARCH)) {
//		    		search.setFocus();
//				} else if(cart!=null && a.equalsIgnoreCase(ACTIVE_CART)) {
//		    		cart.setFocus();
//				} else if(profile!=null && a.equalsIgnoreCase(ACTIVE_PROFILE)) {
//					profile.setFocus();
//				} else if(history!=null && a.equalsIgnoreCase(ACTIVE_HISTORY)) {
//		    		history.setFocus();
//				}
//				
//		} catch (IllegalStateException e) {
//		} catch (Throwable t) {}
////			}
////		});
//		
//	}
//	
//	public void setActive(String a) {
//		
//		//Logger.log("ToolbarManager-setActive: " + a);
//		
//		//we have to remove all the listeners before we change the fields
//		home.setChangeListener(null);
//		search.setChangeListener(null);
//		cart.setChangeListener(null);
//		profile.setChangeListener(null);
//		history.setChangeListener(null);
//		
//		if(a.equalsIgnoreCase(ACTIVE_HOME)) {
//			//home			
//			home.setChecked(false);
//    		search.setChecked(false);
//    		cart.setChecked(false);
//    		profile.setChecked(false);
//    		history.setChecked(false);
//    		
//    		home.setChecked(true);    		
//		} else if(a.equalsIgnoreCase(ACTIVE_SEARCH)) {
//			//search
//			home.setChecked(false);
//    		search.setChecked(false);
//    		cart.setChecked(false);
//    		profile.setChecked(false);
//    		history.setChecked(false);
//			
//    		search.setChecked(true);
//		} else if(a.equalsIgnoreCase(ACTIVE_CART)) {
//			
//			Logger.log("setActiveToolbar: c");
//			
//			//cart
//			home.setChecked(false);
//    		search.setChecked(false);
//    		cart.setChecked(false);
//    		profile.setChecked(false);
//    		history.setChecked(false);
//    		
//    		cart.setChecked(true);
////    		cart.select(true);
////    		cart.setFocus();
//		} else if(a.equalsIgnoreCase(ACTIVE_PROFILE)) {
//			//profile
//			home.setChecked(false);
//    		search.setChecked(false);
//    		cart.setChecked(false);
//    		profile.setChecked(false);
//    		history.setChecked(false);
//    		
//			profile.setChecked(true);
//		} else if(a.equalsIgnoreCase(ACTIVE_HISTORY)) {
//			//history
//			home.setChecked(false);
//    		search.setChecked(false);
//    		cart.setChecked(false);
//    		profile.setChecked(false);
//    		history.setChecked(false);
//			
//    		history.setChecked(true);
////    		history.select(true);
//    		//history.setFocus();
//		}
//			
//		//we set the listeners back
//		if(outerListener== null) {
//			home.setChangeListener(this);
//			search.setChangeListener(this);
//			cart.setChangeListener(this);
//			profile.setChangeListener(this);
//			history.setChangeListener(this);
//		} else {
//			home.setChangeListener(outerListener);
//			search.setChangeListener(outerListener);
//			cart.setChangeListener(outerListener);
//			profile.setChangeListener(outerListener);
//			history.setChangeListener(outerListener);
//		}
//	}
//
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
//	public void modelPropertyChange(final PropertyChangeEvent evt) {
//        if(evt.getPropertyName().equals(DefaultController.SET_ACTIVE_TOOLBAR_ITEM)) {
//        	if(evt.getNewValue()!= null && evt.getNewValue() instanceof String) {
//        		//setActive((String)evt.getNewValue());
//        	}
//        }
//	}
}
