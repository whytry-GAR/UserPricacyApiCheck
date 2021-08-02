package com.wizard.userpricacyapicheck.hook.descripe;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.CancellationSignal;
import com.google.android.gms.location.LocationRequest;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import java.util.concurrent.Executor;
import java.util.function.Consumer;


public class LocationManagerHook extends HookDescribe {


    @Override
    public String getClassName() {
        return "android.location.LocationManager";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //GPS地址
        hookInfo
            .addMethodInfo(new MethodInfo("getLastKnownLocation", String.class.getName()))
            .addMethodInfo(new MethodInfo("getLastLocation"));
        if (VERSION.SDK_INT >= VERSION_CODES.N) {
            hookInfo
                .addMethodInfo(new MethodInfo("getCurrentLocation", LocationRequest.class.getName(),
                CancellationSignal.class.getName(), Executor.class.getName(), Consumer.class.getName()))
                .addMethodInfo(new MethodInfo("getCurrentLocation", String.class.getName(),
                    CancellationSignal.class.getName(), Executor.class.getName(), Consumer.class.getName()));
        }
        return hookInfo;
    }
}