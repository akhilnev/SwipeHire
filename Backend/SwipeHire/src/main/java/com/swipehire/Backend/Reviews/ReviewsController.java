package com.swipehire.Backend.Reviews;


import com.swipehire.Backend.Candidate.CandidateRepository;
import com.swipehire.Backend.Managers.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewsController {

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    ManagerRepository managerRepository;

    @PostMapping(path = "/addReviews/{candidateUserId}/{companyName}/{review}/{rating}/{interviewProcess}")
        public String addReview(@PathVariable String candidateUserId ,@PathVariable String companyName , @PathVariable String review , @PathVariable int rating, @PathVariable String interviewProcess ){
        Reviews temp = new Reviews(candidateUserId,companyName,review,rating,interviewProcess);
        reviewsRepository.save(temp);
        return temp.toString();
        }

    @GetMapping(path="/getReviews/{companyName}")
    public String getReviews(@PathVariable String companyName){
        List<Reviews> arr = reviewsRepository.findReviewsByCompanyName(companyName);

        StringBuilder sb = new StringBuilder();
        sb.append("Reviews for ").append(companyName).append(":\n");

        for (Reviews r : arr) {
            sb.append(r.toString()).append("\n\n");
        }

        // You can return the string directly, or save it to a variable and return later
        return sb.toString();
    }


}
