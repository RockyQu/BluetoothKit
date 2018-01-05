package com.tool.bluetooth.detector.utils.timer;

public interface ITimer {

    /**
     * 开始执行定时任务
     * <p>
     * 默认扫描总时间 = {@link TimerManager#PERIOD} * {@link TimerManager#FREQUENCY} = 10 分钟
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
     * @param delay  初次执行间隔时间
     * @param period 执行间隔时间
     */
    ITimer schedule(long delay, long period);

    /**
     * 开始执行定时任务
     *
     * @param frequency 次数
     */
    ITimer scheduleAtFrequency(int frequency);

    /**
     * 开始执行定时任务
     * 循环总时间 = 间隔时间 * 次数
     *
     * @param delay     初次执行间隔时间
     * @param period    执行间隔时间
     * @param frequency 次数
     */
    ITimer schedule(long delay, long period, int frequency);

    /**
     * 重置定时任务
     */
    void reset();

    ITimer setListener(TimerManager.TimerListener listener);
}