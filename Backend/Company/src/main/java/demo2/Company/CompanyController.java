package demo2.Company;

import demo2.Managers.ManagerRepository;
import demo2.Managers.Managers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ManagerRepository managerRepository;

    @GetMapping(path = "/Company") // getting a list of all companies displayed
    List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @GetMapping(path = "/Company/{id}") // accesing a company using its respective ID
    Company getCompanyById(@PathVariable int id){
        return companyRepository.findById(id);
    }

    @PostMapping(path = "/Company/add") // adding a new company using request body parameter
    Company createCompany(@RequestBody Company NewCompany)
    {
       companyRepository.save(NewCompany);

       return NewCompany;

    }
    @PutMapping("/Company/{id}") // change a company by passing in ID
    Company updateCompany(@PathVariable int id, @RequestBody Company company){
        Company company1 = companyRepository.findById(id);
        if(company1 == null)
            return null;
        companyRepository.save(company);
        return companyRepository.findById(id);
    }

    @DeleteMapping(path = "/Company/{id}")
    Company deleteCompany(@PathVariable int id){
        Company comp = companyRepository.findById(id);
        companyRepository.deleteById(id);
        return comp;
    }

    @PutMapping(path="Company/{CompanyId}/Manager/{ManagerId}")
    void assignManagertoCompany(@PathVariable int CompanyId , @PathVariable int ManagerId){
        Company company = companyRepository.findById(CompanyId);
        Managers manager = managerRepository.findById(ManagerId);
        company.setManager(manager);
        //Change Manager such that it stores Company variable then edit this method here
        manager.setCompany(company);
        companyRepository.save(company);
    }



}
