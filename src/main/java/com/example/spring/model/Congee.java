package com.example.spring.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "congee")
public class Congee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_congee")
    private int id_congee;

    @Column(name = "date_demande")
    private Date date_demande;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "durre")
    private int durre;

    @Column(name = "motif")
    private String motif;

    @OneToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public int getId_congee() {
        return id_congee;
    }

    public void setId_congee(int id_congee) {
        this.id_congee = id_congee;
    }

    public Date getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(Date date_demande) {
        this.date_demande = date_demande;
    }

    public Date getDate_debut() {
        return dateDebut;
    }

    public void setDate_debut(Date date_debut) {
        this.dateDebut = date_debut;
    }

    public Date getDate_fin() {
        return dateFin;
    }

    public void setDate_fin(Date date_fin) {
        this.dateFin = date_fin;
    }

    public int getDurre() {
        return durre;
    }

    public void setDurre(int durre) {
        this.durre = durre;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Employee getEmployee_id() {
        return employee;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee = employee_id;
    }
}
