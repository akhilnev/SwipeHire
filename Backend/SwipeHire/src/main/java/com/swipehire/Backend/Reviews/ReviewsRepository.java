package com.swipehire.Backend.Reviews;

import com.swipehire.Backend.SkillAssess.SkillAssess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews,Long>{

    List<Reviews> findReviewsByCompanyName(String companyName);

}


