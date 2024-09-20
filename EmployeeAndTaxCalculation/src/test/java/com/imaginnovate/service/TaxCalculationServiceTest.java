package com.imaginnovate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaxCalculationServiceTest {

	private EmployeeService service;

	@Test
	public void testTaxCalculationForAmount() {
		double tax = service.calculateTax(800000);
		assertEquals(37500, tax);
	}

	@Test
	public void testCessCalculation() {
		double cess = service.calculateCess(2800000);

		assertEquals(6000, cess);
	}

}
