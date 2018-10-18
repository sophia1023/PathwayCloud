package snu.bioinfo.pathway.domain;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectVO {

	private String projectName;
	private String pCheckResult;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getpCheckResult() {
		return pCheckResult;
	}
	public void setpCheckResult(String pCheckResult) {
		this.pCheckResult = pCheckResult;
	}
	
	
}
