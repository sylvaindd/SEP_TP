package com.callable;

import com.impls.Afficheur;
import com.interfaces.Capteur;

import java.util.concurrent.Callable;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Update implements Callable {


    private final Capteur mCapteur;
    private final Afficheur mAfficheur;

    public Update(Capteur s, Afficheur afficheur) {
        mCapteur = s;
        mAfficheur = afficheur;
    }

    @Override
    public Object call() throws Exception {
        mAfficheur.update(mCapteur);
        return null;
    }
}
