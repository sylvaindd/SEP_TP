package com.interfaces;

import java.util.concurrent.Future;

/**
 * Created by thoma on 28/11/2016.
 */
public interface ObserveurDeCapteurAsync {

	Future<Integer> update(CapteurAsync capteurAsync);

}
