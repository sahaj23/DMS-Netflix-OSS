package com.techprimers.cloud.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Document")
public class Document {

	
	private String filename;
	private String awsRegion;
	private String bucketName;
	
	
	private String timeStamp;

	@DynamoDBHashKey(attributeName = "filename")
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	@DynamoDBAttribute(attributeName = "awsRegion")
	public String getAwsRegion() {
		return awsRegion;
	}

	
	public void setAwsRegion(String awsRegion) {
		this.awsRegion = awsRegion;
	}

	@DynamoDBAttribute(attributeName = "bucketName")
	public String getBucketName() {
		return bucketName;
	}


	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	@DynamoDBAttribute(attributeName = "timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}

	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
