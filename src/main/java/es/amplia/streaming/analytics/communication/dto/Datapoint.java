package es.amplia.streaming.analytics.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Datapoint {

	private long at;
	private Integer value;
}
