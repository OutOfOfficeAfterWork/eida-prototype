package org.outofoffice.support.springboot;

import org.outofoffice.eidaprototype.lib.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class EidaConfiguration {

    @Bean
    protected EidaManagerClient managerClient() {
        return new EidaManagerClientImpl("localhost:1234");
    }

    @Bean
    protected EidaShardClient shardClient() {
        return new EidaShardClientImpl();
    }

    @Bean
    protected EidaSerializer eidaserializer() {
        return new EidaSerializerImpl();
    }

}
