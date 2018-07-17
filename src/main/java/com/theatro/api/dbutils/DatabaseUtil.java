package com.theatro.api.dbutils;

import com.theatro.api.config.DataSourceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by theatro on 9/6/17.
 */

@Component
public class DatabaseUtil {
    public static final Logger LOGGER= LoggerFactory.getLogger(DatabaseUtil.class);


    private static final int STORE_MANAGER_ROLE_ID = 4;

    @Autowired
    DataSourceConfiguration dataSourceConfiguration;

    private JdbcTemplate bizJdbcTemplate;
    private JdbcTemplate statJdbcTemplate;


    public Integer getUserIdByName(String userName){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Integer userId=0;
        try{
            connection=dataSourceConfiguration.bizDataSource().getConnection();
            preparedStatement=connection.prepareStatement("SELECT UserID FROM useraccounts WHERE Login=?");
            preparedStatement.setString(1,userName);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                userId=resultSet.getInt("UserID");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE FETCHING THE DB CONNECTION : {}",e);
        }finally {
            CloseResources.closeResultSet(resultSet);
            CloseResources.closePreparedStatement(preparedStatement);
            CloseResources.closeConnection(connection);
        }
        return userId;
    }


    public Integer getCompanyIdByName(String userName){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Integer companyId=0;
        try{
            connection=dataSourceConfiguration.bizDataSource().getConnection();
            preparedStatement=connection.prepareStatement("SELECT CompanyID FROM useraccounts WHERE Login=?");
            preparedStatement.setString(1,userName);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                companyId=resultSet.getInt("CompanyID");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE FETCHING THE DB CONNECTION : {}",e);
        }finally {
            CloseResources.closeResultSet(resultSet);
            CloseResources.closePreparedStatement(preparedStatement);
            CloseResources.closeConnection(connection);
        }
        return companyId;
    }

    public Integer getCompanyIdByUserId(Integer userId){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Integer companyId=0;
        try{
            connection=dataSourceConfiguration.bizDataSource().getConnection();
            preparedStatement=connection.prepareStatement("SELECT CompanyID FROM useraccounts WHERE UserID=?");
            preparedStatement.setInt(1,userId);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                companyId=resultSet.getInt("CompanyID");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE FETCHING THE DB CONNECTION : {}",e);
        }finally {
            CloseResources.closeResultSet(resultSet);
            CloseResources.closePreparedStatement(preparedStatement);
            CloseResources.closeConnection(connection);
        }
        return companyId;
    }

    public String getCompanyNameById(Integer companyId){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String companyName = null;
        try{
            connection=dataSourceConfiguration.bizDataSource().getConnection();
            preparedStatement=connection.prepareStatement("SELECT Name FROM companies WHERE CompanyID=?");
            preparedStatement.setInt(1,companyId);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                companyName=resultSet.getString("Name");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE FETCHING THE DB CONNECTION : {}",e);
        }finally {
            CloseResources.closeResultSet(resultSet);
            CloseResources.closePreparedStatement(preparedStatement);
            CloseResources.closeConnection(connection);
        }
        return companyName;
    }

    public String getStoreNameById(Integer storeId){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String storeName = null;
        try{
            connection=dataSourceConfiguration.bizDataSource().getConnection();
            preparedStatement=connection.prepareStatement("SELECT Name FROM stores WHERE StoreID=?");
            preparedStatement.setInt(1,storeId);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                storeName=resultSet.getString("Name");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE FETCHING THE DB CONNECTION : {}",e);
        }finally {
            CloseResources.closeResultSet(resultSet);
            CloseResources.closePreparedStatement(preparedStatement);
            CloseResources.closeConnection(connection);
        }
        return storeName;
    }

    public int getStoreIdbyName(String storeName){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int id = -1;
        try{
            connection=dataSourceConfiguration.bizDataSource().getConnection();
            preparedStatement=connection.prepareStatement("SELECT StoreID FROM stores WHERE name=?");
            preparedStatement.setString(1,storeName);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                id=resultSet.getInt("StoreID");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE FETCHING THE DB CONNECTION : {}",e);
        }finally {
            CloseResources.closeResultSet(resultSet);
            CloseResources.closePreparedStatement(preparedStatement);
            CloseResources.closeConnection(connection);
        }
        return id;
    }


    public int isStoreManager(int userId) {
        String SQL = "SELECT us.StoreID FROM user_store us LEFT OUTER JOIN userroles ur ON ur.UserID = us.UserID WHERE" +
                " ur.UserID = ? AND ur.RoleID = ?";
        Integer storeID = bizJdbcTemplate.query(SQL, new Object[]{userId, STORE_MANAGER_ROLE_ID}, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                int storeId = 0;
                while (resultSet.next()) {
                    storeId = resultSet.getInt("StoreID");
                }
                return storeId;
            }
        });
        return storeID;
    }
}
