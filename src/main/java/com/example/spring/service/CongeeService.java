package com.example.spring.service;

import com.example.spring.model.Congee;
import com.example.spring.model.Employee;
import com.example.spring.repository.CongeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CongeeService {
    @Autowired
    private CongeeRepository congeeRepository;

    public Congee create(Congee congee){
        congeeRepository.save(congee);
        return congee;
    }

    public List<Congee> findAll(){
        return congeeRepository.findAll();
    }

    public Optional<Congee> findById(Integer id){
        return congeeRepository.findById(id);
    }

    public List<Congee> findBetweenDateDebutAndDateFin(Date debut, Date fin){
        return congeeRepository.findBetweenDateDebutAndDateFin(debut,fin);
    }

    public Integer TotalMois(Integer mois, Integer annee, Integer id_employee){
        return congeeRepository.TotalMois(mois, annee, id_employee);
    }

    public List<Congee> findByDateDebutAndFonction(Date debut, Integer id_fonction){
        return congeeRepository.findByDateAndFonction(debut, id_fonction);
    }

    public List<Congee> findByEmployee(Employee employee){
        return congeeRepository.findByEmployee(employee);
    }

    public void delete(Integer id){
        Optional<Congee> congees = findById(id);
        if(congees.isPresent()){
            congeeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }

    public Congee update(Integer id, Congee congee){
        Congee existCongee = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        create(congee);
        return congee;
    }

    public List<Congee> findCongeeNonValider(){
        return congeeRepository.findCongeeNonValider();
    }
}
