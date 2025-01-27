package com.example.spring.controller;

import com.example.spring.model.Employee;
import com.example.spring.model.Fonction;
import com.example.spring.model.Grade;
import com.example.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Employee> employees = employeeService.findAll();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Employee employee){
        try{
            Employee employees = employeeService.create(employee);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            Employee employee = employeeService.findById(id).get();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Object> findByMatricule(@PathVariable String matricule) throws Exception{
        try{
            Employee employee = employeeService.findByMatricule(matricule);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nomORprenom/{data}")
    public ResponseEntity<Object> findByNomOrPrenom(@PathVariable String data) throws Exception{
        try{
            List<Employee> employee = employeeService.findByNomOrPrenom(data,data);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fonction")
    public ResponseEntity<Object> findByFonction(@RequestBody Fonction fonction) throws Exception{
        try{
            List<Employee> employee = employeeService.findByFonctionId(fonction);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/grade")
    public ResponseEntity<Object> findByGrade(@RequestBody Grade grade) throws Exception{
        try{
            List<Employee> employee = employeeService.findByGradeId(grade);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            employeeService.delete(id);
            return new ResponseEntity<>("suppresion effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Employee employee) throws Exception{
        try{
            Optional<Employee> ExistingEmployee = employeeService.findById(id);
            if(ExistingEmployee.isPresent()){
                    ExistingEmployee.get().setMatricule(employee.getMatricule());
                    ExistingEmployee.get().setNom(employee.getNom());
                    ExistingEmployee.get().setPrenom(employee.getPrenom());
                    ExistingEmployee.get().setAdresse(employee.getAdresse());
                    ExistingEmployee.get().setTelephone(employee.getTelephone());
                    ExistingEmployee.get().setDate_naissance(employee.getDate_naissance());
                    ExistingEmployee.get().setConge_annuel(employee.getConge_annuel());
                    ExistingEmployee.get().setFonction_id(employee.getFonction_id());
                    ExistingEmployee.get().setGrade_id(employee.getGrade_id());
                employeeService.update(id,ExistingEmployee.get());
            }
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
