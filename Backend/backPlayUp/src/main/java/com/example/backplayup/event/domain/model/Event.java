package com.example.backplayup.event.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Event {

    private Long id;
    private String title;
    private String description;
    private String photo;
    private int points;



}
