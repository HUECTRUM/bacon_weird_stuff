package com.bacon.persistencefiles.mappers;

public interface PersistenceMapper<BeanClass, EntityClass> {
    BeanClass toBean(EntityClass entity);
    EntityClass toEntity(BeanClass bean);
}
