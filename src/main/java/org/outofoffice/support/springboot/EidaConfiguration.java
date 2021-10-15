package org.outofoffice.support.springboot;

import org.outofoffice.eidaprototype.lib.core.EidaManagerClient;
import org.outofoffice.eidaprototype.lib.core.EidaSerializer;
import org.outofoffice.eidaprototype.lib.core.EidaSerializerImpl;
import org.outofoffice.eidaprototype.lib.core.EidaShardClient;
import org.outofoffice.eidaprototype.lib.testing.mock.ConsoleManagerClient;
import org.outofoffice.eidaprototype.lib.testing.mock.ConsoleShardClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class EidaConfiguration {

    @Bean
    protected EidaManagerClient managerClient() {
        return new ConsoleManagerClient("localhost:1234");
    }

    @Bean
    protected EidaShardClient shardClient() {
        return new ConsoleShardClient();
    }

    @Bean
    protected EidaSerializer eidaserializer() {
        return new EidaSerializerImpl();
    }

}
