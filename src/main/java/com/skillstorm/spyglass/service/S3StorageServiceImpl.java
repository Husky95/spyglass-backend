package com.skillstorm.spyglass.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class S3StorageServiceImpl implements S3StorageService {

    @Value("${Amazon.s3.imageBucket}")
	private String bucketName;
    
	@Autowired
	private AmazonS3 amazonS3;
	
	@Override
	public String uploadFileToS3(MultipartFile file) {
		    File fileObj = convertMultiPartFileToFile(file);
		    String fileName = generateFileName() + "_" + file.getOriginalFilename();
		    PutObjectResult putObjectResult = amazonS3
		        .putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		    fileObj.delete();
		    if (Objects.nonNull(putObjectResult)) {
		      return fileName;
		    }
		    return null;
	}

	@Override
	public byte[] downloadFileFromS3(String fileName) {
		S3Object s3Object = amazonS3.getObject(bucketName, fileName);
	    S3ObjectInputStream inputStream = s3Object.getObjectContent();
	    try {
	      byte[] content = IOUtils.toByteArray(inputStream);
	      return content;
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public String deleteFileFromS3(String fileName) {
		 amazonS3.deleteObject(bucketName, fileName);
		 return fileName + " removed ...";
	}
	
	private File convertMultiPartFileToFile(MultipartFile file) {
	    File convertedFile = new File(file.getOriginalFilename());
	    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
	      fos.write(file.getBytes());
	    } catch (IOException e) {
	    }
	    return convertedFile;
	  }
	
	private String generateFileName() {
		String generatedString = RandomStringUtils.randomAlphanumeric(16);
		return generatedString;
	}

}
