package snu.bioinfo.pathway.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class ToolVO {
	private ArrayList<String> toolSet;
	private ArrayList<MultipartFile> inputFiles;
	private String projectName;
	private String userEmail;
	public ArrayList<String> getToolSet() {
		return toolSet;
	}
	public void setToolSet(ArrayList<String> toolSet) {
		this.toolSet = toolSet;
	}
	public ArrayList<MultipartFile> getInputFiles() {
		return inputFiles;
	}
	public void setInputFiles(ArrayList<MultipartFile> inputFiles) {
		this.inputFiles = inputFiles;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}	
	
}
