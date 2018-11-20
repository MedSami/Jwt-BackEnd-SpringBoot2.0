package com.sami.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sami.demo.dao.TaskRepository;
import com.sami.demo.entities.Tasks;

@RestController
public class TasksController {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/tasks")
	public List<Tasks> listTasks(){
		return taskRepository.findAll();
	}
	
	@PostMapping("/tasks")
	public Tasks addTask(@RequestBody Tasks task){
		return taskRepository.save(task);
	}
}
