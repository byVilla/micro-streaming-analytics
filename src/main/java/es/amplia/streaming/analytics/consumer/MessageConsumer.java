package es.amplia.streaming.analytics.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageConsumer {

	private static Logger logger = LogManager.getLogger(MessageConsumer.class);
	@Value(value = "${default.listener.method.name}")
	private String defaultListenerMethodName;

	private List<Object> messageList = new ArrayList();

	public String getDefaultListenerMethodName() {
		return defaultListenerMethodName;
	}

	public void receiveMessage(String message) {
		messageList.add(message);
		logger.info("message received: {}", message);
	}

	@Scheduled(fixedDelayString = "${processing.fixedDelay.in.milliseconds}")
	private void processingMessageList() {
		List copy = messageList.stream().collect(Collectors.toList());
		messageList.clear();
		copy.forEach(msg -> logger.info("Message {}", msg));
	}
}
