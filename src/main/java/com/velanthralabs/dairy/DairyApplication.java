package com.velanthralabs.dairy;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableCaching
@EnableAsync
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "5m")
@EntityScan(basePackages = "com.velanthralabs.dairy.*.domain")
@EnableJpaRepositories(basePackages = "com.velanthralabs.dairy.*.repository")
@ComponentScan("com.velanthralabs.dairy")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DairyApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(DairyApplication.class, args);
	}

}
