package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Observer;
import com.interfaces.Subject;

import java.util.List;

/**
 * Created by thoma on 28/11/2016.
 */
public class DiffusionSequentielle implements AlgoDiffusion {
    private List<Observer> mList;
    private Subject mSubject;

    @Override
    public void configure(Subject s, List<Observer> list) {
        mList = list;
        mSubject = s;
    }

    @Override
    public void execute() {

    }
}
