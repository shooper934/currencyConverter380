import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection extends APIconnection{
	
	public static Connection conn;
	
	public static void connect() {

		// Connect to the database ****Insert url, username, and password here************************************************
		String url = "jdbc:mysql://localhost/conversionhistory";
		String username = "root";
		String password = ""; //********************************PASSWORD HERE***************
	
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("connected");
			
			
		}catch(Exception e) {
			System.out.println("exception "+ e.getMessage());
		}
	}
}
