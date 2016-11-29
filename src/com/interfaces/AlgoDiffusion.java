package com.interfaces;

import java.util.List;

/**
 * Created by Guillaume on 29/11/2016.
 */
public interface AlgoDiffusion {
    void configure(Subject s, List<Observer> list);

    void execute();
}
