package com.f1rst.blackberry.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.system.Backlight;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.PNGEncodedImage;
import net.rim.device.api.system.RadioException;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.MainScreen;

import com.f1rst.blackberry.ImageCache;
import com.f1rst.blackberry.facebook.ApplicationSettings;
import com.f1rst.blackberry.facebook.F1rstFacebookClient;
import com.f1rst.blackberry.facebook.Facebook;
import com.f1rst.blackberry.json.JSONException;
import com.f1rst.blackberry.json.JSONObject;
import com.f1rst.blackberry.log.Logger;
import com.f1rst.blackberry.model.LoginResult;
import com.f1rst.blackberry.net.RestException;
import com.f1rst.blackberry.net.RestThread;
import com.f1rst.blackberry.restapi.F1rstApiClient;
import com.f1rst.blackberry.restapi.User;
import com.f1rst.blackberry.ui.FontManager;
import com.f1rst.blackberry.ui.ImageGetterListener;
import com.f1rst.blackberry.view.LoginView;
import com.f1rst.blackberry.view.OKDialogListener;
import com.f1rst.blackberry.view.StatusView2;

/**
 * The controller of the application
 * 
 * @author ivaylo
 */
public class DefaultController extends AbstractControllerImplementation
		implements OKDialogListener {

	
	boolean isInterrupted = false;
	RestThread t;
	F1rstApiClient client;
	
	static long lastOperation;

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
	
	public void getUserInfo(final String token) {
		Logger.log("GetUserIngo");
		
		if(isNetworkOperationStarted()) {
			return;
		}
		
		// showStatus(Labels.INF_GETTING_USER_INFO);
		setModelProperty("SET_STATUS_MASSAGE", Labels.INF_GETTING_USER_INFO + "...");
		setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(true));
		isInterrupted = false;
		
		t = new RestThread() {
			// @Override

			public void run() {
				boolean isError = false;
				String message = "";

				if (Model.DEBUG) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
					}
				}

				try {
					F1rstApiClient c = new F1rstApiClient();

					String t = new JSONObject(Model.getModel().getSettings().getLoginResult().getResult()).getJSONObject(
							"Token").optString("Token");
					if (t.length() == 0) {
						inform("invalid token!");
					} else {
						String result = c.getUserInfo(t);

						propertyChange(new PropertyChangeEvent(null,
								SHOW_PROFILE_VIEW, null, null));
						propertyChange(new PropertyChangeEvent(null,
								SET_PROFILE, null,
								c.mapProfileFromResponse(new JSONObject(
										result))));

						Logger.log(result);
					}
				} catch (Exception e) {
					isError = true;
					message = "Unable to connect to service.";
					Logger.log("Unable to connect to service. Service returned:\n"
							+ e.toString());
				} finally {
					setModelProperty(SET_STATUS_MESSAGE, "");
					setModelProperty(DefaultController.SET_IS_STATUS_SHOWN,
							new Boolean(false));
					if (isError) {
						propertyChange(new PropertyChangeEvent(null,
								ERROR_GET_PROFILE, null, message));
					}
				}
			}
		};

		t.start();
	}

	// search all brands -> on item click
	 //prepare to send utf encoded string to server (bytes like c3,a8( = "e" with))
    public static String toUTF(String latin) {
        String utf = "";
        try {
            utf = new String(latin.getBytes("UTF-8"));
            return utf;
        } catch (java.io.UnsupportedEncodingException e) {
            return latin;
        } catch (Exception ex) {
            return latin;
        }
    }

    
    /**
	 * 
	 * @param userName
	 * @param password
	 * @param languageIndex index from the following values String [] values = { Labels.LBL_ENGLISH, Labels.LBL_ITALIAN, Labels.LBL_SPANISH};
	 * @param save
	 */
	public void userNormalLogin(final String userName, final String password, final boolean save) {
		Logger.log("userNormalLogin at:" + "http://f1rst.trapis.net/f1rst-apisim/rest/authentication.signin?username=test&password=test");

		if(isNetworkOperationStarted()) {
			return;
		}
		
//		showStatus(Labels.INF_LOGGING_IN);

		if (!(UiApplication.getUiApplication().getActiveScreen() instanceof LoginView)) {
//			setModelProperty(SET_STATUS_MESSAGE, Labels.LBL_SIGNING_IN);
//			setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(true));
		}
		
		
//		setModelProperty(SET_STATUS_MESSAGE, Labels.LBL_LOGGING);
		isInterrupted = false;

		if (save) {
			Model.getModel().setUserName(userName);
			Model.getModel().setPassword(password);
			Model.getModel().getSettings().setSaveCredentials(true);
		}

		t = new RestThread() {
			// @Override

			public void run() {
				boolean isError = false;
				String message = "";

				if (Model.DEBUG) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
					}
				}

				try {
					User u = new User();
					String application_id = "test";
					String resultS = u.login(userName, password, application_id);
					 
					Logger.log("userNormalLogin result: " + String.valueOf(resultS));

					//validating if the token is valid
//					try {
//						String t = new JSONObject(resultS).getJSONObject(
//								"Token").optString("Token");
//						if(TextUtils.isEmpty(t)) {
//							//username or password problem.
//							showMessage(Labels.INF_INVALID_CREDENTIALS);
//							if (save) {
//								Model.getModel().setUserName("");
//								Model.getModel().setPassword("");
//								Model.getModel().getSettings().setSaveCredentials(false);
//								Model.getModel().getSettings().commit();
//							}
//							return;
//						}
//					} catch (JSONException e) {
//						showMessage(Labels.INF_INVALID_CREDENTIALS);
//						if (save) {
//							Model.getModel().setUserName("");
//							Model.getModel().setPassword("");
//							Model.getModel().getSettings().setSaveCredentials(false);
//							Model.getModel().getSettings().commit();
//						}
//						return;
//					}

					// parse user login result
					if (resultS != null) {
						Logger.log("ttttttt");
						LoginResult lr = new LoginResult(resultS);
						Logger.log("lr: "+lr.getResult());
						setModelProperty(SET_LOGIN_RESULT, lr);
						propertyChange(new PropertyChangeEvent(null, HIDE_LOGIN, null, null));						
						// show main menu						
					} else {
						showMessage(Labels.INF_SOMETHING_WRONG_RETRY);
					}
				} catch (Exception e) {
					isError = true;					
					message = checkErrorMessage(e);
					Logger.log("Unable to connect to service. Service returned:\n" + e.toString());
				} finally {
					setModelProperty(SET_STATUS_MESSAGE, "");
					setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(false));
					
					if(isError) {
						inform(message);
					}
				}
			}
		};

		t.start();
	}

	private String checkErrorMessage(Exception e) {
		String message = "Unable to connect to service.";
		//Logger.log("Unable to connect to service. Service returned:\n" + e.toString());
		if(e instanceof RestException) {
			if(((RestException)e).getCurrentException()!=null) {
				if(((RestException)e).getCurrentException() instanceof IOException) {
					message = "Unable to transmit data.";
				} else if(((RestException)e).getCurrentException() instanceof ConnectionNotFoundException) {
					message = "Unable to transmit data.";
				} else if(((RestException)e).getCurrentException() instanceof InterruptedIOException) {
					message = "Unable to transmit data.";
				} else if(((RestException)e).getCurrentException() instanceof RadioException) {
					message = "Insufficient network coverage";
				}
			}
		}
		
		return message;	
	}
	
	// get product details
