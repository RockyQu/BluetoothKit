package com.tool.bluetooth.detector.utils.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class TimerManager implements ITimer {

    private static final String TAG = TimerManager.class.getSimpleName();

    // 初次启动延迟时间 默认 60 秒
    private static final long DELAY = 60 * 1000;
    // 循环间隔时间 默认 60 秒
    private static final long PERIOD = 60 * 1000;
    // 循环次数 默认 10 次
    private static final int FREQUENCY = 10;

    private long currentPeriod = TimerManager.PERIOD;
    private int currentFrequency = TimerManager.FREQUENCY;
    private int frequency;

    private long total;

    private Timer timer = null;
    private TimerListener listener = null;

    private final static class HolderClass {
        private final static ITimer INSTANCE = new TimerManager();
    }

    public static ITimer getInstance() {
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
    public ITimer schedule(long delay, long period) {
        return schedule(delay, period, TimerManager.FREQUENCY);
    }

    @Override
    public ITimer scheduleAtFrequency(int frequency) {
        return schedule(TimerManager.DELAY, TimerManager.PERIOD, frequency);
    }

    @Override
    public ITimer schedule(long delay, long period, int frequency) {
        this.currentPeriod = period;
        this.currentFrequency = frequency;
        this.frequency = frequency;

        total = period * frequency;

        if (timer == null) {
            timer = new Timer();
        } else {
            timer.purge();
        }

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (currentFrequency <= 0) {
                    reset();
                    return;
                }

                currentFrequency--;

                if (listener != null) {
                    listener.progress(total, currentPeriod * currentFrequency);
                }
            }
        }, delay, period);

        return this;
    }

    @Override
    public void reset() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (listener != null) {
            listener.complete(frequency);
        }
    }

    @Override
    public TimerManager setListener(TimerListener listener) {
        this.listener = listener;
        return this;
    }

    public interface TimerListener {

        void progress(long total, long progress);

        void complete(int frequency);
    }
}