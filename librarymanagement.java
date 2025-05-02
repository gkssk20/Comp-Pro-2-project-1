                                                                                                                                      
import java.sql.*;

public class librarymanagement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        System.out.println("Welcome to Messiah University Team 3's Library");
        System.out.println("It is a small library so please give some grace :)");
        System.out.println("Please1 login first before to access the Menu!");
        login login = new login();
        login.actionLogin();
     

        
        
    }
}
