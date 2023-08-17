package demo2.Managers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo2.Company.Company;
import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Managers")
public class Managers {
    public Managers (int managerID, String name, String emailaddress, demo2.Company.Company company) {
        id = managerID;
        this.name = name;
        this.emailaddress = emailaddress;
        Company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String emailaddress;

    @OneToOne
    @JsonIgnore
    private demo2.Company.Company Company;

    public Managers() {

    }


    public int getManagerID() {
        return id;
    }

    public void setManagerID(int managerID) {
        id = managerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public demo2.Company.Company getCompany() {
        return Company;
    }

    public void setCompany(demo2.Company.Company company) {
        Company = company;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }


    @Override
    public String toString() {
        return "Managers{" +
                "ManagerID=" + id +
                ", name='" + name + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                '}';
    }
}
