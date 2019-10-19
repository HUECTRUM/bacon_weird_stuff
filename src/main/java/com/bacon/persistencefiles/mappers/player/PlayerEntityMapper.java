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
        return new Player(
                entity.playerId,
                entity.health,
                characterMapper.toBean(entity.character),
                mapList(entity.discardOne, cardMapper::toBean),
                mapList(entity.discardTwo, cardMapper::toBean),
                playerBeatMapper.toBean(entity.beatHolder),
                mapList(entity.prevBeats, playerBeatMapper::toBean),
                entity.bonuses
        );
    }

    @Override
    public PlayerEntity toEntity(Player bean) {
        return new PlayerEntity(
                bean.playerId,
                bean.health,
                characterMapper.toEntity(bean.character),
                mapList(bean.discardOne, cardMapper::toEntity),
                mapList(bean.discardTwo, cardMapper::toEntity),
                playerBeatMapper.toEntity(bean.beatHolder),
                mapList(bean.prevBeats, playerBeatMapper::toEntity),
                bean.bonuses
        );
    }
}



