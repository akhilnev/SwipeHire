package com.swipehire.Backend.Candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swipehire.Backend.AppliedCandidates.AppliedCandidates;
import com.swipehire.Backend.SkillAssess.SkillAssess;
import com.swipehire.Backend.Users.Users;
import jakarta.persistence.*;
import com.swipehire.Backend.Skills.*;

import java.util.*;

@Entity
public class Candidate {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    private String userid;
    private String name;
    //private String dateOfBirth;
    private String major;
    //private String minor; //if none put NA or leave blank
    //private String classification; //Freshman, Sophomore, Junior, Senior
    private String password;

    private String bio;
    //@Embedded
    private Skills skills;
    @OneToOne
    @JsonIgnore
    private Users user;

    //Adding new fields to be updated in the RESUME USING A PUT METHOD

    private String workExperience;

    private String projects;

    private String awardsAndAccomplishments;

     private String leadershipAndExtra;
//    @OneToMany
//    private ArrayList<SkillAssess> skillAssess;


    // =============================== Constructors ================================== //
    public Candidate(String userid, String password, String name, String major, String skillString) {
        this.password = password;
        this.userid = userid;
        this.name = name;
        this.major = major;
        this.bio = "";
//        this.skillAssess = new ArrayList<>();
        this.skills = new Skills(skillString);
        //this.minor = minor;
        //this.classification = classification;
    }



    public Candidate() {
//        this.skillAssess = new ArrayList<>();
    }

    // =============================== Getters and Setters for each field ================================== //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    //Questionable getters and setters, might delete later
    public Users getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    //@JsonProperty("skills")
    public List<String> getSkills() {
        return skills.getSkills();
    }

    public void addSkill(String skillString){
        this.skills.addSkill(skillString);
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getAwardsAndAccomplishments() {
        return awardsAndAccomplishments;
    }

    public void setAwardsAndAccomplishments(String awardsAndAccomplishments) {
        this.awardsAndAccomplishments = awardsAndAccomplishments;
    }

    public String getLeadershipAndExtra() {
        return leadershipAndExtra;
    }

    public void setLeadershipAndExtra(String leadershipAndExtra) {
        this.leadershipAndExtra = leadershipAndExtra;
    }

    @Override
    public String toString() {
        StringBuilder resume = new StringBuilder();
        resume.append("Resume for ").append(name).append("\n\n");
        resume.append("Email: ").append(userid).append("\n");
        resume.append("Major: ").append(major).append("\n");
        resume.append("Bio: ").append(bio).append("\n\n");
        resume.append("Skills: ").append(skills.getSkills().toString()).append("\n\n");
        resume.append("Work Experience:\n").append(workExperience).append("\n\n");
        resume.append("Projects:\n").append(projects).append("\n\n");
        resume.append("Awards and Accomplishments:\n").append(awardsAndAccomplishments).append("\n\n");
        resume.append("Leadership and Extracurricular Activities:\n").append(leadershipAndExtra).append("\n\n");
        return resume.toString();
    }




    //    public ArrayList<SkillAssess> getSkillAssess() {
//        return skillAssess;
//    }
//
//    public void setSkillAssess(ArrayList<SkillAssess> skillAssess) {
//        this.skillAssess = skillAssess;
//    }
//
//    public void addSkills(SkillAssess sk){this.skillAssess.add(sk);}
}
