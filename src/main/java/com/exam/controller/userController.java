package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class userController {

    @Autowired
   private UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

   //create user
    @PostMapping("/")
    public User CreateUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");
        //encoding password with Bcrypt password encoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> roles = new HashSet<>();


        Role role = new Role();
        role.setRoleId(45);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user , roles);

    }

    // get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){

        return this.userService.getUser(username);

    }

    // delete the uer by id
    @DeleteMapping("/{userId}")
    public void  deleteUser(@PathVariable("userId") Long userId){

        this.userService.deleteuser(userId);

    }

    // update user by id
    @PutMapping("{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User user){
        user.setId(userId);

        Set<UserRole> roles = new HashSet<>();


        Role role = new Role();
        role.setRoleId(45);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return userService.updateUser(user);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }



}
