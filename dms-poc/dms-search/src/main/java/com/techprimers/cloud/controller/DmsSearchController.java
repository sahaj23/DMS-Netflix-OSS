package com.techprimers.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.cloud.model.DmsSearchModel;
import com.techprimers.cloud.repository.DmsSearchRepository;

@RestController
@RequestMapping("/rest/dms-search/searchDocument")
public class DmsSearchController {

	@Autowired
    DmsSearchRepository fileRepository;

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
	
	
}
