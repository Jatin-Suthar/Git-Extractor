package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.example.demo.git.services.GitService;
import com.example.demo.git.services.GitServiceImpl;

import reactor.core.publisher.Mono;

@Service
public class RepoServiceImpl implements RepoService {
	
	private int row=1;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private GitService gitService;
	
	@Override
	public InputStreamResource getRepoFilesCount(String userlink) {
		int idx=userlink.lastIndexOf("/");
		String userName=userlink.substring(idx+1);
		List<Map> repoDetailsList = gitService.getJSONFromGitAPI("/users/" + userName + "/repos").collectList().block();
		repoDetailsList.parallelStream().forEach((repoDetails) -> {
			try {
				String repoName=repoDetails.get("name").toString();
				Map repoFilesDetails = gitService.getJSONObjectFromGitAPI("/repos/" + userName + "/" + repoName + "/git/trees/master?recursive=1").block();
				this.processAllRepoData(repoFilesDetails, repoName);
			} catch(Exception e) {
				System.out.println("Exception Catched...");
			}
		});
		try {
			File resFile=excelService.createFinalExcel(userName);
			FileInputStream inputStream = new FileInputStream(resFile);
			return new InputStreamResource(inputStream);
		} catch(Exception e) {
			LOGGER.info("Exception Catched...");
		}
		return null;
	}
	
	@Override
	public boolean processAllRepoData(Map file, String repoName) {
		if(file.get("tree")==null) return false;
		List<Map<String, Object>> fileList = (List<Map<String, Object>>) file.get("tree");
		Map<String, Integer> map=new HashMap<>();
		fileList.stream().forEach((file_specs) -> {
			String file_path=(String) file_specs.get("path");
			int index=file_path.lastIndexOf("/");
			String fileName="";
			if(index==-1) fileName=file_path;
			else fileName=file_path.substring(index+1);
			
			if(fileName.contains(".") && index!=-1 || fileName.contains(".") && index==-1) {
				int _idx=fileName.lastIndexOf(".");
				String lang=fileName.substring(_idx+1);
				map.put(lang, map.getOrDefault(lang, 0)+1);
			}
		});

		map.keySet().stream().forEach((repoMap) -> {
			excelService.addRow(repoName, repoMap, map.get(repoMap), row++);
		});
		return true;
	}
	
}
