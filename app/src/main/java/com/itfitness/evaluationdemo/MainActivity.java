package com.itfitness.evaluationdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itfitness.evaluationdemo.base.BaseActivity;
import com.itfitness.evaluationdemo.widget.EvaluationChoiceImageView;
import com.itfitness.evaluationdemo.widget.EvaluationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView activityMainRecycleOrderlist;
    private BaseQuickAdapter<String,BaseViewHolder> mBaseQuickAdapter;
    private List<String> mTempDatas;//模拟订单数据
    @Override
    protected boolean isHaveTitle() {
        return true;
    }
    @Override
    protected int LayoutRes() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDatas();
    }

    private void initDatas() {
//        添加测试数据（简单的添加图片地址模拟订单）
        if(mTempDatas==null){
            mTempDatas=new ArrayList<>();
            for (int x=0;x<4;x++){
                mTempDatas.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3562318354,3929854534&fm=26&gp=0.jpg");
            }
        }
        initAdapter();
    }

    private void initAdapter() {
        mBaseQuickAdapter=new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_regularevaluation,mTempDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int position=mBaseQuickAdapter.getData().indexOf(item);
                ImageView itemRegularevaluationImgOrderimg=helper.getView(R.id.item_regularevaluation_img_orderimg);
                EditText itemRegularevaluationEtContent=helper.getView(R.id.item_regularevaluation_et_content);
                ImageView itemRegularevaluationImgAddimage=helper.getView(R.id.item_regularevaluation_img_addimage);
                EvaluationView itemRegularevaluationEvaluatinview=helper.getView(R.id.item_regularevaluation_evaluatinview);
                ImageView itemRegularevaluationImgDeleteimage=helper.getView(R.id.item_regularevaluation_img_deleteimage);
                final EvaluationChoiceImageView itemRegularevaluationEvaluationchoiceimageview=helper.getView(R.id.item_regularevaluation_evaluationchoiceimageview);
                Glide.with(MainActivity.this).load(item).into(itemRegularevaluationImgOrderimg);
                itemRegularevaluationEvaluatinview.setmEvaluationSelectListener(new EvaluationView.OnEvaluationSelectListener() {
                    @Override
                    public void onEvaluationSelect(int evaluationType) {
                        Toast.makeText(MainActivity.this, evaluationType+"", Toast.LENGTH_SHORT).show();
                    }
                });
                itemRegularevaluationEvaluationchoiceimageview.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
                    @Override
                    public void onClickAddImage() {
                        itemRegularevaluationEvaluationchoiceimageview.addImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3498118633,3420624807&fm=26&gp=0.jpg");
                    }
                });
                itemRegularevaluationEvaluationchoiceimageview.setOnClickDeleteImageListener(new EvaluationChoiceImageView.OnClickDeleteImageListener() {
                    @Override
                    public void onClickDeleteImage(int position) {
                        Toast.makeText(MainActivity.this, "删除"+position, Toast.LENGTH_SHORT).show();
                    }
                });
                itemRegularevaluationEvaluationchoiceimageview.setOnClickImageListener(new EvaluationChoiceImageView.OnClickImageListener() {
                    @Override
                    public void onClickImage(int position) {
                        Toast.makeText(MainActivity.this, "查看"+position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        activityMainRecycleOrderlist.setAdapter(mBaseQuickAdapter);
    }


    private void initView() {
        setTitle("发布评价");
        setRightTitle(true, "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        activityMainRecycleOrderlist = (RecyclerView) findViewById(R.id.activity_main_recycle_orderlist);
        activityMainRecycleOrderlist.setLayoutManager(new LinearLayoutManager(this));
    }
}
