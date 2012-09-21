package com.f1rst.blackberry.view;
import java.util.Enumeration;

import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;

import com.f1rst.blackberry.util.AbstractViewPanel;
import com.f1rst.blackberry.util.DefaultController;
import com.f1rst.blackberry.util.Labels;
import com.f1rst.blackberry.util.Model;
import com.f1rst.blackberry.util.PropertyChangeEvent;
import com.f1rst.blackberry.util.Settings;

import net.rim.device.api.gps.BlackBerryCriteria;
import net.rim.device.api.gps.BlackBerryLocation;
import net.rim.device.api.gps.BlackBerryLocationProvider;
import net.rim.device.api.gps.GPSInfo;
import net.rim.device.api.gps.SatelliteInfo;
import net.rim.device.api.lbs.maps.MapFactory;
import net.rim.device.api.lbs.maps.model.MapDataModel;
import net.rim.device.api.lbs.maps.model.MapLocation;
import net.rim.device.api.lbs.maps.model.MapPoint;
import net.rim.device.api.lbs.maps.model.Mappable;
import net.rim.device.api.lbs.maps.ui.MapAction;
import net.rim.device.api.lbs.maps.ui.RichMapField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.EmailAddressEditField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * sign in screen
 *
 * @author ivaylo
 */
public class MenuScreen extends BasicMainScreen implements FieldChangeListener, AbstractViewPanel {


    private static com.f1rst.blackberry.view.MenuScreen instance;

    private DefaultController controller;      
    
    //test for gps
    private GPSThread gpsThread;
	private static double lat;
	private static double longt;
	static RichMapField map;// = MapFactory.getInstance().generateRichMapField();
	static MapDataModel data;
	//test for gps

    public MenuScreen() {
    }

    public MenuScreen(DefaultController controller) {
    	super(controller);

        instance = this;
        
        init();
        updateTitle(Labels.LBL_MAIN_TITLE);
    }

    private void init() {
        createFields();  

 
    }

    public boolean onClose() {
    	controller.exit();
        return true;
    }

    MenuItem exitMenuItem = new MenuItem(Labels.LBL_EXIT, 10, 1001) {
//            @Override

        public void run() {
            controller.exit();
        }
    };
 
    public void invalidate() {
        super.invalidate();
    }

    protected void onUiEngineAttached(boolean attached) {
        if (attached) {
            hideThrobber();
            final Runnable r = new Runnable() {

                public void run() {
                    //show warning
                }
            };
            new Thread(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                    controller.invokeLater(r);
                }
            }).start();
        } else {
        }
    }

    private void createFields() {
    	
    	try {
    		map = MapFactory.getInstance().generateRichMapField();
    		data = map.getModel();
    		add(map);
    		handleGPS();
    		
    		
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    }
    
    public static MenuScreen getMenuScreen(DefaultController controller) {
        //if(instance == null){
           instance = new MenuScreen(controller);           
        //}
           
        return instance;
    }

	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	
	///for testing gps
	private static double getLat() {
		// TODO Auto-generated method stub
		return lat;
	}


	private static double getLongt() {
		// TODO Auto-generated method stub
		return longt;
	}


	public void handleGPS()
    {
        gpsThread = new GPSThread();
        gpsThread.start();
    }
	private static void setLongt(double longitude) {
		longt = longitude;
		
	}

	private static void setLat(double latitude) {
		lat = latitude;
		
	}

    private static class GPSThread extends Thread {
        public void run() {
            try {
                BlackBerryCriteria criteria = new BlackBerryCriteria(GPSInfo.GPS_MODE_AUTONOMOUS);

                try
                {
                    BlackBerryLocationProvider myProvider =
                      (BlackBerryLocationProvider)
                        LocationProvider.getInstance(criteria);

                    try
                    {
                        BlackBerryLocation myLocation = (BlackBerryLocation)myProvider.getLocation(300);

                        int satCount = myLocation.getSatelliteCount();
                        
                        setLat(myLocation.getQualifiedCoordinates().getLatitude());
                        setLongt(myLocation.getQualifiedCoordinates().getLongitude());
                        
                        //data.setVisibleNone();
                		MapLocation test = new MapLocation(myLocation.getQualifiedCoordinates().getLatitude(), myLocation.getQualifiedCoordinates().getLongitude(), "test", null);
                		int testId = data.add((Mappable) test, "test");
                		data.tag(testId, "test");
                		data.setVisibleNone();
                		data.setVisible( "test");
//                		MapAction action = map.getAction();
//                		action.setCentreAndZoom(new MapPoint(43.47462, -80.53820), 2);
                		map.getMapField().update(true);
                        
                        
                        
                        
                        int signalQuality = myLocation.getAverageSatelliteSignalQuality();
                        int dataSource = myLocation.getDataSource();
                        int gpsMode = myLocation.getGPSMode();

                        SatelliteInfo si;
                        StringBuffer sb = new StringBuffer("[Id:SQ:E:A]\n");
                        String separator = ":";

                        for (Enumeration e = myLocation.getSatelliteInfo();
                          e!=null && e.hasMoreElements(); )
                        {
                            si = (SatelliteInfo)e.nextElement();
                            sb.append(si.getId() + separator);
                            sb.append(si.getSignalQuality() + separator);
                            sb.append(si.getElevation() + separator);
                            sb.append(si.getAzimuth());
                            sb.append('\n');
                            System.out.println(sb);
                        }
                    }
                    catch ( InterruptedException iex )
                    {
                        return;
                    }
                    catch ( LocationException lex )
                    {
                        return;
                    }
                }
                catch ( LocationException lex )
                {
                    return;
                }
            }
            catch ( UnsupportedOperationException uoex )
            {
                return;
            }

            return;
        }
    }
    
    //end for testing gps
}
