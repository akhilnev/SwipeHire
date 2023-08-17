package demo2.Managers;

import demo2.Company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    demo2.Company.CompanyRepository companyRepository;

    @Autowired
    demo2.Managers.ManagerRepository managerRepository;



    @GetMapping(path = "/Managers") // getting a list of all companies displayed
    List<Managers> getAllManagers(){
        return managerRepository.findAll();
    }

    @GetMapping(path = "/Managers/{id}") // accesing a company using its respective ID
    Managers getManagerById(@PathVariable int id){
        return managerRepository.findById(id);
    }

    @PostMapping(path = "/Managers/add") // adding a new company using request body parameter
    Managers createManager(@RequestBody Managers newManager)
    {
        managerRepository.save(newManager);

        return newManager;

    }

  //Add a Delete mapping method and ask TA about how we can find
  @DeleteMapping(path = "/Remove_Managers/{id}")
  Managers deleteManager(@PathVariable int id){
      // Check if there is an object depending on user and then remove the dependency
      Company company = null ;
      Managers manager = managerRepository.findById(id);
      for(Company comp : companyRepository.findAll()){
          if(comp.getManager().getManagerID()==id){
              company =comp;
          }
      }
      company.setManager(null);
      companyRepository.save(company);

      // delete the laptop if the changes have not been reflected by the above statement
      managerRepository.deleteById(id);
      return manager;
  }
    @PutMapping(path = "/ChangeManagers/{id}")
    Managers ChangeManager(@PathVariable int id, @RequestBody Managers newManager){
        Managers manager = managerRepository.findById(id);
        if(manager == null)
            return null;
        managerRepository.save(newManager);
        return newManager;
    }

}
