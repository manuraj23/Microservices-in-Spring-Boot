package com.hms.UserService.Service;

import com.hms.UserService.Entity.User;
import com.hms.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId)).orElse(null);
    }

}
