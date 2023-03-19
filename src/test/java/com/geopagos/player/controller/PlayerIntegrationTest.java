package com.geopagos.player.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geopagos.player.dao.repository.PlayerRepository;
import com.geopagos.player.model.api.Gender;
import com.geopagos.player.model.api.ModelApiException;
import com.geopagos.player.model.api.PlayerRequest;
import com.geopagos.player.model.api.PlayerResponse;
import com.geopagos.player.model.thridparty.db.PlayerDocument;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlayerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    void Given_NewPlayerInformation_When_IsSavedAsFemale_Then_ReturnFemalePlayer() throws Exception {
        String uniqueValue = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());

        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setGender(Gender.FEMALE);
        playerRequest.setName("Player Testing " + uniqueValue);
        playerRequest.setAbility(BigDecimal.valueOf(95));
        playerRequest.setSpeed(BigDecimal.valueOf(85));
        playerRequest.setStrength(BigDecimal.valueOf(83));
        playerRequest.setReactionTime(BigDecimal.valueOf(75));

        PlayerDocument playerDocumentBuilt = new PlayerDocument();
        playerDocumentBuilt.setGender("FEMALE");
        playerDocumentBuilt.setName("Player Testing " + uniqueValue);
        playerDocumentBuilt.setAbility(95);
        playerDocumentBuilt.setSpeed(85);
        playerDocumentBuilt.setStrength(83);
        playerDocumentBuilt.setReactionTime(75);

        PlayerDocument playerDocumentFromDB = new PlayerDocument();
        playerDocumentFromDB.setId(UUID.randomUUID().toString());
        playerDocumentFromDB.setGender("FEMALE");
        playerDocumentFromDB.setName("Player Testing " + uniqueValue);
        playerDocumentFromDB.setAbility(95);
        playerDocumentFromDB.setSpeed(85);
        playerDocumentFromDB.setStrength(83);
        playerDocumentFromDB.setReactionTime(75);

        Mockito.when(playerRepository.save(playerDocumentBuilt)).thenReturn(playerDocumentFromDB);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(playerRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PlayerResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PlayerResponse.class);

        assertNotNull(response.getId());
        assertEquals(playerRequest.getGender().name(), response.getGender().name());
        assertEquals(playerRequest.getName(), response.getName());
        assertEquals(playerRequest.getAbility(), response.getAbility());
        assertEquals(playerRequest.getSpeed(), response.getSpeed());
        assertEquals(playerRequest.getStrength(), response.getStrength());
        assertEquals(playerRequest.getReactionTime(), response.getReactionTime());

    }

    @Test
    void Given_NewPlayerInformation_When_IsSavedAsMale_Then_ReturnMalePlayer() throws Exception {
        String uniqueValue = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());

        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setGender(Gender.MALE);
        playerRequest.setName("Player Testing " + uniqueValue);
        playerRequest.setAbility(BigDecimal.valueOf(95));
        playerRequest.setSpeed(BigDecimal.valueOf(85));
        playerRequest.setStrength(BigDecimal.valueOf(83));
        playerRequest.setReactionTime(BigDecimal.valueOf(75));

        PlayerDocument playerDocumentBuilt = new PlayerDocument();
        playerDocumentBuilt.setGender("MALE");
        playerDocumentBuilt.setName("Player Testing " + uniqueValue);
        playerDocumentBuilt.setAbility(95);
        playerDocumentBuilt.setSpeed(85);
        playerDocumentBuilt.setStrength(83);
        playerDocumentBuilt.setReactionTime(75);

        PlayerDocument playerDocumentFromDB = new PlayerDocument();
        playerDocumentFromDB.setId(UUID.randomUUID().toString());
        playerDocumentFromDB.setGender("MALE");
        playerDocumentFromDB.setName("Player Testing " + uniqueValue);
        playerDocumentFromDB.setAbility(95);
        playerDocumentFromDB.setSpeed(85);
        playerDocumentFromDB.setStrength(83);
        playerDocumentFromDB.setReactionTime(75);

        Mockito.when(playerRepository.save(playerDocumentBuilt)).thenReturn(playerDocumentFromDB);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(playerRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PlayerResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PlayerResponse.class);

        assertNotNull(response.getId());
        assertEquals(playerRequest.getGender().name(), response.getGender().name());
        assertEquals(playerRequest.getName(), response.getName());
        assertEquals(playerRequest.getAbility(), response.getAbility());
        assertEquals(playerRequest.getSpeed(), response.getSpeed());
        assertEquals(playerRequest.getStrength(), response.getStrength());
        assertEquals(playerRequest.getReactionTime(), response.getReactionTime());

    }

    @Test
    void Given_PlayerId_When_ThePlayerExists_Then_ReturnPlayerInformation() throws Exception {
        String id = UUID.randomUUID().toString();

        String uniqueValue = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());

        PlayerDocument playerDocumentFromDB = new PlayerDocument();
        playerDocumentFromDB.setId(id);
        playerDocumentFromDB.setGender("MALE");
        playerDocumentFromDB.setName("Player Testing " + uniqueValue);
        playerDocumentFromDB.setAbility(95);
        playerDocumentFromDB.setSpeed(85);
        playerDocumentFromDB.setStrength(83);
        playerDocumentFromDB.setReactionTime(75);

        Mockito.when(playerRepository.findById(id)).thenReturn(Optional.of(playerDocumentFromDB));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/players/{playerId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PlayerResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PlayerResponse.class);

        assertEquals(id, response.getId());
        assertEquals("MALE", response.getGender().name());
        assertEquals("Player Testing " + uniqueValue, response.getName());
        assertEquals(95, response.getAbility().intValue());
        assertEquals(85, response.getSpeed().intValue());
        assertEquals(83, response.getStrength().intValue());
        assertEquals(75, response.getReactionTime().intValue());

    }

    @Test
    void Given_PlayerId_When_ThePlayerNotExists_Then_ReturnException() throws Exception {
        String id = UUID.randomUUID().toString();

        Mockito.when(playerRepository.findById(id)).thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/players/{playerId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();

        ModelApiException response = objectMapper.readValue(result.getResponse().getContentAsString(), ModelApiException.class);

        assertEquals("00090", response.getCode());
        assertEquals("The record was not found", response.getDescription());

    }

    @Test
    void Given_PlayerSearch_When_GenderIsMale_Then_ReturnOnlyMalePlayers() throws Exception {
        String uniqueValue = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());

        PlayerDocument playerDocumentFromDB = new PlayerDocument();
        playerDocumentFromDB.setId(UUID.randomUUID().toString());
        playerDocumentFromDB.setGender("MALE");
        playerDocumentFromDB.setName("Player Testing " + uniqueValue);
        playerDocumentFromDB.setAbility(95);
        playerDocumentFromDB.setSpeed(85);
        playerDocumentFromDB.setStrength(83);
        playerDocumentFromDB.setReactionTime(75);

        Mockito.when(playerRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(playerDocumentFromDB));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/players")
                        .param("gender", "MALE")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<PlayerResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertFalse(response.isEmpty());

        assertEquals("MALE", response.get(0).getGender().name());
        assertEquals("Player Testing " + uniqueValue, response.get(0).getName());
        assertEquals(95, response.get(0).getAbility().intValue());
        assertEquals(85, response.get(0).getSpeed().intValue());
        assertEquals(83, response.get(0).getStrength().intValue());
        assertEquals(75, response.get(0).getReactionTime().intValue());

    }

}