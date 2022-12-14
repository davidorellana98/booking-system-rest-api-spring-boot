package com.davidorellana.bookingsystemrestapi.auth.service;

import com.davidorellana.bookingsystemrestapi.user.model.data.User;
import com.davidorellana.bookingsystemrestapi.user.repository.UserRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceAuth implements UserDetailsService {

    private final UserRepositoryDao userRepositoryDao;

    @Autowired
    public UserDetailServiceAuth(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFound = userRepositoryDao.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                userFound.getEmail(), "{noop}" + userFound.getPassword(), new ArrayList<>()
        );
    }
}
