package com.wizard.userpricacyapicheck.hook.descripe;

import android.net.wifi.WifiInfo;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class WifiInfoHook extends HookDescribe {


    @Override
    public String getClassName() {
        return WifiInfo.class.getName();
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        hookInfo
            .addMethodInfo(new MethodInfo("getMacAddress"))
            .addMethodInfo(new MethodInfo("getIpAddress"))
            .addMethodInfo(new MethodInfo("getSSID"))
            .addMethodInfo(new MethodInfo("getBSSID"));
        return hookInfo;
    }
}