import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

        String authorName = sc.nextLine();
        booksdatabase database = new booksdatabase();
        book book = database.getBooksByAuthorName(conn, authorName);
        if (book != null){
            System.out.println("/// Book Details ///");
            System.out.println("ISBN: " + book.getISBN()+ "|| Book Name: " + book.getBookName()+ "|| Author Name: " + book.getAuthorName());

        } else{
            System.out.println("No books for Author Name: "+ authorName + "found. ");

        }

    }
    public void addBook(Connection conn) throws SQLException{
        System.out.println("Enter the ISBN of the Book: ");
        int ISBN = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Enter the Book Name: ");
        String bookName = sc.nextLine();

        System.out.println("Enter the Author Name: ");
        String authorName = sc.nextLine();

        System.out.println("Enter the quantity of books");
        int qty = sc.nextInt();

        booksdatabase database = new booksdatabase();
        book book = database.getBooksByAuthorNameorISBN(conn, bookName, ISBN);

        if(book != null){
            System.out.println("Book details exist into our system. Please try anther book.");
            return;
        }

        book input = new book();
        input.setAuthorName(authorName);
        input.setBookName(bookName);
        input.setBookQty (qty);
        input.setISBN(ISBN);
        database.saveBook(conn, input);

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



    public void updateBookQty(Connection conn) throws SQLException{
        System.out.println("Enter the ISBN of the Book: ");
        int ISBN = sc.nextInt();

        booksdatabase database = new booksdatabase();
        book book = database.getBooksByISBN(conn, ISBN);

        if(book == null){
            System.out.println("Book not available.");
            return;
        }
        System.out.println("Enter the number of books to be added: ");
        int qty = sc.nextInt();
        book input = new book();
        input.setBookQty(book.getBookQty() + qty);
        input.setISBN(ISBN);
        database.updateBookQty(conn, input);

    }

    public void checkOutBook(Connection conn) throws SQLException{
       
       studentdatabase database = new studentdatabase();
        System.out.println("Enter registration number: ");
        String regNum = sc.nextLine();

        boolean isExist = database.getStudentByRegNum(conn, regNum);

        if(!isExist){
            System.out.println("Student is not Registered. Get Registered first.");
            return;
        }
        getAllBooks(conn);
        System.out.println("Enter ISBN of Book to be Checked out.");
        int ISBN = sc.nextInt();
        sc.nextLine();

        booksdatabase booksdatabase = new booksdatabase();
        book book =  booksdatabase.getBooksByISBN(conn, ISBN);
        

        if(book == null){
            System.out.println("Book is not available.");
            return;
        }

        book.setBookQty(book.getBookQty() -1);

        int id = database.getStudentByRegNum2(conn, regNum);

        database.saveBookingDetails(conn, id, book.getId(), 1);
        booksdatabase.updateBookQty(conn, book);



    }


    public void returnBook(Connection conn) throws SQLException{
        studentdatabase database = new studentdatabase();
        System.out.println("Enter registration number: ");
        String regNum = sc.nextLine();
        boolean isExist = database.getStudentByRegNum(conn, regNum);
        if(!isExist){
            System.out.println("Student is not registered. Get registered first");
            return;
        }

        int id = database.getStudentByRegNum2(conn, regNum);
        List<bookingdetails> bookingdetails = database.getBookDetailsID(conn, id);
        bookingdetails.stream().forEach(b->System.out.println(b.ISBN + "\t\t\t" +b.bookName + "\t\t\t"+b.authorName));
        System.out.println("Enter the ISBN of book to be returned");
        int ISBN = sc.nextInt();
        sc.nextLine();
        bookingdetails filterDetails = bookingdetails.stream().filter(b->b.getISBN()==ISBN).findAny().orElse(null);

        booksdatabase booksdatabase = new booksdatabase();
        book book = booksdatabase.getBooksByISBN(conn, ISBN);
        book.setBookQty(book.getBookQty()+1);

        booksdatabase.updateBookQty(conn, book);
        database.deleteBookingDetails(conn, filterDetails.getId());        

    }

}
