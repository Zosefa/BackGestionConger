package com.example.spring.service;

import com.example.spring.model.Role;
import com.example.spring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role create(Role role){
        roleRepository.save(role);
        return role;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(Integer id){
        return roleRepository.findById(id).get();
    }
}
