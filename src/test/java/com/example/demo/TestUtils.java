package com.example.demo;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {

    public static Map<String,Object> getAPIResponse(){
        Map<String,Object> responseList = new LinkedHashMap<>();
        responseList.put("list",jsonObjects());
        return responseList;
    }

    public static List<JSONObject> jsonObjects(){
        JSONObject finalResponse = new JSONObject();
        List<JSONObject> weatherList = new ArrayList<>();
        JSONObject w1 = new JSONObject();
        w1.put("id",400);
        w1.put("main","Rain");
        w1.put("description","light rain");
        w1.put("icon","10n");
        weatherList.add(w1);
        finalResponse.put("weather",weatherList);

        JSONObject clouds =  new JSONObject();
        clouds.put("all",10);
        finalResponse.put("clouds",clouds);

        JSONObject main = new JSONObject();
        main.put("temp",234.90);
        main.put("temp_min",200);
        main.put("temp_max",300);
        main.put("pressure",12);
        main.put("sea_level",1025);
        main.put("grnd_level",12);
        main.put("humidity",12);
        main.put("temp_kf",-90);
        finalResponse.put("main",main);

        finalResponse.put("dt",1546689600);

        weatherList.add(finalResponse);

        return weatherList;

    }
}
