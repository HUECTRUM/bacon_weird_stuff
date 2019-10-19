package com.bacon.persistencefiles.mappers.game;

import com.bacon.gamefiles.holders.GameInfoHolder;
import com.bacon.gamefiles.ioc.PlayerMode;
import com.bacon.gamefiles.service.GameService;
import com.bacon.persistencefiles.gamedto.entities.game.GameEntity;
import com.bacon.persistencefiles.mappers.GamePersistenceMapper;
import com.bacon.persistencefiles.mappers.cards.CardEffectMapper;
import com.bacon.persistencefiles.mappers.player.PlayerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.utils.MapUtils.mapValues;
import static com.bacon.gamefiles.utils.StreamUtils.mapList;

@Component
@AllArgsConstructor
public class GameInfoMapper implements GamePersistenceMapper {
    private final PlayerEntityMapper playerEntityMapper;
    private final CardEffectMapper cardEffectMapper;
    private final BeatInfoMapper beatInfoMapper;
    private final GameService gameService;

    @Override
    public GameInfoHolder toBean(GameEntity entity, PlayerMode firstPlayerMode, PlayerMode secondPlayerMode) {
        GameInfoHolder holder = gameService.createGame(firstPlayerMode, secondPlayerMode);

        holder.gameId = entity.gameId;
        holder.state = entity.state;
        holder.playerOne = playerEntityMapper.toBean(entity.playerOne);
        holder.playerTwo = playerEntityMapper.toBean(entity.playerTwo);
        holder.additionalEffects = mapValues(entity.additionalEffects, v -> mapList(v, cardEffectMapper::toBean));
        holder.beatInfoHolder = beatInfoMapper.toBean(entity.beatInfoHolder);
        holder.prevBeats = mapList(entity.prevBeats, beatInfoMapper::toBean);

        return holder;
    }

    @Override
    public GameEntity toEntity(GameInfoHolder bean) {
        return new GameEntity(
                bean.gameId,
                bean.state,
                playerEntityMapper.toEntity(bean.playerOne),
                playerEntityMapper.toEntity(bean.playerTwo),
                mapValues(bean.additionalEffects, v -> mapList(v, cardEffectMapper::toEntity)),
                beatInfoMapper.toEntity(bean.beatInfoHolder),
                mapList(bean.prevBeats, beatInfoMapper::toEntity)
        );
    }
}
