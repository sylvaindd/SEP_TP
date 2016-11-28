package com;

import com.impls.Afficheur;
import com.interfaces.Capteur;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Canal {

    private final Afficheur mAfficheur;
    private Capteur mCapteur;
    ScheduledExecutorService mScheduler;

    public Canal() {
        mScheduler = new ScheduledThreadPoolExecutor(1);
        mAfficheur = new Afficheur();
    }

    public void update(Capteur s) {
        Update update = new Update(s, mAfficheur);
        ScheduledFuture future = mScheduler.schedule(update, 720, TimeUnit.MILLISECONDS);
        s.setFuture(future);
        mCapteur = s;
    }

    public Integer getValue() {
        return mCapteur.getValue();
    }
}