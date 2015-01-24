package com.tmat.scheduler.thread;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import com.tmat.scheduler.constants.UploadConstants;
import com.tmat.scheduler.model.SchedulerModel;
import com.tmat.scheduler.util.DBConnection;
import com.tmat.scheduler.util.SchedulerUtil;

public class DBTask extends Thread{

	public void run() {
		while (true) {
			System.out.println("DBTask...........");
			
			try {
				List<SchedulerModel> schedulerdatas = SchedulerUtil.getSchedulerList();
				
				for (SchedulerModel schedulerdata : schedulerdatas) {
					
					Date uploadedTime = schedulerdata.getLastUploadedTime();
					
					Date currentTime = new Date();
					System.out.println("uploadedTime: "+uploadedTime);
					System.out.println(new Date() + " : "  +uploadedTime);
					
		        	if(currentTime.compareTo(uploadedTime)<0){
		        		
		        		Connection connection = DBConnection.getConnection();
		        		String sql="update FTP_File_Audit set uploadedStatus = 'Completed' where idFTP_File_Audit="+schedulerdata.getIdFTP_File_Audit();
		        		try {
		        			Statement statement  = connection.createStatement();
		        			statement.execute(sql);
		        			connection.close();
		        			System.out.println("Job done!");
		        		} catch (SQLException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        		
		        		System.out.println("currentTime is before uploadedTime"); /// cpode
		        		
						JobDetail job = new JobDetail();
				    	job.setName("JOB"+schedulerdata.getIdFTP_File_Audit());
				    	job.setJobClass(UploadFileJob.class);
				    	job.getJobDataMap().put(UploadConstants.SCEDULAR_DATA, schedulerdata); 
						
						SimpleTrigger trigger = new SimpleTrigger();

				    	trigger.setName("TRG"+schedulerdata.getIdFTP_File_Audit());
				    	trigger.setStartTime(uploadedTime);
				    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
				    	trigger.setRepeatInterval(schedulerdata.getUploadInterval());
				    	
				    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				    	scheduler.start();
				    	scheduler.scheduleJob(job, trigger);
				    	
		        	}
					
				}
				
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
