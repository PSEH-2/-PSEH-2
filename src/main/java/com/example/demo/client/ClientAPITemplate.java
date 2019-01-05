package com.example.demo.client;

import com.example.demo.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClientAPITemplate {

  @Value("${client.api.endpoint}")
  private String CLIENT_API_ENDPOINT;

  @Value("${client.api.key}")
  private String apiKey;

  @Autowired private RestTemplate restTemplate;

  @Autowired ObjectMapper objectMapper;

  public ClientWeatherResponseList geClientResponseDetails(String city, String countryCode)
      throws IOException {

    UriComponentsBuilder uriComponentsBuilder =
        UriComponentsBuilder.fromUriString(CLIENT_API_ENDPOINT)
            .queryParam("q", String.format("%s,%s", city, countryCode))
            .queryParam("appid", apiKey);
    Map<String, Object> jsonResponse =
        (LinkedHashMap<String, Object>)
            restTemplate.getForObject(uriComponentsBuilder.toUriString(), Object.class);
    List<JSONObject> jsonObjects = (ArrayList<JSONObject>) jsonResponse.get("list");

    return createClientResponseDetailsObject(jsonObjects);
  }

  private ClientWeatherResponseList createClientResponseDetailsObject(List<JSONObject> jsonReaponse)
      throws IOException {
    List<ClientResponseDetails> clientResponseDetails = new ArrayList<>();
    for (Map<String, Object> jsonObject : jsonReaponse) {
      Weather weather = createWeatherObject(jsonObject);
      Clouds clouds =
          objectMapper.readValue(
              objectMapper.writeValueAsString(jsonObject.get("clouds")), Clouds.class);
      Main main =
          objectMapper.readValue(
              objectMapper.writeValueAsString(jsonObject.get("main")), Main.class);
      long epochTime = objectMapper.readValue(
              objectMapper.writeValueAsString(jsonObject.get("dt")), Long.class);
      clientResponseDetails.add(ClientResponseDetails.builder().cloudsList(clouds).mainList(main).weathers(weather).epochTime(epochTime).build());
    }
    return ClientWeatherResponseList.builder().clientResponseDetails(clientResponseDetails).build();
  }

  public Weather createWeatherObject(Map<String, Object> objectMap) throws IOException {
    List<Weather> stringObject =
        (ArrayList<Weather>)
            objectMapper.readValue(
                objectMapper.writeValueAsString(objectMap.get("weather")), Object.class);

    if (stringObject.isEmpty()) {
      return null;
    }
    return objectMapper.readValue(objectMapper.writeValueAsString(stringObject.get(0)),Weather.class);
  }
}
