package com.f1rst.blackberry.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.io.ConnectionNotFoundException;

import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.net.RestThread;
import com.f1rst.blackberry.restapi.F1rstApiClient;
import com.f1rst.blackberry.ui.FontManager;
import com.f1rst.blackberry.view.OKDialogListener;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.RadioException;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.MainScreen;

public class DefaultController extends AbstractControllerImplementation implements OKDialogListener {
	boolean isInterrupted = false;
	RestThread t;
	F1rstApiClient client;
	
	static long lastOperation;
	static Font defaultFont;
	static Font defaultFont12;
	static Font defaultFont14;
	static Font defaultFont16;
	static Font defaultFont18;
	static Font defaultFont20;
	static Font defaultFont22;
	static Font defaultFontBold14;
	static Font defaultFontBold16;
	static Font defaultFontBold18;
	static Font defaultFontBold20;
	
	public DefaultController() {
	}
	
	/**
	 * we set a long var when we have do a network operation.
	 * then we count the difference between the last operation and
	 * if the difference is < 1000 (one second) then it return true.
	 * @return
	 */
	private boolean isNetworkOperationStarted() {
		long now = System.currentTimeMillis();
		
		if(now - lastOperation < 1000) {
			Logger.log("isNetworkOperationStarted() true");
			return true;
		} else {
			Logger.log("isNetworkOperationStarted() false");
			lastOperation = now;
			return false;
		}
	}
	
	public void show() {
		Logger.log("show");

		Runnable r = new Runnable() {

			public void run() {
				while (UiApplication.getUiApplication().getScreenCount() > 1) {
					UiApplication.getUiApplication().popScreen(
							UiApplication.getUiApplication().getActiveScreen());
				}
			}
		};
		
		invokeLater(r);

		new Timer().schedule(new TimerTask() {

			public void run() {
				
				propertyChange(new PropertyChangeEvent(null, DefaultController.SHOW_LOGIN, this, this));
				
			}
		}, 200);

	}
	
	public void pushScreen(final MainScreen s) {
		if (s == null) {
			Logger.log("DefaultController.pushScreen: screen is null.");
			return;
		}

		if (s.isDisplayed()) {
			Logger.log("DefaultController.pushScreen: screen is displayed.");
			// if the screen is already on the display stack.			
			return;
		}
		Logger.log("DefaultController.pushScreen: " + s);
		final UiApplication ui = UiApplication.getUiApplication();
		if (!ui.hasEventThread()) {
			Logger.log("hasEventThread false ");
			ui.pushScreen(s);
		} else {
			ui.invokeLater(new Runnable() {

				// @Override
				public void run() {
					if (s != null) {
						if (UiApplication.getUiApplication().getActiveScreen() != null
								&& !UiApplication.getUiApplication()
										.getActiveScreen().equals(s)) {
							ui.pushScreen(s);
						}
					} else {
						inform("controller push screen that is empty(s  is null)");
					}
				}
			});
		}
	}
	
	public static void inform(final String message) {
		UiApplication.getUiApplication().invokeLater(new Runnable() {

			// @Override
			public void run() {
				Status.show(message);
			}
		});
	}
	
	public static void inform(final String message, final int seconds) {
		UiApplication.getUiApplication().invokeLater(new Runnable() {

			// @Override
			public void run() {
				Status.show(message, seconds * 1000);
			}
		});
	}
	
	public static EncodedImage scaleImageToHeight(EncodedImage encoded, int newHeight) {
		return scaleToFactor(encoded, encoded.getHeight(), newHeight);
	}
	
	public static EncodedImage scaleImageToWidth(EncodedImage encoded,
			int newWidth) {
		return scaleToFactor(encoded, encoded.getWidth(), newWidth);
	}
	
	public static EncodedImage scaleToFactor(EncodedImage encoded, int curSize,
			int newSize) {
		int numerator = Fixed32.toFP(curSize);
		int denominator = Fixed32.toFP(newSize);
		int scale = Fixed32.div(numerator, denominator);

		return encoded.scaleImage32(scale, scale);
	}
	
//	public static void showMessage(String message) {
//		final String _message = message;
//		UiApplication.getUiApplication().invokeLater(new Runnable() {
//
//			public synchronized void run() {
//				// Dialog.inform(_message);
//				StatusView2.showInfoDialog(_message);
//			}
//		});
//	}
	
//	private String checkErrorMessage(Exception e) {
//		String message = "Unable to connect to service.";
//		//Logger.log("Unable to connect to service. Service returned:\n" + e.toString());
//		if(e instanceof RestException) {
//			if(((RestException)e).getCurrentException()!=null) {
//				if(((RestException)e).getCurrentException() instanceof IOException) {
//					message = "Unable to transmit data.";
//				} else if(((RestException)e).getCurrentException() instanceof ConnectionNotFoundException) {
//					message = "Unable to transmit data.";
//				} else if(((RestException)e).getCurrentException() instanceof InterruptedIOException) {
//					message = "Unable to transmit data.";
//				} else if(((RestException)e).getCurrentException() instanceof RadioException) {
//					message = "Insufficient network coverage";
//				}
//			}
//		}
//		
//		return message;	
//	}
	
