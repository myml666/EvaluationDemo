package com.itfitness.evaluationdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.itfitness.evaluationdemo.R;

/**
 * @ProjectName: EvaluationDemo
 * @Package: com.itfitness.evaluationdemo.widget
 * @ClassName: EvaluationChioceImageItem
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/10/13 12:33
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/13 12:33
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class EvaluationChioceImageItem extends RelativeLayout implements View.OnClickListener{
    private ImageView itemRegularevaluationImgAddimage;
    private ImageView itemRegularevaluationImgDeleteimage;
    private OnChildClickListener onChildClickListener;
    private Context mContext;
    public OnChildClickListener getOnChildClickListener() {
        return onChildClickListener;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public EvaluationChioceImageItem(Context context) {

        this(context,null);
    }

    public EvaluationChioceImageItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        mContext=context;
    }

    public EvaluationChioceImageItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_evaluationchoicwimageitem,this);
        initView();
        initListener();
    }

    private void initListener() {
        itemRegularevaluationImgAddimage.setOnClickListener(this);
        itemRegularevaluationImgDeleteimage.setOnClickListener(this);
    }

    private void initView() {
        itemRegularevaluationImgAddimage = (ImageView) findViewById(R.id.item_regularevaluation_img_addimage);
        itemRegularevaluationImgDeleteimage = (ImageView) findViewById(R.id.item_regularevaluation_img_deleteimage);
    }
    public void setImage(String imagePath){
        Glide.with(mContext).load(imagePath).into(itemRegularevaluationImgAddimage);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_regularevaluation_img_addimage:
                onChildClickListener.onImageClick(this);
                break;
            case R.id.item_regularevaluation_img_deleteimage:
                onChildClickListener.onDeleteImageClick(this);
                break;
        }
    }

    public interface OnChildClickListener{
        void onImageClick(View parent);
        void onDeleteImageClick(View parent);
    }
}
