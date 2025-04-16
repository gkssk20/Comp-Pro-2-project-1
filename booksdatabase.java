
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class booksdatabase {
    public book getBooksByISBN(Connection conn, int ISBN) throws SQLException{
        String query = "select * from books where sr_no = ? ";
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
                }
            

            }
        }
        return null;
    }



    public book getBooksByAuthorName(Connection conn, String authorName) throws SQLException{
        String query = "select * from books where author_name = ? ";
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
                }
            

            }
        }
        return null;
    }
}
