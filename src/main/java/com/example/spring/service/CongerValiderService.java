package com.example.spring.service;

import com.example.spring.model.CongeValider;
import com.example.spring.repository.CongerValiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongerValiderService {
    @Autowired
    private CongerValiderRepository congerValiderRepository;

    public CongeValider create(CongeValider congeValider){
        congerValiderRepository.save(congeValider);
        return congeValider;
    }
    public List<CongeValider> findAll(){
        return congerValiderRepository.findAll();
    }
    public Optional<CongeValider> findById(Integer id){
        return  congerValiderRepository.findById(id);
    }
    public CongeValider update(Integer id, CongeValider valider){
        CongeValider existValide = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        create(valider);
        return valider;
    }
    public void dalete(Integer id){
        Optional<CongeValider> valider = findById(id);
        if(valider.isPresent()){
            congerValiderRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }
}
