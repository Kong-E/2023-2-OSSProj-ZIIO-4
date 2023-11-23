package com.ziio.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Getter
@Setter
@Entity
public class Mypage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String user_email;

    @Column(nullable = false)
    private String title;

    @Column
    private String colorId;

    @Column
    private String url;

    @Column
    private String memo;

    @Column
    private String date;
}
