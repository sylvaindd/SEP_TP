package com.interfaces;

/**
 * Observable interface
 */
public interface Subject {
    void attach(Observer o);

    void detach(Observer o);
}
