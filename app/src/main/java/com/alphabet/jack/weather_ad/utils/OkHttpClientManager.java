package com.alphabet.jack.weather_ad.utils;

import android.os.Handler;
import android.os.Looper;
import android.service.carrier.CarrierMessagingService;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.internal.spdy.FrameReader;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;

/**
 * Created by Jack on 2016/6/5.
 */
public class OkHttpClientManager {
    private static OkHttpClientManager mInstance;
    private Handler mDelivery;
    private Gson mGson;
    public OkHttpClientManager(){

        OkHttpClient okHttpClient = new OkHttpClient();
        //cookie enabled
        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();


    }

    public static OkHttpClientManager getInstance(){
        if (mInstance == null){
            synchronized(OkHttpClientManager.class){
                if (mInstance == null){
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
        }
    private void _postAsnc(String url, final CarrierMessagingService.ResultCallback callback,
                           Map<String,String> params){

//        Param[] paramsArr =
    }
}
