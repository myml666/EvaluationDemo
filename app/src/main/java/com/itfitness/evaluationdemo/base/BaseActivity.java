package com.itfitness.evaluationdemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itfitness.evaluationdemo.R;

/**
  *
  * @ProjectName:
  * @Package:        com.itfitness.evaluationdemo
  * @ClassName:      BaseActivity
  * @Description:    Activity基类
  * @Author:         LML
  * @CreateDate:     2018/8/27 13:55
  * @UpdateUser:     更新者：
  * @UpdateDate:     2018/8/27 13:55
  * @UpdateRemark:   更新说明：
  * @Version:        1.0
 */


public  abstract class BaseActivity extends AppCompatActivity {
    protected TextView mTitleTvTitle;
    protected ImageView mTitleImgBack;
    protected TextView mTitleTvRightTitle;
    //判断是否包含标题栏
    protected abstract boolean isHaveTitle();
    @LayoutRes
    protected abstract int LayoutRes();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutRes());
        if(isHaveTitle()){
            mTitleTvTitle = (TextView) findViewById(R.id.layout_title_tv_title);
            mTitleImgBack = (ImageView) findViewById(R.id.layout_title_img_back);
            mTitleTvRightTitle = (TextView) findViewById(R.id.layout_title_tv_right_title);
            mTitleImgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }
    /**
     * @method  setTitle
     * @description 描述一下方法的作用
     * @date: 2018/8/27 14:43
     * @author: LML
     * @param title 设置主标题
     * @return void
     */
    protected void setTitle(String title){
        if(isHaveTitle()){
            mTitleTvTitle.setText(title);
        }
    }
    /**
     * @method  setRightTitle
     * @description 描述一下方法的作用
     * @date: 2018/8/27 14:38
     * @author: LML
     * @param title 设置右标题显示文字
     * @param isShow 设置是否显示右标题
     * @return void
     */
    protected void setRightTitle(boolean isShow, String title, View.OnClickListener onClickListener){
        if(isHaveTitle()){
            if (isShow){
                mTitleTvRightTitle.setVisibility(View.VISIBLE);
                mTitleTvRightTitle.setText(title);
                mTitleTvRightTitle.setOnClickListener(onClickListener);
            }
        }
    }
    /**
     * @method  getStartActivityIntent
     * @description 用于返回跳转Activity的Intent
     * @date: 2018/8/27 17:32
     * @author: LML
     * @param clazz Activity的类名
     * @return android.content.Intent
     */
    protected Intent getStartActivityIntent(Class clazz){
        return new Intent(this,clazz);
    }
}
