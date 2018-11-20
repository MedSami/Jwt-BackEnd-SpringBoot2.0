package com.sami.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.demo.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{

	public AppUser findByUsername(String username);
}
