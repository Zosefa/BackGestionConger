package com.example.spring.repository;

import com.example.spring.model.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexeRepository extends JpaRepository<Sexe, Integer> {
}
