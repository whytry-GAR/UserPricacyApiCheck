package com.wizard.userpricacyapicheck.hook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法数据描述
 */
public class MethodInfo {

    public String methodName;
    public ArrayList<String> methodParams = new ArrayList<>();

    public MethodInfo(String methodName, String... params) {
        this.methodName = methodName;
        if (params != null && params.length > 0) {
            methodParams.addAll(List.of(params));
        }
    }
}
