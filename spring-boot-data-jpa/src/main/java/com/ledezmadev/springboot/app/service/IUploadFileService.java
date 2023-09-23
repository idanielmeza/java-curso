package com.ledezmadev.springboot.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface IUploadFileService {

	public Resource load(String filename);
	
	public String copy(MultipartFile file);
	
	public void delete(String filename);
	
}
