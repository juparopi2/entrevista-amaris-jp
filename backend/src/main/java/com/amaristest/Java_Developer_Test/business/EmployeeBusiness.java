package com.amaristest.Java_Developer_Test.business;

import com.amaristest.Java_Developer_Test.model.Employee;

public class EmployeeBusiness {

    public Integer computeAnnualSalary(Employee employee) {
        if (employee == null || employee.getEmployeeSalary() < 0) {
            throw new IllegalArgumentException("Employee or salary cannot be null or negative");
        }
        return employee.getEmployeeSalary() * 12;
    }
}