package com.tool.bluetooth.detector.config;

import android.app.Application;

/**
 * 参数配置
 */
public class BluetoothConfiguration {

    private Application application;

    private boolean debug;

    public BluetoothConfiguration(Builder builder) {
        this.application = builder.application;
        this.debug = builder.debug;
    }

    public Application getApplication() {
        return application;
    }

    public boolean isDebug() {
        return debug;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Application application;

        // 调试模式
        private boolean debug = false;

        private Builder() {
            ;
        }

        public Builder application(Application application) {
            this.application = application;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public BluetoothConfiguration build() {
            if (application == null) {
                throw new NullPointerException();
            }
            return new BluetoothConfiguration(this);
        }
    }
}