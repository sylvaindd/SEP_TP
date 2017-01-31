package com.interfaces;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Observable, with method for a Capteur
 */
public interface Capteur extends Subject {
    void attach(Observer o);

    void detach(Observer o);

    /**
     * Gets value.
     *
     * @return the value
     */
    Integer getValue();

    /**
     * Gets v.
     *
     * @return the v
     */
    Integer getV();

    /**
     * Gets list observer.
     *
     * @return the list observer
     */
    List<Observer> getListObserver();

    /**
     * Tick.
     */
    void tick();

    /**
     * Inc.
     */
    void inc();

    /**
     * Sets future.
     *
     * @param future the future
     */
    void setFuture(Future future);

    /**
     * Remove id from list.
     *
     * @param mCanalId the m canal id
     */
    void removeIdFromList(Integer mCanalId);

    /**
     * Init list remaining id.
     */
    void initListRemainingId();

    /**
     * Load from canals.
     */
    void loadFromCanals();

    /**
     * Is list remaining empty boolean.
     *
     * @return the boolean
     */
    boolean isListRemainingEmpty();

    /**
     * Sets reading.
     *
     * @param reading boolean readings
     */
    void setReading(boolean reading);
}
