package com.wizard.userpricacyapicheck.hook.descripe;

import android.telephony.TelephonyManager;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class TelephonyManagerHook extends HookDescribe {


    @Override
    public String getClassName() {
        return TelephonyManager.class.getName();
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        hookInfo
            //IMEI
            .addMethodInfo(new MethodInfo("getImei"))
            .addMethodInfo(new MethodInfo("getImei", "int"))
            .addMethodInfo(new MethodInfo("getDeviceId"))
            .addMethodInfo(new MethodInfo("getDeviceId", "int"))
            //基站定位
            .addMethodInfo(new MethodInfo("getCellLocation"))
            .addMethodInfo(new MethodInfo("getAllCellInfo"))
            //imsi
            .addMethodInfo(new MethodInfo("getSubscriberId"))
            .addMethodInfo(new MethodInfo("getSubscriberId", "int"))
            //sim卡信息
            .addMethodInfo(new MethodInfo("getSimSerialNumber"))
            .addMethodInfo(new MethodInfo("getSimSerialNumber", "int"))

            .addMethodInfo(new MethodInfo("getNetworkOperatorForPhone", "int"))
            .addMethodInfo(new MethodInfo("getSimCountryIsoForPhone", "int"))
            .addMethodInfo(new MethodInfo("getSimOperatorNameForPhone", "int"));
        return hookInfo;
    }
}
