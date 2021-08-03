package com.wizard.userpricacyapicheck.hook.descripe;

import android.content.ContentResolver;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.util.Log;
import com.wizard.userpricacyapicheck.ToastUtil;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.HookModule;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import de.robv.android.xposed.XC_MethodHook;


public class SystemHook extends HookDescribe {


    @Override
    public String getClassName() {
        return System.class.getName();
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

    public static class AndroidIdXC_MethodHook extends XC_MethodHook {

        private final String className;
        String logTag;

        public AndroidIdXC_MethodHook(String className, String logTag) {
            this.className = className;
            this.logTag = logTag;
        }

        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);

            Object value = param.args[param.args.length - 1];
            if (value instanceof String) {
                if (Secure.ANDROID_ID.equals(value)) {
                    try {

                        throw new RuntimeException(
                            "打印" + className + "#" + param.method.getName() + "调用栈 - " + value);
                    } catch (Throwable e) {
                        Log.d(logTag, "\n\n\n-----------------------------------------------------------");
                        Log.e(logTag, "beforeHookedMethod: ", e);
                        Log.d(logTag, "-----------------------------------------------------------\n\n\n");
                        if (HookModule.needToast) {
                            ToastUtil.makeToast(HookModule.context,
                                "调用了" + className + "#" + param.method.getName() + "调用栈 - " + value,
                                ToastUtil.LENGTH_SHORT);
                        }
                    }
                } else {
                    String logMsg = "调用了" + className + "#" + param.method.getName() + "  -" + value;
                    Log.d(logTag, "\n\n\n-----------------------------------------------------------");
                    Log.i(logTag, logMsg);
                    Log.d(logTag, "-----------------------------------------------------------\n\n\n");
                }
            }
        }
    }
}