package com.bacon.persistencefiles.mappers.cards;

import com.bacon.gamefiles.gameobjects.cards.Card;
import com.bacon.gamefiles.gameobjects.triggers.EffectTrigger;
import com.bacon.gamefiles.utils.ReflectionUtils;
import com.bacon.persistencefiles.gamedto.entities.cards.CardEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

import static com.bacon.gamefiles.utils.StreamUtils.mapList;
import static java.util.stream.Collectors.toMap;

@Component
@AllArgsConstructor
public class CardMapper implements PersistenceMapper<Card, CardEntity> {
    private final CardEffectMapper cardEffectMapper;
    private final ApplicationContext applicationContext;

    @Override
    public Card toBean(CardEntity entity) {
        Object generator = applicationContext.getBean(entity.methodName);
        Card card = (Card) ReflectionUtils.callMethod(generator, entity.getMethodName());
        card.cardEffects = entity.cardEffects == null
                ? null :
                mapEffects(entity.cardEffects, e -> mapList(e.getValue(), cardEffectMapper::toBean));
        return card;
    }

    @Override
    public CardEntity toEntity(Card bean) {
        return new CardEntity(
                bean.getClass(),
                Character.toLowerCase(bean.name.charAt(0)) + bean.name.substring(1),
                bean.cardEffects == null
                        ? null
                        : mapEffects(bean.cardEffects, e -> mapList(e.getValue(), cardEffectMapper::toEntity))
        );
    }

    private <T, R> Map<EffectTrigger, R> mapEffects(Map<EffectTrigger, T> effectMap, Function<? super Map.Entry<EffectTrigger, T>, ? extends R> mapper) {
        return effectMap
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, mapper));
    }
}
