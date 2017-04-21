package com.lakshmi.walkthromvp.common;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.Display;
import android.view.WindowManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.common.preferences.SharedPreferenceHelper;
import com.lakshmi.walkthromvp.common.request.AdditionalInfo;
import com.lakshmi.walkthromvp.common.request.DeviceAnalytics;
import com.lakshmi.walkthromvp.common.request.DeviceDetails;
import com.lakshmi.walkthromvp.common.request.RequestInfo;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by mgs1899 on 4/20/2017.
 */

public class DeviceDetailsSingleton {


    private static DeviceDetailsSingleton deviceDetailsSingleton;
    TelephonyManager telephonyManager;
    TelephonyInfo telephonyInfo;
    SubscriptionManager subscriptionManager;
    SharedPreferenceHelper sharedPreferenceHelper;
    String simStatePair;
    int simSerialNoLength;
    WifiManager wifiManager;
    String blutoothmacAddress;
    String androidId, mobileNo;
    ConnectivityManager connectivityManager;
    ActivityManager activityManager;
    String screenDensity = "";
    WindowManager windowManager;

    public DeviceDetailsSingleton(Context context) {
        telephonyInfo = TelephonyInfo.getInstance(context);
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        sharedPreferenceHelper = SharedPreferenceHelper.getInstance();
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        blutoothmacAddress = Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
        androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 21)
            initSubscriptionManger(context);
        screenDensity = context.getString(R.string.density);
    }


    public static void init(Context context) {
        deviceDetailsSingleton = new DeviceDetailsSingleton(context);
    }

    public static DeviceDetailsSingleton getInstance() {
        return deviceDetailsSingleton;
    }

    @TargetApi(22)
    private void initSubscriptionManger(Context context) {
        subscriptionManager = SubscriptionManager.from(context);
    }

    public boolean isSimAvailable() {
        return telephonyManager.getSimSerialNumber() == null ? false : true;
    }

    public boolean isDualSimDetectable() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public boolean isDualSimDevice() {
        return telephonyInfo == null ? false : telephonyInfo.isDualSIM();
    }

    public boolean isSimOneReady() {
        return telephonyInfo == null ? false : telephonyInfo.isSIM1Ready();
    }

    public boolean isSimTwoReady() {
        return telephonyInfo == null ? false : telephonyInfo.isSIM2Ready();
    }

    public SimData getPrimarySimData() {
        SimData simData = new SimData();
        if (!isDualSimDetectable()) {
            simData.careerName = telephonyManager.getNetworkOperatorName();
            simData.simSerialNumber = telephonyManager.getSimSerialNumber();
            simData.slotNo = 0;
            simData.simStatePair = AppConstants.D1_S1;
            return simData;
        } else {
            simData = getSimData(0);
            return simData;
        }
    }

    public SimData getSecondarySimData() {
        return getSimData(1);
    }

    @TargetApi(22)
    private SimData getSimData(int index) {
        SimData simData = new SimData();
        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
        try {

            if (subscriptionInfoList.size() == 1 && !isSimOneReady()) {
                simData.slotNo = 1;
                simData.subscriptionNumber = subscriptionInfoList.get(0).getSubscriptionId();
                simData.simSerialNumber = subscriptionInfoList.get(0).getIccId();
                simData.simStatePair = AppConstants.D2_S2;
                String carrireName1 = (String) subscriptionInfoList.get(0).getCarrierName();
                String[] arr1 = carrireName1.split(" ");
                simData.careerName = arr1[0];
            } else {
                simData.slotNo = index;
                simData.subscriptionNumber = subscriptionInfoList.get(index).getSubscriptionId();
                simData.simSerialNumber = subscriptionInfoList.get(index).getIccId();
                String carrireName1 = (String) subscriptionInfoList.get(index).getCarrierName();
                String[] arr1 = carrireName1.split(" ");
                simData.careerName = arr1[0];
                if (index == 0)
                    simData.simStatePair = AppConstants.D1_S1;
                else
                    simData.simStatePair = AppConstants.D2_S2;

            }
            return simData;
        } catch (Exception e) {
            return null;
        }
    }

    @TargetApi(22)
    public String getSecondarySimCarrierName() {

        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();

        try {
            String carrireName1 = (String) subscriptionInfoList.get(0).getCarrierName();
            String[] arr1 = carrireName1.split(" ");
            return arr1[0];
        } catch (Exception e) {
            return "";
        }
    }

    public String getSystemIMEI() {
        try {
            String deviceIMEI;

            simStatePair = sharedPreferenceHelper.getSimStatePair();

            if (isDualSimDetectable()) {
                if (simStatePair.equalsIgnoreCase("D1S1")) {
                    deviceIMEI = telephonyManager.getDeviceId(0);
                } else if (simStatePair.equalsIgnoreCase("D2S2")) {
                    deviceIMEI = telephonyManager.getDeviceId(1);
                } else {
                    deviceIMEI = telephonyManager.getDeviceId();
                }
            } else {
                deviceIMEI = telephonyManager.getDeviceId();
            }

            return deviceIMEI;
        } catch (Exception ex) {
            return "";
        }
    }

    public String getSystemSimserial() {
        try {
            String simSerialNumber = "";

            simStatePair = sharedPreferenceHelper.getSimStatePair();


            if (isDualSimDevice() && isSimOneReady() && isSimTwoReady()) {
                if (simStatePair.equalsIgnoreCase("D1S1")) {
                    if (isDualSimDetectable()) {

                        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                        try {
                            simSerialNumber = subscriptionInfoList.get(0).getIccId();
                        } catch (Exception e) {

                            simSerialNumber = telephonyManager.getSimSerialNumber();
                        }

                    } else {

                        simSerialNumber = telephonyManager.getSimSerialNumber();
                    }
                } else if (simStatePair.equalsIgnoreCase("D2S2")) {
                    if (isDualSimDetectable()) {

                        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                        try {
                            simSerialNumber = subscriptionInfoList.get(1).getIccId();
                        } catch (Exception e) {

                            simSerialNumber = telephonyManager.getSimSerialNumber();
                        }

                    } else {

                        simSerialNumber = telephonyManager.getSimSerialNumber();
                    }
                } else {
                    simSerialNumber = telephonyManager.getSimSerialNumber();
                }
            } else if (isDualSimDevice() && isSimOneReady()) {
                if (simStatePair.equalsIgnoreCase("D1S1")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                        try {
                            simSerialNumber = subscriptionInfoList.get(0).getIccId();
                        } catch (Exception e) {

                            simSerialNumber = telephonyManager.getSimSerialNumber();
                        }

                    } else {

                        simSerialNumber = telephonyManager.getSimSerialNumber();
                    }
                } else {

                    simSerialNumber = telephonyManager.getSimSerialNumber();
                }
            } else if (isDualSimDevice() && isSimTwoReady()) {
                if (simStatePair.equalsIgnoreCase("D2S2")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                        try {
                            simSerialNumber = subscriptionInfoList.get(0).getIccId();
                        } catch (Exception e) {

                            simSerialNumber = telephonyManager.getSimSerialNumber();
                        }

                    } else {

                        simSerialNumber = telephonyManager.getSimSerialNumber();
                    }
                } else {
                    simSerialNumber = telephonyManager.getSimSerialNumber();
                }
            } else {
                simSerialNumber = telephonyManager.getSimSerialNumber();
            }

            return getSimNo19(simSerialNumber);
        } catch (Exception ex) {

            return "";
        }
    }

    public String getSimNo19(String simno) {
        String simSerialNumber = "";

        try {

            simSerialNoLength = sharedPreferenceHelper.getSimSerialNoLength();

            simSerialNumber = simno.substring(0, simSerialNoLength);
            return simSerialNumber;
        } catch (Exception e) {
            return simSerialNumber;
        }

    }

    public String getSystemIP() {
        try {

            String ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
            if (ip.equalsIgnoreCase("0.0.0.0") && ip != null) {
                ip = "127.0.0.1";
            } else {
                ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
            }
            return ip;
        } catch (Exception ex) {
            return "127.0.0.1";
        }
    }

    public String getSystemWifiMac() {
        try {
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            String macAddress = wInfo.getMacAddress();

            return macAddress;
        } catch (Exception ex) {
            return "";
        }
    }

    public String generateUUID() {
        UUID uId = UUID.randomUUID();
        String msgId = "SBI" + uId.toString().replace("-", "").toUpperCase();
        return msgId.substring(0, 30);
    }

    public DeviceDetails getDeviceDetails(boolean dualFlag) {

        DeviceDetails deviceDetails = new DeviceDetails();
        deviceDetails.appName = BuildConfig.APPLICATION_ID;
        deviceDetails.ip = getSystemIP();
        deviceDetails.capability = AppConstants.CAPABILITY;
        deviceDetails.geoCode = AppConstants.GEOCODE;
        deviceDetails.location = AppConstants.LOCATION;
        deviceDetails.deviceType = AppConstants.TYPE;
        deviceDetails.os = AppConstants.OS;
//        deviceDetails.deviceId = "860994030844735";
//        deviceDetails.simId = "8991000900323520679";
        deviceDetails.deviceId = dualFlag ? getSystemIMEIDual() : getSystemIMEI();
        deviceDetails.simId = (dualFlag ? getSystemSimserial_dual() : getSystemSimserial());
        deviceDetails.androidId = androidId;
        deviceDetails.wifiMac = getSystemWifiMac();
        deviceDetails.bluetoothMac = blutoothmacAddress;
        deviceDetails.regId = AppConstants.REGID;
        deviceDetails.appVersionName = BuildConfig.VERSION_NAME;
        deviceDetails.appVersionCode = String.valueOf(BuildConfig.VERSION_CODE);
        mobileNo = sharedPreferenceHelper.getMobileNumber();
        deviceDetails.fcmToken = FirebaseInstanceId.getInstance().getToken();
        deviceDetails.mobileNo = mobileNo;
        return deviceDetails;
    }

    public DeviceAnalytics getDeviceAnalytics() {
        DeviceAnalytics deviceAnalytics = new DeviceAnalytics();
        deviceAnalytics.androidApiLevel = Build.VERSION.SDK_INT;
        deviceAnalytics.androidId = androidId;
        deviceAnalytics.androidOSName = getOsName();
        deviceAnalytics.brand = Build.BRAND;
        deviceAnalytics.model = Build.MANUFACTURER;
        deviceAnalytics.locale = Locale.getDefault().getDisplayLanguage();
        deviceAnalytics.carrierNameOne = !isDualSimDetectable() ? telephonyManager.getNetworkOperatorName() : getSimData(0).careerName;
        deviceAnalytics.carrierNameTwo = isDualSimDetectable() ? (getSimData(1) != null ? getSimData(1).careerName : "") : "";
        deviceAnalytics.ramSize = getRamSize();
        deviceAnalytics.screenDensity = screenDensity;
        return deviceAnalytics;
    }


    public RequestInfo getRequestInfo() {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.pspId = Long.valueOf(AppConstants.PSPSCODE);
        requestInfo.pspRefNo = generateUUID();
        return requestInfo;
    }

    public AdditionalInfo getAdditionalInfo() {
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.addInfo9 = "NA";
        additionalInfo.addInfo10 = "NA";

        return additionalInfo;
    }

    public boolean hasConnectivity() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null;
    }

    public String getSystemSimserial_dual() {
        try {
            String simSerialNumber = "";
            if (isDualSimDevice() && isSimOneReady() && isSimTwoReady()) {
                if (isDualSimDetectable()) {
                    final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    try {
                        simSerialNumber = getSimNo19(subscriptionInfoList.get(0).getIccId()) + "#" + getSimNo19(subscriptionInfoList.get(1).getIccId());
                    } catch (Exception e) {
                        simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                    }

                } else {
                    simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                }
            } else if (isDualSimDevice() && isSimOneReady()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    try {
                        simSerialNumber = getSimNo19(subscriptionInfoList.get(0).getIccId());

                    } catch (Exception e) {
                        simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                    }

                } else {

                    simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                }
            } else if (isDualSimDevice() && isSimTwoReady()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    try {
                        simSerialNumber = getSimNo19(subscriptionInfoList.get(0).getIccId());

                    } catch (Exception e) {
                        simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                    }

                } else {
                    simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                }
            } else {
                simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
            }
            return simSerialNumber;
        } catch (Exception ex) {
            // LogUtil.info("Error occured in while getting Response"+ e.getMessage());
            return "";
        }
    }

    public String getSystemSimserial_status(String simNo) {
        try {
            String simSerialNumber = "", simStatus = "";

            if (isDualSimDevice() && isSimOneReady() && isSimTwoReady()) {
                if (isDualSimDetectable()) {
                    final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    try {

                        if (getSimNo19(subscriptionInfoList.get(0).getIccId()).equalsIgnoreCase(simNo)) {
                            simStatus = "D1S1";
                        } else if (getSimNo19(subscriptionInfoList.get(1).getIccId()).equalsIgnoreCase(simNo)) {
                            simStatus = "D2S2";
                        }
                    } catch (Exception e) {
                        simSerialNumber = telephonyManager.getSimSerialNumber();
                        if (getSimNo19(simSerialNumber).equalsIgnoreCase(simNo)) {
                            simStatus = "D1S1";
                        }
                    }

                } else {
                    simSerialNumber = telephonyManager.getSimSerialNumber();
                    if (getSimNo19(simSerialNumber).equalsIgnoreCase(simNo)) {
                        simStatus = "D1S1";
                    }
                }
            } else if (isDualSimDevice() && isSimOneReady()) {
                if (isDualSimDetectable()) {
                    final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    try {
                        simSerialNumber = getSimNo19(subscriptionInfoList.get(0).getIccId());
                        if (simSerialNumber.equalsIgnoreCase(simNo)) {
                            simStatus = "D1S1";
                        }

                    } catch (Exception e) {
                        simSerialNumber = telephonyManager.getSimSerialNumber();
                        if (getSimNo19(simSerialNumber).equalsIgnoreCase(simNo)) {
                            simStatus = "D1S1";
                        }
                    }

                } else {
                    simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                    if (simSerialNumber.equalsIgnoreCase(simNo)) {
                        simStatus = "D1S1";
                    }
                }
            } else if (isDualSimDevice() && isSimTwoReady()) {
                if (isDualSimDetectable()) {

                    final List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    try {
                        simSerialNumber = getSimNo19(subscriptionInfoList.get(0).getIccId());
                        if (simSerialNumber.equalsIgnoreCase(simNo)) {
                            simStatus = "D2S2";
                        }

                    } catch (Exception e) {
                        simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                        if (simSerialNumber.equalsIgnoreCase(simNo)) {
                            simStatus = "D1S1";
                        }
                    }

                } else {
                    simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                    if (simSerialNumber.equalsIgnoreCase(simNo)) {
                        simStatus = "D2S2";
                    }
                }
            } else {
                simSerialNumber = getSimNo19(telephonyManager.getSimSerialNumber());
                if (simSerialNumber.equalsIgnoreCase(simNo)) {
                    simStatus = "D1S1";
                }
            }
            return simStatus;
        } catch (Exception ex) {
//             LogUtil.info("Error occured in while getting Response"+ e.getMessage());
            ex.getStackTrace();
            return "";
        }
    }

    public String getSystemIMEIDual() {
        try {

            String deviceIMEI = "";
            if (isDualSimDevice() && isSimOneReady() && isSimTwoReady()) {
                if (isDualSimDetectable()) {
                    deviceIMEI = telephonyManager.getDeviceId(0) + "#" + telephonyManager.getDeviceId(1);

                } else {
                    deviceIMEI = telephonyManager.getDeviceId();
                }
            } else if (isDualSimDevice() && isSimOneReady()) {
                if (isDualSimDetectable()) {
                    deviceIMEI = telephonyManager.getDeviceId(0);
                } else {
                    deviceIMEI = telephonyManager.getDeviceId();
                }
            } else if (isDualSimDevice() && isSimTwoReady()) {
                if (isDualSimDetectable()) {
                    deviceIMEI = telephonyManager.getDeviceId(1);

                } else {
                    deviceIMEI = telephonyManager.getDeviceId();
                }
            } else {

                deviceIMEI = telephonyManager.getDeviceId();
            }


            return deviceIMEI;
        } catch (Exception ex) {
            return "";
        }
    }

    public String getSystemIMEI_Status(String deviceId) {
        try {

            String deviceIMEI = "", simStatus = "";
            if (isDualSimDevice() && isSimOneReady() && isSimTwoReady()) {
                if (isDualSimDetectable()) {
                    deviceIMEI = telephonyManager.getDeviceId(0) + "#" + telephonyManager.getDeviceId(1);
                    if (telephonyManager.getDeviceId(0).equalsIgnoreCase(deviceId)) {
                        simStatus = "D1S1";
                    } else if (telephonyManager.getDeviceId(1).equalsIgnoreCase(deviceId)) {
                        simStatus = "D2S2";
                    }
                } else {
                    simStatus = "D1S1";
                    deviceIMEI = telephonyManager.getDeviceId();
                }
            } else if (isDualSimDevice() && isSimOneReady()) {
                if (isDualSimDetectable()) {
                    deviceIMEI = telephonyManager.getDeviceId(0);
                    if (deviceId.equalsIgnoreCase(deviceIMEI)) {
                        simStatus = "D1S1";
                    }
                } else {
                    deviceIMEI = telephonyManager.getDeviceId();
                    if (deviceId.equalsIgnoreCase(deviceIMEI)) {
                        simStatus = "D1S1";
                    }
                }
            } else if (isDualSimDevice() && isSimTwoReady()) {
                if (isDualSimDetectable()) {
                    deviceIMEI = telephonyManager.getDeviceId(1);
                    if (deviceId.equalsIgnoreCase(deviceIMEI)) {
                        simStatus = "D2S2";
                    }

                } else {
                    deviceIMEI = telephonyManager.getDeviceId();
                    if (deviceId.equalsIgnoreCase(deviceIMEI)) {
                        simStatus = "D1S1";
                    }
                }
            } else {

                deviceIMEI = telephonyManager.getDeviceId();
                if (deviceId.equalsIgnoreCase(deviceIMEI)) {
                    simStatus = "D1S1";
                }
            }


            return simStatus;
        } catch (Exception ex) {

            return "";
        }
    }


    public String getBlutoothmacAddress() {
        return blutoothmacAddress;
    }

    public String getAndroidId() {
        return androidId;
    }

    public int getScreenWidth() {
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    private String getOsName() {
        String fieldName = "";
        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
        return fieldName;
    }

    public String getRamSize() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double mb = memoryInfo.totalMem / 1048576.0;
        double gb = memoryInfo.totalMem / 1073741824.0;
        String lastValue = "";
        if (gb > 1) {
            lastValue = twoDecimalForm.format(gb).concat(" GB");
        } else if (mb > 1) {
            lastValue = twoDecimalForm.format(mb).concat(" MB");
        } else {
            lastValue = twoDecimalForm.format(memoryInfo.totalMem).concat(" KB");
        }

        return lastValue;
    }
}