//	public void getDataSheet(final String token, final String productCode, Product p) {
//		Logger.log("getDataSheet: " + token + " " + productCode);
//
//		if(isNetworkOperationStarted()) {
//			return;
//		}
//		
////		propertyChange(new PropertyChangeEvent(null,
////				SHOW_PRODUCT_DESTAILS_VIEW, null, p));
//		
//		final ProductDetailsView pd = new ProductDetailsView(this);
//		pd.init();
//		pd.setInitialProduct(p);
//		pushScreen(pd);
//		this.addView(pd);
//
//		// showStatus(Labels.LBL_SEARCHING);
//		setModelProperty(SET_STATUS_MESSAGE, Labels.INF_LOADING);
//		setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(true));
//		
//		isInterrupted = false;
//
//		t = new RestThread() {
//			// @Override
//
//			public void run() {
//				boolean isError = false;
//				String message = "";
//
//				if (Model.DEBUG) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException ex) {
//					}
//				}
//
//				try {
//					F1rstApiClient c = new F1rstApiClient();
//
//					String t = new JSONObject(Model.getModel().getSettings()
//							.getLoginResult().getResult()).getJSONObject(
//							"Token").optString("Token");
//					if (t.length() == 0) {
//						inform("invalid token!");
//					} else {
//						String result = c.getDataSheet(t, productCode);
//						Logger.log(result);
////						propertyChange(new PropertyChangeEvent(
////								null,
////								SET_PRODUCT_DETAILS,
////								null,
////								c.mapProductFromResponse(new JSONObject(result))));
//
//						pd.setProduct(new PropertyChangeEvent(
//								null,
//								SET_PRODUCT_DETAILS,
//								null,
//								c.mapProductFromResponse(new JSONObject(result))));
//						
//					}
//
//					// set model property
//					// setModelProperty(SET_CATEGORIES, p);
//				} catch (Exception e) {
//					isError = true;
//					message = "Unable to connect to service.";
//					Logger.log("Unable to connect to service. Service returned:\n"
//							+ e.toString());
//				} finally {
//					setModelProperty(SET_STATUS_MESSAGE, "");
//					setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(false));
//					if (isError) {
//						propertyChange(new PropertyChangeEvent(null,
//								ERROR_GET_PRODUCT_DETAILS, null, message));
//					}
//				}
//			}
//		};
//
//		t.start();
//	}
//
//	// get product details for the cart view
//	public Product getDataSheet(final String productCode) {
//        Logger.log("getDataSheet: " + productCode==null?"null":productCode);
//
////        if(isNetworkOperationStarted()) {
////			return;
////		}
//        
//        if(productCode == null) {
//        	return null;
//        }
////        t = new RestThread() {
////            //@Override
////
////            public void run() {
////                boolean isError = false;
////                String message = "";               
////                
////                if (Model.DEBUG) {
////                    try {
////                        Thread.sleep(100);
////                    } catch (InterruptedException ex) {
////                    }
////                }
////               
//                try {
//                	F1rstApiClient c = new F1rstApiClient();
//                	
//                	String t = new JSONObject( Model.getModel().getSettings().getLoginResult().getResult()).getJSONObject("Token").optString("Token");
//                	if(t.length() == 0) {
//                		//inform("invalid token!");
//                		return null;
//                	} else {
//                		String result = c.getDataSheet(t, productCode);
//                		 
//                		return c.mapProductFromResponse(new JSONObject(result));                		
//                	}
//                } catch (Exception e) {
////                    isError = true;
////                    message = "Unable to connect to service.";
//                    Logger.log("Unable to connect to service. Service returned:\n"
//                            + e.toString());
//                } finally {
////                    setModelProperty(SET_STATUS_MESSAGE, "");
////                    setModelProperty(DefaultController.SET_IS_STATUS_SHOWN, new Boolean(false));
////                    if(isError)
////                        propertyChange(new PropertyChangeEvent(null, ERROR_GET_PRODUCT_DETAILS, null, message));
//                }
//                return null;
////            }
////        };
////
////        t.start();
//    }

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

				// Logger.log("User id: " + Model.getModel().getUserId());
				Logger.log("SO id: " + Model.getModel().getSettingOne());
				Logger.log("AT: " + Model.getModel().getAccessToken());
				
				// sets the language { Labels.LBL_ENGLISH, Labels.LBL_ITALIAN, Labels.LBL_SPANISH}
