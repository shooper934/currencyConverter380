import java.sql.*;

public class DBConnection extends APIconnection {
    public static Connection conn;
    
    //instance of the InitialScreen
    private static InitialScreen in = new InitialScreen();
    private static ConverterGUI cg = new ConverterGUI();

    public static void connect() {
        // Connect to the database
        String url = "jdbc:mysql://localhost/";
        String username = in.getUsername();
        String password = in.getPassword();

        try {
            cg.saveValid = true;
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
                //System.out.println("Table created successfully.");
            } else {
                //System.out.println("Table already exists.");
            }

            // Check if historical table exists
            String historicalQuery = "SHOW TABLES LIKE 'historical_data'";
            ResultSet historicalResultSet = statement.executeQuery(historicalQuery);

            if(!historicalResultSet.next()) {
                // Historical data does not exist
                String createHistoricalQuery = "CREATE TABLE historical_data ("
                        + "his_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                        + "conAmount VARCHAR(45) NOT NULL,"
                        + "date VARCHAR(45) NOT NULL"
                        + ")";
                statement.executeUpdate(createHistoricalQuery);
                System.out.println("Historical Table created successfully.");
            } else {
                System.out.println("Historical Table already exists. Refreshing table.");
                statement.executeUpdate("DELETE FROM `conversion_history`.`historical_data`");
                statement.executeUpdate("ALTER TABLE `conversion_history`.`historical_data` AUTO_INCREMENT = 1");
            }


            tableResultSet.close();
            statement.close();
        } catch (SQLException e) {
        	System.out.println("Incorrect username or password, MySQL not connected.");
        	cg.saveValid = false;
            //e.printStackTrace();
        }
    }
}