package com.geopagos.player.business;

import com.geopagos.player.dao.PlayerDao;
import com.geopagos.player.model.domain.Gender;
import com.geopagos.player.model.domain.Player;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerDao playerDao;

    public Player create(Player player) {
        return playerDao.save(player);
    }

    public List<Player> find(Player player) {
        return playerDao.find(player);
    }

    public Player findById(String playerId) {
        return playerDao.findById(playerId);
    }
}
