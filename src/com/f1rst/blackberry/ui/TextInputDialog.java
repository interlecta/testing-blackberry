/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.f1rst.blackberry.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.AutoTextEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.DialogFieldManager;

/**
 *
 * @author ivaylo
 */
public final class TextInputDialog extends Dialog {

    private AutoTextEditField userNameField;
    private ButtonField okButton;
    private ButtonField cancelButton;

//    public TextInputDialog() {
//        super(null, null, null, D_OK_CANCEL, null);
//    }
    public TextInputDialog(String choices[], int values[]) {
        super("Enter a phone number", choices, values, Dialog.OK, Bitmap.getPredefinedBitmap(Bitmap.QUESTION), Dialog.GLOBAL_STATUS);
        userNameField = new AutoTextEditField("Phone number: ", "", 20, EditField.EDITABLE | EditField.FILTER_PHONE);
        net.rim.device.api.ui.Manager delegate = getDelegate();
        if (delegate instanceof DialogFieldManager) {
            DialogFieldManager dfm = (DialogFieldManager) delegate;
            net.rim.device.api.ui.Manager manager = dfm.getCustomManager();
            if (manager != null) {
                manager.insert(userNameField, 0);
            }
        }
    }

    public TextInputDialog(final String title, final String fieldLabel, final String optFieldValue, final long constrains, String choices[], int values[]) {
        super(title, choices, values, Dialog.OK, Bitmap.getPredefinedBitmap(Bitmap.QUESTION), Dialog.GLOBAL_STATUS);
        userNameField = new AutoTextEditField(fieldLabel, optFieldValue, 300, constrains);
        net.rim.device.api.ui.Manager delegate = getDelegate();
        if (delegate instanceof DialogFieldManager) {
            DialogFieldManager dfm = (DialogFieldManager) delegate;
            net.rim.device.api.ui.Manager manager = dfm.getCustomManager();
            if (manager != null) {
                manager.insert(userNameField, 0);
            }
        }
    }

    public TextInputDialog(String title, String subtitle, String value, String choices[], int values[], boolean dummy) {
        super(title, choices, values, Dialog.OK, Bitmap.getPredefinedBitmap(Bitmap.QUESTION), Dialog.GLOBAL_STATUS);
        userNameField = new AutoTextEditField( subtitle + ": ", value, 20, EditField.EDITABLE | EditField.FILTER_NUMERIC);
        net.rim.device.api.ui.Manager delegate = getDelegate();
        if (delegate instanceof DialogFieldManager) {
            DialogFieldManager dfm = (DialogFieldManager) delegate;
            net.rim.device.api.ui.Manager manager = dfm.getCustomManager();
            if (manager != null) {
                manager.insert(userNameField, 0);
            }
        }
    }
    
    public String getUsernameFromField() {
        return userNameField.getText();
    }
}
