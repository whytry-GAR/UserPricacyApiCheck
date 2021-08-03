package com.wizard.userpricacyapicheck.hook;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.wizard.userpricacyapicheck.Constants;
import com.wizard.userpricacyapicheck.MultiprocessSharedPreferences;
import com.wizard.userpricacyapicheck.ToastUtil;
import com.wizard.userpricacyapicheck.hook.descripe.AccountManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.ActivityManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.ApplicationPackageManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.BluetoothLeScannerHook;
import com.wizard.userpricacyapicheck.hook.descripe.BuildHook;
import com.wizard.userpricacyapicheck.hook.descripe.LocationManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.NetworkInterfaceHook;
import com.wizard.userpricacyapicheck.hook.descripe.SecureHook;
import com.wizard.userpricacyapicheck.hook.descripe.SensorManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.SubscriptionManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.SystemHook;
import com.wizard.userpricacyapicheck.hook.descripe.SystemPropertiesHook;
import com.wizard.userpricacyapicheck.hook.descripe.TelephonyManagerHook;
import com.wizard.userpricacyapicheck.hook.descripe.WifiInfoHook;
import com.wizard.userpricacyapicheck.hook.descripe.WifiManagerHook;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * hook的模块
 */
public class HookModule implements IXposedHookLoadPackage {

    private static final String TAG = "HookModule";
    public static final List<HookInfo> hookInfos = new ArrayList<>();
    public String hookPackage = "";
    public String hookPackageTAG = TAG;
    public static Context context;
    public static boolean needToast;

    @Override
    public void handleLoadPackage(LoadPackageParam lpp) {
        initContext(lpp);
    }

    private void initContext(LoadPackageParam lpp) {
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                context = (Context) param.args[0];
                initHookPackageAndTAG(context);
                String label = context != null ? String
                    .valueOf(context.getPackageManager().getApplicationLabel(lpp.appInfo)) : "【包名获取失败】";
                String tip = "包名不匹配，应用名为 = " + label + ",包名为" + lpp.packageName;
                if (!TextUtils.isEmpty(hookPackage) && hookPackage.equals(lpp.packageName)) {
                    hookWithPackageName(lpp, hookPackage, hookPackageTAG);
                    tip =
                        "包名匹配，执行hook成功，应用名为=" + label + ",包名为" + lpp.packageName;
                }
                Log.d(TAG, tip);
                if (needToast && context != null) {
                    ToastUtil.makeToast(HookModule.context, tip, ToastUtil.LENGTH_SHORT);
                }
            }
        });
    }

    private void initHookPackageAndTAG(Context context) {
        Log.d(TAG,
            "initHookPackageAndTAG context=" + context);
        if (context != null) {
            MultiprocessSharedPreferences.setAuthority("com.wizard.userpricacyapicheck.provider");
            SharedPreferences sharedPreferences = MultiprocessSharedPreferences
                .getSharedPreferences(context, "com.wizard.userpricacyapicheck", MODE_PRIVATE);
            hookPackage = sharedPreferences.getString(Constants.KEY_CHECK_PACKAGE_NAME, "");
            needToast = sharedPreferences.getBoolean(Constants.KEY_NEED_SHOW_TOAST, false);
//        hookPackageTAG = sharedPreferences.getString("check_package_tag", "HookModule");
            Log.d(TAG,
                "initHookPackageAndTAG check_package_name=" + hookPackage + "  check_package_TAG = " + hookPackageTAG
                    + " needToast =" + needToast);
        } else {
            Log.d(TAG, "获取上下文失败");
        }
    }


    private void hookWithPackageName(@NonNull LoadPackageParam lpp, @NonNull String packageName,
        @NonNull String logTag) {

        initHookInfos(logTag);
        Log.d(logTag,
            "handleLoadPackage: packageName = " + lpp.packageName + ", processName = " + lpp.processName);
        Log.d(logTag, "package classloader: " + lpp.classLoader.toString());
        for (HookInfo hookInfo : hookInfos) {
            String className = hookInfo.className;
            for (MethodInfo methodInfo : hookInfo.methodInfoList) {
                doHook(logTag, lpp, className, methodInfo.methodName, methodInfo.methodParams, hookInfo.mXC_MethodHook);
            }
        }
    }

    public static void initHookInfos(String logTag) {
        hookInfos.add(new AccountManagerHook().build(logTag));
        hookInfos.add(new ActivityManagerHook().build(logTag));
        hookInfos.add(new ApplicationPackageManagerHook().build(logTag));
        hookInfos.add(new BluetoothLeScannerHook().build(logTag));
        hookInfos.add(new BuildHook().build(logTag));
        hookInfos.add(new LocationManagerHook().build(logTag));
        hookInfos.add(new NetworkInterfaceHook().build(logTag));
        hookInfos.add(new SecureHook().build(logTag));
        hookInfos.add(new SystemHook().build(logTag));
        hookInfos.add(new SensorManagerHook().build(logTag));
        hookInfos.add(new SubscriptionManagerHook().build(logTag));
        hookInfos.add(new SystemPropertiesHook().build(logTag));
        hookInfos.add(new TelephonyManagerHook().build(logTag));
        hookInfos.add(new WifiInfoHook().build(logTag));
        hookInfos.add(new WifiManagerHook().build(logTag));
    }

    private void doHook(String logTag, LoadPackageParam lpp, String className, String methodName,
        ArrayList<String> params, XC_MethodHook mXC_MethodHook) {
        try {
            Object[] p = params.toArray(new String[params.size()]);
            Class<?> clazz = XposedHelpers.findClass(className, lpp.classLoader);
            Method method = XposedHelpers.findMethodExactIfExists(clazz, methodName, p);
            if (method == null) {
                Log.e(logTag, "doHook: 方法没找到： " + className + "#" + methodName + " ");
            } else {
                Log.d(logTag, "doHook: 找到方法： " + className + "#" + methodName + " ");
                XposedBridge.hookMethod(method, mXC_MethodHook);
            }
        } catch (Exception e) {
            Log.e(logTag, "doHook: 方法没找到 " + methodName + " ", e);
        }
    }

}


