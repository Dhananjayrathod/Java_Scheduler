package com.tmat.scheduler.thread;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tmat.scheduler.constants.UploadConstants;
import com.tmat.scheduler.model.SchedulerModel;
import com.tmat.scheduler.util.FileUploaderUtil;

public class UploadFileJob implements Job
{
	
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		SchedulerModel schedulardata = (SchedulerModel) context.getJobDetail().getJobDataMap().get(UploadConstants.SCEDULAR_DATA);
		
		System.out.println("Job executing ........................"+schedulardata.getIdFTP_File_Audit());
		System.out.println(schedulardata);
		
		FileUploaderUtil task = new FileUploaderUtil(schedulardata.getHost(), 21, schedulardata.getUsername(), schedulardata.getPassword(), 
				schedulardata.getLPath());
		
		boolean isUploadFileStatus = task.uploadFile();
		if(isUploadFileStatus){
			 System.out.println("The first file is uploaded successfully.");
		}
		System.out.println("Job termionating ........................" +schedulardata.getIdFTP_File_Audit());
		
		
	}
	
}
