package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {


		SpringApplication.run(ExamserverApplication.class, args);
		System.out.println("hello world");

			}

	@Override
	public void run(String... args) throws Exception {

		try {


			System.out.println("Starting code ");

			User user = new User();
			user.setFirsname("Alok Soni");
			user.setLastname("Soni");
			user.setUsername("Aloksoni95");
			user.setPassword(this.bCryptPasswordEncoder.encode("Ak@870735"));
			user.setNumber("8707352255");
			user.setProfile("defaulf.png");
			user.setEmail("aloksoni@gmail.com");

			Role role1 = new Role();
			role1.setRoleId(44);
			role1.setRoleName("ADMIN");


			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);

			Set<UserRole> userRoleSet = new HashSet<>();
			userRoleSet.add(userRole);

			User user1;
			user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());
		}catch (UserFoundException e){
			e.printStackTrace();
		}


	}
}
