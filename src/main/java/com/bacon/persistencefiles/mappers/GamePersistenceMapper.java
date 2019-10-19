package com.bacon.persistencefiles.mappers;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.PlayerMode;
import com.bacon.persistencefiles.gamedto.entities.game.GameEntity;

public interface GamePersistenceMapper {
    GameInfoHolder toBean(GameEntity entity, PlayerMode firstPlayerMode, PlayerMode secondPlayerMode);
    GameEntity toEntity(GameInfoHolder bean);
}
