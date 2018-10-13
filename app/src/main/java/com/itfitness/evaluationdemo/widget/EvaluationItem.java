package com.itfitness.evaluationdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itfitness.evaluationdemo.R;


/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.widget
 * @ClassName: EvaluationView
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/9/27 14:56
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/9/27 14:56
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class EvaluationItem extends RelativeLayout {

    private View mView;
    private ImageView myModuleViewEvaluationImg;
    private TextView myModuleViewEvaluationTv;
    private Context mContext;
    private int mSelectImage;
    private int mUnSelectImage;
    private int mSelectTextColor;
    private int mUnSelectTextColor;
    public EvaluationItem(Context context) {
        this(context,null);
    }

    public EvaluationItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EvaluationItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = View.inflate(context, R.layout.view_evaluationitem, this);
        mContext=context;
        initDefaultDatas();
        initView();
        initAttr(attrs);
    }

    private void initDefaultDatas() {
        mSelectImage= R.drawable.ic_haoping_select;
        mUnSelectImage=R.drawable.ic_haoping_unselect;
        mSelectTextColor= Color.parseColor("#ff7043");
        mUnSelectTextColor= Color.parseColor("#bfbfbf");
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.EvaluationItem);
        if(typedArray!=null){
            mSelectImage = typedArray.getResourceId(R.styleable.EvaluationItem_selectImage, R.drawable.ic_haoping_select);
            mUnSelectImage = typedArray.getResourceId(R.styleable.EvaluationItem_unSelectImage, R.drawable.ic_haoping_unselect);
            mSelectTextColor = typedArray.getResourceId(R.styleable.EvaluationItem_selectTextColor, R.color.colorEvaluationSelect);
            mUnSelectTextColor = typedArray.getResourceId(R.styleable.EvaluationItem_unSelectTextColor,R.color.colorEvaluationUnSelect);
            myModuleViewEvaluationImg.setImageResource(mUnSelectImage);
            myModuleViewEvaluationTv.setTextColor(mContext.getResources().getColor(mUnSelectTextColor));
            String string = typedArray.getString(R.styleable.EvaluationItem_text);
            myModuleViewEvaluationTv.setText(string);
            typedArray.recycle();
        }
    }
    public void select(){
        myModuleViewEvaluationImg.setImageResource(mSelectImage);
        myModuleViewEvaluationTv.setTextColor(mContext.getResources().getColor(mSelectTextColor));

    }
    public void unSelect(){
        myModuleViewEvaluationImg.setImageResource(mUnSelectImage);
        myModuleViewEvaluationTv.setTextColor(mContext.getResources().getColor(mUnSelectTextColor));

    }
    private void initView() {
        myModuleViewEvaluationImg = (ImageView) mView.findViewById(R.id.my_module_view_evaluation_img);
        myModuleViewEvaluationTv = (TextView) mView.findViewById(R.id.my_module_view_evaluation_tv);
    }
}
