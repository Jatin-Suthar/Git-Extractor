package com.example.demo.git.services;

import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GitService {
	
	/**
	 * this service operation when called, uses web-client to get the response from the end point we give as url and return response as Flux of Map...
	 * 
	 * @param url
	 * @return
	 */
	public Flux<Map> getJSONFromGitAPI(String url);
	
	/**
	 * this service operation when called, uses web-client to get the response from the end point we give as url and return response as Mono of Map...
	 * 
	 * @param url
	 * @return
	 */
	public Mono<Map> getJSONObjectFromGitAPI(String url);
}
