package com.wizard.userpricacyapicheck.hook.descripe;

import android.os.Build;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class BuildHook extends HookDescribe {


    @Override
    public String getClassName() {
        return Build.class.getName();
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        hookInfo.addMethodInfo(new MethodInfo("getSerial"));
        return hookInfo;
    }
}