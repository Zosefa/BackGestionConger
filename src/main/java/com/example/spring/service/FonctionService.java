package com.example.spring.service;

import com.example.spring.model.Fonction;
import com.example.spring.model.Grade;
import com.example.spring.repository.FonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FonctionService {
    @Autowired
    private FonctionRepository fonctionRepository;

    public Fonction create(Fonction fonction){
        fonctionRepository.save(fonction);
        return fonction;
    }

    public List<Fonction> findAll(){
        return fonctionRepository.findAll();
    }

    public Optional<Fonction> findById(Integer id){
        return fonctionRepository.findById(id);
    }

    public Fonction findByFonction(String fonction){
        return fonctionRepository.findByFonction(fonction);
    }

    public void delete(Integer id){
        Optional<Fonction> fonctions = findById(id);
        if(fonctions.isPresent()){
            fonctionRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }

    public Fonction update(Integer id, Fonction fonction){
        Fonction existFonction = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        create(fonction);
        return fonction;
    }
}
