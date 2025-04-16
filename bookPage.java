import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class bookPage {
    Scanner sc = new Scanner(System.in);
    public void searchByISBN(Connection conn) throws SQLException{
        System.out.println("Enter ISBN of Book:");
        int ISBN = sc.nextInt();
        booksdatabase database = new booksdatabase();
        book book = database.getBooksByISBN(conn, ISBN);
        if (book != null){
            System.out.println("/// Book Details ///");
            System.out.println("ISBN: " + book.getISBN()+ " Book Name: " + book.getBookName()+ " Author Name: " + book.getAuthorName());

        } else{
            System.out.println("No books for ISBN: "+ ISBN + "found. ");

        }


    }

    public void searchByAuthorName(Connection conn) throws SQLException{
        System.out.println("Enter Author Name:");
        sc.nextLine();
        String authorName = sc.nextLine();
        booksdatabase database = new booksdatabase();
        book book = database.getBooksByAuthorName(conn, authorName);
        if (book != null){
            System.out.println("/// Book Details ///");
            System.out.println("ISBN: " + book.getISBN()+ " Book Name: " + book.getBookName()+ " Author Name: " + book.getAuthorName());

        } else{
            System.out.println("No books for Author Name: "+ authorName + "found. ");

        }


    }
}
