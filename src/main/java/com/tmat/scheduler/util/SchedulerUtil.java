package com.tmat.scheduler.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmat.scheduler.constants.UploadConstants;
import com.tmat.scheduler.model.SchedulerModel;

public class SchedulerUtil {

		public static List<SchedulerModel> getSchedulerList() {
			
			System.out.println("----------- getSchedulerList -----------");
			
			Connection connection = DBConnection.getConnection();
			List<SchedulerModel> schedulerDatas = new ArrayList<SchedulerModel>();
			try {
				Statement statement=connection.createStatement();
				String sql="select * from FTP_File_Audit";
				ResultSet resultset=statement.executeQuery(sql);
				
				while(resultset.next())
				{
					if(resultset.getString("uploadedStatus") !=null && !resultset.getString("uploadedStatus").equalsIgnoreCase(UploadConstants.UPLOAD_STATUS)){
						SchedulerModel schedulardata = new SchedulerModel();
						
						schedulardata.setIdFTP_File_Audit(resultset.getInt("idFTP_File_Audit"));
						schedulardata.setHost(resultset.getString("Host"));
						schedulardata.setUsername(resultset.getString("Username"));
						schedulardata.setPassword("123456789");
						schedulardata.setLPath(resultset.getString("LPath"));
						schedulardata.setRPath(resultset.getString("RPath"));
						schedulardata.setLastUploadedTime(resultset.getTimestamp("UploadedTime"));
						schedulardata.setUploadStatus(resultset.getString("Status"));
						schedulardata.setUploadInterval(resultset.getLong("uploadInterval"));
						
						schedulerDatas.add(schedulardata);
					}
				}
				
			} catch (SQLException e) {
				System.out.println("--------------- SQLException ---------------");
				e.printStackTrace();
			}
			
			return schedulerDatas;
		}
}
