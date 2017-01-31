package com.utils;

/**
 * Class which presents Constants and utils method
 */
public class Utils {
    /**
     * The constant DEBUG.
     */
    public static boolean DEBUG = false;
    /**
     * The constant NB_CAPTEUR.
     */
    public static final int NB_CAPTEUR = 4;
    /**
     * The constant CORE_POOL_SIZE.
     */
    public static final int CORE_POOL_SIZE = 20;

    /**
     * Show a debug log.
     *
     * @param cl    the class
     * @param value the value
     */
    public static void showDebug(Class cl, String value) {
        if (DEBUG) {
            System.out.println(cl.getName() + " " + value);
        }
    }
}
