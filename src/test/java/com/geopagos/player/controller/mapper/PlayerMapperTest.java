package com.geopagos.player.controller.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.geopagos.player.model.api.Gender;
import com.geopagos.player.model.api.PlayerRequest;
import com.geopagos.player.model.api.PlayerSearchRequest;
import com.geopagos.player.model.domain.Player;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PlayerMapperTest {

    private final PlayerMapper sut = new PlayerMapperImpl();

    @Test
    void GivenPlayerRequest_WhenMaps_ThenCorrect() {

        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setName("Test Name");
        playerRequest.setGender(Gender.MALE);
        playerRequest.setAbility(BigDecimal.valueOf(56));
        playerRequest.setStrength(BigDecimal.valueOf(99));
        playerRequest.setSpeed(BigDecimal.valueOf(66));
        playerRequest.setReactionTime(BigDecimal.valueOf(77));

        Player player = sut.fromRequest(playerRequest);

        assertEquals(playerRequest.getName(), player.getName());
        assertEquals(playerRequest.getGender().name(), player.getGender().name());
        assertEquals(playerRequest.getAbility().intValue(), player.getAbility());
        assertEquals(playerRequest.getStrength().intValue(), player.getStrength());
        assertEquals(playerRequest.getSpeed().intValue(), player.getSpeed());
        assertEquals(playerRequest.getReactionTime().intValue(), player.getReactionTime());

    }

    @Test
    void GivenPlayerSearchRequest_WhenMaps_ThenCorrect() {

        PlayerSearchRequest playerSearchRequest = new PlayerSearchRequest();
        playerSearchRequest.setName("Test Name");
        playerSearchRequest.setGender(Gender.MALE);

        Player player = sut.fromRequest(playerSearchRequest);

        assertEquals(playerSearchRequest.getName(), player.getName());
        assertEquals(playerSearchRequest.getGender().name(), player.getGender().name());

    }
}