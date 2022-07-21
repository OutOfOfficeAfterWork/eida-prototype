package org.outofoffice.eida.api.configuration;

import org.outofoffice.common.socket.EidaSocketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRegistration {

    @Bean
    public EidaSocketClient eidaSocketClient() {
        return new EidaSocketClient();
    }

}
