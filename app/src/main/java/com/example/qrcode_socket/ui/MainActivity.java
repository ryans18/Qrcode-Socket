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
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.qrcode_socket.R;
import com.example.qrcode_socket.api.ApiService;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author : Ryans
 * Date : 2021/7/30
 * Introduction :
 */
public class MainActivity extends AppCompatActivity{

    private TextView mWelcome;
    private Button mBtnScan;
    private Button mBtnLogin;
    private String qrMsg = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcome = findViewById(R.id.welcome);
        mBtnScan = findViewById(R.id.scan);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.server_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        mBtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionUtils.permission(PermissionConstants.CAMERA).callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                        ActivityUtils.startActivityForResult(MainActivity.this,intent, 1000);
                        LogUtils.i("start QR Activity");
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("无权限");
                    }
                }).request();
            }
        });
        mBtnLogin = findViewById(R.id.login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnLogin.setVisibility(View.GONE);
                String token = SPUtils.getInstance().getString("TOKEN", "");
                LogUtils.i("token: ","Bearer " +token );
                service.qrCodeMsg("Bearer " +token, qrMsg).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Object body = response.body();
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        LogUtils.e(t.getMessage());
                    }
                });
            }
        });
        String token = SPUtils.getInstance().getString("TOKEN", "");
        service.getUser("Bearer " + token).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                Map<String, Object> body = response.body();
                String username = (String)body.get("username");
                mWelcome.setText(getString(R.string.welcome_you) + username);
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                LogUtils.e(t.getMessage());
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
                if (content.contains("QR:"))  {
                    String[] split = content.split("QR:");
                    if (!StringUtils.isEmpty(split[1])) {
                        qrMsg = split[1];
                        mBtnLogin.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
