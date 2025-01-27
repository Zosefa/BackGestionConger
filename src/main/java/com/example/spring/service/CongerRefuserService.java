package com.example.spring.service;

import com.example.spring.model.CongerRefuser;
import com.example.spring.repository.CongerRefuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongerRefuserService {
    @Autowired
    private CongerRefuserRepository congerRefuserRepository;

    public CongerRefuser create(CongerRefuser congerRefuser){
        congerRefuserRepository.save(congerRefuser);
        return congerRefuser;
    }
    public List<CongerRefuser> findAll(){
        return congerRefuserRepository.findAll();
    }
    public Optional<CongerRefuser> findById(Integer id){
        return  congerRefuserRepository.findById(id);
    }
    public CongerRefuser update(Integer id, CongerRefuser refus){
        CongerRefuser refuser = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        create(refus);
        return refus;
    }
    public void dalete(Integer id){
        Optional<CongerRefuser> refuser = findById(id);
        if(refuser.isPresent()){
            congerRefuserRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }
}
