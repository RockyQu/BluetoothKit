package me.bluetooth.detector.utils;

import android.util.Log;

public class LogDetector {

    /**
     * 是否开启日志输出
     */
    public static final boolean DEBUG = false;

    /**
     * 黑色
     *
     * @param message Message
     */
    public static void v(String message) {
        if (!DEBUG) {
            return;
        }
        if (message == null) {
            return;
        }
        if (!message.contains("(") || !message.contains(")")) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuffer sb = new StringBuffer().append("(").append(stackTraceElement.getFileName()).append(":")
                    .append(stackTraceElement.getLineNumber()).append(")");
            message = message.concat(sb.toString());
        }
        Log.v("verbose", message);
    }

    /**
     * 蓝色
     *
     * @param message Message
     */
    public static void d(String message) {
        if (!DEBUG) {
            return;
        }
        if (message == null) {
            return;
        }
        if (!message.contains("(") || !message.contains(")")) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuffer sb = new StringBuffer().append("(").append(stackTraceElement.getFileName()).append(":")
                    .append(stackTraceElement.getLineNumber()).append(")");
            message = message.concat(sb.toString());
        }
        Log.d("debug", message);
    }

    /**
     * 绿色
     *
     * @param message Message
     */
    public static void i(String message) {
        if (!DEBUG) {
            return;
        }
        if (message == null) {
            return;
        }
        if (!message.contains("(") || !message.contains(")")) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuffer sb = new StringBuffer().append("(").append(stackTraceElement.getFileName()).append(":")
                    .append(stackTraceElement.getLineNumber()).append(")");
            message = message.concat(sb.toString());
        }
        Log.i("information", message);
    }

    /**
     * 橘色
     *
     * @param message Message
     */
    public static void w(String message) {
        if (!DEBUG) {
            return;
        }
        if (message == null) {
            return;
        }
        if (!message.contains("(") || !message.contains(")")) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuffer sb = new StringBuffer().append("(").append(stackTraceElement.getFileName()).append(":")
                    .append(stackTraceElement.getLineNumber()).append(")");
            message = message.concat(sb.toString());
        }
        Log.w("warning", message);
    }

    /**
     * 红色
     *
     * @param message Message
     */
    public static void e(String message) {
        if (!DEBUG) {
            return;
        }
        if (message == null) {
            return;
        }
        if (!message.contains("(") || !message.contains(")")) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuffer sb = new StringBuffer().append("(").append(stackTraceElement.getFileName()).append(":")
                    .append(stackTraceElement.getLineNumber()).append(")");
            message = message.concat(sb.toString());
        }
        Log.e("error", message);
    }
}