package com.valleon.rewardyourteacherapi.security;

import com.valleon.rewardyourteacherapi.entity.RytUser;
import com.valleon.rewardyourteacherapi.repository.RytUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final RytUserRepository rytUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RytUser rytUser = rytUserRepository.findByUsername(username).orElse(null);
        return new org.springframework.security.core.userdetails.User(rytUser.getUsername(),rytUser.getPassword(), new ArrayList<>());

    }
}
