package com.utils;

/**
 * Class which presents Constants and utils method
 */
public class Utils {
    public static boolean DEBUG = false;
    public static final int NB_CAPTEUR = 4;
    public static final int CORE_POOL_SIZE = 20;

    public static void showDebug(Class cl, String value) {
        if (DEBUG) {
            System.out.println(cl.getName() + " " + value);
        }
    }
}
