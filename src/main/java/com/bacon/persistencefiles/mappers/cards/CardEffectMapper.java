package com.bacon.persistencefiles.mappers.cards;

import com.bacon.gamefiles.gameobjects.cards.CardEffect;
import com.bacon.persistencefiles.gamedto.entities.cards.CardEffectEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CardEffectMapper implements PersistenceMapper<CardEffect, CardEffectEntity> {
    private final ApplicationContext applicationContext;

    @Override
    public CardEffect toBean(CardEffectEntity entity) {
        ObjectProvider provider = applicationContext.getBeanProvider(entity.getCardClass());
        return (CardEffect)provider.getObject(entity.getConstructorArgs());
    }

    @Override
    public CardEffectEntity toEntity(CardEffect bean) {
        return new CardEffectEntity(bean.getClass(), bean.constructorParams());
    }
}
