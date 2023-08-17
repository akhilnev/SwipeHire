package com.swipehire.Backend.AppliedCandidates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swipehire.Backend.Candidate.Candidate;
import com.swipehire.Backend.Managers.ManagerRepository;
import com.swipehire.Backend.Managers.Managers;
import jakarta.persistence.*;

@Entity
public class AppliedCandidates {

    @Id
    private String candidateName;//stores candidate name


    private String candidateEmail; // stores candidate EmailId

    private String position;


    private String companyName;

    private String userid;

    @ManyToOne
    @JoinColumn(name = "managers_userid", referencedColumnName = "userid")
    @JsonIgnore
    private Managers manager;



    //companyName and candidateName currently being sent



    //Constructor
    public AppliedCandidates(String candidateName, String position, String companyName , String candidateEmail) {
        this.candidateName = candidateName;
        this.position = position;
        this.companyName = companyName;
        this.candidateEmail = candidateEmail;
    }

    public AppliedCandidates() {
    }

    //GETTERS AND SETTERS

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }
    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String name) {
        this.candidateName = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
