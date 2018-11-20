package com.sami.demo.service;

import com.sami.demo.entities.AppRole;
import com.sami.demo.entities.AppUser;

public interface AccountService {
	 AppUser saveUser(AppUser user);
	 AppRole saveRole(AppRole role);
	 void addRoleToUse(String username, String rolename);
	 AppUser findUserByUserName(String username);

}
