package com.exam.service;



import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    //Creating User
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public  User getUser(String username);

    // delete user by id
    public  void  deleteuser(long userId);

     //update user by id
  public  User updateUser( User user);
}
