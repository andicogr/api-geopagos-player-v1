package com.geopagos.player.dao.mapper;

import com.geopagos.player.model.domain.Player;
import com.geopagos.player.model.thridparty.db.PlayerDocument;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapperDao {

    PlayerDocument toDocument(Player player);

    Player fromDocument(PlayerDocument document);

}
