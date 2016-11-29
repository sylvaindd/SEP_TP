package com.callable;

import com.interfaces.Capteur;

import java.util.concurrent.Callable;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class GetValue implements Callable {

    private final Capteur mCapteur;

    public GetValue(Capteur s) {
        mCapteur = s;
    }

    @Override
    public Object call() throws Exception {
        return mCapteur.getValue();
    }
}
