package com.example.user_manager_v1;

/**

 The Company class represents a company with a name and a candidate name.

 @author Aryan Rao
 */
public class Company {

    private String name; // The name of the company
    private String candidateName; // The name of the candidate

    /**

     Constructs a new Company object with the given name.
     @param name the name of the company
     */
    public Company(String name) {
        this.name = name;
    }
    /**

     Returns the name of the company.
     @return the name of the company
     */
    public String getName() {
        return name;
    }
    /**

     Returns the name of the candidate.
     @return the name of the candidate
     */
    public String getCandidateName() {
        return candidateName;
    }
}
