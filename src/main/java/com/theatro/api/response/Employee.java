package com.theatro.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Theatro generated Employee ID")
    @JsonProperty(value="id")

    private String employeeId;

    @JsonProperty(value = "firstname")
    @ApiModelProperty(notes="First Name of the Employee",required = true)
    private String firstName;

    @JsonProperty(value="middlename")
    @ApiModelProperty(notes="First Name of the Employee",required = false)
    private String middleName;

    @JsonProperty(value="lastname")
    @ApiModelProperty(notes="First Name of the Employee",required = true)
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
