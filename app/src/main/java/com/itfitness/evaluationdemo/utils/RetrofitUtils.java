package com.itfitness.evaluationdemo.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.utils
 * @ClassName: RetrofitForEShopUtils
 * @Description: 订单联网工具类
 * @Author: LML
 * @CreateDate: 2018/9/3 14:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/9/3 14:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class RetrofitUtils {
    private static final String  BASE_URL="https://www.jianshu.com";//替换为自己的服务器地址
    private volatile static RetrofitUtils mRetrofitUtils;
    private Retrofit mRetrofit;

    private RetrofitUtils(){

        initRetrofit();
    }

    public static RetrofitUtils getInstance(){
        if(mRetrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if(mRetrofitUtils==null){
                    mRetrofitUtils=new RetrofitUtils();
                }
            }
        }
        return mRetrofitUtils;
    }


    private void initRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String msg = URLDecoder.decode(message, "utf-8");
                    Log.i("OkHttpInterceptor-----", msg);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.i("OkHttpInterceptor-----", message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**okhttp默认时间10秒 请求时间较长时，重新设置下  **/
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public <T> T getApiServier(@NonNull Class<T> reqServer){
        return mRetrofit.create(reqServer);
    }
}
