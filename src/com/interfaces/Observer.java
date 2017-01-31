package com.interfaces;

import java.util.concurrent.Future;

/**
 * Observateur interface
 *
 * @param <T> the type parameter
 */
public interface Observer<T> {
    /**
     * Update future.
     *
     * @param subject the subject
     * @return the future
     */
    Future update(T subject);

    /**
     * Gets id.
     *
     * @return the id
     */
    Integer getId();
}
