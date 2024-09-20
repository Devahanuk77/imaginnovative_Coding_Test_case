package com.imaginnovate.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.dao.EmployeeRepository;
import com.imaginnovate.dto.EmployeeDTO;
import com.imaginnovate.dto.TaxDeductionDTO;
import com.imaginnovate.entity.Employee;
import com.imaginnovate.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository repository;

	@Override
	public Employee convertToEmployee(EmployeeDTO dto) {
		Employee emp = new Employee();
		emp.setEmployeeId(dto.getEmployeeId());
		emp.setFirstName(dto.getFirstName());
		emp.setLastName(dto.getLastName());
		emp.setEmail(dto.getEmail());
		emp.setPhoneNumbers(dto.getPhoneNumbers());
		emp.setDoj(dto.getDoj());
		emp.setSalary(dto.getSalary());
		return emp;
	}

	@Override
	public EmployeeDTO convertToEmployeeDto(Employee emp) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(emp.getEmployeeId());
		dto.setFirstName(emp.getFirstName());
		dto.setLastName(emp.getLastName());
		dto.setEmail(emp.getEmail());
		dto.setPhoneNumbers(emp.getPhoneNumbers());
		dto.setDoj(emp.getDoj());
		dto.setSalary(emp.getSalary());
		return dto;	
	}
	
	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO dto) {
		Employee employee = convertToEmployee(dto);
		employee = repository.save(employee);
		return convertToEmployeeDto(employee);
	}

	@Override
	public TaxDeductionDTO getTaxDeductionsByEmp(String employeeId) {
	Optional<Employee> employee = repository.findById(employeeId);
	LocalDate doj = employee.get().getDoj();
	LocalDate current = LocalDate.now();
	long monthsWorked = ChronoUnit.MONTHS.between(doj, current);
	System.out.println(monthsWorked);
	double yearlySalary = employee.get().getSalary()*monthsWorked;
	System.out.println(yearlySalary);
	double taxAmount = calculateTax(yearlySalary);
	double cessAmount = calculateCess(yearlySalary);
	TaxDeductionDTO dto = new TaxDeductionDTO();
	dto.setEmployeeId(employee.get().getEmployeeId());
	dto.setFirstName(employee.get().getFirstName());
	dto.setLastName(employee.get().getLastName());
	dto.setYearlySalary(yearlySalary);
	dto.setTaxAmount(taxAmount);
	dto.setCessAmount(cessAmount);
		return dto;
	}

	@Override
	public double calculateTax(double yearlySalary) {
		double tax =0;
		if(yearlySalary >250000) {
			if(yearlySalary <=500000) {
				tax = (yearlySalary-250000) * 0.05;
			}
			else if(yearlySalary <=1000000) {
				tax = 12500+(yearlySalary - 500000)*0.10;
			}
			else {
				tax = 62500 +(yearlySalary-1000000)*0.20;
			}
		}
		return tax;
	}

	@Override
	public double calculateCess(double yearlySalary) {
		return yearlySalary > 2500000 ? (yearlySalary-2500000)*0.02 :0; 
		
	}
	
	

}
