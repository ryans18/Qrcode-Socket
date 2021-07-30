package com.example.qrcode_socket.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.qrcode_socket.R;
import com.king.zxing.CaptureActivity;
import com.king.zxing.DecodeFormatManager;
import com.king.zxing.camera.FrontLightMode;

public class QRCodeActivity extends CaptureActivity {

    private Button mBtnBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {requestWindowFeature(Window.FEATURE_NO_TITLE);
        //鍙栨秷鐘舵�佹爮
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        //鍙栨秷鏍囬
        getCaptureHelper()
                .playBeep(false)//鎾斁闊虫晥
                .vibrate(true)//闇囧姩
                .supportVerticalCode(false)//鏀寔鎵瀭鐩存潯鐮侊紝寤鸿鏈夋闇�姹傛椂鎵嶄娇鐢ㄣ��
                .fullScreenScan(false)
                .decodeFormats(DecodeFormatManager.QR_CODE_FORMATS)//璁剧疆鍙瘑鍒簩缁寸爜浼氭彁鍗囬�熷害
//                .framingRectRatio(0.8f)//璁剧疆璇嗗埆鍖哄煙姣斾緥锛岃寖鍥村缓璁湪0.625 ~ 1.0涔嬮棿銆傞潪鍏ㄥ睆璇嗗埆鏃舵墠鏈夋晥
//                .framingRectVerticalOffset(0)//璁剧疆璇嗗埆鍖哄煙鍨傜洿鏂瑰悜鍋忕Щ閲忥紝闈炲叏灞忚瘑鍒椂鎵嶆湁鏁�
//                .framingRectHorizontalOffset(0)//璁剧疆璇嗗埆鍖哄煙姘村钩鏂瑰悜鍋忕Щ閲忥紝闈炲叏灞忚瘑鍒椂鎵嶆湁鏁�
                .frontLightMode(FrontLightMode.AUTO)//璁剧疆闂厜鐏ā寮�
                .tooDarkLux(45f)//璁剧疆鍏夌嚎澶殫鏃讹紝鑷姩瑙﹀彂寮�鍚棯鍏夌伅鐨勭収搴﹀��
                .brightEnoughLux(100f)//璁剧疆鍏夌嚎瓒冲鏄庝寒鏃讹紝鑷姩瑙﹀彂鍏抽棴闂厜鐏殑鐓у害鍊�
                .supportLuminanceInvert(true);//鏄惁鏀寔璇嗗埆鍙嶈壊鐮侊紙榛戠櫧鍙嶈壊鐨勭爜锛夛紝澧炲姞璇嗗埆鐜�
    }

}