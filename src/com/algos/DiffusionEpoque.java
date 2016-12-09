package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;

/**
 * Algo without restrictions, the Afficheur's value is updated when it get the value from the Capteur
 */
public class DiffusionEpoque implements AlgoDiffusion {

    private Capteur mCapteur;

    @Override
    public void configure(Capteur s) {
        mCapteur = s;
        mCapteur.initListRemainingId();
        mCapteur.setReading(false);
    }

    @Override
    public void execute() {
        mCapteur.inc();
        for (Observer observer : mCapteur.getListObserver()) {
            mCapteur.setFuture(observer.update(mCapteur));
        }
    }
}
