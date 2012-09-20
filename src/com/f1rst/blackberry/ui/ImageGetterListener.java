package com.f1rst.blackberry.ui;

import net.rim.device.api.system.Bitmap;

/**
 *
 * @author ivaylo
 */
public interface ImageGetterListener {
    public void setBitmap(Bitmap b);

    public void error(String s);

}
