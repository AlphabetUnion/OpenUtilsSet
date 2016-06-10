package com.alphabet.jack.weather_ad.domain;

import java.util.List;

/**
 * Created by Jack on 2016/6/8.
 */
public class TotalResult {

    public String resultcode;  public String reason;
    public List<Result> resultList;
    public String error_code;

//    @Override
//    public String toString() {
//        return "TotalResult [resultcode="+resultcode+"reason="+reason+"result="+resultList+"    " +
//                "]";
//    }
}
