package com.utils;

/**
 * Created by thoma on 09/12/2016.
 */
public class Constants {
    public static boolean DEBUG = false;
    public static final int NB_CAPTEUR = 4;
    public static final int CORE_POOL_SIZE = 2;

    public static void showDebug(Class cl, String value) {
        if (DEBUG) {
            System.out.println(cl.getName() + " " + value);
        }
    }
}
