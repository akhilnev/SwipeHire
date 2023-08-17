package com.swipehire.Backend;


import com.swipehire.Backend.AppliedCandidates.AppliedCandidates;
import com.swipehire.Backend.AppliedCandidates.AppliedCandidatesRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
public class AppliedCandidatesController {


   @Autowired
    AppliedCandidatesRepository appliedCandidatesRepository;

    //@LocalServerPort
    //int port = 8080;

    @Before
    public void setUp() {
       // RestAssured.port = port;
        RestAssured.baseURI = "http://coms-309-017.class.las.iastate.edu:8080";
    }


    // Akhilesh's Test
    // Testing addition of Applied Candidate in Table
    //Testing based on Response code returned by server on addition of an applied candidate
    @Test
    public void testAddAppliedCandidate() {
        // Create a new applied candidate object
        //String candidateName, String position, String companyName , String candidateEmail
        AppliedCandidates apcandidate = new AppliedCandidates("John Doe", "SWE intern", "Software Engineer","john@gmail.com");

        // Send POST request to add the candidate
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(apcandidate)
                .when()
                .post("/apcandidate/add");

        // Check response status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct message
        String message = response.jsonPath().getString("message");
        assertEquals("Candidate added to applied user pool", message);

        // Check that the candidate was added to the database
//        List<AppliedCandidates> candidates = appliedCandidatesRepository.findAll();
//        //assertEquals(1, candidates.size());
//        AppliedCandidates addedCandidate = candidates.get(0);
//        assertEquals("John Doe", addedCandidate.getCandidateName());
//        assertEquals("john@example.com", addedCandidate.getCandidateEmail());
//        assertEquals("Software Engineer", addedCandidate.getPosition());

        // Delete the added candidate from the database
        appliedCandidatesRepository.delete(apcandidate);
    }
}
