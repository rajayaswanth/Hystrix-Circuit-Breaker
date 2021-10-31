package com.mla.htd;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixCircuitBreakerClientController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	Employee singleCall() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(HttpStatus.ACCEPTED);
		try {
			response = template.exchange("http://localhost:8080/employee", HttpMethod.GET, null, Employee.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response.getBody();
	}
	
	@RequestMapping(value = "/loop", method = RequestMethod.GET)
	Employee hello() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(HttpStatus.ACCEPTED);
		for(int i=0;i<100;i++) {
			try {
				response = template.exchange("http://localhost:8080/employee", HttpMethod.GET, null, Employee.class);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return response.getBody();
	}

}
