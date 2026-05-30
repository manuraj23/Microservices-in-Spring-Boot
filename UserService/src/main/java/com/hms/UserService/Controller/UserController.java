package com.hms.UserService.Controller;


import com.hms.UserService.Entity.User;
import com.hms.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable String userId){
        User user = userService.getUserById(userId);
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveNewUser(user));
    }
}
