package com.bacon.persistencefiles.mappers.cards;

import com.bacon.gamefiles.attacks.AttackPair;
import com.bacon.persistencefiles.gamedto.entities.cards.AttackPairEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bacon.gamefiles.utils.StreamUtils.mapList;

@Component
@AllArgsConstructor
public class AttackPairMapper implements PersistenceMapper<AttackPair, AttackPairEntity> {
    private final CardMapper cardMapper;

    @Override
    public AttackPair toBean(AttackPairEntity entity) {
        return entity == null || entity.cards == null
                ? new AttackPair(null)
                : new AttackPair(mapList(entity.cards, cardMapper::toBean));
    }

    @Override
    public AttackPairEntity toEntity(AttackPair bean) {
        return bean == null || bean.cards == null
                ? new AttackPairEntity(null)
                : new AttackPairEntity(mapList(bean.cards, cardMapper::toEntity));
    }
}
