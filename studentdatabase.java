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

    public int getStudentByRegNum2(Connection conn, String regNum) throws SQLException{
        String query = "select * from students where reg_num = ? ";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, regNum);

            try(ResultSet rs = ps.executeQuery()){
               if(rs.next()){
                return rs.getInt(1);
               }
            }
        }
        return 0;
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
    
    public void getAllStudents(Connection conn) throws SQLException{
        String query = "select * from students";

        System.out.println("+---------------+-----------------+--------------------+");
        System.out.println("|Student Number |Student Name     | Registration Number|");
        System.out.println("+---------------+-----------------+--------------------+"); 

        List<book> books = new ArrayList<> ();
        
        try (PreparedStatement ps = conn.prepareStatement(query)){
            try(ResultSet rs = ps.executeQuery()){
                  
                while (rs.next()){ 
                    System.out.printf("| %-10s | %-16s | %-16s | \n", rs.getInt(1), rs.getString(2), rs.getString(3));                       
                    System.out.println("+---------------+-----------------+--------------------+");
            
                }
            }
        }
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

    
    public void saveBookingDetails(Connection conn, int stdId, int qty, int bookId) throws SQLException{
        String query = "insert into booking_deatils (std_id, book_id, qty) values (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, stdId);
            ps.setInt(2, bookId);
            ps.setInt(3, qty);

            int rows = ps.executeUpdate(); // number of rows inserted into the database array
            if(rows > 0){
                System.out.println("Booking details added successfully.");
            } else {
                System.out.println("Failed to add student.");
            }
        }
    }

    public List<bookingdetails> getBookDetailsID(Connection conn, int stdId) throws SQLException{
        String query = "SELECT * FROM booking_deatils bd "
            + "INNER JOIN books b on b.id = bd.book_id "
            +" where bd.std_id = ?";
        List<bookingdetails> bookingdetails = new ArrayList<>();
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, stdId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bookingdetails bookingDetails = new bookingdetails();
                bookingDetails.setAuthorName(resultSet.getString("author_name"));
                bookingDetails.setBookId(resultSet.getInt("book_id"));
                bookingDetails.setBookName(resultSet.getString("name"));
                bookingDetails.setQty(resultSet.getInt("qty"));
                bookingDetails.setStdId(resultSet.getInt("std_id"));
                bookingDetails.setISBN(resultSet.getInt("ISBN"));
                bookingDetails.setId(resultSet.getInt("id"));
            }
        }
        return bookingdetails;
    }
 
    public void deleteBookingDetails(Connection conn, int id) throws SQLException{
        String query = "delete from booking_deatils where id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
           
            

        }
    }

}

