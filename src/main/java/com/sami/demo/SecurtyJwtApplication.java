package com.sami.demo;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sami.demo.dao.TaskRepository;
import com.sami.demo.entities.AppRole;
import com.sami.demo.entities.AppUser;
import com.sami.demo.entities.Tasks;
import com.sami.demo.service.AccountService;

@SpringBootApplication
public class SecurtyJwtApplication implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(SecurtyJwtApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
		accountService.saveUser(new AppUser(null, "admin", "1234", null));
		accountService.saveUser(new AppUser(null, "user", "1234", null));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUse("admin", "ADMIN");
		accountService.addRoleToUse("admin", "USER");
		accountService.addRoleToUse("user", "USER");


		
		Stream.of("T1", "T2", "T3").forEach(t -> {
			taskRepository.save(new Tasks(null, t));
		});
		taskRepository.findAll().stream().forEach(t -> {
			System.out.println(t.getTasksName());
		});

	}
}
