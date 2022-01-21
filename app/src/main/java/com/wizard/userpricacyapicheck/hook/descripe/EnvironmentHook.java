package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;

public class EnvironmentHook extends HookDescribe {
    @Override
    public String getClassName() {
        return "android.os.Environment";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //mac地址
        hookInfo
            .addMethodInfo(new MethodInfo("getExternalStorageDirectory"));
        return hookInfo;
    }
}
