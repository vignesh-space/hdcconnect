package com.theatro.api.controller;

import com.theatro.api.response.Employee;
import com.theatro.api.response.Location;
import com.theatro.api.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value="Location API", description="API for In store Locations")
public class LocationController {


    public static final Logger LOGGER= LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "Get All Locations in a store", response = Employee.class,responseContainer = "List")
    @RequestMapping(value = "location",method = RequestMethod.GET,produces = "application/json")
    public Iterable<Location> fetchLocations(@RequestParam("store")String storename){
        return locationService.fetchLocations( storename);
    }


    @ApiOperation(value = "Get the Details of an employee in a store", response = Employee.class)
    @RequestMapping(value = "location/{locationName}",method = RequestMethod.GET,produces = "application/json")
    public Location getEmployee(@PathVariable("locationName") String locationName , @RequestParam("store") String storename ){
        LOGGER.info("Fetching details of location < {} > for store < {} > ",locationName,storename);
        Location location = locationService.getEmployee(locationName,storename);
        return location;
    }

}
