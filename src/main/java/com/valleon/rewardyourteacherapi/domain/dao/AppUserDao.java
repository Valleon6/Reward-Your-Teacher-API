package com.valleon.rewardyourteacherapi.domain.dao;

import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;

import java.util.Optional;

public interface AppUserDao extends CrudDAO<AppUser, Long>{

    AppUser findAppUserByEmail(String email);

    AppUser findAppUserByEmailAndRole(String email, Role role);
}

