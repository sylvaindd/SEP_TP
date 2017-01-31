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
 * Implementation of Observateur de capteur and Capteur asynchrone
 * With all method to manage Canal
 */
public class Canal implements ObserveurDeCapteur, CapteurAsync {

    private final Afficheur mAfficheur;
    private final Integer mId;
    private Capteur mCapteur;

    /**
     * Instantiates a new Canal.
     *
     * @param id the id
     * @param c  the capteur
     */
    public Canal(Integer id, Capteur c) {
        mId = id;
        mCapteur = c;
        mAfficheur = new Afficheur(id, this);
    }

    /**
     * Detach a canal
     */
    public void detach() {
        mCapteur.detach(this);
    }

    /**
     * Get the actual value
     * @return Future
     */
    @Override
    public Future getValue() {
        GetValue value = new GetValue(mCapteur, mId);
        int random = ThreadLocalRandom.current().nextInt(500, 4000 + 1);
        return SchedulerSingleton.INSTANCE.getScheduler().schedule(value, random, TimeUnit.MILLISECONDS);
    }

    /**
     * Update the observer from a capteur
     *
     * @param c Capteur
     * @return Future
     */
    @Override
    public Future update(Capteur c) {
        mCapteur = c;
        Update update = new Update(this, mAfficheur);
        int random = ThreadLocalRandom.current().nextInt(500, 4000 + 1);
        return SchedulerSingleton.INSTANCE.getScheduler().schedule(update, random, TimeUnit.MILLISECONDS);
    }

    @Override
    public Integer getId() {
        return mId;
    }
}