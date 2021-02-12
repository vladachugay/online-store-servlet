package com.vlados.controller.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    private static final String BUNDLE_NAME = "lang";
    private static final ResourceBundle resourceBundleEn = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
    private static final ResourceBundle resourceBundleUa = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("ua"));

    public static String getMessage(String key, Locale locale) {
        try {
            return locale.equals(Locale.ENGLISH) ? resourceBundleEn.getString(key) :
                    resourceBundleUa.getString(key);
        } catch (NullPointerException ex) {
            System.out.println(key);
            System.out.println(locale);
        }
        return key;
    }
}
