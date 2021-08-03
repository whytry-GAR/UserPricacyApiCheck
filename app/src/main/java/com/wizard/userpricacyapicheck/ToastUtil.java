package com.wizard.userpricacyapicheck;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import java.lang.reflect.Field;


@SuppressLint({"ShowToast", "ToastShowLint"})
public class ToastUtil {

    private static final String TAG = "ToastUtil";
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    private static Field sField_TN;
    private static Field sField_TN_Handler;

    static {
        if (isSdkLess28()) {
            try {
                sField_TN = Toast.class.getDeclaredField("mTN");
                sField_TN.setAccessible(true);
                sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
                sField_TN_Handler.setAccessible(true);
            } catch (Exception e) {
                Log.e(TAG, "ToastUtil reflect error " + e);

            }
        }
    }

    public static void makeToast(Context context, String tip, int duration) {
        if (context != null) {
            show(Toast.makeText(context, tip, duration));
        }
    }

    public static void makeToast(Context context, int resId, int duration) {
        if (context != null) {
            show(Toast.makeText(context, resId, duration));
        }
    }

    public static void makeToast(Context context, CharSequence tip, int duration) {
        if (context != null) {
            show(Toast.makeText(context, tip, duration));
        }
    }

    public static void makeToast(Context context, String tip, int duration, int gravity, int xOffset, int yOffset) {
        if (context != null) {
            Toast toast = Toast.makeText(context, tip, duration);
            toast.setGravity(gravity, xOffset, yOffset);
            show(toast);
        }
    }

    /**
     * @param str 优先使用str字符串，如果为null或者空串则去使用 defaultId
     */
    public static void makeToastWithDefault(Context context, String str, int defaultId,
        int duration) {
        if (context != null) {
            Toast toast;
            if (str != null && str.length() > 0) {
                toast = Toast.makeText(context, str, duration);
            } else {
                toast = Toast.makeText(context, defaultId, duration);
            }
            show(toast);
        }
    }

    public static void show(Toast toast) {
        if (BuildConfig.DEBUG && Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("should be in the main thread");
        }
        tryToHook(toast);
        toast.show();
    }

    private static boolean isSdkLess28() {
        return Build.VERSION.SDK_INT < VERSION_CODES.P;
    }

    private static void tryToHook(Toast toast) {
        if (sField_TN != null && sField_TN_Handler != null) {
            try {
                Object tn = sField_TN.get(toast);
                Handler preHandler = (Handler) sField_TN_Handler.get(tn);
                sField_TN_Handler.set(tn, new SafeHandler(preHandler));
            } catch (Exception e) {
                Log.e(TAG, "try to hook error " + e);

            }
        }
    }

    private static class SafeHandler extends Handler {

        private Handler impl;

        SafeHandler(Handler impl) {
            this.impl = impl;
        }

        @Override
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                Log.e(TAG, "system show toast error" + e);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            impl.handleMessage(msg);
        }
    }

}