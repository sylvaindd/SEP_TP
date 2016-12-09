package com.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by Guillaume on 09/12/2016.
 */
public enum SchedulerSingleton {
    INSTANCE;

    private ScheduledExecutorService mScheduler;

    public ScheduledExecutorService getScheduler() {
        return mScheduler;
    }

    public void initScheduler() {
        this.mScheduler = new ScheduledThreadPoolExecutor(Constants.CORE_POOL_SIZE);
    }
}
