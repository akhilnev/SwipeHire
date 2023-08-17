package com.swipehire.Backend.Reviews;

import com.swipehire.Backend.Candidate.Candidate;
import com.swipehire.Backend.Managers.Managers;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reviews {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String candidateUserId;

    private String companyName;

    private String review;

    private int rating;

    private String interviewProcess;

    public Reviews(String candidateUserId, String companyName, String review, int rating , String interviewProcess) {
        this.candidateUserId = candidateUserId;
        this.companyName = companyName;
        this.review = review;
        this.rating = rating;
        this.interviewProcess = interviewProcess;
    }

    public Reviews() {

    }

    public String getCandidateUserId() {
        return candidateUserId;
    }

    public void setCandidateUserId(String candidateUserId) {
        this.candidateUserId = candidateUserId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review by candidate " + candidateUserId + " for " + companyName + ":\n" +
                "Rating: " + rating + "\n" +
                "Review: " + review + "\n" +
                "Interview Process:" + interviewProcess;
    }
}
