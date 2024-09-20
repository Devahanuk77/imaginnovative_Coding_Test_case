package com.imaginnovate.service;

import com.imaginnovate.dto.EmployeeDTO;
import com.imaginnovate.dto.TaxDeductionDTO;
import com.imaginnovate.entity.Employee;

public interface EmployeeService {
	
	EmployeeDTO saveEmployee(EmployeeDTO dto);
	
	Employee convertToEmployee(EmployeeDTO dto);
	
	EmployeeDTO convertToEmployeeDto(Employee emp); 
	
	TaxDeductionDTO getTaxDeductionsByEmp(String employeeId);
	
	double calculateTax(double yearlySalary);
	double calculateCess (double yearlySalary);

}
