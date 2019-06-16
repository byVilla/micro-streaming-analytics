package es.amplia.streaming.analytics.communication.publisher;

import es.amplia.streaming.analytics.communication.dto.Datapoint;
import es.amplia.streaming.analytics.communication.dto.DataStream;
import es.amplia.streaming.analytics.communication.dto.MessageData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

@Component
public class MessagePublisher {

	private static Logger logger = LogManager.getLogger(MessagePublisher.class);

	@Autowired
	private RabbitTemplate template;
	@Value(value = "${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value(value = "${rabbitmq.routing.key}")
	private String routingKey;
	@Value(value = "${message.data.version}")
	private String version;
	@Value(value = "${message.data.device}")
	private String device;
	@Value(value = "${message.data.datastream.id}")
	private String id;
	@Value(value = "${message.data.datastream.feed}")
	private String feed;
	private Random rand = new Random();

	@Scheduled(fixedDelayString = "${publish.fixedDelay.in.milliseconds}",
			initialDelayString = "${publish.initialDelay.in.milliseconds}")
	public void send() {
		String message = generateMessage();
		this.template.convertAndSend(exchangeName, routingKey, message);
		logger.info("Sending message: {}", message);
	}

	private String generateMessage() {
		DataStream datastream = new DataStream(id, feed,
				Collections.singletonList(new Datapoint(new Date().getTime(), rand.nextInt(100))));
		return new MessageData(version, device, Collections.singletonList(datastream)).toString();
	}
}
