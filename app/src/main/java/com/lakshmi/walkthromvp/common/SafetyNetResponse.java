package com.lakshmi.walkthromvp.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mgs1850 on 3/24/2017.
 */

public class SafetyNetResponse {
    @SerializedName("nonce")
    @Expose
    public String nonce;
    @SerializedName("timestampMs")
    @Expose
    public long timestampMs;
    @SerializedName("apkPackageName")
    @Expose
    public String apkPackageName;
    @SerializedName("apkDigestSha256")
    @Expose
    public String apkDigestSha256;
    @SerializedName("ctsProfileMatch")
    @Expose
    public Boolean ctsProfileMatch;
    @SerializedName("basicIntegrity")
    @Expose
    public Boolean basicIntegrity;

}
