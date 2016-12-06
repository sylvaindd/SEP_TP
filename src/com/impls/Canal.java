package com.impls;

import com.callable.GetValue;
import com.callable.Update;
import com.interfaces.Capteur;
import com.interfaces.CapteurAsync;
import com.interfaces.ObserveurDeCapteur;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Canal implements ObserveurDeCapteur, CapteurAsync {

    private final Afficheur mAfficheur;
    private final Integer mId;
    private Capteur mCapteur;
    private ScheduledExecutorService mScheduler;

    public Canal(Integer id, Capteur s) {
        mId = id;
        mCapteur = s;
        mScheduler = new ScheduledThreadPoolExecutor(20);
        mAfficheur = new Afficheur(id, this);
    }

    public void detach() {
        mCapteur.detach(this);
    }

    @Override
    public Future getValue() {
        GetValue value = new GetValue(mCapteur, mId);
        return mScheduler.schedule(value, 810, TimeUnit.MILLISECONDS);
    }

    @Override
    public Future update(Capteur s) {
        mCapteur = s;
        Update update = new Update(this, mAfficheur);
        return mScheduler.schedule(update, 720, TimeUnit.MILLISECONDS);
    }

    @Override
    public Integer getId() {
        return mId;
    }
}