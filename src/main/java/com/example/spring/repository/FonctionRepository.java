package com.example.spring.repository;

import com.example.spring.model.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Integer> {
    Fonction findByFonction(String fonction);
}
