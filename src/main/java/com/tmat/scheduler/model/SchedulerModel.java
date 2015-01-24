package com.tmat.scheduler.model;

import java.util.Date;

public class SchedulerModel {
	
	int idFTP_File_Audit;
	String host;
	String username;
	String password;
	String LPath;
	String RPath;
	Date LastUploadedTime;
	Date NextUploadTime;
	String UploadStatus;
	long uploadInterval;
	
	public SchedulerModel() {
		// TODO Auto-generated constructor stub
	}
	

	public SchedulerModel(int idFTP_File_Audit, String host, String username,
			String password, String lPath, String rPath, Date lastUploadedTime,
			Date nextUploadTime, String uploadStatus, long uploadInterval) {
		super();
		this.idFTP_File_Audit = idFTP_File_Audit;
		this.host = host;
		this.username = username;
		this.password = password;
		LPath = lPath;
		RPath = rPath;
		LastUploadedTime = lastUploadedTime;
		NextUploadTime = nextUploadTime;
		UploadStatus = uploadStatus;
		this.uploadInterval = uploadInterval;
	}


	public long getUploadInterval() {
		return uploadInterval;
	}

	public void setUploadInterval(long uploadInterval) {
		this.uploadInterval = uploadInterval;
	}


	public int getIdFTP_File_Audit() {
		return idFTP_File_Audit;
	}
	public void setIdFTP_File_Audit(int idFTP_File_Audit) {
		this.idFTP_File_Audit = idFTP_File_Audit;
	}


	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLPath() {
		return LPath;
	}
	public void setLPath(String lPath) {
		LPath = lPath;
	}
	public String getRPath() {
		return RPath;
	}
	public void setRPath(String rPath) {
		RPath = rPath;
	}
	public java.util.Date getLastUploadedTime() {
		return LastUploadedTime;
	}
	public void setLastUploadedTime(java.util.Date lastUploadedTime) {
		LastUploadedTime = lastUploadedTime;
	}
	public java.util.Date getNextUploadTime() {
		return NextUploadTime;
	}
	public void setNextUploadTime(java.util.Date nextUploadTime) {
		NextUploadTime = nextUploadTime;
	}
	public String getUploadStatus() {
		return UploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		UploadStatus = uploadStatus;
	}
	@Override
	public String toString() {
		return "Schedulardata [host=" + host + ", username=" + username
				+ ", password=" + password + ", LPath=" + LPath + ", RPath="
				+ RPath + ", LastUploadedTime=" + LastUploadedTime
				+ ", NextUploadTime=" + NextUploadTime + ", UploadStatus="
				+ UploadStatus + "]";
	}
	
	
	
}
