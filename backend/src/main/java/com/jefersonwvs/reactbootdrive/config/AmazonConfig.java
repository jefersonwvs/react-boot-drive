package com.jefersonwvs.reactbootdrive.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

	@Value("${awsRegion}")
	private String region;

	@Value("${awsAccessKey}")
	private String accessKey;

	@Value("${awsSecretAccessKey}")
	private String secretAccessKey;

	@Value("${awsS3Bucket}")
	private String s3Bucket;

	@Bean
	public AmazonS3 s3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretAccessKey);
		return AmazonS3ClientBuilder
				.standard()
				.withRegion(this.region)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();
	}

	@Bean
	public String s3Bucket() {
		return this.s3Bucket;
	}
}
