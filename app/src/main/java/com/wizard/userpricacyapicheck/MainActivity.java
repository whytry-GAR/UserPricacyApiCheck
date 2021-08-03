package com.wizard.userpricacyapicheck;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etv_package_name);
        btnConfirm = findViewById(R.id.btn_confirm);
        initCheckPackageName(editText);
        btnConfirm.setOnClickListener(v -> {
            String name = "";
            if (editText.getText() != null) {
                name = editText.getText().toString();
            }
            saveCheckPackageName(name);
        });
    }

    private void initCheckPackageName(EditText editText) {
        MultiprocessSharedPreferences.setAuthority("com.wizard.userpricacyapicheck.provider");
        SharedPreferences sharedPreferences = MultiprocessSharedPreferences
            .getSharedPreferences(this, getPackageName(), Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("check_package_name", "");
        if (name.length() > 0) {
            editText.setText(name);
            editText.setSelection(name.length());
        }
    }


    private void saveCheckPackageName(String name) {
        MultiprocessSharedPreferences.setAuthority("com.wizard.userpricacyapicheck.provider");
        SharedPreferences sharedPreferences = MultiprocessSharedPreferences
            .getSharedPreferences(this, getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check_package_name", name);
        editor.apply();
        ToastUtil.makeToast(this, "保存检测包名为" + name, ToastUtil.LENGTH_SHORT);
    }
}