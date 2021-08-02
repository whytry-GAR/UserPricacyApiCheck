package com.wizard.userpricacyapicheck.hook.model;

import de.robv.android.xposed.XC_MethodHook;
import java.util.ArrayList;
import java.util.List;

/**
 * 需要hook的类和方法 数据类
 */
public class HookInfo {

    public String className;
    public List<MethodInfo> methodInfoList = new ArrayList<>();
    public XC_MethodHook mXC_MethodHook;

    public HookInfo(String className) {
        this.className = className;
    }

    public HookInfo addMethodInfo(MethodInfo methodInfo) {
        if (methodInfo != null) {
            methodInfoList.add(methodInfo);
        }
        return this;
    }
}
