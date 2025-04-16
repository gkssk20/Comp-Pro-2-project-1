import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class login {
    Scanner sc = new Scanner(System.in);
    public void actionLogin() throws ClassNotFoundException, SQLException {
        System.out.println("Please provide username: ");
        String userName = sc.nextLine();
        System.out.println("Please provide password: ");
        String password = sc.nextLine();

        try (Connection conn = database.getConnection()){
            logindatabase logindatabase = new logindatabase();
            String userType = logindatabase.actionLogin(conn, userName, password);
            if(userType == null){
                System.out.println("Invalid User");
                return;
            }
            System.out.println("Login Success! You logged in as a " + userType + " . PLease selection from brlow options. ");
            if(userType.equals("admin")){
                displayAdminMenu(conn);

            } else {
                //going to print student related menu

            }
        }

    }

    public void displayAdminMenu(Connection conn) throws SQLException{
        int choice;

        do{
            System.out.println("//////////////////////////////////////");
            System.out.println("1. Search a book. ");
            System.out.println("2. Add a new book. ");
            System.out.println("3. Upgrade amount of a book");
            System.out.println("4. Show all books. ");
            System.out.println("5. Register Student. ");
            System.out.println("6. Show all registered students. ");
            System.out.println("7. Exit from application. ");
            System.out.println("//////////////////////////////////////");

            System.out.println("Please enter your hoice: ");
            choice = sc.nextInt();
            switch (choice){
                case 1: 
                    searchBook(conn);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4: 
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Thank you for using library managment system!");
                    break;
                    default:
                    System.out.println("Please select a valid option. ");
            }

        } while (choice != 7);
    }

    private void searchBook(Connection conn) throws SQLException{
        bookPage bookPage = new bookPage();
        System.out.println("1. Search with book ISBN: ");
        System.out.println("2. Search with Author's name.");
        System.out.println("Please enter your choice:");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                bookPage.searchByISBN(conn);
                break;
            case 2:
                bookPage.searchByAuthorName(conn);
                
        }
    }


}
