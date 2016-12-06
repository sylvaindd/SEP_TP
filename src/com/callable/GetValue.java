package com.callable;

import com.interfaces.Capteur;

import java.util.concurrent.Callable;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class GetValue implements Callable {

    private final Capteur mCapteur;
    private final Integer mCanalId;

    public GetValue(Capteur s, Integer canalId) {
        mCapteur = s;
        mCanalId = canalId;
    }

    @Override
    public Object call() throws Exception {
        mCapteur.removeIdFromList(mCanalId);
        return mCapteur.getValue();
    }
}
