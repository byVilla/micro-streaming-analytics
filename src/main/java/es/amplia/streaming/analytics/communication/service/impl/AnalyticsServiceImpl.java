package es.amplia.streaming.analytics.communication.service.impl;

import es.amplia.streaming.analytics.communication.dto.AnalyticsData;
import es.amplia.streaming.analytics.communication.dto.MessageData;
import es.amplia.streaming.analytics.communication.service.IAnalyticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("analyticsService")
public class AnalyticsServiceImpl implements IAnalyticsService {

	@Override
	public AnalyticsData getAnalyticsData(List<MessageData> messageList) {
/*		List<Integer> valueList = messageList.stream().map(messageData -> messageData.getDatastreams().stream()
				.map(dataStream -> dataStream.getDatapoints().stream().toArray(Datapoint::getValue))).collect(Collectors.toList());*/
		return null;
	}

}
