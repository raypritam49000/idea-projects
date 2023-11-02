package com.rest.api;

import com.rest.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Hibernate2levelcacheApplication implements CommandLineRunner {

	@Autowired
	private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(Hibernate2levelcacheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(cityRepository.findByName("Punjab"));
		System.out.println(cityRepository.findByName("Punjab"));
		System.out.println(cityRepository.findByName("Punjab"));
		System.out.println(cityRepository.findByName("Punjab"));

		System.out.println(cityRepository.findAllByName("Punjab"));
		System.out.println(cityRepository.findAllByName("Punjab"));
		System.out.println(cityRepository.findAllByName("Punjab"));

		System.out.println(cityRepository.findAll());
		System.out.println(cityRepository.findAll());
		System.out.println(cityRepository.findAll());
		System.out.println(cityRepository.findAll());

	}
}
