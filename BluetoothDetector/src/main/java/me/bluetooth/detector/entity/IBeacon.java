/**
 * Radius Networks, Inc.
 * http://www.radiusnetworks.com
 *
 * @author David G. Young
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package me.bluetooth.detector.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * ==================================================
 * 这个类表示 Android 设备检测到的单个硬件 iBeacon 设备
 * <p>
 * iBeacon由基于字段的三部分标识符组成
 * proximityUUID - 一个字符串UUID通常标识一系列信标的所有者
 * major - 一个16位整数，相当于群组号，同一个组里Beacon有相同的Major
 * minor - 一个16位整数，相当于识别群组里单个的Beacon
 * <p>
 * iBeacon 发送包里包含以上这三个标识符，以及 iBeacon 的蓝牙的信息强度 RSSI
 * UUID + Major + Minor 就构成了一个 iBeacon 的识别号
 * <p>
 * iBeacon <a href="https://baike.baidu.com/item/iBeacon/13826305?fr=aladdin"></a>
 * ==================================================
 */
public class IBeacon implements Parcelable {

    private static final String TAG = IBeacon.class.getSimpleName();

    private final static char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 距离不到半米远
     */
    public static final int PROXIMITY_IMMEDIATE = 1;

    /**
     * 距离超过半米远，但不到四米远
     */
    public static final int PROXIMITY_NEAR = 2;

    /**
     * 距离超过四米远
     */
    public static final int PROXIMITY_FAR = 3;

    /**
     * RSSI 值不佳或测量的发射功率，无法估算距离
     */
    public static final int PROXIMITY_UNKNOWN = 0;

    /**
     * 一个16字节的 UUID 通常代表拥有 iBeacon 的所有者
     * Example: E2C56DB5-DFFB-48D2-B060-D0F5A71096E0
     */
    private String proximityUuid;

    /**
     * 一个16位整数，指示一组 iBeacons
     */
    private int major;

    /**
     * 一个16位整数标识一个单一的 iBeacon
     */
    private int minor;

    /**
     * 当前 iBeacon 的距离，只可能有如下四个值
     *
     * @see #PROXIMITY_IMMEDIATE
     * @see #PROXIMITY_NEAR
     * @see #PROXIMITY_FAR
     * @see #PROXIMITY_UNKNOWN
     */
    private Integer proximity;

    /**
     * 这个数字是估计这个 iBeacon 离米有多远的估计值
     */
    private Double accuracy;

    /**
     * 测量到的蓝牙设备的信号强度
     */
    private int rssi;

    /**
     * RSSI 中 iBeacon 的校准测量发射功率，该值帮助帮助估计距离
     */
    private int txPower;

    /**
     * 如果有多个RSSI样本可用，则这是运行平均值
     */
    private Double runningAverageRssi = null;

    public IBeacon() {

    }

    public IBeacon(String proximityUuid, int major, int minor, int txPower, int rssi) {
        this.proximityUuid = proximityUuid;
        this.major = major;
        this.minor = minor;
        this.rssi = rssi;
        this.txPower = txPower;
    }

    public IBeacon(IBeacon otherIBeacon) {
        this.major = otherIBeacon.major;
        this.minor = otherIBeacon.minor;
        this.accuracy = otherIBeacon.accuracy;
        this.proximity = otherIBeacon.proximity;
        this.rssi = otherIBeacon.rssi;
        this.proximityUuid = otherIBeacon.proximityUuid;
        this.txPower = otherIBeacon.txPower;
    }

    /**
     * @return accuracy
     * @see #accuracy
     */
    public double getAccuracy() {
        if (accuracy == null) {
            accuracy = calculateAccuracy(txPower, runningAverageRssi != null ? runningAverageRssi : rssi);
        }
        return accuracy;
    }

    /**
     * @return major
     * @see #major
     */
    public int getMajor() {
        return major;
    }

    /**
     * @return minor
     * @see #minor
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @return proximity
     * @see #proximity
     */
    public int getProximity() {
        if (proximity == null) {
            proximity = calculateProximity(getAccuracy());
        }
        return proximity;
    }

    /**
     * @return rssi
     * @see #rssi
     */
    public int getRssi() {
        return rssi;
    }

    /**
     * @return txPowwer
     * @see #txPower
     */
    public int getTxPower() {
        return txPower;
    }

    /**
     * @return proximityUuid
     * @see #proximityUuid
     */
    public String getProximityUuid() {
        return proximityUuid;
    }

    @Override
    public int hashCode() {
        return minor;
    }

