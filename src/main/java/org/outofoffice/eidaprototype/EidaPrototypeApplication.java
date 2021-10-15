package org.outofoffice.eidaprototype;

import org.outofoffice.support.springboot.EnableEida;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication @EnableEida
public class EidaPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EidaPrototypeApplication.class, args);
    }

}
