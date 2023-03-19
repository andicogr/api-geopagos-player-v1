package com.geopagos.player.controller;

import com.geopagos.player.business.PlayerService;
import com.geopagos.player.controller.mapper.PlayerMapper;
import com.geopagos.player.model.api.PlayerRequest;
import com.geopagos.player.model.api.PlayerResponse;
import com.geopagos.player.model.api.PlayerSearchRequest;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PlayersApiDelegateImpl implements PlayersApiDelegate {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    @Override
    public ResponseEntity<PlayerResponse> createPlayer(PlayerRequest playerRequest) {

        return ResponseEntity.ok(playerMapper.toResponse(playerService.create(playerMapper.fromRequest(playerRequest))));
    }

    @Override
    public ResponseEntity<List<PlayerResponse>> findPlayers(PlayerSearchRequest playerSearchRequest) {

        return ResponseEntity.ok(
                playerService.find(playerMapper.fromRequest(playerSearchRequest))
                        .stream()
                        .map(playerMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<PlayerResponse> findPlayerById(String playerId) {

        return ResponseEntity.ok(playerMapper.toResponse(playerService.findById(playerId)));
    }
}
