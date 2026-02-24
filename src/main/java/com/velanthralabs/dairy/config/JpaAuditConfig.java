package com.velanthralabs.dairy.config;

import com.velanthralabs.dairy.common.util.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing // This is the switch that turns it on
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Using our wrapper logic here
        return () -> Optional.of(SecurityUtils.getCurrentUsername());
    }
}