package com.ziio.backend.service;

import com.ziio.backend.entity.Academic;
import com.ziio.backend.repository.AcademicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicService {
    private final AcademicRepository academicRepository;

    @Autowired
    public AcademicService(AcademicRepository academicRepository) {
        this.academicRepository = academicRepository;
    }

    // DB에 공지사항 저장
    public void save(Academic academic) {
        academicRepository.save(academic);
    }
    public List<Academic> getAllAcademics() {
        return academicRepository.findAll();
    }
}