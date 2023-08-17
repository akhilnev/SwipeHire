package com.swipehire.Backend.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUsersByUserid(String userid);
    boolean deleteByUserid(String userid);
}
