package com.impls;

import com.callable.GetValue;
import com.callable.Update;
import com.interfaces.Capteur;
import com.interfaces.ObserveurDeCapteur;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Canal implements ObserveurDeCapteur {

    private final Afficheur mAfficheur;
    private Capteur mCapteur;
    ScheduledExecutorService mScheduler;

    public Canal() {
        mScheduler = new ScheduledThreadPoolExecutor(1);
        mAfficheur = new Afficheur(this);
    }

    public Future getValue() {
        GetValue value = new GetValue(mCapteur);
        return mScheduler.schedule(value, 810, TimeUnit.MILLISECONDS);
    }

    @Override
    public void update(Capteur s) {
        Update update = new Update(s, mAfficheur);
        Future future = mScheduler.schedule(update, 720, TimeUnit.MILLISECONDS);
        s.setFuture(future);
        mCapteur = s;
    }
}