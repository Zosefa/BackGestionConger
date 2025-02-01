package com.example.spring.controller;

import com.example.spring.Request.ConnexionRequest;
import com.example.spring.conf.JWTManager;
import com.example.spring.model.Users;
import com.example.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTManager JWT;

    @Autowired
    private ConnexionRequest connexionRequest;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Users users){
        try{
            usersService.createUser(users);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ConnexionRequest connexion) throws Exception{
        try {
            Users users = usersService.findByEmailAndPassword(connexion.getEmail(), connexion.getPassword());
            return new ResponseEntity<>(JWT.generateToken(users), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/real")
    public ResponseEntity<Object> realToken(@RequestHeader("Authorization") String token){
        System.out.println(token);
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String Email = JWT.getClaim(token, "email");
        return ResponseEntity.ok(Email);
        
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        JWT.destroyToken(token);
        return ResponseEntity.ok("Déconnexion réussie, token invalidé");
    }
}
