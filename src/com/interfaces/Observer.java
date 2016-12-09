package com.interfaces;

import java.util.concurrent.Future;

/**
 * Observateur interface
 */
public interface Observer<T> {
    Future update(T subject);

    Integer getId();
}
