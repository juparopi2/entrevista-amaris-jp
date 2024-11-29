package com.amaristest.Java_Developer_Test.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.amaristest.Java_Developer_Test.model.Employee;
import com.amaristest.Java_Developer_Test.repository.EmployeeRepository;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Juan Romero", 5000, 30, ""),
                new Employee(2L, "Pepito Perez", 6000, 25, "")
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(2, result.size());
        assertEquals("Juan Romero", result.get(0).getEmployeeName());
        assertEquals("Pepito Perez", result.get(1).getEmployeeName());
    }

    @Test
    public void testGetEmployeeById() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Juan Romero", 5000, 30, "")
        );
        when(employeeRepository.findById(1L)).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeeById(1L);
        assertEquals(1, result.size());
        assertEquals("Juan Romero", result.get(0).getEmployeeName());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(null);

        List<Employee> result = employeeService.getEmployeeById(1L);
        assertNull(result);
    }

    @Test
    public void testGetAnnualSalaryById() {
        Employee employee = new Employee(1L, "Juan Romero", 5000, 30, "");
        when(employeeRepository.findById(1L)).thenReturn(Arrays.asList(employee));

        Integer annualSalary = employeeService.getAnnualSalaryById(1L);
        assertEquals(60000, annualSalary);
    }

    @Test
    public void testGetAnnualSalaryById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(null);

        Integer annualSalary = employeeService.getAnnualSalaryById(1L);
        assertNull(annualSalary);
    }
}
