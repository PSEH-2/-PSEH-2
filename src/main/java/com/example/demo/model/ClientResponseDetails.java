package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class ClientResponseDetails {

    private long epochTime;
    private Weather weathers;
    private Clouds cloudsList;
    private Main mainList;
}
