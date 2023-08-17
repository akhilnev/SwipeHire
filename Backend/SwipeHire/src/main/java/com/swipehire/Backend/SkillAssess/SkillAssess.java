package com.swipehire.Backend.SkillAssess;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swipehire.Backend.Candidate.Candidate;
import jakarta.persistence.*;

@Entity
public class SkillAssess {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String SkillAssessment;

    private String score;

    private String candidateID;

//    @ManyToOne
//    @JoinColumn(name = "candidate_userid" , referencedColumnName = "userid")
//    @JsonIgnore
//    private Candidate candidate;


    //should create id automatically thus not there in constructor
    public SkillAssess(String skillAssessment, String score, String candidateName) {
        SkillAssessment = skillAssessment;
        this.score = score;
        this.candidateID = candidateName;
    }

    public SkillAssess() {
    }

    public String getSkillAssessment() {
        return SkillAssessment;
    }

    public void setSkillAssessment(String skillAssessment) {
        SkillAssessment = skillAssessment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }
}
