package com.interfaces;

/**
 * Observateur asynchrone interface
 */
public interface ObserveurDeCapteurAsync {

    /**
     * Update the observer from a capteurAsync
     *
     * @param capteurAsync the capteur async
     */
    void update(CapteurAsync capteurAsync);
}
