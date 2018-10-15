package com.itfitness.evaluationdemo.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.itfitness.evaluationdemo.R;
import com.itfitness.evaluationdemo.SeePhotoActivity;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: EvaluationDemo
 * @Package: com.itfitness.evaluationdemo.widget
 * @ClassName: EvaluationChoiceImageView
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/10/12 9:44
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/12 9:44
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class EvaluationChoiceImageView extends RelativeLayout implements View.OnClickListener{
    private FlowLayout viewEvaluationchoiceimageFlowlayout;
    private ImageView viewEvaluationchoiceimageImgAdd;
    private Context mContext;
    private List<View> mFlowlayoutChilds;
    private OnClickAddImageListener onClickAddImageListener;
    private OnClickDeleteImageListener onClickDeleteImageListener;
    private OnClickImageListener onClickImageListener;
    private int maxChoicesImage=10;

    public int getMaxChoicesImage() {
        return maxChoicesImage;
    }

    public void setMaxChoicesImage(int maxChoicesImage) {
        this.maxChoicesImage = maxChoicesImage;
    }

    public OnClickAddImageListener getOnClickAddImageListener() {
        return onClickAddImageListener;
    }

    public void setOnClickAddImageListener(OnClickAddImageListener onClickAddImageListener) {
        this.onClickAddImageListener = onClickAddImageListener;
    }

    public OnClickDeleteImageListener getOnClickDeleteImageListener() {
        return onClickDeleteImageListener;
    }

    public void setOnClickDeleteImageListener(OnClickDeleteImageListener onClickDeleteImageListener) {
        this.onClickDeleteImageListener = onClickDeleteImageListener;
    }

    public OnClickImageListener getOnClickImageListener() {
        return onClickImageListener;
    }

    public void setOnClickImageListener(OnClickImageListener onClickImageListener) {
        this.onClickImageListener = onClickImageListener;
    }

    public EvaluationChoiceImageView(Context context) {
        this(context,null);
    }

    public EvaluationChoiceImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EvaluationChoiceImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        View.inflate(context,R.layout.view_evaluationchoiceimage,this);
        mFlowlayoutChilds=new ArrayList<>();
        initView();
    }

    private void initView() {
        viewEvaluationchoiceimageFlowlayout = (FlowLayout) findViewById(R.id.view_evaluationchoiceimage_flowlayout);
        viewEvaluationchoiceimageImgAdd = (ImageView) findViewById(R.id.view_evaluationchoiceimage_img_add);
        viewEvaluationchoiceimageImgAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_evaluationchoiceimage_img_add:
                onClickAddImageListener.onClickAddImage();
                break;
        }
    }
    public void addImage(String imagePath){
        if(mFlowlayoutChilds.size()+1>=maxChoicesImage){
            viewEvaluationchoiceimageImgAdd.setVisibility(GONE);
        }
        EvaluationChioceImageItem evaluationChioceImageItem=new EvaluationChioceImageItem(mContext);
        evaluationChioceImageItem.setOnChildClickListener(new EvaluationChioceImageItem.OnChildClickListener() {
            @Override
            public void onImageClick(View parent) {
//                Toast.makeText(mContext, "查看"+mFlowlayoutChilds.indexOf(parent), Toast.LENGTH_SHORT).show();
                onClickImageListener.onClickImage(mFlowlayoutChilds.indexOf(parent));
            }

            @Override
            public void onDeleteImageClick(View parent) {
//                Toast.makeText(mContext, "删除"+mFlowlayoutChilds.indexOf(parent), Toast.LENGTH_SHORT).show();
                onClickDeleteImageListener.onClickDeleteImage(mFlowlayoutChilds.indexOf(parent));
                mFlowlayoutChilds.remove(parent);
                viewEvaluationchoiceimageFlowlayout.removeView(parent);
                viewEvaluationchoiceimageImgAdd.setVisibility(VISIBLE);
            }
        });
        evaluationChioceImageItem.setImage(imagePath);
        mFlowlayoutChilds.add(0,evaluationChioceImageItem);
        viewEvaluationchoiceimageFlowlayout.addView(evaluationChioceImageItem,0);
    }
    //添加图片
    public interface OnClickAddImageListener{
        void onClickAddImage();
    }
    //查看图片
    public interface OnClickImageListener{
        void onClickImage(int position);
    }
    //删除图片
    public interface OnClickDeleteImageListener{
        void onClickDeleteImage(int position);
    }
}
