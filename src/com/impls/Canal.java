package com.impls;

import com.callable.GetValue;
import com.callable.Update;
import com.interfaces.Capteur;
import com.interfaces.CapteurAsync;
import com.interfaces.ObserveurDeCapteur;
import com.utils.SchedulerSingleton;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class Canal implements ObserveurDeCapteur, CapteurAsync {

    private final Afficheur mAfficheur;
    private final Integer mId;
    private Capteur mCapteur;

    public Canal(Integer id, Capteur s) {
        mId = id;
        mCapteur = s;
        mAfficheur = new Afficheur(id, this);
    }

    public void detach() {
        mCapteur.detach(this);
    }

    @Override
    public Future getValue() {
        GetValue value = new GetValue(mCapteur, mId);
        int random = ThreadLocalRandom.current().nextInt(500, 3000 + 1);
        return SchedulerSingleton.INSTANCE.getScheduler().schedule(value, random, TimeUnit.MILLISECONDS);
    }

    @Override
    public Future update(Capteur s) {
        mCapteur = s;
        Update update = new Update(this, mAfficheur);
        int random = ThreadLocalRandom.current().nextInt(500, 3000 + 1);
        return SchedulerSingleton.INSTANCE.getScheduler().schedule(update, random, TimeUnit.MILLISECONDS);
    }

    @Override
    public Integer getId() {
        return mId;
    }
}