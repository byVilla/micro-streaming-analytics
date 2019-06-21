package es.amplia.streaming.analytics.communication.service;

import es.amplia.streaming.analytics.communication.dto.MessageData;
import es.amplia.streaming.analytics.storage.model.Analytic;

import java.util.List;

public interface IAnalyticsService {

	/**
	 * Get analytics information from message list.
	 * @param messageList List {@link MessageData}
	 * @return Analytic
	 */
	Analytic getAnalyticsData(List<MessageData> messageList);
}
