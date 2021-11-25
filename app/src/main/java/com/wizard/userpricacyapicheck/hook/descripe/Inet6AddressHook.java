package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;

public class Inet6AddressHook extends HookDescribe {
    @Override
    public String getClassName() {
        return "java.net.Inet6Address";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //账户
        hookInfo
            .addMethodInfo(new MethodInfo("getHostAddress"));
        return hookInfo;
    }
}