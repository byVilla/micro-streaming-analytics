package es.amplia.streaming.analytics.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Document(collection = "analytics")
public class Analytic implements Serializable {

	@Id
	private String id;
	private Long date;
	private Integer average;
	private Integer median;
	private List<Integer> mode;
	private Double typicalDeviation;
	private List<Integer> quartiles = new ArrayList<>();
	private Integer biggestValue;
	private Integer lowestValue;

	public String toString() {
		return "{\"_id\":".concat(Optional.ofNullable(id).orElse("")).concat("\",")
			.concat("\"date\":").concat(Optional.ofNullable(date).map(String::valueOf).orElse("0")).concat(",")
			.concat("\"average\":").concat(Optional.ofNullable(average).map(String::valueOf).orElse("0")).concat(",")
			.concat("\"median\":").concat(Optional.ofNullable(median).map(String::valueOf).orElse("0")).concat(",")
			.concat("\"mode\":").concat(Optional.ofNullable(mode).map(String::valueOf).orElse("0")).concat(",")
			.concat("\"typicalDeviation\":").concat(Optional.ofNullable(typicalDeviation).map(String::valueOf).orElse("0")).concat(",")
			.concat("\"quartiles\":").concat(quartiles.toString()).concat(",")
			.concat("\"biggestValue\":").concat(Optional.ofNullable(biggestValue).map(String::valueOf).orElse("0")).concat(",")
			.concat("\"lowestValue\":").concat(Optional.ofNullable(lowestValue).map(String::valueOf).orElse("0")).concat("}");
	}
}
