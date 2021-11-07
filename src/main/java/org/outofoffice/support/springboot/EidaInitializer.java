package org.outofoffice.support.springboot;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eidaprototype.lib.core.EidaDllClient;
import org.outofoffice.eidaprototype.lib.core.EidaRepository;
import org.outofoffice.eidaprototype.lib.core.EidaSerializer;
import org.outofoffice.eidaprototype.lib.core.EidaDmlClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static java.lang.Integer.MIN_VALUE;


@Component
@Order(MIN_VALUE)
@RequiredArgsConstructor
public class EidaInitializer implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    private final EidaDllClient managerClient;
    private final EidaDmlClient shardClient;
    private final EidaSerializer eidaserializer;


    @Override
    public void run(ApplicationArguments args) {
        applicationContext.getBeansOfType(EidaRepository.class).values()
                .forEach(repository -> repository.init(managerClient, shardClient, eidaserializer));
    }

}
