package com.example.qrcode_socket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.qrcode_socket.R;
import com.king.zxing.Intents;

/**
 * Author : Ryans
 * Date : 2021/7/30
 * Introduction :
 */
public class MainActivity extends AppCompatActivity {

    private TextView mWelcome;
    private Button mBtnScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcome = findViewById(R.id.welcome);
        mBtnScan = findViewById(R.id.scan);
        mBtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionUtils.permission(PermissionConstants.CAMERA).callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(MainActivity.this, QRCodeActivity.class);
                        ActivityUtils.startActivityForResult(MainActivity.this,intent, 1000);
                        LogUtils.i("start QR Activity");
                    }

                    @Override
                    public void onDenied() {

                    }
                }).request();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Intents.Scan.RESULT);
                LogUtils.i("QR code content:", content);
                long id;
                try {
                    id = Long.parseLong(content);
                } catch (NumberFormatException e) {
                    ToastUtils.showLong("無法解析");
                    return;
                }
            }
        }
    }
}
