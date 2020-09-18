package com.techprimers.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/dms-report/reportDocument")
public class DmsReportResource {

	@Value("${server.port}")
	String port;
    @GetMapping
    public String hello() {
    	
        return "Document reported!! "+port;
    }
}
