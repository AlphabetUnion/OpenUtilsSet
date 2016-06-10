package com.alphabet.jack.weather_ad;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import com.alphabet.jack.weather_ad.utils.HttpUtils;

import junit.framework.TestResult;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

     public void testHttp(){
           String appKey = "e339f616158308bb314bed6e2041be67";

         String cityName = "上海";
         String url = "http://v.juhe.cn/weather/index"+ "?"+
                 "format=2" + "&" + "cityname="+ cityName + "&" + "key=" + appKey;
         HttpUtils.requestGet(url);
     }
}