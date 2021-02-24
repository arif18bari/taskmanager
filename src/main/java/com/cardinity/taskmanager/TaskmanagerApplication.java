package com.cardinity.taskmanager;

import com.cardinity.taskmanager.model.*;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

@SpringBootApplication
@EnableWebMvc
public class TaskmanagerApplication  implements CommandLineRunner {
	@Autowired
	private IService<User> userService;
	@Autowired
	private IService<Role> roleService;
	@Autowired
	private IService<Project> projectService;
	@Autowired
	private IService<Task> taskService;
	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.saveOrUpdate(new Role(1L, "admin"));
		roleService.saveOrUpdate(new Role(2L, "user"));
		projectService.saveOrUpdate(new Project(1L,"FirstProject"));
		projectService.saveOrUpdate(new Project(2L,"SecondProject"));

		Task task1 = new Task();
		task1.setTaskId(1L);
		task1.setTaskDescription("First Task");
		task1.setStatus(TaskStatus.OPEN);
		task1.setTaskStartDate(new Date());
		task1.setTaskEndDate(new Date());
		task1.setProject(projectService.findById(1L).get());
		taskService.saveOrUpdate(task1);

		Task task2 = new Task();
		task2.setTaskId(2L);
		task2.setTaskDescription("Second Task");
		task2.setStatus(TaskStatus.OPEN);
		task2.setTaskStartDate(new Date());
		task2.setTaskEndDate(new Date());
		task2.setProject(projectService.findById(2L).get());
		taskService.saveOrUpdate(task2);


		User user1 = new User();
		user1.setId(1L);
		user1.setEmail("test@user.com");
		user1.setEmployeeName("Test User");
		user1.setMobile("1234");
		user1.setRole(roleService.findById(2L).get());
		user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
		user1.setProject(projectService.findById(1L).get());
		user1.setTask(task1);
		userService.saveOrUpdate(user1);

		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("test@admin.com");
		user2.setEmployeeName("Test Admin");
		user2.setMobile("345");
		user2.setRole(roleService.findById(1L).get());
		user2.setProject(projectService.findById(2L).get());
		user2.setTask(task2);
		user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
		userService.saveOrUpdate(user2);


	}
}
