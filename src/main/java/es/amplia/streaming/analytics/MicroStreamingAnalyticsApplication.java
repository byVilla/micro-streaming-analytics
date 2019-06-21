package es.amplia.streaming.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MicroStreamingAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroStreamingAnalyticsApplication.class, args);
	}

}
