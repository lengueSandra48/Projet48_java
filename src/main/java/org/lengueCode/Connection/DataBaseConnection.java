package org.lengueCode.Connection;
import java.sql.*;


public class DataBaseConnection {

    static String URL = "jdbc:postgresql://localhost:5432/bibliotheque" ;
    static String user = "postgres";
    static String password = "sandra" ;
    static Connection connection;

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL,user,password);
            Class.forName("org.postgresql.Driver");

            if (connection != null){
                System.out.println("Connection etablie avec la base de donnee...");
            }else {
                System.out.println("Connection refuser");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
