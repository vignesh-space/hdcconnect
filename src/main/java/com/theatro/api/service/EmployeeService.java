package com.theatro.api.service;

import com.theatro.api.dao.EmployeeDao;
import com.theatro.api.response.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> fetchEmployeeList(String storename ){
        return employeeDao.getEmployeeList(storename);
    }

    public Employee getEmployee(String fullname, String storename) throws Exception{
        return employeeDao.getEmployeeDetails(fullname,storename);
    }
}
