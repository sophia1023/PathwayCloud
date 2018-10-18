package snu.bioinfo.pathway.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import snu.bioinfo.pathway.AsyncConfig;
import snu.bioinfo.pathway.domain.ProjectVO;
import snu.bioinfo.pathway.domain.ToolVO;
import snu.bioinfo.pathway.service.AsyncTask;


@Controller
public class RunController {

	private static final Logger logger = LoggerFactory.getLogger(RunController.class);
	
    @Autowired
    private ServletContext sc; 
    
    @Autowired
    private ToolVO tvo;
    
    @Autowired
    private ProjectVO pvo;
    
    public REXP x = null;
    public RConnection c = null;
    public String retStr = "";
    public BufferedReader bf = null;
	public FileReader fileReader = null;
    
	@RequestMapping(value = "run", method = RequestMethod.GET)
	public void run(Locale locale, Model model){//, REXPMismatchException, REngineException{
		logger.info("Welcome runpage! The client locale is {}.", locale); 
		//rcode 하나씩 입력
				/*String filePath = sc.getRealPath("/resources/rcode/gsva.txt");		
		    	String readTxt;    
		    	System.out.println("!!!!!"+filePath);
		    	
		    	c = new RConnection();		 
				 
		        fileReader = new FileReader(filePath);
		        if(fileReader != null) {
		            bf = new BufferedReader(fileReader);
		            while((readTxt = bf.readLine()) != null) {
		            	c.parseAndEval(readTxt);
		            	System.out.println(readTxt);
		            }
		              	x = c.eval("print(sum)");
		                retStr = x.asString();
		                System.out.println(retStr);
		                model.addAttribute("result", retStr);
		        } else {
		        	System.out.println("nononoonoonon");
		        
		        }*/
 	}  
	
	@RequestMapping(value = "run", method = RequestMethod.POST)
	public void makeProject(Locale locale, Model model, ProjectVO pvo){		
		 /*makeProject*/
		String projectName = pvo.getProjectName();
		
		Process p = null;
		ProcessBuilder pb = null;
		List<String> cmdList = new ArrayList<String>();
		cmdList.add("sh");
        // adding command and args to the list       
         try {
				cmdList.add("/data/home/pathwaycloud/PathwayWebService/checkProject.sh");
				cmdList.add(projectName);
				pb = new ProcessBuilder(cmdList);
				pb.directory(new File("/data/home/pathwaycloud/PathwayWebService"));
	        
				p = pb.start();			
				BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
				 String line; 
				 while((line = reader.readLine()) != null) { 
					 logger.info(line);
					 pvo.setpCheckResult(line);
					 model.addAttribute("pCheckRes", pvo.getpCheckResult());
				 }          	         
				p.waitFor();
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
	
	/** 시뮬레이션 스레드 */
    @Resource(name = "asyncTask")
    private AsyncTask asyncTask;
    
    /** AsyncConfig */
    @Resource(name = "asyncConfig")
    private AsyncConfig asyncConfig;


	@RequestMapping(value = "run/toolOption", method = RequestMethod.POST)
	public String toolOption(Locale locale, Model model, ToolVO tvo, HttpServletRequest request, HttpServletResponse response){		
        
		String inputFile = "";
        String infoFile  = "";
        String inputPath = "/data/home/pathwaycloud/PathwayWebService/input/"+tvo.getProjectName();
		
		ArrayList<MultipartFile> files = tvo.getInputFiles();
		ArrayList<String> fileNames = new ArrayList<>();
		
		if(null != files && files.size() > 0) {
			for(int i = 0; i < files.size(); i++) {
				MultipartFile file = files.get(i);
				String fileName = file.getOriginalFilename();
				fileNames.add(fileName);
				try {
					byte[] bytes = file.getBytes();
					File serverFile = new File(inputPath + File.separator + fileName);
					file.transferTo(serverFile);
				} catch (Exception e) {
					return "You failed to upload " + fileName + " => " + e.getMessage();
				}
			}
		}
					
         /*async*/         
         try {
             // 등록 가능 여부 체크
             if (asyncConfig.isTaskExecute()) {
                 // task 
                 asyncTask.executor(tvo, fileNames);
             } else {				
                 System.out.println("==============>>>>>>>>>>>> THREAD 개수 초과");
             }
         } catch (TaskRejectedException e) {
             // TaskRejectedException : 개수 초과시 발생
             System.out.println("==============>>>>>>>>>>>> THREAD ERROR");
             System.out.println("TaskRejectedException : 등록 개수 초과");
             System.out.println("==============>>>>>>>>>>>> THREAD END");
         }

         
        return "redirect:/runReturn";
 	}
	
	@RequestMapping(value = "/runReturn", method = RequestMethod.GET)
	public void showResult(Locale locale, Model model) {
 	}  
}
