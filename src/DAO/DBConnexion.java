package DAO;
import java.sql.*;

public class DBConnexion {

        public static final String url = "jdbc:mysql://localhost:3306/gestion";
        public static final String user = "root";
        public static final String password = "";
        public static Connection conn = null;

        public static Connection getConnexion() throws ClassNotFoundException {
            if (conn != null) {
                return conn;
            }
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("correct");
            } catch (SQLException e) {
                throw new RuntimeException("Error de connexion", e);
            }

            return conn;
        }
    }

