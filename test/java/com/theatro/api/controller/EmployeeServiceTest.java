package com.theatro.api.controller;

import com.theatro.api.response.Employee;
import com.theatro.api.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeServiceTest.class)
public class EmployeeServiceTest {

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("JUnit");
        employee.setLastName("TestCase");
        employee.setEmployeeId("10");

        try {
            when(employeeService.getEmployee(employee.getEmployeeId(),employee.getFirstName())).thenReturn(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("JUnit",employee.getFirstName());
    }
}
