package com.geopagos.player.controller.mapper;

import com.geopagos.player.model.api.PlayerRequest;
import com.geopagos.player.model.api.PlayerResponse;
import com.geopagos.player.model.api.PlayerSearchRequest;
import com.geopagos.player.model.domain.Player;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerResponse toResponse(Player player);

    Player fromRequest(PlayerRequest request);

    Player fromRequest(PlayerSearchRequest request);

}