    /**
     * 如果两个检测到的 iBeacons 共享相同的三个标识符，则认为它们是相等的，而不管它们的距离或 RSSI
     */
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof IBeacon)) {
            return false;
        }
        IBeacon thatIBeacon = (IBeacon) that;
        return (thatIBeacon.getMajor() == this.getMajor() && thatIBeacon.getMinor() == this.getMinor() && thatIBeacon.getProximityUuid().equals(this.getProximityUuid()));
    }

    /**
     * 创建一个 iBeacon
     *
     * @param scanData 实际的数据包字节
     * @param rssi     T信息包的测量信号强度
     * @return
     */
    public static IBeacon createIBeacon(byte[] scanData, int rssi) {
        int startByte = 0;
        boolean patternFound = false;
        while (startByte <= 5) {
            if (((int) scanData[startByte] & 0xff) == 0x4c &&
                    ((int) scanData[startByte + 1] & 0xff) == 0x00 &&
                    ((int) scanData[startByte + 2] & 0xff) == 0x02 &&
                    ((int) scanData[startByte + 3] & 0xff) == 0x15) {
                // yes!  This is an iBeacon 
                patternFound = true;
                break;
            } else if (((int) scanData[startByte] & 0xff) == 0x2d &&
                    ((int) scanData[startByte + 1] & 0xff) == 0x24 &&
                    ((int) scanData[startByte + 2] & 0xff) == 0xbf &&
                    ((int) scanData[startByte + 3] & 0xff) == 0x16) {
                // this is an Estimote beacon
                IBeacon iBeacon = new IBeacon();
                iBeacon.major = 0;
                iBeacon.minor = 0;
                iBeacon.proximityUuid = "00000000-0000-0000-0000-000000000000";
                iBeacon.txPower = -55;
                return iBeacon;
            }
            startByte++;
        }


        if (patternFound == false) {
            // This is not an iBeacon
            Log.d(TAG, "This is not an iBeacon advertisment (no 4c000215 seen in bytes 2-5).  The bytes I see are: " + bytesToHex(scanData));
            return null;
        }

        IBeacon iBeacon = new IBeacon();

        iBeacon.major = (scanData[startByte + 20] & 0xff) * 0x100 + (scanData[startByte + 21] & 0xff);
        iBeacon.minor = (scanData[startByte + 22] & 0xff) * 0x100 + (scanData[startByte + 23] & 0xff);
        iBeacon.txPower = (int) scanData[startByte + 24]; // this one is signed
        iBeacon.rssi = rssi;

        // AirLocate:
        // 02 01 1a 1a ff 4c 00 02 15  # Apple's fixed iBeacon advertising prefix
        // e2 c5 6d b5 df fb 48 d2 b0 60 d0 f5 a7 10 96 e0 # iBeacon profile uuid
        // 00 00 # major 
        // 00 00 # minor 
        // c5 # The 2's complement of the calibrated Tx Power
        // Estimote:        
        // 02 01 1a 11 07 2d 24 bf 16 
        // 394b31ba3f486415ab376e5c0f09457374696d6f7465426561636f6e00000000000000000000000000000000000000000000000000

        byte[] proximityUuidBytes = new byte[16];
        System.arraycopy(scanData, startByte + 4, proximityUuidBytes, 0, 16);
        String hexString = bytesToHex(proximityUuidBytes);
        StringBuilder sb = new StringBuilder();
        sb.append(hexString.substring(0, 8));
        sb.append("-");
        sb.append(hexString.substring(8, 12));
        sb.append("-");
        sb.append(hexString.substring(12, 16));
        sb.append("-");
        sb.append(hexString.substring(16, 20));
        sb.append("-");
        sb.append(hexString.substring(20, 32));
        iBeacon.proximityUuid = sb.toString();

        return iBeacon;
    }

    public double calculateAccuracy(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // 如果无法确定准确度，则返回-1
        }

        Log.d(TAG, "calculating accuracy based on rssi of " + rssi);

        double ratio = rssi * 1.0 / txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        } else {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
            Log.d(TAG, " avg rssi: " + rssi + " accuracy: " + accuracy);
            return accuracy;
        }
    }

    public int calculateProximity(double accuracy) {
        if (accuracy < 0) {
            return PROXIMITY_UNKNOWN;
        }

        if (accuracy < 0.5) {
            return IBeacon.PROXIMITY_IMMEDIATE;
        }

        if (accuracy <= 4.0) {
            return IBeacon.PROXIMITY_NEAR;
        }

        return IBeacon.PROXIMITY_FAR;

    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX[v >>> 4];
            hexChars[j * 2 + 1] = HEX[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UUID=").append(this.proximityUuid.toUpperCase());
        sb.append(" Major=").append(this.major);
        sb.append(" Minor=").append(this.minor);
        sb.append(" TxPower=").append(this.txPower);
        return sb.toString();
    }

    private IBeacon(Parcel in) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<IBeacon> CREATOR = new Creator<IBeacon>() {

        @Override
        public IBeacon createFromParcel(Parcel in) {
            return new IBeacon(in);
        }

        @Override
        public IBeacon[] newArray(int size) {
            return new IBeacon[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
