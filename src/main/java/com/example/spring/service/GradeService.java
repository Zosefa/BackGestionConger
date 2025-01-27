package com.example.spring.service;

import com.example.spring.model.Grade;
import com.example.spring.model.Sexe;
import com.example.spring.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public Grade createGrade(Grade grade){
        gradeRepository.save(grade);
        return grade;
    }

    public List<Grade> findAll(){
        return gradeRepository.findAll();
    }

    public Optional<Grade> findById(Integer id){
        return gradeRepository.findById(id);
    }

    public Grade findByGrade(Integer grade){
        return gradeRepository.findByGrade(grade);
    }

    public void delete(Integer id){
        Optional<Grade> grades = findById(id);
        if(grades.isPresent()){
            gradeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }

    public Grade updateGrade(Integer id, Grade grade){
        Grade existGrade = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        createGrade(grade);
        return grade;
    }
}