//				if(Model.getModel().getLanguageIndex() == 0) {
//					// English
//					Labels.setLabels(Labels.LABELS_ENGLISH);
//				} else if (Model.getModel().getLanguageIndex() == 1) {
//					// Italian
//					Labels.setLabels(Labels.LABELS_ITALIAN);
//				} else {
//					// Spanish
//					Labels.setLabels(Labels.LABELS_SPANISH);
//				}
				
				if (Model.getModel().getSettings().isSaveCredentials()
						&& !TextUtils.isEmpty(Model.getModel().getSettings().getUserName())
						&& !TextUtils.isEmpty(Model.getModel().getSettings().getPassword())) {
					
					userNormalLogin(Model.getModel().getUserName(), Model
							.getModel().getPassword(), true);

				} else {
					// login
					propertyChange(new PropertyChangeEvent(null, DefaultController.SHOW_LOGIN, this, this));
				}
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

	public void popScreen(final MainScreen s) {
		if (s == null) {
			Logger.log("pop screen: null");
			return;
		}
		Logger.log("pop screen: " + s.toString());
		final UiApplication ui = UiApplication.getUiApplication();
		ui.invokeLater(new Runnable() {

			// @Override
			public void run() {
				try {
					ui.popScreen(s);
				} catch (IllegalArgumentException iae) {
					Logger.log("tried to pop a screen that is not on display stack"
							+ iae.getMessage());
				}
			}
		});
	}

	public void invokeLater(Runnable r) {
		final UiApplication ui = UiApplication.getUiApplication();
		ui.invokeLater(r);
	}
	
	public void invokeAndWait(Runnable r) {
		final UiApplication ui = UiApplication.getUiApplication();
		ui.invokeAndWait(r);
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

	public static void showMessage(String message) {
		final String _message = message;
		UiApplication.getUiApplication().invokeLater(new Runnable() {

			public synchronized void run() {
				// Dialog.inform(_message);
				StatusView2.showInfoDialog(_message);
			}
		});
	}

	public void showStatus(String text) {
		// show the spinner
	}

	public void hideStatus() {
		// hide the spinne
	}

	// OKDialog listener implementation
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

	public void exit() {
		Logger.log("Controler exit()");
		Model.getModel().getSettings().commit();

		System.exit(0);
	}

	public void turnOnBacklight() {
		if ((Display.getProperties() & Display.DISPLAY_PROPERTY_REQUIRES_BACKLIGHT) != 0) {
			Backlight.enable(true, 255);// 255 seconds
		}
	}

	public void turnOnBacklight(int seconds) {
		if ((Display.getProperties() & Display.DISPLAY_PROPERTY_REQUIRES_BACKLIGHT) != 0) {
			Backlight.enable(true, seconds);// 255 seconds max
		}
	}

	public String getNiceErrormessage(String error) {
		String niceErrorMessage = Labels.LBL_OPERATION_FAILED;

		if (error == null) {
			return niceErrorMessage;
		}

		error = error.toLowerCase();

		if (error.indexOf("ioexception") > -1) {
			if (error.indexOf("connection closed") > -1) {
				niceErrorMessage = "";
			} else if (error.indexOf("tcp read time out") > -1) {
				niceErrorMessage = Labels.INF_CONNECTION_TIMED_OUT;
			} else if (error.indexOf("timed out") > -1) {
				niceErrorMessage = Labels.INF_CONNECTION_TIMED_OUT;
			} else if (error.indexOf("connection timed out") > -1) {
				niceErrorMessage = Labels.INF_CONNECTION_TIMED_OUT;// removed -
																	// return ""
			}
		} else if (error != null
				&& error.indexOf("interruptedioexception") > -1) {
			if (error.indexOf("local connection timed out after") > -1) {
				niceErrorMessage = Labels.INF_CONNECTION_TIMED_OUT;
			}
		} else if (error.indexOf("access denied") > -1) {
			return "";
		}

		return niceErrorMessage;
	}

	public void setModelProperty(String propertyName, Object newValue) {
		super.setModelProperty(propertyName, newValue);
	}

	// private ArrayOfString vectorToArrayOfStrings(Vector v) {
	// if(v!=null && v.size()>0){
	// String s[] = new String[v.size()];
	// for (int i = 0; i < v.size(); i++) {
	// s[i] = (String)v.elementAt(i);
	// }
	// ArrayOfString a = new ArrayOfString(s);
	// return a;
	// } else {
	// return null;
	// }
	// }

	/**
	 * http get the image form network
	 * 
	 * @param u = url of the image
	 * @param listener
	 * @param optWidth
	 * @param optHeight
	 * @param getFromCache
	 */
	public void getImage(String u, final ImageGetterListener listener,
			final int optWidth, final int optHeight, final boolean getFromCache) {
		// Logger.log("getImage: w: " + String.valueOf(optWidth)
		// + "h: " + String.valueOf(optHeight)
		// + " [" + url + "]");
		
		
		if (u != null && u.length() > 0) {				
			if(u.indexOf(' ')>-1) {
				u = com.f1rst.blackberry.util.string.StringUtils.replaceAll(u, " ", "%20");
			}
			final String url = u;
			if (!url.startsWith("http")) {
				// Logger.log("loading local: " + url);
				try {
					String s = url.substring((url.lastIndexOf('/') + 1));
					// Logger.log("----" + s);

					if (s.endsWith("noimage.gif")) {
						// fix the problem with noimage.gif
						// Logger.log("replacing gif");
						s = "noimage.png";
					}
					// Logger.log("----" + s);

					Bitmap b = Bitmap.getBitmapResource(s);
					if (b != null && b.getWidth() != optWidth
							&& b.getHeight() != optHeight && optWidth > 0
							&& optHeight > 0) {

						PNGEncodedImage encoder = PNGEncodedImage.encode(b);

						byte[] imageBytes = encoder.getData();
						EncodedImage fullImage = EncodedImage
								.createEncodedImage(imageBytes, 0,
										imageBytes.length);
						if (optWidth > 0)
							fullImage = scaleImageToWidth(fullImage, optWidth);
						if (optHeight > 0)
							fullImage = scaleImageToHeight(fullImage, optHeight);

						b = fullImage.getBitmap();
					}
					listener.setBitmap(b);
					return;
				} catch (Throwable t) {
				}
			}

			// load image from cache if exists
			// Bitmap t = ImageCache.getInstance().get(url);
			// listener.setBitmap(t);

			if (getFromCache) {
				EncodedImage t = ImageCache.getInstance().getEncodedImage(url);
				if (t != null) {
					if (t.getWidth() > optWidth && optWidth > 0) {
						t = scaleImageToWidth(t, optWidth);
					}

					if (t.getHeight() > optHeight && optHeight > 0) {
						t = scaleImageToHeight(t, optWidth);
					}
					if (t.getBitmap() != null) {
						listener.setBitmap(t.getBitmap());
						return;
					}
				}
			} else {
				// try to load the image from session saved images
				EncodedImage t = ImageCache.getInstance()
						.getSessionEncodedImage(url);
				if (t != null) {
					if (t.getWidth() > optWidth && optWidth > 0) {
						t = scaleImageToWidth(t, optWidth);
					}
					if (t.getHeight() > optHeight && optHeight > 0) {
						t = scaleImageToHeight(t, optHeight);
					}
					if (t.getBitmap() != null) {
						listener.setBitmap(t.getBitmap());
						return;
					}
				}
			}

			// Logger.log("gi pre exec");
			ThreadPoolFactory.execute(new Runnable() {

				public void run() {
					// Logger.log("gi run");
					HttpConnection connection = null;
					InputStream inputStream = null;

					try {
						// connection = (HttpConnection) Connector.open(url, Connector.READ_WRITE, true);
						// connection = (HttpConnection) Connector.open(url + new AutoConnectionManager().connectionSuffix, Connector.READ_WRITE, false);
						connection = new F1rstApiClient().getConnection(url);
						inputStream = connection.openInputStream();
						byte[] responseData = new byte[100];
						int length = 0;
						ByteArrayOutputStream rawResponse = new ByteArrayOutputStream();
						while (-1 != (length = inputStream.read(responseData))) {
							rawResponse.write(responseData, 0, length);
						}
						int responseCode = connection.getResponseCode();
						if (responseCode != HttpConnection.HTTP_OK) {
							throw new IOException("HTTP response code: " + responseCode);
						}

						EncodedImage bitmap = EncodedImage.createEncodedImage(
								rawResponse.toByteArray(), 0,
								rawResponse.toByteArray().length);

						if (optWidth > 0 && bitmap.getWidth() > optWidth) {
							bitmap = scaleImageToWidth(bitmap, optWidth);
						}
						if (optHeight > 0 && bitmap.getHeight() > optHeight) {
							bitmap = scaleImageToHeight(bitmap, optHeight);
						}

						Bitmap b = bitmap.getBitmap();
						listener.setBitmap(b);

						// put the image into the cache
						if (getFromCache) {
							// ImageCache.getInstance().put(url, b);
							ImageCache.getInstance().put(url, bitmap);
						} else {
							// put the image in the session image cache
							ImageCache.getInstance().putSession(url, bitmap);
						}

						// Logger.log("finish " + url);
					} catch (final Exception ex) {
						Logger.log("gi ERR: " + ex.getMessage() + url);
						listener.error(ex.getMessage());
					} finally {
						try {
							inputStream.close();
							inputStream = null;
							connection.close();
							connection = null;
						} catch (Exception e) {
						}
					}
				}
			});
		}
	}

	public static EncodedImage scaleImageToWidth(EncodedImage encoded,
			int newWidth) {
		return scaleToFactor(encoded, encoded.getWidth(), newWidth);
	}

	public static EncodedImage scaleImageToHeight(EncodedImage encoded,
			int newHeight) {
		return scaleToFactor(encoded, encoded.getHeight(), newHeight);
	}

	public static EncodedImage scaleToFactor(EncodedImage encoded, int curSize,
			int newSize) {
		int numerator = Fixed32.toFP(curSize);
		int denominator = Fixed32.toFP(newSize);
		int scale = Fixed32.div(numerator, denominator);

		return encoded.scaleImage32(scale, scale);
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

				if (Model.getModel().getSettingsTable().getSettingOne() != null
						&& !Model.getModel().getSettingsTable().getSettingOne()
								.equals("0")) {
				}
				Model.getModel().getSettingsTable().setSettingOne("0");
				Model.getModel().getSettings().setSaveCredentials(false);
				Model.getModel().getSettings().setUserName("");
				Model.getModel().getSettings().setPassword("");

				Model.getModel().getSettings().setSettingsTable(null);

				show();
			}
		};

		t.start();
	}

	static Font defaultFont;

	public static Font getFont() {
		if (defaultFont == null)
			defaultFont = FontManager.getComponentFont(20);
		return defaultFont;
	}

	static Font defaultFont18;

	public static Font getFont18() {
		if (defaultFont18 == null)
			defaultFont18 = FontManager.getComponentFont(18);
		return defaultFont18;
	}

	static Font defaultFontBold18;

	public static Font getFontBold18() {
		if (defaultFontBold18 == null)
			defaultFontBold18 = FontManager.getComponentFont(Font.BOLD, 18);
		return defaultFontBold18;
	}

	static Font defaultFontBold16;

	public static Font getFontBold16() {
		if (defaultFontBold16 == null)
			defaultFontBold16 = FontManager.getComponentFont(Font.BOLD, 16);
		return defaultFontBold16;
	}

	static Font defaultFontBold14;

	public static Font getFontBold14() {
		if (defaultFontBold14 == null)
			defaultFontBold14 = FontManager.getComponentFont(Font.BOLD, 14);
		return defaultFontBold14;
	}

	static Font defaultFont20;

	public static Font getFont20() {
		if (defaultFont20 == null)
			defaultFont20 = FontManager.getComponentFont(20);
		return defaultFont20;
	}

	static Font defaultFont22;

	public static Font getFont22() {
		if (defaultFont22 == null)
			defaultFont22 = FontManager.getComponentFont(22);
		return defaultFont22;
	}

	static Font defaultFont12;

	public static Font getFont12() {
		if (defaultFont12 == null)
			defaultFont12 = FontManager.getComponentFont(12);
		return defaultFont12;
	}

	static Font defaultFont14;

	public static Font getFont14() {
		if (defaultFont14 == null)
			defaultFont14 = FontManager.getComponentFont(14);
		return defaultFont14;
	}

	static Font defaultFont16;

	public static Font getFont16() {
		if (defaultFont16 == null)
			defaultFont16 = FontManager.getComponentFont(16);
		return defaultFont16;
	}

	static Font defaultFontBold20;

	public static Font getFontBold20() {
		if (defaultFontBold20 == null)
			defaultFontBold20 = FontManager.getComponentFont(Font.BOLD, 20);
		return defaultFontBold20;
	}

	Bitmap noFaceBitmap;

	public Bitmap getNoPhotoFriendBitmap() {
		if (noFaceBitmap == null) {
			noFaceBitmap = Bitmap.getBitmapResource("noface.png");
		}
		return noFaceBitmap;
	}

	Bitmap noPlanBitmap;

	public Bitmap getNoPlanBitmap() {
		return getHourglass40Bitmap();
	}

	public Bitmap getHourglass22Bitmap() {
		if (hourglass22 != null)
			return hourglass22;

		hourglass22 = Bitmap.getBitmapResource("loading-22x22.png");
		byte data[] = PNGEncodedImage.encode(hourglass22).getData();
		EncodedImage e = EncodedImage.createEncodedImage(data, 0, data.length);

		e = DefaultController.scaleImageToWidth(e, 22);
		e = DefaultController.scaleImageToHeight(e, 22);

		hourglass22 = e.getBitmap();
		return hourglass22;
	}

	private Bitmap hourglass22;

	public Bitmap getHourglass25Bitmap() {
		if (hourglass25 != null)
			return hourglass25;

		hourglass25 = Bitmap.getBitmapResource("loading-25x25.png");
		byte data[] = PNGEncodedImage.encode(hourglass25).getData();
		EncodedImage e = EncodedImage.createEncodedImage(data, 0, data.length);

		e = DefaultController.scaleImageToWidth(e, 25);
		e = DefaultController.scaleImageToHeight(e, 25);

		hourglass25 = e.getBitmap();
		return hourglass25;
	}

	private Bitmap hourglass25;

	public Bitmap getHourglass40Bitmap() {
		if (hourglass40 != null)
			return hourglass40;

		hourglass40 = Bitmap.getBitmapResource("loading-42x42.png");
		byte data[] = PNGEncodedImage.encode(hourglass40).getData();
		EncodedImage e = EncodedImage.createEncodedImage(data, 0, data.length);

		e = DefaultController.scaleImageToWidth(e, 40);
		e = DefaultController.scaleImageToHeight(e, 40);

		hourglass40 = e.getBitmap();
		return hourglass40;
	}

	private Bitmap hourglass40;
	private Facebook fb;
	public boolean registered = false;

	public final static String SHOW_LOGIN = "showLogin";
	public final static String SET_LOGIN_RESULT = "setLoginResult";
	public final static String SET_USERNAME = "setUserName";
	public final static String SET_PASSWORD = "setPassword";

	public final static String HIDE_THROBBER = "hideThrobber";
	public final static String HIDE_LOGIN = "hideLogin";
	public final static String SET_SETTINGS = "setSettings";
	public final static String SET_SETTINGS_TABLE = "setSettingsTable";
	public final static String SET_SAVE_CREDENTIALS = "setSaveCredentials";

	public final static String HIDE_GLOBAL_STATUS_SCREEN = "hideGlobalSatusScreen";

	public final static String SET_SETTING_ONE = "setSettingOne";
	public final static String SET_ACCESS_TOKEN = "setAccessToken";

	public final static String SHOW_HOME_SCREEN = "showHomeScreen";

	public final static String HIDE_SETTINGS_VIEW = "hideSettingsView";

	public static final String SET_STATUS_MESSAGE = "setStatusMessage";
	// short search
	public final static String SHOW_SEARCH_RESULTS = "showSearchResults";// beginProductOpenSearch
	public final static String SET_SEARCH_TERM = "setSearchTerm";
	public final static String SET_SEARCH_RESULTS = "setSearchResults";
	public static final String ERROR_GET_SEARCH_RESULTS = "errorGetSearchResults";

	public final static String SHOW_PROFILE_VIEW = "showProfileView";
	public final static String SET_PROFILE = "setProfile";
	public final static String ERROR_GET_PROFILE = "errorGetProfile";

	public static final String SHOW_MENU = "showMainMenu";
	
	public static final String SET_ACTIVE_TOOLBAR_ITEM = "setactiveToolbarItem";
	public static final String SET_IS_STATUS_SHOWN = null;
	public static final String SHOW_SAMPLE_LOGIN = "showSampleLogin";
	public static final String SHOW_TEST = "showTest";
	public static final String SET_FACEBOOK_ID = "setFacebookId";
	public static final String SET_FACEBOOK_ACCESS_TOKEN = "setFacebookAccessToken";
	public static final String SHOW_REGISTER_VIEW = "showRegisterView";
	public static final String SHOW_MENU_VIEW = "showMenuView";

	public void userFacebookLogin() {
//		String NEXT_URL = "http://www.facebook.com/connect/login_success.html";
//		String APPLICATION_ID = "445031458872299";
//		String APPLICATION_SECRET = "500ab3186faf6330e8316287873dd7d2";
//		String[] PERMISSIONS = Facebook.Permissions.USER_DATA_PERMISSIONS;
//		Logger.log("facebook permisions: "+PERMISSIONS.length);
//		ApplicationSettings as = new ApplicationSettings(NEXT_URL, APPLICATION_ID, APPLICATION_SECRET, PERMISSIONS);
//		Logger.log("facebook as: "+as.getPermissionsString());
//		fb = Facebook.getInstance(as);
//		Logger.log("facebook fb: "+fb.getAccessToken());
		

		new F1rstFacebookClient(UiApplication.getUiApplication(), this, false);
		
		propertyChange(new PropertyChangeEvent(null,SHOW_TEST, null, null));
		
	}

	public Facebook getFacebook() {
		
		return fb;
	}
}
