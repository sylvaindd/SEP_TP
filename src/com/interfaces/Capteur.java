package com.interfaces;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Sylvain on 21/11/2016.
 */
public interface Capteur extends Subject {
    void attach(Observer o);

    void detach(Observer o);

    Integer getValue();

    List<Observer> getListObserver();

    void tick();

    void inc();

    void setFuture(Future future);

    void removeIdFromList(Integer mCanalId);

    void initListIdFromCanals();

    boolean isListRemainingEmpty();
}
