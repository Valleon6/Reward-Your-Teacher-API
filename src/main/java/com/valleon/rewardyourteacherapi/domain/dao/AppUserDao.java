package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;

import java.util.Optional;

public interface AppUserDao extends CrudDAO<AppUser, Long>{

    Optional<AppUser> findAppUserByEmail(String email);
}
