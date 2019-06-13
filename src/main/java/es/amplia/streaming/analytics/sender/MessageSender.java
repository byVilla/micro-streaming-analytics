package es.amplia.streaming.analytics.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MessageSender {

	private static Logger logger = LogManager.getLogger(MessageSender.class);

	@Autowired
	private RabbitTemplate template;
	@Autowired
	private Queue principalQueue;

	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}", initialDelayString = "${initialDelay.in.miliseconds}")
	public void send() {
		String message = "Test";
		this.template.convertAndSend(this.principalQueue.getName(), message);
		logger.info("Sending message: s%", message);
	}
}
