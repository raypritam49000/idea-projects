package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;



	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.saveAll(List.of(
				new Role(ERole.ROLE_ADMIN),
				new Role(ERole.ROLE_USER),
				new Role(ERole.ROLE_MODERATOR)
		));

		User user = new User();
		user.setEmail("admin@gmai.com");
		user.setPassword(BCrypt.hashpw("admin123",BCrypt.gensalt()));
		user.setUsername("admin123");
		user.setRoles(Set.of(roleRepository.findByName(ERole.ROLE_ADMIN).get()));

		userRepository.save(user);

	}
}
