package com.swipehire.Backend.Users;

import com.swipehire.Backend.Candidate.Candidate;
import com.swipehire.Backend.Managers.Managers;
import jakarta.persistence.*;

@Entity
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    private String userid;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_userid")
    private Candidate candidate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managers_userid")
    private Managers manager;


    // =============================== Constructors ================================== //
    public Users(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
    public Users(){}

    // =============================== Getters and Setters for each field ================================== //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
