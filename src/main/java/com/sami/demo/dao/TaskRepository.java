package com.sami.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.demo.entities.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long>{

}
