package com.itfitness.evaluationdemo.api;

import com.itfitness.evaluationdemo.beans.ResultBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * @ProjectName: EvaluationDemo
 * @Package: com.itfitness.evaluationdemo.api
 * @ClassName: Api
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/10/15 11:02
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/15 11:02
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public interface Api {
    @Multipart
    @POST("aaa/bbb/submitEvaluation")
    Observable<ResultBean> submitEvaluation(@QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> pic);
}
