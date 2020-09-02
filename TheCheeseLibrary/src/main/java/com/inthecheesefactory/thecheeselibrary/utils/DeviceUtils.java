package com.inthecheesefactory.thecheeselibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.provider.Settings;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by nuuneoi on 10/16/2014.
 */
public class DeviceUtils {

    private static DeviceUtils instance;

    public static DeviceUtils getInstance() {
        if (instance == null)
            instance = new DeviceUtils();
        return instance;
    }

    private Context mContext;

    private DeviceUtils() {
        mContext = Contextor.getInstance().getContext();
    }


    @SuppressLint("HardwareIds")
    public String getDeviceId() {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getVersionName() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pInfo.versionName;
        } catch (Exception e) {
            return "1.0";
        }
    }

    public int getVersionCode() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (Exception e) {
            return 1;
        }
    }

    public String getAPIVersion() {
        return "1.1.0.0";
    }

    public String getOSName() {
        return "Android";
    }

    public String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public String getDeviceName() {
        return android.os.Build.MANUFACTURER;
    }

    public String getDeviceModel() {
        return android.os.Build.MODEL;
    }
}
