package com.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.FileSystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.DisposableBean;

@SpringBootApplication
public class DemoApplication {
	@Value("${uploads.dir}")
    private String uploadPath;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    CommandLineRunner init() {
	    return (args) -> {
	    	System.out.println(String.format("Re-creating the upload directory at %s", uploadPath));
		    FileSystemUtils.deleteRecursively(new File(uploadPath));
		    Files.createDirectory(Paths.get(uploadPath));
    	};
    }

	@Bean
	DisposableBean resourceCleaner() {
		return () -> {
			System.out.println(String.format("Deleting the upload directory at %s", uploadPath));
			FileSystemUtils.deleteRecursively(new File(uploadPath));
		};
	}
}
