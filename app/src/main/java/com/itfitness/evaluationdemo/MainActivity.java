package com.itfitness.evaluationdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itfitness.evaluationdemo.api.Api;
import com.itfitness.evaluationdemo.base.BaseActivity;
import com.itfitness.evaluationdemo.beans.EvaluationBean;
import com.itfitness.evaluationdemo.beans.ResultBean;
import com.itfitness.evaluationdemo.utils.ChoiceImageUtils;
import com.itfitness.evaluationdemo.utils.FileUtil;
import com.itfitness.evaluationdemo.utils.RetrofitUtils;
import com.itfitness.evaluationdemo.widget.EvaluationChoiceImageView;
import com.itfitness.evaluationdemo.widget.EvaluationView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MainActivity extends BaseActivity {
    private RecyclerView activityMainRecycleOrderlist;
    private BaseQuickAdapter<String,BaseViewHolder> mBaseQuickAdapter;
    private List<String> mTempDatas;//模拟订单数据
    private List<EvaluationBean> evaluationBeans;
    private RxPermissions mRxPermissions;
    private EvaluationChoiceImageView mTempEvaluationChoiceImageView;//存放临时的EvaluationChoiceImageView
    private int mTempPosition;//存放选择的商品position
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
        if(mRxPermissions==null){
            mRxPermissions=new RxPermissions(this);
        }
        if(evaluationBeans==null){
            evaluationBeans=new ArrayList<>();
        }
        if(mTempDatas==null){
            mTempDatas=new ArrayList<>();
            for (int x=0;x<4;x++){
                mTempDatas.add(""+x);
            }
        }
        for(int x=0;x<mTempDatas.size();x++){
            EvaluationBean evaluationBean=new EvaluationBean();
            evaluationBean.setEvaluatinType(1);
            evaluationBeans.add(evaluationBean);
        }
        initAdapter();
    }

    private void initAdapter() {
        mBaseQuickAdapter=new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_regularevaluation,mTempDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                //获取每个Item的position，用于对应evaluationBeans中的各个商品对应的EvaluationBean
                final int itemposition=mBaseQuickAdapter.getData().indexOf(item);
                ImageView itemRegularevaluationImgOrderimg=helper.getView(R.id.item_regularevaluation_img_orderimg);
                EditText itemRegularevaluationEtContent=helper.getView(R.id.item_regularevaluation_et_content);
                EvaluationView itemRegularevaluationEvaluatinview=helper.getView(R.id.item_regularevaluation_evaluatinview);
                final EvaluationChoiceImageView itemRegularevaluationEvaluationchoiceimageview=helper.getView(R.id.item_regularevaluation_evaluationchoiceimageview);
                Glide.with(MainActivity.this).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3562318354,3929854534&fm=26&gp=0.jpg").into(itemRegularevaluationImgOrderimg);
                //这里通过监听事件将EditText中的数据传到EvaluationBean中
                itemRegularevaluationEtContent.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        evaluationBeans.get(itemposition).setEvaluationContent(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                itemRegularevaluationEvaluatinview.setmEvaluationSelectListener(new EvaluationView.OnEvaluationSelectListener() {
                    @Override
                    public void onEvaluationSelect(int evaluationType) {
                        evaluationBeans.get(itemposition).setEvaluatinType(evaluationType);
                    }
                });
                itemRegularevaluationEvaluationchoiceimageview.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
                    @Override
                    public void onClickAddImage() {
                        Toast.makeText(mContext, ""+itemposition, Toast.LENGTH_SHORT).show();
                        //这里将EvaluationChoiceImageView存到临时变量中好对不同的EvaluationChoiceImageView添加图片
                        mTempEvaluationChoiceImageView=itemRegularevaluationEvaluationchoiceimageview;
                        mTempPosition=itemposition;
                        choiceImage();
                    }
                });
                itemRegularevaluationEvaluationchoiceimageview.setOnClickDeleteImageListener(new EvaluationChoiceImageView.OnClickDeleteImageListener() {
                    @Override
                    public void onClickDeleteImage(int position) {
                        evaluationBeans.get(itemposition).getEvaluationImages().remove(position);
                    }
                });
                itemRegularevaluationEvaluationchoiceimageview.setOnClickImageListener(new EvaluationChoiceImageView.OnClickImageListener() {
                    @Override
                    public void onClickImage(int position) {
                        Intent intent=new Intent(MainActivity.this,SeePhotoActivity.class);
                        intent.putExtra("photoPath",evaluationBeans.get(itemposition).getEvaluationImages().get(position).getAbsolutePath());
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }
                });
            }
        };
        activityMainRecycleOrderlist.setAdapter(mBaseQuickAdapter);
    }

    @SuppressLint("CheckResult")
    private void choiceImage(){
        mRxPermissions
                .requestEachCombined(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            ChoiceImageUtils.choiceImage(MainActivity.this);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            Toast.makeText(MainActivity.this, "您已拒绝权限申请", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "您已拒绝权限申请，请前往设置>应用管理>权限管理打开权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ChoiceImageUtils.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            File fileByUri = FileUtil.getFileByUri(Matisse.obtainResult(data).get(0), this);
//            压缩文件
            Luban.with(this)
                    .load(fileByUri)
                    .ignoreBy(100)
                    .filter(new CompressionPredicate() {
                        @Override
                        public boolean apply(String path) {
                            return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                        }
                    })
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {
                            evaluationBeans.get(mTempPosition).getEvaluationImages().add(0,file);
                            mTempEvaluationChoiceImageView.addImage(file.getAbsolutePath());
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                    }).launch();
        }
    }
    private void initView() {
        setTitle("发布评价");
        setRightTitle(true, "发布", new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                for (EvaluationBean evaluationBean:evaluationBeans){
                    Log.e("测试",evaluationBean.toString());
                    HashMap<String,String> parama=new HashMap<>();
                    parama.put("evaluationType",evaluationBean.getEvaluatinType()+"");
                    parama.put("evaluationContent",evaluationBean.getEvaluationContent());
                    HashMap<String, RequestBody> pics=new HashMap<>();
                    for (File file:evaluationBean.getEvaluationImages()){
                        pics.put(file.getName(),RequestBody.create(MediaType.parse("image/*"), file));
                    }
                    RetrofitUtils.getInstance().getApiServier(Api.class)
                            .submitEvaluation(parama,pics)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResultBean>() {
                                @Override
                                public void accept(ResultBean resultBean) throws Exception {

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
                }
            }
        });
        activityMainRecycleOrderlist = (RecyclerView) findViewById(R.id.activity_main_recycle_orderlist);
        activityMainRecycleOrderlist.setLayoutManager(new LinearLayoutManager(this));
    }
}
