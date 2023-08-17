package com.swipehire.Backend.Managers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swipehire.Backend.AppliedCandidates.AppliedCandidates;
import com.swipehire.Backend.Candidate.Candidate;

//import com.swipehire.Backend.Jobs.Jobs;

import jakarta.persistence.*;
import com.swipehire.Backend.Users.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name="Managers")
public class Managers {
    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Managers (String name, String userid, String password, String companyName) {
         this.password = password;
         this.name = name;
         this.userid = userid;
         this.companyName = companyName;
         this.jobtype = "";
         this.location = "";
         this.salary = "";
         this.requiredSkills = "";


         appliedCandidates = new ArrayList<>();
    }

    private int id;
    private String name;
    @Id
    private String userid;

    private String password;


    private String companyName;


    private String companyDescription;

    private String jobtype;

    private String location;

    private String salary;

    private String requiredSkills;

    public String getAcceptedCandidates() {
        return acceptedCandidates;
    }

    public void setAcceptedCandidates(String acceptedCandidate) {
        this.acceptedCandidates += acceptedCandidate + ", ";
    }

    private String acceptedCandidates = "";

   @OneToMany
   private List<AppliedCandidates> appliedCandidates;// one company can map to multiple applied candidates



    @OneToOne
    @JsonIgnore
    private Users user;


//    @Embedded
//    private Jobs job;



    public Managers() {
        appliedCandidates = new ArrayList<>();
    }


    public void setAppliedCandidates(String candidateUserid) {
        this.appliedCandidates = appliedCandidates;
    }


    public List<AppliedCandidates> getAppliedCandidates() {
        return appliedCandidates;
    }


    public int getManagerID() {
        return id;
    }

    public void setManagerID(int managerID) {
        id = managerID;
    }

    public String getName() {
        return name;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String emailaddress) {
        this.userid = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Jobs getJob() {
//        return job;
//    }
//
//    public void setJob(Jobs job) {
//        this.job = new Jobs(job.getJobtype(), job.getLocation(), job.getSalary());
//    }


    public void addAppliedCandidates(AppliedCandidates ap){
        this.appliedCandidates.add(ap);
    }

    @Override
    public String toString() {
        return "Managers{" +
                ", name='" + name + '\'' +
                ", userid='" + userid + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                '}';
    }
}
