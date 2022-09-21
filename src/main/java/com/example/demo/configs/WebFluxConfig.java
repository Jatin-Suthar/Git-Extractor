package com.example.demo.configs;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * 
 * @author Jatin_Suthar
 *
 */

@Configuration
public class WebFluxConfig {
	
	private final String Authorization = "Authorization";
	private final String AuthToken="token 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";
	private final String BASE_URL="https://api.github.com";
	
	/**
	 * this service operation when called, will create a WebClient with default headers like Authorization and Content-Type also added filter, returns the WebClient...
	 * 
	 * @return
	 */
	@Bean
	public WebClient createWebClient() {
		WebClient webClient = WebClient.builder()
				  .baseUrl(BASE_URL)
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				  .defaultHeader(Authorization, AuthToken)
				  .defaultUriVariables(Collections.singletonMap("url", BASE_URL))
//				  .filter(errorHandler())
				  .build();
		
		return webClient;
	}
	
	/**
	 * /**
	 * this service operation when called, will create a filter which track any exception occur, returns Mono<ClientResponse>
	 * 
	 * @return
	 */
	public ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			if(clientResponse.statusCode().isError()) {
				return clientResponse.bodyToMono(Map.class)
						.flatMap(errorBody -> Mono.error(new Exception("Exception Occured")));
			}else {
				return Mono.just(clientResponse);
			}
		});
	}
}
