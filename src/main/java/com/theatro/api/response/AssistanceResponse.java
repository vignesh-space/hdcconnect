package com.theatro.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AssistanceResponse {

    @JsonProperty(value = "firstname")
    @ApiModelProperty(notes="First Name of the Employee",required = true)
    private String firstName;

    @JsonProperty(value="middlename")
    @ApiModelProperty(notes="First Name of the Employee",required = false)
    private String middleName;

    @JsonProperty(value="lastname")
    @ApiModelProperty(notes="First Name of the Employee",required = true)
    private String lastName;

    @JsonProperty(value="location")
    @ApiModelProperty(notes="Location Where Employee will greet the customer",required = true)
    public String location;

    @JsonProperty(value="imageUrl")
    @ApiModelProperty(notes="Image of the Employee who will Assist the customer",required = true)
    public String employeeImageUrl;


    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployeeImageUrl() {
        return this.employeeImageUrl;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmployeeImageUrl(String employeeImageUrl) {

        this.employeeImageUrl = employeeImageUrl;
    }
}
