package es.amplia.streaming.analytics.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DataStream {

	private String id;
	private String feed;
	@NonNull private List<Datapoint> datapoints = new ArrayList<>();

}
