package com.example.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sexe")
public class Sexe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sexe")
    private int id_sexe;

    @Column(name = "sexe", nullable = false)
    private String sexe;

    public int getId_sexe() {
        return id_sexe;
    }

    public void setId_sexe(int id_sexe) {
        this.id_sexe = id_sexe;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
}
