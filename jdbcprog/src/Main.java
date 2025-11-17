import java.sql.*;
import java.util.Scanner;

public class Main {
    // Database connection details
    static final String URL = "jdbc:mysql://localhost:3306/TestDB";
    static final String USER = "root"; // Replace with your MySQL username
    static final String PASSWORD = "root"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            int choice;
            do {
                System.out.println("\n--- JDBC CRUD MENU ---");
                System.out.println("1. Insert User");
                System.out.println("2. Read Users");
                System.out.println("3. Update U ser");
                System.out.println("4. Delete User");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        insertUser(conn, scanner);
                        break;
                    case 2:
                        readUsers(conn);
                        break;
                    case 3:
                        updateUser(conn, scanner);
                        break;
                    case 4:
                        deleteUser(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to Insert a User
    private static void insertUser(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();

        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user inserted.");
        }
    }

    // Method to Read Users
    private static void readUsers(Connection conn) throws SQLException {
        String query = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nUser Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }
        }
    }

    // Method to Update a User
    private static void updateUser(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new email: ");
        String email = scanner.next();

        String query = "UPDATE users SET name=?, email=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user updated.");
        }
    }

    // Method to Delete a User
    private static void deleteUser(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows + " user deleted.");
        }
    }
}