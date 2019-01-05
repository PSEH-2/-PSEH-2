package com.example.demo.serviceImpl;

import com.example.demo.client.ClientAPITemplate;
import com.example.demo.model.ClientResponseDetails;
import com.example.demo.model.ClientWeatherResponseList;
import com.example.demo.model.WeatherResponseDetails;
import com.example.demo.service.WeatherService;
import com.example.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

  @Autowired ClientAPITemplate clientAPITemplate;

  @Override
  public List<WeatherResponseDetails> getWeatherDetails(String city, String countryCode)
      throws IOException, ParseException {
    ClientWeatherResponseList clientResponseDetails =
        clientAPITemplate.geClientResponseDetails(city, countryCode);
    List<WeatherResponseDetails> responseList = new ArrayList<>();
    int numberOfDays = 1;
    for (ClientResponseDetails clientResponseDetails1 :
        clientResponseDetails.getClientResponseDetails()) {
      WeatherResponseDetails weatherResponseDetails = null;
      if ((weatherResponseDetails = getWeatherInfo(clientResponseDetails1)) != null) {
        numberOfDays++;
        responseList.add(weatherResponseDetails);
      }
      if(numberOfDays > 3){
          break;
      }
    }
    return responseList;
  }

  private WeatherResponseDetails getWeatherInfo(ClientResponseDetails clientResponseDetails)
      throws ParseException {
    if (!Utils.dateDiff(clientResponseDetails.getEpochTime())) {
      return null;
    }
    if (Utils.fahrenheitToCelsius(clientResponseDetails.getMainList().getTemp()) > 40) {
        return WeatherResponseDetails.builder().message("Use sunscreen lotion").build();
    } else {
        return WeatherResponseDetails.builder().message("Carry umbrella").build();
    }
  }
}
