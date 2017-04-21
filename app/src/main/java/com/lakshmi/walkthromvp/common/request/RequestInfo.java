package com.lakshmi.walkthromvp.common.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mgs1850 on 2/8/2017.
 */

public class RequestInfo implements Parcelable {
    @SerializedName("pspId")
    @Expose
    public long pspId;

    @SerializedName("pspRefNo")
    @Expose
    public String pspRefNo;

    public String pspRespRefNo;

    public RequestInfo() {

    }

    protected RequestInfo(Parcel in) {
        pspId = in.readLong();
        pspRefNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(pspId);
        dest.writeString(pspRefNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RequestInfo> CREATOR = new Creator<RequestInfo>() {
        @Override
        public RequestInfo createFromParcel(Parcel in) {
            return new RequestInfo(in);
        }

        @Override
        public RequestInfo[] newArray(int size) {
            return new RequestInfo[size];
        }
    };
}
