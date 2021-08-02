package com.wizard.userpricacyapicheck.hook;

import android.util.Log;

import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import de.robv.android.xposed.XC_MethodHook;


public abstract class HookDescribe implements IHookDescribe {


    @Override
    public HookInfo build(String logTag) {
        HookInfo hookInfo = new HookInfo(getClassName());
        initHookMethod(hookInfo);
        hookInfo.mXC_MethodHook = getXC_MethodHook(logTag);
        return hookInfo;
    }

    @Override
    public XC_MethodHook getXC_MethodHook(String logTag) {
        return new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                normalLog(getClassName(), param.method.getName(), logTag);
            }
        };
    }



    public void normalLog(String className, String methodName, String logTag) {
        try {
            throw new RuntimeException("打印" + className + "#" + methodName + "调用栈");
        } catch (Throwable e) {
            Log.d(logTag, "\n\n\n-----------------------------------------------------------");
            Log.e(logTag, "beforeHookedMethod: ", e);
            Log.d(logTag, "-----------------------------------------------------------\n\n\n");
        }
    }
}
