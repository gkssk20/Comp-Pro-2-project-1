import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class studentdatabase {
    public boolean getStudentByRegNum(Connection conn, String regNum) throws SQLException{
        String query = "select * from students where reg_num = ? ";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, regNum);

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();

            }
        }
      }



    public void saveStudent(Connection conn, String studentName, String regNum) throws SQLException{
        String query = "insert into students (std_name, reg_num) values (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, studentName);
            ps.setString(2,regNum);

            int rows = ps.executeUpdate(); // number of rows inserted into the database array
            if(rows > 0){
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Failed to add student.");
            }
        }
    }
    public List<book> getAllStudents(Connection conn) throws SQLException{
        String query = "select * from students";

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

