package com.velanthralabs.dairy.config;

import com.velanthralabs.dairy.common.entity.User;
import com.velanthralabs.dairy.common.enums.Role;
import com.velanthralabs.dairy.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        // Check if the database is empty
        if (userRepository.count() == 0) {

            User owner = User.builder()
                    .name("Haridoss Owner")
                    .username("haridoss")
                    // Password will be 'admin123'
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ROLE_SUPER_ADMIN)
                    .active(true)
                    .build();

            userRepository.save(owner);

            System.out.println("==============================================");
            System.out.println("BOOTSTRAP SUCCESSFUL");
            System.out.println("Owner Account Created: haridoss");
            System.out.println("Default Password: admin123");
            System.out.println("==============================================");
        } else {
            System.out.println("Users already exist in database. Skipping bootstrap.");
        }
    }
}