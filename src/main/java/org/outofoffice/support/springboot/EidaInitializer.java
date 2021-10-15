package org.outofoffice.support.springboot;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eidaprototype.lib.core.EidaManagerClient;
import org.outofoffice.eidaprototype.lib.core.EidaRepository;
import org.outofoffice.eidaprototype.lib.core.EidaSerializer;
import org.outofoffice.eidaprototype.lib.core.EidaShardClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EidaInitializer implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    private final EidaManagerClient managerClient;
    private final EidaShardClient shardClient;
    private final EidaSerializer eidaserializer;


    @Override
    public void run(ApplicationArguments args) {
        applicationContext.getBeansOfType(EidaRepository.class).values()
                .forEach(repository -> repository.init(managerClient, shardClient, eidaserializer));
    }

}
