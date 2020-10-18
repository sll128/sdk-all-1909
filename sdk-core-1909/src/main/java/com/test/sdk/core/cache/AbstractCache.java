package com.test.sdk.core.cache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 徒有琴
 */
public abstract class AbstractCache extends Thread {
    private boolean isRunning = false;
    private long interval = 20 * 60 * 1000;

    @Override
    public void run() {
        while (isRunning) {
            update();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public abstract void update();

    @PostConstruct//利用Spring生命周期
    public void init() {
        this.isRunning = true;
        //this.start();
        update();//如果线程起不来
    }

    @PreDestroy
    public void destroy() {
        this.isRunning = false;
    }
}
