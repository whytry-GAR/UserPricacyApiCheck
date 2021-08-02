package com.wizard.userpricacyapicheck.hook.descripe;

import android.app.PendingIntent;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanSettings;
import android.os.WorkSource;
import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import java.util.List;


public class BluetoothLeScannerHook extends HookDescribe {




    @Override
    public String getClassName() {
        return "android.bluetooth.le.BluetoothLeScanner";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //蓝牙信息
        hookInfo
            .addMethodInfo(new MethodInfo("startScan", List.class.getName(), ScanSettings.class.getName(),
                WorkSource.class.getName(),
                ScanCallback.class.getName(), PendingIntent.class.getName(), List.class.getName()));
        return hookInfo;
    }
}