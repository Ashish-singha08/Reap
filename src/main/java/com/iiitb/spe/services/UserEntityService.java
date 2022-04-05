package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }
    public String updateCoins(int coins, long id){
        userRepository.updateCoins(coins,id);
        return "Coins Updated";
    }
}
