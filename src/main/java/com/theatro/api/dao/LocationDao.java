package com.theatro.api.dao;

import com.theatro.api.dbutils.DatabaseUtil;
import com.theatro.api.response.Group;
import com.theatro.api.response.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationDao {

    @Autowired
    DatabaseUtil databaseUtil;

    private JdbcTemplate bizJdbcTemplate;

    @Autowired
    public void setBizJdbcTemplate(@Qualifier("bizDatasource") DataSource bizDatasource) {
        this.bizJdbcTemplate = new JdbcTemplate(bizDatasource);
    }

    public List<Location> getLocations(String storeName){

        List<Location> locationList = new ArrayList<>();
        int storeId = databaseUtil.getStoreIdbyName(storeName);
        String SQL = "select location_name from locations where store_id=?";

        bizJdbcTemplate.query(SQL,new Object[]{storeId},new ResultSetExtractor<List<Location>>() {
            @Override
            public List<Location> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

                while (resultSet.next()) {
                    Location location = new Location();
                    location.setName(resultSet.getString("location_name"));
                    locationList.add(location);
                }
                return locationList;
            }
        });

        return locationList;
    }

    public Location getLocationDetails(String locationName, String storeName){
        Location location = new Location();
        location.setName(locationName);

        return location;
    }
}
