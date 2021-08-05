package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {
	
	//@Autowired
	//FileupDao fileupDao;
	
	//file upload
	public String restore(MultipartFile file) {
		System.out.println("FileupService.restore");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		
		//file insert db
		
		//original name
		String orgFile = file.getOriginalFilename();
		System.out.println("orgFile = "+orgFile);
		
		//filename extension
		String exName = orgFile.substring(orgFile.lastIndexOf("."));
		System.out.println("exName = "+exName);
		
		//rename file
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println("saveName = "+saveName);
		
		//file path
		String filePath = saveDir+"\\"+saveName;
		System.out.println("filePath = "+filePath);
		
		//file size
		long fileSize = file.getSize();
		System.out.println("fileSize = "+fileSize);
		
		//file restore on hdd
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
	}
}
