package org.outofoffice.lib.context;

import org.outofoffice.lib.example.KemiRepository;
import org.outofoffice.lib.example.TestEidaRepository;

import java.util.List;

public class EidaRepositoryScanner {
    public List<String> scan() {
        return List.of(TestEidaRepository.class.getName(), KemiRepository.class.getName());
    }
}
