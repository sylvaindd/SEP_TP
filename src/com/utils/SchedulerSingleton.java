package com.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Singleton to have only one scheduler
 */
public enum SchedulerSingleton {
    INSTANCE;

    private ScheduledExecutorService mScheduler;

    public ScheduledExecutorService getScheduler() {
        return mScheduler;
    }

    public void initScheduler() {
        this.mScheduler = new ScheduledThreadPoolExecutor(Utils.CORE_POOL_SIZE);
    }
}
