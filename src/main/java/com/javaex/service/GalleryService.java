package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	public GalleryVo writeGallery(GalleryVo galleryVo, MultipartFile file) {
		
		String saveDir = "C:\\javaStudy\\upload";
		
		// original name
		String orgName = file.getOriginalFilename();
		System.out.println("orgFile = " + orgName);
		galleryVo.setOrgName(orgName);

		// filename extension
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName = " + exName);
		
		// rename file
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName = " + saveName);
		galleryVo.setSaveName(saveName);
		
		// file path
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath = " + filePath);
		galleryVo.setFilePath(filePath);
		
		// file size
		long fileSize = file.getSize();
		System.out.println("fileSize = " + fileSize);
		galleryVo.setFileSize(fileSize);
		
		// file restore on hdd
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
		
		galleryDao.insertGallery(galleryVo);
		
		return galleryVo;
	}

}
