package com.example.spring.controller;

import com.example.spring.model.Grade;
import com.example.spring.model.Sexe;
import com.example.spring.repository.GradeRepository;
import com.example.spring.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<Object> findAll() throws Exception{
        try{
            List<Grade> grades = gradeService.findAll();
            return new ResponseEntity<>(grades, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Grade grade) throws Exception{
        try{
            Grade grades = gradeService.createGrade(grade);
            return new ResponseEntity<>(grades, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            Grade grades = gradeService.findById(id).get();
            return new ResponseEntity<>(grades, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            gradeService.delete(id);
            return new ResponseEntity<>("suppresion effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Grade grade) throws Exception{
        try{
            Optional<Grade> ExistingGrade = gradeService.findById(id);
            if(ExistingGrade.isPresent()){
                ExistingGrade.get().setGrade(grade.getGrade());
                gradeService.updateGrade(id,ExistingGrade.get());
            }
            return new ResponseEntity<>(grade, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
