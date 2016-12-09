package com.utils;

/**
 * Created by thoma on 09/12/2016.
 */
public class Constants {
    private static final boolean  DEBUG = false;
    public static void showDebug(String value){
        if(DEBUG){
            System.out.println(value);
        }
    }
}
