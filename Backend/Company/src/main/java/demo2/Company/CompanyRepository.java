package demo2.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findById(int id);

    @Transactional
    void deleteById(int id);

}
