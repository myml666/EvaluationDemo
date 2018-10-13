package com.itfitness.evaluationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.itfitness.evaluationdemo.base.BaseActivity;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.activitys
 * @ClassName: SeePhotoActivity
 * @Description: 用于查看图片
 * @Author: LML
 * @CreateDate: 2018/10/8 10:11
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/8 10:11
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class SeePhotoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView myModuleActivitySeephotoImgClose;
    private PhotoView myModuleActivitySeephotoPvImage;

    @Override
    protected boolean isHaveTitle() {
        return false;
    }

    @Override
    protected int LayoutRes() {
        return R.layout.activity_seephoto;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initDatas();
    }

    private void initDatas() {
        String photoPath = getIntent().getStringExtra("photoPath");
        Glide.with(this).load(photoPath).into(myModuleActivitySeephotoPvImage);
    }

    private void initListener() {
        myModuleActivitySeephotoImgClose.setOnClickListener(this);
    }

    private void initView() {
        myModuleActivitySeephotoImgClose = (ImageView) findViewById(R.id.activity_seephoto_img_close);
        myModuleActivitySeephotoPvImage = (PhotoView) findViewById(R.id.activity_seephoto_pv_image);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.activity_seephoto_img_close) {
            onBackPressed();
        }
    }
}
