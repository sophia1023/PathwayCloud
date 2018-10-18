package snu.bioinfo.pathway.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import snu.bioinfo.pathway.controller.RunController;
import snu.bioinfo.pathway.domain.ToolVO;

@Service("asyncTask")
public class AsyncTask {

	private static final Logger logger = LoggerFactory.getLogger(RunController.class);
	
		@Async("executor")
	    public void executor(ToolVO tvo, ArrayList<String> fileName) {
		 	ArrayList<String> toolSet = tvo.getToolSet();
	        String inputCSV = fileName.get(0);
	        String infoCSV = fileName.get(1);
	     
			Process p = null;
			ProcessBuilder pb = null;
			List<String> cmdList = new ArrayList<String>();
	        
			// adding command and args to the list
	        try {
	        	 for(int i = 0; i < toolSet.size(); i++) {
	        		 
	        		 String toolName = toolSet.get(i);
	        		 String projectName = tvo.getProjectName();
	        		 
			         switch(toolName) {
				         case "GSVA" :
				        	 cmdList.add("./GSVA.R");
				        	 cmdList.add(inputCSV);
				        	 cmdList.add(infoCSV);
				        	 cmdList.add(projectName);  
				  	         break;
				         case "PADOG_prepare" :
				        	 cmdList.add("./PADOG_prepare.py");
				        	 cmdList.add(inputCSV);
				        	 cmdList.add(infoCSV);
				        	 cmdList.add(projectName);
				  	         break;
				         case "PADOG" :
				        	 cmdList.add("./PADOG.py");
				        	 cmdList.add(inputCSV);
				        	 cmdList.add(infoCSV);
				        	 cmdList.add(projectName);				 
				  	         break;
				         case "Test" :
				        	 cmdList.add("sh");
				        	 cmdList.add("./test.sh");			 
				  	         break;
			        }
			        
			        pb = new ProcessBuilder(cmdList);
			        pb.directory(new File("/data/home/pathwaycloud/PathwayWebService/bin"));
					p = pb.start();			
					/*BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			         String line; 
			         while((line = reader.readLine()) != null) { 
			        	 logger.info(line);
			         }  */        	         
					p.waitFor();
	        	 }		        
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(null != p) {
					p.destroy();
				}else {
					throw new RuntimeException("Cannot close Process Streams");
				}
			}
	    }
}
