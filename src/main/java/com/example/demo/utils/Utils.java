package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {


    public static boolean dateDiff(Long givenEpochTime) throws ParseException {
        Date systemDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date givenDate = new Date(givenEpochTime*1000);
        Date current = sdf.parse(sdf.format(systemDate));
        long diffInDays = Math.abs(givenDate.getTime() - current.getTime());
        long diff = TimeUnit.DAYS.convert(diffInDays, TimeUnit.MILLISECONDS);
        return diff < 1 || diff> 4 ;
    }

    public static float fahrenheitToCelsius(Double temprature){
        return ((temprature.floatValue() - 32)*5)/9;
    }
}
