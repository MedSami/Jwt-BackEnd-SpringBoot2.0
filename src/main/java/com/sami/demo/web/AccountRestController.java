package com.sami.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sami.demo.entities.AppUser;
import com.sami.demo.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public AppUser addUser(@RequestBody RegisterForm userForm) {

		if (!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("you must confirm your password");

		AppUser user=accountService.findUserByUserName(userForm.getUsername());

		if (user!=null)
			throw new RuntimeException("User already exist ");

		AppUser appUser=new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());

		 accountService.saveUser(appUser);
		 accountService.addRoleToUse(userForm.getUsername(), "USER");
		 return appUser;

	}
}
