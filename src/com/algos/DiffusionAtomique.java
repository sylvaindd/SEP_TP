package com.algos;

import com.interfaces.AlgoDiffusion;
import com.interfaces.Observer;
import com.interfaces.Subject;

import java.util.List;

/**
 * Created by Guillaume on 29/11/2016.
 */
public class DiffusionAtomique implements AlgoDiffusion {

    private List<Observer> mList;
    private Subject mSubject;

    @Override
    public void configure(Subject s, List<Observer> list) {
        mList = list;
        mSubject = s;
    }

    @Override
    public void execute() {
        for (Observer observer : mList) {
            observer.update(mSubject);
        }
    }
}
