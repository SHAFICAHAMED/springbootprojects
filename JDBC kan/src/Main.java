import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/kaniDB";
    static final String USER = "root"; // Replace with your MySQL username
    static final String PASSWORD = "root";

    
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        Scanner sc = new Scanner(System.in);

        //add
      /*  String query = "INSERT INTO student (id,name,age) VALUES (?, ?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);


        stmt.setInt(1, sc.nextInt());
        stmt.setString(2, sc.next());
        stmt.setInt(3, sc.nextInt());

        stmt.executeUpdate();
        System.out.println(" user inserted.");*/

        //read
        /*String readquery = "SELECT * FROM student";
        Statement rstmt = conn.createStatement();
        ResultSet rs = rstmt.executeQuery(readquery);

        System.out.println("\nUser Records:");
        while (rs.next()) {
            System.out.println(" Id: " + rs.getInt("id") + ", Name: " + rs.getString("name")+" "+rs.getInt("age"));
        }*/

//        String upquery = "UPDATE student SET name=? where id=?";
//
//        PreparedStatement upstmt = conn.prepareStatement(upquery);
//
//        int id=sc.nextInt();
//        String nam=sc.next();
//        upstmt.setInt(2,id);
//        upstmt.setString(1, nam);
//        upstmt.executeUpdate();
//        System.out.println(" user updated.");

        String denam = sc.next();
        String query = "DELETE FROM student WHERE name=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, denam);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user deleted.");
        } catch (Exception e) {

        }
    }
}