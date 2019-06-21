package es.amplia.streaming.analytics.communication.consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.amplia.streaming.analytics.communication.dto.MessageData;
import es.amplia.streaming.analytics.communication.service.IAnalyticsService;
import es.amplia.streaming.analytics.storage.model.Analytic;
import es.amplia.streaming.analytics.storage.service.IStorageAnalyticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MessageConsumer {

	@Value(value = "${default.listener.method.name}")
	private String defaultListenerMethodName;
	@Autowired
	private IAnalyticsService analyticsService;
	@Autowired
	private IStorageAnalyticService storageAnalyticService;
	private Gson gson = new GsonBuilder().create();

	private List<MessageData> messageList = new ArrayList();

	public String getDefaultListenerMethodName() {
		return defaultListenerMethodName;
	}

	public void receiveMessage(String message) {
		messageList.add(gson.fromJson(message, MessageData.class));
		log.info("message received: {}", message);
	}

	@Scheduled(fixedDelayString = "${processing.fixedDelay.in.milliseconds}",
			initialDelayString = "${processing.initialDelay.in.milliseconds}")
	private void processingMessageList() {
		try {
			List<MessageData> copy = new ArrayList(messageList);
			messageList.clear();
			Analytic data = analyticsService.getAnalyticsData(copy);
			data = storageAnalyticService.createAnalyticsRegistry(data);
			log.info("Analytic registry: {}", data);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
