package com.alphabet.jack.weather_ad.utils;

/**
 * Created by Jack on 2016/6/5.
 */
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HttpUtils {

    private static final String CHARSET = "UTF-8";
    public static String request(String url, ArrayList<NameValuePair> nameValuePairArrayList) throws IOException {
        return request(url,nameValuePairArrayList,CHARSET);
    }
    public static String request(String url, ArrayList<NameValuePair> nameValuePairArrayList, String charset) throws IOException {

        Log.i("MainActivity","Url是:" + url + "?" + nameValuePairArrayList);
        String result ;
        //客户端http协议
        DefaultHttpClient client = new DefaultHttpClient();

        //请求配置
        HttpPost post = new HttpPost(url);
        post.addHeader("charset",charset);
        post.setEntity(new UrlEncodedFormEntity(nameValuePairArrayList,charset));

        //http响应的结果
        HttpResponse httpResponse = client.execute(post);

        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

            HttpEntity entity = httpResponse.getEntity();
            InputStream is = entity.getContent();
            result =  convertStreamToString(is);
        }else {
            result = "error";
        }

        return result;



    }

    private static String convertStreamToString(InputStream isStream )  {
        InputStreamReader is = null;
        BufferedReader reader = null;
        StringBuffer sb = null;
        try {
             is = new InputStreamReader(isStream,CHARSET);
             reader = new BufferedReader(is,8);

            String line = null;
             sb = new StringBuffer();
            while ((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (reader != null){
                    reader.close();
                    }
                    if (is != null ){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

            }
        }

        return sb.toString();
    }

    /**
     * get请求方法
     * @param httpUrl
     * @return
     */
    public static String requestGet(String httpUrl) {
        //创建httpRequest对象
        HttpGet httpRequest = new HttpGet(httpUrl);
        String strResult = null;
        try {
            //取得HttpClient对象
            HttpClient httpclient = new DefaultHttpClient();
            //请求HttpClient，取得HttpResponse
            HttpResponse httpResponse = httpclient.execute(httpRequest);
            //请求成功
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //取得返回的字符串
                 strResult = EntityUtils.toString(httpResponse.getEntity());
//                resultText.setText(strResult);
            } else {
//                resultText.setText("请求错误!");
            }
        } catch (ClientProtocolException e) {
//            resultText.setText(e.getMessage().toString());
        } catch (IOException e) {
//            resultText.setText(e.getMessage().toString());
        } catch (Exception e) {
//            resultText.setText(e.getMessage().toString());
        }

        return strResult;
    }
}
