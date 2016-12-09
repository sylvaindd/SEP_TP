package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;

/**
 * Created by Guillaume on 09/12/2016.
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
            observer.update(mCapteur);
        }
    }
}
