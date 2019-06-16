package es.amplia.streaming.analytics.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageData {

	private String version;
	private String device;
	@NonNull private List<DataStream> datastreams = new ArrayList<>();

}
