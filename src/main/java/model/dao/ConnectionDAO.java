package model.dao;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class ConnectionDAO {

    public static JdbcRowSet getRowSetConnection(){
        //Free MysqlHost
        String url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10356689?useTimezone=true&serverTimezone=UTC";
        String user = "sql10356689";
        String password = "******";

        try {
            JdbcRowSet jdbcrowset = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcrowset.setUrl(url);
            jdbcrowset.setUsername(user);
            jdbcrowset.setPassword(password);
            return jdbcrowset;
        } catch (SQLException throwables) {
            throwables.printStackTrace();            
        }
        return null;
    }

    public static void close(JdbcRowSet jrs){
        try {
            if(jrs != null) {
                jrs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
