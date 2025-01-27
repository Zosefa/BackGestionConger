package com.example.spring.repository;

import com.example.spring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Boolean findByEmailAndPassword(String email, String password);

    Users findByEmail(String email);
}
