package com.callable;

import com.impls.Afficheur;
import com.interfaces.CapteurAsync;

import java.util.concurrent.Callable;

/**
 * Callable call by the scheduler to update the Afficheur
 */
public class Update implements Callable {

    private final CapteurAsync mCapteurAsync;
    private final Afficheur mAfficheur;

    public Update(CapteurAsync capteurAsync, Afficheur afficheur) {
        mCapteurAsync = capteurAsync;
        mAfficheur = afficheur;
    }

    @Override
    public Object call() throws Exception {
        mAfficheur.update(mCapteurAsync);
        return null;
    }
}
