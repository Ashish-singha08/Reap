package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public UserEntity getUserDetails(long id){
        UserEntity user = userRepository.findById(id);
        return user;
    }
    public UserEntity getUserByName(String username){
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }
    public String updateCoins(@RequestBody Map<String,Object> payload, UserEntity user, boolean flag){
        String s = (String)payload.get("coins");
        int coins = Integer.valueOf(s);
        if(!flag)
          userRepository.updateCoins(user.getCoinBalance()-coins,user.getId());
        else
            userRepository.updateCoins(user.getCoinBalance()+coins,user.getId());
        return String.valueOf(user.getCoinBalance()-coins);
    }
    public List<Object> getAllUsers(long id){
        List<Object> users  =userRepository.findAllUsers(id);
        return users;
    }
}
