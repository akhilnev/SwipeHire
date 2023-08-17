package com.swipehire.Backend.Managers;

import com.swipehire.Backend.Candidate.Candidate;
import com.swipehire.Backend.Candidate.CandidateRepository;
//import com.swipehire.Backend.Jobs.Jobs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.swipehire.Backend.Users.*;

import java.util.*;


@RestController
public class ManagerController {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping(path = "/Managers") // getting a list of all companies displayed
    @Operation(summary = "Get a list of all managers details displayed", description = "Retrieves a list of all Managers." , responses = {
            @ApiResponse(responseCode= "200" , description = " All managers are sent succesfully !"),
            @ApiResponse(responseCode = "400", description = "Bad request initiated!"),
            @ApiResponse(responseCode = "404", description = "Managers not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")

    })
    List<Managers> getAllManagers(){
        return managerRepository.findAll();
    }


    @GetMapping(path = "/Managers/{userid}") // accesing a company using its respective ID
    @Operation(summary = "Get a manager by ID" , description = "Retrieves a Specefic manager by ID." , responses = {
            @ApiResponse(responseCode= "200" , description = " Manager Found and Returned !"),
            @ApiResponse(responseCode = "404", description = "Manager not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @Parameter(name = "userid", description = "User ID of the manager", example = "akhilnev@iastate.edu", required = true)
    Managers getManagerById(@PathVariable @Parameter(description = "Manager ID", required = true) String userid){
        return managerRepository.findByUserid(userid);
    }



    @PostMapping(path = "/Managers/add") // adding a new company using request body parameter
    @Operation(summary = "We can add a specefic manager sent as a Json body to the server", responses = {
            @ApiResponse(responseCode= "200" , description = " Manager has been added succesfully!"),
            @ApiResponse(responseCode = "400", description = "Bad request initiated!"),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    String createManager(@RequestBody Managers newManager)
    {
        managerRepository.save(newManager);
        //companyRepository.save(new Company(newManager.getCompanyName(),newManager.getUserid()));//get company not working as company is not getting created
        userRepository.save(new Users(newManager.getUserid(), newManager.getPassword()));
        return "true";
    }

    @PutMapping(path = "/Managers/{companyDescription}/{userId}")
    @Operation(summary = "We can add a Company Description sent as a Parameter and the corresponding manager is identitfied using the userid also sent as a paramter", responses = {
            @ApiResponse(responseCode= "200" , description = " Company description for a Manager has been added succesfully!"),
            @ApiResponse(responseCode = "400", description = "Bad request initiated!")
    })
    String AddCompanyDescription(@PathVariable @Parameter(description = "Company Description to add", example = "This is a sample company description", required = true)
                                 String companyDescription ,@PathVariable  @Parameter(description = "User ID of the Manager to update", example = "akhilnev@iastate.edu", required = true) String userId){
        Managers manager = managerRepository.findByUserid(userId);
        manager.setCompanyDescription(companyDescription);
        managerRepository.save(manager);
        return manager.toString();
    }



    //returns all companyNames as a string
    @GetMapping (path="/Managers/getCompanies") //ok
    @Operation(summary = "We get all companies linked to each manager", description = "List of all companies are sent to frontend" , responses = {
            @ApiResponse(responseCode= "200" , description = " Companies have been succesfully sent to frontend!"),
            @ApiResponse(responseCode = "400", description = "Bad request initiated!"),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<String> getCompaniesByName(){
        List<String> ans = new ArrayList<String>();
        for(Managers manager : managerRepository.findAll()){
            if(manager.getCompanyName()!= null && !(ans.contains(manager.getCompanyName())))ans.add(manager.getCompanyName());
        }
        //have to fix where it says "[" in the start and in th end
        return ans;
    }

    @GetMapping(path = "/Managers/getDetails/{companyName}")
    @Operation(summary = "Get Managers by Company Name" , responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved managers"),
            @ApiResponse(responseCode = "404", description = "Managers not found")
    })
    @Parameter(name = "companyName", description = "Name of the company to retrieve Managers for", example = "Citadel", required = true)
    String returnnetdetails(@PathVariable String companyName){
        ArrayList<Managers> manager = managerRepository.findByCompanyName(companyName);
        return manager.toString();
    }

   @DeleteMapping(path = "/removeManager/{userid}")
   @Operation(summary = "Remove a Manager by UserId" , responses = {
           @ApiResponse(responseCode = "200", description = "Successfully removed Manager"),
           @ApiResponse(responseCode = "404", description = "Manager not found")
   })
   @Parameter(name = "userid", description = "User ID of the Manager to remove", example = "1234", required = true)

   String removeManager(@PathVariable String userid){
        managerRepository.deleteByUserid(userid);
        userRepository.deleteByUserid(userid);
        return " Manager is removed";
   }

    //check if the same works
    @PutMapping("/Managers/update/{userid}")
    @Operation(summary = "Update a Manager", responses = {
            @ApiResponse(responseCode = "200", description = "Manager updated successfully"),
            @ApiResponse(responseCode = "404", description = "Manager not found")
    })
    @Parameter(name = "userid", description = "User ID of the manager to be updated", example = "manager123@gmail.com", required = true)
    Managers updateManager(@PathVariable String userid, @RequestBody Managers newManager){
        Managers manager = managerRepository.findByUserid(userid);
        if(manager == null)
            return null;
        managerRepository.save(newManager);
        userRepository.save(new Users(newManager.getUserid(), newManager.getPassword()));
        return managerRepository.findByUserid(userid);
    }

    @GetMapping("/Manager/search/{skill}")
    @Operation(summary = "Get Candidates by Skill specefic to candidates applied to this manager",
            parameters = {@Parameter(name = "skill", description = "Searcing for candidate by skill", example = "Java", required = true)})
    @ApiResponse(responseCode = "200", description = "Successfully retrieved candidates")
    public List<String> getCandidatesBySkill(@PathVariable String skill) {
        List<String> res = new ArrayList<>();
        for(Candidate candidate : candidateRepository.findAll()){
            for(String s : candidate.getSkills()){
                if (skill.equals(s)){
                    res.add(candidate.getName());
                }
            }
        }
        return res;
        //return candidateRepository.findAllBySkillsContaining(skill);
    }

    @DeleteMapping(path= "/Managers/clear")
    @Operation(summary = "Remove all managers", responses = {
            @ApiResponse(responseCode = "200", description = "All instances of managers deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    String removeAllManagers(){
        // use this method with caution as it will delete all records present in table
        managerRepository.deleteAll();
        return "All instances of managers deleted";
    }



    @GetMapping(path = "Managers/getCompanyByJobLocationSalary/{job}/{location}/{salary}")
    @Operation(summary = "Get Companies by Job, Location and Salary", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved companies"),
            @ApiResponse(responseCode = "404", description = "Companies not found")
    })
    @Parameter(name = "job", description = "Job type of the company", example = "Software Engineer")
    @Parameter(name = "location", description = "Location of the company", example = "New York")
    @Parameter(name = "salary", description = "Salary range of the company", example = "100000-150000")
    List<String> getCompanybyJLS(@PathVariable String job , @PathVariable String location , @PathVariable String salary){
        List<Managers> arr = new ArrayList<>();
        //ArrayList<Managers> arrJLS = managerRepository.findByJobtypeAndLocationAndSalary(job,location,salary);
        if(salary.compareTo("NA")==0 && location.compareTo("NA")==0){
             arr = managerRepository.findByJobtype(job);
        }else if(job.compareTo("NA")==0 && salary.compareTo("NA")==0){
             arr = managerRepository.findByLocation(location);
        }else if(job.compareTo("NA")==0 && location.compareTo("NA")==0){
             arr = managerRepository.findBySalary(salary);
        }else if(salary.compareTo("NA")==0){
             arr = managerRepository.findByJobtypeAndLocation(job,location);
        }else if(location.compareTo("NA")==0 ){
            arr = managerRepository.findByJobtypeAndSalary(job,salary);
        }else if(job.compareTo("NA")==0){
             arr = managerRepository.findBySalaryAndLocation(salary,location);
        }else{
             arr = managerRepository.findByJobtypeAndLocationAndSalary(job,location,salary);
        }
        //We get a unique set of managers with all combinations
        HashSet<String> comp = new HashSet<>();
        for(Managers manager: arr){
                comp.add(manager.getCompanyName());
        }
        
        List<String> companies = new ArrayList<String>(comp);
        return companies;

    }



    @PutMapping("/Manager/{userid}/addjob/{jobtype}/{location}/{salary}/{requiredSkills}")
    @Operation(summary = "Add Job Details for a Manager", responses = {
            @ApiResponse(responseCode = "200", description = "Job details added successfully"),
            @ApiResponse(responseCode = "404", description = "Manager not found")
    })
    @Parameter(name = "userid", description = "User ID of the manager to add job details for", example = "1234", required = true)
    @Parameter(name = "jobtype", description = "Job type of the manager", example = "Software Engineer", required = true)
    @Parameter(name = "location", description = "Location of the job", example = "New York", required = true)
    @Parameter(name = "salary", description = "Salary range of the job", example = "100000-150000", required = true)
    public ResponseEntity<String> addJob(@PathVariable String userid, @PathVariable String jobtype, @PathVariable String location, @PathVariable String salary, @PathVariable String requiredSkills)  {
        Managers manager = managerRepository.findByUserid(userid);

        if (manager == null) {
            return ResponseEntity.notFound().build(); // return a 404 error response if the manager with the given user ID does not exist
        }

        manager.setJobtype(jobtype);
        manager.setLocation(location);
        manager.setSalary(salary);
        manager.setRequiredSkills(requiredSkills);
        managerRepository.save(manager);

        
        return ResponseEntity.ok("Job added successfully"); // return a 200 OK response if the job was added successfully
    }

//    }

    @GetMapping(path = "/Manager/{companyName}/getJobDetails")
        public String getJobDetails(@PathVariable String companyName){
            List<Managers> arr = managerRepository.findByCompanyName(companyName);
            String jobDetails = "";
            for(Managers manager: arr){
                jobDetails += manager.getCompanyName();
                jobDetails += "$";
                jobDetails += manager.getJobtype();
                jobDetails += "$";
                jobDetails += manager.getLocation();
                jobDetails += "$";
                jobDetails += manager.getSalary();
                jobDetails += "$";
                String requiredSkillString = manager.getRequiredSkills();
//                List<String> reqSkills = new ArrayList<>(Arrays.asList(requiredSkillString.split(", ")));
                jobDetails += requiredSkillString;
                //jobDetails += manager.getRequiredSkills() ;
            }
            return jobDetails;
        }

}