	public void exit() {
		Logger.log("Controler exit()");
		Model.getModel().getSettings().commit();

		System.exit(0);
	}
	
	public void invokeAndWait(Runnable r) {
		final UiApplication ui = UiApplication.getUiApplication();
		ui.invokeAndWait(r);
	}
	
	public void invokeLater(Runnable r) {
		final UiApplication ui = UiApplication.getUiApplication();
		ui.invokeLater(r);
	}
	
	public void logout() {

		Logger.log("logging out");
		isInterrupted = false;

		final DefaultController df = this;
		//setModelProperty(SET_STATUS_MESSAGE, Labels.INF_LOGGING_OUT + "..");
		t = new RestThread() {
			// @Override

			public void run() {
				boolean isError = false;

//				if (Model.getModel().getSettingsTable().getSettingOne() != null
//						&& !Model.getModel().getSettingsTable().getSettingOne()
//								.equals("0")) {
//				}
//				Model.getModel().getSettingsTable().setSettingOne("0");
//				Model.getModel().getSettings().setSaveCredentials(false);
//				Model.getModel().getSettings().setUserName("");
//				Model.getModel().getSettings().setPassword("");
//
//				Model.getModel().getSettings().setSettingsTable(null);

				show();
			}
		};

		t.start();
	}
	
	public void performAction(Object o) {
		if (o instanceof Boolean) {
			// status 2
			if (((Boolean) o).booleanValue()) {
				// true if cancel is pressed
				isInterrupted = true;
				if (t != null) {
					t.stopConn(); // stop the connection if the user click on
							      // cancel button
				}
				setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(false));
			} else {
			}
		}
	}
	
	public void setModelProperty(String propertyName, Object newValue) {
		super.setModelProperty(propertyName, newValue);
	}
	
	public static Font getFont() {
		if (defaultFont == null)
			defaultFont = FontManager.getComponentFont(20);
		return defaultFont;
	}
	
	public static Font getFont18() {
		if (defaultFont18 == null)
			defaultFont18 = FontManager.getComponentFont(18);
		return defaultFont18;
	}
	
	public static Font getFontBold18() {
		if (defaultFontBold18 == null)
			defaultFontBold18 = FontManager.getComponentFont(Font.BOLD, 18);
		return defaultFontBold18;
	}
	
	public static Font getFontBold16() {
		if (defaultFontBold16 == null)
			defaultFontBold16 = FontManager.getComponentFont(Font.BOLD, 16);
		return defaultFontBold16;
	}
	
	public static Font getFontBold14() {
		if (defaultFontBold14 == null)
			defaultFontBold14 = FontManager.getComponentFont(Font.BOLD, 14);
		return defaultFontBold14;
	}
	
	public static Font getFont20() {
		if (defaultFont20 == null)
			defaultFont20 = FontManager.getComponentFont(20);
		return defaultFont20;
	}
	
	public static Font getFont22() {
		if (defaultFont22 == null)
			defaultFont22 = FontManager.getComponentFont(22);
		return defaultFont22;
	}
	
	public static Font getFont12() {
		if (defaultFont12 == null)
			defaultFont12 = FontManager.getComponentFont(12);
		return defaultFont12;
	}
	
	public static Font getFont14() {
		if (defaultFont14 == null)
			defaultFont14 = FontManager.getComponentFont(14);
		return defaultFont14;
	}
	
	public static Font getFont16() {
		if (defaultFont16 == null)
			defaultFont16 = FontManager.getComponentFont(16);
		return defaultFont16;
	}
	
	public static Font getFontBold20() {
		if (defaultFontBold20 == null)
			defaultFontBold20 = FontManager.getComponentFont(Font.BOLD, 20);
		return defaultFontBold20;
	}
	
	public final static String SHOW_LOGIN = "showLogin";
	public final static String SET_LOGIN_RESULT = "setLoginResult";
	public static final String SET_STATUS_MESSAGE = "setStatusMessage";
	
	public static final String SET_IS_STATUS_SHOWN = "setIsStatusShown";
	
	public final static String SET_SETTINGS_TABLE = "setSettingsTable";
	
	public final static String SET_USERNAME = "setUserName";
	public final static String SET_PASSWORD = "setPassword";
	
	public final static String SET_SETTING_ONE = "setSettingOne";
	public final static String SET_ACCESS_TOKEN = "setAccessToken";

}
