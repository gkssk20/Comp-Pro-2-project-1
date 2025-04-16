import java.sql.*;

public class database {
        public static Connection conn;
        private static Connection createConn() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_", "root","ImissyouAbrahamJung!");
            System.out.println("Database connection created successfully. ");
            return conn;
        }
        public static Connection getConnection() throws ClassNotFoundException, SQLException{
            if(conn == null){
                return createConn();
            }
            return conn;
        }
}
