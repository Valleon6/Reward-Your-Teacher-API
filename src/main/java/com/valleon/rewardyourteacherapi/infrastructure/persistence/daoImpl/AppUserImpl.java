package com.valleon.rewardyourteacherapi.infrastructure.persistence.daoImpl;

import com.valleon.rewardyourteacherapi.domain.dao.AppUserDao;
import com.valleon.rewardyourteacherapi.domain.entities.AppUser;
import com.valleon.rewardyourteacherapi.infrastructure.persistence.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserImpl extends CrudDaoImpl<AppUser, Long> implements AppUserDao {
    private final AppUserRepository appUserRepository;

    public AppUserImpl(AppUserRepository appUserRepository) {
        super(appUserRepository);
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> findAppUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }
}
