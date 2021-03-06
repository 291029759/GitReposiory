package ule.com.etl.action;

import org.apache.log4j.Logger;

public class ShellAction {
	
	private static Logger log = Logger.getLogger(ShellAction.class);
	
	private static ShellAction instance=new ShellAction();
    public static ShellAction getInstance(){
        return  instance;
    }
    
	public void action(String time){
		try {
			String shPath="chmod a+x "+time+"/home/web/etl/shell/call_kettle.sh ";
			log.info("脚本路径"+shPath);
			
			Process ps = Runtime.getRuntime().exec(shPath);   // Windows下调用程序
			//Process ps = Runtime.getRuntime().exec(""./shpath);
			ps.waitFor();  
			
			shPath = "bash /home/web/etl/shell/call_kettle.sh "+time+" >>/home/web/etl/etl_log/java_call_shell.log "; 
			log.info("脚本命令"+shPath);
			
			ps = Runtime.getRuntime().exec(shPath);
			log.info("脚本调用完成");
			/*
	        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
	        StringBuffer sb = new StringBuffer();  
	        String line;  
	        while ((line = br.readLine()) != null) {  
	            sb.append(line).append("\n");  
	        }  
	        String result = sb.toString();  
	        System.out.println(result);  
	        */
		} catch (Exception e) {
			log.error("调用脚本错误:"+e.getMessage());
			e.printStackTrace();
		} 
	}
}
