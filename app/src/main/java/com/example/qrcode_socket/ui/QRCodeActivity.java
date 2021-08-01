package com.example.qrcode_socket.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.qrcode_socket.R;
import com.king.zxing.CaptureActivity;
import com.king.zxing.DecodeFormatManager;
import com.king.zxing.camera.FrontLightMode;

public class QRCodeActivity extends CaptureActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_qr_code;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
              requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        //取消标题
        getCaptureHelper()
                .playBeep(false)//播放音效
                .vibrate(true)//震动
                .supportVerticalCode(false)//支持扫垂直条码，建议有此需求时才使用。
                .fullScreenScan(false)
                .decodeFormats(DecodeFormatManager.QR_CODE_FORMATS)//设置只识别二维码会提升速度
//                .framingRectRatio(0.8f)//设置识别区域比例，范围建议在0.625 ~ 1.0之间。非全屏识别时才有效
//                .framingRectVerticalOffset(0)//设置识别区域垂直方向偏移量，非全屏识别时才有效
//                .framingRectHorizontalOffset(0)//设置识别区域水平方向偏移量，非全屏识别时才有效
                .frontLightMode(FrontLightMode.AUTO)//设置闪光灯模式
                .tooDarkLux(45f)//设置光线太暗时，自动触发开启闪光灯的照度值
                .brightEnoughLux(100f)//设置光线足够明亮时，自动触发关闭闪光灯的照度值
                .supportLuminanceInvert(true);//是否支持识别反色码（黑白反色的码），增加识别率
    }

}