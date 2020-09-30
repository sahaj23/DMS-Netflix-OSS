package com.techprimers.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.techprimers.cloud.model.DmsSearchModel;
import com.techprimers.cloud.model.Document;
import com.techprimers.cloud.repository.DmsSearchRepository;

@RestController
@RequestMapping("/rest/dms-search/searchDocument")
public class DmsSearchController {

	@Autowired
    DmsSearchRepository fileRepository;

	static String accesskey = "AKIA4TML7TVYWLU3TBQ2";
	static String secretkey = "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";
	static AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1)
			.build();
	
	@GetMapping("/files/{filename}.{ext}")
    public ResponseEntity<Object> downloadFile(@PathVariable String filename,@PathVariable("ext") String ext) {
    	DmsSearchModel file = fileRepository.findByName(filename+"."+ext);
    	if(file==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No document found!");
    	}
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getPic());
    }
	
	@GetMapping("/fileSearch/{filename}.{ext}")
    public DmsSearchModel searchFile(@PathVariable String filename,@PathVariable("ext") String ext) {
    	DmsSearchModel file = fileRepository.findByName(filename+"."+ext);
    	if(file==null) {
    		return null;
    	}
        return file;
    }
	
	@GetMapping("/fileSearchFromDynamo/{filename}.{ext}")
	public ResponseEntity<Object> searchFileFromDynamo(@PathVariable String filename,@PathVariable("ext") String ext) {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Document itemRetrieved = mapper.load(Document.class, filename+"."+ext);
		System.out.println(itemRetrieved);
		if(itemRetrieved==null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Document not found!!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(itemRetrieved);
    }
	
	
}
