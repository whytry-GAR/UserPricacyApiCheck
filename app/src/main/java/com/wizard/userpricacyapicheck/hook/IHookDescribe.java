package com.wizard.userpricacyapicheck.hook;


import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import de.robv.android.xposed.XC_MethodHook;

public interface IHookDescribe {

    String getClassName();

    HookInfo initHookMethod(HookInfo hookInfo);

    HookInfo build(String logTag);

    XC_MethodHook getXC_MethodHook(String logTag);
}
