import java.sql.*;

public class AdminDBHandler extends DBHandler {
	boolean checkAdmin (String name, String password) {
		boolean userExist = false;
		try {
			Connection con = makeConnection();
			String query = "select * from admin where name = '" + name + "' and password = '" + password + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			userExist = rs.next();
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		return userExist;
	}
}
