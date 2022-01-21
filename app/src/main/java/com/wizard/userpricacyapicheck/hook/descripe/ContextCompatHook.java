package com.wizard.userpricacyapicheck.hook.descripe;

import android.content.Context;
import android.os.IBinder;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;

public class ContextCompatHook extends HookDescribe {

    @Override
    public String getClassName() {
        return "androidx.core.content.ContextCompat";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //mac地址
        hookInfo
            .addMethodInfo(new MethodInfo("checkSelfPermission", Context.class.getName(), String.class.getName()));
        return hookInfo;
    }
}
