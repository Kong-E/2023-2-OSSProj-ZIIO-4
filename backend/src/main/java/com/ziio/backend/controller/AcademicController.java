package com.ziio.backend.controller;

import com.ziio.backend.entity.Academic;
import com.ziio.backend.service.AcademicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academics")
public class AcademicController {

    @Autowired
    private AcademicService academicService;

    //
    @GetMapping
    public ResponseEntity<List<Academic>> getAllAcademics() {
        List<Academic> academics = academicService.getAllAcademics();
        return new ResponseEntity<>(academics, HttpStatus.OK);
    }

}


