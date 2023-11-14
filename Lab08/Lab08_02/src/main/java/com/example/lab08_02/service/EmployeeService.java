package com.example.lab08_02.service;

import com.example.lab08_02.model.Employee;
import com.example.lab08_02.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> selectAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee selectEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        if (employee != null)
            return employeeRepository.save(employee);
        return null;
    }

    @Override
    public boolean deleteEmployeeById(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employee1 = selectEmployeeById(employee.getId());
        if (employee != null && employee1 != null) {
            employee1.setName(employee.getName());
            employee1.setEmail(employee.getEmail());
            employee1.setAddress(employee.getAddress());
            employee1.setPhone(employee.getPhone());
            return employeeRepository.save(employee1);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteEmployees(List<Integer> ids) {
        employeeRepository.deleteByIdIn(ids);
    }
}
