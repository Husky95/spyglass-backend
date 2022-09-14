package com.skillstorm.spyglass.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skillstorm.spyglass.dto.ImageDTO;
import com.skillstorm.spyglass.service.S3StorageService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/images")
public class ImageController {
	@Autowired
	private S3StorageService s3StorageService;
	
	@Value("${Amazon.s3.imageBucket}")
    private String bucketName;
    
    @Value("${Amazon.region.static}")
    private String region;
    
	@PostMapping("/upload")
	  public ResponseEntity<ImageDTO>  uploadFile(@RequestParam(value = "file") MultipartFile file, Model model) {
	    String imageName = s3StorageService.uploadFileToS3(file);
	    String url = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + imageName;
	    ImageDTO imageURL = new ImageDTO();
	    imageURL.setImageURL(url);
        return ResponseEntity.ok(imageURL);
	  }


	  @DeleteMapping("/delete/{fileName}")
	  public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
	    return new ResponseEntity<>(s3StorageService.deleteFileFromS3(fileName), HttpStatus.OK);
	  }
}
