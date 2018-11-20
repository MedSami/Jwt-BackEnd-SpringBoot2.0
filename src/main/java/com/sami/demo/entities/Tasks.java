package com.sami.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Tasks {
@Id
@GeneratedValue
	private Long Id;
	private String TasksName;
	
	
	public Tasks() {
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getTasksName() {
		return TasksName;
	}


	public void setTasksName(String tasksName) {
		TasksName = tasksName;
	}


	public Tasks(Long id, String tasksName) {
		super();
		Id = id;
		TasksName = tasksName;
	}
	
}
