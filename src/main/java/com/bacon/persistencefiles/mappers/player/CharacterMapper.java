package com.bacon.persistencefiles.mappers.player;

import com.bacon.gamefiles.characters.Character;
import com.bacon.persistencefiles.gamedto.entities.player.CharacterEntity;
import com.bacon.persistencefiles.mappers.PersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CharacterMapper implements PersistenceMapper<Character, CharacterEntity> {
    private final ApplicationContext applicationContext;

    @Override
    public Character toBean(CharacterEntity entity) {
        return (Character) applicationContext.getBean(entity.characterClass);
    }

    @Override
    public CharacterEntity toEntity(Character bean) {
        return new CharacterEntity(bean.getClass());
    }
}
