package com.example.lab08_02.service;

import com.example.lab08_02.model.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> selectAllEmployee();
    public Employee selectEmployeeById(Integer id);
    public Employee insertEmployee(Employee employee);
    public boolean deleteEmployeeById(Integer id);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployees(List<Integer> selectedEmployees);
}
