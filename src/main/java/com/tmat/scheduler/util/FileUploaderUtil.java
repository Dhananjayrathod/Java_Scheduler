package com.tmat.scheduler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class FileUploaderUtil {
	
	private String host;
	private int port;
	private String username;
	private String password;
	private String fileSourcePath;
	
	public FileUploaderUtil(String host, int port, String username, String password, String fileSourcePath) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.fileSourcePath = fileSourcePath;
	}

	public boolean uploadFile() {
		
		boolean isUploadStatus= false ;
		
		FTPClient ftpClient = new FTPClient();
		
        try {
 
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            // APPROACH #1: uploads first file using an InputStream
            System.out.println("fileSourcePath : "+fileSourcePath);
            File firstLocalFile = new File(fileSourcePath);
 
            String firstRemoteFile = firstLocalFile.getName();
            
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            
            isUploadStatus = ftpClient.storeFile(firstRemoteFile, inputStream);
            
            inputStream.close();
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
        	System.out.println("-----Finally-----");
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return isUploadStatus;
		
	}
	
	protected void updateDatabase(String host, String username, String lPath, String rPath, 
			long fSize, String status, Date time) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		Timestamp timestamp = new Timestamp(time.getTime());
		
		connection = DBConnection.getConnection();
		
		String sql = "insert into FTP_File_Audit (Host, UserName, LPath,RPath, UploadedTime, FSize, Status) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, host);
			statement.setString(2, username);
			statement.setString(3, lPath);
			statement.setString(4, rPath);
			statement.setTimestamp(5, timestamp);
			statement.setLong(6, fSize);
			statement.setString(7, status);
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}