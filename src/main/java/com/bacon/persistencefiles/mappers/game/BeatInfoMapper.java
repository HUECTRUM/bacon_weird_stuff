package com.bacon.persistencefiles.mappers.game;

import com.bacon.gamefiles.holders.beat.BeatInfoHolder;
import com.bacon.persistencefiles.gamedto.entities.game.BeatInfoEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import com.bacon.persistencefiles.mappers.cards.AttackPairMapper;
import com.bacon.persistencefiles.mappers.player.PlayerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BeatInfoMapper implements PersistenceMapper<BeatInfoHolder, BeatInfoEntity> {
    private final PlayerEntityMapper playerEntityMapper;
    private final AttackPairMapper attackPairMapper;
    private final ClashInfoMapper clashInfoMapper;

    @Override
    public BeatInfoHolder toBean(BeatInfoEntity entity) {
        return new BeatInfoHolder(
                entity.beatNumber,
                playerEntityMapper.toBean(entity.activePlayer),
                playerEntityMapper.toBean(entity.reactivePlayer),
                attackPairMapper.toBean(entity.activePlayerPair),
                attackPairMapper.toBean(entity.reactivePlayerPair),
                clashInfoMapper.toBean(entity.clashInfoHolder)
        );
    }

    @Override
    public BeatInfoEntity toEntity(BeatInfoHolder bean) {
        return new BeatInfoEntity(
                bean.beatNumber,
                playerEntityMapper.toEntity(bean.activePlayer),
                playerEntityMapper.toEntity(bean.reactivePlayer),
                attackPairMapper.toEntity(bean.activePlayerPair),
                attackPairMapper.toEntity(bean.reactivePlayerPair),
                clashInfoMapper.toEntity(bean.clashInfoHolder)
        );
    }
}
