package es.amplia.streaming.analytics.communication.publisher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.amplia.streaming.analytics.communication.dto.DataStream;
import es.amplia.streaming.analytics.communication.dto.Datapoint;
import es.amplia.streaming.analytics.communication.dto.MessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class MessagePublisher {

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
	private Gson gson = new GsonBuilder().create();

	@Scheduled(fixedDelayString = "${publish.fixedDelay.in.milliseconds}",
			initialDelayString = "${publish.initialDelay.in.milliseconds}")
	public void send() {
		MessageData message = generateMessage();
		this.template.convertAndSend(exchangeName, routingKey, gson.toJson(message));
		log.info("Sending message: {}", message);
	}

	private MessageData generateMessage() {
		DataStream datastream = new DataStream(id, feed,
				Collections.singletonList(new Datapoint(new Date().getTime(), rand.nextInt(10))));
		return new MessageData(version, device, Collections.singletonList(datastream));
	}
}
