package com.example.springapp.application;

import com.example.springapp.domain.Major;
import com.example.springapp.domain.MajorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    public List<Major> findAll() {
        return majorRepository.listAll();
    }
}
