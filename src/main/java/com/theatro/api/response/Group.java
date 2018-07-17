package com.theatro.api.response;


import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Theatro generated ID")
    private String id;

    @ApiModelProperty(notes=" Name of the Group",required = true)
    private String name;

    private List<Employee> employeeList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
