package com.colorbook.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class ApiApplication {

    private Environment environment;

    public ApiApplication(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        log.info("applicationReady, profiles = {}", Arrays.toString(environment.getActiveProfiles()));
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApiApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        SpringApplication.run(ApiApplication.class, args);
    }
}
