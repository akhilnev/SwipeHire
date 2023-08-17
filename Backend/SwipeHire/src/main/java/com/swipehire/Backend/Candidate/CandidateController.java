package com.swipehire.Backend.Candidate;

import com.swipehire.Backend.Managers.ManagerRepository;
import com.swipehire.Backend.Managers.Managers;
import com.swipehire.Backend.Users.UserRepository;
import com.swipehire.Backend.Users.Users;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.swipehire.Backend.Skills.*;
import com.swipehire.Backend.Candidate.*;
import org.springframework.http.*;

import java.util.*;

@RestController
public class CandidateController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ManagerRepository managerRepository;


    //Implemented all necessary CRUD operations
    @GetMapping(path = "/candidates/all")
    @Operation(summary = "Get all candidates", description = "Retrieves a list of all candidates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidates retrieved successfully.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Candidate.class)))),
            @ApiResponse(responseCode = "404", description = "No candidates found.")
    })
    List<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }

    @GetMapping(path = "/candidates/{userid}")
    @Operation(summary = "Get candidate by user ID", description = "Retrieves a candidate by their user ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate retrieved successfully.",
                    content = @Content(schema = @Schema(implementation = Candidate.class))),
            @ApiResponse(responseCode = "404", description = "Candidate not found.")
    })
    Candidate getCandidateByUserId(@PathVariable @Parameter(description = "User ID of the candidate to retrieve", example = "123456", required = true) String userid){
        return candidateRepository.findByUserid(userid);
    }

    @GetMapping(path = "/candidates/{name}")
    @Operation(summary = "Get candidate by name", description = "Retrieves a candidate by their name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate retrieved successfully.",
                    content = @Content(schema = @Schema(implementation = Candidate.class))),
            @ApiResponse(responseCode = "404", description = "Candidate not found.")
    })
    Candidate getCandidateByName(@PathVariable @Parameter(description = "Name of the candidate to retrieve", example = "John Doe", required = true) String name){
        if(name == null){return null;}
        return candidateRepository.findCandidateByName(name);
    }

    @PostMapping(path = "/candidates/add")
    @Operation(summary = "Add a new candidate", description = "Adds a new candidate to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate added successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request body.")
    })
    String postCandidate(@RequestBody @Parameter(description = "Candidate object to add", required = true) Candidate candidate){
        if(candidate == null){
            return "false";
        }
        candidateRepository.save(candidate);
        userRepository.save(new Users(candidate.getUserid(), candidate.getPassword()));
        return "true";
    }

    @PutMapping(path = "/candidates/update/{candidate}")
    @Operation(summary = "Update a candidate", description = "Updates a candidate's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate updated successfully.",
                    content = @Content(schema = @Schema(implementation = Candidate.class))),
            @ApiResponse(responseCode = "404", description = "Candidate not found.")
    })
    Candidate updateCandidate(@RequestBody @Parameter(description = "Candidate object to update", required = true) Candidate candidate){
        candidateRepository.save(candidate);
        userRepository.save(new Users(candidate.getUserid(), candidate.getPassword()));
        return candidate;
    }

    @DeleteMapping(path = "/candidates/delete/{userid}")
    @Operation(summary = "Delete a candidate", description = "Deletes a candidate from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Candidate not found.")
    })
    String removeCandidate(@PathVariable @Parameter(description = "User ID of the candidate to delete", example = "jdoe123", required = true) String userid){
        candidateRepository.deleteCandidateByUserid(userid);
        userRepository.deleteByUserid(userid);
        return "Candidate with user ID " + userid + " is removed";
    }
    @PutMapping(path = "/candidates/{userid}/addskills/{skillString}")
    @Operation(summary = "Add a set of skills to a candidate", description = "Adds a all skills separated by commas to a candidate's profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill added successfully."),
            @ApiResponse(responseCode = "404", description = "Candidate not found.")
    })
    void addSkills(@PathVariable @Parameter(description = "User ID of the candidate to update skills", example = "jdoe123", required = true) String userid, @PathVariable @Parameter(description = "A comma separated list of skills to be added", example = "Java, Python, C++", required = true) String skillString){
        Candidate candidate = candidateRepository.findCandidateByUserid(userid);
        candidate.getSkills().add(skillString);
        candidateRepository.save(candidate);
    }

    @GetMapping(path = "/candidates/{userid}/recommend/")
    String recommendJobs(@PathVariable String userid){
        List<String> recommendedCompanies = new ArrayList<>();
        List<String> skills = candidateRepository.findCandidateByUserid(userid).getSkills();
        Set<String> set = new HashSet<>(skills);
        for(Managers manager : managerRepository.findAll()){
            if(manager.getRequiredSkills()!= null){
                String requiredSkillString = manager.getRequiredSkills();
                List<String> reqSkills = new ArrayList<>(Arrays.asList(requiredSkillString.split(", ")));
                for(String req : reqSkills){
                    if(set.contains(req)){
                        recommendedCompanies.add(manager.getCompanyName());
                    }
                }
            }
        }
        return recommendedCompanies.toString();
    }
    //ADD NEW RESUME DETAILS TO MODIFIED CANDIDATE PROFILE
    @PutMapping(path= "/candidates/updateResume/{userid}/{workExperience}/{projects}/{awardsAndAccomplishments}/{leadershipAndExtra}")
    String addResumeDetails(@PathVariable String userid, @PathVariable String workExperience ,@PathVariable String projects,@PathVariable String awardsAndAccomplishments , @PathVariable String leadershipAndExtra) {
        Candidate candidate = candidateRepository.findCandidateByUserid(userid);
        candidate.setWorkExperience(workExperience);
        candidate.setProjects(projects);
        candidate.setAwardsAndAccomplishments(awardsAndAccomplishments);
        candidate.setLeadershipAndExtra(leadershipAndExtra);
        candidateRepository.save(candidate);
        return candidate.toString();
    }
    
    

    @GetMapping("/candidate/getAllResumes")
    String sendAllResumes(){
        List<Candidate> candidates = candidateRepository.findAll();
        String result = "";
        for(Candidate candidate : candidates){
            result += (candidate.toString());
            result += "$";
        }
        return result;
    }




}
