package org.outofoffice.lib.context;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.lib.example.KemiRepository;
import org.outofoffice.lib.example.TestEidaRepository;

import java.util.List;

@Slf4j
public class EidaRepositoryScanner {
    public List<String> scan() {
        List<String> names = List.of(TestEidaRepository.class.getName(), KemiRepository.class.getName());
        log.info("Eida repository scan: names- {}", names);
        return names;

    }
}
