package org.outofoffice.eida.application;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.domain.Major;
import org.outofoffice.eida.domain.MajorRepository;

@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

    public void save(String name, String englishName) {
        Major major = new Major(name, englishName);
        majorRepository.insert(major);
    }

    public Major mustFind(String majorName) {
        return majorRepository.find(majorName).orElseThrow();
    }
}
