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
            System.out.println("Login Success! You logged in as a " + userType + ". PLease selection from below options. ");
            if(userType.equals("admin")){
                displayAdminMenu(conn);

            } else {
                displayStudentMenu(conn);
            }
        }

    }

    public void displayAdminMenu(Connection conn) throws SQLException{
        int choice;
        bookPage bookPage = new bookPage();
        studentPage studentPage = new studentPage();

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

            System.out.println("Please enter your choice: ");
            choice = sc.nextInt();
            switch (choice){
                case 1: 
                    searchBook(conn);
                    break;
                case 2:
                    bookPage.addBook(conn);
                    break;
                case 3:
                    bookPage.updateBookQty(conn);
                    break;
                case 4: 
                    bookPage.getAllBooks(conn);
                    break;
                case 5:
                    studentPage.addStudent(conn);
                    break;
                case 6:
                    studentPage.getAllStudents(conn);
                    break;
                case 7:
                    System.out.println("Thank you for using library managment system!");
                    break;
                    default:
                    System.out.println("Please select a valid option. ");
            }

        } while (choice != 7);
    }
public void displayStudentMenu(Connection conn) throws SQLException{
    int choice;
    bookPage bookPage = new bookPage();
    studentPage studentPage = new studentPage();

    do{
        System.out.println("//////////////////////////////////////");
        System.out.println("1. Search a book. ");
        System.out.println("2. Check out Book");
        System.out.println("3. Return a Book");
        System.out.println("4. Exit from application. ");
        System.out.println("//////////////////////////////////////");

        System.out.println("Please enter your choice: ");
        choice = sc.nextInt();
        switch (choice){
            case 1: 
                searchBook(conn);
                break;
            case 2:
                bookPage.checkOutBook(conn);
                break;
            case 3:
                bookPage.returnBook(conn);
                break;
            case 4: 
               System.out.println("Thank you for using our Library!");
               System.exit(0);
            default:
            System.out.println("Please select a valid option. ");
        }

    } while (choice != 4);
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
