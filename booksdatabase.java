
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class booksdatabase {
    public book getBooksByISBN(Connection conn, int ISBN) throws SQLException{
        String query = "select * from books where ISBN = ? ";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, ISBN);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    book book = new book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setISBN(rs.getInt("ISBN"));
                    return book;
                }
            

            }
        }
        return null;
    }

    public book getBooksByAuthorName(Connection conn, String authorName) throws SQLException{
        String query = "select * from books where author_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, authorName);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    book book = new book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setISBN(rs.getInt("ISBN"));
                    return book;
                }
            

            }
        }
        return null;
    }


    public book getBooksByAuthorNameorISBN(Connection conn, String authorName, int ISBN) throws SQLException{
        String query = "select * from books where author_name = ? or ISBN = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, authorName);
            ps.setInt(2,ISBN);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    book book = new book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setISBN(rs.getInt("ISBN"));
                    return book;
                }
            

            }
        }
        return null;
    }

    public void saveBook(Connection conn, book book) throws SQLException{
        String query = "insert into books (ISBN, name, author_name, qty) values (?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, book.getISBN());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthorName());
            ps.setInt(4, book.getBookQty());

            int rows = ps.executeUpdate(); // number of rows inserted into the database array
            if(rows > 0){
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Failed to add book.");
            }
        }
    }
    public List<book> getAllBooks(Connection conn) throws SQLException{
        String query = "select * from books";

        List<book> books = new ArrayList<> ();
        try (PreparedStatement ps = conn.prepareStatement(query)){
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    book book = new book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("name"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setISBN(rs.getInt("ISBN"));
                    books.add(book);
                }
            }
        }
        return books;
    }
    public void updateBookQty(Connection conn, book book) throws SQLException{
        String query = "update books set qty = ? where ISBN = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, book.getBookQty());
            ps.setInt(2, book.getISBN());
           int rows =  ps.executeUpdate();
            if(rows > 0){
                System.out.println("Book quantity updated successfully");
            } else {
                System.out.println("Failed to add book quantity.");
            }
        }
    }


}
