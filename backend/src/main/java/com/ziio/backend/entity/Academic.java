package com.ziio.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Academic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 +1
    private Long id;

    @Column(nullable = true)
    private String start_date;

    @Column(nullable = true)
    private String end_date;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String host_department;

    @Column(nullable = false)
    private String color_code;
}
