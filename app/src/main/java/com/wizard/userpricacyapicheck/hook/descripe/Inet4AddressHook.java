package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;

public class Inet4AddressHook extends HookDescribe {
    @Override
    public String getClassName() {
        return "java.net.Inet4Address";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //账户
        hookInfo
            .addMethodInfo(new MethodInfo("getHostAddress"));
        return hookInfo;
    }
}
