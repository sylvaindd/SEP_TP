package com.interfaces;

import java.util.concurrent.Future;

/**
 * Observateur de capteur interface
 */
public interface ObserveurDeCapteur extends Observer<Capteur> {

    @Override
    Future update(Capteur capteur);

    @Override
    Integer getId();
}
