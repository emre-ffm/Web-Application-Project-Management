package edu.fra.uas;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.fra.uas.controller.project.ProjectController;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"edu.fra.uas","edu.fra.uas.controller.project"})
public class EmreInvantar2021WebbassierteAnwendungenV1Application {
	
	public static void main(String[] args) {
	
		
		
		
	/*	new File(ProjectController.uploadDirectory).mkdirs();*/
		//create a new folder in selected directory
		new File(ProjectController.uploadDirectory2).mkdirs();
		
		SpringApplication.run(EmreInvantar2021WebbassierteAnwendungenV1Application.class, args);

	}

}
