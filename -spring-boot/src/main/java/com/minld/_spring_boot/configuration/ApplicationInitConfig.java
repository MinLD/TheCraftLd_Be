package com.minld._spring_boot.configuration;

import java.util.HashSet;

import com.minld._spring_boot.Repository.RoleReponsitory;
import com.minld._spring_boot.constant.PredefindRole;
import com.minld._spring_boot.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.minld._spring_boot.Repository.UserRepository;
import com.minld._spring_boot.entity.User;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository,
                                        RoleReponsitory roleReponsitory) {
        return args -> {
            if (userRepository.findByEmail("admin").isEmpty()) {
                roleReponsitory.save(
                        Role.builder()
                                .name(PredefindRole.USER_ROLE)
                                .description("User role")
                                .build()
                );

                roleReponsitory.save(
                        Role.builder()
                                .name(PredefindRole.SELLER_ROLE)
                                .description("Seller role")
                                .build()
                );


                Role adminRole = roleReponsitory.save(
                        Role.builder()
                                .name(PredefindRole.ADMIN_ROLE)
                                .description("Admin role")
                                .build()
                );

                var roles = new HashSet<Role>();
                roles.add(adminRole);
                User user = User.builder()
                        .email("admin")
                        .password(passwordEncoder.encode("admin"))
                        .isActive(true)
                        .roles(roles)
                        .fullName("admin")
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
