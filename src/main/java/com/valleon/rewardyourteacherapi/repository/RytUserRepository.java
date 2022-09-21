package com.valleon.rewardyourteacherapi.repository;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RytUserRepository extends JpaRepository<RytUser, Long>{

    @Override
    Optional<RytUser> findById(Long userId);

//    Optional<RytUser> findRytUsersByLastName(String lastName);

}
