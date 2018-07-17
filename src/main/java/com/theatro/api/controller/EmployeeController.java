package com.theatro.api.controller;

import com.theatro.api.ExceptionHandler.NotFoundException;
import com.theatro.api.response.Employee;
import com.theatro.api.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value="Employee API", description="API for In store Employee")
public class EmployeeController {

    public static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get All employees in a store", response = Employee.class,responseContainer = "List")
    @GetMapping(value = "/employees/{store}",produces = "application/json")
    public Iterable<Employee> fetchEmployeeList(@PathVariable("store")String storename){
        return employeeService.fetchEmployeeList( storename);
    }


    @ApiOperation(value = "Get the Details of an employee in a store", response = Employee.class)
    @GetMapping(value = "employees/{store}/employeename/{employeeName}",produces = "application/json")
    public Employee getEmployee(@PathVariable("store") @Valid String storeName , @PathVariable("employeeName") @Valid String fullName )
    throws Exception {
        LOGGER.info("Fetching details of employee < {} > for store < {} > ",fullName,storeName);
        Employee employee = employeeService.getEmployee(fullName,storeName);
        if(employee.getEmployeeId()==null){
            throw new NotFoundException("name-"+fullName);
        }
        return employee;
    }

}
