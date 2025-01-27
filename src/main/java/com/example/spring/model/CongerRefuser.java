package com.example.spring.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "conger_refuser")
public class CongerRefuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conger_refuser")
    private int id_conger_refuser;

    @OneToOne
    @JoinColumn(name = "id_congee")
    private Congee congee;

    @Column(name = "date_refus")
    private Date date_refus;

    @OneToOne
    @JoinColumn(name = "id_users")
    private Users users;

    public int getId_conger_refuser() {
        return id_conger_refuser;
    }

    public void setId_conger_refuser(int id_conger_refuser) {
        this.id_conger_refuser = id_conger_refuser;
    }

    public Congee getCongee() {
        return congee;
    }

    public void setCongee(Congee congee_id) {
        this.congee = congee_id;
    }

    public Date getDate_refus() {
        return date_refus;
    }

    public void setDate_refus(Date date_refus) {
        this.date_refus = date_refus;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users_id) {
        this.users = users_id;
    }
}
