package com.theatro.api.dbutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by theatro on 9/6/17.
 */
public class CloseResources {
    public static final Logger LOGGER= LoggerFactory.getLogger(CloseResources.class);

    private CloseResources() {
        //OBJECT CREATION IS NOT REQUIRED :D
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement){
        try {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE CLOSING PREPARED STMT : {}",e);
        }
    }

    public static void closeStatement(Statement statement){
        try {
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE CLOSING STMT : {}",e);
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        try {
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE CLOSING RESULTSET : {}",e);
        }
    }

    public static void closeConnection(Connection connection){
        try {
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE CLOSING CONNECTION : {}",e);
        }
    }

    public static void commitConnection(Connection connection){
        try {
            if (connection!=null){
                connection.commit();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE COMMITTING CONNECTION : {}",e);
        }
    }

    public static void rollBackConnection(Connection connection){
        try {
            if (connection!=null){
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL EXCEPTION WHILE ROLLING BACK CONNECTION : {}",e);
        }
    }
}
