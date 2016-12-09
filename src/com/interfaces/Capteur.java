package com.interfaces;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Observable, with method for a Capteur
 */
public interface Capteur extends Subject {
    void attach(Observer o);

    void detach(Observer o);

    Integer getValue();

    Integer getV();

    List<Observer> getListObserver();

    void tick();

    void inc();

    void setFuture(Future future);

    void removeIdFromList(Integer mCanalId);

    void initListRemainingId();

    void loadFromCanals();

    boolean isListRemainingEmpty();

    void setReading(boolean reading);
}
