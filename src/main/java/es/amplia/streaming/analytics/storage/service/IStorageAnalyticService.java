package es.amplia.streaming.analytics.storage.service;

import es.amplia.streaming.analytics.storage.model.Analytic;

public interface IStorageAnalyticService {

	/**
	 * Create analytic registry.
	 * @param data {@link Analytic} Analytic information
	 */
	Analytic createAnalyticsRegistry(Analytic data);
}
