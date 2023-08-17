package demo2.Managers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface ManagerRepository extends JpaRepository<Managers,Long> {

    Managers findById(int id);

    @Transactional
    Managers deleteById(int id);


}
