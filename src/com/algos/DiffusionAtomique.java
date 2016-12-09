package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;

/**
 * Created by Guillaume on 29/11/2016.
 */
public class DiffusionAtomique implements AlgoDiffusion {

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
            mCapteur.inc();
            mCapteur.loadFromCanals();
            for (Observer observer : mCapteur.getListObserver()) {
                observer.update(mCapteur);
            }
        }
    }
}
