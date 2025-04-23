import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class studentPage {
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


    public void addStudent(Connection conn) throws SQLException{
       
        System.out.println("Enter the Student: ");
        String studentName = sc.nextLine();

        System.out.println("Enter the Registration Number: ");
        String regNum = sc.nextLine();

        studentdatabase database  = new studentdatabase();
        boolean isStdExist = database.getStudentByRegNum(conn, regNum);

        if(isStdExist){
            System.out.println("Student details exist into our system.");
            return;
        }

      
        
        database.saveStudent(conn, studentName, regNum);

    }
    public void getAllBooks(Connection conn) throws SQLException{
        booksdatabase database = new booksdatabase();
        List <book> books = database.getAllBooks(conn);
        System.out.println("+------------+-------------------------------------+--------------------+------------");
        System.out.println("| Book ISBN  | Name                                | Author Name        | Quantity  |");
        System.out.println("+------------+-------------------------------------+--------------------+------------"); 
        for(book book: books){
           
           // System.out.println(book.getISBN() + "        " + book.getBookName() + "       " + book.getAuthorName());
            System.out.printf("| %-10s | %-18s | %-16s | %-13s | \n", book.getISBN(), book.getBookName(), book.getAuthorName(), book.getBookQty());
            System.out.println("+------------+------------------------4-------+--------------------+------------+");
        }
    }



  
}
