package com.lakshmi.walkthromvp.common.request;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mgs1850 on 2/8/2017.
 */

public class AdditionalInfo implements Parcelable{

    @SerializedName("addInfo1")
    @Expose
    public String addInfo1;
    @SerializedName("addInfo2")
    @Expose
    public String addInfo2;
    @SerializedName("addInfo3")
    @Expose
    public String addInfo3;
    @SerializedName("addInfo4")
    @Expose
    public String addInfo4;
    @SerializedName("addInfo5")
    @Expose
    public String addInfo5;
    @SerializedName("addInfo6")
    @Expose
    public String addInfo6;
    @SerializedName("addInfo7")
    @Expose
    public String addInfo7;
    @SerializedName("addInfo8")
    @Expose
    public String addInfo8;
    @SerializedName("addInfo9")
    @Expose
    public String addInfo9;
    @SerializedName("addInfo10")
    @Expose
    public String addInfo10;

    public AdditionalInfo() {

    }

    protected AdditionalInfo(Parcel in) {
        addInfo1 = in.readString();
        addInfo2 = in.readString();
        addInfo3 = in.readString();
        addInfo4 = in.readString();
        addInfo5 = in.readString();
        addInfo6 = in.readString();
        addInfo7 = in.readString();
        addInfo8 = in.readString();
        addInfo9 = in.readString();
        addInfo10 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addInfo1);
        dest.writeString(addInfo2);
        dest.writeString(addInfo3);
        dest.writeString(addInfo4);
        dest.writeString(addInfo5);
        dest.writeString(addInfo6);
        dest.writeString(addInfo7);
        dest.writeString(addInfo8);
        dest.writeString(addInfo9);
        dest.writeString(addInfo10);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdditionalInfo> CREATOR = new Creator<AdditionalInfo>() {
        @Override
        public AdditionalInfo createFromParcel(Parcel in) {
            return new AdditionalInfo(in);
        }

        @Override
        public AdditionalInfo[] newArray(int size) {
            return new AdditionalInfo[size];
        }
    };
}

