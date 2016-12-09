package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;

/**
 * Algo which increment all the time the capteur's value but wait all the canal before increasing the Afficheur's value
 */
public class DiffusionSequentielle implements AlgoDiffusion {
    private Capteur mCapteur;

    @Override
    public void configure(Capteur s) {
        mCapteur = s;
        mCapteur.initListRemainingId();
        mCapteur.setReading(false);
    }

    @Override
    public void execute() {
        if (mCapteur.isListRemainingEmpty()) {
            mCapteur.setReading(false);
            mCapteur.inc();
            mCapteur.loadFromCanals();
            for (Observer observer : mCapteur.getListObserver()) {
                mCapteur.setFuture(observer.update(mCapteur));
            }
        } else {
            mCapteur.setReading(true);
            mCapteur.inc();
        }
    }
}
