package com.iiitb.spe.services;

import com.iiitb.spe.model.CustomUserDetails;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity user = this.userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Not found");
        }

        return new CustomUserDetails(user);
    }
}
