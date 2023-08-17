package com.swipehire.Backend.SkillAssess;

import com.swipehire.Backend.AppliedCandidates.AppliedCandidates;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
public class SkillAssessController {
    @Autowired
    SkillAssessRepository skillAssessRepository;

    @PostMapping(path = "/skillAssess/add")
    @Operation(summary = "Add a new skill assessment taken by a candidate", responses = {
            @ApiResponse(responseCode = "200", description = "Skill assessment added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public String addSkillAssessment(@RequestBody SkillAssess skillA){
        if(skillA==null){
            return " Nothing added as Json sent was null";
        }
        skillAssessRepository.save(skillA);
        return " Candidate SkillAssesment added ";
    }

//    USE METHOD WITH CAUTION
       @DeleteMapping (path = "/skillAssess/clear")
       @Operation(summary = "Clear the Skill Assessment table", responses = {
               @ApiResponse(responseCode = "200", description = "Skill Assessment table cleared successfully"),
               @ApiResponse(responseCode = "500", description = "Internal server error")
       })
        public String clearSA(){
        skillAssessRepository.deleteAll();
        return " Table cleared ";
        }
        

        @GetMapping(path= "/skillAssess")
        @Operation(summary = "Get all skill assessments taken by all candidates ", responses = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved skill assessments"),
                @ApiResponse(responseCode = "404", description = "Skill assessments not found")
        })
        public List<SkillAssess> displayTable(){
        return skillAssessRepository.findAll();

        }


    }







