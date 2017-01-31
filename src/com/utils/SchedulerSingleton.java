package com.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Singleton to have only one scheduler
 */
public enum SchedulerSingleton {
    /**
     * Instance scheduler singleton.
     */
    INSTANCE;

    private ScheduledExecutorService mScheduler;

    /**
     * Gets scheduler.
     *
     * @return the scheduler
     */
    public ScheduledExecutorService getScheduler() {
        return mScheduler;
    }

    /**
     * Init scheduler.
     */
    public void initScheduler() {
        this.mScheduler = new ScheduledThreadPoolExecutor(Utils.CORE_POOL_SIZE);
    }
}
