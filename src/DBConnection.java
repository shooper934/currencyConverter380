import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection extends APIconnection {
    public static Connection conn;

    public static void connect() {
        // Connect to the database
        String url = "jdbc:mysql://localhost/";
        String username = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");

            // Check if the schema exists
            Statement statement = conn.createStatement();
            String schemaQuery = "SHOW DATABASES LIKE 'conversion_history'";
            ResultSet schemaResultSet = statement.executeQuery(schemaQuery);

            if (!schemaResultSet.next()) {
                // Schema doesn't exist, create it
                String createSchemaQuery = "CREATE DATABASE conversion_history";
                statement.executeUpdate(createSchemaQuery);
                System.out.println("Schema created successfully.");
            } else {
                System.out.println("Schema already exists.");
            }

            schemaResultSet.close();

            // Use the schema
            String useSchemaQuery = "USE conversion_history";
            statement.executeUpdate(useSchemaQuery);

            // Check if the table exists
            String tableQuery = "SHOW TABLES LIKE 'conversion_history'";
            ResultSet tableResultSet = statement.executeQuery(tableQuery);

            if (!tableResultSet.next()) {
                // Table doesn't exist, create it
                String createTableQuery = "CREATE TABLE conversion_history ("
                        + "con_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        + "baseCurr VARCHAR(45) NOT NULL,"
                        + "targetCurr VARCHAR(45) NOT NULL,"
                        + "conAmount VARCHAR(45) NOT NULL,"
                        + "date VARCHAR(45) NOT NULL"
                        + ")";
                statement.executeUpdate(createTableQuery);
                System.out.println("Table created successfully.");
            } else {
                System.out.println("Table already exists.");
            }

            tableResultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
