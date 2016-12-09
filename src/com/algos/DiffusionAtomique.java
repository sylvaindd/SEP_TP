package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;
import com.utils.Constants;

/**
 * Created by Guillaume on 29/11/2016.
 */
public class DiffusionAtomique implements AlgoDiffusion {

    private Capteur mCapteur;

    @Override
    public void configure(Capteur s) {
        mCapteur = s;
        mCapteur.setReading(false);
    }

    @Override
    public void execute() {
        if (mCapteur.isListRemainingEmpty()) {
            mCapteur.inc();
            mCapteur.initListIdFromCanals();
            for (Observer observer : mCapteur.getListObserver()) {
                observer.update(mCapteur);
            }
            Constants.showDebug(this.getClass(), "algo fini");
        }
    }
}
