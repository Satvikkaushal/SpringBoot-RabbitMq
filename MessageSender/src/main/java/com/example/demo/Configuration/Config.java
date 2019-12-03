package com.example.demo.Configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	Queue IndianQueue() {
		return new Queue("IndianQueue", true);
	}

	@Bean
	Queue ForeignQueue() {
		return new Queue("ForeignQueue", true);
	}
    
	//created exchange
	@Bean
	HeadersExchange headerExchange() {
		return new HeadersExchange("Exchanger");
	}

	@Bean
	Binding asiaBinding(Queue IndianQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(IndianQueue).to(headerExchange).where("department").matches("India");
	}

	@Bean
	Binding europeBinding(Queue ForeignQueue, HeadersExchange headerExchange) {
		return BindingBuilder.bind(ForeignQueue).to(headerExchange).where("department").matches("Foreign");
	}

}