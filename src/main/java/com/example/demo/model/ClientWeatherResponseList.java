package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ClientWeatherResponseList {
    private List<ClientResponseDetails> clientResponseDetails;
}
