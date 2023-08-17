package com.swipehire.Backend;


import com.swipehire.Backend.Candidate.Candidate;
import com.swipehire.Backend.Managers.ManagerRepository;
import com.swipehire.Backend.Managers.Managers;
import com.swipehire.Backend.Reviews.ReviewsRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jakarta.servlet.http.PushBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerController {

//    @LocalServerPort
    private int port;

    @Autowired
    private ManagerRepository managerRepository;

    @Before
    public void setUp() {
        //RestAssured.port = 8080;
        RestAssured.baseURI = "http://coms-309-017.class.las.iastate.edu:8080";
    }



    //Akhilesh's Test
    //Testing Feature where List of companies returned must be with unique company Names
    // Add multiple companies with same names and check that they dont double count
    @Test
    public void testGetCompaniesByName() {
        // Create test managers with company names
        //String name, String userid, String password, String companyName
        Managers manager1 = new Managers("Manager1", "m1testuserid" , "m1" , "Company1");
        Managers manager2 = new Managers("Manager2", "m2testuserid" , "m2" , "Company2");
        Managers manager3 = new Managers("Manager3", "m3testuserid" , "m1" , "Company1");

        //Previously 25 Companies present
        //Adding 3 new companies but two companies have the same name so total companies returned should only be 25 + 2 . Total Should be 27 now
        // Save the test managers to the database
        managerRepository.save(manager1);
        managerRepository.save(manager2);
        managerRepository.save(manager3);

        // Send request and receive response
        Response response = RestAssured.given()
                .when()
                .get("/Managers/getCompanies");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        List<String> returnedCompanies = response.jsonPath().getList(".", String.class);
        assertEquals(27, returnedCompanies.size()); // Expected a total of 27 companies as of now unique company names
        assertTrue(returnedCompanies.contains("Company1"));
        assertTrue(returnedCompanies.contains("Company2"));

        // Delete the test managers from the database
        managerRepository.delete(manager1);
        managerRepository.delete(manager2);
        managerRepository.delete(manager3);
    }

    @Test
    public void addJobTest(){
        //Create test managers with company names
        //String name, String userid, String password, String companyName
        Managers manager1 = new Managers("Manager1", "m1testuserid" , "m1" , "Company1");

        // Save the test managers to the database
        managerRepository.save(manager1);

        // Send request and receive response
        Response response = RestAssured.given()
                .pathParam("userid", "m1testuserid")
                .pathParam("jobtype", "SWETestJob")
                .pathParam("location", "NYCTestLocation")
                .pathParam("salary", "100000")
                .pathParam("requiredSkills", "Java, Python, TestSkill")
                .when()
                .put("/Manager/{userid}/addjob/{jobtype}/{location}/{salary}/{requiredSkills}");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnedJob = response.getBody().asString();
        assertEquals("Job added successfully", returnedJob);

        // Delete the test managers from the database
        managerRepository.delete(manager1);

    }

    //TO GO ABOVE AND BEYOND WE WILL TEST THE GIVEN JOB USING FILER JOBS METHOD
    @Test
    public void filterJobs(){
        //Create test managers with company names
        //String name, String userid, String password, String companyName
        Managers manager1 = new Managers("Manager1", "m1testuserid" , "m1" , "Company1");

        // Save the test managers to the database
        managerRepository.save(manager1);

        // Send request and receive response
        Response response = RestAssured.given()
                .pathParam("userid", "m1testuserid")
                .pathParam("jobtype", "SWETestJob")
                .pathParam("location", "NYCTestLocation")
                .pathParam("salary", "100000TestSalary")
                .pathParam("requiredSkills", "Java, Python, TestSkill")
                .when()
                .put("/Manager/{userid}/addjob/{jobtype}/{location}/{salary}/{requiredSkills}");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response from the addJob Method
        String returnedJob = response.getBody().asString();
        assertEquals("Job added successfully", returnedJob);

        Response response1 = RestAssured.given()
                .pathParam("job", "SWETestJob")
                .pathParam("location", "NYCTestLocation")
                .pathParam("salary", "100000TestSalary")
                .when()
                .get("Managers/getCompanyByJobLocationSalary/{job}/{location}/{salary}");

        // Check status code
        int statusCode1 = response1.getStatusCode();
        assertEquals(200, statusCode1);

        // Check response body for correct response from the filterJobs Method
        List<String> returnedJobs = response1.jsonPath().getList(".", String.class);
        assertEquals(1, returnedJobs.size()); // Expected 1 unique job
        assertTrue(returnedJobs.contains("Company1")); // Expected Company1


        Response response2 = RestAssured.given()
                .pathParam("job", "NA")
                .pathParam("location", "NYCTestLocation")
                .pathParam("salary", "100000TestSalary")
                .when()
                .get("Managers/getCompanyByJobLocationSalary/{job}/{location}/{salary}");

        // Check status code
        int statusCode2 = response2.getStatusCode();
        assertEquals(200, statusCode1);

        // Check response body for correct response from the filterJobs Method
        List<String> returnedJobs2 = response2.jsonPath().getList(".", String.class);
        assertEquals(1, returnedJobs2.size()); // Expected 1 unique job
        assertTrue(returnedJobs2.contains("Company1")); // Expected Company1

        Response response3 = RestAssured.given()
                .pathParam("job", "SWETestJob")
                .pathParam("location", "NA")
                .pathParam("salary", "100000TestSalary")
                .when()
                .get("Managers/getCompanyByJobLocationSalary/{job}/{location}/{salary}");

        // Check status code
        int statusCode3 = response3.getStatusCode();
        assertEquals(200, statusCode1);

        // Check response body for correct response from the filterJobs Method
        List<String> returnedJobs3 = response3.jsonPath().getList(".", String.class);
        assertEquals(1, returnedJobs3.size()); // Expected 1 unique job
        assertTrue(returnedJobs3.contains("Company1")); // Expected Company1


        // Delete the test managers from the database
        managerRepository.delete(manager1);
    }
}
