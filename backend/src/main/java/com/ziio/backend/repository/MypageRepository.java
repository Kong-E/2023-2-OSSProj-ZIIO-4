package com.ziio.backend.repository;

import com.ziio.backend.entity.Mypage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<Mypage, Long> {
}