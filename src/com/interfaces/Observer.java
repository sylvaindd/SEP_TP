package com.interfaces;

import java.util.concurrent.Future;

/**
 * Created by thoma on 28/11/2016.
 */
public interface Observer<T> {
    Future update(T subject);

    Integer getId();
}
