package com.impls;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Capteur;
import com.interfaces.Observer;
import com.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class CapteurImpl implements Capteur {

    private AlgoDiffusion mAlgo;
    private List<Observer> mObservers;
    private List<Integer> mObserverRemaining;
    private Integer v;
    private Integer copy_v;
    private boolean isReading;
    private Future mFuture;


    public CapteurImpl(AlgoDiffusion algoDiffusion) {
        mAlgo = algoDiffusion;
        mObservers = new ArrayList<>();
        this.v = 0;
        isReading = false;

        mAlgo.configure(this);
    }

    public void setAlgo(AlgoDiffusion algo) {
        this.mAlgo = algo;
        this.mAlgo.configure(this);
    }

    @Override
    public void attach(Observer o) {
        mObservers.add(o);
    }

    @Override
    public void detach(Observer o) {
        mObservers.remove(o);
        if (mObserverRemaining != null) {
            mObserverRemaining.remove(o.getId());
        }
    }

    @Override
    public Integer getValue() {
        Constants.showDebug(this.getClass()," v:"+v+" v_copy:"+copy_v +" algo :" +this.mAlgo.getClass().getName());
        return copy_v;
    }

    @Override
    public Integer getV() {
        return v;
    }

    @Override
    public List<Observer> getListObserver() {
        return mObservers;
    }

    @Override
    public void tick() {
        mAlgo.execute();
    }

    @Override
    public void inc() {
        v++;
        if (!isReading) {
            copy_v = v;
        }
    }

    @Override
    public void setFuture(Future future) {
        mFuture = future;
    }

    @Override
    public void removeIdFromList(Integer mCanalId) {
        Constants.showDebug(this.getClass(),"Remove from list :  " + mCanalId + " size : "+mObserverRemaining.size());
        mObserverRemaining.remove(mCanalId);
    }

    @Override
    public void initListIdFromCanals() {
        mObserverRemaining = new ArrayList<>();
        for (Observer observer : mObservers) {
            mObserverRemaining.add(observer.getId());
        }
    }

    @Override
    public boolean isListRemainingEmpty() {
        return mObserverRemaining == null || mObserverRemaining.isEmpty();
    }

    @Override
    public void setReading(boolean reading) {
        isReading = reading;
    }
}
