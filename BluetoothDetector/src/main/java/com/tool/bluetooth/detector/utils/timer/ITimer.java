package com.tool.bluetooth.detector.utils.timer;

public interface ITimer {

    /**
     * 开始执行定时任务
     */
    ITimer schedule();

    /**
     * 开始执行定时任务
     *
     * @param period 执行间隔时间
     */
    ITimer schedule(long period);

    /**
     * 开始执行定时任务
     *
     * @param delay 初次执行间隔时间
     * @param period 执行间隔时间
     */
    ITimer schedule(long delay, long period);

    /**
     * 重置定时任务
     */
    void reset();

    ITimer setListener(TimerManager.TimerListener listener);
}