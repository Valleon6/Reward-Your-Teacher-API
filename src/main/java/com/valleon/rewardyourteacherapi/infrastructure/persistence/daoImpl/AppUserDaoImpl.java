package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.domain.entities.enums.Role;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDaoImpl extends CrudDaoImpl<AppUser, Long> implements AppUserDao {
    private final AppUserRepository appUserRepository;

    protected AppUserDaoImpl(AppUserRepository appUserRepository) {
        super(appUserRepository);
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser findAppUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }

    @Override
    public AppUser findAppUserByEmailAndRole(String email, Role role) {
        return appUserRepository.findAppUserByEmailAndRole(email, role);
    }
}
