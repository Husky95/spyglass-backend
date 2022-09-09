package com.skillstorm.spyglass.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
	

	@Value("${amazon.credentials.access-key}")
	private String awsAccessKey;

	@Value("${amazon.credentials.secret-key}")
	private String awsSecretKey;

	@Value("${amazon.region.static}")
	private String region;
	  
    @Bean
    public AmazonS3 s3() {
    	AWSCredentials awsCredentials =  new BasicAWSCredentials( awsAccessKey, awsSecretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}