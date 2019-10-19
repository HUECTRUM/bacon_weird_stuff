package com.bacon.persistencefiles.mappers.player;

import com.bacon.gamefiles.player.Player;
import com.bacon.persistencefiles.gamedto.entities.player.PlayerEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import com.bacon.persistencefiles.mappers.cards.CardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.utils.StreamUtils.mapList;

@Component
@AllArgsConstructor
public class PlayerEntityMapper implements PersistenceMapper<Player, PlayerEntity> {
    private final CardMapper cardMapper;
    private final CharacterMapper characterMapper;
    private final PlayerBeatMapper playerBeatMapper;

    @Override
    public Player toBean(PlayerEntity entity) {
        return entity == null ? null : new Player(
                entity.playerId,
                entity.health,
                characterMapper.toBean(entity.character),
                entity.discardOne == null ? null : mapList(entity.discardOne, cardMapper::toBean),
                entity.discardTwo == null ? null : mapList(entity.discardTwo, cardMapper::toBean),
                playerBeatMapper.toBean(entity.beatHolder),
                entity.prevBeats == null ? null : mapList(entity.prevBeats, playerBeatMapper::toBean),
                entity.bonuses
        );
    }

    @Override
    public PlayerEntity toEntity(Player bean) {
        return bean == null ? null : new PlayerEntity(
                bean.playerId,
                bean.health,
                characterMapper.toEntity(bean.character),
                bean.discardOne == null ? null : mapList(bean.discardOne, cardMapper::toEntity),
                bean.discardTwo == null ? null : mapList(bean.discardTwo, cardMapper::toEntity),
                playerBeatMapper.toEntity(bean.beatHolder),
                bean.prevBeats == null ? null : mapList(bean.prevBeats, playerBeatMapper::toEntity),
                bean.bonuses
        );
    }
}



