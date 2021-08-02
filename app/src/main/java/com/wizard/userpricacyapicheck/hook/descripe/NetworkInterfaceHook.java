package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import java.net.NetworkInterface;


public class NetworkInterfaceHook extends HookDescribe {


    @Override
    public String getClassName() {
        return NetworkInterface.class.getName();
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //mac地址
        hookInfo
            .addMethodInfo(new MethodInfo("getHardwareAddress"));
        return hookInfo;
    }
}