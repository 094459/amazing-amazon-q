package uk.co.beachgeek.springgithubdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.SpringSecurityCoreVersion;



@SpringBootApplication
public class SpringGithubDemoApplication {

	public static void main(String[] args) {


		String springSecurityVersion = SpringSecurityCoreVersion.getVersion();
		System.out.println("Spring Security Version: " + springSecurityVersion);
		
		SpringApplication.run(SpringGithubDemoApplication.class, args);



	}

}

