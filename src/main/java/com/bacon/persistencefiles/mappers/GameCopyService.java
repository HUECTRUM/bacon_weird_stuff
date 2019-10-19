package com.bacon.persistencefiles.mappers;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.PlayerMode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameCopyService {
    private final GamePersistenceMapper gamePersistenceMapper;

    public GameInfoHolder deepCopy(GameInfoHolder holder, PlayerMode firstPlayerMode, PlayerMode secondPlayerMode) {
        return gamePersistenceMapper.toBean(
                gamePersistenceMapper.toEntity(holder),
                firstPlayerMode,
                secondPlayerMode
        );
    }
}
