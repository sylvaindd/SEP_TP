package com.callable;

import com.impls.Afficheur;
import com.interfaces.CapteurAsync;

import java.util.concurrent.Callable;

/**
 * Created by Sylvain on 28/11/2016.
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
