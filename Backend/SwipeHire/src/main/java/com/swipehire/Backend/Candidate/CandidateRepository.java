package com.swipehire.Backend.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findCandidateByUserid(String userid);
    Candidate findByUserid(String userid);
    boolean deleteCandidateByUserid(String userid);

    Candidate findCandidateByName(String name);

    List<Candidate> findCandidatesBySkillsContaining(String skill);
    List<Candidate> findAllBySkillsContaining(String skill);
}