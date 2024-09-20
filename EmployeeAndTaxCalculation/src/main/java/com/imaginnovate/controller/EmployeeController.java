package com.imaginnovate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.dto.EmployeeDTO;
import com.imaginnovate.dto.TaxDeductionDTO;
import com.imaginnovate.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeDTO dto){
	 employeeService.saveEmployee(dto);
		return new ResponseEntity<String>("Employee saved suceessfully",HttpStatus.OK);
	}
	@GetMapping("/{employeeId}/tax-deductions")
	public ResponseEntity<TaxDeductionDTO> getTaxDeductionsByEmp(@PathVariable String employeeId){
		 TaxDeductionDTO deductionData = employeeService.getTaxDeductionsByEmp(employeeId);
		 return new ResponseEntity<TaxDeductionDTO>(deductionData, HttpStatus.OK);
		
	}

}
