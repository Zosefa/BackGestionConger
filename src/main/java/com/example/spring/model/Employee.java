package com.example.spring.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private int id_employee;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "date_naissance", nullable = false)
    private Date date_naissance;

    @Column(name = "conge_annuel", nullable = false)
    private int conge_annuel;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getConge_annuel() {
        return conge_annuel;
    }

    public void setConge_annuel(int conge_annuel) {
        this.conge_annuel = conge_annuel;
    }

    public Fonction getFonction_id() {
        return fonction;
    }

    public void setFonction_id(Fonction fonction_id) {
        this.fonction = fonction_id;
    }

    public Grade getGrade_id() {
        return grade;
    }

    public void setGrade_id(Grade grade_id) {
        this.grade = grade_id;
    }
}
