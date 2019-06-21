package es.amplia.streaming.analytics.communication.service.impl;

import es.amplia.streaming.analytics.communication.dto.Datapoint;
import es.amplia.streaming.analytics.communication.dto.MessageData;
import es.amplia.streaming.analytics.communication.service.IAnalyticsService;
import es.amplia.streaming.analytics.storage.model.Analytic;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("analyticsService")
public class AnalyticsServiceImpl implements IAnalyticsService {

	@Override
	public Analytic getAnalyticsData(List<MessageData> messageList) {
		List<Integer> valueList = messageList.stream()
				.flatMap(messageData -> messageData.getDatastreams().stream())
				.flatMap(dataStream -> dataStream.getDatapoints().stream())
				.map(Datapoint::getValue)
				.sorted(Comparator.naturalOrder())
				.collect(Collectors.toList());
		if (valueList.isEmpty()) return null;
		Analytic data = new Analytic();
		data.setDate(new Date().getTime());
		data.setAverage(getAverage(valueList));
		data.setMedian(getMedian(valueList));
		data.setMode(getMode(valueList));
		data.setTypicalDeviation(getTypicalDeviation(valueList));
		data.setQuartiles(getQuartiles(valueList));
		data.setBiggestValue(getBiggestValue(valueList));
		data.setLowestValue(getLowestValue(valueList));
		return data;
	}

	private Integer getAverage(List<Integer> valueList) {
		return Math.floorDiv(valueList.stream()
				.reduce(0, Integer::sum), valueList.size());
	}

	private Integer getMedian(List<Integer> valueList) {
		int size = valueList.size();
		int med = Math.floorDiv(size, 2);
		if (size % 2 == 0) return valueList.get(Math.floorDiv(valueList.get(med) + valueList.get(med - 1), 2));
		return valueList.get(med);
	}

	private List<Integer> getMode(List<Integer> valueList) {
		Map<Integer, Integer> modes = new HashMap<>();
		valueList.forEach(value -> {
			if (modes.containsKey(value)) {
				modes.put(value, modes.get(value) + 1);
			} else {
				modes.put(value, 1);
			}
		});
		int max = modes.values().stream().max(Comparator.naturalOrder()).orElse(0);
		if (max <= 1) return Collections.singletonList(0);
		return modes.entrySet().stream()
				.filter(val -> val.getValue().equals(max))
				.map(v -> v.getKey())
				.collect(Collectors.toList());
	}

	private Double getTypicalDeviation(List<Integer> valueList) {
		int median = getMedian(valueList);
		return valueList.stream()
				.map(value -> Math.pow(value - median, 2))
				.reduce(Double::sum)
				.map(value -> Math.floorDiv(Math.round(value), valueList.size()))
				.map(Math::sqrt)
				.orElse(0.0);
	}

	private List<Integer> getQuartiles(List<Integer> valueList) {
		int size = valueList.size();
		return IntStream.range(1, 4).mapToObj(value -> {
			int q = Math.floorDiv(Math.multiplyExact(size, value), 4);
			if (size % 2 == 0) return valueList.get(Math.floorDiv(valueList.get(q) + valueList.get(q + 1), 2));
			return valueList.get(q);
		}).collect(Collectors.toList());
	}

	private Integer getBiggestValue(List<Integer> valueList) {
		return valueList.get(valueList.size() - 1);
	}

	private Integer getLowestValue(List<Integer> valueList) {
		return valueList.get(0);
	}
}
