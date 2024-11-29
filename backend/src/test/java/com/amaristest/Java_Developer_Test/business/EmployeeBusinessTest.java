package com.amaristest.Java_Developer_Test.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.amaristest.Java_Developer_Test.model.Employee;

public class EmployeeBusinessTest {

    private final EmployeeBusiness employeeBusiness = new EmployeeBusiness();

    @Test
    public void testComputeAnnualSalary() {
        Employee employee = new Employee(1L, "Juan Romero", 5000, 30, "");
        Integer annualSalary = employeeBusiness.computeAnnualSalary(employee);
        assertEquals(60000, annualSalary);
    }

    @Test
    public void testComputeAnnualSalary_NullEmployee() {
        assertThrows(IllegalArgumentException.class, () -> {
            employeeBusiness.computeAnnualSalary(null);
        });
    }

    @Test
    public void testComputeAnnualSalary_NegativeSalary() {
        Employee employee = new Employee(1L, "Juan Romero", -5000, 30, "");
        assertThrows(IllegalArgumentException.class, () -> {
            employeeBusiness.computeAnnualSalary(employee);
        });
    }
}
