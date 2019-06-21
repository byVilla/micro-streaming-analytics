package es.amplia.streaming.analytics.storage.repository;

import es.amplia.streaming.analytics.storage.model.Analytic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalyticRepository extends MongoRepository<Analytic, String> {
}
