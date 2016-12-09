package com.callable;

import com.interfaces.Capteur;

import java.util.concurrent.Callable;

/**
 * Callable call by the scheduler to update remaining list and to get the value from the Capteur
 * This algo wait all the canals before incrementing the Capteur's value
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
