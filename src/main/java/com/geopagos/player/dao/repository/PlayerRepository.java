package com.geopagos.player.dao.repository;

import com.geopagos.player.model.thridparty.db.PlayerDocument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<PlayerDocument, String> {
}
