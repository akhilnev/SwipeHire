package com.swipehire.Backend;


import com.swipehire.Backend.Candidate.*;
import com.swipehire.Backend.Managers.ManagerRepository;
import com.swipehire.Backend.Managers.Managers;
import com.swipehire.Backend.Users.UserRepository;
import com.swipehire.Backend.Users.Users;
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


import java.util.Arrays;
import java.util.HashSet;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)

public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    ManagerRepository managerRepository;
    //@LocalServerPort
    //int port = 8080;

    @Before
    public void setUp() {
        //RestAssured.port = port;
        RestAssured.baseURI = "http://coms-309-017.class.las.iastate.edu:8080";
    }




    //Akhilesh Tests
    //FEATURE - UPDATE RESUME REVIEWS
    //TESTING ADDITION OF Resume Addition Feature for a Candidate
    @Test
    public void testAddResumeDetails() {
        // Create a candidate with the desired fields
        String userid = "testuserid";
        String workExperience = "Test work experience";
        String projects = "Test projects";
        String awardsAndAccomplishments = "Test awards and accomplishments";
        String leadershipAndExtra = "Test leadership and extracurricular activities";
        String name = " Kausshik";
        String major = "CPRE";
        //String userid, String password, String name, String major, String skillString
        Candidate candidate = new Candidate(userid, "", name, major, "");
        candidateRepository.save(candidate);

        // Call the addResumeDetails method
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(candidate)
                .when()
                .put("/candidates/updateResume/testuserid/Test work experience/Test projects/Test awards and accomplishments/Test leadership and extracurricular activities");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String result = response.getBody().asString();

        // Assert that the return value matches the expected value
        StringBuilder expectedResume = new StringBuilder();
        expectedResume.append("Resume for ").append(candidate.getName()).append("\n\n");
        expectedResume.append("Email: ").append(userid).append("\n");
        expectedResume.append("Major: ").append(candidate.getMajor()).append("\n");
        expectedResume.append("Bio: ").append(candidate.getBio()).append("\n\n");
        expectedResume.append("Skills: ").append(candidate.getSkills().toString()).append("\n\n");
        expectedResume.append("Work Experience:\n").append(workExperience).append("\n\n");
        expectedResume.append("Projects:\n").append(projects).append("\n\n");
        expectedResume.append("Awards and Accomplishments:\n").append(awardsAndAccomplishments).append("\n\n");
        expectedResume.append("Leadership and Extracurricular Activities:\n").append(leadershipAndExtra).append("\n\n");
        String expected = expectedResume.toString() ;


        assertEquals(expected, result);
    }

    @Test
    public void testCandidateLogin() {
        // Create a test candidate and a user
        Candidate testCandidate = new Candidate("testuserid", "testpassword", "Test Candidate", "Computer Science", "Golang");
        Users testUser = new Users("testuserid", "testpassword");
        userRepository.save(testUser);
        candidateRepository.save(testCandidate);


        // Send request to login and receive a response
        Response response = RestAssured.given()
                .contentType("application/json")
                .with().body(testUser)
                .when()
                .post("/users/login");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String responseString = response.getBody().asString();
        System.out.println(responseString);
        assertEquals("true,1", responseString);
        // Delete the test candidate from the database
        candidateRepository.delete(testCandidate);
        userRepository.delete(testUser);
    }


    @Test
    public void testRecommendJobs(){
        // Create test managers with company names
        Managers manager1 = new Managers("Manager1", "m1testuserid" , "m1" , "Company1");
        Managers manager2 = new Managers("Manager2", "m2testuserid" , "m2" , "Company2");
        Managers manager3 = new Managers("Manager3", "m3testuserid" , "m3" , "Company3");
        Managers manager4 = new Managers("Manager4", "m4testuserid" , "m4" , "Company4");
        // Create candidate for Testing
        Candidate candidate1 = new Candidate("testCandidate", "testPassword" , "Test Candidate" ,"Everything", "Java,R,Data,Aerodynamics");



        // Save the test candidate to the database
        candidateRepository.save(candidate1);

        //Populate with jobs
        manager1.setJobtype("Software Engineer");
        manager1.setSalary("100000");
        manager1.setLocation("NYC");
        manager1.setRequiredSkills("Java");

        manager2.setJobtype("Data Engineer");
        manager2.setSalary("10000");
        manager2.setLocation("LA");
        manager2.setRequiredSkills("R, Data");

        manager3.setJobtype("Aerospace Engineer");
        manager3.setSalary("555000");
        manager3.setLocation("Chicago");
        manager3.setRequiredSkills("Aerodynamics");

        manager4.setJobtype("Football Coach");
        manager4.setSalary("200000");
        manager4.setLocation("Manchester");
        manager4.setRequiredSkills("Training, Coaching");

        // Save the test managers to the database
        managerRepository.save(manager1);
        managerRepository.save(manager2);
        managerRepository.save(manager3);
        managerRepository.save(manager4);


        // Send request and receive response
        Response response = RestAssured.given()
                .pathParam("userid", candidate1.getUserid())
                .when()
                .get("/candidates/{userid}/recommend/");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String responseString = response.getBody().asString();
        System.out.println(responseString);
        String[] arr = responseString.substring(1, responseString.length()-1).split(", ");
        HashSet<String> set = new HashSet<>(Arrays.asList(arr));
        assertEquals(true, set.contains("Company1"));
        assertEquals(true, set.contains("Company2"));
        assertEquals(true, set.contains("Company3"));
        assertEquals(true, !set.contains("Company4"));// Expected 2 unique company names

        // Delete the test managers from the database
        managerRepository.delete(manager1);
        managerRepository.delete(manager2);
        managerRepository.delete(manager3);
        managerRepository.delete(manager4);

        // Delete the test candidate from the database
        candidateRepository.delete(candidate1);


    }










}
