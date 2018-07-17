package com.theatro.api.dao;

import com.theatro.api.dbutils.DatabaseUtil;
import com.theatro.api.response.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    @Autowired
    DatabaseUtil databaseUtil;

    private JdbcTemplate bizJdbcTemplate;

    @Autowired
    public void setBizJdbcTemplate(@Qualifier("bizDatasource") DataSource bizDatasource) {
        this.bizJdbcTemplate = new JdbcTemplate(bizDatasource);
    }


    public Employee getEmployeeDetails(String fullName,String storename){
        int storeId = databaseUtil.getStoreIdbyName(storename);
        LOGGER.info("Store ID for store name <{}> is <{}>",storename,storeId);
        Employee employee = new Employee();
        if(storeId < 0 ) {
            LOGGER.error("Invalid store name received <{}>",storename);
        }
        else
        {
            String SQL =  "SELECT e.firstname, e.lastname ,e.employeeid from employees e where e.tagoutname = ? and e.employeeid in " +
                    "( select employeeid from employeestores where storeid = ?)";
            final Employee query = bizJdbcTemplate.query(SQL, new Object[]{fullName, storeId}, new ResultSetExtractor<Employee>() {
                @Override
                public Employee extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    while (resultSet.next()) {
                        employee.setFirstName(resultSet.getString("firstname"));
                        employee.setLastName(resultSet.getString("lastname"));
                        employee.setEmployeeId(resultSet.getString("employeeid"));
                        LOGGER.debug("Employee details are firstname < {} > lastname < {} >", employee.getFirstName(), employee.getLastName());

                    }
                    return employee;
                }
            });
        }
        if(employee.getEmployeeId() == null) {
            LOGGER.error("employee not found {}",fullName);
        }
        return employee;
    }


    public List<Employee> getEmployeeList(String storename){
        List<Employee> employeeList = new ArrayList<>();
        int storeId = databaseUtil.getStoreIdbyName(storename);
        LOGGER.info("Store ID for store name <{}> is <{}>",storename,storeId);

        if(storeId > 0){
            String SQL = "SELECT firstname,lastname,tagoutname from employees where employeeid in ( select employeeid from " +
                    "employeestores where storeid = ?)";
            bizJdbcTemplate.query(SQL,new Object[]{storeId},new ResultSetExtractor<List<Employee>>() {
                @Override
                public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

                    while (resultSet.next()) {
                        Employee employee = new Employee();
                        employee.setFirstName(resultSet.getString("firstname"));
                        employee.setLastName(resultSet.getString("lastname"));
                        employeeList.add(employee);
                    }
                    return employeeList;
                }
            });

        }  else{
            LOGGER.error("Invalid store name < {} > ",storename);
        }
        return employeeList;

    }
}
