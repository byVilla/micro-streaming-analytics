package es.amplia.streaming.analytics.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "${principal.queue.name")
public class MessageConsumer {

	private static Logger logger = LogManager.getLogger(MessageConsumer.class);

	@RabbitHandler
	public void receive(String message) {
		logger.info("message received: s%", message);
	}
}
