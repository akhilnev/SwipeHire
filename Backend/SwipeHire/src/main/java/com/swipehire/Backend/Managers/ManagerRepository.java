package com.swipehire.Backend.Managers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface ManagerRepository extends JpaRepository<Managers,Long> {

 

    Managers findByUserid(String userid);

    ArrayList<Managers> findByCompanyName(String CompanyName);

    @Transactional
    Managers deleteByUserid(String userid);


    ArrayList<Managers> findByJobtypeAndLocationAndSalary(String jobtype , String location , String salary);

    ArrayList<Managers> findByJobtypeAndLocation(String jobtype , String location);

    ArrayList<Managers> findByJobtypeAndSalary(String jobtype , String salary );

    ArrayList<Managers> findBySalaryAndLocation(String salary , String location);

    ArrayList<Managers> findByJobtype(String job);

    ArrayList<Managers> findBySalary(String salary);

    ArrayList<Managers> findByLocation(String location);




}
