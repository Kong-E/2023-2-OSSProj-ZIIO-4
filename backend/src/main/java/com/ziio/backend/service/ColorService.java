package com.ziio.backend.service;

import com.ziio.backend.entity.Color;
import com.ziio.backend.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    // DB에 공지사항 저장
    public void save(Color color) {
        colorRepository.save(color);
    }
    public String findCodeById(Long id) {
        return colorRepository.findById(id)
                .map(Color::getCode)
                .orElse(null);
    }

}