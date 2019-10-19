package com.bacon.persistencefiles.mappers.game;

import com.bacon.gamefiles.holders.beat.ClashInfoHolder;
import com.bacon.persistencefiles.gamedto.entities.game.ClashInfoEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import com.bacon.persistencefiles.mappers.cards.CardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.utils.StreamUtils.mapList;

@Component
@AllArgsConstructor
public class ClashInfoMapper implements PersistenceMapper<ClashInfoHolder, ClashInfoEntity> {
    private final CardMapper cardMapper;

    @Override
    public ClashInfoHolder toBean(ClashInfoEntity entity) {
        return new ClashInfoHolder(
                entity.firstPlayerBasesPlayed == null ? null : mapList(entity.firstPlayerBasesPlayed, cardMapper::toBean),
                entity.secondPlayerBasesPlayed == null ? null : mapList(entity.secondPlayerBasesPlayed, cardMapper::toBean)
        );
    }

    @Override
    public ClashInfoEntity toEntity(ClashInfoHolder bean) {
        return new ClashInfoEntity(
                bean.firstPlayerBasesPlayed == null ? null : mapList(bean.firstPlayerBasesPlayed, cardMapper::toEntity),
                bean.secondPlayerBasesPlayed == null ? null : mapList(bean.secondPlayerBasesPlayed, cardMapper::toEntity)
        );
    }
}
