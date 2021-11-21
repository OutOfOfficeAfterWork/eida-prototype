package org.outofoffice.eidaspringsupport;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class EidaConfiguration {

    private final String managerServerUrl = "localhost:1234";

}
