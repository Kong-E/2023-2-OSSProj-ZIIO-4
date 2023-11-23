package com.ziio.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 +1
    private Long id;

    @Column(nullable = false)
    private String category_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int top_fixed;
}
