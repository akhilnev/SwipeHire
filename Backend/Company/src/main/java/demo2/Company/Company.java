package demo2.Company;
import demo2.Managers.Managers;
import jakarta.persistence.*;

@Entity
@Table(name="Company")
public class Company{
    public Company(int companyID, String companyName, String company_description, Managers manager) {
        id = companyID;
        CompanyName = companyName;
        Company_description = company_description;
        Manager = manager;
    }

    public Company() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String CompanyName;

    private String Company_description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ManagerID")
    private Managers Manager;

    public int getCompanyID() {
        return id;
    }

    public void setCompanyID(int companyID) {
        id = companyID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public Managers getManager() {
        return Manager;
    }

    public void setManager(Managers manager) {
        Manager = manager;
    }

    public String getCompany_description() {
        return Company_description;
    }

    public void setCompany_description(String company_description) {
        Company_description = company_description;
    }

    @Override
    public String toString() {
        return "Company{" +
                "CompanyID=" + id +
                ", CompanyName=" + CompanyName +
                ", Company_description='" + Company_description + '\'' +
                '}';
    }
}
