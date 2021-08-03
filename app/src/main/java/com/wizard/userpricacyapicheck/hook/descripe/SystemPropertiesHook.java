package com.wizard.userpricacyapicheck.hook.descripe;

import android.util.Log;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import de.robv.android.xposed.XC_MethodHook;


public class SystemPropertiesHook extends HookDescribe {


    @Override
    public String getClassName() {
        return "android.os.SystemProperties";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        hookInfo
            .addMethodInfo(new MethodInfo("get", String.class.getName(), String.class.getName()))
            .addMethodInfo(new MethodInfo("getInt", String.class.getName(), "int"))
            .addMethodInfo(new MethodInfo("getLong", String.class.getName(), "long"))
            .addMethodInfo(new MethodInfo("getBoolean", String.class.getName(), "boolean"));
        return hookInfo;
    }

    @Override
    public XC_MethodHook getXC_MethodHook(String logTag) {
        return new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Object value = param.args[0];
                if (value instanceof String) {
                    if ("no.such.thing".equals(value)) {
                        try {
                            throw new RuntimeException(
                                "打印" + getClassName() + "#" + param.method.getName() + "调用栈 - " + value);
                        } catch (Throwable e) {
                            Log.d(logTag, "\n\n\n-----------------------------------------------------------");
                            Log.e(logTag, "beforeHookedMethod: ", e);
                            Log.d(logTag, "-----------------------------------------------------------\n\n\n");

                        }
                    } else {
                        String logMsg = "调用了" + getClassName() + "#" + param.method.getName() + " - " + value;
                        Log.d(logTag, "\n\n\n-----------------------------------------------------------");
                        Log.i(logTag, logMsg);
                        Log.d(logTag, "-----------------------------------------------------------\n\n\n");
                    }
                }
            }
        };
    }
}