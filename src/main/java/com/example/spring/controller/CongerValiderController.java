package com.example.spring.controller;

import com.example.spring.model.CongeValider;
import com.example.spring.model.Congee;
import com.example.spring.model.Employee;
import com.example.spring.model.Sexe;
import com.example.spring.service.CongeeService;
import com.example.spring.service.CongerValiderService;
import com.example.spring.service.EmployeeService;
import com.example.spring.service.SexeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CongerValider")
public class CongerValiderController {
    @Autowired
    private CongerValiderService congerValiderService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CongeeService congeeService;

    @GetMapping
    public ResponseEntity<Object> findAll() throws Exception{
        try{
            List<CongeValider> congeValiders = congerValiderService.findAll();
            return new ResponseEntity<>(congeValiders, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CongeValider congeValider) throws Exception{
        try{
            CongeValider congeValiders = congerValiderService.create(congeValider);
            Congee congee = congeeService.findById(congeValider.getCongee().getId_congee()).get();
            Employee employee = employeeService.findById(congee.getEmployee_id().getId_employee()).get();
            Integer newAnnuelConger = employee.getConge_annuel() - congee.getDurre();
            employee.setConge_annuel(newAnnuelConger);
            employeeService.update(employee.getId_employee(), employee);
            return new ResponseEntity<>(congeValiders, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            CongeValider congeValider = congerValiderService.findById(id).get();
            return new ResponseEntity<>(congeValider, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            congerValiderService.dalete(id);
            return new ResponseEntity<>("suppression effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
