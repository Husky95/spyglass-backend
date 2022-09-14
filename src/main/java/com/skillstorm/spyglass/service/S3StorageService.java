package com.skillstorm.spyglass.service;
import org.springframework.web.multipart.MultipartFile;

public interface S3StorageService {
	  String uploadFileToS3(MultipartFile file);

	  byte[] downloadFileFromS3(String fileName);

	  String deleteFileFromS3(String fileName);
}
