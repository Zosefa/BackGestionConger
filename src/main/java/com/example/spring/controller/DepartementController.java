package com.example.spring.controller;

import com.example.spring.model.Departement;
import com.example.spring.model.Grade;
import com.example.spring.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departement")
public class DepartementController {
    @Autowired
    private DepartementService departementService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Departement> departements = departementService.findAll();
            return new ResponseEntity<>(departements, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Departement departement){
        try{
            Departement departements = departementService.create(departement);
            return new ResponseEntity<>(departements, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            Departement departements = departementService.findById(id).get();
            return new ResponseEntity<>(departements, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            departementService.delete(id);
            return new ResponseEntity<>("suppresion effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Departement departement) throws Exception{
        try{
            Optional<Departement> ExistingDepartement = departementService.findById(id);
            if(ExistingDepartement.isPresent()){
                ExistingDepartement.get().setDepartement(departement.getDepartement());
                departementService.updateDepartement(id,ExistingDepartement.get());
            }
            return new ResponseEntity<>(departement, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
