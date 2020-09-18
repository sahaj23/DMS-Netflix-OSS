package com.techprimers.cloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/dms-create/createDocument")
public class DmsCreateResource {

    @GetMapping
    public String createDocument() {
        return "Document created!";
    }

    

}
