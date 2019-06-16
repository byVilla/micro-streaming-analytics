package es.amplia.streaming.analytics.communication.dto;

import lombok.Data;

@Data
public class AnalyticsData {

	private int average;
	private int median;
	private int mode;
	private int typicalDeviation;
	private int quartiles;
	private int biggestValue;
	private int lowestValue;
}
