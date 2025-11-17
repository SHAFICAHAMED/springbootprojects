import java.sql.*;
import java.util.Scanner;

public class JdbcClass {
    static final String URL = "jdbc:mysql://localhost:3306/innocent";
    static final String USER = "root"; // Replace with your MySQL username
    static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Scanner sc=new Scanner(System.in);

        String query = "INSERT INTO black_and_green (name, email) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);


        stmt.setString(1, sc.next());
        stmt.setString(2, sc.next());

        stmt.executeUpdate();
        System.out.println(" user inserted.");

        //read
        String readquery = "SELECT * FROM black_and_green";
        Statement rstmt = conn.createStatement();
        ResultSet rs = rstmt.executeQuery(readquery);

            System.out.println("\nUser Records:");
            while (rs.next()) {
                System.out.println(" Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }

            //update
        String upquery = "UPDATE black_and_green SET name=?, email=? where name=?";

        PreparedStatement upstmt = conn.prepareStatement(upquery);

        String nam=sc.next();
        upstmt.setString(1, nam);
        upstmt.setString(2, sc.next());
        upstmt.setString(3, nam);
        upstmt.executeUpdate();
        System.out.println(" user updated.");

        //delete
        String denam=sc.next();
        String query = "DELETE FROM black_and_green WHERE name=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, denam);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user deleted.");
        }

    }
}
