package com.valleon.rewardyourteacherapi.persistence.repository;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RytUserRepository extends JpaRepository<AppUser, Long>{

    Optional<AppUser> findByUsername(String username);

//    @Override
//    Optional<AppUser> findById(Long userId);
//    Optional<AppUser> findRytUsersByLastName(String lastName);

}
