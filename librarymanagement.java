
import java.sql.*;

public class librarymanagement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        System.out.println("Welcom to Messiah University Team 3's Library");
        System.out.println("It is a small library so please give some grace :)");
        System.out.println("Please login first before to access the Menu!");
        login login = new login();
        login.actionLogin();
     

        
    }
}
