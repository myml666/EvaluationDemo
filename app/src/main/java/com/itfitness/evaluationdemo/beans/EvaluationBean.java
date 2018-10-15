package com.itfitness.evaluationdemo.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: EvaluationDemo
 * @Package: com.itfitness.evaluationdemo.beans
 * @ClassName: EvaluationBean
 * @Description: 发表评价Bean，用于存储不同商品的评价
 * @Author: LML
 * @CreateDate: 2018/10/11 17:11
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/11 17:11
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class EvaluationBean {
    private int evaluatinType=1;//默认好评
    private String evaluationContent;//评价内容
    private List<File> evaluationImages;//评价图片集合

    public EvaluationBean() {
        if(evaluationImages==null){
            evaluationImages=new ArrayList<>();
        }
    }

    public int getEvaluatinType() {
        return evaluatinType;
    }

    public void setEvaluatinType(int evaluatinType) {
        this.evaluatinType = evaluatinType;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public List<File> getEvaluationImages() {
        return evaluationImages;
    }

    public void setEvaluationImages(List<File> evaluationImages) {
        this.evaluationImages = evaluationImages;
    }

    @Override
    public String toString() {
        return "EvaluationBean{" +
                "evaluatinType=" + evaluatinType +
                ", evaluationContent='" + evaluationContent + '\'' +
                ", evaluationImages=" + evaluationImages +
                '}';
    }
}
