package com.utils;

/**
 * Created by thoma on 09/12/2016.
 */
public class Constants {
    public static final boolean DEBUG = false;
    public static final int NB_CAPTEUR = 8;
    public static final int CORE_POOL_SIZE = 20;
    public static void showDebug(String value){
        if(DEBUG){
            System.out.println(value);
        }
    }
}
