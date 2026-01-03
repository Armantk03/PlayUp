package com.example.backplayup.event.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private Long id;
    private String title;
    private String description;
    private String photo;
    private int points;



}
