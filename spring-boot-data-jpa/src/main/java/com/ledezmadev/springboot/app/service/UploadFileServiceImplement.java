package com.ledezmadev.springboot.app.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImplement implements IUploadFileService{

	@Override
	public Resource load(String filename) {
		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		
		Resource rescurso = null;

		try {
			rescurso = new UrlResource(pathFoto.toUri());
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		return rescurso;
	}

	@Override
	public String copy(MultipartFile file) {
		
		int lastIndex = file.getOriginalFilename().lastIndexOf('.');
		
		String extension = file.getOriginalFilename().substring(lastIndex + 1);
		
		String uuid = UUID.randomUUID().toString().concat(".").concat(extension);
		
		Path rootPath = Paths.get("uploads").resolve(uuid);
		
		Path rootAabsolutPath = rootPath.toAbsolutePath();
		
		try {
			Files.copy(file.getInputStream(), rootAabsolutPath);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return uuid;
	}

	@Override
	public void delete(String filename) {
		Path rootPath = Paths.get("uploads").resolve(filename).toAbsolutePath();
		
		File archivo = rootPath.toFile();
		
		if(archivo.exists() && archivo.canRead()) {
			archivo.delete();
		}
	}
	
}
