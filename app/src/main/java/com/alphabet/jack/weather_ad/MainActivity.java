package com.alphabet.jack.weather_ad;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alphabet.jack.weather_ad.utils.HttpUtils;
import com.alphabet.jack.weather_ad.domain.TotalResult;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

/**
 * 包含天气, Gson,okHttp
 * OKHttpDemo
 * AppKey : e339f616158308bb314bed6e2041be67
 */
public class MainActivity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON  = MediaType.parse("application/json; charset=utf-8");
    TextView resultcode;

    private ArrayList<NameValuePair> list = new ArrayList<>();
    private String appKey = "e339f616158308bb314bed6e2041be67";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getGet();

    }

    public void initViews(){
         resultcode =  (TextView) findViewById(R.id.resulcode);
    }
    /**
     * 获取url
     * @param cityName
     * @return
     */
    public  String getUrl(String cityName){
            String url = "http://v.juhe.cn/weather/index"+ "?"+
                "format=2" + "&" + "cityname="+ cityName + "&" + "key=" + appKey;
        return  url;
    }

    /**
     * get方法
     * 获取接口返回字段
     */
    public void getGet(){

        //这里要加网络判断, 不然会出现bug, 我这里没加
         new Thread(new Runnable() {
             @Override
             public void run() {
                 Gson gson = new Gson();
                 String result = HttpUtils.requestGet(getUrl("上海"));

                 TotalResult resultBean = gson.fromJson(result,TotalResult.class);
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 handler.obtainMessage(1,resultBean).sendToTarget();
             }
         }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    TotalResult resultBean = (TotalResult)msg.obj;
                    if (Integer.valueOf(resultBean.resultcode) == 200){
                        resultcode.setText(resultBean.resultcode);
                    }else {
                        Toast.makeText(MainActivity.this,"错误代码:" + resultBean.error_code,
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    /**
     * post
     * 获取接口数据
     */
    public void getPost(){
//        list.add(new BasicNameValuePair("format","2"));
//        list.add(new BasicNameValuePair("cityname",cityName));
//        list.add(new BasicNameValuePair("key",appKey));
        //            String result =  HttpUtils.request(getUrl("上海"),list);

    }

}
