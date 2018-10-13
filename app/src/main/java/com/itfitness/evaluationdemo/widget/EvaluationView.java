package com.itfitness.evaluationdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.itfitness.evaluationdemo.R;

/**
 * @ProjectName: EvaluationDemo
 * @Package: com.itfitness.evaluationdemo.widget
 * @ClassName: EvaluationView2
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/10/11 17:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/11 17:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class EvaluationView extends RelativeLayout implements View.OnClickListener{
    private EvaluationItem itemRegularevaluationHaoping;
    private EvaluationItem itemRegularevaluationZhongping;
    private EvaluationItem itemRegularevaluationChaping;
    private OnEvaluationSelectListener mEvaluationSelectListener;

    public OnEvaluationSelectListener getmEvaluationSelectListener() {
        return mEvaluationSelectListener;
    }

    public void setmEvaluationSelectListener(OnEvaluationSelectListener mEvaluationSelectListener) {
        this.mEvaluationSelectListener = mEvaluationSelectListener;
    }

    private int mEvaluationType=1;
    public EvaluationView(Context context) {
        this(context,null);
    }

    public EvaluationView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EvaluationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_evaluation,this);
        initView();
        initListener();
    }

    private void initListener() {
        itemRegularevaluationChaping.setOnClickListener(this);
        itemRegularevaluationHaoping.setOnClickListener(this);
        itemRegularevaluationZhongping.setOnClickListener(this);
    }

    private void initView() {
        itemRegularevaluationHaoping = (EvaluationItem) findViewById(R.id.item_regularevaluation_haoping);
        itemRegularevaluationZhongping = (EvaluationItem) findViewById(R.id.item_regularevaluation_zhongping);
        itemRegularevaluationChaping = (EvaluationItem) findViewById(R.id.item_regularevaluation_chaping);
        itemRegularevaluationHaoping.select();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_regularevaluation_haoping:
                itemRegularevaluationHaoping.select();
                itemRegularevaluationZhongping.unSelect();
                itemRegularevaluationChaping.unSelect();
                mEvaluationType=1;
                break;
            case R.id.item_regularevaluation_zhongping:
                itemRegularevaluationHaoping.unSelect();
                itemRegularevaluationZhongping.select();
                itemRegularevaluationChaping.unSelect();
                mEvaluationType=2;
                break;
            case R.id.item_regularevaluation_chaping:
                itemRegularevaluationHaoping.unSelect();
                itemRegularevaluationZhongping.unSelect();
                itemRegularevaluationChaping.select();
                mEvaluationType=3;
                break;
        }
        if(mEvaluationSelectListener!=null){
            mEvaluationSelectListener.onEvaluationSelect(mEvaluationType);
        }
    }

    public interface OnEvaluationSelectListener{
        void onEvaluationSelect(int evaluationType);
    }
}
