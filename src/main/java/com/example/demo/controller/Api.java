package com.example.demo.controller;

import com.example.demo.model.WeatherResponseDetails;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class Api {

  @Autowired
  WeatherService weatherService;

  @GetMapping("weatherDetails")
  public List<WeatherResponseDetails> getWeatherResponseDetails(
      @RequestParam("city") String city, @RequestParam("countryCode") String countryCode)
          throws IOException, ParseException {
    return weatherService.getWeatherDetails(city, countryCode);
  }
}
