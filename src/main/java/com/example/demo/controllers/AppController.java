package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.RepoService;

/**
 * This controller takes /fetchRepoDetail end point and process it with some logic...
 * 
 * @author Jatin_Suthar
 *
 */
@RestController
public class AppController {
	
	@Autowired
	private RepoService repoService;
	
	/**
	 * this api when called, will call the service methods to fetch all the files count with particular repository and language...
	 * 
	 * @param link
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fetchRepoDetail")
	public ResponseEntity<?> fetchRepoDetail(@RequestBody String link) {
		InputStreamResource file = repoService.getRepoFilesCount(link);
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Github.xlsx")
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file);
	}
}
