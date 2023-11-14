package com.example.lab08_02.controller;

import com.example.lab08_02.model.Employee;
import com.example.lab08_02.service.IEmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(value = "/employees")
    public ModelAndView employees(HttpSession session) {
        ModelAndView mv = new ModelAndView("index");
        String status = String.valueOf(session.getAttribute("status"));
        String message = String.valueOf(session.getAttribute("message"));
        if (status != null && message != null) {
            mv.addObject("status", status);
            mv.addObject("message", message);
            session.removeAttribute("status");
            session.removeAttribute("message");
        }

        List<Employee> employees = employeeService.selectAllEmployee();
        if (!employees.isEmpty())
            mv.addObject("employees", employees);
        return mv;
    }

    @GetMapping(value = "/employees/add")
    public ModelAndView showAddInterface() {
        return new ModelAndView("add");
    }

    @GetMapping(value = "/employees/edit/{id}")
    public ModelAndView showEditingInterface(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("edit");
        Employee employee = employeeService.selectEmployeeById(id);
        mv.addObject("employee", employee);

        return mv;
    }

    @PostMapping(value = "/employees/edit/{id}")
    public ModelAndView handleEdit(HttpSession session,  @ModelAttribute("employee") Employee employee) {
        ModelAndView mv = new ModelAndView("redirect:/employees");
        if (employeeService.updateEmployee(employee) != null) {
            session.setAttribute("status", "success");
            session.setAttribute("message", "Employee has been updated successfully");
        } else {
            session.setAttribute("status", "danger");
            session.setAttribute("message", "Update employee failed");
        }
        return mv;
    }

    @PostMapping(value = "/employees/add")
    public ModelAndView handleAdd(HttpSession session, @ModelAttribute("employee") Employee employee) {
        ModelAndView mv = new ModelAndView("redirect:/employees");
        if (employeeService.insertEmployee(employee) != null) {
            session.setAttribute("status", "success");
            session.setAttribute("message", "Insert Employee successfully");
        } else {
            session.setAttribute("status", "danger");
            session.setAttribute("message", "Insert employee failed");
        }
        return mv;
    }

    @PostMapping(value = "/employees/delete/{id}")
    @ResponseBody
    public ResponseEntity<Boolean> handleDelete(HttpSession session, @PathVariable(value = "id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/employees");
        boolean isDelete = employeeService.deleteEmployeeById(id);
        if (isDelete) {
            session.setAttribute("status", "success");
            session.setAttribute("message", "Delete Employee successfully");
        } else {
            session.setAttribute("status", "danger");
            session.setAttribute("message", "Delete employee failed");
        }
        return ResponseEntity.ok(isDelete);
    }

    @PostMapping(value = "/delete-selected-employees")
    @ResponseBody
    public ResponseEntity<Boolean> deleteSelectedEmployees(@RequestBody List<Integer> ids) {
        employeeService.deleteEmployees(ids);
        return ResponseEntity.ok(true);
    }

}
