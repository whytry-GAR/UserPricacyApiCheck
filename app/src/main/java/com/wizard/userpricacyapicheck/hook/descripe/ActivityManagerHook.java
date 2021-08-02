package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class ActivityManagerHook extends HookDescribe {


    @Override
    public String getClassName() {
        return "android.app.ActivityManager";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //账户
        hookInfo
            .addMethodInfo(new MethodInfo("getRunningAppProcesses"));
        return hookInfo;
    }
}