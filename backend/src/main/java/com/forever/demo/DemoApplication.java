package com.forever.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/* ********** */

//    @Value("${spring.profiles}")
//    private String springProfiles;

    @EventListener(ApplicationReadyEvent.class)
    public void logSpringProfiles(){
        // LoggerFactory.getLogger(DemoApplication.class).info("Active Profile is [" + springProfiles + "]");
    }
}
