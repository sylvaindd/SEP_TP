package com.interfaces;

import java.util.concurrent.Future;

/**
 * Capteur asynchrone
 */
public interface CapteurAsync {

    /**
     * Gets value.
     *
     * @return the value
     */
    Future getValue();

}
