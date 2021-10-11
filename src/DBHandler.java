import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBHandler {
	private String dbName = "test";
	private String dbUserName = "naim";
	private String dbPort = "3306";
	private String dbPassword = "testpass";
	
	protected Connection makeConnection () throws SQLException {
		String url = "jdbc:mysql://localhost:" + dbPort + "/" + dbName;
		Connection connection = (Connection) DriverManager.getConnection(url, dbUserName, dbPassword);
		return connection;
	}
	
}
