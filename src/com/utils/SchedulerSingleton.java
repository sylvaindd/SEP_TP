package com.utils;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Guillaume on 09/12/2016.
 */
public enum SchedulerSingleton {
    INSTANCE;

    private ScheduledExecutorService mScheduler;

    public ScheduledExecutorService getScheduler() {
        return mScheduler;
    }

    public void setScheduler(ScheduledExecutorService mScheduler) {
        this.mScheduler = mScheduler;
    }
}
