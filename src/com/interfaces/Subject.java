package com.interfaces;

/**
 * Observable interface
 */
public interface Subject {
    /**
     * Attach an observer.
     *
     * @param o the observer
     */
    void attach(Observer o);

    /**
     * Detach an observer.
     *
     * @param o the observer
     */
    void detach(Observer o);
}
