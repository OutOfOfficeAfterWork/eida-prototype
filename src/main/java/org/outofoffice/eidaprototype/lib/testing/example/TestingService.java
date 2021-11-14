package org.outofoffice.eidaprototype.lib.testing.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TestingService {

    private final TestEidaRepository testEidaRepository;
    private final KemiRepository kemiRepository;

}
