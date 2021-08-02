package com.wizard.userpricacyapicheck.hook.descripe;


import android.content.ContentResolver;
import android.provider.Settings.Secure;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.descripe.SystemHook.AndroidIdXC_MethodHook;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import de.robv.android.xposed.XC_MethodHook;

public class SecureHook extends HookDescribe {


    @Override
    public String getClassName() {
        return Secure.class.getName();
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //android_id
        hookInfo
            .addMethodInfo(new MethodInfo("getString", ContentResolver.class.getName(), String.class.getName()));
        return hookInfo;
    }

    @Override
    public XC_MethodHook getXC_MethodHook(String logTag) {
        return new AndroidIdXC_MethodHook(getClassName(), logTag);
    }
}