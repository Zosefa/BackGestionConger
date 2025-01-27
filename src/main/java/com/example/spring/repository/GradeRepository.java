package com.example.spring.repository;

import com.example.spring.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Grade findByGrade(Integer grade);
}
