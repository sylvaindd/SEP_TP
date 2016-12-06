package com.algos;

import com.interfaces.*;

import java.util.List;

/**
 * Created by thoma on 28/11/2016.
 */
public class DiffusionSequentielle implements AlgoDiffusion {
    private Capteur mCapteur;
    private int counter;
    @Override
    public void configure(Capteur s) {
        mCapteur = s;
    }

    @Override
    public void execute() {
            counter++;
        if (mCapteur.isListRemainingEmpty()) {
            mCapteur.setValue(counter);
            mCapteur.initListIdFromCanals();
            for (Observer observer : mCapteur.getListObserver()) {
                observer.update(mCapteur);
            }
        }

    }
}
