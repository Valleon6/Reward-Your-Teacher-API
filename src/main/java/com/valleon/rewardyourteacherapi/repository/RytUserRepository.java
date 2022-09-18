package com.valleon.rewardyourteacherapi.repository;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RytUserRepository extends JpaRepository<RytUser, Long>{
//    @Query("" +
//            "SELECT CASE WHEN COUNT(s) > 0 THEN " + "TRUE ELSE FALSE END " + "FROM User s " + "WHERE s.email = ?1"
//    )
//    Boolean selectExistsEmail(String email);
}
