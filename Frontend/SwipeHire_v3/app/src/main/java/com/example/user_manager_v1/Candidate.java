package com.example.user_manager_v1;

/**
 * Represents a candidate for a job position.
 *
 * @author Aryan Rao
 */
public class Candidate {

    /** The candidate's full name */
    private String full_name;

    /** The candidate's biography */
    private String bio;

    /** The candidate's email address */
    private String email;

    /** The candidate's password */
    private String password;

    /** The candidate's password confirmation */
    private String confirm;

    /**
     * Creates a new candidate with the given name.
     *
     * @param name the candidate's name
     */
    public Candidate(String name) {
        this.full_name = name;
    }

    /**
     * Creates a new candidate with an empty name.
     */
    public Candidate() {
        this.full_name = "";
    }

    /**
     * Sets the candidate's name.
     *
     * @param name the candidate's name
     */
    public void setName(String name) {
        this.full_name = name;
    }

    /**
     * Creates a new candidate with the given name and biography.
     *
     * @param name the candidate's name
     * @param bio the candidate's biography
     */
    public Candidate(String name, String bio) {
        this.full_name = name;
        this.bio = bio;
    }

    /**
     * Returns the candidate's name.
     *
     * @return the candidate's name
     */
    public String getName() {
        return full_name;
    }

    /**
     * Returns the candidate's biography.
     *
     * @return the candidate's biography
     */
    public String getBio() {
        return bio;
    }

    /**
     * Returns the candidate's email address.
     *
     * @return the candidate's email address
     */
    public String getEmail(){
        return email;
    }

    /**
     * Returns the candidate's password.
     *
     * @return the candidate's password
     */
    public String getPassword(){
        return password;
    }

}
