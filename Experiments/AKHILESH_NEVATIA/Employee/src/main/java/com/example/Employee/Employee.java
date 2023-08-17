package com.example.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

public class Employee {
    private String firstName;

    private String lastName;

    private String emailaddress;

    private String telephone;

    private LocalDate date = LocalDate.now();

    public LocalDate getAccountCreationDate() {
        return date;
    }

    public Employee() {
    }

    public Employee(String firstName, String lastName, String emailaddress, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailaddress = emailaddress;
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "com.example.Employee.Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }


}
