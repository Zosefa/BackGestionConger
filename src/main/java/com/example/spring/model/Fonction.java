package com.example.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fonction")
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fonction")
    private int id_fonction;

    @Column(name = "fonction", nullable = false)
    private String fonction;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public int getId_fonction() {
        return id_fonction;
    }

    public void setId_fonction(int id_fonction) {
        this.id_fonction = id_fonction;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Departement getDepartement_id() {
        return departement;
    }

    public void setDepartement_id(Departement departement_id) {
        this.departement = departement_id;
    }
}
