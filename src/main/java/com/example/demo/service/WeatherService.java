package com.example.demo.service;

import com.example.demo.model.WeatherResponseDetails;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface WeatherService {
    List<WeatherResponseDetails> getWeatherDetails(String city, String countryCode) throws IOException, ParseException;
}
