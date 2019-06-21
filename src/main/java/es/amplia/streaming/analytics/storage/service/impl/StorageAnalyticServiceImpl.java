package es.amplia.streaming.analytics.storage.service.impl;

import es.amplia.streaming.analytics.storage.model.Analytic;
import es.amplia.streaming.analytics.storage.repository.AnalyticRepository;
import es.amplia.streaming.analytics.storage.service.IStorageAnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storageAnalyticService")
public class StorageAnalyticServiceImpl implements IStorageAnalyticService {

	@Autowired
	private AnalyticRepository repository;

	@Override
	public Analytic createAnalyticsRegistry(Analytic data) {
		return repository.save(data);
	}
}
