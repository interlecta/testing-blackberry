package com.f1rst.blackberry;

import java.util.Enumeration;
import java.util.Hashtable;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.util.Persistable;

public class ImageCache implements Persistable {

    static ImageCache single;

    /**
     * store images that will be persisted
     */
    private final Hashtable data;

    /**
     * store images retrieved in one session
     */
    private final Hashtable session;     //key -> url,   value -> encodedimage
    private final Hashtable sessionKeys; //key -> index, value -> url
    private int index = 0;
    private final static int MAX_SESSION_IMAGES = 20;


    private ImageCache() {
        data = new Hashtable();

        session = new Hashtable();
        sessionKeys = new Hashtable();
    }

    public static ImageCache getInstance() {
        if (single == null) {
            single = new ImageCache();
        }

        return single;
    }

    public void put(String url, EncodedImage b) {
        if (b ==null) return;

        synchronized (data) {
            RawEncodedImage r = new RawEncodedImage();
            r.widht = b.getWidth();
            r.heigh = b.getHeight();
            r.data = b.getData();
            data.put(url, r);

//            for (int i = 0; i < 400; i++) {
//                RawEncodedImage r2 = new RawEncodedImage();
//                r2.widht = b.getWidth();
//                r2.heigh = b.getHeight();
//                r2.data = b.getData();
//                data.put(url+String.valueOf(i), r2);
//            }
        }
    }

    public void put(String url, Bitmap b) {
        if(b==null) return;

        synchronized (data) {
            RawBitmap r = new RawBitmap();
            r.type = b.getType();
            r.widht = b.getWidth();
            r.heigh = b.getHeight();
            r.data = new int[r.widht * r.heigh];
            b.getARGB(r.data, 0, r.widht, 0, 0, r.widht, r.heigh);
//            data.put(url, b);
            data.put(url, r);

//            for (int i = 0; i < 200; i++) {
//                RawBitmap r2 = new RawBitmap();
//                r2.type = b.getType();
//                r2.widht = b.getWidth();
//                r2.heigh = b.getHeight();
//                r2.data = new int[r2.widht * r2.heigh];
//                b.getARGB(r2.data, 0, r2.widht, 0, 0, r2.widht, r2.heigh);
//                data.put(url+String.valueOf(i), r2);
//            }
        }
    }

    public boolean inCache(String url) {
        synchronized(data){
            return data.contains(url);
        }
    }

    public Bitmap get(String url) {
        synchronized (data){
            Object o = data.get(url);
            if(o != null && o instanceof RawBitmap) {
                RawBitmap r = (RawBitmap)o;
                Bitmap b = new Bitmap(r.widht, r.heigh);
                b.setARGB(r.data, 0, r.widht, 0, 0, r.widht, r.heigh);
                return b;
            }
        }
//        synchronized (data){
//            Object o = data.get(url);
//            if(o != null && o instanceof Bitmap) {
//                return (Bitmap) o;
//            }
//        }

        return null;
    }

    public EncodedImage getEncodedImage(String url) {
        synchronized (data){
            Object o = data.get(url);
            if(o != null && o instanceof RawEncodedImage) {
                RawEncodedImage r = (RawEncodedImage)o;
                return EncodedImage.createEncodedImage(r.data, 0, r.data.length);
            }
        }

        return null;
    }

    public void setData(Hashtable data) {
        //this.data = data;
        if(data!=null && this.data!=null) {
            Enumeration e = data.elements();
            Enumeration k = data.keys();

            this.data.clear();
            Hashtable dataLocal = this.data;
            while(e.hasMoreElements() && k.hasMoreElements()) {
                dataLocal.put(k.nextElement(), e.nextElement());
            }
        }
    }

    public Hashtable getData() {
        return data;
    }

    class RawBitmap implements Persistable {
        int type;
        int widht;
        int heigh;
        int [] data;
    }

    class RawEncodedImage implements Persistable {
        int widht;
        int heigh;
        byte [] data;
    }

    public void clear() {
        synchronized(data) {
            if(data!=null) {
                data.clear();
                //data = null;
            }
        }
    }


    public void putSession(String url, EncodedImage b) {
        if(b==null) return;
        synchronized (session) {
            RawEncodedImage r = new RawEncodedImage();
            r.widht = b.getWidth();
            r.heigh = b.getHeight();
            r.data = b.getData();
            session.put(url, r);
            sessionKeys.put(String.valueOf((index++)), url);
            if(index>MAX_SESSION_IMAGES) {
                //remove starting indexes
                try {
                    String urlToDelete = (String)sessionKeys.get(String.valueOf((index - MAX_SESSION_IMAGES)));
                    sessionKeys.remove(String.valueOf((index - MAX_SESSION_IMAGES)));
                    session.remove(urlToDelete);
                } catch (NullPointerException n){}
            }
        }
    }

     public EncodedImage getSessionEncodedImage(String url) {
        synchronized (session) {
            Object o = session.get(url);
            if(o != null && o instanceof RawEncodedImage) {
                RawEncodedImage r = (RawEncodedImage)o;
                return EncodedImage.createEncodedImage(r.data, 0, r.data.length);
            }
        }

        return null;
    }

    public boolean inCacheSession(String url) {
        synchronized(session){
            return session.contains(url);
        }
    }

}
