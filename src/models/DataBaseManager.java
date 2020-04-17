package models;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public abstract class DataBaseManager {

    private static Connection connection = null ;
    private static final String url  ="jdbc:sqlite:medical-appointment.db" ;

    static final String  clientTable = "client";
    static final String  appointmentTable = "appointment";



     private Connection getConnection(){
        if( connection == null){
            try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
                System.out.println("Data base connected");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return connection ;
     }

     /**
      * I make this method because of duplication of the code !!
     * */
     ResultSet query(String sql) throws SQLException {
          ResultSet resultSet = null ;
          Statement stmt  = this.getConnection().createStatement();
          System.out.println(sql);
          resultSet = stmt.executeQuery(sql) ;
            // stmt.executeUpdate();
          System.out.println(stmt.getFetchSize());

         return resultSet;
     }

     void insert(String sql) throws SQLException  {
         Statement stmt  = this.getConnection().createStatement();
         stmt.executeUpdate(sql);
     }



}
