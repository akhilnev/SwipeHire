package com.swipehire.Backend.Users;

import com.swipehire.Backend.Candidate.CandidateRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

@RestController
public class UserController {
    //letting the db know that I wanna work with this
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;

    //Implemented all CRUD operations
//    @GetMapping(path = "/users/all")
//    @Operation(summary = "Get all users", description = "Returns a list of all users in the database.")
//    @ApiResponse(responseCode = "200", description = "List of all users retrieved successfully.")
//    List<Users> getAllUsers(){
//        return userRepository.findAll();
//    }
//
//    @GetMapping(path = "/users/{userid}")
//    @Operation(summary = "Get user by userid", description = "Returns the user with the specified userid.")
//    @ApiResponse(responseCode = "200", description = "User retrieved successfully.")
//    Users getUserByUserId(@PathVariable String userid){
//        return userRepository.findUsersByUserid(userid);
//    }
//
//    @PostMapping(path = "/users/add")
//    @Operation(summary = "Add new user", description = "Adds a new user to the database.")
//    @ApiResponse(responseCode = "200", description = "User added successfully.")
//    Users PostUserByBody(@RequestBody Users user){
//        if (user == null)
//            return null;
//        userRepository.save(user);
//        return user;
//    }
//
//    @PostMapping(path = "/users/login")
//    @Operation(summary = "Log user in", description = "Logs in a user with the specified credentials.")
//    @ApiResponse(responseCode = "200", description = "User logged in successfully.")
//    String LogUserIn(@RequestBody Users user){
//        if(user == null){
//            return "false";
//        }
//        if(user.getPassword().equals(userRepository.findUsersByUserid(user.getUserid()).getPassword())){
//            return "true";
//        }
//        return "false";
//    }
//
//    @PutMapping(path = "/users/update/{userid}")
//    @Operation(summary = "Update user by userid", description = "Updates the user with the specified userid.")
//    @ApiResponse(responseCode = "200", description = "User updated successfully.")
//    Users UpdateUserId(@RequestBody Users user){
//        if(user == null){return null;}
//        user.setUserid(user.getUserid());
//        return userRepository.findUsersByUserid(user.getUserid());
//    }
//    @PutMapping(path = "/users/update/password/{userid}")
//    @Operation(summary = "Update user password", description = "Updates the password of the user with the specified userid.")
//    @ApiResponse(responseCode = "200", description = "User password updated successfully.")
//    boolean UpdatePassword(@RequestBody Users user){
//        if(user == null){return false;}
//        user.setPassword(user.getPassword());
//        return true;
//    }
//
//    @DeleteMapping(path = "/users/delete/{userid}")
//    @Operation(summary = "Delete user by userid", description = "Deletes the user with the specified userid.")
//    @ApiResponse(responseCode = "200", description = "User deleted successfully.")
//    boolean DeleteAccount(@PathVariable String userid){
//        if(userid == null){return false;}
//        userRepository.deleteByUserid(userid);
//        return true;
//    }
    @GetMapping(path = "/users/all")
    @Operation(summary = "Get all users", description = "Returns a list of all users in the database.")
    @ApiResponse(responseCode = "200", description = "List of all users retrieved successfully.")
    List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{userid}")
    @Operation(summary = "Get user by userid", description = "Returns the user with the specified userid.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully.")
    Users getUserByUserId(@PathVariable @Parameter(description = "User ID of the user to retrieve", example = "johndoe", required = true) String userid){
        return userRepository.findUsersByUserid(userid);
    }

    @PostMapping(path = "/users/add")
    @Operation(summary = "Add new user", description = "Adds a new user to the database.")
    @ApiResponse(responseCode = "200", description = "User added successfully.")
    Users PostUserByBody(@RequestBody @Parameter(description = "The user object to add", required = true) Users user){
        if (user == null)
            return null;
        userRepository.save(user);
        return user;
    }

    @PostMapping(path = "/users/login")
    @Operation(summary = "Log user in", description = "Logs in a user with the specified credentials.")
    @ApiResponse(responseCode = "200", description = "User logged in successfully.")
    String LogUserIn(@RequestBody @Parameter(description = "The user object to log in", required = true) Users user){
        if(user == null){
            return "false";
        }
        if(user.getPassword().equals(userRepository.findUsersByUserid(user.getUserid()).getPassword())){
            if(candidateRepository.findByUserid(user.getUserid()) != null){
                return "true,1";
            }else{
                return "true,2";
            }
        }
        return "false"; //pushing
    }

    @PutMapping(path = "/users/update/{userid}")
    @Operation(summary = "Update user by userid", description = "Updates the user with the specified userid.")
    @ApiResponse(responseCode = "200", description = "User updated successfully.")
    Users UpdateUserId(@RequestBody @Parameter(description = "The user object with updated information", required = true) Users user){
        if(user == null){return null;}
        user.setUserid(user.getUserid());
        return userRepository.findUsersByUserid(user.getUserid());
    }

    @PutMapping(path = "/users/update/password/{userid}")
    @Operation(summary = "Update user password", description = "Updates the password of the user with the specified userid.")
    @ApiResponse(responseCode = "200", description = "User password updated successfully.")
    boolean UpdatePassword(@RequestBody @Parameter(description = "The user object with updated password", required = true) Users user){
        if(user == null){return false;}
        user.setPassword(user.getPassword());
        return true;
    }

    @DeleteMapping(path = "/users/delete/{userid}")
    @Operation(summary = "Delete user by userid", description = "Deletes the user with the specified userid.")
    @ApiResponse(responseCode = "200", description = "User deleted successfully.")
    boolean DeleteAccount(@PathVariable @Parameter(description = "User ID of the user to delete", example = "johndoe", required = true) String userid){
        if(userid == null){return false;}
        userRepository.deleteByUserid(userid);
        return true;
    }





}
