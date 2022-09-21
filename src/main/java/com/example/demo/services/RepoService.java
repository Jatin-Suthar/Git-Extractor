package com.example.demo.services;

import java.util.Map;

import org.springframework.core.io.InputStreamResource;

/**
 * 
 * 
 * @author ongraph
 *
 */
public interface RepoService {

	/**
	 * this service operation when called, will call github_api asynchronously and store the results in blocks, returns InputStreamResource with file details.
	 * 
	 * @param userlink
	 * @return
	 * @throws Exception
	 */
	public InputStreamResource getRepoFilesCount(String userlink);
	
	/**
	 * this service operation when called, will store response of current thread in list of maps then call addRow method to fill data in excel sheet...
	 * 
	 * @param file
	 * @param repoName
	 * @return
	 */
	public boolean processAllRepoData(Map file, String repoName);
}
