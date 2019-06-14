package es.amplia.streaming.analytics.configuration;

import es.amplia.streaming.analytics.consumer.MessageConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerServiceConfiguration {

	@Value(value = "${rabbitmq.queue.name}")
	private String queueName;
	@Value(value = "${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value(value = "${rabbitmq.routing.key}")
	private String routingKey;

	@Bean
	Queue queue(){
		return new Queue(queueName);
	}

	@Bean
	DirectExchange exchange() { return new DirectExchange(exchangeName); }

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(this.queue()).to(this.exchange()).with(routingKey);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageConsumer consumer) {
		return new MessageListenerAdapter(consumer, consumer.getDefaultListenerMethodName());
	}
}
