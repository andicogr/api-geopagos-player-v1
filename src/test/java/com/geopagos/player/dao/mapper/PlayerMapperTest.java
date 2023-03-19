package com.geopagos.player.dao.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.geopagos.player.model.domain.Player;
import com.geopagos.player.model.thridparty.db.PlayerDocument;

import org.junit.jupiter.api.Test;

class PlayerMapperTest {

    private final PlayerMapperDao sut = new PlayerMapperDaoImpl();

    @Test
    void GivenPlayerDocument_WhenMaps_ThenCorrect() {

        PlayerDocument playerDocument = new PlayerDocument();
        playerDocument.setId("uuid-test-test");
        playerDocument.setName("Test Name");
        playerDocument.setGender("MALE");
        playerDocument.setAbility(56);
        playerDocument.setStrength(99);
        playerDocument.setSpeed(75);
        playerDocument.setReactionTime(86);

        Player player = sut.fromDocument(playerDocument);

        assertEquals(playerDocument.getId(), player.getId());
        assertEquals(playerDocument.getName(), player.getName());
        assertEquals(playerDocument.getGender(), player.getGender().name());
        assertEquals(playerDocument.getAbility(), player.getAbility());
        assertEquals(playerDocument.getStrength(), player.getStrength());
        assertEquals(playerDocument.getSpeed(), player.getSpeed());
        assertEquals(playerDocument.getReactionTime(), player.getReactionTime());
    }

}