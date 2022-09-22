package com.example.demo.git.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GitServiceImpl implements GitService {
	
	@Autowired
	private WebClient webClient; 
	
	@Override
	public Flux<Map> getJSONFromGitAPI(String url) {
		return webClient.get()
				     .uri(url)
				     .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				     .retrieve()
				     .bodyToFlux(Map.class)
				     .onErrorResume(WebClientResponseException.class,
				             ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
	}
	
	@Override
	public Mono<Map> getJSONObjectFromGitAPI(String url) {
		return webClient.get()
			     .uri(url)
			     .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			     .retrieve()
			     .bodyToMono(Map.class)
			     .onErrorResume(WebClientResponseException.class,
			             ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
	}
	
}
