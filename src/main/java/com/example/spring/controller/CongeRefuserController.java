package com.example.spring.controller;

import com.example.spring.model.CongerRefuser;
import com.example.spring.service.CongerRefuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CongerRefuser")
public class CongeRefuserController {
    @Autowired
    private CongerRefuserService congerRefuserService;

    @GetMapping
    public ResponseEntity<Object> findAll() throws Exception{
        try{
            List<CongerRefuser> congerRefusers = congerRefuserService.findAll();
            return new ResponseEntity<>(congerRefusers, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CongerRefuser congerRefuser) throws Exception{
        try{
            CongerRefuser congerRefusers = congerRefuserService.create(congerRefuser);
            return new ResponseEntity<>(congerRefusers, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            CongerRefuser congerRefuser = congerRefuserService.findById(id).get();
            return new ResponseEntity<>(congerRefuser, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            congerRefuserService.dalete(id);
            return new ResponseEntity<>("suppression effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
