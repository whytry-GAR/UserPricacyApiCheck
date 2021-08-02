package com.wizard.userpricacyapicheck.hook.descripe;

import android.os.Build.VERSION_CODES;
import android.telephony.SubscriptionManager;
import androidx.annotation.RequiresApi;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class SubscriptionManagerHook extends HookDescribe {


    @RequiresApi(api = VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public String getClassName() {
        return SubscriptionManager.class.getName();
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        hookInfo
            .addMethodInfo(new MethodInfo("getDefaultDataSubscriptionId"))
            .addMethodInfo(new MethodInfo("getDefaultDataPhoneId"))
            .addMethodInfo(new MethodInfo("getDefaultDataSubId"));
        return hookInfo;
    }
}