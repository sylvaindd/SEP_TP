package com.impls;

import com.interfaces.Capteur;
import com.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Sylvain on 28/11/2016.
 */
public class CapteurImpl implements Capteur {

    List<Observer> observers;
    List<Canal> canals;
    Integer v;
    Future mFuture;

    public CapteurImpl() {
        this.observers = new ArrayList<Observer>();
        this.v = 0;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public Integer getValue() {
        return v;
    }

    @Override
    public void tick() {
        v++;
    }

    @Override
    public void setFuture(Future future) {
        mFuture = future;
    }
}
