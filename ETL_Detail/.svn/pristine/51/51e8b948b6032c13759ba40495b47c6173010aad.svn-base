package ule.com.etl.action;

import java.io.File;

import org.apache.log4j.Logger;

public class DeleteFile {
	
	private static Logger log = Logger.getLogger(DeleteFile.class);
	private static DeleteFile instance=new DeleteFile();
    public static DeleteFile getInstance(){
        return  instance;
    }
	
	public void delFile(String fileName) {
	    try {
	      File myDelFile = new File("/home/web/etl/day_etl/"+fileName.toString()+".txt");
	      log.info("检查文件"+myDelFile.getPath()+"是否存在");
	      if(myDelFile.exists()){
	    	  log.info("开始删除文件"+myDelFile.getPath());
	    	  myDelFile.delete();
	      }
	      log.info("开始调用sh脚本");
    	  ShellAction.getInstance().action(fileName);
	    }
	    catch (Exception e) {
	      System.err.println("删除文件操作出错");
	      e.printStackTrace();
	    }
	  }
}
