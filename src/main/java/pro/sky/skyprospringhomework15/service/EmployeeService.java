package pro.sky.skyprospringhomework15.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringhomework15.exception.EmployeeAlreadyAddedException;
import pro.sky.skyprospringhomework15.exception.EmployeeNotFoundException;
import pro.sky.skyprospringhomework15.exception.EmployeeStorageIsFullException;
import pro.sky.skyprospringhomework15.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int SIZE = 5;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee addEmployee(String firstName,
                                String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < SIZE) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String firstName,
                                   String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains((employee))) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }


    public Employee findEmployee(String firstName,
                                 String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains((employee))) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }
}

