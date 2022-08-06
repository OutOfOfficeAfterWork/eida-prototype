package org.outofoffice.eida.testing;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntityService {
    private final EntityRepository entityRepository;

    public void insert(Entity entity) {
        entityRepository.insert(entity);
    }

}
