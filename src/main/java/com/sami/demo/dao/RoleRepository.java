package com.sami.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.demo.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{
	
	public AppRole findByRoleName(String rolename);

}
