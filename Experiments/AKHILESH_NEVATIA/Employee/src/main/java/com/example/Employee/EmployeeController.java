package com.example.Employee;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {
    HashMap<String, Employee> EmployeeList = new HashMap<String, Employee>();

   // Returns a Hashmap of all Employees with com.example.Employee.Employee ID and first Name
    //List Operation - CRUDL
    @GetMapping(path="/employee")
    public @ResponseBody HashMap<String, Employee> getEmployeeList(){
        return EmployeeList;
    }

    //WELCOME PAGE OF EMPLOYEE
    @GetMapping(path="/")
    public @ResponseBody String HomePage(){
        return "Hello welcome to our Employee Class";
    }

    //adds employee from the body given in postman
    //CREATE Operation - CRUDL
    @PostMapping("/employee/add")
    public @ResponseBody Employee addEmployee(@RequestBody Employee emp){
        EmployeeList.put(emp.getFirstName(),emp);
        return emp;
    }

    //Gives out the date employee account was created
    //Read Operation - CRUDL
    @GetMapping("/CreationDate/{FirstName}")
    public @ResponseBody LocalDate getCreationDate(@PathVariable String FirstName){
        return (EmployeeList.get(FirstName)).getAccountCreationDate();
    }

    //READS TELEPHONE NUMBER OF SPECIFIED EMPLOYEE
    @GetMapping("/Telephone/{FirstName}")
    public @ResponseBody String getTelephoneNumber(@PathVariable String FirstName){
        return (EmployeeList.get(FirstName)).getTelephone();
    }

    //Change Telephone number of employee
    //UPDATE Operation - CRUDL
    @PutMapping("/TelephoneChange/{NewNumber}/{employeeFname}")
    public @ResponseBody Employee ChangeTelephoneNumber(@PathVariable String NewNumber , @PathVariable String employeeFname){
        (EmployeeList.get(employeeFname)).setTelephone(NewNumber);
        return EmployeeList.get(employeeFname);
    }
    //Removes com.example.Employee.Employee from list
    //Delete Operation - CRUDL
    @DeleteMapping("/delete/{EmpName}")
    public @ResponseBody String DeleteEmployee(@PathVariable String EmpName){
        EmployeeList.remove(EmpName);
        return EmpName;
    }
    //Returns a List of Employees with all their fields seperated in inner lists
    //List Operation - CRUDL
    @GetMapping("/ListFullEmployee")
    public @ResponseBody List<List<String>> ListFullEmployee(){
        List<List<String>> arr = new ArrayList<List<String>>();
        for(String Employeename : EmployeeList.keySet()){
            List<String> arrin= new ArrayList<String>();
            Employee emp = EmployeeList.get(Employeename);
            arrin.add(emp.getFirstName()+" "+emp.getLastName());
            arrin.add(emp.getEmailaddress());
            arrin.add(emp.getTelephone());
            arrin.add(emp.getAccountCreationDate()+" ");
            arr.add(arrin);
        }
        return arr;
    }

}
