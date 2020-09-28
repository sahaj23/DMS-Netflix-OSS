package com.techprimers.cloud.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.techprimers.cloud.model.DmsCreateModel;
import com.techprimers.cloud.repository.DmsCreateRepository;

@RestController
@RequestMapping("/rest/dms-create/createDocument")
public class DmsCreateController {

	@Autowired
    private RestTemplate restTemplate;
	@Autowired
    DmsCreateRepository fileRepository;

    @GetMapping("/")
    public String index() {
        return "uploadform";
    }

    @HystrixCommand(fallbackMethod = "fallbackUpload")
    @PostMapping("/")
    public ResponseEntity<String> uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
        List<String> fileNames = new ArrayList<String>();
//        try {
        	
             
            List<DmsCreateModel> storedFile = new ArrayList<DmsCreateModel>();
            for (MultipartFile file : files) {
            
            	String url = "http://dms-search/rest/dms-search/searchDocument/fileSearch/"+file.getOriginalFilename()+"/";
            	System.out.println("Original "+file.getOriginalFilename());
            	DmsCreateModel fileModel = restTemplate.getForObject(url, DmsCreateModel.class);
            	System.out.println("reached  here");
                if (fileModel != null) {
                    try {
						fileModel.setPic(file.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    fileModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
                } else {
                    try {
						fileModel = new DmsCreateModel(file.getOriginalFilename(), file.getContentType(), file.getBytes(),new Timestamp(new java.util.Date().getTime()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }

                fileNames.add(file.getOriginalFilename());
                storedFile.add(fileModel);
            }

            fileRepository.saveAll(storedFile);
            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", fileNames);
//        } catch (Exception e) {
//        	System.out.println(e);
//            model.addAttribute("message", "Fail!");
//            model.addAttribute("files", fileNames);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Document could not be uploaded! Exception "+e);
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Document uploaded successfully!!");
    }
    
    public ResponseEntity<String> fallbackUpload(@RequestParam("files") MultipartFile[] files, Model model){
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Document could not be uploaded! One or more services might be down! Try again later");
    }
    
    
   
}
