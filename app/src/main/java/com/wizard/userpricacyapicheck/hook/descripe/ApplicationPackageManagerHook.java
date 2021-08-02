package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class ApplicationPackageManagerHook extends HookDescribe {

    @Override
    public String getClassName() {
        return "android.app.ApplicationPackageManager";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //安装列表
        hookInfo
            .addMethodInfo(new MethodInfo("getInstalledApplications", "int"))
            .addMethodInfo(new MethodInfo("getInstalledPackages", "int"))
            .addMethodInfo(new MethodInfo("getInstalledModules", "int"))
        ;
        return hookInfo;
    }
}