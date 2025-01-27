package com.example.spring.service;

import com.example.spring.model.Sexe;
import com.example.spring.repository.SexeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SexeService {
    @Autowired
    private SexeRepository sexeRepository;

    public Sexe createSexe(Sexe sexe){
        sexeRepository.save(sexe);
        return sexe;
    }
    public List<Sexe> findAll(){
        return sexeRepository.findAll();
    }
    public Optional<Sexe> findById(Integer id){
        return  sexeRepository.findById(id);
    }
    public Sexe updateSexe(Integer id, Sexe sexe){
        Sexe existSexe = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        createSexe(sexe);
        return sexe;
    }
    public void daleteSexe(Integer id){
        Optional<Sexe> sexes = findById(id);
        if(sexes.isPresent()){
            sexeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }
}
