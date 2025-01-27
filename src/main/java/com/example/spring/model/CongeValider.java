package com.example.spring.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "conger_valider")
public class CongeValider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conger_valider")
    private int id_conger_valider;

    @OneToOne
    @JoinColumn(name = "id_congee")
    private Congee congee;

    @Column(name = "date_validation")
    private Date date_validation;

    @OneToOne
    @JoinColumn(name = "id_users")
    private Users users;

    public int getId_conger_valider() {
        return id_conger_valider;
    }

    public void setId_conger_valider(int id_conger_valider) {
        this.id_conger_valider = id_conger_valider;
    }

    public Congee getCongee() {
        return congee;
    }

    public void setCongee(Congee congee_id) {
        this.congee = congee_id;
    }

    public Date getDate_validation() {
        return date_validation;
    }

    public void setDate_validation(Date date_validation) {
        this.date_validation = date_validation;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users_id) {
        this.users = users_id;
    }
}
