package com.imaginnovate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.imaginnovate.dao.EmployeeRepository;
import com.imaginnovate.dto.EmployeeDTO;
import com.imaginnovate.entity.Employee;

public class EmployeeServiceTest {
	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	private Employee employee;
	private EmployeeDTO dto;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		employee = new Employee();
		employee.setEmployeeId("E123");
		employee.setFirstName("Jaya");
		employee.setLastName("Shiva");
		employee.setEmail("jaya.shiva@example.com");
		employee.setPhoneNumbers(List.of("8993456789", "9876543210"));
		employee.setDoj(LocalDate.of(2024, 05, 16));
		employee.setSalary(50000.0);
	}

	@Test
	public void testSaveEmployee() {
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		EmployeeDTO savedEmployee1 = employeeService.saveEmployee(dto);
		Employee savedEmployee = employeeService.convertToEmployee(savedEmployee1);
		assertNotNull(savedEmployee);
		assertEquals("E123", savedEmployee.getEmployeeId());
		assertEquals("Jaya", savedEmployee.getFirstName());
	}

}
