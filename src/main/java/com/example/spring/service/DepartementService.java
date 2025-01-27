package com.example.spring.service;

import com.example.spring.model.Departement;
import com.example.spring.model.Grade;
import com.example.spring.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {
    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> findAll(){
        return departementRepository.findAll();
    }

    public Departement create(Departement departement){
        departementRepository.save(departement);
        return departement;
    }

    public Optional<Departement> findById(Integer id){
        return departementRepository.findById(id);
    }

    public void delete(Integer id){
        Optional<Departement> departements = findById(id);
        if(departements.isPresent()){
            departementRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }

    public Departement updateDepartement(Integer id, Departement departement){
        Departement existDepartement = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        create(departement);
        return departement;
    }
}
