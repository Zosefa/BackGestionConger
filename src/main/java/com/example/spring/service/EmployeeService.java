package com.example.spring.service;

import com.example.spring.model.Employee;
import com.example.spring.model.Fonction;
import com.example.spring.model.Grade;
import com.example.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Integer id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findByNomOrPrenom(String nom, String prenom){
        return employeeRepository.findByNomOrPrenom(nom,prenom);
    }

    public Employee findByMatricule(String matricule){
        return employeeRepository.findByMatricule(matricule);
    }

    public List<Employee> findByFonctionId(Fonction fonction){
        return employeeRepository.findByFonction(fonction);
    }

    public List<Employee> findByGradeId(Grade grade){
        return employeeRepository.findByGrade(grade);
    }

    public void delete(Integer id){
        Optional<Employee> employees = findById(id);
        if(employees.isPresent()){
            employeeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Donnée non trouver!");
        }
    }

    public Employee update(Integer id, Employee employee){
        Employee existEmployee = findById(id)
                .orElseThrow(() -> new RuntimeException("Donnée non trouver!"));
        create(employee);
        return employee;
    }
}
