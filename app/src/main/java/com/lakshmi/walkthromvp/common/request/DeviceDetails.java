package com.lakshmi.walkthromvp.common.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by biztalk on 06-02-2017.
 */

public class DeviceDetails implements Parcelable {
    public String imeiOne;
    public String imeiTwo;
    public String capabaility;
    public String appId;
    public String clientType;
    public String appVersionCode;
    public String appVersionName;
    public SimData primarySimData;
    public SimData secondarySimData;
    public int selectedSimSlot;
    public String oldMobileNo;
    public DeviceAnalytics deviceAnalytics;
    public String fcmToken;

    public DeviceDetails() {
    }

    @SerializedName("deviceId")
    @Expose
    public String deviceId;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("simId")
    @Expose
    public String simId;
    @SerializedName("geoCode")
    @Expose
    public String geoCode;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("ip")
    @Expose
    public String ip;
    @SerializedName("os")
    @Expose
    public String os;
    @SerializedName("deviceType")
    @Expose
    public String deviceType;
    @SerializedName("appName")
    @Expose
    public String appName;
    @SerializedName("capability")
    @Expose
    public String capability;

    @SerializedName("androidId")
    @Expose
    public String androidId;
    @SerializedName("bluetoothMac")
    @Expose
    public String bluetoothMac;
    @SerializedName("wifiMac")
    @Expose
    public String wifiMac;
    @SerializedName("simSerialNo")
    @Expose
    public String simSerialNo;
    @SerializedName("regId")
    @Expose
    public String regId;

    protected DeviceDetails(Parcel in) {
        imeiOne = in.readString();
        imeiTwo = in.readString();
        capabaility = in.readString();
        appId = in.readString();
        clientType = in.readString();
        appVersionCode = in.readString();
        appVersionName = in.readString();
        selectedSimSlot = in.readInt();
        deviceId = in.readString();
        mobileNo = in.readString();
        simId = in.readString();
        geoCode = in.readString();
        location = in.readString();
        ip = in.readString();
        os = in.readString();
        deviceType = in.readString();
        appName = in.readString();
        capability = in.readString();
        androidId = in.readString();
        bluetoothMac = in.readString();
        wifiMac = in.readString();
        simSerialNo = in.readString();
        regId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imeiOne);
        dest.writeString(imeiTwo);
        dest.writeString(capabaility);
        dest.writeString(appId);
        dest.writeString(clientType);
        dest.writeString(appVersionCode);
        dest.writeString(appVersionName);
        dest.writeInt(selectedSimSlot);
        dest.writeString(deviceId);
        dest.writeString(mobileNo);
        dest.writeString(simId);
        dest.writeString(geoCode);
        dest.writeString(location);
        dest.writeString(ip);
        dest.writeString(os);
        dest.writeString(deviceType);
        dest.writeString(appName);
        dest.writeString(capability);
        dest.writeString(androidId);
        dest.writeString(bluetoothMac);
        dest.writeString(wifiMac);
        dest.writeString(simSerialNo);
        dest.writeString(regId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceDetails> CREATOR = new Creator<DeviceDetails>() {
        @Override
        public DeviceDetails createFromParcel(Parcel in) {
            return new DeviceDetails(in);
        }

        @Override
        public DeviceDetails[] newArray(int size) {
            return new DeviceDetails[size];
        }
    };
}
