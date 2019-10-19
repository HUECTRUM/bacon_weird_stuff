package com.bacon.persistencefiles.mappers.player;

import com.bacon.gamefiles.player.PlayerBeatHolder;
import com.bacon.persistencefiles.gamedto.entities.player.PlayerBeatEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import com.bacon.persistencefiles.mappers.cards.AttackPairMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerBeatMapper implements PersistenceMapper<PlayerBeatHolder, PlayerBeatEntity> {
    private final AttackPairMapper attackPairMapper;

    @Override
    public PlayerBeatHolder toBean(PlayerBeatEntity entity) {
        return new PlayerBeatHolder(
                attackPairMapper.toBean(entity.currentBeatPair),
                entity.damageDealt,
                entity.damageTaken,
                entity.attackHit,
                entity.stunned
        );
    }

    @Override
    public PlayerBeatEntity toEntity(PlayerBeatHolder bean) {
        return new PlayerBeatEntity(
                attackPairMapper.toEntity(bean.currentBeatPair),
                bean.damageDealt,
                bean.damageTaken,
                bean.attackHit,
                bean.stunned
        );
    }
}
