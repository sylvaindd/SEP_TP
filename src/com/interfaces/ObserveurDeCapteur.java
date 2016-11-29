package com.interfaces;

import java.util.concurrent.Future;

/**
 * Created by thoma on 28/11/2016.
 */
public interface ObserveurDeCapteur extends Observer<Capteur> {

    @Override
    Future update(Capteur capteur);

}
