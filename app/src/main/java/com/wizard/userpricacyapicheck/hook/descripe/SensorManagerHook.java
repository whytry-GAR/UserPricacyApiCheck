package com.wizard.userpricacyapicheck.hook.descripe;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.os.Handler;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class SensorManagerHook extends HookDescribe {


    @Override
    public String getClassName() {
        return "android.hardware.SensorManager";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        hookInfo
            .addMethodInfo(
                new MethodInfo("registerListener",
                    SensorEventListener.class.getName(), Sensor.class.getName(), "int", Handler.class.getName()));
        return hookInfo;
    }
}