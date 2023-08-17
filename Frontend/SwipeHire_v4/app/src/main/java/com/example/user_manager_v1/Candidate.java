package com.example.user_manager_v1;

public class Candidate {

    private  String full_name;
    private String bio, email, password, confirm;

    public Candidate(String name) {
        this.full_name = name;
    }

    public Candidate() {
        this.full_name = "";
    }

    public void setName(String name) {
        this.full_name = name;
    }

    public Candidate(String name, String bio) {
        this.full_name = name;
        this.bio = bio;
    }

    public String getName() {
        return full_name;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}
