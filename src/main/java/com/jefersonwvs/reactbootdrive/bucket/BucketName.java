package com.jefersonwvs.reactbootdrive.bucket;

import org.springframework.beans.factory.annotation.Value;

public enum BucketName {

	PROFILE_IMAGE("react-boot-drive");

	private final String bucketName;

	BucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketName() {
		return this.bucketName;
	}

}
