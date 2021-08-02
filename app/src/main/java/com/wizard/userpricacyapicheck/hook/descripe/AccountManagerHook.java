package com.wizard.userpricacyapicheck.hook.descripe;

import com.wizard.userpricacyapicheck.hook.HookDescribe;
import com.wizard.userpricacyapicheck.hook.model.HookInfo;
import com.wizard.userpricacyapicheck.hook.model.MethodInfo;


public class AccountManagerHook extends HookDescribe {


    @Override
    public String getClassName() {
        return "android.accounts.AccountManager";
    }

    @Override
    public HookInfo initHookMethod(HookInfo hookInfo) {
        //账户
        hookInfo
            .addMethodInfo(new MethodInfo("getAccounts"))
            .addMethodInfo(new MethodInfo("getAccountsByType", String.class.getName()));
        return hookInfo;
    }
}