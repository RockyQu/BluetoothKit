package com.tool.bluetooth.detector.utils.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class TimerManager extends TimerTask implements ITimer {

    private static final long DELAY = 1;
    private static final long PERIOD = 30 * 1000;

    private Timer timer = null;
    private TimerListener listener = null;

    private final static class HolderClass {
        private final static ITimer INSTANCE = new TimerManager();
    }

    public static ITimer get() {
        return TimerManager.HolderClass.INSTANCE;
    }

    private TimerManager() {

    }

    @Override
    public ITimer schedule() {
        return schedule(TimerManager.PERIOD);
    }

    @Override
    public ITimer schedule(long period) {
        return schedule(TimerManager.DELAY, period);
    }

    @Override
    public TimerManager schedule(long delay, long period) {
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.purge();
        }

        timer.schedule(this, delay, period);

        return this;
    }

    @Override
    public void reset() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public TimerManager setListener(TimerListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void run() {
        if (listener != null) {
            listener.run();
        }
    }

    public interface TimerListener {
        void run();
    }
}