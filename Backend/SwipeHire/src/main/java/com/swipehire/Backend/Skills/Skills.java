//package com.swipehire.Backend.Skills;
//
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Embeddable;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//@Embeddable
//public class Skills {
//    @ElementCollection
//    private List<String> skills = new ArrayList<>();
//
//    public Skills(String skillString) {
//        String[] temp = skillString.split(",");
//        this.skills.addAll(Arrays.asList(temp));
//    }
//
//    public void setSkills(List<String> skills) {
//        this.skills.addAll(skills);
//    }
//
//    public Skills() {
//
//    }
//
//    public void addSkill(String skillString) {
//        String[] temp = skillString.split(",");
//        this.skills.addAll(Arrays.asList(temp));
//    }
//
//    public List<String> getSkills() {
//        return skills;
//    }
//
//}
package com.swipehire.Backend.Skills;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Embeddable
public class Skills {
    @ElementCollection
    private List<String> skills = new ArrayList<>();

    public Skills(String skillString) {
        String[] temp = skillString.split(",");
        this.skills = new ArrayList<>(Arrays.asList(temp));
    }

    public Skills() {}

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

}
