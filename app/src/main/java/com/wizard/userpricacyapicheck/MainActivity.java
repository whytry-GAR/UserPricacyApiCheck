package com.wizard.userpricacyapicheck;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView btnConfirm;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchNeedToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etv_package_name);
        btnConfirm = findViewById(R.id.btn_confirm);
        switchNeedToast = findViewById(R.id.switch_need_toast);
        initView(editText);
        switchNeedToast.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveNeedToast(isChecked);
            ToastUtil.makeToast(MainActivity.this, "开关已" + (isChecked ? "开启" : "关闭"), ToastUtil.LENGTH_SHORT);
        });
        btnConfirm.setOnClickListener(v -> {
            String name = "";
            if (editText.getText() != null) {
                name = editText.getText().toString();
            }
            saveCheckPackageName(name);
        });
    }

    private void initView(EditText editText) {
        MultiprocessSharedPreferences.setAuthority("com.wizard.userpricacyapicheck.provider");
        SharedPreferences sharedPreferences = MultiprocessSharedPreferences
            .getSharedPreferences(this, getPackageName(), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.KEY_CHECK_PACKAGE_NAME, "");
        boolean needToast = sharedPreferences.getBoolean(Constants.KEY_NEED_SHOW_TOAST, false);
        if (name.length() > 0) {
            editText.setText(name);
            editText.setSelection(name.length());
        }
        switchNeedToast.setChecked(needToast);
    }


    private void saveCheckPackageName(String name) {
        MultiprocessSharedPreferences.setAuthority("com.wizard.userpricacyapicheck.provider");
        SharedPreferences sharedPreferences = MultiprocessSharedPreferences
            .getSharedPreferences(this, getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.KEY_CHECK_PACKAGE_NAME, name);
        editor.apply();
        ToastUtil.makeToast(this, "保存检测包名为" + name, ToastUtil.LENGTH_SHORT);
    }

    private void saveNeedToast(Boolean needToast) {
        MultiprocessSharedPreferences.setAuthority("com.wizard.userpricacyapicheck.provider");
        SharedPreferences sharedPreferences = MultiprocessSharedPreferences
            .getSharedPreferences(this, getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.KEY_NEED_SHOW_TOAST, needToast);
        editor.apply();
    }

}