package es.amplia.streaming.analytics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRabbit
@ActiveProfiles(value = {"test"})
public class MicroStreamingAnalyticsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
