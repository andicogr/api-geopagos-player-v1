package com.geopagos.player.dao;

import com.geopagos.player.dao.mapper.PlayerMapperDao;
import com.geopagos.player.dao.repository.PlayerRepository;
import com.geopagos.player.exceptions.RecordNotFoundException;
import com.geopagos.player.model.domain.Player;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PlayerDao {

    private final PlayerRepository playerRepository;
    private final PlayerMapperDao playerMapperDao;

    public Player save(Player player) {

        return playerMapperDao.fromDocument(playerRepository.save(playerMapperDao.toDocument(player)));

    }

    public List<Player> find(Player player) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("gender", ExampleMatcher.GenericPropertyMatchers.exact().caseSensitive())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        return playerRepository.findAll(Example.of(playerMapperDao.toDocument(player), exampleMatcher))
                .stream()
                .map(playerMapperDao::fromDocument)
                .collect(Collectors.toList());
    }

    public Player findById(String playerId) {

        return playerMapperDao.fromDocument(playerRepository.findById(playerId).orElseThrow(RecordNotFoundException::new));

    }
}
