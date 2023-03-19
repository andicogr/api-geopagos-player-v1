package com.geopagos.player.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String id;
    private String name;
    private Gender gender;
    private Integer ability;
    private Integer strength;
    private Integer speed;
    private Integer reactionTime;

}
