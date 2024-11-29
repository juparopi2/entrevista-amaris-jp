package com.amaristest.Java_Developer_Test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaristest.Java_Developer_Test.business.EmployeeBusiness;
import com.amaristest.Java_Developer_Test.model.Employee;
import com.amaristest.Java_Developer_Test.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final EmployeeBusiness employeeBusiness = new EmployeeBusiness();

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Integer getAnnualSalaryById(Long id) {
        List<Employee> employee = employeeRepository.findById(id);
        if (employee == null || employee.isEmpty()) {
            return null;
        }
        return employeeBusiness.computeAnnualSalary(employee.get(0));
    }
}
