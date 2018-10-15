package com.itfitness.evaluationdemo.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.utils
 * @ClassName: ChoiceImageUtils
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/9/4 17:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/9/4 17:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class ChoiceImageUtils {
    public static final int REQUEST_CODE_CHOOSE=1;
    public static void choiceImage(Activity activity){
        Matisse.from(activity)
                .choose(MimeType.ofImage())
                .countable(true)
                .capture(true) //使用拍照功能
                .captureStrategy(new CaptureStrategy(true, "com.itfitness.evaluationdemo")) //是否拍照功能，并设置拍照后图片的保存路径
                .maxSelectable(1)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.8f)
                .forResult(REQUEST_CODE_CHOOSE);
    }
}
