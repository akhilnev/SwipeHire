package com.swipehire.Backend;


import com.swipehire.Backend.Reviews.Reviews;
import com.swipehire.Backend.Reviews.ReviewsRepository;
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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.junit.Assert;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewsController {

   // @LocalServerPort
    //private int port;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Before
    public void setUp() {
        //RestAssured.port = port;
        RestAssured.baseURI = "http://coms-309-017.class.las.iastate.edu:8080";
    }

    // Akhilesh Tests
    // Testing Reviews Feature ( If details are put accordingly or not )
    @Test
    public void testAddReview() {
        // Create a test review
        String candidateUserId = "testuser";
        String companyName = "testcompany";
        String review = "This is a test review";
        int rating = 4;
        String interviewProcess = "The interview process was smooth";
        Reviews testReview = new Reviews(candidateUserId, companyName, review, rating, interviewProcess);

        // Send the request to add the review
        Response response = given().contentType(ContentType.JSON)
                .with().body(testReview)
                .when().post("/addReviews/{candidateUserId}/{companyName}/{review}/{rating}/{interviewProcess}", candidateUserId, companyName, review, rating, interviewProcess)
                .then().extract().response();

        // Check that the response is successful and the review was added
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.asString());
        assertTrue(response.asString().contains("testuser"));
        assertTrue(response.asString().contains("testcompany"));
        assertTrue(response.asString().contains("This is a test review"));
        assertTrue(response.asString().contains("4"));
        assertTrue(response.asString().contains("The interview process was smooth"));

        // Delete the test review from the database
        reviewsRepository.delete(testReview);
    }


    @Test
    public void testGetReviews() {
        // Create some test reviews in the database
        Reviews review1 = new Reviews("testuser1", "testcompany1", "This is a test review 1", 3, "The interview process was smooth for me");
        Reviews review2 = new Reviews("testuser2", "testcompany1", "This is a test review 2", 4, "The interview process was a bit slow");
        Reviews review3 = new Reviews("testuser3", "testcompany2", "This is a test review 3", 5, "The interview process was quick and efficient");
        reviewsRepository.saveAll(Arrays.asList(review1, review2, review3));

        // Send the request to get the reviews for "testcompany1"
        Response response = given().contentType(ContentType.JSON)
                .when().get("/getReviews/{companyName}", "testcompany1")
                .then().extract().response();

        // Check that the response is successful and the correct reviews are returned
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.asString());
        assertTrue(response.asString().contains("Reviews for testcompany1"));
        assertTrue(response.asString().contains("This is a test review 1"));
        assertTrue(response.asString().contains("This is a test review 2"));
        assertFalse(response.asString().contains("This is a test review 3"));

        // Delete the test reviews from the database
        reviewsRepository.deleteAll(Arrays.asList(review1, review2, review3));
    }

}
