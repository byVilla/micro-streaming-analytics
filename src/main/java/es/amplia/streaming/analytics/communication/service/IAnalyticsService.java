package es.amplia.streaming.analytics.communication.service;

import es.amplia.streaming.analytics.communication.dto.AnalyticsData;
import es.amplia.streaming.analytics.communication.dto.MessageData;

import java.util.List;

public interface IAnalyticsService {

	/**
	 * Get analytics information from message list.
	 * @param messageList List {@link MessageData}
	 * @return AnalyticsData
	 */
	AnalyticsData getAnalyticsData(List<MessageData> messageList);
}
