package com.example.spring.controller;

import com.example.spring.model.Fonction;
import com.example.spring.service.FonctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fonction")
public class FonctionController {
    @Autowired
    private FonctionService fonctionService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Fonction> fonctions = fonctionService.findAll();
            return new ResponseEntity<>(fonctions, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Fonction fonction){
        try{
            Fonction fonctions = fonctionService.create(fonction);
            return new ResponseEntity<>(fonctions, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            Fonction fonction = fonctionService.findById(id).get();
            return new ResponseEntity<>(fonction, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            fonctionService.delete(id);
            return new ResponseEntity<>("suppresion effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Fonction fonction) throws Exception{
        try{
            Optional<Fonction> ExistingFonction = fonctionService.findById(id);
            if(ExistingFonction.isPresent()){
                ExistingFonction.get().setFonction(fonction.getFonction());
                ExistingFonction.get().setDepartement_id(fonction.getDepartement_id());
                fonctionService.update(id,ExistingFonction.get());
            }
            return new ResponseEntity<>(fonction, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
