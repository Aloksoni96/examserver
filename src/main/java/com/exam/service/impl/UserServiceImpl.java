package com.exam.service.impl;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles)  throws  Exception{

       User local= this.userRepository.findByUsername(user.getUsername());
       if (local!=null){
           System.out.println("User is already there!!");
           throw new UserFoundException();
       }
       else {
           //create a user
           for (UserRole ur: userRoles){
               roleRepository.save(ur.getRole());
           }

           user.getUserRoles().addAll(userRoles);
         local=  this.userRepository.save(user);
       }
        return local;
    }
// get user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteuser(long userId) {
        this.userRepository.deleteById( userId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }


}
