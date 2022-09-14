package com.valleon.rewardyourteacherapi.repository;

import com.valleon.rewardyourteacherapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
