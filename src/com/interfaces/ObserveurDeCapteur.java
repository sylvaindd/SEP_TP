package com.interfaces;

import java.util.concurrent.Future;

/**
 * Observateur de capteur interface
 */
public interface ObserveurDeCapteur extends Observer<Capteur> {
    /**
     * Update the observer
     * @param capteur the capteur that the observer is connected to
     * @return Future
     */
    @Override
    Future update(Capteur capteur);

    /**
     * get the id of the observer
     * @return Integer
     */
    @Override
    Integer getId();
}
