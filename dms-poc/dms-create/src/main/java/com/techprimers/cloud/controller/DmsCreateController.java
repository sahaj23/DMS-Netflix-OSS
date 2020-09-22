package com.techprimers.cloud.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.techprimers.cloud.model.DmsCreateModel;
import com.techprimers.cloud.repository.DmsCreateRepository;

@RestController
@RequestMapping("/rest/dms-create/createDocument")
public class DmsCreateController {

	@Autowired
    DmsCreateRepository fileRepository;

    @GetMapping("/")
    public String index() {
        return "uploadform";
    }

    @PostMapping("/")
    public ResponseEntity<String> uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
        List<String> fileNames = new ArrayList<String>();
        try {
            List<DmsCreateModel> storedFile = new ArrayList<DmsCreateModel>();
            for (MultipartFile file : files) {
            	DmsCreateModel fileModel = fileRepository.findByName(file.getOriginalFilename());
                if (fileModel != null) {
                    fileModel.setPic(file.getBytes());
                    fileModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
                } else {
                    fileModel = new DmsCreateModel(file.getOriginalFilename(), file.getContentType(), file.getBytes(),new Timestamp(new java.util.Date().getTime()));
                }

                fileNames.add(file.getOriginalFilename());
                storedFile.add(fileModel);
            }

            fileRepository.saveAll(storedFile);
            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", fileNames);
        } catch (Exception e) {
        	System.out.println(e);
            model.addAttribute("message", "Fail!");
            model.addAttribute("files", fileNames);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Document could not be uploaded!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Document uploaded successfully!!");
    }
    
   
}
