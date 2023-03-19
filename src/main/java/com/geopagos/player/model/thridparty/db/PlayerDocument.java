package com.geopagos.player.model.thridparty.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("player")
@EqualsAndHashCode
public class PlayerDocument {

    @Id
    private String id;
    private String name;
    private String gender;
    private Integer ability;
    private Integer strength;
    private Integer speed;
    private Integer reactionTime;

}
