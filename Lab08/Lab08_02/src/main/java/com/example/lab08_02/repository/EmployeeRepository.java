package com.example.lab08_02.repository;

import com.example.lab08_02.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    void deleteByIdIn(List<Integer> ids);
}
