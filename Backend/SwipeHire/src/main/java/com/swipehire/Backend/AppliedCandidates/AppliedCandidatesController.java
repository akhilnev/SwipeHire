package com.swipehire.Backend.AppliedCandidates;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppliedCandidatesController {
    @Autowired
    AppliedCandidatesRepository appliedCandidatesrepository;


    @PostMapping(path = "/apcandidate/add")
    @Operation(summary = "Add Applied Candidate", description = "Add an applied candidate to the database",
    responses = {
            @ApiResponse(responseCode = "200", description = "Candidate added to applied user pool"),
            @ApiResponse(responseCode = "400", description = "Json object sent was null")
    })
    public ResponseEntity<Map<String, Object>> addAppliedCandidate(@RequestBody AppliedCandidates apcandidate){
        if(apcandidate == null){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Nothing added as Json sent was null");
            return ResponseEntity.badRequest().body(response);
        }
        appliedCandidatesrepository.save(apcandidate);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Candidate added to applied user pool");
        return ResponseEntity.ok(response);
    }

    @GetMapping(path="/apcandidate")
    @Operation(summary = "Get All Applied Candidates", description = "Get all applied candidates from the database" , responses={
            @ApiResponse(responseCode = "200", description = "Applied candidates successfully retrieved !")
    })
    List<AppliedCandidates> getAllAPC(){
        return appliedCandidatesrepository.findAll();
    }



}
