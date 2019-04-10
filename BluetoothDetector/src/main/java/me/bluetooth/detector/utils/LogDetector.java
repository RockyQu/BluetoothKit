package me.bluetooth.detector.utils;

import android.util.Log;

import me.bluetooth.detector.BluetoothDetector;

public class LogDetector {

    private static final String TAG = BluetoothDetector.class.getSimpleName();

    /**
     * 黑色
     *
     * @param message Message
     */
    public static void v(boolean debug, String message) {
        if (!debug) {
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
        Log.v(TAG, message);
    }

    /**
     * 蓝色
     *
     * @param message Message
     */
    public static void d(boolean debug, String message) {
        if (!debug) {
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
        Log.d(TAG, message);
    }

    /**
     * 绿色
     *
     * @param message Message
     */
    public static void i(boolean debug, String message) {
        if (!debug) {
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
        Log.i(TAG, message);
    }

    /**
     * 橘色
     *
     * @param message Message
     */
    public static void w(boolean debug, String message) {
        if (!debug) {
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
        Log.w(TAG, message);
    }

    /**
     * 红色
     *
     * @param message Message
     */
    public static void e(boolean debug, String message) {
        if (!debug) {
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
        Log.e(TAG, message);
    }
}