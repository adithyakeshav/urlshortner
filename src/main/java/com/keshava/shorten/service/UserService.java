package com.keshava.shorten.service;

import com.keshava.shorten.configuration.UserDetailsImpl;
import com.keshava.shorten.entity.UserEntity;
import com.keshava.shorten.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findById(user).orElseThrow(() -> new UsernameNotFoundException("User Not Found : " + user));
        return new UserDetailsImpl(userEntity);
    }
}
