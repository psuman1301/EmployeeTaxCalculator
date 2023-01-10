package com.suman.springboot.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suman.springboot.Response.EmployeeResponse;
import com.suman.springboot.model.EmployeeModel;

@Service
public class EmployeeService {

	public String add(EmployeeModel employee) throws Exception {
		
		List<EmployeeModel> list= new ArrayList<>();
		list.add(employee);
		
		if(list.isEmpty()) {
			return "Unsuccessful";
		}else {
			return employee.getEmployeeId()+" successfully added";
		}
	}
	
	public EmployeeResponse calculateTax(EmployeeModel employee) throws Exception{
		
		String date="31/03/2023";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		LocalDate firstDate = LocalDate.parse(employee.getDoj(), formatter);
        LocalDate secondDate = LocalDate.parse(date, formatter);
		Long noOfDays= ChronoUnit.DAYS.between(secondDate,firstDate);
		
		if(employee.getSalary()<=250000) {
			EmployeeResponse response= new EmployeeResponse();
			response.setEmployeeId(employee.getEmployeeId());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setYearlySalary(employee.getSalary()*12);
			response.setTaxAmount(0.0);
			response.setCessAmount(0.0);
			
			return response;
		}else if(employee.getSalary()>250000 && employee.getSalary()<=500000) {
			
			Double tax= ((employee.getSalary()/30*noOfDays)-250000)*5/100;
			EmployeeResponse response= new EmployeeResponse();
			response.setEmployeeId(employee.getEmployeeId());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setYearlySalary(employee.getSalary()*12);
			response.setTaxAmount(tax);
			response.setCessAmount(0.0);
			
			return response;
		}else if(employee.getSalary()>500000 && employee.getSalary()<=1000000) {
			
			Double tax= (((employee.getSalary()/30*noOfDays)-250000)*5/100)+(((employee.getSalary()/30*noOfDays)-500000)*10/100);
			EmployeeResponse response= new EmployeeResponse();
			response.setEmployeeId(employee.getEmployeeId());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setYearlySalary(employee.getSalary()*12);
			response.setTaxAmount(tax);
			response.setCessAmount(0.0);
			
			return response;
		}else if(employee.getSalary()>1000000) {
			
			Double tax= (((employee.getSalary()/30*noOfDays)-250000)*5/100)
					   +(((employee.getSalary()/30*noOfDays)-500000)*10/100)
					   +(((employee.getSalary()/30*noOfDays)-1000000)*20/100);
			EmployeeResponse response= new EmployeeResponse();
			response.setEmployeeId(employee.getEmployeeId());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setYearlySalary(employee.getSalary()*12);
			response.setTaxAmount(tax);
			response.setCessAmount(0.0);
			
			return response;
		}else if(employee.getSalary()>2500000) {
			
			Double tax= (((employee.getSalary()/30*noOfDays)-250000)*5/100)
					   +(((employee.getSalary()/30*noOfDays)-500000)*10/100)
					   +(((employee.getSalary()/30*noOfDays)-1000000)*20/100);
			Double cess= (((employee.getSalary()/30*noOfDays)-2500000)*2/100);
			EmployeeResponse response= new EmployeeResponse();
			response.setEmployeeId(employee.getEmployeeId());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setYearlySalary(employee.getSalary()*12);
			response.setTaxAmount(tax);
			response.setCessAmount(cess);
			
			return response;
		}else {
			return null;
		}
	}
}
