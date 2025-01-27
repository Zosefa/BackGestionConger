package com.example.spring.repository;

import com.example.spring.model.Employee;
import com.example.spring.model.Fonction;
import com.example.spring.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByMatricule(String matricule);

    List<Employee> findByNomOrPrenom(String nom, String prenom);

    List<Employee> findByFonction(Fonction fonction);

    List<Employee> findByGrade(Grade grade);
}
