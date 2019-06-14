package es.amplia.streaming.analytics.publisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

	private static Logger logger = LogManager.getLogger(MessagePublisher.class);

	@Autowired
	private RabbitTemplate template;
	@Value(value = "${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value(value = "${rabbitmq.routing.key}")
	private String routingKey;

	@Scheduled(fixedDelayString = "${publish.fixedDelay.in.milliseconds}", initialDelayString = "${initialDelay.in.milliseconds}")
	public void send() {
		String message = "Test";
		this.template.convertAndSend(exchangeName, routingKey, message);
		logger.info("Sending message: {}", message);
	}
}
