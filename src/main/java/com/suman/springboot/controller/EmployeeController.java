package com.suman.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suman.springboot.Response.EmployeeResponse;
import com.suman.springboot.model.EmployeeModel;
import com.suman.springboot.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@PostMapping(value = "/add", produces = "applicaton/json", consumes = "application/json")
	public String addEmployee(@RequestBody EmployeeModel employee) throws Exception{
	
		return service.add(employee);
	}
	
	@PostMapping(value = "/calculateTax", produces = "application/json", consumes = "application/json")
	public ResponseEntity<EmployeeResponse> calculateTaxAmount(@RequestBody EmployeeModel employee) throws Exception{
		
		EmployeeResponse response= service.calculateTax(employee);
		if(response!=null) {
		   return new ResponseEntity<EmployeeResponse>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<EmployeeResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
