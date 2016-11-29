package com.impls;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class CapteurImpl implements Capteur {

    private final AlgoDiffusion mAlgo;
    private List<Observer> mObservers;
    private Integer v;
    private Future mFuture;

    public CapteurImpl(AlgoDiffusion algoDiffusion) {
        mAlgo = algoDiffusion;
        mObservers = new ArrayList<Observer>();
        this.v = 0;

        mAlgo.configure(this, mObservers);
    }

    @Override
    public void attach(Observer o) {
        mObservers.add(o);
    }

    @Override
    public void detach(Observer o) {
        mObservers.remove(o);
    }

    @Override
    public Integer getValue() {
        return v;
    }

    @Override
    public void tick() {
        v++;
        mAlgo.execute();
    }

    @Override
    public void setFuture(Future future) {
        mFuture = future;
    }
}
