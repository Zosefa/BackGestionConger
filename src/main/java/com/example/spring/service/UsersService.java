package com.example.spring.service;

import com.example.spring.model.Users;
import com.example.spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users users){
        usersRepository.save(users);
        return users;
    }
    public Users findById(Integer id){
        return usersRepository.findById(id).get();
    }
    public Users findByEmail(String email){
        return usersRepository.findByEmail(email);
    }
    public Users findByEmailAndPassword(String email, String password){
        Users users = findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password, users.getPassword())){
            return users;
        }else{
            throw new RuntimeException("verifier vos identifiant");
        }
    }

}
