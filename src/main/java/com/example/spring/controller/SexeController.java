package com.example.spring.controller;

import com.example.spring.model.Sexe;
import com.example.spring.service.SexeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sexe")
public class SexeController {
    @Autowired
    private SexeService sexeService;

    @GetMapping
    public ResponseEntity<Object> findAll() throws Exception{
        try{
            List<Sexe> sexes = sexeService.findAll();
            return new ResponseEntity<>(sexes, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createSexe(@RequestBody Sexe sexe) throws Exception{
        try{
            Sexe sexes = sexeService.createSexe(sexe);
            return new ResponseEntity<>(sexes, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            Sexe sexes = sexeService.findById(id).get();
            return new ResponseEntity<>(sexes, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            sexeService.daleteSexe(id);
            return new ResponseEntity<>("suppression effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Sexe sexe) throws Exception{
        try{
            Optional<Sexe> ExistingSexe = sexeService.findById(id);
            if(ExistingSexe.isPresent()){
                ExistingSexe.get().setSexe(sexe.getSexe());
                sexeService.updateSexe(id,ExistingSexe.get());
            }
            return new ResponseEntity<>(sexe, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
