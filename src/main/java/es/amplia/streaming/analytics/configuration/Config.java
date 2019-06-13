package es.amplia.streaming.analytics.configuration;

import es.amplia.streaming.analytics.sender.MessageSender;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Value(value = "${principal.queue.name}")
	String queueName;

	@Bean
	public Queue principalQueue(){
		return new Queue(queueName);
	}

	@Bean
	public MessageSender sender() {
		return new MessageSender();
	}
}
